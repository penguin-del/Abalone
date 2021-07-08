package player;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import move.generator.NonPushMoveGenerator;
import move.representation.ShiftLine;
import utilities.LocalRandom;

public class RandomPlayerShiftLine extends AbstractPlayer{

	protected ArrayList<ShiftLine> _playableMoves;
	protected NonPushMoveGenerator _mg;
	protected LineComputer _lc;



	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {

		//every time PlayMove is called, this method will create a new MoveGenerator and a new line computer with the updated board information
		_mg = new NonPushMoveGenerator(layer); 

		//This initializes a list of all the possible moves that conform to a particular move style; in this case shifting
		_playableMoves = _mg.computeAllShifts(color); 

		//in case no shifting exists, we will initialize a simple move generator
		RandomPlayerSimpleMove rpsimple = new RandomPlayerSimpleMove();

		//call the simple move generator if there is no shifting moves
		if(_playableMoves.isEmpty()) {
			return rpsimple.takeTurn(layer, color);
		}
		return _playableMoves.get(LocalRandom.nextInt(_playableMoves.size())).makeMove(layer);
		//else {
			//randomly chooses a move
//			Random rand = new Random();
//			int random_Move = rand.nextInt(_playableMoves.size());
//			Line from = _playableMoves.get(random_Move).getFrom();
//			Node to = _playableMoves.get(random_Move).getTo();
			
			//System.out.print("Shift: "+from +"-->" + to + "\n");

			//makes a move
			//ShiftLine chosenShift = new ShiftLine(from, to);
			
			//return chosenShift.makeMove(bg);
		}
	}

