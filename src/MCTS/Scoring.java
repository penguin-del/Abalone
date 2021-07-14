package MCTS;

import board.Layer;
import board.Marble.MarbleColor;

public abstract class Scoring {
	public abstract float getScore(Layer layer, MarbleColor color);
	protected abstract float calculatePoints(int numOfMarblesKnockedOff);
}
