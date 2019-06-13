package Collection;

import java.io.Serializable;

public class Fortress extends PhysicalObject implements Serializable {
    private Wall wall;
    private Window window;

    public Fortress(String name, Rectangle rectangle,
                    Wall wall, Window window) {
        super(name,rectangle);
        this.wall = wall;
        this.window = window;
    }

    public Fortress() {
        super("Default name", new Rectangle(new Coordinate(0,0), 0,0));
    }




    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Fortress) {
            if (((Fortress) obj).rectangle.getLength() != this.rectangle.getLength())
                return false;
            return ((Fortress) obj).rectangle.getWidth() == this.rectangle.getWidth();
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (int)this.rectangle.getLength() + (int)this.rectangle.getWidth();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: " + getName() + "\n");
        builder.append("Length: " + rectangle.getLength() + "\n");
        builder.append(("Width: " + rectangle.getWidth() + "\n"));
        return builder.toString();
    }

}
