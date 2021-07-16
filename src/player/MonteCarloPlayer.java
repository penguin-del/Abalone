package player;

import MCTS.MonteCarloTree;
import board.Layer;
import board.Marble.MarbleColor;
import move.representation.Move;

public class MonteCarloPlayer extends AbstractPlayer{

	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {
		MonteCarloTree mct = new MonteCarloTree(layer, color);
		
		Move chosenMove = mct.run();
		
		Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);
		System.out.println(chosenMove);
		System.out.println(moveLayer);
		return moveLayer;
	}

}
