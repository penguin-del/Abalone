package player;

import MCTS.BoardStateAssessment;
import board.Layer;
import board.Marble.MarbleColor;

public class HybridPlayerMoveFirst extends AbstractPlayer{
	protected boolean _isEarlyGame;
	
	public HybridPlayerMoveFirst() {
		_isEarlyGame = true;
	}

	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {

		if (_isEarlyGame) {
			//			MonteCarloTreeTime mctt= new MonteCarloTreeTime(layer, color);
			//			Move chosenMove = mctt.run();
			//			
			//			
			//			Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);
			MonteCarloPlayer mcp = new MonteCarloPlayer();

			Layer moveLayer = mcp.takeTurn(layer, color);
			
			BoardStateAssessment bsa = new BoardStateAssessment(moveLayer, color);
			if(bsa.isMidGame(moveLayer, color)) _isEarlyGame = false;
			return moveLayer;

		}
		else {
			
			TimeBasedPlayer tbp = new TimeBasedPlayer();
			Layer moveLayer = tbp.takeTurn(layer, color);


			//		MonteCarloTree mct = new MonteCarloTree(layer, color);
			//
			//		Move chosenMove = mct.run();
			//
			//		Layer moveLayer = chosenMove.makeMoveOnCopyBoard(layer);


			return moveLayer;
		}
	}
	
	@Override
	public void reset() {
		_isEarlyGame = true;
	}
}
