package com.sasha.mts.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
/**
 * Servlet Filter для добавления конфигурируемого HTTP-заголовка в ответы.
 * <p>
 * Фильтр проверяет включена ли функциональность ({@code enabled} флаг) и добавляет
 * указанный заголовок во все HTTP-ответы. Реализует стандартный интерфейс {@link Filter}.
 *
 * @see HeaderProperties
 * @see HeaderConfig
 *
 * @author Alexandr Daev
 */
public class HeaderFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(HeaderFilter.class);
    private final HeaderProperties properties;

    public HeaderFilter(HeaderProperties properties) {
        this.properties = properties;
        log.debug("HeaderFilter initialized with properties: {}", properties);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (properties.isEnabled()) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String headerName = properties.getName();
            String headerValue = properties.getValue();

            httpResponse.addHeader(headerName, headerValue);
            log.debug("Added header [{}: {}] to response", headerName, headerValue);
        } else {
            log.trace("Header addition is disabled");
        }
        chain.doFilter(request, response);
    }
}