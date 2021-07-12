package move.representation;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Formation;
import formation.shape.Line;
import graph.Node;

//
//CTA: Amazing what pictures could do with this class. MakeMove would make more sense, etc.
//

public class LinePushLine extends Push
{
	protected Line _pushed;	// Line that is getting pushed

	public LinePushLine(Line line, Line pushed, Node destination)
	{
		super(line, destination);

		_pushed = pushed;
	}


	//should return true if pushed off board, returns false if move is on board
	@Override
	public void makeMoveOnOriginalBoard(Layer layer)
	{
		makeMoveWithLayer(layer);
	}
	
	//should return true if pushed off board, returns false if move is on board
	@Override
	public Layer makeMoveOnCopyBoard(Layer layer)
	{
		Layer newLayer = layer.getClone();
		
		makeMoveWithLayer(newLayer);
		
		return newLayer;
	}
	
	private void makeMoveWithLayer(Layer layer)
	{
		//if destination isn't going off the board
		if(layer.isValid(_destination._col,_destination._row))
		{
			//if it is getting pushed toward its lowerEndPoint
			if (_line.getLowerEndpoint().compareTo(_pushed.getUpperEndpoint()) > 0) {
				makeItOnBoard(layer, _pushed.getUpperEndpoint(), _line.getUpperEndpoint());
				return;
			}
			else {
				makeItOnBoard(layer, _pushed.getLowerEndpoint(), _line.getLowerEndpoint());
				return;
			}
		}

		//if it is pushing off edge, checks to see which way we are pushing, toward LowerEndPoint or UpperEndPoint
		if (_line.getLowerEndpoint().compareTo(_pushed.getUpperEndpoint()) > 0) {
			makeItOffBoard(layer, _pushed.getUpperEndpoint(), _line.getUpperEndpoint());
		}
		else {
			makeItOffBoard(layer, _pushed.getLowerEndpoint(), _line.getLowerEndpoint());
		}
	}

	private void makeItOnBoard(Layer layer, Node pushed, Node shoving)
	{
		// Remove the pushed marble and shift it to the destination
		MarbleColor pushColor = layer.remove(pushed._col, pushed._row);		
		layer.add(_destination._col, _destination._row, pushColor);

		// Remove the line marble at the endpoint...it will be shifted to the _pushed position
		MarbleColor shovingColor = layer.remove(shoving._col, shoving._row);
		layer.add(pushed._col, pushed._row, shovingColor);
	}	
	
	private void makeItOffBoard(Layer layer, Node pushed, Node shoving)
	{
		// Remove the pushed marble and shift it to the destination
		layer.remove(pushed._col, pushed._row);

		// Remove the line marble at the endpoint...it will be shifted to the _pushed position
		MarbleColor shovingColor = layer.remove(shoving._col, shoving._row);
		layer.add(pushed._col, pushed._row, shovingColor);
	}
	
	
	//	// pushed: _pushed.getUpperEndpoint() shoving: _line.getUpperEndpoint()
	//	private boolean pushLowerEndPointOnBoard(Layer layer)
	//    {
	//		bg.getBoard().changeValue(_pushed.getUpperEndpoint(), bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//		//make the space you moved from empty
	//		bg.getBoard().changeValue(_line.getUpperEndpoint(), MarbleColor.EMPTY);
	//
	//		//change destination to the color of the pushed line
	//		bg.getBoard().changeValue(_destination, bg.getBoard().getValue(_pushed.getLowerEndpoint()));
	//		return false;
	//	}
	//
	//	// pushed: _pushed.getLowerEndpoint() shoving: _line.getLowerEndpoint()
	//	private boolean pushUpperEndPointOnBoard(Layer layer) {
	//		bg.getBoard().changeValue(_pushed.getLowerEndpoint(), bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//		//make the space you moved from empty
	//		bg.getBoard().changeValue(_line.getLowerEndpoint(), MarbleColor.EMPTY);
	//
	//		bg.getBoard().changeValue(_destination, bg.getBoard().getValue(_pushed.getUpperEndpoint()));
	//
	//		return false;
	//	}

	


	//	// pushed: _pushed.getUpperEndpoint() shoving: _line.getUpperEndpoint()
	//	private boolean pushLowerEndPointOffBoard(Layer layer)
	//	{
	//		//change upperEndPoint of pushed to sumito
	//		bg.getBoard().changeValue(_pushed.getUpperEndpoint(), bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//		//change sumito upperEndPoint to empty
	//		bg.getBoard().changeValue(_line.getUpperEndpoint(), MarbleColor.EMPTY);
	//
	//		return true;
	//	}

	////// pushed: _pushed.getLowerEndpoint() shoving: _line.getLowerEndpoint()
	//private boolean pushUpperEndPointOffBoard(Layer layer)
	//{
	//	bg.getBoard().changeValue(_pushed.getLowerEndpoint(), bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//	//change sumitoLowerEndPoint to Empty
	//	bg.getBoard().changeValue(_line.getLowerEndpoint(), MarbleColor.EMPTY);
	//
	//	return true;
	//}


	@Override
	public boolean equals (Object obj) {
		if(!(obj instanceof LinePushLine)) return false;
		LinePushLine that = (LinePushLine) obj;
		return linePushEquals(that);
	}

	public boolean linePushEquals(LinePushLine that) {

		if(!(this._line.equals(that._line))) return false;

		if(!(this._pushed.equals(that._pushed))) return false;

		return true;
	}

	@Override
	public String toString() {
		return _line + " is pushing " + _pushed + " to " + _destination;
	}

	// CTA: NO comments here. You are using numbers 2, 3 etc like that is obvious.
	// It is not since there are no explanatory comments in this file.
	// Code is repeated many times...why? Have a utility method.
	@Override
	public boolean moveApplies(Formation formation)
	{
		int count = 0;
		switch(this._line.size())
		{
		case 2:
			for (Node n : formation.decompose())
			{
				if(_line.contains(n)) count++;
				if (count == 2) return true;
			}
			break;

		case 3:
			for (Node n : formation.decompose())
			{
				if(_line.contains(n)) count++;
				if (count == 3) return true;
			}
			break;

		default:
			System.err.println("Unexpected number of lines in forma");
		}
		return false;
	}
}
