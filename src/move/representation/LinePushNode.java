package move.representation;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Formation;
import formation.shape.Line;
import graph.Node;

public class LinePushNode extends Push
{
	protected Node _pushed; 	// Node that is getting pushed

	public LinePushNode(Line line, Node pushed, Node destination)
	{
		super(line, destination);

		_pushed = pushed;
	}

	@Override
	//returns true if move is pushed offBoard, returns false otherwise
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
		if (layer.isValid(_destination._col,_destination._row))
		{
			//if it is pushing toward lowerEndPoint side
			if((_line.getLowerEndpoint().compareTo(_pushed)) > 0) {
				makeItOnBoard(layer, _line.getUpperEndpoint());
				return;
			}
			if((_line.getUpperEndpoint().compareTo(_pushed)) < 0) {
				//pushing toward upperEndPoint side
				makeItOnBoard(layer, _line.getLowerEndpoint());
				return;
			}

		}

		//pushing off board toward LowerEndPoint Side
		if ((_line.getLowerEndpoint().compareTo(_pushed)) > 0)
			makeItOffBoard(layer, _line.getUpperEndpoint()); //return pushLowerEndPointOffBoard(bg);

		//if it is pushing off board toward UpperEndPoint Side
		makeItOffBoard(layer, _line.getLowerEndpoint());
		//return pushUpperEndPointOffBoard(bg);
	}

	private void makeItOnBoard(Layer layer, Node endpointToRemove)
	{
		//
		// Remove the pushed marble and shift it to the destination
		MarbleColor pushColor = layer.remove(_pushed._col, _pushed._row);		
		layer.add(_destination._col, _destination._row, pushColor);

		// Remove the line marble at the endpoint...it will be shifted to the _pushed position
		MarbleColor lineColor = layer.remove(endpointToRemove._col, endpointToRemove._row);
		layer.add(_pushed._col, _pushed._row, lineColor);
	}
	
	private void makeItOffBoard(Layer layer, Node endpointToRemove)
	{
		// Remove the pushed marble
		layer.remove(_pushed._col, _pushed._row);		

		// Remove the line marble at the endpoint...it will be shifted to the _pushed position
		MarbleColor lineColor = layer.remove(endpointToRemove._col, endpointToRemove._row);
		layer.add(_pushed._col, _pushed._row, lineColor);
	}


	//	//if pushing on board toward the lowerEnd
	//	private boolean pushLowerEndPointOnBoard(BoardGame bg) {
	//
	//		bg.getBoard().changeValue(_destination, bg.getBoard().getValue(_pushed));
	//
	//		//change value of node to the correct value
	//		bg.getBoard().changeValue(_pushed, bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//		//make the space you moved from empty
	//		bg.getBoard().changeValue(_line.getUpperEndpoint(), MarbleColor.EMPTY);
	//
	//		return false;
	//
	//	}
	//
	//	//if pushing on board toward upperEnd
	//	private boolean pushUpperEndPointOnBoard(BoardGame bg) {
	//
	//		bg.getBoard().changeValue(_destination, bg.getBoard().getValue(_pushed));
	//
	//		bg.getBoard().changeValue(_pushed, bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//		//make the space you moved from empty
	//		bg.getBoard().changeValue(_line.getLowerEndpoint(), MarbleColor.EMPTY);
	//
	//		return false;
	//
	//	}

	//	//if pushing off board toward lowerEnd
	//	private boolean pushLowerEndPointOffBoard(BoardGame bg) {
	//
	//		bg.getBoard().changeValue(_pushed, bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//		bg.getBoard().changeValue(_line.getUpperEndpoint(), MarbleColor.EMPTY);
	//
	//		return true;
	//	}
	//
	//	//if pushing off board toward upperEnd
	//	private boolean pushUpperEndPointOffBoard(BoardGame bg) {
	//
	//		bg.getBoard().changeValue(_pushed, bg.getBoard().getValue(_line.getLowerEndpoint()));
	//
	//		bg.getBoard().changeValue(_line.getLowerEndpoint(), MarbleColor.EMPTY);
	//
	//		return true;
	//	}

	

	@Override
	public boolean equals (Object obj) 
	{
		if(!(obj instanceof LinePushNode)) return false;
		return linePushEquals((LinePushNode) obj);
	}

	public boolean linePushEquals(LinePushNode that) {

		if(!(this._line.equals(that._line))) return false;

		if(!(this._pushed.equals(that._pushed))) return false;

		return true;
	}

	@Override
	public boolean moveApplies(Formation formation)
	{
		int count = 0;
		if (this._line.size()==2) {
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


	@Override
	public String toString() {
		return _line + " is pushing "+ _pushed + " to "+ _destination;
	}

	
}


