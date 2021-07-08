package move.generator;

import java.util.ArrayList;
import board.Layer;
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
	protected ArrayList<SimpleMove> _simple;
	protected ArrayList<ShiftLine> _shift;
	protected ArrayList<SideStep> _sideStep;
	protected Layer _layer;
	
	protected LineComputer _lineComp;

	public NonPushMoveGenerator(Layer layer)
	{
		_simple = new ArrayList<SimpleMove>();
		_shift= new ArrayList<ShiftLine>();
		_sideStep= new ArrayList<SideStep>();
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
		AbaloneGraph graph = AbaloneGraph.get();

		for(Node from: graph.getPopulatedNodes(color))
		{
			// for each neighbor of non-empty node
			for(Node to: from.getNeighbors())
			{
				// if space is empty, add to SimpleMoves
				if (_layer.isEmpty(to._col, to._row)) _simple.add(new SimpleMove(from, to));
			}
		}
		return _simple;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////


	////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////// Shift Move Calculation ///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	//should be private

	private void makeShiftLineMove(Line line, Node candidate)
	{
		if (_layer.isEmpty(candidate._col, candidate._row)) _shift.add(new ShiftLine(line, candidate));
	}

	//adds all right to left diag line moves to list
	private void addRightToLeftDiag(Line line) 
	{
		//if line is not right to left, return
		if (line.getDirection()!=Direction.RIGHTTOLEFTDIAG) return;
		{
			makeShiftLineMove(line, new Node((line.getLowerEndpoint()._col),line.getLowerEndpoint()._row-1));

			makeShiftLineMove(line, new Node((line.getUpperEndpoint()._col) , line.getUpperEndpoint()._row+1));
		}
	}

	//adds all left to right diag line moves to list
	private void addLeftToRightDiag(Line line)
	{
		//if line is not left to right, return
		if(line.getDirection() != Direction.LEFTTORIGHTDIAG) return;
		{
			makeShiftLineMove(line, new Node( (char) (line.getLowerEndpoint()._col-1),line.getLowerEndpoint()._row-1));

			makeShiftLineMove(line, new Node((char) (line.getUpperEndpoint()._col+1) , line.getUpperEndpoint()._row+1));
		}
	}
	
	//adds all horizontal line moves to list
	private void addHorizontal(Line line) 
	{
		//if line is not horizontal, return
		if(line.getDirection() != Direction.HORIZONTAL) return;
		{
			makeShiftLineMove(line,new Node((char) (line.getLowerEndpoint()._col-1),line.getLowerEndpoint()._row));

			makeShiftLineMove(line,new Node((char) (line.getUpperEndpoint()._col+1) , line.getUpperEndpoint()._row));
		}
	}


	//computes all possible shifts for lines 2 and 3
	public ArrayList<ShiftLine> computeAllShifts(MarbleColor color)
	{
		//gets possible shifts for all lines 
		for (Line line : _lineComp.getLines(2, color))
		{
			addRightToLeftDiag(line);
			addLeftToRightDiag(line);
			addHorizontal(line);
		}
		for(Line line : _lineComp.getLines(3, color)) {
			addRightToLeftDiag(line);
			addLeftToRightDiag(line);
			addHorizontal(line);
		}

		return _shift;
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
		//goes through every line and computes sidesteps for those lines
		for (Line line : _lineComp.getLines(2, color))
		{
			_sideStep.addAll(computeSideStep(line));
		}
		
		for (Line line : _lineComp.getLines(3, color)) {
			_sideStep.addAll(computeSideStep(line));
		}
		
		return _sideStep;
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
