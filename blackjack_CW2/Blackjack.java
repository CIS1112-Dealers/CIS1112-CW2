package blackjack_CW2;

public class Blackjack {

	public static void main(String[] args) {
		System.out.println("Blackjack");
		System.out.println("Deck Output:");
		
		Deck playerDeck = new Deck();
		playerDeck.createFullDeck();
		System.out.println("PlayerDeck:" + playerDeck);
		
		/*
		 * //testing shuffle
		 *
		 *playerDeck.shuffle();
		 *System.out.println(playerDeck);
		 */
		
		Deck dealerDeck = new Deck();
		dealerDeck.createFullDeck();
		System.out.println("DealerDeck:" + dealerDeck);
		

	}

}
