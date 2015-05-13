package main;

public class Player {
private String name;
private Card currentCard;

Player(String n) {
	setName(n);
}

public Card drawCard(Deck deck) {
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
