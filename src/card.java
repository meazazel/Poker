public class card {
    String suit;
    int value;

    public card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getCardName() {
        String v;

        if (value == 14) v = "Ace";
        else if (value == 13) v = "King";
        else if (value == 12) v = "Queen";
        else if (value == 11) v = "Jack";
        else v = String.valueOf(value);

        return v + " of " + suit;
    }
}