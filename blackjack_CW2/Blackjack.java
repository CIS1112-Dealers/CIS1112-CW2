package blackjack_CW2;

public class Blackjack {

	public static void main(String[] args) {
		System.out.println("Blackjack");
		System.out.println("Deck Output:");
		
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		System.out.println(playingDeck);
		playingDeck.shuffle();
		System.out.println(playingDeck);
	}

}
