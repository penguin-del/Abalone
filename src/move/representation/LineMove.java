package move.representation;

import formation.shape.Line;

public abstract class LineMove extends Move
{	
	
	protected Line _line;
	
	public LineMove(Line line)
	{
		_line = line;
	}
	public LineMove() {
		_line=null;
	}
}
