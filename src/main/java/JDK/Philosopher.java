package JDK;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    private String name;
    private int leftFork;
    private int rightFork;
    private int countToEat;
    private CountDownLatch cdl;
    private Table table;
    private Random random;

    public Philosopher(String name, int leftFork, int rightFork, CountDownLatch cdl, Table table) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.table = table;
        this.cdl = cdl;
        random = new Random();
        countToEat = 0;
    }

    public void eat() throws InterruptedException {
        if (table.tryGetForks(leftFork, rightFork)) {
            System.out.println(name + " Начал есть" + " используя вилки " + leftFork + " и " + rightFork);
            Thread.sleep(random.nextInt(2000));
            System.out.println(name + " Закончил есть");
            table.putForks(leftFork, rightFork);
            countToEat++;
        }
    }

    public void think() throws InterruptedException {
        Thread.sleep(random.nextInt(2000));
    }


    public void run() {
        while (countToEat < 3) {
            try {
                think();
                eat();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Наелся до отвала " + name);
            cdl.countDown();
        }
    }
}


