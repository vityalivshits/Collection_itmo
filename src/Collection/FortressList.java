package Collection;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FortressList implements Serializable {

    private Date date;



    private List<Fortress> fortresses = new CopyOnWriteArrayList<>();

    public FortressList() {
        date = new Date();
    }

    public void removeLower(Fortress fortress) {
        FortressComparator fortressComparator = new FortressComparator();
        for( Fortress f: fortresses) {
            if (fortressComparator.compare(fortress, f) > 0) {
                removeAll(f);
            }
        }
    }

    public int getSize() {
        return fortresses.size();
    }

    public synchronized void show() {
        StringBuilder builder = new StringBuilder();

        for(Fortress f: fortresses) {
            builder.append(f.toString()).append("\n");
        }
        System.out.println(builder.toString());
    }

    public FortressList clear() {
        fortresses.clear();
        return this;
    }

    public void removeAll(Fortress fortress) {
        while(fortresses.contains(fortress)) {
            fortresses.remove(fortress);
        }
    }

    public void add(Fortress fortress) {
        fortresses.add(fortress);
    }


    public void remove(Fortress fortress) {
        fortresses.remove(fortress);
    }

    public void info() {
        System.out.println(("Initialization date: " + date + "\n"
                + "Amount of elements: " + fortresses.size()));
    }
    public void setDate() {
        date = new Date();
    }

    public List<Fortress> getFortresses() {
        return fortresses;
    }


}
