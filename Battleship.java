public class Battleship {
    private boolean isSunk;
    private int remainingHitsToSink;
    private int size;

    public Battleship(int size) {
        this.isSunk = false;
        this.remainingHitsToSink = size;
        this.size = size;
    }

    public boolean getIsSunk() {
        return this.isSunk;
    }

    public int getRemainingHitsToSink() {
        return this.remainingHitsToSink;
    }

    public int getSize() {
        return this.size;
    }

    public void setIsSunk(boolean isSunk) {
        this.isSunk = isSunk;
    }

    public void setRemainingHitsToSink(int remainingHitsToSink) {
        this.remainingHitsToSink = remainingHitsToSink;
    }
}
