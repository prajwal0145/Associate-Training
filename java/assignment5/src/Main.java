import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {


    private static final int NUMBER_OF_DRIVERS = 10;
    private static final int NUMBER_OF_PASSENGERS = 20;

    public static void main(String[] args) {
        System.out.println("Starting Ride-Hailing Simulation...");


        RideHailingService service = new RideHailingService(NUMBER_OF_DRIVERS);

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_PASSENGERS);

        CountDownLatch latch = new CountDownLatch(NUMBER_OF_PASSENGERS);


        System.out.println("Dispatching " + NUMBER_OF_PASSENGERS + " passengers...");
        for (int i = 1; i <= NUMBER_OF_PASSENGERS; i++) {
            executor.submit(new Passenger(i, service, latch));
        }

        try {

            System.out.println("Main thread is waiting for all passengers to finish...");
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread was interrupted.");
        }


        System.out.println("All passengers have completed their rides. Shutting down.");
        executor.shutdown();
        try {

            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
        }

        System.out.println("Simulation finished.");
    }
}