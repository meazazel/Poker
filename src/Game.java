import java.util.*;

public class Game {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            // =========================
            // SETUP NEW GAME
            // =========================
            Deck deck = new Deck();
            deck.shuffle();

            Player user = new Player("You");
            Player computer = new Player("Computer");

            ArrayList<card> table = new ArrayList<>();

            // =========================
            // DEAL CARDS
            // =========================
            for (int i = 0; i < 2; i++) {
                user.addCard(deck.drawCard());
                computer.addCard(deck.drawCard());
            }

            // show player cards
            System.out.println("\nYour cards:");
            user.showHand();

            // first 2 community cards
            for (int i = 0; i < 2; i++) {
                table.add(deck.drawCard());
            }

            System.out.println("\nTable:");
            for (card c : table) {
                System.out.println(c.getCardName());
            }

            // =========================
            // ADD COMMUNITY CARDS
            // =========================
            while (table.size() < 5) {

                System.out.println("\nDo you want to continue? (yes/no)");
                String choice = sc.nextLine();

                if (choice.equals("yes")) {
                    table.add(deck.drawCard());

                    System.out.println("\nTable updated:");
                    for (card c : table) {
                        System.out.println(c.getCardName());
                    }
                } else {
                    break;
                }
            }

            // =========================
            // COMBINE CARDS
            // =========================
            ArrayList<card> userCards = new ArrayList<>(user.getHand());
            ArrayList<card> compCards = new ArrayList<>(computer.getHand());

            userCards.addAll(table);
            compCards.addAll(table);

            // =========================
            // EVALUATE
            // =========================
            Result r1 = Evaluater.evaluate(userCards);
            Result r2 = Evaluater.evaluate(compCards);

            // =========================
            // COMPARE
            // =========================
            System.out.println();

            if (r1.rank > r2.rank) {
                System.out.println("You Win!");
            }
            else if (r2.rank > r1.rank) {
                System.out.println("Computer Wins!");
            }
            else {
                boolean decided = false;

                for (int i = 0; i < Math.min(r1.values.size(), r2.values.size()); i++) {

                    if (r1.values.get(i) > r2.values.get(i)) {
                        System.out.println("You Win!");
                        decided = true;
                        break;
                    }

                    if (r2.values.get(i) > r1.values.get(i)) {
                        System.out.println("Computer Wins!");
                        decided = true;
                        break;
                    }
                }

                if (!decided) {
                    System.out.println("Draw!");
                }
            }

            // =========================
            // REPLAY MENU
            // =========================
            System.out.println("\nPlay again? (yes / no / quit)");
            String choice = sc.nextLine();

            if (choice.equals("yes")) {
                continue;
            }
            else {
                System.out.println("Thanks for playing!");
                break;
            }
        }

        sc.close();
    }
}