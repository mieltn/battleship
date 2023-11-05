import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        // init boards and add 6 ships of different sizes
        // then init players with corresponding boards
        // each placeBattleship takes in Battleship class reference
        // since Battleship is a parent class for all ship classes ships of different sizes can be passed
        Board b1 = new Board(10);
        b1.placeBattleship(new SmallBattleship());
        b1.placeBattleship(new SmallBattleship());
        b1.placeBattleship(new SmallBattleship());
        b1.placeBattleship(new MediumBattleship());
        b1.placeBattleship(new MediumBattleship());
        b1.placeBattleship(new LargeBattleship());

        System.out.println("Enter first player's name: ");
        String p1Name = s.nextLine();
        Player p1 = new Player(p1Name, b1);
        System.out.println("Here are ships locations for " + p1.getName());
        b1.printShips();

        Board b2 = new Board(10);
        b2.placeBattleship(new SmallBattleship());
        b2.placeBattleship(new SmallBattleship());
        b2.placeBattleship(new SmallBattleship());
        b2.placeBattleship(new MediumBattleship());
        b2.placeBattleship(new MediumBattleship());
        b2.placeBattleship(new LargeBattleship());

        System.out.println("Enter second player's name: ");
        String p2Name = s.nextLine();
        Player p2 = new Player(p2Name, b2);
        System.out.println("Here are ships locations for " + p2.getName());
        b2.printShips();

        System.out.println("--- PLAY ---\n");

        // take turns until the last ship was sunk by any player
        boolean p1SunkLastShip = false;
        boolean p2SunkLastShip = false;
        while (!p1SunkLastShip && !p2SunkLastShip) {
            p1SunkLastShip = p1.takeTurn(s, p2);
            p2SunkLastShip = p2.takeTurn(s, p2);
        }

        // print the winner
        if (p1.getOpponentShipsSunk() == p2.getOpponentShipsSunk()) {
            System.out.println(" DRAW");
        } else {
            String winner = p1.getOpponentShipsSunk() > p2.getOpponentShipsSunk() ? p1.getName() : p2.getName();
            System.out.println(winner + " WON");
        }
        s.close();
    }
}
