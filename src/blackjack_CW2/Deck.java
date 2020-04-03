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
		//Shuffling the deck
		Collections.shuffle(cards);
	}
	
	public String toString() {
		String cardListOutput = "";
		for (Card xCard : this.cards) {
			cardListOutput += "\n" + xCard.toString();
		}
		return cardListOutput;
		
		}
	public int deckSize() {
		
		return cards.size();
	}
	
	public int valueOfCards() {
		int playerDeck = 0;
		int amtOfAces = 0;
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
			case ACE : amtOfAces += 1; break;
			}
		}
		
		for(int i = 0; i < amtOfAces; i++) {
			if (playerDeck> 10) {
				playerDeck+=1;
			}
			else {
				playerDeck+=11;
			}
		}
		
		return playerDeck;

	}
	public void removeCard(int i) {
		this.cards.remove(i);
	}
	
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}
	
	public void draw(Deck incoming) {
		this.cards.add(incoming.getCard(0));
		incoming.removeCard(0);
	}
	
	public void moveAllToDeck(Deck moveTo) {
		int thisDeckSize = this.cards.size();
		for(int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		
		for(int i = 0; i<thisDeckSize; i++) {
			this.removeCard(0);
		}
		
	}
}
