package blackjack_CW2;

public class Card {
	private Suit suit;
	private CardValue cardValue;
	
	public Card(CardValue cardValue, Suit suit) {
		this.suit = suit;
		this.cardValue = cardValue;
	}
	
	public String toString(){
		return this.cardValue.toString() + " OF " + this.suit.toString();
	}
	
	public CardValue getValue() {
		return this.cardValue;
	}
}
