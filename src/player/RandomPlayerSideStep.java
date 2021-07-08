package player;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import formation.shape.Line;
import move.generator.NonPushMoveGenerator;
import move.representation.SideStep;
import utilities.LocalRandom;

public class RandomPlayerSideStep extends AbstractPlayer{

	protected ArrayList<SideStep> _playableMoves;
	protected LineComputer _lc;
	protected NonPushMoveGenerator _mg;


	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {

		//every time PlayMove is called, this method will create a new MoveGenerator and a new line computer with the updated board information
		_mg = new NonPushMoveGenerator(layer);
		_lc = new LineComputer(layer);
		ArrayList<Line> lines = _lc.getLines(2, color);
		lines.addAll(_lc.getLines(3, color));

		//This initializes a list of all the possible moves that conform to a particular move style; in this case SideStep
		_playableMoves = _mg.computeAllSideSteps(color);
		RandomPlayerShiftLine rpshift = new RandomPlayerShiftLine();

		//if there are no sidestep moves then try a shift move
		if (_playableMoves.isEmpty()) {
			return rpshift.takeTurn(layer, color);
		}
		return _playableMoves.get(LocalRandom.nextInt(_playableMoves.size())).makeMove(layer);
//		else {

			//randomly chooses the move to make
//			Random rand = new Random();
//			int random_Move = rand.nextInt(_playableMoves.size());
//			Line from = _playableMoves.get(random_Move).getFrom();
//			HexagonCardinalDirections direction = _playableMoves.get(random_Move).getDirection();

			//System.out.print("Sidestep: "+ from +"-->" + direction + "\n");

			//makes the chosen move
//			SideStep chosenStep = new SideStep(from, direction);
//
//			return 	chosenStep.makeMove(bg);
//		}
	}
}
