package move.generator;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import formation.shape.Line;
import formation.shape.Line.Direction;
import graph.Node;
import move.representation.LinePushLine;

public class LinePushLineMoveGenerator 
{

	protected Layer _layer;
	public ArrayList<LinePushLine> _whiteLinePushLine;
	public ArrayList<LinePushLine> _blackLinePushLine;



	public LinePushLineMoveGenerator(Layer layer) {
		_layer = layer;
		_whiteLinePushLine= new ArrayList<LinePushLine>();
		_blackLinePushLine= new ArrayList<LinePushLine>();
	}
	//computes all LinePushLine for giving lines
	public ArrayList<LinePushLine>computeAllLinePushLine(MarbleColor color)
	{
		LineComputer lc = new LineComputer(_layer);
		// Given all lines of length 3;
		// compute all moves in which 3 can push 2. 

		for (Line line : lc.getLines(3, color))
		{
			checkHorizontal(line, color);
			checkLeftToRight(line, color);
			checkRightToLeft(line, color);
		}

		//this list was populated by the loop above
		if (color == MarbleColor.WHITE)return _whiteLinePushLine;
		return _blackLinePushLine;

	}

	//checks to see if line is Horizontal, then calls checkPushedNode with candidate
	public void checkHorizontal(Line line, MarbleColor color)
	{
		if (line.getDirection() != Direction.HORIZONTAL) return;

		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		//calls checkPushedNode with candidate node
		checkPushedLine(line, new Node((char) (le._col-1),le._row), color);
		checkPushedLine(line, new Node((char) (ue._col+1),ue._row), color);
	}

	//checks to see if line is RightToLeft, then calls checkPushedNode with candidate
	public void checkRightToLeft(Line line, MarbleColor color)
	{
		if (line.getDirection() != Direction.RIGHTTOLEFTDIAG) return;

		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		checkPushedLine(line, new Node((char) (le._col),le._row-1), color);

		checkPushedLine(line, new Node((char) (ue._col),ue._row+1), color);

	}

