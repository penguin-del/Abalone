package move.representation;

import formation.shape.Line;
import graph.AbaloneGraph;
import graph.Node;

public abstract class Push extends LineMove
{

	//Node is not a vertex because it may not be on board
	protected Node _destination;

	public Push(Line line, Node destination)
	{
		super(line);
		_destination = destination;
	}

	public Push() { _destination = null; }

	protected boolean isValidDestination()
	{
		return AbaloneGraph.get().hasVertex(_destination);
	
	}
}
