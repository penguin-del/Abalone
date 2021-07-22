package MCTS;

import board.Layer;
import board.Marble.MarbleColor;

public class HybridScoring extends Scoring{

	
	
	
	@Override
	public float getScore(Layer layer, MarbleColor playerColor) {
		BoardStateAssessment bsa = new BoardStateAssessment(layer, playerColor);
		float boardScore = bsa.determineScore();
		ExponentialScoring es = new ExponentialScoring();
		float totalScore = es.getScore(layer, playerColor) + boardScore;
		return  (float) (totalScore / 2.0);
		
	}

	@Override
	protected float calculatePoints(int numOfMarblesKnockedOff) {
		return 0;
	}

}
