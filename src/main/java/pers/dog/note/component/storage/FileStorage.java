package pers.dog.note.component.storage;

import pers.dog.note.component.logger.Logger;
import pers.dog.note.component.logger.factory.LoggerFactory;
import pers.dog.note.component.logger.factory.LoggerFactoryConfig;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author 废柴 2021/2/28 16:47
 */
public class FileStorage {
    private static final Logger logger = LoggerFactory.getLogger(FileStorage.class, new LoggerFactoryConfig().setEndurance(false));
    private final String[] path;
    private final String file;
    private final Path filePath;
    private Charset charset;

    public FileStorage(String file, String... path) {
        this(file, StandardCharsets.UTF_8, path);
    }

    public FileStorage(String file, Charset charset, String... path) {
        if (path == null || path.length == 0) {
            path = new String[]{"."};
        }
        this.file = file;
        this.charset = charset;
        this.path = path;
        Path targetPath = Path.of(path[0]);
        for (int i = 1; i < path.length; i++) {
            targetPath = targetPath.resolve(path[i]);
        }
        this.filePath = targetPath.resolve(this.file);
    }

    public FileStorage setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    public String[] getPath() {
        return path;
    }


    public String getFile() {
        return file;
    }

    public void appendLine(String content) {
        try {
            Files.writeString(filePath, String.format("%s%n", content), charset,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.DSYNC);
        } catch (IOException e) {
            logger.error("An error occurred while writing the file %s", e, filePath);
        }
    }

    public void overwrite(String content) {
        try {
            prepareDirectory();
            Files.writeString(filePath, content, charset,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, StandardOpenOption.DSYNC);
        } catch (IOException e) {
            logger.error("An error occurred while writing the file %s", e, filePath);
        }
    }

    private void prepareDirectory() {
        Path directory = filePath.getParent();
        if (!Files.exists(directory, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                logger.error("An error occurred while creating the directory %s", e, directory);
            }
        }
    }

    public String read() {
        try {
            if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
                return null;
            }
            return Files.readString(filePath, charset);
        } catch (IOException e) {
            logger.error("An error occurred while reading the file %s", e, filePath);
            return null;
        }
    }
}
