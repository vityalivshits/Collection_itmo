package Collection;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FortressList implements Serializable {

    private Date date;



    private List<Fortress> fortresses = new CopyOnWriteArrayList<>();

    public FortressList() {
        setDate();
    }

    public void removeLower(Fortress fortress) {
        FortressComparator fortressComparator = new FortressComparator();
        for( Fortress f: fortresses) {
            if (fortressComparator.compare(fortress, f) > 0) {
                removeAll(f);
            }
        }
    }

    public synchronized void show() {
        String[] result = {""};
        fortresses.stream().forEach(c->result[0]+=c.toString());
        System.out.println(result[0]);
    }

    public void clear() {
        fortresses.clear();
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
    private void setDate() {
        date = new Date();
    }

    public List<Fortress> getFortresses() {
        return fortresses;
    }


}
