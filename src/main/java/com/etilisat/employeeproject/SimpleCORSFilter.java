package com.etilisat.employeeproject;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {


public SimpleCORSFilter() {
}

@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
    if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
        try {
            chain.doFilter(req, res);
        } catch(Exception e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Pre-flight");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +

                "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

@Override
public void init(FilterConfig filterConfig) {
}

@Override
public void destroy() {
}

}