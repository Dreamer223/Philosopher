package JDK;

public class Fork {
    private boolean isFree;
    private String name;

    public Fork(boolean isFree) {
        this.isFree = isFree;
    }

    public void using(boolean using) {
        isFree = using;
    }

    public boolean isFree() {
        return isFree;
    }
}
