package pers.dog.note.component.logger.factory.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import pers.dog.note.component.logger.Logger;
import pers.dog.note.component.logger.factory.LoggerLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 废柴 2021/2/28 17:18
 */
public abstract class AbstractLogger implements Logger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public String getContent(String message, Throwable e, Object... args) {
        return getTime() +
                " " +
                LoggerLevel.ERROR +
                " " +
                getThread() +
                " " +
                getLoggerName() +
                " : " +
                (args != null && args.length > 0 ? String.format(message, args) : message) +
                "\n" +
                ExceptionUtils.getStackTrace(e);
    }


    private String getTime() {
        return LocalDateTime.now().format(FORMATTER);
    }

    private String getThread() {
        return String.format("[%15s]", Thread.currentThread().getName());
    }

    abstract String getLoggerName();
}
