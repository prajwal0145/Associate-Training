import java.util.concurrent.CountDownLatch;

public class Passenger implements Runnable {

    private final int id;
    private final RideHailingService service;
    private final CountDownLatch latch;

    public Passenger(int id, RideHailingService service, CountDownLatch latch) {
        this.id = id;
        this.service = service;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {

            service.bookRide(this);
        } finally {

            latch.countDown();
        }
    }

    @Override
    public String toString() {
        return "Passenger " + id;
    }
}