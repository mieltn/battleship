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

    public boolean takeTurn(Scanner s, Player targetPlayer) {

        boolean gameEnded = false;
        boolean tookTurn = false;

        // run until user finishes attempt
        while (!tookTurn) {
            System.out.println(targetPlayer.getBoard().toString());
            System.out.println(this.getName() + "'s turn: ");

            int row = s.nextInt();
            int col = s.nextInt();

            Square square = targetPlayer.getBoard().getArr()[row][col];

            // if square was already shot ask for new coords
            if (square.getIsShot()) {
                System.out.println("Choose a square that hasn't been shot yet");
                continue;
            }
            square.setIsShot(true);

            // if sqaure has ship get ship and update info
            // if last ship was sunk set gameended to true and break
            if (square.getHasShip()) {
                System.out.println("On target");
            
                Battleship ship = square.getShip();
                if (ship.getRemainingHitsToSink() - 1 == 0) {
                    ship.setIsSunk(true);
                    this.setOpponentShipsSunk(this.getOpponentShipsSunk() + 1);
                    
                    if (this.getOpponentShipsSunk() == targetPlayer.getBoard().getNumberOfShips()) {
                        gameEnded = true;
                        break;
                    }

                }
                ship.setRemainingHitsToSink(ship.getRemainingHitsToSink() - 1);
                continue;
            } else {
                tookTurn = true;
                System.out.println("Missed");
            }
        }
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
