package pers.dog.note.component.logger;

/**
 * @author 废柴 2021/2/28 16:27
 */
public interface Logger {

    String error(String message, Throwable e, Object... args);
}
