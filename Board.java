import java.util.Random;

public class Board {
    private Square[][] board;

    public Board(int boardSize) {
        this.board = new Square[boardSize][boardSize];
        fillWithSquares();
    }

    public void fillWithSquares() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = new Square(i, j);
            }
        }
    }

    public Square[][] get() {
        return this.board;
    }

    public void placeBattleship(int shipSize) {

        Battleship ship = new Battleship(shipSize);
        Random r = new Random();
        boolean placedSuccessfully = false;

        while (!placedSuccessfully) {
            int shipSquareRow = r.nextInt(this.board.length);
            int shipSquareCol = r.nextInt(this.board.length);
            boolean isVertical = r.nextBoolean();

            // adjust init point for the ship if it goes beyond the board
            if (isVertical) {
                if (shipSize + shipSquareRow > this.board.length) {
                    shipSquareRow -= shipSize + shipSquareRow - this.board.length;
                }
            } else {
                if (shipSize + shipSquareCol > this.board.length) {
                    shipSquareCol -= shipSize + shipSquareCol - this.board.length;
                }
            }

            // check if ship with given input can be placed without any conflicts with existing fleet
            // if not - regenerate starting point
            if (!battleshipCanBePlaced(isVertical, shipSize, shipSquareRow, shipSquareCol)) {
                continue;
            }

            // if ok - change while loop condition
            placedSuccessfully = true;

            // fill ship squares and squares surrounding newly placed ship
            fillShipSquares(ship, isVertical, shipSize, shipSquareRow, shipSquareCol);
            fillShipNeighbouringSquares(ship, isVertical, shipSize, shipSquareRow, shipSquareCol);
        }
    }

    private boolean battleshipCanBePlaced(
        boolean isVertical,
        int shipSize,
        int shipSquareRow,
        int shipSquareCol
    ) {
        boolean isFree = true;
        if (isVertical) {
            for (
                int i = Math.max(0, shipSquareRow - 1);
                i < Math.min(this.board.length, shipSquareRow + shipSize + 2);
                i++
            ) {
                for (
                    int j = Math.max(0, shipSquareCol - 1);
                    j < Math.min(this.board.length, shipSquareCol + 2);
                    j++
                ) {
                    if (
                        this.board[i][j].getHasShip() ||
                        this.board[i][j].getIsBattleshipNeighbour()
                    ) {
                        isFree = false;
                        break;
                    }
                }
            }
        } else {
            for (
                int i = Math.max(0, shipSquareRow - 1);
                i < Math.min(this.board.length, shipSquareRow + 2);
                i++
            ) {
                for (
                    int j = Math.max(0, shipSquareCol - 1);
                    j < Math.min(this.board.length, shipSquareCol + shipSize + 2);
                    j++
                ) {
                    if (
                        this.board[i][j].getHasShip() ||
                        this.board[i][j].getIsBattleshipNeighbour()
                    ) {
                        isFree = false;
                        break;
                    }
                }
            }
        }

        return isFree;
    }

    private void fillShipSquares(
        Battleship ship,
        boolean isVertical,
        int shipSize,
        int shipSquareRow,
        int shipSquareCol
    ) {
        for (int shipSquareIdx = 0; shipSquareIdx < shipSize; shipSquareIdx++) {
            Square square;
            if (isVertical) {
                square = this.board[shipSquareRow+shipSquareIdx][shipSquareCol];
            } else {
                square = this.board[shipSquareRow][shipSquareCol+shipSquareIdx];
            }
            square.setHasShip(true);
            square.setShip(ship);
        }
    }

    private void fillShipNeighbouringSquares(
        Battleship ship,
        boolean isVertical,
        int shipSize,
        int shipSquareRow,
        int shipSquareCol
    ) {
        if (isVertical) {
            for (
                int i = Math.max(0, shipSquareRow - 1);
                i < Math.min(this.board.length, shipSquareRow + shipSize + 2);
                i++
            ) {
                for (
                    int j = Math.max(0, shipSquareCol - 1);
                    j < Math.min(this.board.length, shipSquareCol + 2);
                    j++
                ) {
                    if (!this.board[i][j].getHasShip()) {
                        this.board[i][j].setIsBattleshipNeighbour(true);
                    }
                }
            }
        } else {
            for (
                int i = Math.max(0, shipSquareRow - 1);
                i < Math.min(this.board.length, shipSquareRow + 2);
                i++
            ) {
                for (
                    int j = Math.max(0, shipSquareCol - 1);
                    j < Math.min(this.board.length, shipSquareCol + shipSize + 2);
                    j++
                ) {
                    if (!this.board[i][j].getHasShip()) {
                        this.board[i][j].setIsBattleshipNeighbour(true);
                    }
                }
            }
        }
    }

    public void print() {
        String boardStr = "";
        for (Square[] boardRow : this.board) {
            for (Square boardSquare : boardRow) {
                String cellStr;
                if (boardSquare.getHasShip() && boardSquare.getIsShot()) {
                    cellStr = "X";
                } else if (boardSquare.getIsShot()) {
                    cellStr = "O";
                } else {
                    cellStr = "-";
                }

                if (boardSquare.getCol() != this.board.length) {
                    cellStr += " ";
                }

                boardStr += cellStr;
            }
            boardStr += "\n";
        }
        System.out.println(boardStr);
    }

    public void printShips() {
        String boardStr = "";
        for (Square[] boardRow : this.board) {
            for (Square boardSquare : boardRow) {
                String cellStr;
                if (boardSquare.getHasShip()) {
                    cellStr = "@";
                } else {
                    cellStr = "-";
                }

                if (boardSquare.getCol() != this.board.length) {
                    cellStr += " ";
                }

                boardStr += cellStr;
            }
            boardStr += "\n";
        }
        System.out.println(boardStr);
    }
}