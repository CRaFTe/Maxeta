package main;

/**
 * 
 * @author Micah T. Moore
 *
 */
public class Scoreboard {
private int maxPoints;
private int[] playerScores;

/**
 * 
 * @param numPlayers
 */
Scoreboard(int numPlayers) {
	maxPoints = 0;
	playerScores = new int[numPlayers];
}
/**
 * 
 * @param pScores
 * @return
 */
public int updateScores(int[] pScores) {
	for(int i = 0; i < playerScores.length; i++) {
		playerScores[i] += pScores[i];
		// if a penalty card was drawn that would bring the player below 0
		// points, keep that player at 0 points
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
	System.out.println("             @@@@@  SCORES @@@@@");
	String rightSideScoreBoard = null;
	for(int i = 0; i < playerScores.length; i++) {
		if(playerScores[i]-9 > 0) rightSideScoreBoard = "  @@";
		else rightSideScoreBoard = "   @@";
		System.out.println("             @@ Player " + (i+1) +": " 
		+ playerScores[i] + rightSideScoreBoard);
	}
	System.out.print("             @@@@@@@@@@@@@@@@@@@\n\n");
}
public int checkWin(int numPlayers) {
	if(maxPoints >= 21 && closestSecond() >= 2) {
		int maxPIndex = updateScores(new int[numPlayers]);
		return maxPIndex;
	}
	else return -1;
}
public int closestSecond() {
	int maxIndex = updateScores(new int[playerScores.length]);
	int closeSecond = 21;
	for(int i = 0; i < playerScores.length; i++) {
		if(i != maxIndex) {
			if(playerScores[maxIndex] - playerScores[i] < closeSecond) {
				closeSecond = playerScores[maxIndex] - playerScores[i];
			}
		}
	}
	return closeSecond;
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
