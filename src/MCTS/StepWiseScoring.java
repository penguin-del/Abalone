package MCTS;

import board.Layer;
import board.Marble.MarbleColor;

public class StepWiseScoring extends Scoring{
//abstract class 
	
	
	//number of starting marbles for each side, can be changed
	protected final int numOfStartingMarbles = 14;

	//fractional weight of each marble, except for 5th one
	protected final int weightOfMarbles = 7;

	//take number of marbles and divide by 6 and add that value to 1 or subtract from 1 if it
	//is opposite from what we want
	//this way we can figure out a ratio of it
	

	//each marble weighs 1/7 more than the previous except for 5 which weighs twice as much as a normal marble (so 2/7)

	//1 marble is equal to 1/7 of a point
	//2 marble is equal to 2/7 of a point
	//3 marble is equal to 3/7 of a point
	//4 marble is equal to 4/7 of a point
	//5 marble is equal to 6/7 of a point
	//6 marble is equal to 7/7 or a whole point
	
	
	//so if you have 11 marbles left with your color, and your opponent has 9 marbles left it calculates:
	//14 - 11 = 3 marbles knocked off
	//14 - 9 = 5 marbles knocked off
	// PlayerPoints is 3/7
	// OpponentPoints is 6/7 (because getting the 5th marble weighs 1/7 more than everything else)
	// Final score would be 6/7 - 3/7

	//getScore will only be computed when there are 5 or fewer marbles knocked off
	public float getScore(Layer layer, MarbleColor playerColor) {

		int playerMarblesKnockedOff = numOfStartingMarbles - layer.numMarblesLeft(playerColor);

		int opponentMarblesKnockedOff = numOfStartingMarbles - layer.numMarblesLeft(playerColor);

		//initialize to 0 for use later on
		float playerPoints = 0;
		
		//initialize to 0 for use later on
		float opponentPoints = 0;
		
		//if it is anything not equal to 5
		if(opponentMarblesKnockedOff != 5) {
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
		
		//returns difference between playerPoints and opponentPoints 
		
		return playerPoints - opponentPoints;
		
	}


	protected float calculatePoints(int numOfMarblesKnockedOff) {
		return (float) numOfMarblesKnockedOff / 7;
	}

	

}
