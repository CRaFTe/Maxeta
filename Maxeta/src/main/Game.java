package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		boolean correctInput = false;
		int numberOfPlayers = 0;
		String s;
		while(!correctInput) {
			System.out.println("Please Enter the Number of Players(2-4): ");
			s = in.nextLine();
			if(isInteger(s)) {
				if(isInRange(Integer.parseInt(s))) {
				correctInput = true;
				numberOfPlayers = Integer.parseInt(s);
				}
			}
		}

		Card card1 = new Card(2,"Spades");
		Card card2 = new Card(2,"Clubs");
		System.out.println(card2.isSuitGreater(card1));
		// startGame(numberOfPlayers);
		
//		for(int i = 0; i < gameDeck.getMyDeck().size(); i++) {
//			System.out.println("Value: " + gameDeck.getMyDeck().get(i).getValue() + 
//					" Suit: " + gameDeck.getMyDeck().get(i).getSuit());
//		}
		
		

	}
	public static void startGame(int players) {
		Scanner in = new Scanner(System.in);
		// create the players
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		for(int p = 0; p < players; p++) {
			Player myPlayer = new Player(Integer.toString(p));
			allPlayers.add(myPlayer);
		}
		// create the scoreboard
		Scoreboard scoreBoard = new Scoreboard(players);
		// create the deck
		Deck gameDeck = new Deck();
		
		// player index leading in points
		int currentLeader = -1;
		
		// start the game
		while(scoreBoard.getMaxPoints() < 21) {
			//shuffle the deck
			gameDeck.shuffle();
			// initialize the player score array
			int[] playerScores = new int[players];
			// initialize the maxCard to the the lowest card in the deck
			Card maxCard = new Card(2, "Club");
			int maxPlayerIndex = -1;
			for(int z = 0; z < players; z++) {
				Player currentPlayer = allPlayers.get(z);
				System.out.println("Player " + currentPlayer.getName() + 
						" it's your turn, press enter to draw a card");
				in.nextLine();
				currentPlayer.setCurrentCard(currentPlayer.drawCard(gameDeck));
				// output the card
				System.out.println("<<<<< Player " + currentPlayer.getName() + 
						" drew a " + currentPlayer.getCurrentCard().toString() + " >>>>>\n");
				// compare the player's cards and find the winner and penalty cards
				if(currentPlayer.getCurrentCard().getValue() == -1) {
					playerScores[z] = -1;
				}
				else if(currentPlayer.getCurrentCard().getValue() > maxCard.getValue()) {
					maxCard = currentPlayer.getCurrentCard();
					maxPlayerIndex = z;
				}
				else if(currentPlayer.getCurrentCard().getValue() == maxCard.getValue()) {
					if(currentPlayer.getCurrentCard().isSuitGreater(maxCard)) {
						maxCard = currentPlayer.getCurrentCard();
						maxPlayerIndex = z;
					}
				}
			}
			playerScores[maxPlayerIndex] = 2;
			System.out.println("$$$$$ Player " + (maxPlayerIndex+1) + " wins the round with the " + 
			maxCard.toString() + " $$$$$");
			
			currentLeader = scoreBoard.updateScores(playerScores);
			scoreBoard.printScores();
			
		}
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	    	System.out.println("---That's not a number!---");
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public static boolean isInRange(int n) {
		if(n >= 2 && n <= 4) return true;
		else if( n < 2){
			System.out.println("---Number of players is below 2.---");
			return false;
		}
		else {
			System.out.println("---Number of players is above 4.---");
			return false;
		}
		
	}

}
