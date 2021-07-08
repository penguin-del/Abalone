package board;

import board.Marble.MarbleColor;
//NO LONGER IN USE. ARCHAIC CLASS. JUST RETURN LENGTH OF LISTS WITHIN LAYERS!
public class MarbleCounter {
	
	protected int _numBlackMarbles;
	protected int _numWhiteMarbles;
	
	public MarbleCounter(int blackMarbles, int whiteMarbles) {
		_numBlackMarbles = blackMarbles;
		_numWhiteMarbles = whiteMarbles;
		
	}
		
	public void pushedOff (MarbleColor color) {
		if (color == MarbleColor.BLACK) _numBlackMarbles -= 1;
		if (color == MarbleColor.WHITE) _numWhiteMarbles -= 1;
	}
	
	public int getNumBlackMarbles() {
		return _numBlackMarbles;
	}
	
	public int getWhiteMarbles() {
		return _numWhiteMarbles;
	}

}
