package Collection;

import java.util.Comparator;

public class FortressComparator implements Comparator<Fortress> {
    @Override
    public int compare(Fortress f1, Fortress f2) {
        double square1 = f1.rectangle.getWidth() * f1.rectangle.getLength();
        double square2 = f2.rectangle.getWidth() * f2.rectangle.getLength();

        return Double.compare(square1, square2);
    }
}
