package main;

/**
 * Score board holds the players current scores and the max score among all the
 * players.
 * @author Micah T. Moore
 *
 */
public class Scoreboard {
private int maxPoints;
private int[] playerScores;

/**
 * Constructs the score board based on the number of players.  Initializes
 * the max score to 0. 
 * @param numPlayers the number of players in the game
 */
Scoreboard(int numPlayers) {
	maxPoints = 0;
	playerScores = new int[numPlayers];
}
/**
 * Updates this score board's player scores.  Also updates the max score field
 * of this score board.
 * @param pScores integer array of current round scores to add to the running
 * scores on the score board.
 * @return the player number (index) of the player with the highest score.
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
/**
 * Displays the current score of all the players on this score board.
 */
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
/**
 * Checks this score board to see if a player satisfies the win conditions.
 * To win a player must have more than 20 points, and be in the lead by at 
 * least 2 points.
 * @param numPlayers number of players involved in the game.
 * @return The index of the winning player. -1 if no player has won.
 */
public int checkWin(int numPlayers) {
	if(maxPoints >= 21 && closestSecond() >= 2) {
		int maxPIndex = updateScores(new int[numPlayers]);
		return maxPIndex;
	}
	else return -1;
}
/**
 * Checks this score board to find how close second place is to the leader.
 * This is intended to determine how much the leading player is winning by.
 * @return The leader's score minus second place's score.
 */
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
