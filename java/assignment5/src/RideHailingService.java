
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RideHailingService {


    private final Semaphore availableDriversSemaphore;


    private final Lock driverPoolLock = new ReentrantLock();


    private final List<Driver> drivers;



    public RideHailingService(int numberOfDrivers) {

        this.availableDriversSemaphore = new Semaphore(numberOfDrivers, true); // true for fairness


        this.drivers = IntStream.range(0, numberOfDrivers)
                .mapToObj(i -> new Driver(String.valueOf((char) ('A' + i))))
                .collect(Collectors.toList());
        System.out.println("Ride Hailing Service is online with " + numberOfDrivers + " drivers.");
    }




    public void bookRide(Passenger passenger) {
        try {

            System.out.println(passenger + " is looking for a ride...");
            availableDriversSemaphore.acquire();
            System.out.println(">>> Permit acquired for " + passenger + ". Finding a driver.");


            Driver assignedDriver = findAndAssignDriver(passenger);

            if (assignedDriver != null) {
                simulateRide(passenger, assignedDriver);

                releaseDriver(assignedDriver);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(passenger + " was interrupted while waiting for a ride.");
        } finally {

            availableDriversSemaphore.release();
            System.out.println("<<< Permit released by " + passenger + ".");
        }
    }


    private Driver findAndAssignDriver(Passenger passenger) {
        driverPoolLock.lock();
        try {
            for (Driver driver : drivers) {
                if (!driver.isBusy()) {
                    driver.setBusy(true);
                    System.out.println("✅ MATCH: " + passenger + " --> " + driver);
                    return driver;
                }
            }

            return null;
        } finally {
            driverPoolLock.unlock();
        }
    }


    private void simulateRide(Passenger passenger, Driver driver) throws InterruptedException {
        System.out.println("   " + passenger + " is on a ride with " + driver + "...");

        Thread.sleep((long) (Math.random() * 2000) + 1000);
        System.out.println("   " + passenger + " has finished the ride with " + driver + ".");
    }


    private void releaseDriver(Driver driver) {
        driverPoolLock.lock();
        try {
            driver.setBusy(false);
            System.out.println(driver + " is now available.");
        } finally {
            driverPoolLock.unlock();
        }
    }
}