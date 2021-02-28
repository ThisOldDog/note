package pers.dog.note.component.logger.factory;

/**
 * @author 废柴 2021/2/28 16:29
 */
public class LoggerFactoryConfig {
    private LoggerLevel loggerLevel = LoggerLevel.WARNING;
    private boolean endurance = true;

    public LoggerLevel getLoggerLevel() {
        return loggerLevel;
    }

    public LoggerFactoryConfig setLoggerLevel(LoggerLevel loggerLevel) {
        this.loggerLevel = loggerLevel;
        return this;
    }

    public boolean isEndurance() {
        return endurance;
    }

    public LoggerFactoryConfig setEndurance(boolean endurance) {
        this.endurance = endurance;
        return this;
    }
}
