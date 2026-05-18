import java.util.*;

public class Game {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Deck deck = new Deck();
        deck.shuffle();

        Player user = new Player("You");
        Player computer = new Player("Computer");

        ArrayList<card> table = new ArrayList<>();

        // deal 2 cards
        for (int i = 0; i < 2; i++) {
            user.addCard(deck.drawCard());
            computer.addCard(deck.drawCard());
        }

        // show player cards
        user.showHand();

        // first 2 table cards
        for (int i = 0; i < 2; i++) {
            table.add(deck.drawCard());
        }

        System.out.println("\nTable:");
        for (card c : table) {
            System.out.println(c.getCardName());
        }

        // grow table to 5 cards
        while (table.size() < 5) {

            System.out.println("\nContinue? (yes/no)");
            String choice = sc.nextLine();

            if (choice.equals("yes")) {
                table.add(deck.drawCard());

                System.out.println("\nTable:");
                for (card c : table) {
                    System.out.println(c.getCardName());
                }
            } else {
                break;
            }
        }

        // combine cards
        ArrayList<card> userCards = new ArrayList<>(user.getHand());
        ArrayList<card> compCards = new ArrayList<>(computer.getHand());

        userCards.addAll(table);
        compCards.addAll(table);

        // evaluate
        Result r1 = Evaluater.evaluate(userCards);
        Result r2 = Evaluater.evaluate(compCards);

        // compare
        if (r1.rank > r2.rank) {
            System.out.println("\nYou Win!");
        } else if (r2.rank > r1.rank) {
            System.out.println("\nComputer Wins!");
        } else {
            for (int i = 0; i < Math.min(r1.values.size(), r2.values.size()); i++) {
                if (r1.values.get(i) > r2.values.get(i)) {
                    System.out.println("\nYou Win!");
                    return;
                }
                if (r2.values.get(i) > r1.values.get(i)) {
                    System.out.println("\nComputer Wins!");
                    return;
                }
            }
            System.out.println("\nDraw!");
        }
    }
}