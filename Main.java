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

        Player attacker = p1;
        Player defender = p2;
        
        while (p1.getOpponentShipsSunk() < 5 && p2.getOpponentShipsSunk() < 5) {

            System.out.println(attacker.getName() + "'s turn: ");
            defender.getBoard().print();
            int row = s.nextInt();
            int col = s.nextInt();

            boolean isHit = attacker.takeShotAt(defender, row, col);
            if (isHit) {
                System.out.println("On target! Keep going");
            } else {
                System.out.println("Missed! ");
                Player tmp = attacker;
                attacker = defender;
                defender = tmp;
            }
        }

        s.close();
    }
}
