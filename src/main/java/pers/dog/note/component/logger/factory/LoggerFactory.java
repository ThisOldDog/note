package pers.dog.note.component.logger.factory;

import pers.dog.note.component.logger.Logger;
import pers.dog.note.component.logger.factory.impl.ConsoleLogger;
import pers.dog.note.component.logger.factory.impl.FileLogger;

/**
 * Logger 工厂
 *
 * @author 废柴 2021/2/28 16:26
 */
public class LoggerFactory {

    public static Logger getLogger(Class<?> actionClass) {
        return LoggerFactory.getLogger(actionClass, new LoggerFactoryConfig());
    }

    public static Logger getLogger(Class<?> actionClass, LoggerFactoryConfig config) {
        if (!config.isEndurance()) {
            return new ConsoleLogger(actionClass.getCanonicalName(), config);
        }
        return new FileLogger(actionClass, config);
    }

}
