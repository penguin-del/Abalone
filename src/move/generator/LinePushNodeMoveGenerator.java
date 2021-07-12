package move.generator;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import formation.shape.Line;
import formation.shape.Line.Direction;
import move.representation.LinePushNode;
import graph.Node;

public class LinePushNodeMoveGenerator
{
	protected Layer _layer;
	protected ArrayList<LinePushNode> _whiteLinePushNode;
	protected ArrayList<LinePushNode> _blackLinePushNode;

	public LinePushNodeMoveGenerator(Layer layer)
	{
		_layer = layer;
		_whiteLinePushNode= new ArrayList<LinePushNode>();
		_blackLinePushNode= new ArrayList<LinePushNode>();
	}

	//Computes all LinePushNodes for a Given arrayList of Lines
	public ArrayList<LinePushNode> computeAllLinePushNodes(MarbleColor color)
	{
		LineComputer lc = new LineComputer(_layer);

		for (Line line : lc.getLines(2, color))
		{
			checkHorizontal(line, color);
			checkLeftToRight(line, color);
			checkRightToLeft(line, color);
		}
		for (Line line : lc.getLines(3, color))
		{
			checkHorizontal(line, color);
			checkLeftToRight(line, color);
			checkRightToLeft(line, color);
		}
		if (color==MarbleColor.WHITE) return _whiteLinePushNode;
		else return _blackLinePushNode;
	}


	//checks if candidate node is opposite color, then calls method(check2...) to add it as potential move
	private void checkPushedNode(Line line, Node candidate,  MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		//if the marble(candidate) that you are trying to push is not the opposite color, return
		if (!_layer.isValid(candidate._col, candidate._row)) return;
		if	(_layer.isEmpty(candidate._col, candidate._row)) return;
		if (_layer.sameColor(candidate._col, candidate._row, le._col, le._row)) return;

		//		if((_bg.getBoard().getValue(candidate)) != 
		//				(_bg.getBoard().flipColor(line.getLowerEndpoint()))){
		//			return;
		//		}

		//if candidate is the lower side of horizontal
		if((char) le._col-1==(candidate._col) && (le._row==(candidate._row))) {
			check2LowerHorizontal(line, color);
		}

		//if candidate is the upper side of horizontal
		if((char) ue._col+1==(candidate._col) && (ue._row==(candidate._row))) {
			check2UpperHorizontal(line, color);
		}

		//if candidate is lower side of right to left
		if(le._row-1==(candidate._row) && (le._col==(candidate._col))){
			check2LowerRightToLeft(line, color);
		}

		//if candidate is lower side of right to left
		if(ue._row+1==(candidate._row) && (ue._col==(candidate._col))){
			check2UpperRightToLeft(line, color);
		}

		//if candidate is lower side of left to right
		if(le._row-1==(candidate._row) &&((char) le._col-1==(candidate._col))){
			check2LowerLeftToRight(line, color);
		}

		//if candidate is lower side of left to right
		if(ue._row+1==(candidate._row) &&((char) ue._col+1==(candidate._col))){
			check2UpperLeftToRight(line, color);
		}

	}


	//checks to see if line is horizontal, then calls checkPushedNode with candidate
	public void checkHorizontal(Line line,  MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		if (line.getDirection()!=Direction.HORIZONTAL) return;

		//calls checkPushedNode with candidate node
		checkPushedNode(line, new Node((char) (le._col-1),le._row), color);

		checkPushedNode(line, new Node((char) (ue._col+1),ue._row), color);
	}

	//checks to see if line is RightToLeft, then calls checkPushedNode with candidate
	public void checkRightToLeft(Line line,  MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		if (line.getDirection()!=Direction.RIGHTTOLEFTDIAG) return;

		checkPushedNode(line, new Node((char) (le._col),le._row-1), color);

		checkPushedNode(line, new Node((char) (ue._col),ue._row+1), color);

	}

	//checks to see if line is LeftToRight, then calls checkPushedNode with candidate
	public void checkLeftToRight(Line line,  MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		if (line.getDirection() != Direction.LEFTTORIGHTDIAG) return;

		checkPushedNode(line, new Node((char) (le._col-1),le._row-1), color);

		checkPushedNode(line, new Node((char) (ue._col+1),ue._row+1), color);
	}

