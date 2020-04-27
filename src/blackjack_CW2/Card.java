package blackjack_CW2;

public class Card {
	private Suit suit;
	private CardValue cardValue;
	//Each card has a value and a suit
	public Card(CardValue cardValue, Suit suit) {
		this.suit = suit;
		this.cardValue = cardValue;
	}
	
	public String toString(){
		return this.cardValue.toString() + " OF " + this.suit.toString();
	}
	//Each card also has a value defined within the deck class.
	public CardValue getValue() {
		return this.cardValue;
	}
}
