package experiment;


import java.io.IOException;
import board.Game;
import board.Layer;
import board.Marble.MarbleColor;
import utilities.Timer;

public class Experiment
{
	protected int _whiteWinCounter;
	protected int _blackWinCounter;
	
	protected Experiment_Parameters _params;
	
	public Experiment(Experiment_Parameters params)
    {
		_params = params;
	}

	public ExperimentResults run() throws IOException
    {
		Timer timer = new Timer();
		
		int whiteMoveCounter = 0;
		int blackMoveCounter = 0;

		timer.start();
		for(int i = 0; i < _params._iterations; i++)
        {
			//creates the board from the standard text file
			Layer startLayer = Layer.getDefaultBoard();

			//initializes the game with the board game and players then plays the game
			Game game = new Game(startLayer, _params._player1, _params._player2, _params._maxTurns);
			MarbleColor winner = game.play();
			if(winner == MarbleColor.WHITE) _whiteWinCounter++;
			if(winner == MarbleColor.BLACK) _blackWinCounter++;
			whiteMoveCounter += game._whiteMoves;
			blackMoveCounter += game._blackMoves;
		}
		timer.stop();
		
		return new ExperimentResults(_blackWinCounter, _whiteWinCounter, whiteMoveCounter, blackMoveCounter, timer);

//		System.out.print(timer + "\n");
//		System.out.print("White won "+_whiteWinCounter+ " times\n");
//		System.out.print("Black won "+_blackWinCounter+ " times\n");
	}
	
	
}