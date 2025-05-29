package com.sasha.mts.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
/**
 * Конфигурация Spring-бинов для функционала HTTP-заголовков.
 * <p>
 * Регистрирует:
 * <ul>
 *   <li>{@link HeaderFilter} как Servlet Filter</li>
 *   <li>Настраивает URL-паттерны для фильтрации (все URL: /*)</li>
 *   <li>Устанавливает порядок выполнения фильтра</li>
 * </ul>
 *
 * <p>Этот класс конфигурации импортируется основной автоконфигурацией.
 *
 * @see HttpHeaderAutoConfiguration
 *
 * @author Alexandr Daev
 */
@Configuration
public class HeaderConfig {
    private static final Logger log = LoggerFactory.getLogger(HeaderConfig.class);

    @Bean
    public FilterRegistrationBean<HeaderFilter> headerFilter(HeaderProperties properties) {
        log.info("Registering HeaderFilter with URL patterns: /*");

        FilterRegistrationBean<HeaderFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HeaderFilter(properties));
        registration.addUrlPatterns("/*");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);

        log.debug("HeaderFilter registered with order: {}", Ordered.LOWEST_PRECEDENCE);
        return registration;
    }
}