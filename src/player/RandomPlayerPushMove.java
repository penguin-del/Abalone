package player;

import java.util.ArrayList;
import board.Layer;
import board.Marble.MarbleColor;
import move.generator.LinePushLineMoveGenerator;
import move.generator.LinePushNodeMoveGenerator;
import move.generator.NonPushMoveGenerator;
import move.representation.Move;
import utilities.LocalRandom;

public class RandomPlayerPushMove extends AbstractPlayer
{
	
	protected ArrayList<Move> _playablePushMoves;
	protected NonPushMoveGenerator _mg;
	protected LinePushNodeMoveGenerator _LpushN;
	protected LinePushLineMoveGenerator _LpushL;
	
	@Override
	public Layer takeTurn(Layer layer, MarbleColor color) 
	{
		
		//each move will take in a new board state and calculate all possible moves from it as well as all possible lines that can move
		_mg = new NonPushMoveGenerator(layer);
		_LpushN = new LinePushNodeMoveGenerator(layer);
		_LpushL = new LinePushLineMoveGenerator(layer);
		_playablePushMoves = new ArrayList<Move>();
		
		//initializes these constructors to contain all possible push moves
		_playablePushMoves.addAll(_LpushL.computeAllLinePushLine(color));
		_playablePushMoves.addAll(_LpushN.computeAllLinePushNodes(color)); 
		RandomPlayerShiftLine rpshift = new RandomPlayerShiftLine();
		
		//If push moves are avaliable, do them. Otherwise try shifting.
		if (!(_playablePushMoves.isEmpty())) return _playablePushMoves.get(LocalRandom.nextInt(_playablePushMoves.size())).makeMoveOnCopyBoard(layer);
		return rpshift.takeTurn(layer, color);
	}
	
//	private boolean randomlyChoseNodePush(BoardGame bg) {
//		Random rand = new Random();
//		int random_Move = rand.nextInt(_playableNodePushMoves.size());
//		Line pusher = _playableNodePushMoves.get(random_Move).getLine();
//		Node pushed = _playableNodePushMoves.get(random_Move).getPushed();
//		Node destination = _playableNodePushMoves.get(random_Move).getDestination();
//		
//		//System.out.print("Push: "+pusher+" pushed "+pushed+ " to"+ destination + "\n");
//		
//		LinePushNode chosenPush = new LinePushNode(pusher, pushed, destination);
//
//		
//		return chosenPush.makeMove(bg);
//		
//	}
//
//	public boolean randomlyChoseLinePush(BoardGame bg) {
//		Random rand = new Random();
//		int random_Move = rand.nextInt(_playableLinePushMoves.size());
//		Line pusher = _playableLinePushMoves.get(random_Move).getLine();
//		Line pushed = _playableLinePushMoves.get(random_Move).getPushed();
//		Node destination = _playableLinePushMoves.get(random_Move).getDestination();
//
//		//System.out.print("Push: "+pusher+" pushed "+pushed+ " to"+ destination + "\n");
//		
//		LinePushLine chosenPush = new LinePushLine(pusher, pushed, destination);
//
//		return chosenPush.makeMove(bg);
//	}
//		

}
