package move.generator;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import move.representation.Move;

public class MoveGenerator
{
	// All possible moves
	protected ArrayList<Move> _allMoves;

	protected ArrayList<Move> _simpleMoves;

	// All PushMoves: LinePushNode and LinePushLine
	protected ArrayList<Move> _pushMoves;

	protected ArrayList<Move> _shiftLines;

	protected ArrayList<Move> _sideSteps;

	protected Layer _layer;

	//create ArrayList of each move up here, then method that calls each compute all only if empty.
	public MoveGenerator(Layer layer)
	{
		_simpleMoves= new ArrayList<Move>();
		_pushMoves= new ArrayList<Move>();
		_shiftLines= new ArrayList<Move>();
		_sideSteps= new ArrayList<Move>();
		_allMoves= new ArrayList<Move>();
		_layer = layer;
	}

	public ArrayList<Move> computeAllSimpleMove(MarbleColor color)
	{
		if(!_simpleMoves.isEmpty()) return _simpleMoves;

		NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);

		_simpleMoves.addAll(npm.computeAllSimple(color));

		return _simpleMoves;
	}

	public ArrayList<Move> computeAllShiftLine(MarbleColor color)
	{
		if(!_shiftLines.isEmpty()) return _shiftLines;

		NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);
		_shiftLines.addAll(npm.computeAllShifts(color));

		return _shiftLines;
	}

	public ArrayList<Move> computeAllSideStep(MarbleColor color)
	{
		if(!_sideSteps.isEmpty()) return _sideSteps;

		NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);
		_sideSteps.addAll(npm.computeAllSideSteps(color));

		return _sideSteps;
	}

	public ArrayList<Move> computeAllPushMoves(MarbleColor color)
	{
		if(!_pushMoves.isEmpty()) return _pushMoves;

		LinePushLineMoveGenerator lpl = new LinePushLineMoveGenerator(_layer);
		_pushMoves.addAll(lpl.computeAllLinePushLine(color));

		LinePushNodeMoveGenerator lpn = new LinePushNodeMoveGenerator(_layer);
		_pushMoves.addAll(lpn.computeAllLinePushNodes(color));

		return _pushMoves;
	}

	public ArrayList<Move> computeAllMoves(MarbleColor color)
	{
		_allMoves.addAll(computeAllPushMoves(color));
		_allMoves.addAll(computeAllSideStep(color));
		_allMoves.addAll(computeAllShiftLine(color));
		_allMoves.addAll(computeAllSimpleMove(color));

		return _allMoves;
	}
}
