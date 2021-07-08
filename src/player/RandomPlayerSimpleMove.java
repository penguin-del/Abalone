package player;

import java.util.ArrayList;

import move.generator.NonPushMoveGenerator;
import move.representation.SimpleMove;
import utilities.LocalRandom;
import board.Layer;
import board.Marble.MarbleColor;

public class RandomPlayerSimpleMove extends AbstractPlayer
{	
	protected ArrayList<SimpleMove> _playableMoves;
	protected NonPushMoveGenerator _mg;
	

	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) {
		
		//initializes new move generator based on current board state
		_mg = new NonPushMoveGenerator(layer);
		
		
		//computes all the simple moves, i.e. moves where only one node moves 
		_playableMoves = _mg.computeAllSimple(color);
		
		if (_playableMoves.isEmpty()) {
			System.out.print(_playableMoves);
		}
		return _playableMoves.get(LocalRandom.nextInt(_playableMoves.size())).makeMove(layer);
		//randomly chooses an index to play  
		//Random rand = new Random();
		//int random_Move = rand.nextInt(_playableMoves.size());
		//Node from = _playableMoves.get(random_Move).getFrom();
		//Node to = _playableMoves.get(random_Move).getTo();
		
		//System.out.print("Simple: "+ from +"-->" + to + "\n");

		//makes a move
		//SimpleMove chosenMove = new SimpleMove(from, to);

		//return chosenMove.makeMove(bg);
	}

}
