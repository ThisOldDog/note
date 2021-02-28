package pers.dog.note.component.logger.factory.impl;

import pers.dog.note.component.logger.factory.LoggerFactoryConfig;
import pers.dog.note.component.logger.factory.LoggerLevel;
import pers.dog.note.component.storage.FileStorage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author 废柴 2021/2/28 16:35
 */
public class FileLogger extends ConsoleLogger {
    private static final String LOG_PATH = ".log";
    private static final DateTimeFormatter LOG_FILE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private final Class<?> actionClass;
    private final LoggerFactoryConfig loggerFactoryConfig;
    private FileStorage fileStorage;

    public FileLogger(Class<?> actionClass, LoggerFactoryConfig loggerFactoryConfig) {
        super(actionClass.getCanonicalName(), loggerFactoryConfig);
        this.actionClass = actionClass;
        this.loggerFactoryConfig = loggerFactoryConfig;
    }

    @Override
    public String error(String message, Throwable e, Object... args) {
        String content = super.error(message, e, args);
        if (loggerFactoryConfig.getLoggerLevel().ordinal() <= LoggerLevel.ERROR.ordinal()) {
            getFileStorage().appendLine(content);
        }
        return content;
    }

    @Override
    String getLoggerName() {
        return actionClass.getCanonicalName();
    }

    private synchronized FileStorage getFileStorage() {
        String logFileName = getLogFileName();
        if (fileStorage == null || !Objects.equals(logFileName, fileStorage.getFile())) {
            fileStorage = new FileStorage(getLogFileName(), LOG_PATH);
        }
        return fileStorage;
    }

    private String getLogFileName() {
        return LocalDate.now().format(LOG_FILE_FORMATTER);
    }

    public LoggerFactoryConfig getLoggerFactoryConfig() {
        return loggerFactoryConfig;
    }
}
