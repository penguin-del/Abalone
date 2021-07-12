package move.generator;

import java.util.ArrayList;
import board.Layer;
import board.LightNode;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import formation.shape.Line;
import formation.shape.Line.Direction;
import formation.shape.Line.HexagonCardinalDirections;
import graph.AbaloneGraph;
import graph.Node;
import move.representation.*;

public class NonPushMoveGenerator
{
	protected ArrayList<SimpleMove> _whiteSimple;
	protected ArrayList<SimpleMove> _blackSimple;
	protected ArrayList<ShiftLine> _whiteShift;
	protected ArrayList<ShiftLine> _blackShift;
	protected ArrayList<SideStep> _whiteSideStep;
	protected ArrayList<SideStep> _blackSideStep;
	protected Layer _layer;


	protected LineComputer _lineComp;

	public NonPushMoveGenerator(Layer layer)
	{
		_whiteSimple = new ArrayList<SimpleMove>();
		_blackSimple = new ArrayList<SimpleMove>();
		_whiteShift= new ArrayList<ShiftLine>();
		_blackShift= new ArrayList<ShiftLine>();
		_whiteSideStep= new ArrayList<SideStep>();
		_blackSideStep= new ArrayList<SideStep>();
		_layer = layer;
		_lineComp = new LineComputer(_layer);
	}

