package JDK;

import java.util.concurrent.CountDownLatch;

public class Table extends Thread {
    private final int PHILOSOPHER_COUNT = 5;
    private Fork[] forks;
    private Philosopher[] philosophers;
    private CountDownLatch cdl;

    public void run() {
        System.out.println("Началась конференция");
        try {
            thinkingProgress();
            cdl.await();

        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println("Все накушались");
    }

    public Table() {
        forks = new Fork[PHILOSOPHER_COUNT];
        philosophers = new Philosopher[PHILOSOPHER_COUNT];
        cdl = new CountDownLatch(PHILOSOPHER_COUNT);
        init();
    }

    public synchronized boolean tryGetForks(int leftFork, int rightFork) {
        if (!forks[leftFork].isFree() && !forks[rightFork].isFree()) {
            forks[leftFork].using(true);
            forks[rightFork].using(true);
            return true;
        }
        return false;
    }

    public void putForks(int leftFork, int rightFork) {
        forks[leftFork].using(false);
        forks[rightFork].using(false);
    }

    public void init() {
        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            forks[i] = new Fork(false);
        }

        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            philosophers[i] = new Philosopher("Philosopher " + i, i,
                    (i + 1) % PHILOSOPHER_COUNT, cdl,this);
        }

    }

    public void thinkingProgress(){
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }
}