	//checks to see if line is LeftToRight, then calls checkPushedNode with candidate
	public void checkLeftToRight(Line line, MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();
		if (line.getDirection()!=Direction.LEFTTORIGHTDIAG) return;

		checkPushedLine(line, new Node((char) (le._col-1),le._row-1), color);

		checkPushedLine(line, new Node((char) (ue._col+1),ue._row+1), color);


	}
	//checks the pushed "line" to make sure it is completely the right color
	private void checkPushedLine(Line line, Node candidate1, MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		if (_layer.sameColor(candidate1._col, candidate1._row, le._col, le._row) || 
				!_layer.isValid(candidate1._col, candidate1._row)|| 
				_layer.isEmpty(candidate1._col, candidate1._row)) return;

		//if candidate is the lower side of horizontal
		if((char) le._col-1==(candidate1._col) && (le._row==(candidate1._row))) {
			check2LowerHorizontal(line, color);
		}

		//if candidate is the upper side of horizontal
		if(ue._row==(candidate1._row) && ((char)ue._col+1==(candidate1._col))){
			check2UpperHorizontal(line, color);
		}

		//if candidate is lower side of right to left
		if(le._row-1==(candidate1._row) && (le._col==(candidate1._col))){
			check2LowerRightToLeft(line, color);
		}

		//if candidate is upper side of right to left
		if(ue._row+1==(candidate1._row) &&((char)ue._col==(candidate1._col))){
			check2UpperRightToLeft(line, color);
		}

		//if candidate is lower side of left to right
		if(le._row-1==(candidate1._row) &&((char)le._col-1==(candidate1._col))){
			check2LowerLeftToRight(line, color);
		}

		//if candidate is lower side of left to right
		if(ue._row+1==(candidate1._row) &&((char)ue._col+1==(candidate1._col))){
			check2UpperLeftToRight(line, color);
		}

	}
	private void check2UpperLeftToRight(Line line, MarbleColor color)
	{
		Node ue = line.getUpperEndpoint();
		if (line.getDirection()!=Direction.LEFTTORIGHTDIAG) return;

		//making upperCandidate1 1 row and column away
		Node upperCand1 = new Node((char) (ue._col+1), ue._row+1);

		//making upperCandidate2 2 rows away
		Node upperCand2 = new Node((char) (ue._col+2), ue._row+2);

		//makingDestination 3 nodes away
		Node destination= new Node((char) (ue._col+3), ue._row+3);

		//if the value isn't opposite sumitoLine value, return
		if (_layer.sameColor(upperCand2._col, upperCand2._row, ue._col, ue._row) || 
				!_layer.isValid(upperCand2._col, upperCand2._row)|| 
				_layer.isEmpty(upperCand2._col, upperCand2._row)) return;

		makePushedLineMove(line, upperCand1, upperCand2, destination, color);

	}
	private void check2LowerLeftToRight(Line line, MarbleColor color)
	{
		Node le = line.getLowerEndpoint();

		if (line.getDirection() != Direction.LEFTTORIGHTDIAG) return;
		//making lowerCandidate 1 row and column away
		Node lowerCand1 = new Node((char) (le._col-1), le._row-1);

		//making lowerDestination 2 rows away
		Node lowerCand2= new Node((char) (le._col-2), le._row-2);

		Node destination= new Node((char) (le._col-3), le._row-3);


		//if the value isn't opposite sumitoLine value, return
		if (_layer.sameColor(lowerCand2._col, lowerCand2._row, le._col, le._row) ||
				!_layer.isValid(lowerCand2._col, lowerCand2._row)|| 
				_layer.isEmpty(lowerCand2._col, lowerCand2._row)) return;
		

		makePushedLineMove(line, lowerCand1, lowerCand2, destination, color);

	}
	private void check2UpperRightToLeft(Line line, MarbleColor color)
	{
		Node ue = line.getUpperEndpoint();

		if (line.getDirection()!=Direction.RIGHTTOLEFTDIAG) return;

		//making lowerCandidate 1 row away
		Node upperCand1 = new Node((ue._col), ue._row+1);

		//making lowerDestination 2 rows away
		Node upperCand2= new Node((ue._col), ue._row+2);

		Node destination= new Node((ue._col), ue._row+3);


		//if the value isn't opposite sumitoLine value, return
		//		if(_bg.getBoard().getValue(upperCandidate2)!=_bg.getBoard().flipColor(le)) return;
		if (_layer.sameColor(upperCand2._col, upperCand2._row, ue._col, ue._row) ||
				!_layer.isValid(upperCand2._col, upperCand2._row)|| 
				_layer.isEmpty(upperCand2._col, upperCand2._row)) return;


		makePushedLineMove(line, upperCand1, upperCand2, destination, color);

	}
	private void check2LowerRightToLeft(Line line, MarbleColor color)
	{
		Node le = line.getLowerEndpoint();

		if (line.getDirection() != Direction.RIGHTTOLEFTDIAG) return;

		//making lowerCandidate 1 row away
		Node lowerCand1 = new Node((le._col), le._row-1);

		//making lowerDestination 2 rows away
		Node lowerCand2= new Node((le._col), le._row-2);

		Node destination= new Node((le._col), le._row-3);


		//if the value isn't opposite sumitoLine value, return
		if (_layer.sameColor(lowerCand2._col, lowerCand2._row, le._col, le._row) ||
				!_layer.isValid(lowerCand2._col, lowerCand2._row)|| 
				_layer.isEmpty(lowerCand2._col, lowerCand2._row)) return;
		//if(_bg.getBoard().getValue(lowerCandidate2)!=_bg.getBoard().flipColor(le)) return;


		makePushedLineMove(line, lowerCand1, lowerCand2, destination, color);

	}
	private void check2UpperHorizontal(Line line, MarbleColor color)
	{
		Node ue = line.getUpperEndpoint();
		if ((line.getDirection()!=Direction.HORIZONTAL)) return;

		Node upperCand1 = new Node((char) (ue._col + 1), ue._row);

		Node upperCand2= new Node((char) (ue._col + 2), ue._row);

		Node destination= new Node((char) (ue._col + 3), ue._row);

		//if value 2 spaces away is not equal to flipped color of line, return 
		if (_layer.sameColor(upperCand2._col, upperCand2._row, ue._col, ue._row) ||
				!_layer.isValid(upperCand2._col, upperCand2._row)|| 
				_layer.isEmpty(upperCand2._col, upperCand2._row)) return;


		// CTA: IS this a bug? le vs ue?
		// if(_bg.getBoard().getValue(upperCandidate2)!=_bg.getBoard().flipColor(le)) return;

		makePushedLineMove(line, upperCand1, upperCand2, destination, color);

	}

	private void check2LowerHorizontal(Line line, MarbleColor color)
	{
		Node le = line.getLowerEndpoint();
		if ((line.getDirection()!=Direction.HORIZONTAL)) return;

		Node lowerCand1 = new Node((char) (le._col - 1), le._row);

		Node lowerCand2 = new Node((char) (le._col - 2), le._row);

		Node destination= new Node((char) (le._col - 3), le._row);

		//if value 2 spaces away is equal to line color, return
		if (_layer.sameColor(lowerCand2._col, lowerCand2._row, le._col, le._row) ||
				!_layer.isValid(lowerCand2._col, lowerCand2._row)|| 
				_layer.isEmpty(lowerCand2._col, lowerCand2._row)) return;

		//if(_bg.getBoard().getValue(lowerCandidate2)!=_bg.getBoard().flipColor(le)) return;


		makePushedLineMove(line, lowerCand1, lowerCand2, destination, color);

	}
	private void makePushedLineMove(Line sumitoLine, Node upperCandidate1, Node upperCandidate2, Node destination, MarbleColor color)
	{
		//if destination space isn't empty and is on board, return
		if (!_layer.isEmpty(destination._col, destination._row)&&_layer.isValid(destination._col, destination._row)) return;

		// if(!_bg.isEmptyorInvalid(destination)) return;

		//you now know its a line, so create line with 2 candidates
		Line pushedLine = new Line();
		pushedLine.addToLine(upperCandidate1);
		pushedLine.addToLine(upperCandidate2);

		//create LinePushLine with both lines and the destination node
		LinePushLine linePushed= new LinePushLine(sumitoLine, pushedLine, destination);

		//adds linepushline based on color
		if (color==MarbleColor.WHITE) _whiteLinePushLine.add(linePushed);
		else _blackLinePushLine.add(linePushed);

	}
}
