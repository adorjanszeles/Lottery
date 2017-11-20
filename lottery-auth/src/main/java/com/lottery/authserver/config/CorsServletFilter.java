package com.lottery.authserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Configuration class a CORS filter számára.
 */
public class CorsServletFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorsServletFilter.class);
    private static final String OPTIONS_METHOD = "OPTIONS";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        CorsServletFilter.LOGGER.debug("Cors filter...");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Max-Age", "3600");
        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (CorsServletFilter.OPTIONS_METHOD.equals(request.getMethod())) {
            CorsServletFilter.LOGGER.debug("Cors filter... OPTIONS kérés kész!");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            // pass the request along the filter chain
            CorsServletFilter.LOGGER.debug("Cors filter... kész!");
            filterChain.doFilter(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
