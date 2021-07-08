package formation.shape;

import java.util.ArrayList;

import graph.Node;
import utilities.ListUtilities;

// Line1 --> N   N <---- Line2
//            N N
//             N  <--- This node is shared between the two pictured lines
//
//  These two lines in the V will always have a shared vertex node 
//
public class V extends GeometricShape implements Comparable<V>
{
	protected ArrayList<Line> _lines;

	public V(ArrayList<Line> lines) { _lines = lines; }

	public V() { this(new ArrayList<Line>()); }

	public void addToV(Line newLine)
	{
		ListUtilities.ListInsertInOrder(_lines, newLine);
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof V)) return false;

		return VEquals((V) o);
	}

	private boolean VEquals(V that)
	{
		return (this._lines.get(0).equals(that._lines.get(0))) && 
			   (this._lines.get(1).equals(that._lines.get(1)));
	}

	@Override
	public int compareTo(V that)
	{
		return ListUtilities.ListCompareTo(_lines, that._lines);
	}

	//	public Line get(int index) {
	//		return _lines.get(index);
	//	}

//	public int size() {
//		return _lines.size();
//	}

	@Override
	public ArrayList<Node> decompose()
	{
		ArrayList<Node> decomposed = new ArrayList<Node>();

		for(Line line : _lines)
		{
			for (Node n : line.decompose())
			{
				// Avoid duplicates in the decomposition
				if (!decomposed.contains(n)) decomposed.add(n);
			}
		}

		return decomposed;
	}

	public String toString()
	{
		return "[" + ListUtilities.toStringCommaSeparated(_lines) + "]";
	}
}
