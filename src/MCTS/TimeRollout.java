package MCTS;

import board.Game;
import board.Layer;
import board.Marble.MarbleColor;
import globals.Constants;
import utilities.Pair;

public class TimeRollout {

	//all caps is things that can be changed later on
	
	//winner returns 1 CAN BE CHANGED TO WHATEVER WE DECIDE TO SCORE
	protected final int winnerPoints = 1;
	
	//loser returns 0 CAN BE CHANGED TO WHATEVER WE DECIDE TO SCORE
	protected final int loserPoints = -1;
	

	
	
	//takes in copy layer and "our" color, returns int COULD BE FLOAT DEPENDING ON HOW WE WANT TO SCORE
	public float rollout(Layer layer, MarbleColor playerColor, MarbleColor moverColor) {
		
		//plays game on copy layer with FULLY RANDOM PLAYERS and with a MAX NUMBER OF MOVES 
		Game game = new Game(layer, Constants.PLAYER_1_TYPE, Constants.PLAYER_2_TYPE ,Constants.MAX_NUM_MOVES);
		
		//sets winning color to the color of the winner, it will be empty if no one wins
		//CHANGE OUTPUT OF GAME.PLAY IF WE WANT TO IMPLEMENT SCORE MECHANISM
		Pair<MarbleColor, Layer> winner = game.play();
		
		//Gets winning color from pair
		MarbleColor winningColor = winner.getFirst();
		
		
		//if "we"(color that we input) haven't won, return 0
		if(moverColor==winningColor) return winnerPoints;
		
		
		//if it was a tie, then calculate and return score
		if(winningColor==MarbleColor.EMPTY) {
			//gets finalLayer from pair
			Layer finalLayer = winner.getSecond();
			HybridScoring hst = new HybridScoring();
			return hst.getScore(finalLayer, moverColor);
		}
		
		
		//if it passes through the previous 2 conditions, player must have lost
		return loserPoints;
	
	}
}
