package sample;

public interface CanvasInterface {
    double getWidth();
    double getHeight();
    void setFill(String color);
    void fillRect(double x, double y, double w, double h);
    void fillPolygon(double[] xPoints, double[] yPoints, int nPoints);
    void fillArc(double x, double y, double w, double h, double startAngle, double arcExtent);
    void fillOval(double x, double y, double w, double h);
}
