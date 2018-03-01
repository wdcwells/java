package com.wdc.test.filters;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "first", urlPatterns = "/*")
public class FirstFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("fist filter before");
        chain.doFilter(request, response);
        System.out.println("fist filter after");
    }

    @Override
    public void destroy() {

    }
}
