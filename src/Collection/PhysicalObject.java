package Collection;

import java.io.Serializable;

public class PhysicalObject implements Serializable {
    private String name;
    Rectangle rectangle;

    PhysicalObject(String name, Rectangle rectangle){
        setName(name);
        this.rectangle = rectangle;
    }

    private Rectangle getRectangle() {
        return rectangle;
    }

    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PhysicalObject){
            if(!((PhysicalObject) obj).getName().equals(this.getName()))
                return false;
            return ((PhysicalObject) obj).getRectangle().equals(this.rectangle);

        }
        return false;
    }
}
