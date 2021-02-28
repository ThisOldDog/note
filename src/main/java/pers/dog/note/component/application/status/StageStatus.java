package pers.dog.note.component.application.status;

/**
 *
 * @author 废柴 2021/2/28 16:40
 */
public class StageStatus {
    public static class Position {
        private double x;
        private double y;

        public double getX() {
            return x;
        }

        public Position setX(double x) {
            this.x = x;
            return this;
        }

        public double getY() {
            return y;
        }

        public Position setY(double y) {
            this.y = y;
            return this;
        }
    }

    private boolean maximized = true;
    private double height;
    private double width;
    private Position position;

    public boolean isMaximized() {
        return maximized;
    }

    public StageStatus setMaximized(boolean maximized) {
        this.maximized = maximized;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public StageStatus setHeight(double height) {
        this.height = height;
        return this;
    }

    public double getWidth() {
        return width;
    }

    public StageStatus setWidth(double width) {
        this.width = width;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public StageStatus setPosition(Position position) {
        this.position = position;
        return this;
    }
}
