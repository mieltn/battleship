import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Board b1 = new Board(10);
        b1.placeBattleship(5);
        b1.placeBattleship(4);
        b1.placeBattleship(3);
        b1.placeBattleship(2);
        b1.placeBattleship(1);

        System.out.println("Enter first player's name: ");
        String p1Name = s.nextLine();
        Player p1 = new Player(p1Name, b1);
        System.out.println("Here are ships locations for " + p1.getName());
        b1.printShips();

        Board b2 = new Board(10);
        b2.placeBattleship(5);
        b2.placeBattleship(4);
        b2.placeBattleship(3);
        b2.placeBattleship(2);
        b2.placeBattleship(1);

        System.out.println("Enter second player's name: ");
        String p2Name = s.nextLine();
        Player p2 = new Player(p2Name, b2);
        System.out.println("Here are ships locations for " + p2.getName());
        b2.printShips();

        s.close();

        boolean attackerSunkLastShip = false;
        while (!attackerSunkLastShip) {
            p2.getBoard().print();
            attackerSunkLastShip = p1.takeTurn(p2);

            p1.getBoard().print();
            attackerSunkLastShip = p2.takeTurn(p1);
        }

        if (p1.getOpponentShipsSunk() == p2.getOpponentShipsSunk()) {
            System.out.println("DRAW");
        } else {
            String winner = p1.getOpponentShipsSunk() > p2.getOpponentShipsSunk() ? p1.getName() : p2.getName();
            System.out.println(winner + "WON");
        }
    }
}
