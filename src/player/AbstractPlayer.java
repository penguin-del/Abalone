package player;

import board.Layer;
import board.Marble.MarbleColor;

public abstract class AbstractPlayer
{
	public abstract Layer takeTurn(Layer layer, MarbleColor color);
	
	public void reset() {}
}
