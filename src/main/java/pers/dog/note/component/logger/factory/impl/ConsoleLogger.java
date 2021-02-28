package pers.dog.note.component.logger.factory.impl;

import pers.dog.note.component.logger.factory.LoggerFactoryConfig;

/**
 * @author 废柴 2021/2/28 17:14
 */
public class ConsoleLogger extends AbstractLogger {
    private final System.Logger logger;
    private final LoggerFactoryConfig loggerFactoryConfig;

    public ConsoleLogger(String loggerName, LoggerFactoryConfig loggerFactoryConfig) {
        logger = System.getLogger(loggerName);
        this.loggerFactoryConfig = loggerFactoryConfig;
    }

    @Override
    public String error(String message, Throwable e, Object... args) {
        String content = getContent(message, e, args);
        logger.log(System.Logger.Level.ERROR, content);
        return content;
    }

    @Override
    String getLoggerName() {
        return logger.getName();
    }
}
