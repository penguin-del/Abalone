package move.representation;

import board.Layer;
import formation.shape.Formation;

public abstract class Move
{
	//public abstract boolean makeMove(BoardGame bg);
		public abstract boolean moveApplies(Formation formation);

	// Given a layer, make this move; the result is a
	// new representation of the Board (as a new Layer)
		public abstract Layer makeMove(Layer layer);
}
