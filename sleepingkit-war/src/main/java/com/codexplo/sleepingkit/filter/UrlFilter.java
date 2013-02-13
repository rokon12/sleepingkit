package com.codexplo.sleepingkit.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/11/13
 * Time: 11:14 AM
 */
public class UrlFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(UrlFilter.class);
    private final static  String TAG = "sleepingkit";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        logger.debug(TAG, "Requested URI "+ uri);

        if (uri.startsWith("resources") || uri.contains("index")){
             filterChain.doFilter(request,response);
        }else {
            request.getRequestDispatcher("/WEB-INF/faces/"+uri+".xhtml").forward(request,response);
        }
    }

    @Override
    public void destroy() {
    }
}
