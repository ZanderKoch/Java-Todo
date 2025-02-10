package sequencers;

public abstract class IdSequencer {
    private int currentId = 0;

    protected IdSequencer() {}

    public int nextId() {
        return ++currentId;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }
}