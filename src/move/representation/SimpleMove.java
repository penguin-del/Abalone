package move.representation;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Formation;
import graph.Node;

public class SimpleMove extends Move
{
	//Node that you are moving
	protected Node _from;

	//Node that you are moving to
	protected Node _to;

	//SimpleMove is a Node that you are moving(from), and where you are moving it(to)
	public SimpleMove(Node from, Node to)
    {
		_from=from;
		_to=to;
	}

	//
	// CTA: DEPRECATED technique
	//
	//makes the actual move on the board by changing "to" color to "from" color
	//public for testing
	//returns false because move is not going off board
//	public boolean makeMove(BoardGame bg)
//    {
//		AbaloneGraph graph = AbaloneGraph.get();
//
//		//gets the value of the original (from) Node
//		MarbleColor fromColor = graph.getValue(_from);
//
//		//changes value of node you are moving to to the color of original node
//		graph.changeValue(_to, fromColor);
//
//		//changing value of now empty node to empty
//		graph.changeValue(_from, MarbleColor.EMPTY);
//		return false;
//	}

	/*
	 *  Making a move means first copying the current layer -> newLayer.
	 *    For simple move:
	 *      (1) delete _from
	 *      (2) add the Marble in Node _from into the Node represented by _to
	 *  @param current -- an existing layer of marbles
	 *  @return a copy of a layer with move made.
	 */
	public Layer makeMove(Layer current)
	{
		Layer newLayer = null;

		try { newLayer = (Layer)current.clone(); }
		catch(CloneNotSupportedException cne)
		{
			System.err.println("Cloning a Layer failed: " + current);
			System.exit(1);
		}

		MarbleColor color = newLayer.remove(_from._col, _from._row);

		switch(color)
		{
			case WHITE:
				newLayer.addWhite(_to._col, _to._row);
				break;

			case BLACK:
				newLayer.addBlack(_to._col, _to._row);
				break;

			case EMPTY:
				System.err.println("Failed to remove " + _from + " in " + this);
				break;

			case INVALID:
				System.err.println("Unexpected INVALID move " + _from + " in " + this);
		}

		return newLayer;
	}

	@Override
	//ASSUMES SIMPLEMOVE HAS BEEN CALCULATED, DOESN'T CHECK LEGITIMACY OF SIMPLEMOVE
	public boolean moveApplies(Formation formation)
    {
		for (Node n : formation.decompose())
        {
			if (_from.equals(n)) return true;
		}
		return false;
	}

	//Equals method to check equivalent SimpleMove, used for testings
	@Override
	public boolean equals (Object obj)
    {
		if(!(obj instanceof SimpleMove)) return false;

		return smEquals((SimpleMove) obj);
	}

	public boolean smEquals(SimpleMove that) {

		if(!this._from.equals(that._from)) return false;

		if(!this._to.equals(that._to)) return false;

		return true;
	}

//	public Node getFrom() {
//		return _from;
//	}
//
//	public Node getTo() {
//		return _to;
//	}

	@Override
	public String toString() {
		return _from.toSimpleString() + ", "+ _to.toSimpleString();
	}
}
