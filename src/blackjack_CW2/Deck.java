package blackjack_CW2;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card> cards;
	
	
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	
	public void createFullDeck() {
		//generating deck of cards w/o jokers
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
		//outputs the card to a string value
		String cardListOutput = "";
		for (Card xCard : this.cards) {
			cardListOutput += "\n" + xCard.toString();
		}
		return cardListOutput;
		
		}
	
	public int deckSize() {
		//returns value of the deck size
		return cards.size();
	}
	
	public void removeCard(int i) {
		//removes a card from the deck
		//prevents a card being used in both hands
		this.cards.remove(i);
	}
	
	public void draw(Deck incoming) {
		//Draws a card from the deck.
		//This removes a card from the deck so that it cannot be used again within this round
		this.cards.add(incoming.getCard(0));
		incoming.removeCard(0);
	}
		
	public Card getCard(int i) {
		//returns a chosen card
		return this.cards.get(i);
	}
	
	public void addCard(Card addCard) {
		//adds a card eg. adding a card to a hand
		this.cards.add(addCard);
	}
	

	public void moveAllToDeck(Deck moveTo) {
		//moves an entire deck. 
		//Useful for restarting a round where the hands need to be moved to the deck
		int thisDeckSize = this.cards.size();
		for(int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		
		for(int i = 0; i<thisDeckSize; i++) {
			this.removeCard(0);
		}
		
	}
	/*
	 * Each card has a specific value, eg. 4 of Clubs == 4.
	 * Aces can either have a value of 1 or 11 depending on the rest of the hand.
	 * Ace value will always be 11 unless it would bust the hand of the player/dealer.
	 * This has been taken into account by having a counter of aces separate from the deck value.
	 */
	public int valueOfCards() {
		int deckValue = 0;
		int amtOfAces = 0;
		for (Card xCard: this.cards) {
			switch (xCard.getValue()){
			case TWO: deckValue += 2; break;
			case THREE: deckValue += 3; break;
			case FOUR: deckValue += 4; break;
			case FIVE: deckValue += 5; break;
			case SIX: deckValue += 6; break;
			case SEVEN: deckValue += 7; break;
			case EIGHT: deckValue += 8; break;
			case NINE: deckValue += 9; break;
			case TEN: deckValue += 10; break;
			case JACK: deckValue += 10; break;
			case QUEEN: deckValue += 10; break;
			case KING: deckValue += 10; break;
			case ACE : amtOfAces += 1; break;
			}
		}
		//Below calculation works out if the high ace would bust the hand
		for(int i = 0; i < amtOfAces; i++) {
			if (deckValue> 10) {
				deckValue+=1;
			}
			else {
				deckValue+=11;
			}
		}
		//outputs the value of the hand
		return deckValue;

	}
	
}
