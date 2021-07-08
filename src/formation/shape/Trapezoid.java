package formation.shape;

import java.util.ArrayList;

import graph.Node;

//    N N
//   N N N 
//
// This is the Trapezoid. One line of two and one line of three
//
public class Trapezoid extends GeometricShape implements Comparable <Trapezoid>{

	protected ArrayList<Line> _lines;

	public Trapezoid(ArrayList<Line> trapezoid) {
		super();
		_lines = trapezoid;
	}

	public Trapezoid() {
		this(new ArrayList<Line>());
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Trapezoid)) {
			return false;
		}
		Trapezoid that = (Trapezoid) o;
		return TrapezoidEquals(that);
	}
	//method for equality
	public boolean TrapezoidEquals(Trapezoid that) { //Rewrite to be index based

		if (this._lines.size() != that._lines.size()) return false;
		for (int i = 0; i < _lines.size(); i++) {
			if (!(this._lines.get(i).equals(that._lines.get(i)))) {
				return false;
			}
		}
		return true;
	}

	//You must feed add to trapezoid two or three different sized lines. 
	//This adds to the trapezoid so that the line sizes are ordered least to greatest
	public void addToTrapezoid(Line newline) {

		for (int i =  0; i < this._lines.size(); i++) {
			if (newline.size() < this._lines.get(i).size()) {
				this._lines.add(i, newline);
				return;
			}
		}

		this._lines.add(newline);

	}

	@Override
	public int compareTo(Trapezoid that)
	{
		//initializes index to be zero
		int index = 0;

		while (index < _lines.size()) {

			// adds onto the index if the current line in both parallelograms are the same.
			if (_lines.get(index).compareTo(that._lines.get(index)) == 0) index++;

			//compares two lines at the current index.
			else if (_lines.get(index).compareTo(that._lines.get(index)) == -1) return -1;

			else return 1;
		}

		// two parallelograms are exactly equal.
		return 0;
	}

	/**
	 * 	For every line making up this trapezoid, decompose the Line then return the nodes
	 */
	@Override
	public ArrayList<Node> decompose()
	{
		ArrayList<Node> decomposed = new ArrayList<Node>();

		for(Line line : _lines)
		{
			decomposed.addAll(line.decompose());
		}

		return decomposed;
	}

	public String toString()
	{
		String trapString = "";

		for (Line line : _lines)
		{
			trapString += line.toString() + ", ";
		}

		return trapString.substring(0, trapString.length() - 2);
	}
}
