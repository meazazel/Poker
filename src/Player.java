import java.util.*;

public class Player {
    String name;
    ArrayList<card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCard(card c) {
        hand.add(c);
    }

    public ArrayList<card> getHand() {
        return hand;
    }

    public void showHand() {
        System.out.println(name + " cards:");
        for (card c : hand) {
            System.out.println(c.getCardName());
        }
    }
}