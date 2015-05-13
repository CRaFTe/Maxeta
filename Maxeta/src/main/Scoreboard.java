package main;


public class Scoreboard {
private int maxPoints;
private int[] playerScores;

Scoreboard(int numPlayers) {
	maxPoints = 0;
	playerScores = new int[numPlayers];
}
public int updateScores(int[] pScores) {
	for(int i = 0; i < playerScores.length; i++) {
		playerScores[i] += pScores[i];
		if(playerScores[i] < 0) {
			playerScores[i] = 0;
		}
	}
	int maxPIndex = 0;
	int maxPScore = playerScores[0];
	for(int j = 1; j < playerScores.length; j++) {
		if(playerScores[j] > maxPScore) {
			maxPScore = playerScores[j];
			maxPIndex = j;
		}
	}
	maxPoints = maxPScore;
	return maxPIndex;
}
public void printScores() {
	System.out.println("@@@@@ Current Scores @@@@@");
	for(int i = 0; i < playerScores.length; i++) {
		System.out.println("Player " + (i+1) +": " + playerScores[i]);
	}
	System.out.print("\n\n");
}
public int getMaxPoints() {
	return this.maxPoints;
}

public void setMaxPoints(int maxPoints) {
	this.maxPoints = maxPoints;
}

public int[] getPlayerScores() {
	return playerScores;
}
public void setPlayerScores(int[] playerScores) {
	this.playerScores = playerScores;
}

}
