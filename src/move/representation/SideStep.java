package move.representation;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Formation;
import formation.shape.Line;
import formation.shape.Line.HexagonCardinalDirections;
import graph.Node;

public class SideStep extends LineMove
{
	//direction in which you are moving that line, or moving all the nodes in that line
	protected HexagonCardinalDirections _direction;

	//SideSteps are made with a line from, and the direction in which you want to move the line
	public SideStep(Line line, HexagonCardinalDirections direction)
	{
		super(line);
		_direction=direction;
	}

	//makes the actual move on the board, instead of just suggesting possible moves
	//returns false because move is not going off board
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
		for (Node node : _line)
		{
			// Remove the marble from current location
			MarbleColor color = layer.remove(node._col, node._row);

			//
			// Relocate the marble based on directionality
			//
			switch(_direction)
			{
			case NORTHEAST:
				Node ne = node.getNorthEast();
				layer.add(ne._col, ne._row, color);
				break;

			case EAST:
				Node e = node.getEast();
				layer.add(e._col, e._row, color);
				break;

			case SOUTHEAST:
				Node se = node.getSouthEast();
				layer.add(se._col, se._row, color);
				break;

			case SOUTHWEST:
				Node sw = node.getSouthWest();
				layer.add(sw._col, sw._row, color);
				break;

			case WEST:
				Node w = node.getWest();
				layer.add(w._col, w._row, color);
				break;

			case NORTHWEST:
				Node nw = node.getNorthWest();
				layer.add(nw._col, nw._row, color);
				break;

			default:
				System.err.println("Unexpected: SideStep::makeMove");
			}
		}
	}

	//
	//	private boolean makeNorthEastSideStep(BoardGame bg)
	//	{
	//
	//		//making it into vertex, so can get neighbors
	//		Node v= bg.getBoard().getVertex(_line.get(i));
	//		bg.getBoard().changeValue(v.getNorthEast(), bg.getBoard().getValue(_line.getUpperEndpoint()));
	//		bg.getBoard().changeValue(_line.get(i), MarbleColor.EMPTY);
	//	}
	//
	//	private boolean makeEastSideStep(BoardGame bg) {
	//		for (int i=0; i<_line.size();i++) {
	//			//making it into vertex, so can get neighbors
	//			Node v= bg.getBoard().getVertex(_line.get(i));
	//			bg.getBoard().changeValue(v.getEast(), bg.getBoard().getValue(_line.getUpperEndpoint()));
	//			bg.getBoard().changeValue(_line.get(i), MarbleColor.EMPTY);
	//		}
	//		return false;
	//	}
	//
	//	private boolean makeSouthEastSideStep(BoardGame bg) {
	//		for (int i=0; i<_line.size();i++) {
	//			//making it into vertex, so can get neighbors
	//			Node v= bg.getBoard().getVertex(_line.get(i));
	//			bg.getBoard().changeValue(v.getSouthEast(), bg.getBoard().getValue(_line.getUpperEndpoint()));
	//			bg.getBoard().changeValue(_line.get(i), MarbleColor.EMPTY);
	//		}
	//		return false;
	//	}
	//
	//	private boolean makeSouthWestSideStep(BoardGame bg) {
	//		for (int i=0; i<_line.size();i++) {
	//			//making it into vertex, so can get neighbors
	//			Node v= bg.getBoard().getVertex(_line.get(i));
	//			bg.getBoard().changeValue(v.getSouthWest(), bg.getBoard().getValue(_line.getUpperEndpoint()));
	//			bg.getBoard().changeValue(_line.get(i), MarbleColor.EMPTY);
	//		}
	//		return false;
	//	}
	//
	//	private boolean makeWestSideStep(BoardGame bg) {
	//		for (int i=0; i<_line.size();i++) {
	//			//making it into vertex, so can get neighbors
	//			Node v= bg.getBoard().getVertex(_line.get(i));
	//			bg.getBoard().changeValue(v.getWest(), bg.getBoard().getValue(_line.getUpperEndpoint()));
	//			bg.getBoard().changeValue(_line.get(i), MarbleColor.EMPTY);
	//		}
	//		return false;
	//	}
	//
	//	private boolean makeNorthWestSideStep(BoardGame bg) {
	//		for (int i=0; i<_line.size();i++) {
	//			//making it into vertex, so can get neighbors
	//			Node v= bg.getBoard().getVertex(_line.get(i));
	//			bg.getBoard().changeValue(v.getNorthWest(), bg.getBoard().getValue(_line.getUpperEndpoint()));
	//			bg.getBoard().changeValue(_line.get(i), MarbleColor.EMPTY);
	//		}
	//		return false;
	//	}


	//equals method to check if SideSteps are equal, for testing
	@Override
	public boolean equals (Object obj)
	{
		if(!(obj instanceof SideStep)) return false;
		return sSEquals((SideStep) obj);
	}

	public boolean sSEquals(SideStep that)
	{
		return _line.equals(that._line) && _direction == that._direction;
	}


	// CTA: Same code AGAIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Override
	public boolean moveApplies(Formation formation) {
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
	public String toString()
	{
		return "Line" + _line.toString() + "is moving "+ _direction;
	}








}
