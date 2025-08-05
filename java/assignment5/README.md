# Java Ride-Hailing Concurrency Simulation

This project is a backend simulation of a ride-hailing application (like Uber or Lyft) designed to demonstrate core Java concurrency principles. It simulates a scenario where multiple passengers (threads) compete to book a limited number of available drivers (shared resources).

The primary goal is to manage concurrent access to the driver pool safely, ensuring that no two passengers book the same driver at the same time and that passengers wait gracefully when all drivers are busy.

---

## Core Concurrency Concepts Demonstrated

This simulation is a practical example of solving a classic "bounded-resource" problem using the following Java concurrency utilities:

* **`ExecutorService`**: Manages a pool of threads to run passenger tasks efficiently. Instead of manually creating and starting 20 threads, we submit 20 `Passenger` runnables to the service, letting it handle the thread lifecycle.

* **`Semaphore`**: Acts as the primary gatekeeper for the driver pool. It is initialized with a number of "permits" equal to the number of drivers (10). A passenger must acquire a permit before they can even attempt to book a driver. If no permits are available, the passenger thread blocks (waits) until one is released. This elegantly controls the number of concurrent rides.

* **`ReentrantLock`**: Protects the critical section where a passenger scans the driver list, finds an available one, and marks them as busy. The lock ensures that this find-and-assign operation is atomic, preventing a race condition where two passengers might see the same available driver and try to book them simultaneously.

* **`CountDownLatch`**: Used for coordination. The main application thread needs to wait for all 20 passengers to complete their journey before it can shut down the simulation. The latch is initialized with a count of 20, and each passenger decrements the count upon finishing their task. The main thread calls `latch.await()` to pause until the count reaches zero.

---

## Code Structure

The project is organized into four distinct classes to promote separation of concerns:

* **`RideHailingApp.java`**: The main entry point of the application. It sets up the simulation parameters (number of drivers/passengers), initializes the `ExecutorService` and `CountDownLatch`, creates all the passenger tasks, and waits for the simulation to complete before shutting down.

* **`RideHailingService.java`**: The core of the simulation. It owns the shared resources: the list of `Driver` objects, the `Semaphore`, and the `Lock`. It contains the central `bookRide()` logic that passengers use.

* **`Passenger.java`**: A simple `Runnable` task representing a passenger. Each instance is submitted to the `ExecutorService`. Its `run()` method contains the logic for a single passenger's journey: requesting a ride, waiting for it, and signaling completion.

* **`Driver.java`**: A simple POJO (Plain Old Java Object) that models a driver, containing an ID and a boolean `isBusy` status.

---

## How to Compile and Run

### Prerequisites
* Java Development Kit (JDK) 8 or newer.

### Steps
1.  Place all four `.java` files (`Driver.java`, `Passenger.java`, `RideHailingService.java`, and `RideHailingApp.java`) in the same directory.

2.  Open a terminal or command prompt and navigate to that directory.

3.  Compile all the Java source files:
    ```bash
    javac *.java
    ```

4.  Run the main application:
    ```bash
    java RideHailingApp
    ```

---

## Sample Output

The exact order of events will vary slightly with each run due to thread scheduling. However, the output will follow this general pattern:

```text
Starting Ride-Hailing Simulation...
Ride Hailing Service is online with 10 drivers.
Dispatching 20 passengers...
Main thread is waiting for all passengers to finish...
Passenger 1 is looking for a ride...
Passenger 2 is looking for a ride...
...
>>> Permit acquired for Passenger 1. Finding a driver.
✅ MATCH: Passenger 1 --> Driver A
   Passenger 1 is on a ride with Driver A...
>>> Permit acquired for Passenger 2. Finding a driver.
✅ MATCH: Passenger 2 --> Driver B
   Passenger 2 is on a ride with Driver B...
...
// After all 10 drivers are booked
Passenger 11 is looking for a ride...
Passenger 12 is looking for a ride...
// Passengers 11 and 12 wait here
...
   Passenger 1 has finished the ride with Driver A.
Driver A is now available.
<<< Permit released by Passenger 1.
>>> Permit acquired for Passenger 11. Finding a driver.
✅ MATCH: Passenger 11 --> Driver A
   Passenger 11 is on a ride with Driver A...
...
// This continues until all passengers are served
...
All passengers have completed their rides. Shutting down.
Simulation finished.

