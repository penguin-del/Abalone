package experiment;

import player.AbstractPlayer;

public class Experiment_Parameters {
	
	public AbstractPlayer _player1;
	public AbstractPlayer _player2;
	public int _maxTurns;
	public int _iterations;
	
	public Experiment_Parameters(AbstractPlayer player1, AbstractPlayer player2, int maxTurns, int iterations) {
		_player1 = player1;
		_player2 = player2;
		_maxTurns = maxTurns;
		_iterations = iterations;
	}
	
}