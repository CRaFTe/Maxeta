package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * The Deck class holds the deck of cards in an ArrayList for the game.
 * @author Micah T. Moore
 *
 */
public class Deck {
private ArrayList<Card> myDeck;

/**
 * Creates a deck containing cards between given values and of a variety of 
 * suits.
 * @param minCard minimum card value in the deck.  Accepts values 2 through 14 
 * inclusive.
 * @param maxCard maximum card value in the deck.  Accepts values 2 through 14
 * inclusive.  maxCard should be greater than or equal to minCard otherwise 
 * the deck will be empty.
 * @param suits array of suits for the cards in the deck.  maximum 4 suits.
 * @param penaltyCards number of penalty cards in the deck.
 */
Deck(int minCard, int maxCard, String[]suits, int penaltyCards) {
	int tempValue = 0;
	String tempSuit = null;
	ArrayList<Card> tempDeck = new ArrayList<Card>();
	// create the deck of 52 regular cards
	for(int i = minCard-2; i < maxCard-1; i++) {
		for(int j = 0; j < suits.length; j++) {
			tempValue = i + 2;
			switch(j) {
			case 0: tempSuit = suits[0];
					break;
			case 1: tempSuit = suits[1];
					break;
			case 2: tempSuit = suits[2];
					break;
			case 3: tempSuit = suits[3];
					break;
			default: tempSuit = "NoSuit";
			
			}
			tempDeck.add(new Card(tempValue, tempSuit));
		}
	}
	// add the 4 special penalty cards
	for(int k = 0; k < penaltyCards; k++) {
		tempDeck.add(new Card(-1,"Penalty"));
	}
	myDeck = tempDeck;
}

public ArrayList<Card> getMyDeck() {
	return myDeck;
}

public void setMyDeck(ArrayList<Card> myDeck) {
	this.myDeck = myDeck;
}

/**
 * Uses nanoTime as a seed to randomly shuffle this deck of cards.
 */
public void shuffle() {
	long seed = System.nanoTime();
	Collections.shuffle(myDeck, new Random(seed));

}

}
