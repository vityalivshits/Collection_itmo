package Collection;

import java.io.Serializable;

public class Rectangle implements Serializable {
    private Coordinate coordinate;
    private double length;
    private double width;

   public Rectangle(Coordinate coordinate, double length, double width) {
        this.coordinate = coordinate;

        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }


    boolean radiusIntersection(Coordinate coordinate, double radius) {
        double x;
        double y1, y2;
        for (x = coordinate.getX() - radius; x <= coordinate.getX() + radius; x+=0.1) {
            double root = Math.sqrt(-coordinate.getX() * coordinate.getX() + 2 * coordinate.getX() * x + radius * radius - x * x);
            y1 = coordinate.getY() - root;
            y2 = coordinate.getY() + root;
            if (intersection(new Coordinate(x, y1)) || intersection(new Coordinate(x, y2)))
                return true;
        }
        return false;
    }

    private boolean intersection(Coordinate coordinate) {
        return xIntersection(coordinate) && yIntersection(coordinate);
    }

    private boolean xIntersection(Coordinate coordinate) {
        return coordinate.getX() >= this.coordinate.getX() && coordinate.getX() <= this.coordinate.getX() + length;
    }

    private boolean yIntersection(Coordinate coordinate) {
        return coordinate.getY() >= this.coordinate.getY() && coordinate.getY() <= this.coordinate.getY() + width;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            if (!(((Rectangle) obj).getLength() == this.getLength()))
                return false;
            if (!(((Rectangle) obj).getWidth() == this.getWidth())) {
                return false;
            }
                return (((Rectangle) obj).getCoordinate().equals(this.getCoordinate()));
        }
        return false;
    }

    private Coordinate getCoordinate() {
        return coordinate;
    }
}
