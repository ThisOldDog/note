package pers.dog.note.component.application;

import javafx.stage.Stage;
import pers.dog.note.component.application.status.StageStatus;
import pers.dog.note.component.logger.Logger;
import pers.dog.note.component.logger.factory.LoggerFactory;
import pers.dog.note.component.storage.FileStorage;
import pers.dog.note.util.JsonUtils;

import java.util.Optional;

/**
 * @author 废柴 2021/2/28 16:39
 */
public class ApplicationStatusManager {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStatusManager.class);
    private static final String CACHE_PATH = ".cache";
    private static final String CACHE_STAGE_STATUS_FILE = "stage_status.json";
    private final Stage stage;
    private final FileStorage stageStatusFileStore = new FileStorage(CACHE_STAGE_STATUS_FILE, CACHE_PATH);

    public ApplicationStatusManager(Stage stage) {
        this.stage = stage;
    }

    public void storeStatus() {
        storeStageStatus();
    }

    public void resumeStatus() {
        resumeStageStatus();
    }

    public void storeStageStatus() {
        StageStatus stageStatus = new StageStatus();
        if (stage != null) {
            stageStatus.setMaximized(stage.isMaximized())
                    .setHeight(stage.getHeight())
                    .setWidth(stage.getWidth())
                    .setPosition(new StageStatus.Position()
                            .setX(stage.getX())
                            .setY(stage.getY()));

        }
        stageStatusFileStore.overwrite(JsonUtils.parserString(stageStatus, (o, e) -> {
            logger.error("An error occurred during store stage status.", e);
            return "{}";
        }));
    }

    private void resumeStageStatus() {
        StageStatus stageStatus = Optional.ofNullable(stageStatusFileStore.read()).map(value -> JsonUtils.valueOf(value, StageStatus.class, (j, e) -> {
            logger.error("An error occurred during read stage status.", e);
            return new StageStatus();
        })).orElseGet(StageStatus::new);
        if (stageStatus.isMaximized()) {
            stage.setMaximized(true);
        } else {
            stage.setHeight(stageStatus.getHeight());
            stage.setWidth(stageStatus.getWidth());
            if (stageStatus.getPosition() != null) {
                stage.setX(stageStatus.getPosition().getX());
                stage.setY(stageStatus.getPosition().getY());
            }
        }
    }
}
