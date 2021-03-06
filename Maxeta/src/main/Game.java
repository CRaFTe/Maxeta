package main;


import java.util.ArrayList;
import java.util.Scanner;
/**
 * Game class contains the main method which sets up the card game to be played.
 * This is a text/terminal/console based card game.
 * The card game is played with a deck of 56 cards, the 52 regular cards and 4
 * special 'penalty cards'.  The user is prompted to enter the number of players
 * (2-4) and the game begins.  Each player picks one card from the shuffled deck
 * each round.  The player with the highest value card wins the round and is 
 * awarded 2 points.  If two or more players draw the same highest value card, 
 * then rank is determined by suit as follows (greatest to least): 
 * Spades, Hearts, Diamonds, Clubs.
 * If any player draws a 'penalty card' he is assigned -1 points for that round.
 * The game continues until a player reaches 21 points or over and the closest
 * second place player is more than 1 point away.
 * @author Micah T. Moore
 *
 */
public class Game {

	public static void main(String[] args) {
		boolean keepPlaying = true;
		while(keepPlaying) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		boolean correctInput = false;
		int numberOfPlayers = 0;
		String s;
		System.out.println("      ++++++Card Game++++++");
		System.out.println("\nInstructions:");
		String instructions = "At the begining of the game you will be prompted"
				+ " to enter the number of players (2-4). Every round "
				+ "consists of each player pressing enter to draw a card. "
				+ "The card the player draws will be displayed on the screen. "
				+"Once every player has drawn a card for a given round,"
				+ " the card values will be checked against eachother. Ace "
				+ "being the highest and 2 being the lowest. The player"
				+ " with the highest valued card will win the round and be "
				+ "awarded two points. If two players or more draw a card with"
				+ " the same highest value, the suits will be compared. Suits "
				+ "are ranked in the following order from greatest to least:"
				+ " Spade, Heart, Diamond, Club. There are also special "
				+ "penalty cards (4 in the deck). Whenever a player draws "
				+ "a penalty card he is penalized 1 point for the round. "
				+ "At the end of each round the winning player will be shown "
				+ "along with what card they won with. The score board will "
				+ "also be displayed to show each player's current total "
				+ "score. The game continues until one of the players reaches "
				+ "21 points or higher and is leading the second place player "
				+ "by at least 2 points.";
		System.out.println(wrapBigString(instructions,80));
		System.out.println("Thanks for playing! Have fun!");
		while(!correctInput) {
			System.out.println("\nPlease enter the number of players(2-4):");
			s = in.nextLine();
			if(isInteger(s)) {
				if(isInRange(Integer.parseInt(s))) {
				correctInput = true;
				numberOfPlayers = Integer.parseInt(s);
				}
			}
		}
		 startGame(numberOfPlayers);
		 keepPlaying = playOn();

		}
	}
	/**
	 * Starts the game after the number of players is determined.
	 * Creates the player list, score board, and deck.
	 * Each round is started by shuffling the deck.  Each player is prompted to
	 * push enter to draw a card, any penalty cards are found and 
	 * the cards are compared to find the winner of the round.  
	 * If only penalty cards are drawn that round, there is no winner of the 
	 * round.  The cards are displayed on the screen as they are drawn. At the 
	 * end of each round the winner of the round and what card he won with are 
	 * displayed.  The score board is also displayed with each players total 
	 * current score.
	 * @param players number of players for the game.
	 */
	public static void startGame(int players) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		// create the players
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		for(int p = 0; p < players; p++) {
			Player myPlayer = new Player(Integer.toString(p));
			allPlayers.add(myPlayer);
		}
		// create the score board
		Scoreboard scoreBoard = new Scoreboard(players);
		// create the deck
		String[]suits = {"Spade","Heart","Diamond","Club"};
		Deck gameDeck = new Deck(2,14,suits,4);
		
		
		// player index leading in points
		@SuppressWarnings("unused")
		int currentLeader = -1;
		// to keep track of the round number
		int round = 0;
		System.out.println("_________________________"
				+ "___________________________");
		
		// continue playing the game until someone wins
		while(scoreBoard.checkWin(players) < 0) {
			System.out.println("                       Round #" + (round+1));
			gameDeck.shuffle();
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
						" drew a " + currentPlayer.getCurrentCard().toString() 
						+ " >>>>>\n");
				
				// compare the player's cards and find the winner
				// and penalty cards
				
				// if the player drew a penalty card
				if(currentPlayer.getCurrentCard().getValue() == -1) {
					playerScores[z] = -1;
				}
				// if the player's card value is greater than the previous max 
				// value card
				else if(currentPlayer.getCurrentCard().getValue() 
						> maxCard.getValue()) {
					maxCard = currentPlayer.getCurrentCard();
					maxPlayerIndex = z;
				}
				// if the player's card value is equal to the 
				// previous max value card
				else if(currentPlayer.getCurrentCard().getValue() 
						== maxCard.getValue()) {
					// check if the suit is greater
					if(currentPlayer.getCurrentCard().isSuitGreater(maxCard)) {
						maxCard = currentPlayer.getCurrentCard();
						maxPlayerIndex = z;
					}
					// this is a special case if the only non-penalty 
					// card drawn is a 2 of clubs.
					// the values will be equal and the suits will be equal
					// because maxCard is initialized to the 2 of clubs.
					else if(currentPlayer.getCurrentCard().getValue() == 2 && 
					currentPlayer.getCurrentCard().getSuit().equals("Club")) {
					maxPlayerIndex = z;
					}
				}
			}
			
			String winningPlayer = "";
			if(maxPlayerIndex != -1) {
			playerScores[maxPlayerIndex] = 2;
			
			winningPlayer = "$$ Player " + (maxPlayerIndex+1) + " wins round #" 
			+ (round+1) + " with the " + maxCard.toString() + " $$";
			}
			// if only penalty cards are drawn, no one wins
			else {
				winningPlayer = "$$ All losers, " 
						+ "only penalty cards were drawn $$";
			}
			String dollarSign = "";
			String dashLine = "";
			for(int k = 0; k < winningPlayer.length();k++) {
				dollarSign += "$";
				dashLine += "_";
			}
			System.out.println(dollarSign);
			System.out.println(winningPlayer);
			System.out.println(dollarSign);
			
			currentLeader = scoreBoard.updateScores(playerScores);
			scoreBoard.printScores();
			System.out.println(dashLine);
			
			int winningPlayerIndex = scoreBoard.checkWin(players);
			if(winningPlayerIndex >= 0) {
			String winningPlayerString = "!! CONGRATS Player " 
			+ (winningPlayerIndex+1) + ". You are the winner !!";
			int winPlayerStringLength = winningPlayerString.length();
			String exclamationString = "";
			for(int i = 0; i < winPlayerStringLength; i++) {
			exclamationString += "!";
			}
			System.out.println(exclamationString);
			System.out.println(winningPlayerString);
			System.out.println(exclamationString);
			System.out.println(dashLine);
			}
			round++;
			
		}
	}
	/**
	 * Check whether a given string is an integer.
	 * @param s String to check whether it's an integer.
	 * @return True if the string is integer. False otherwise.
	 */
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	    	System.out.println("---That's not a number!---");
	        return false; 
	    }
	    return true;
	}
	/**
	 * Checks if a given integer is within the range 2-4 inclusive.
	 * @param n integer to check whether it's in the range.
	 * @return True if n is between 2 and 4 inclusive. False otherwise.
	 */
	private static boolean isInRange(int n) {
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
	/**
	 * Checks whether the user wants to play again by asking them with a prompt.
	 * The user's response isn't case sensitive so they can enter any variation 
	 * of y,yes,n,n and it would be accepted as an input.  If he enters anything
	 * else he is prompted to answer again.
	 * @return True if the user answered Yes, False if the user answered No.
	 */
	private static boolean playOn(){
		boolean keepPlaying = false;
		 @SuppressWarnings("resource")
		Scanner in2 = new Scanner(System.in);
		 boolean validInput = false;
		 while(!validInput) {
		 System.out.println("\nWould you like to play again? (Y/N)");
		 String s2 = in2.nextLine();
		 if(s2.toLowerCase().equals("n") || s2.toLowerCase().equals("no")) {
			 keepPlaying = false;
			 validInput = true;
		 }
		 else if(s2.toLowerCase().equals("y") 
				 || s2.toLowerCase().equals("yes")){
			 keepPlaying = true;
			 validInput = true;
		 }
		 else {
			 System.out.println("Not a valid answer.  Please enter Y or N");
		 }
		 }
		 return keepPlaying;
		
	}
	/**
	 * Takes a long string and returns it broken up in lines no longer than a 
	 * given line length.
	 * @param str Long string to be wrapped.
	 * @param lineLength Width of each line desired.
	 * @return String with new line characters added before each word that
	 * would make the line longer than lineLength characters.
	 */
	private static String wrapBigString(String str, int lineLength) {
		String outputStr = "";
		String eachLine = "";
		String[] splitString = str.split(" ");
		for(int i = 0; i < splitString.length; i++) {
			if(eachLine.isEmpty()) {
				eachLine += splitString[i];
			}
			else if(eachLine.length() + splitString[i].length() < lineLength) {
				eachLine += " " + splitString[i];
				if(i == splitString.length - 1) {
					outputStr += eachLine;
				}
			}
			else {
			outputStr += eachLine + "\n";
			eachLine = "";
			i--;
			}
		}
		return outputStr;
	}

}
