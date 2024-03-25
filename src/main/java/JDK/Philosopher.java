package JDK;

public class Philosophy {
    private String name;
    private Fork leftFork;
    private Fork rightFork;
    private int timeToThink;
    private int timeToEat;

    public Philosophy(String name, Fork leftFork, Fork rightFork, int timeToThink, int timeToEat) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.timeToThink = timeToThink;
        this.timeToEat = timeToEat;
    }

    public void eat() throws InterruptedException {
        leftFork.get();
        rightFork.get();
        Thread.sleep(timeToEat);
        leftFork.put();
        rightFork.put();
    }

    public void think() throws InterruptedException {
        Thread.sleep(timeToThink);
    }

}
