public class Driver1 {
    public static void main(String[] args) {
        Card c[] = new Card[5];
        c[0] = new Card("Ace", "Spades");
        c[1] = new Card("King", "Hearts");
        c[2] = new Card("Queen", "Clubs");
        c[3] = new Card("Ace", "Spades");
        c[4] = new Card("Jack", "Diamonds");
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < i; j++) {
                if (c[i].equals(c[j])) {
                    System.out.println("Duplicate found: " + c[i]);
                    return;
                }
            }
        }
        System.out.println("No Duplicate");
    }
}
