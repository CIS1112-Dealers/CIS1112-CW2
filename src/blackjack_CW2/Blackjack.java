package blackjack_CW2;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		System.out.println("Blackjack"); 			
		
		/*
		 * IMPORTANT
		 * 
		 * To access the admin menu, change isAdmin on the line below
		 * from 'false' to 'true'
		 */
		boolean isAdmin = true;	
		boolean gameStarted = false;
		/*
		 * booleans isAdmin, gameStarted used for verification access to the admin menu
		 */
		
		int playerWins = 0;
		int dealerWins = 0;
		/*
		 * Wins counters to track the game.
		 */
		
		Deck Deck = new Deck();
		/*
		 * Deck - 'Deck' is the deck of playing cards used in the game
		 * This is not the player's deck,as the player does not start with a full deck
		 * The 'playerDeck' is not a full deck, as it is the player's hand.
		 */
		
		Deck.createFullDeck();
		/*System.out.println("Deck:" + Deck);*/
		Deck.shuffle();
		/*System.out.println(Deck);*/
		
		Deck playerDeck = new Deck();
		/*
		 * Creates a deck object for the player named 'playerDeck'.
		 * This will act as the player's hand and draw cards from the playing deck
		 * aptly named 'deck'.
		 */
		
		Deck dealerDeck = new Deck();
		/*
		 * Creates another deck object instead called 'dealerDeck',
		 * this acts as the dealer's hand.
		 */
		
		double playerMoney = 100.00;
		System.out.println("You start with £100.");
		/*
		 * The money value can be adjusted before a game to give the player
		 * an advantage, or for testing purposes.
		 */
		
		Scanner userInput = new Scanner(System.in);
		
		while(isAdmin==true && gameStarted==false){
			System.out.println("Welcome to the Admin menu!"
					+ "\n"
					+ "\nHere, you can:"
					+ "\n>> 1. Add money"
					+ "\n>> 2. Remove money"
					+ "\n>> 3. Change betting multiplier"
					+ "\n>> 4. Change player wins"
					+ "\n>> 5. Change dealer wins"
					+ "\n>> 6. Exit Menu"
					+ "\n"
					+ "\nTo play, exit this menu.");
			int adminChoice = userInput.nextInt();
			if (adminChoice == 1){
				System.out.println("How much money would you like to add?");
				double moneyChange = userInput.nextDouble();
				playerMoney += moneyChange;
				System.out.println("Player money now at: £" + playerMoney);
			}
			else if (adminChoice == 2) {
				System.out.println("How much money would you like to remove?");
				double moneyChange = userInput.nextDouble();
				playerMoney -= moneyChange;
				System.out.println("Player money now at: £" + playerMoney);
			}
			else if (adminChoice == 3) {
				System.out.println("The current multiplier is *2"
						+ "\nWhat would you like to change it to?");
				double bettingMult = userInput.nextDouble();
				System.out.println("Betting Multiplier now set to " + bettingMult);			
			}
			else if (adminChoice == 4) {
				System.out.println("Change current player wins to...?");
				int winChange = userInput.nextInt();
				playerWins=winChange;
			}
			else if (adminChoice == 5) {
				System.out.println("Change current dealer wins to...?");
				int winChange = userInput.nextInt();
				dealerWins=winChange;
			}
			else if (adminChoice == 6) {
				System.out.println("Exiting menu.");
				isAdmin=false;
			}
			else {
				System.out.println("Not a valid choice!");
			}
		}
		
		gameStarted=true;
		//User cannot play the rest of the game without exiting the admin menu
		while((playerMoney>0)&&(gameStarted==true)&&(isAdmin==false)) {
			System.out.println("You have £" + playerMoney + ". How much are you betting?");
			double playerBet = userInput.nextDouble();
			/*
			 * Current multiplier is that the player gets double their bet 
			 */
			if (playerBet>playerMoney){
				System.out.println("Cannot bet more than you have!");
				break;
				//Ends the game if the user tries to bet more money than they have.
			}
			
			boolean endRound = false;
			//Draws 2 cards for the player and dealer
			playerDeck.draw(Deck);
			playerDeck.draw(Deck);
			
			dealerDeck.draw(Deck);
			dealerDeck.draw(Deck);
			
			//Current win counter:
			System.out.println("Player wins: " + playerWins);
			System.out.println("Dealer wins: " + dealerWins);
			
			while(true) {
				System.out.println("Your Hand:");
				System.out.println(playerDeck.toString());
				System.out.println("Your deck is valued at " + playerDeck.valueOfCards());
			
				//Dealers hand - only 1 card;
				System.out.println("Dealer's hand: " + dealerDeck.getCard(0).toString() + "and [HIDDEN]");
				
				//Player's Choice
				System.out.println("(1)Hit or (2)Stand?");
				int response = userInput.nextInt();
				
				//if value of cards in hand == 21:
				if(playerDeck.valueOfCards()==21) {
					break;
					//in a game, if the hand is 21, then the player cannot hit, as this would bust the player.
				}
				//if response == hit:
				if(response == 1) {
					playerDeck.draw(Deck);
					System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//bust if over 21;
					if(playerDeck.valueOfCards()>21) {
						System.out.println("BUST. Currently valued at: " + playerDeck.valueOfCards());
						playerMoney -= playerBet;
						dealerWins+=1;
						endRound=true;
						break;
					}
				}
				//if response == stand:
				if (response ==2) {
					break;
				}
			}
			
			//Reveal dealers cards:
			System.out.println("Dealers Cards:" + dealerDeck.toString());
			if ((dealerDeck.valueOfCards() > playerDeck.valueOfCards()) && endRound == false){
				System.out.println("Dealer wins!");
				dealerWins+=1;
				playerMoney -= playerBet;
				endRound=true;
			}
			//Dealer draws to 16, stands at 17 (standard blackjack rules):
			while((dealerDeck.valueOfCards() < 17) && endRound==false) {
				dealerDeck.draw(Deck);
				System.out.println("Dealer draws:" + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			
			System.out.println("Dealer's hand was valued at: " + dealerDeck.valueOfCards());
			if((dealerDeck.valueOfCards() > 21) && endRound == false) {
				System.out.println("Dealer busts. You win!");
				playerWins+=1;
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
				playerWins+=1;
				playerMoney+=playerBet;
				endRound=true;
			}
			else if(endRound == false) {
				System.out.println("You lose the hand");
				dealerWins+=1;
				playerMoney -= playerBet;
				endRound=true;
			}
			//All cards moved back to deck for restart
			playerDeck.moveAllToDeck(Deck);
			dealerDeck.moveAllToDeck(Deck);
			System.out.println("End of hand");
		}
		
		System.out.println("Game over!");
	}

}
