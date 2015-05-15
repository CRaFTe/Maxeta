package main;

/**
 * Players of the game.  Has a player name and the currentCard field which is 
 * the player's current card for a given round.
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
/**
 * This player draws a card from the deck based on their player name (number),
 * This player is drawing from the beginning of the deck of cards (ArrayList) 
 * offset by their player name (number).
 * @param deck deck this player draws a card from.
 * @return card drawn from the deck.
 */
public Card drawCard(Deck deck) {
	// if the players wanted to create unique names like "Bob"
	// there could be a getPlayerNumber() method created, and
	// and extra private data field added to the class 
	// private int pNum.  And this method would use the pNum to decide which
	// card to draw instead of the player name.
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
