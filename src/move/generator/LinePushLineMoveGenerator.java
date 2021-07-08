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
	public ArrayList<LinePushLine> _linePushLine;


	public LinePushLineMoveGenerator(Layer layer) {
		_layer = layer;
		_linePushLine= new ArrayList<LinePushLine>();
	}
	//computes all LinePushLine for giving lines
	public ArrayList<LinePushLine>computeAllLinePushLine(MarbleColor color)
	{
		LineComputer lc = new LineComputer(_layer);

		// Given all lines of length 3;
		// compute all moves in which 3 can push 2. 
		for (Line line : lc.getLines(3, color))
		{
			checkHorizontal(line);
			checkLeftToRight(line);
			checkRightToLeft(line);
		}

		//this list was populated by the loop above
		return _linePushLine;

	}

	//checks to see if line is Horizontal, then calls checkPushedNode with candidate
	public void checkHorizontal(Line line)
	{
		if (line.getDirection() != Direction.HORIZONTAL) return;

		Node le = line.getLowerEndpoint();
		//Node ue = line.getUpperEndpoint();

		//calls checkPushedNode with candidate node
		checkPushedLine(line, new Node((char) (le._col-1),le._row));
		checkPushedLine(line, new Node((char) (line.getUpperEndpoint()._col+1),line.getUpperEndpoint()._row));
	}

	//checks to see if line is RightToLeft, then calls checkPushedNode with candidate
	public void checkRightToLeft(Line line)
	{
		if (line.getDirection() != Direction.RIGHTTOLEFTDIAG) return;

		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		checkPushedLine(line, new Node((char) (le._col),le._row-1));

		checkPushedLine(line, new Node((char) (ue._col),ue._row+1));

	}

	//checks to see if line is LeftToRight, then calls checkPushedNode with candidate
	public void checkLeftToRight(Line line)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();
		if (line.getDirection()!=Direction.LEFTTORIGHTDIAG) return;

		checkPushedLine(line, new Node((char) (le._col-1),le._row-1));

		checkPushedLine(line, new Node((char) (ue._col+1),ue._row+1));


	}
	//checks the pushed "line" to make sure it is completely the right color
	private void checkPushedLine(Line line, Node candidate1)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		if (_layer.oppositeColor(candidate1._col, candidate1._row, le._col, le._row)) return;
		//if candidate is the lower side of horizontal
		if((char) le._col-1==(candidate1._col) && (le._row==(candidate1._row))) {
			check2LowerHorizontal(line);
		}

		//if candidate is the upper side of horizontal
		if(le._row-1==(candidate1._row) && (le._col==(candidate1._col))){
			check2UpperHorizontal(line);
		}

		//if candidate is lower side of right to left
		if(ue._row+1==(candidate1._row) && (ue._col==(candidate1._col))){
			check2LowerRightToLeft(line);
		}

		//if candidate is lower side of right to left
		if(le._row-1==(candidate1._row) &&((char)le._col-1==(candidate1._col))){
			check2UpperRightToLeft(line);
		}

		//if candidate is lower side of left to right
		if(le._row-1==(candidate1._row) &&((char)le._col-1==(candidate1._col))){
			check2LowerLeftToRight(line);
		}

		//if candidate is lower side of left to right
		if(ue._row+1==(candidate1._row) &&((char)ue._col+1==(candidate1._col))){
			check2UpperLeftToRight(line);
		}

	}
	private void check2UpperLeftToRight(Line line)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();
		if (line.getDirection()!=Direction.LEFTTORIGHTDIAG) return;

		//making upperCandidate1 1 row and column away
		Node upperCand1 = new Node((char) (ue._col+1), ue._row+1);

		//making upperCandidate2 2 rows away
		Node upperCand2 = new Node((char) (ue._col+2), ue._row+2);

		//makingDestination 3 nodes away
		Node destination= new Node((char) (line.getUpperEndpoint()._col+3), line.getUpperEndpoint()._row+3);


		//if the value isn't opposite sumitoLine value, return
		if (_layer.sameColor(upperCand2._col, upperCand2._row, le._col, le._row)) return;

		makePushedLineMove(line, upperCand1, upperCand2, destination);

	}
	private void check2LowerLeftToRight(Line line)
	{
		Node le = line.getLowerEndpoint();
		//Node ue = line.getUpperEndpoint();

		if (line.getDirection() != Direction.LEFTTORIGHTDIAG) return;
		//making lowerCandidate 1 row and column away
		Node lowerCand1 = new Node((char) (le._col-1), le._row-1);

		//making lowerDestination 2 rows away
		Node lowerCand2= new Node((char) (le._col-2), le._row-2);

		Node destination= new Node((char) (le._col-3), le._row-3);


		//if the value isn't opposite sumitoLine value, return
		if (_layer.sameColor(lowerCand2._col, lowerCand2._row, le._col, le._row)) return;

		makePushedLineMove(line, lowerCand1, lowerCand2, destination);

	}
	private void check2UpperRightToLeft(Line line)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();

		if (line.getDirection()!=Direction.RIGHTTOLEFTDIAG) return;

		//making lowerCandidate 1 row away
		Node upperCand1 = new Node((ue._col), ue._row+1);

		//making lowerDestination 2 rows away
		Node upperCand2= new Node((ue._col), ue._row+2);

		Node destination= new Node((ue._col), ue._row+3);


		//if the value isn't opposite sumitoLine value, return
		//		if(_bg.getBoard().getValue(upperCandidate2)!=_bg.getBoard().flipColor(le)) return;
		if (_layer.sameColor(upperCand2._col, upperCand2._row, le._col, le._row)) return;


		makePushedLineMove(line, upperCand1, upperCand2, destination);

	}
	private void check2LowerRightToLeft(Line line)
	{
		Node le = line.getLowerEndpoint();

		if (line.getDirection() != Direction.RIGHTTOLEFTDIAG) return;

		//making lowerCandidate 1 row away
		Node lowerCand1 = new Node((le._col), le._row-1);

		//making lowerDestination 2 rows away
		Node lowerCand2= new Node((le._col), le._row-2);

		Node destination= new Node((le._col), le._row-3);


		//if the value isn't opposite sumitoLine value, return
		if (_layer.sameColor(lowerCand2._col, lowerCand2._row, le._col, le._row)) return;
		//if(_bg.getBoard().getValue(lowerCandidate2)!=_bg.getBoard().flipColor(le)) return;


		makePushedLineMove(line, lowerCand1, lowerCand2, destination);

	}
	private void check2UpperHorizontal(Line line)
	{
		Node le = line.getLowerEndpoint();
		Node ue = line.getUpperEndpoint();
		if ((line.getDirection()!=Direction.HORIZONTAL)) return;

		Node upperCand1 = new Node((char) (ue._col + 1), ue._row);

		Node upperCand2= new Node((char) (ue._col + 2), ue._row);

		Node destination= new Node((char) (ue._col + 3), ue._row);

		//if value 2 spaces away is not equal to flipped color of line, return 
		if (_layer.sameColor(upperCand2._col, upperCand2._row, le._col, le._row)) return;


		// CTA: IS this a bug? le vs ue?
		// if(_bg.getBoard().getValue(upperCandidate2)!=_bg.getBoard().flipColor(le)) return;

		makePushedLineMove(line, upperCand1, upperCand2, destination);

	}

	private void check2LowerHorizontal(Line line)
	{
		Node le = line.getLowerEndpoint();
		if ((line.getDirection()!=Direction.HORIZONTAL)) return;

		Node lowerCand1 = new Node((char) (le._col - 1), le._row);

		Node lowerCand2 = new Node((char) (le._col - 2), le._row);

		Node destination= new Node((char) (le._col - 3), le._row);

		//if value 2 spaces away is not equal to flipped color of line, return 
		if (_layer.sameColor(lowerCand2._col, lowerCand2._row, le._col, le._row)) return;

		//if(_bg.getBoard().getValue(lowerCandidate2)!=_bg.getBoard().flipColor(le)) return;


		makePushedLineMove(line, lowerCand1, lowerCand2, destination);

	}
	private void makePushedLineMove(Line sumitoLine, Node upperCandidate1, Node upperCandidate2, Node destination)
	{
		//if destination space isn't empty or invalid, return
		if (!_layer.isEmpty(destination._col, destination._row)) return;

		// if(!_bg.isEmptyorInvalid(destination)) return;

		//you now know its a line, so create line with 2 candidates
		Line pushedLine = new Line();
		pushedLine.addToLine(upperCandidate1);
		pushedLine.addToLine(upperCandidate2);

		//create LinePushLine with both lines and the destination node
		LinePushLine linePushed= new LinePushLine(sumitoLine, pushedLine, destination);

		//add to moveList
		_linePushLine.add(linePushed);
	}
}
