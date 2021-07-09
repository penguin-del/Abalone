package move.generator;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import move.representation.Move;

public class MoveGenerator
{
	// All possible moves
	protected ArrayList<Move> _allWhiteMoves;
	protected ArrayList<Move> _allBlackMoves;

	protected ArrayList<Move> _simpleWhiteMoves;
	protected ArrayList<Move> _simpleBlackMoves;

	protected ArrayList<Move> _shiftWhiteLines;
	protected ArrayList<Move> _shiftBlackLines;

	protected ArrayList<Move> _sideWhiteSteps;
	protected ArrayList<Move> _sideBlackSteps;
	// All PushMoves: LinePushNode and LinePushLine
	protected ArrayList<Move> _pushWhiteMoves;
	protected ArrayList<Move> _pushBlackMoves;




	protected Layer _layer;

	//create ArrayList of each move up here, then method that calls each compute all only if empty.
	public MoveGenerator(Layer layer)
	{
		_simpleWhiteMoves= new ArrayList<Move>();
		_simpleBlackMoves= new ArrayList<Move>();
		_pushWhiteMoves= new ArrayList<Move>();
		_pushBlackMoves= new ArrayList<Move>();
		_shiftWhiteLines= new ArrayList<Move>();
		_shiftBlackLines= new ArrayList<Move>();
		_sideWhiteSteps= new ArrayList<Move>();
		_sideBlackSteps= new ArrayList<Move>();
		_allWhiteMoves= new ArrayList<Move>();
		_allBlackMoves= new ArrayList<Move>();
		_layer = layer;
	}

	public ArrayList<Move> computeAllSimpleMove(MarbleColor color)
	{
		if(color==MarbleColor.WHITE) {
			if(!_simpleWhiteMoves.isEmpty()) return _simpleWhiteMoves;

			NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);

			_simpleWhiteMoves.addAll(npm.computeAllSimple(color));

			return _simpleWhiteMoves;
		}

		if(!_simpleBlackMoves.isEmpty()) return _simpleBlackMoves;

		NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);

		_simpleBlackMoves.addAll(npm.computeAllSimple(color));

		return _simpleBlackMoves;
	}

	public ArrayList<Move> computeAllShiftLine(MarbleColor color)
	{
		if(color==MarbleColor.WHITE) {
			if(!_shiftWhiteLines.isEmpty()) return _shiftWhiteLines;

			NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);
			_shiftWhiteLines.addAll(npm.computeAllShifts(color));

			return _shiftWhiteLines;
		}

		if(!_shiftBlackLines.isEmpty()) return _shiftBlackLines;

		NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);
		_shiftBlackLines.addAll(npm.computeAllShifts(color));

		return _shiftBlackLines;
	}

	public ArrayList<Move> computeAllSideStep(MarbleColor color)
	{
		if (color==MarbleColor.WHITE) {
			if(!_sideWhiteSteps.isEmpty()) return _sideWhiteSteps;

			NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);
			_sideWhiteSteps.addAll(npm.computeAllSideSteps(color));

			return _sideWhiteSteps;
		}

		if(!_sideBlackSteps.isEmpty()) return _sideBlackSteps;

		NonPushMoveGenerator npm = new NonPushMoveGenerator(_layer);
		_sideBlackSteps.addAll(npm.computeAllSideSteps(color));

		return _sideBlackSteps;
	}

	public ArrayList<Move> computeAllPushMoves(MarbleColor color)
	{
		if (color==MarbleColor.WHITE) {
			if(!_pushWhiteMoves.isEmpty()) return _pushWhiteMoves;

			LinePushLineMoveGenerator lpl = new LinePushLineMoveGenerator(_layer);
			_pushWhiteMoves.addAll(lpl.computeAllLinePushLine(color));

			LinePushNodeMoveGenerator lpn = new LinePushNodeMoveGenerator(_layer);
			_pushWhiteMoves.addAll(lpn.computeAllLinePushNodes(color));

			return _pushWhiteMoves;
		}
		
		if(!_pushBlackMoves.isEmpty()) return _pushBlackMoves;

		LinePushLineMoveGenerator lpl = new LinePushLineMoveGenerator(_layer);
		_pushBlackMoves.addAll(lpl.computeAllLinePushLine(color));

		LinePushNodeMoveGenerator lpn = new LinePushNodeMoveGenerator(_layer);
		_pushBlackMoves.addAll(lpn.computeAllLinePushNodes(color));

		return _pushBlackMoves;
	}

	public ArrayList<Move> computeAllMoves(MarbleColor color)
	{
		if (color==MarbleColor.WHITE) {
			_allWhiteMoves.addAll(computeAllPushMoves(color));
			_allWhiteMoves.addAll(computeAllSideStep(color));
			_allWhiteMoves.addAll(computeAllShiftLine(color));
			_allWhiteMoves.addAll(computeAllSimpleMove(color));

			return _allWhiteMoves;
		}
		else {
			_allBlackMoves.addAll(computeAllPushMoves(color));
			_allBlackMoves.addAll(computeAllSideStep(color));
			_allBlackMoves.addAll(computeAllShiftLine(color));
			_allBlackMoves.addAll(computeAllSimpleMove(color));

			return _allBlackMoves;
		}
	}
}
