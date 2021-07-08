package formation.shape;

import java.util.ArrayList;

import graph.Node;
import utilities.ListUtilities;

public class Hexagon extends GeometricShape implements Comparable<Hexagon>
{	
	protected ArrayList<Node> _nodes;
	private final int _HEX_CENTER_INDEX = 3;
	
	public Hexagon(Node center) {
		_nodes = new ArrayList<Node>();
		ArrayList<Node> neighborList = center.getNeighbors();
		for (Node neighbor: neighborList) {
			ListUtilities.ListInsertInOrder(_nodes, neighbor);
		}
		ListUtilities.ListInsertInOrder(_nodes, center);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Hexagon)) return false;

		return HexagonEquals((Hexagon) o);
	}

	public boolean HexagonEquals(Hexagon that)
	{
		return this._nodes.get(_HEX_CENTER_INDEX).equals(that._nodes.get(_HEX_CENTER_INDEX));
	}

	@Override
	public int compareTo(Hexagon that) {
		
		return this._nodes.get(3).compareTo(that._nodes.get(3));
	}

	public Node get(int i) {
		return _nodes.get(i);
	}

	public int size() {
		return _nodes.size();
	}
	
	@Override
	public ArrayList<Node> decompose() {
		return _nodes;
	}
	
	public String toString() {
		String hexString = "";
		for (int i = 0; i < this.size(); i++) {
			hexString += this.get(i).toString() + ", ";
		}
		
		hexString = hexString.substring(0, hexString.length() - 2);
		hexString += "NEXT";
		return hexString;
	}

	
}
