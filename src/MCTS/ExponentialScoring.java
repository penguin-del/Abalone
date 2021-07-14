package MCTS;

import board.Layer;
import board.Marble.MarbleColor;

public class ExponentialScoring extends Scoring{
	//number of starting marbles for each side, can be changed
	protected final int numOfStartingMarbles = 14;

	//fractional weight of each marble, except for 5th one
	protected final int weightOfMarbles = 7;



	//Each marble's worth is calculated using:
	//e ^ ((numOfMarbles * ln2) / 7)

	//so if you have 11 marbles left with your color, and your opponent has 9 marbles left it calculates:
	//14 - 11 = 3 marbles knocked off
	//14 - 9 = 5 marbles knocked off 
	//Your opponents point value:
	//e ^ ((3 * ln2) / 7 = 1.346
	//Your point value:
	//e ^ ((6 * ln2) / 7 = 1.811
	
	//So final point value would be 1.811-1.346 = .466

	@Override
	public float getScore(Layer layer, MarbleColor playerColor) {


		int playerMarblesKnockedOff = numOfStartingMarbles - layer.numMarblesLeft(playerColor);

		int opponentMarblesKnockedOff = numOfStartingMarbles - layer.numMarblesLeft(playerColor);

		//initialize to 0 for use later on
		float playerPoints = 0;

		//initialize to 0 for use later on
		float opponentPoints = 0;

		//calculates points for player
		if(opponentMarblesKnockedOff!=5) {
			playerPoints = calculatePoints(opponentMarblesKnockedOff);
		}

		//calculates points for opponent
		if(playerMarblesKnockedOff != 5) {
			opponentPoints = calculatePoints(playerMarblesKnockedOff);
		}

		//getting to 5 marbles knocked off doubles the weight of a normal marble so we add 1 to it for 6/7
		if(playerMarblesKnockedOff == 5){
			opponentPoints = calculatePoints(playerMarblesKnockedOff+1);
		}

		//getting to 5 marbles knocked off doubles the weight of a normal marble
		if(opponentMarblesKnockedOff == 5){
			playerPoints = calculatePoints(opponentMarblesKnockedOff+1);
		}
		
		//difference between points (can be negative)
		return playerPoints - opponentPoints;

	}
	protected float calculatePoints(int numOfMarblesKnockedOff) {
		return (float) Math.exp(Math.log(2)*numOfMarblesKnockedOff / weightOfMarbles);
	}
}
