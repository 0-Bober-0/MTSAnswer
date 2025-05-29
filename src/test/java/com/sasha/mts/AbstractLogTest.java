package com.sasha.mts;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractLogTest {
    protected ListAppender<ILoggingEvent> logAppender;
    private LoggerContext loggerContext;
    private Logger originalLogger;

    @BeforeEach
    void setupLogCapture() {
        // Получаем контекст логгера
        loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Создаем и настраиваем appender
        logAppender = new ListAppender<>();
        logAppender.start();

        // Получаем конкретный логгер по имени класса
        String loggerName = getLoggerName();
        originalLogger = loggerContext.getLogger(loggerName);

        // Сохраняем оригинальные настройки
        originalLogger.detachAndStopAllAppenders();

        // Настраиваем наш appender
        originalLogger.addAppender(logAppender);
        originalLogger.setLevel(ch.qos.logback.classic.Level.TRACE);
    }

    @AfterEach
    void teardownLogCapture() {
        // Восстанавливаем оригинальное состояние
        if (originalLogger != null) {
            originalLogger.detachAppender(logAppender);
            originalLogger.setLevel(ch.qos.logback.classic.Level.INFO);
        }
        if (logAppender != null) {
            logAppender.stop();
        }
    }

    protected List<String> getLogMessages() {
        return logAppender.list.stream()
                .map(ILoggingEvent::getFormattedMessage)
                .collect(Collectors.toList());
    }

    protected List<ILoggingEvent> getLogEvents() {
        return logAppender.list;
    }

    // Метод для переопределения в тестах
    protected abstract String getLoggerName();
}