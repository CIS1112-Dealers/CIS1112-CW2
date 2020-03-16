package blackjack_CW2;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public void shuffle() {
		Collections.shuffle(cards);
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
	public int deckSize() {
		
		return cards.size();
	}
	
	public int valueOfCard() {
		for (Card xCard: this.cards) {
			switch (xCard.getValue()){
			case TWO: playerDeck += 2; break;
			case THREE: playerDeck += 3; break;
			case FOUR: playerDeck += 4; break;
			case FIVE: playerDeck += 5; break;
			case SIX: playerDeck += 6; break;
			case SEVEN: playerDeck += 7; break;
			case EIGHT: playerDeck += 8; break;
			case NINE: playerDeck += 9; break;
			case TEN: playerDeck += 10; break;
			case JACK: playerDeck += 10; break;
			case QUEEN: playerDeck += 10; break;
			case KING: playerDeck += 10; break;
			}
		}
		if (playerDeck > 10) {
			for (Card xCard: this.cards) {
				switch (xCard.getValue()){
				case ACE: playerDeck += 1; break;
				}
			}
		} else {
			for (Card xCard: this.cards) {
				switch (xCard.getValue()){
				case ACE: playerDeck += 11; break;
				}
			}
		}
		
	}
		
}
