package com.wdc.test.filters;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(2)
@WebFilter(filterName = "hello", urlPatterns = "/hello/*")
public class HelloFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("hello filter before");
        chain.doFilter(request, response);
        System.out.println("hello filter after");
    }

    @Override
    public void destroy() {

    }
}
