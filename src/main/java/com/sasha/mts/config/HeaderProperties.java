package com.sasha.mts.config;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Конфигурационные свойства для настройки HTTP-заголовков.
 * <p>
 * Позволяет настраивать:
 * <ul>
 *   <li>Имя добавляемого заголовка</li>
 *   <li>Значение заголовка</li>
 *   <li>Флаг активации функционала</li>
 * </ul>
 *
 * <p>Пример использования в application.properties:
 * <pre>{@code
 * http.header.name = X-Custom-Header
 * http.header.value = CustomValue
 * http.header.enabled = true
 * }</pre>
 *
 * @see HttpHeaderAutoConfiguration
 *
 * @author Alexandr Daev
 */

@Getter
@ConfigurationProperties(prefix = "http.header")
public class HeaderProperties {
    private static final Logger log = LoggerFactory.getLogger(HeaderProperties.class);

    private String name = "Foo";
    private String value = "Bar";
    private boolean enabled = true;

    public void setName(String name) {
        log.info("Changing header name from '{}' to '{}'", this.name, name);
        this.name = name;
    }

    public void setValue(String value) {
        log.info("Changing header value from '{}' to '{}'", this.value, value);
        this.value = value;
    }

    public void setEnabled(boolean enabled) {
        log.info("{} header injection", enabled ? "Enabling" : "Disabling");
        this.enabled = enabled;
    }
}