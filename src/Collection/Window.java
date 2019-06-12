package Collection;

import java.io.Serializable;

public class Window implements Serializable {
    private String name;
    private double height;

    public Window(String name, double height) {
        this.name = name;
        this.height = height;
    }
}
