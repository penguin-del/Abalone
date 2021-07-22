package player;

import MCTS.MonteCarloTreeTime;
import board.Layer;
import board.Marble.MarbleColor;
import move.representation.Move;

public class TimeBasedPlayer extends AbstractPlayer{

	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {
		MonteCarloTreeTime mctt= new MonteCarloTreeTime(layer, color);
		Move chosenMove = mctt.run();
		
		Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);
		
		System.out.println("Timed Move");
		System.out.println(chosenMove);
		System.out.println(moveLayer);
	
		return moveLayer;
	}

}