	//Checks upper side of horizontal line, and adds as LinePushNode
	public void check2UpperHorizontal(Line line,  MarbleColor color)
	{
		if ((line.getDirection() != Direction.HORIZONTAL)) return;

		Node ue = line.getUpperEndpoint();

		Node upperCandidate = new Node((char) (ue._col+1), ue._row);

		Node upperDestination= new Node((char) (ue._col+2), ue._row);


		makePushedNodeMove(line,upperCandidate,upperDestination, color);
	}

	//Checks lower side of horizontal line, and adds as LinePushNode
	public void check2LowerHorizontal(Line line,  MarbleColor color)
	{
		if (line.getDirection()!=Direction.HORIZONTAL) return;

		Node le = line.getLowerEndpoint();
		//making lowerCandidate 1 column away
		Node lowerCandidate = new Node((char) (le._col-1), le._row);

		//checking lowerDestination 2 columns away
		Node lowerDestination= new Node((char) (le._col-2), le._row);

		makePushedNodeMove(line, lowerCandidate, lowerDestination, color);

	}

	//Checks lower side of RightToLeft line, and adds as LinePushNode
	public void check2LowerRightToLeft(Line line,  MarbleColor color)
	{
		if (line.getDirection()!=Direction.RIGHTTOLEFTDIAG) return;
		Node le = line.getLowerEndpoint();

		//making lowerCandidate 1 row away
		Node lowerCandidate = new Node((le._col), le._row-1);

		//making lowerDestination 2 rows away
		Node lowerDestination= new Node((le._col), le._row-2);

		makePushedNodeMove(line, lowerCandidate, lowerDestination, color);
	}

	//Checks upper side of RighToLeft line, and adds as LinePushNode
	public void check2UpperRightToLeft(Line line,  MarbleColor color) 
	{
		if (line.getDirection()!=Direction.RIGHTTOLEFTDIAG) return;
		Node ue = line.getUpperEndpoint();

		//making lowerCandidate 1 row away
		Node upperCandidate = new Node((ue._col), ue._row+1);

		//making lowerDestination 2 rows away
		Node upperDestination= new Node((ue._col), ue._row+2);

		makePushedNodeMove(line, upperCandidate, upperDestination, color);
	}

	//Checks lower side of LeftToRight line, and adds as LinePushNode
	public void check2LowerLeftToRight(Line line,  MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		if (line.getDirection()!=Direction.LEFTTORIGHTDIAG) return;

		//making lowerCandidate 1 row and column away
		Node lowerCandidate = new Node((char) (le._col-1), le._row-1);

		//making lowerDestination 2 rows away
		Node lowerDestination= new Node((char) (le._col-2), le._row-2);

		makePushedNodeMove(line, lowerCandidate, lowerDestination, color);
	}

	//Checks upper side of LeftToRight line, and adds as LinePushNode
	public void check2UpperLeftToRight(Line line,  MarbleColor color)
	{

		if (line.getDirection()!=Direction.LEFTTORIGHTDIAG) return;
		Node ue = line.getUpperEndpoint();

		//making lowerCandidate 1 row away
		Node upperCandidate = new Node((char) (ue._col+1), ue._row+1);

		//making lowerDestination 2 rows away
		Node upperDestination= new Node((char) (ue._col+2), ue._row+2);

		makePushedNodeMove(line, upperCandidate, upperDestination, color);
	}

	public void makePushedNodeMove(Line line, Node candidate, Node destination, MarbleColor color)
	{
		LinePushNode lpn= null;

		//add move to possible lines pushing list
		//if it isn't empty or null, return
		if (!_layer.isValid(destination._col, destination._row)) {
			lpn= new LinePushNode(line, candidate, destination);
		}
		else if (_layer.isEmpty(destination._col, destination._row)) {
			lpn= new LinePushNode(line, candidate, destination);
		}
		if (lpn == null) return;


		addMove(color, lpn);
	}
	private void addMove(MarbleColor color, LinePushNode move) {
		//adds to proper linepushnode list based on color
		if (color==MarbleColor.WHITE) _whiteLinePushNode.add(move);
		else _blackLinePushNode.add(move);
	}
}


