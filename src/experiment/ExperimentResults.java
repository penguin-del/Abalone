package experiment;

import java.util.ArrayList;

import utilities.Timer;

public class ExperimentResults
{	
	public int _player1Wins;
	public int _player2Wins;
	public int _player1Moves;
	public int _player2Moves;
	public int _counts;
	public Timer _time;
	
	// CTA: Why is this an Array of Objects????
	public ArrayList<Object> _countSpecificMoves;
	
	public ExperimentResults(int blackWins, int whiteWins, int blackMoves, int whiteMoves, Timer time){
		_player1Wins = blackWins;
		_player2Wins = whiteWins;
		_player1Moves = blackMoves;
		_player2Moves = whiteMoves;	
		_time = time;
	}
	
	
	public String toString()
    {
		String resultString = "";
		
		resultString += "Player 1 won " + _player1Wins + " times. \n";
		resultString += "Player 2 won " + _player2Wins + " times \n";
		resultString += "Player 1 took a total of " + _player1Moves + " moves \n";
		resultString += "Player 2 took a total of " + _player2Moves + " moves \n";
		resultString += "The games took " + _time + "\n";
		
		//Don't yet know how to count and call specific moves.
		resultString += "Player 1 made__ simple moves \n";
		resultString += "Player 1 made__ shift line moves \n";
		resultString += "Player 1 made__ side step moves \n";
		resultString += "Player 1 made__ push moves \n";
		resultString += "Player 2 made__ simple moves \n";
		resultString += "Player 2 made__ shift line moves \n";
		resultString += "Player 2 made__ side step moves \n";
		resultString += "Player 2 made__ push moves \n";
		
		return resultString;
	}
}
