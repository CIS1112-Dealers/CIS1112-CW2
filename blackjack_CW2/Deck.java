package blackjack_CW2;

import java.util.ArrayList;

public class Deck {
	
	//instance
	private ArrayList<Card> cards;
	
	//construct
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	
	public void createFullDeck() {
		//generating deck of cards
		for(Suit cardSuit : Suit.values()) {
			for(CardValue cardValue : CardValue.values()) {
				this.cards.add(new Card(cardValue, cardSuit));
			}
		}
	}
	
	public String toString() {
		String cardListOutput = "";
		int i = 0;
		for (Card xCard : this.cards) {
			cardListOutput += "\n" + i + " - " + xCard.toString();
			i++;
		}
		return cardListOutput;
	}
}
