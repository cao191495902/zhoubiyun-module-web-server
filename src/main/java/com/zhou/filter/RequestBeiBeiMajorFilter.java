package com.zhou.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跳转到贝贝major过滤器

 **/
@Component
public class RequestBeiBeiMajorFilter implements Filter{
    FilterConfig filterConfig = null;
    private static final Logger logger = LoggerFactory.getLogger(RequestBeiBeiMajorFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String URI = request.getRequestURI();
            if (URI != null && URI.contains("/web")) {
                request.getRequestDispatcher("http://127.0.0.1:7011/"+URI).forward(request, response);
                return;
            }
            if(URI != null && URI.contains("/admin")){
                request.getRequestDispatcher("http://127.0.0.1:7001/"+URI).forward(request, response);
                return;
            }
            chain.doFilter(request, response);

        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }




}