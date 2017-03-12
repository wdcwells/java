package com.wdc.test.config;

import com.wdc.test.entity.UserEntity;
import com.wdc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangdachong on 2017/3/7.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String AUTH_HEADER_NAME = "Authorization";
    public static final Map<Integer, String> USER_TOKEN_MAP = new ConcurrentHashMap<>();
    public static final Map<Integer, UserEntity> USER_DB_MAP = new ConcurrentHashMap<>();
    public static final Map<String, UserEntity> USER_LOGIN_MAP = new ConcurrentHashMap<>();

    static {
        USER_DB_MAP.put(1, new UserEntity(1, "wdc", "super"));
        USER_DB_MAP.put(2, new UserEntity(2, "wqh", "admin"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(
                        new MyTokenAuthenticationFilter(
                                new NegatedRequestMatcher(
                                        new OrRequestMatcher(
                                                new AntPathRequestMatcher("/"),
                                                new AntPathRequestMatcher("/login"),
                                                new AntPathRequestMatcher("/hello/**")
                                        )
                                )),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/", "/hello/**", "/login").permitAll()
                    .antMatchers("/user/**").hasAnyAuthority("user","admin","super")
                    .antMatchers("/admin/**").hasAnyAuthority("admin", "super")
                    .antMatchers("/super/**").hasAuthority("super")
                    .anyRequest().authenticated();
    }

    private class MyTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

        public MyTokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
            super(requiresAuthenticationRequestMatcher);
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
            String auth_token = request.getHeader(AUTH_HEADER_NAME);
            if (null == auth_token) {
                response.sendError(401, "TOKEN WRONG");
                return null;
            } else {
                MyTokenAuthentication toBeAuthed = new MyTokenAuthentication(auth_token);
                return authenticationManager().authenticate(toBeAuthed);
            }
        }

        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            SecurityContextHolder.getContext().setAuthentication(authResult);
            getRememberMeServices().loginSuccess(request, response, authResult);
            chain.doFilter(request, response);
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new MyAuthenticationManager();
    }

    private class MyAuthenticationManager implements AuthenticationManager {
        @Autowired
        private UserService userService;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            MyAuthentication myAuthentication = authentication instanceof MyAuthentication ? ((MyAuthentication) authentication) : null;
            String token = null;
            UserEntity login = null;
            if (null != myAuthentication) {
                token = myAuthentication.getToken();
                login = userService.findByToken(token);
            }
            if (null == login) throw new MyAuthenticationException("TOKEN WRONG");

            Authentication authenticated = new MyTokenAuthentication(token, login);
            authenticated.setAuthenticated(true);
            return authenticated;
        }

        private class MyAuthenticationException extends AuthenticationException {

            public MyAuthenticationException(String msg) {
                super(msg);
            }
        }
    }

}
