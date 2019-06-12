package Collection;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void move(double delta_x, double delta_y) {
        x = x + delta_x;
        y = y + delta_y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinate) {
            if(!(((Coordinate) obj).getX() == this.getX()))
                return false;
            return (((Coordinate) obj).getY() == this.getY());
        }
        return false;
    }
}
