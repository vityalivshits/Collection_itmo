package Collection;

import java.io.Serializable;

public class Wall implements Serializable {
    private String name;
    private double thickness;

    public Wall(String name, double thickness) {
        this.name = name;
        this.thickness = thickness;
    }
}
