package board;

import player.AbstractPlayer;

public class Game_Parameter {
	protected int _numBlackMarbles;
	protected int _numWhiteMarbles;
	protected int _numWinningMarbles;
	protected AbstractPlayer _player1;
	protected AbstractPlayer _player2;
	public Game_Parameter(int numWinningMarbles, int numWhiteMarbles, int numBlackMarbles, AbstractPlayer player1, AbstractPlayer player2) {
		_numBlackMarbles=numBlackMarbles;
		_numWhiteMarbles=numWhiteMarbles;
		_numWinningMarbles=numWinningMarbles;
		_player1= player1;
		_player2=player2;
	}
	
	
	/*
	 * returns number of black marbles
	 */
	public int getNumBlackMarbles() {
		return _numBlackMarbles;
	}
	
	
	/*
	 * returns number of white marbles
	 */
	public int getNumWhiteMarbles() {
		return _numWhiteMarbles;
	}
	
	
	/*
	 * returns number of marbles in order to win
	 */
	public int getNumWinningMarbles() {
		return _numWinningMarbles;
	}
	
	
	/*
	 * returns type of player1
	 */
	public AbstractPlayer getPlayer1() {
		return _player1;
	}
	
	
	/*
	 * returns type of player2
	 */
	public AbstractPlayer getPlayer2() {
		return _player2;
	}
}
