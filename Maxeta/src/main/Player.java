package main;

/**
 * 
 * @author Micah T. Moore
 *
 */
public class Player {
private String name;
private Card currentCard;
/**
 * Creates a player with a given name.
 * @param n Name of the player being created.
 */
Player(String n) {
	setName(n);
}

public Card drawCard(Deck deck) {
	// draws a card from the deck based on player name (0-4)
	// if the players wanted to create unique names like "Bob"
	// there could be a getPlayerNumber() method created, and
	// and extra private data field added to the class 
	// private int pNum
	Card picked = deck.getMyDeck().get(Integer.parseInt(getName()));
	return picked;
}

public String getName() {
	int pName = Integer.parseInt(name);
	pName++;
	return Integer.toString(pName);
}

public void setName(String name) {
	this.name = name;
}

public Card getCurrentCard() {
	return currentCard;
}

public void setCurrentCard(Card currentCard) {
	this.currentCard = currentCard;
}
}
