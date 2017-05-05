package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Canvas implements CanvasInterface {
    private final javafx.scene.canvas.Canvas canvas;
    private final GraphicsContext context;

    public Canvas(javafx.scene.canvas.Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }

    @Override public double getWidth() {
        return canvas.getWidth();
    }

    @Override public double getHeight() {
        return canvas.getHeight();
    }

    @Override public void setFill(String color) {
        context.setFill(Color.web(color));
    }

    @Override public void fillRect(double x, double y, double w, double h) {
        context.fillRect(x, y, w, h);
    }

    @Override public void fillPolygon(double[] xPoints, double[] yPoints, int nPoints) {
        context.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override public void fillArc(double x, double y, double w, double h, double startAngle, double arcExtent) {
        context.fillArc(x, y, w, h, startAngle, arcExtent, ArcType.ROUND);
    }

    @Override public void fillOval(double x, double y, double w, double h) {
        context.fillOval(x, y, w, h);
    }
}
