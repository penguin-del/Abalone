package move.representation;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Formation;
import formation.shape.Line;
import graph.Node;

public class ShiftLine extends LineMove
{
	//Node that you are moving to, moving the "back" node of _from to this node
	// CTA: A PICTURE would explain clearly.
	// Notice how this comment is invalid because there is NO _from
	protected Node _to;

	//ShiftLine is composed of a line and a node
	public ShiftLine(Line line, Node to)
	{
		super(line);
		_to = to;
	}

	//Makes the actual move, instead of adding it as a possible move
	@Override
	public Layer makeMoveOnCopyBoard(Layer layer)
	{
		Layer newLayer = layer.getClone();
		
		makeMoveWithLayer(newLayer);
		
		return newLayer;
	}
	
	@Override
	public void makeMoveOnOriginalBoard(Layer layer) 
	{
		makeMoveWithLayer(layer);
	}


	private void makeMoveWithLayer(Layer layer) {
		//for example with a line (B,2) (C,2) (D,2) that is trying to move to (A,2)
		//first checks if (B,2) is greater than (A,2) which it is, so it takes the UpperEndPoint and moves that

		if ((_line.getLowerEndpoint().compareTo(_to)) > 0) makeIt(layer, _line.getUpperEndpoint());

		//if moving toward lower endPoint, changing upperEndPoint value
		else{
			makeIt(layer, _line.getLowerEndpoint());
		}
	}
	
	// Actually make the move
	private void makeIt(Layer layer, Node from)
	{
		// Remove _to from the layer and grab the marble's color
		MarbleColor color = layer.remove(from._col, from._row);

		if (color == MarbleColor.EMPTY || color == MarbleColor.INVALID)
		{
			System.err.println("Empty or invalid node in ShiftLine::makeIt");
			return;
		}

		layer.add(_to._col, _to._row, color);
	}

	// CTA: I've seen this code before...STOP COPYING-PASTING code.
	@Override
	public boolean moveApplies(Formation formation)
	{
		int count = 0;
		if (this._line.size()==2)
		{
			for (Node n : formation.decompose()) {
				if(_line.contains(n)) count++;
				if (count==2) return true;
			}
		}

		if (this._line.size()==3) {
			for (Node n : formation.decompose()) {
				if(_line.contains(n)) count++;
				if (count==3) return true;
			}
		}
		return false;
	}

	//checks to see if 2 ShiftLines are equal
	@Override
	public boolean equals (Object obj)
	{
		if(!(obj instanceof ShiftLine)) return false;
		ShiftLine that = (ShiftLine) obj;
		return smEquals(that);
	}

	public boolean smEquals(ShiftLine that)
	{
		return _line.equals(that._line) && _to.equals(that._to);
	}

	//	public Line getFrom() {
	//		return _line;
	//	}
	//
	//	public Node getTo() {
	//		return _to;
	//	}

	@Override
	public String toString() {
		return "Line" + _line.toString() + "is moving to"+ _to.toSimpleString();
	}
}
