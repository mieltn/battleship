import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private int opponentShipsSunk;

    public Player(String name, Board board) {
        this.name  = name;
        this.board = board;
        this.opponentShipsSunk = 0;
    }

    public boolean takeTurn(Player targetPlayer) {

        boolean gameEnded = false;
        boolean tookTurn = false;
        Scanner s = new Scanner(System.in);

        while (!tookTurn) {
            System.out.println(this.getName() + "'s turn: ");
            
            int row = s.nextInt();
            int col = s.nextInt();

            Square square = targetPlayer.getBoard().get()[row][col];
            if (square.getIsShot()) {
                System.out.println("Choose a square that hasn't been shot yet");
                continue;
            }
            square.setIsShot(true);

            if (square.getHasShip()) {
                System.out.println("On target");
            
                Battleship ship = square.getShip();
                if (ship.getRemainingHitsToSink() - 1 == 0) {
                    ship.setIsSunk(true);
                    this.setOpponentShipsSunk(this.getOpponentShipsSunk() + 1);

                    if (this.getOpponentShipsSunk() == 5) {
                        gameEnded = true;
                    }

                }
                ship.setRemainingHitsToSink(ship.getRemainingHitsToSink() - 1);
                continue;
            } else {
                tookTurn = true;
                System.out.println("Missed");
            }
        }
        s.close();
        return gameEnded;
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

    public void setOpponentShipsSunk(int opponentShipsSunk) {
        this.opponentShipsSunk = opponentShipsSunk;
    }
}
