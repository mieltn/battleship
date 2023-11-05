public class Square {
    private int row;
    private int col;
    private boolean hasBattleship;
    // used to indicate if a square is a neighbour of another ship
    // if so, the square cannot be used to place another ship
    private boolean isBattleshipNeighbour;
    private Battleship battleship;
    private boolean isShot;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean getHasShip() {
        return this.hasBattleship;
    }

    public boolean getIsBattleshipNeighbour() {
        return this.isBattleshipNeighbour;
    }

    public Battleship getShip() {
        return this.battleship;
    }

    public boolean getIsShot() {
        return this.isShot;
    }

    public void setHasShip(boolean hasShip) {
        this.hasBattleship = hasShip;
    }

    public void setIsBattleshipNeighbour(boolean isBattleshipNeighbour) {
        this.isBattleshipNeighbour = isBattleshipNeighbour;
    }

    public void setShip(Battleship ship) {
        this.battleship = ship;
    }

    public void setIsShot(boolean isShot) {
        this.isShot = isShot;
    }

    // formating representation of square
    // shot, shot and has ship, not shot
    public String toString() {
        String value = "";
        if (this.getIsShot() && this.getHasShip()) {
            value += "X";
        } else if (this.getIsShot()) {
            value += "O";
        } else {
            value += "-";
        }
        return String.format(" %s ", value);
    }
}