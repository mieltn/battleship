public class Player {
    private String name;
    private Board board;
    private int opponentShipsSunk;

    public Player(String name, Board board) {
        this.name  = name;
        this.board = board;
        this.opponentShipsSunk = 0;
    }

    public boolean takeShotAt(Player player, int row, int col) {
        Square square = player.getBoard().get()[row][col];
        if (square.getHasShip() && !square.getIsShot()) {
            square.setIsShot(true);

            Battleship ship = square.getShip();
            if (ship.getRemainingHitsToSink() - 1 == 0) {
                ship.setIsSunk(true);
                this.opponentShipsSunk += 1;
            }
            ship.setRemainingHitsToSink(ship.getRemainingHitsToSink() - 1);

            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getOpponentShipsSunk() {
        return this.opponentShipsSunk;
    }
}
