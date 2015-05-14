package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * The Deck class holds the deck of cards for the game.
 * @author Micah T. Moore
 *
 */
public class Deck {
private ArrayList<Card> myDeck;


Deck() {
	int tempValue = 0;
	String tempSuit = null;
	ArrayList<Card> tempDeck = new ArrayList<Card>();
	// create the deck of 52 regular cards
	for(int i = 0; i < 13; i++) {
		for(int j = 0; j < 4; j++) {
			tempValue = i + 2;
			switch(j) {
			case 0: tempSuit = "Spade";
					break;
			case 1: tempSuit = "Heart";
					break;
			case 2: tempSuit = "Diamond";
					break;
			case 3: tempSuit = "Club";
					break;
			default: tempSuit = "NoSuit";
			
			}
			tempDeck.add(new Card(tempValue, tempSuit));
		}
	}
	// add the 4 special penalty cards
	for(int k = 0; k < 4; k++) {
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
// uses nanoTime as a seed to randomly shuffle the deck
public void shuffle() {
	long seed = System.nanoTime();
	Collections.shuffle(myDeck, new Random(seed));

}

}
