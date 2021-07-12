package board;

import board.Marble.MarbleColor;
import globals.Constants;
import player.AbstractPlayer;

public class Game
{
	protected Layer _rootLayer;

	protected AbstractPlayer _player1;
	protected AbstractPlayer _player2;

	protected final int _MAX_TURNS;

	public int _whiteMoves;
	public int _blackMoves;

	public Game(Layer layer, AbstractPlayer player1, AbstractPlayer player2, int maxTurns)
    {
		_rootLayer = layer;
		_player1 = player1;
		_player2 = player2;
		_MAX_TURNS = maxTurns;
		
		_whiteMoves = 0;
		_blackMoves = 0;
	}

	public MarbleColor play()
	{
		int turns = 0;
		Layer currentLayer = _rootLayer; 
		// limit # turns to guarantee termination
		for (;turns < _MAX_TURNS; turns++)
		{
			// Player 1 makes a move and then check player 2 lose condition
			_blackMoves += 1;
			currentLayer = _player1.takeTurn(currentLayer, Constants.PLAYER_1__STARTING_COLOR);
//			System.out.print(currentLayer);
			//if () counter.pushedOff(MarbleColor.WHITE);
			if (currentLayer.numMarblesLeft(MarbleColor.WHITE) == 8) {
				//System.out.print(numturns+ "\n");
				return MarbleColor.BLACK;
			}
			//				System.out.print(_bg +"\n");
			//				System.out.print("White Left: "+ counter.getWhiteMarbles()+ " ");
			//				System.out.print("Black Left: "+ counter.getNumBlackMarbles() + "\n");

			//Player 2 makes a move and then check player 1 lose condition
			_whiteMoves +=1;
			currentLayer = _player2.takeTurn(currentLayer, Constants.PLAYER_2__STARTING_COLOR);
//			System.out.print(currentLayer);
			//if () counter.pushedOff(MarbleColor.BLACK);
			if (currentLayer.numMarblesLeft(MarbleColor.BLACK) == 8) {
				//System.out.print(numturns+ "\n"); 
				return MarbleColor.WHITE; 
			}
			
			//				System.out.print(_bg + "\n");
			//				System.out.print("White Left: "+ counter.getWhiteMarbles()+ " ");
			//				System.out.print("Black Left: "+ counter.getNumBlackMarbles()+ "\n");
		}
//		System.out.println(turns);
//		System.out.println(currentLayer.numMarblesLeft(MarbleColor.WHITE));
//		System.out.println(currentLayer.numMarblesLeft(MarbleColor.BLACK));
//		System.out.println(currentLayer);
		//Only returns empty if the game exceeds the turn limit
		return MarbleColor.EMPTY;
	}
}
