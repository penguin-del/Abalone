package player;

import MCTS.MonteCarloTree;
import MCTS.MonteCarloTreeTime;
import board.Layer;
import board.Marble.MarbleColor;
import move.representation.Move;

public class HybridPlayer extends AbstractPlayer{

	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {
		boolean isEarlyGame = false;
		
		if (isEarlyGame) {
//			MonteCarloTreeTime mctt= new MonteCarloTreeTime(layer, color);
//			Move chosenMove = mctt.run();
//			
//			
//			Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);
			
			//Don't know if we can create a new player like this each time, but if not then have code above to take turn
			TimeBasedPlayer tbp = new TimeBasedPlayer();
			Layer moveLayer = tbp.takeTurn(layer, color);
			
			System.out.println(moveLayer);
			
			return moveLayer;
			
		}
		MonteCarloPlayer mcp = new MonteCarloPlayer();
		
		Layer moveLayer = mcp.takeTurn(layer, color);
		
		System.out.println(moveLayer);
//		MonteCarloTree mct = new MonteCarloTree(layer, color);
//
//		Move chosenMove = mct.run();
//
//		Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);

		System.out.println(moveLayer);

		return moveLayer;
	}

}
