package com.sasha.mts.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;
import jakarta.servlet.Filter;

/**
 * Автоконфигурация Spring Boot для добавления кастомных HTTP-заголовков.
 * <p>
 * Автоматически активируется при выполнении условий:
 * <ul>
 *   <li>Приложение является сервлетным веб-приложением</li>
 *   <li>В classpath присутствуют классы Servlet API и DispatcherServlet</li>
 * </ul>
 *
 * <p>Конфигурация:
 * <ol>
 *   <li>Включает поддержку {@link HeaderProperties}</li>
 *   <li>Импортирует {@link HeaderConfig} для регистрации бинов</li>
 * </ol>
 *
 * <p>Файл регистрации: {@code META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports}
 *
 * @author Alexandr Daev
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Filter.class, DispatcherServlet.class})
@EnableConfigurationProperties(HeaderProperties.class)
@Import(HeaderConfig.class)
public class HttpHeaderAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(HttpHeaderAutoConfiguration.class);

    public HttpHeaderAutoConfiguration(HeaderProperties properties) {
        log.info("HTTP Header AutoConfiguration loaded");
        log.debug("Configuration properties: enabled={}, name={}, value={}",
                properties.isEnabled(),
                properties.getName(),
                properties.getValue());
    }
}