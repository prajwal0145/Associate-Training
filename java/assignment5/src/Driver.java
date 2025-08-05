public class Driver {
    private final String id;
    private boolean isBusy;

    public Driver(String id) {
        this.id = id;
        this.isBusy = false;
    }

    public String getId() {
        return id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public String toString() {
        return "Driver " + id;
    }
}