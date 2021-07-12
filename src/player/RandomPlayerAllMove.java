package player;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import move.generator.MoveGenerator;
import move.representation.*;
import utilities.LocalRandom;

public class RandomPlayerAllMove extends AbstractPlayer
{
	protected ArrayList<Move> _allMoves;
	protected MoveGenerator _mg;

	@Override
	public Layer takeTurn(Layer currLayer, MarbleColor color)
	{
		_mg = new MoveGenerator(currLayer);
		_allMoves = _mg.computeAllMoves(color);
		
		// Returns a new layer with the move made
		return _allMoves.get(LocalRandom.nextInt(_allMoves.size())).makeMoveOnCopyBoard(currLayer);
//		
//		//Since Move has five sub-methods to look through and each have different requirements for construction,
//		//We will check the class of the move make methods to make moves for each one.
//		if (_allMoves.get(random_Move) instanceof SimpleMove) return randomlyChosenSimpleMove(random_Move, bg);
//		if (_allMoves.get(random_Move) instanceof ShiftLine) return randomlyChosenShiftLine(random_Move, bg);
//		if (_allMoves.get(random_Move) instanceof SideStep) return randomlyChosenSideStep(random_Move, bg);
//		if (_allMoves.get(random_Move) instanceof LinePushNode) return randomlyChosenLinePushNode(random_Move, bg);
//		return randomlyChosenLinePushLine(random_Move, bg);
	}

//	private boolean randomlyChosenLinePushLine(int index, BoardGame bg) {
//		
//		//Gets the pushing line, pushed line, and the node to which the pushed line is pushed.
//		Line pusher = ((LinePushLine) _allMoves.get(index)).getLine();
//		Line pushed = ((LinePushLine) _allMoves.get(index)).getPushed();
//		Node destination = ((LinePushLine) _allMoves.get(index)).getDestination();
//		
//		LinePushLine chosenPush = new LinePushLine(pusher, pushed, destination);
//
//		return chosenPush.makeMove(bg);
//	}
//
//	private boolean randomlyChosenLinePushNode(int index, BoardGame bg) {
//		
//		//Gets the pushing line, pushed node, and the node to which the pushed node is pushed.
//		Line pusher = ((LinePushNode) _allMoves.get(index)).getLine();
//		Node pushed = ((LinePushNode)_allMoves.get(index)).getPushed();
//		Node destination =  ((LinePushNode)_allMoves.get(index)).getDestination();
//	
//		LinePushNode chosenPush = new LinePushNode(pusher, pushed, destination);
//
//		return chosenPush.makeMove(bg);
//	}
//
//	private boolean randomlyChosenSideStep(int index, BoardGame bg) {
//		Line from = ((SideStep) _allMoves.get(index)).getFrom();
//		HexagonCardinalDirections direction = ((SideStep) _allMoves.get(index)).getDirection();
//		
//		//makes the chosen move
//		SideStep chosenStep = new SideStep(from, direction);
//		return 	chosenStep.makeMove(bg);
//	}
//
//	private boolean randomlyChosenShiftLine(int index, BoardGame bg) {
//		
//		//Gets the line that's moving and the node to which it's moving.
//		Line from = ((ShiftLine) _allMoves.get(index)).getFrom();
//		Node to = ((ShiftLine) _allMoves.get(index)).getTo();
//		
//		//initializes and makes a move
//		ShiftLine chosenShift = new ShiftLine(from, to);
//		return chosenShift.makeMove(bg);
//	}
//
//	private boolean randomlyChosenSimpleMove(int index, BoardGame bg) {
//		Node from = ((SimpleMove) _allMoves.get(index)).getFrom();
//		Node to = ((SimpleMove) _allMoves.get(index)).getTo();
//		
//		//makes a move
//		SimpleMove chosenMove = new SimpleMove(from, to);
//
//		return chosenMove.makeMove(bg);
//	}
}
