package ua.ithillel.hilleltask.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;


public class LoggingFilter extends HttpFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // before servlet
        LOGGER.info("Incoming request {}", req);
        LOGGER.debug("Incoming request: {} {} {}", req.getMethod(), req.getPathInfo(), req.getQueryString());

        MDC.put("path", req.getPathInfo());

        chain.doFilter(req, res);
        // after servlet

        LOGGER.info("Response {}", res);
        LOGGER.debug("Response: {}", res.getStatus());

    }
}