	// TB: Generate all moves (including sidestep, shifting, simple)
	public ArrayList<Move> computeAllNonPush(MarbleColor color)
	{
		ArrayList <Move> moves = new ArrayList<Move>();
		moves.addAll(computeAllSimple(color));
		moves.addAll(computeAllShifts(color));
		moves.addAll(computeAllSideSteps(color));
		return moves;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////// Simple Move Calculation ///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////

	//
	public ArrayList<SimpleMove> computeAllSimple(MarbleColor color)
	{
		if(color==MarbleColor.WHITE) {

			for(LightNode lightFrom: _layer.getNodes(color)){
				{
					// for each neighbor of non-empty node
					Node heavyFrom = AbaloneGraph.get().getVertex(lightFrom._col, lightFrom._row);

					for(Node to: heavyFrom.getNeighbors())
					{
						// if space is empty, add to SimpleMoves
						if (_layer.isEmpty(to._col, to._row)) _whiteSimple.add(new SimpleMove(heavyFrom, to));
					}
				}
			}
			return _whiteSimple;
		}

		for(LightNode lightFrom: _layer.getNodes(color)){
			{
				// for each neighbor of non-empty node
				Node heavyFrom = AbaloneGraph.get().getVertex(lightFrom._col, lightFrom._row);

				for(Node to: heavyFrom.getNeighbors())
				{
					// if space is empty, add to SimpleMoves
					if (_layer.isEmpty(to._col, to._row)) _blackSimple.add(new SimpleMove(heavyFrom, to));
				}
			}
		}
		return _blackSimple;
	}



	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////


	////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////// Shift Move Calculation ///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	//should be private

	private void makeShiftLineMove(Line line, Node candidate, MarbleColor color)
	{
		if(!_layer.isValid(candidate._col, candidate._row)) return;
		if(! _layer.isEmpty(candidate._col, candidate._row)) return;
		if (color==MarbleColor.WHITE) _whiteShift.add(new ShiftLine(line, candidate));
		if (color==MarbleColor.BLACK) _blackShift.add(new ShiftLine(line, candidate));
	}

	//adds all right to left diag line moves to list
	private void addRightToLeftDiag(Line line, MarbleColor color) 
	{
		//if line is not right to left, return
		if (line.getDirection()!=Direction.RIGHTTOLEFTDIAG) return;
		{
			makeShiftLineMove(line, new Node((line.getLowerEndpoint()._col),line.getLowerEndpoint()._row-1), color);

			makeShiftLineMove(line, new Node((line.getUpperEndpoint()._col), line.getUpperEndpoint()._row+1),color);
		}
	}

	//adds all left to right diag line moves to list
	private void addLeftToRightDiag(Line line, MarbleColor color)
	{
		//if line is not left to right, return
		if(line.getDirection() != Direction.LEFTTORIGHTDIAG) return;
		{
			makeShiftLineMove(line, new Node( (char) (line.getLowerEndpoint()._col-1),line.getLowerEndpoint()._row-1), color);

			makeShiftLineMove(line, new Node((char) (line.getUpperEndpoint()._col+1) , line.getUpperEndpoint()._row+1), color);
		}
	}

	//adds all horizontal line moves to list
	private void addHorizontal(Line line, MarbleColor color) 
	{
		//if line is not horizontal, return
		if(line.getDirection() != Direction.HORIZONTAL) return;
		{
			makeShiftLineMove(line,new Node((char) (line.getLowerEndpoint()._col-1),line.getLowerEndpoint()._row), color);

			makeShiftLineMove(line,new Node((char) (line.getUpperEndpoint()._col+1) , line.getUpperEndpoint()._row), color);
		}
	}


	//computes all possible shifts for lines 2 and 3
	public ArrayList<ShiftLine> computeAllShifts(MarbleColor color)
	{
		if(color==MarbleColor.WHITE) {
			//gets possible shifts for all lines 
			for (Line line : _lineComp.getLines(2, color))
			{
				addRightToLeftDiag(line, color);
				addLeftToRightDiag(line,color);
				addHorizontal(line,color);
			}
			for(Line line : _lineComp.getLines(3, color)) {
				addRightToLeftDiag(line,color);
				addLeftToRightDiag(line,color);
				addHorizontal(line, color);
			}

			return _whiteShift;
		}
		//gets possible shifts for all lines 
		for (Line line : _lineComp.getLines(2, color))
		{
			addRightToLeftDiag(line, color);
			addLeftToRightDiag(line,color);
			addHorizontal(line,color);
		}
		for(Line line : _lineComp.getLines(3, color)) {
			addRightToLeftDiag(line,color);
			addLeftToRightDiag(line,color);
			addHorizontal(line, color);
		}

		return _blackShift;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////SideStep Calculation/////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////

	//gathers all possible sidesteps into one ArrayList
	public ArrayList<SideStep> computeAllSideSteps(MarbleColor color)
	{

		if (color== MarbleColor.WHITE) {
			//goes through every line and computes sidesteps for those lines
			for (Line line : _lineComp.getLines(2, color))
			{
				_whiteSideStep.addAll(computeSideStep(line));
			}

			for (Line line : _lineComp.getLines(3, color)) {
				_whiteSideStep.addAll(computeSideStep(line));
			}

			return _whiteSideStep;
		}
		for (Line line : _lineComp.getLines(2, color))
		{
			_blackSideStep.addAll(computeSideStep(line));
		}

		for (Line line : _lineComp.getLines(3, color)) {
			_blackSideStep.addAll(computeSideStep(line));
		}

		return _blackSideStep;
	}


	//computes all possible sidesteps for a single line
	public ArrayList<SideStep> computeSideStep(Line ell)
	{
		ArrayList<SideStep> moves = new ArrayList<SideStep>();

		//if NorthEast is valid sidestep, add to list
		SideStep result = computeNorthEastSideStep(ell);
		if (result != null) moves.add(result);

		//if East is valid sidestep, add to list
		result = computeEastSideStep(ell);
		if (result != null) moves.add(result);

		//if SouthEast is valid sidestep, add to list
		result = computeSouthEastSideStep(ell);
		if (result != null) moves.add(result);

		//if SouthWest is valid sidestep, add to list
		result = computeSouthWestSideStep(ell);
		if (result != null) moves.add(result);

		//if West is valid sidestep, add to list
		result = computeWestSideStep(ell);
		if (result != null) moves.add(result);

		//if NorthWest is valid sidestep, add to list
		result = computeNorthWestSideStep(ell);
		if (result != null) moves.add(result);

		return moves;
	}


	public SideStep computeNorthEastSideStep(Line line)
	{
		//goes through line and checks to make sure each node can move in that direction
		for (int i = 0; i < line.size(); i++)
		{
			Node neighborNE = line.get(i).getNorthEast();
			if (neighborNE == null) return null;

			if (!_layer.isEmpty(neighborNE._col, neighborNE._row)) return null;
		}
		// Build SideStep object representing this legitimate move.

		return new SideStep(line, HexagonCardinalDirections.NORTHEAST);

	}

	public SideStep computeEastSideStep(Line line)
	{
		//goes through line and checks to make sure each node can move in that direction
		for (int i = 0; i < line.size(); i++)
		{
			Node neighborE = line.get(i).getEast();
			if (neighborE == null) return null;

			if (!_layer.isEmpty(neighborE._col, neighborE._row)) return null;
		}

		// Build SideStep object representing this legitimate move.
		return new SideStep(line, HexagonCardinalDirections.EAST);
	}

	public SideStep computeSouthEastSideStep(Line line)
	{
		//goes through line and checks to make sure each node can move in that direction
		for (int i = 0; i < line.size(); i++)
		{
			Node neighborSE = line.get(i).getSouthEast();
			if (neighborSE == null) return null;

			if (!_layer.isEmpty(neighborSE._col, neighborSE._row)) return null;
			//if (_bg.getBoard().getValue(neighborSE) != MarbleColor.EMPTY) return null;
		}

		// Build SideStep object representing this legitimate move.
		return new SideStep(line, HexagonCardinalDirections.SOUTHEAST);
	}

	public SideStep computeSouthWestSideStep(Line line)
	{
		//goes through line and checks to make sure each node can move in that direction
		for (int i = 0; i < line.size(); i++)
		{
			Node neighborSW = line.get(i).getSouthWest();
			if (neighborSW == null) return null;

			if (!_layer.isEmpty(neighborSW._col, neighborSW._row)) return null;
			//if (_bg.getBoard().getValue(neighborSW) != MarbleColor.EMPTY) return null;
		}

		// Build SideStep object representing this legitimate move.
		return new SideStep(line, HexagonCardinalDirections.SOUTHWEST);
	}

	public SideStep computeWestSideStep(Line line)
	{
		//goes through line and checks to make sure each node can move in that direction
		for (int i = 0; i < line.size(); i++)
		{
			Node neighborW = line.get(i).getWest();
			if (neighborW == null) return null;

			if (!_layer.isEmpty(neighborW._col, neighborW._row)) return null;
			//if (_bg.getBoard().getValue(neighborW) != MarbleColor.EMPTY) return null;
		}

		// Build SideStep object representing this legitimate move.
		return new SideStep(line, HexagonCardinalDirections.WEST);
	}

	public SideStep computeNorthWestSideStep(Line line)
	{
		//goes through line and checks to make sure each node can move in that direction
		for (int i = 0; i < line.size(); i++)
		{
			Node neighborNW = line.get(i).getNorthWest();
			if (neighborNW == null) return null;

			if (!_layer.isEmpty(neighborNW._col, neighborNW._row)) return null;
			//if (_bg.getBoard().getValue(neighborNW) != MarbleColor.EMPTY) return null;
		}

		// Build SideStep object representing this legitimate move.
		return new SideStep(line, HexagonCardinalDirections.NORTHWEST);
	}
}
