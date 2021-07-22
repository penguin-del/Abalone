package player;

import board.Layer;
import board.Marble.MarbleColor;

public class HybridPlayer extends AbstractPlayer{

	protected boolean isEarlyGame = true;
	
	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {
		

		if (isEarlyGame) {
			//			MonteCarloTreeTime mctt= new MonteCarloTreeTime(layer, color);
			//			Move chosenMove = mctt.run();
			//			
			//			
			//			Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);

			TimeBasedPlayer tbp = new TimeBasedPlayer();
			Layer moveLayer = tbp.takeTurn(layer, color);

			return moveLayer;

		}
		else {
			MonteCarloPlayer mcp = new MonteCarloPlayer();

			Layer moveLayer = mcp.takeTurn(layer, color);

			//		MonteCarloTree mct = new MonteCarloTree(layer, color);
			//
			//		Move chosenMove = mct.run();
			//
			//		Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);

			
			return moveLayer;
		}
	}

}
