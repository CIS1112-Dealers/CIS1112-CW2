package blackjack_CW2;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		System.out.println("Blackjack"); 
		System.out.println("hi");
		System.out.println("Deck Output:");
		
		
		/*
		 * Deck - 'Deck' is the deck of playing cards used in the game
		 * This is not the player's deck,as the player does not start with a full deck
		 * The 'playerDeck' is not a full deck, as it is the player's hand.
		 */
		Deck Deck = new Deck();
		Deck.createFullDeck();
		/*System.out.println("Deck:" + Deck);*/
		Deck.shuffle();
		/*System.out.println(Deck);*/
		
		Deck playerDeck = new Deck();		 
		
		Deck dealerDeck = new Deck();
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		/*
		 * Game loop below:
		 * While loop used until player runs out of money
		 */
		while(playerMoney>0) {
			System.out.println("You have £" + playerMoney + ". How much are you betting?");
			double playerBet = userInput.nextDouble();
			if (playerBet>playerMoney){
				System.out.println("Cannot bet more than you have!");
				break;
			}
			
			boolean endRound = false;
			
			playerDeck.draw(Deck);
			playerDeck.draw(Deck);
			
			dealerDeck.draw(Deck);
			dealerDeck.draw(Deck);
			
			while(true) {
				System.out.println("Your Hand:");
				System.out.println(playerDeck.toString());
				System.out.println("Your deck is valued at " + playerDeck.valueOfCards());
			
				//Dealers hand - only 1 card;
				System.out.println("Dealer's hand: " + dealerDeck.getCard(0).toString() + "and [HIDDEN]");
				
				//Player's Choice
				System.out.println("(1)Hit or (2)Stand?");
				int response = userInput.nextInt();
				
				//if response == hit:
				if(response == 1) {
					playerDeck.draw(Deck);
					System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//bust if over 21;
					if(playerDeck.valueOfCards()>21) {
						System.out.println("BUST. Currently valued at: " + playerDeck.valueOfCards());
						playerMoney -= playerBet;
						endRound=true;
						break;
					}
				}
				if (response ==2) {
					break;
				}
			}
			
			//Reveal dealers cards;
			System.out.println("Dealers Cards:" + dealerDeck.toString());
			if ((dealerDeck.valueOfCards() > playerDeck.valueOfCards()) && endRound == false){
				System.out.println("Dealer wins!");
				playerMoney -= playerBet;
				endRound=true;
			}
			//Dealer draws to 16, stands at 17;
			while((dealerDeck.valueOfCards() < 17) && endRound==false) {
				dealerDeck.draw(Deck);
				System.out.println("Dealer draws:" + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			
			System.out.println("Dealer's hand was valued at: " + dealerDeck.valueOfCards());
			if((dealerDeck.valueOfCards() > 21) && endRound == false) {
				System.out.println("Dealer busts. You win!");
				playerMoney+=playerBet;
				endRound=true;
			}
			
			//determine is push(tie):
			if((playerDeck.valueOfCards()==dealerDeck.valueOfCards()) && endRound==false) {
				System.out.println("Push.....(Tie)");
				endRound=true;
			}
			
			if((playerDeck.valueOfCards() > dealerDeck.valueOfCards()) && endRound == false) {
				System.out.println("You win the round");
				playerMoney+=playerBet;
				endRound=true;
			}
			else if(endRound == false) {
				System.out.println("You lose the hand");
				playerMoney -= playerBet;
				endRound=true;
			}
			//All cards moved back to deck
			playerDeck.moveAllToDeck(Deck);
			dealerDeck.moveAllToDeck(Deck);
			System.out.println("End of hand");
		}
		
		System.out.println("Game over!");
	}

}
