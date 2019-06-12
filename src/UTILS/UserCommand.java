package UTILS;

public class UserCommand {
    private String name;
    private String arg;

    public void setName(String name) {
        this.name = name;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public boolean argExists() {
        return arg != null;
    }

    public String getName() {
        return name;
    }

    public String getArg() {
        return arg;
    }
}
