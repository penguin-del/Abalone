package player;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import move.generator.MoveGenerator;
import move.representation.*;
import utilities.LocalRandom;

public class RandomPlayerAllMove extends AbstractPlayer
{
	protected ArrayList<Move> _allMoves;
	protected MoveGenerator _mg;

	@Override
	public Layer takeTurn(Layer currLayer, MarbleColor color)
	{
		_mg = new MoveGenerator(currLayer);
		_allMoves = _mg.computeAllMoves(color);

		// Returns a new layer with the move made
		return _allMoves.get(LocalRandom.nextInt(_allMoves.size())).makeMoveOnCopyBoard(currLayer);
	}
}
