package main;

/**
 * Card is the card value, and the suit name of an individual playing 
 * card.
 * @author Micah T. Moore
 *
 */
public class Card {
private int value;
private String suit;
Card(int v, String s) {
	value = v;
	suit = s;
}

@Override
public String toString() {
    String face;
    if(value > 1 && value < 11) {
    	face = Integer.toString(value);
    }
    else {
    	switch(value) {
    	case -1: face = "Penalty"; 
    			break;
    	case 11: face = "Jack";
    			break;
    	case 12: face = "Queen";
    			break;
    	case 13: face = "King";
    			break;
    	case 14: face = "Ace";
    			break;
    	default: face = "No Face Value";	
    	}
    }
    if(value == -1) {
    	return face + " Card!!!";
    }
    else return face.concat(" of " + suit + "s");
    
}
/**
 * Checks whether this card's suit is greater than anotherCard's.  (greatest to
 * least)
 * Spade, Heart, Diamond, Club.
 * @param anotherCard Card to check against this card to see which suit is 
 * greater.
 * @return True if this cards suit is greater than anotherCard's suit. False
 * otherwise.
 */
public boolean isSuitGreater(Card anotherCard) {
	if(getSuit().equals("Club") || anotherCard.getSuit().equals("Spade")){
		return false;
	}
	else if(getSuit().equals("Diamond") && 
			!anotherCard.getSuit().equals("Club")){
		return false;
	}
	else if(getSuit().equals("Heart") && 
			(!anotherCard.getSuit().equals("Diamond") && 
					!anotherCard.getSuit().equals("Club"))) {
		return false;
	}
	else return true;
	
}
public String getSuit() {
	return suit;
}
public void setSuit(String suit) {
	this.suit = suit;
}
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
}
}
