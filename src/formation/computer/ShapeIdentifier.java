package formation.computer;

import java.util.ArrayList;

import board.Layer;
import board.LightNode;
import board.Marble.MarbleColor;
import formation.shape.Hexagon;
import formation.shape.Line;
import formation.shape.Parallelogram;
import formation.shape.Triangle;
import graph.AbaloneGraph;
import graph.Node;
import utilities.ListUtilities;

public class ShapeIdentifier {

	private Layer _layer;
	private LineComputer _lineComputer;
	@SuppressWarnings("rawtypes")
	private ArrayList[] _parallelogramsBlack;
	@SuppressWarnings("rawtypes")
	private ArrayList[] _parallelogramsWhite;
	public ShapeIdentifier(Layer layer) {

		_layer = layer;
		_lineComputer = new LineComputer(layer);
		_parallelogramsBlack = new ArrayList[4];
		_parallelogramsWhite = new ArrayList[4];

		for (int i = 0; i <= 3; i++) {
			_parallelogramsBlack[i] = new ArrayList<Parallelogram>();
			_parallelogramsWhite[i] = new ArrayList<Parallelogram>();
		}
	}


	public boolean isHex (Node center) {
		//the center of a hexagon must have six neighbors so if the size isn't 6 it can't be a hexagon.
		Node vertex = AbaloneGraph.get().getVertex(center._col, center._row );
		if (vertex.getNeighbors().size() != 6 || _layer.contains(center._col, center._row)== MarbleColor.EMPTY) {
			return false;
		}

		//Makes sure the center is the same color as the first node in the neighbor list. layer.contains returns a color.
		if (_layer.contains(vertex.getNeighbors().get(0)._col, vertex.getNeighbors().get(0)._row) != 
			_layer.contains(center._col, center._row)) return false;

		//check each value in the Hash table to ensure that each node around the center has an equivalent piece
		for (int i = 0; i < vertex.getNeighbors().size(); i++) {
			if ((_layer.contains(vertex.getNeighbors().get(i)._col, vertex.getNeighbors().get(i)._row) != 
				_layer.contains(center._col, center._row)) ||
				(_layer.contains(vertex.getNeighbors().get(i)._col, vertex.getNeighbors().get(i)._row) == MarbleColor.EMPTY)) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Hexagon> getHexes(MarbleColor color) {
		ArrayList<Hexagon> hexList = new ArrayList<Hexagon>();
		for (LightNode lightweight: _layer.getNodes(color)) {
			if(isHex(AbaloneGraph.get().getVertex(lightweight._col, lightweight._row))) {
				Hexagon addHex = new Hexagon(AbaloneGraph.get().getVertex(lightweight._col, lightweight._row));
				ListUtilities.ListInsertInOrder(hexList, addHex);
			}
		}
		return hexList;
	}

	public ArrayList<ArrayList<Line>> getVs(MarbleColor color){
		//get lines of size three
		ArrayList<Line> potentialV = _lineComputer.getLines(3, color);
		ArrayList<ArrayList<Line>> Vs = new ArrayList<ArrayList<Line>>();
		//loop through the lines and find two with the same start point
		for (int i = 0; i < potentialV.size(); i++ ) {

			//gets the endpoints for each line
			Node lowEndPoint = AbaloneGraph.get().getVertex(potentialV.get(i).getLowerEndpoint());
			Node highEndPoint = AbaloneGraph.get().getVertex(potentialV.get(i).getUpperEndpoint());

			//initializes index to be i + 1 to compare a line with every line after it
			int index = i + 1;
			while (index < potentialV.size()) {
				// gets the endpoints of the line to which we will compare
				Node lowIndex = AbaloneGraph.get().getVertex(potentialV.get(index).getLowerEndpoint());
				Node highIndex = AbaloneGraph.get().getVertex(potentialV.get(index).getUpperEndpoint());
				ArrayList<Line> singularV = new ArrayList<Line>();

				//If any of the endpoints match and the middle point of both lines are neighbors, then add the line
				if ((lowEndPoint == lowIndex || lowEndPoint == highIndex || highEndPoint == lowIndex || highEndPoint == highIndex) &&
					(AbaloneGraph.get().getVertex(potentialV.get(i).get(1)).getNeighbors().contains(AbaloneGraph.get().getVertex(potentialV.get(index).get(1))))) {
					_lineComputer.addToList(singularV, potentialV.get(i));
					_lineComputer.addToList(singularV, potentialV.get(index));
					if (!(Vs.contains(singularV))){
						Vs.add(singularV);
					}
				}
				index++;
			}
		}
		return Vs;
	}

	public ArrayList<Triangle> getSmallTriangles(MarbleColor color){
		//get all lines of size two
		ArrayList<Line> potentialTri = _lineComputer.getLines(2, color);
		ArrayList<Triangle> smallTriangles = new ArrayList<Triangle>();
		//loop through the lines and find two with at least one similar point
		for (int i = 0; i < potentialTri.size(); i++) {
			//gets the end points for each line
			Node lowEndPoint = AbaloneGraph.get().getVertex(potentialTri.get(i).getLowerEndpoint());
			Node highEndPoint = AbaloneGraph.get().getVertex(potentialTri.get(i).getUpperEndpoint());

			//initializes index to be one greater than the line 
			int index = i+1;
			while (index < potentialTri.size()) {
				// gets the end points of the line to which we will compare
				Node lowIndex = AbaloneGraph.get().getVertex(potentialTri.get(index).getLowerEndpoint());
				Node highIndex = AbaloneGraph.get().getVertex(potentialTri.get(index).getUpperEndpoint());

				//There are four cases we need to check for: Any combo of the endpoints being equal
				//Case 1: The two low endpoints are equal
				if (lowEndPoint == lowIndex) {
					//We will feed TriangleCompareAndAdd the two endpoints not used to ensure they are neighbors
					//The third point is an arbitrary selection from the other two endpoints since they are equal
					triangleCompareAndAdd(highEndPoint, highIndex, lowEndPoint, potentialTri, smallTriangles);
				}
				//Case 2: The two high endpoints are equal
				if (highEndPoint == highIndex) {
					//We will feed TriangleCompareAndAdd the two endpoints not used to ensure they are neighbors
					//The third point is an arbitrary selection from the other two endpoints since they are equal
					triangleCompareAndAdd(lowEndPoint, lowIndex, highEndPoint,potentialTri, smallTriangles);
				}
				//Case 3: The low endpoint from the first triangle and the high endpoint from the second
				if (lowEndPoint == highIndex) {
					//We will feed TriangleCompareAndAdd the two endpoints not used to ensure they are neighbors
					//The third point is an arbitrary selection from the other two endpoints since they are equal
					triangleCompareAndAdd(highEndPoint, lowIndex, lowEndPoint,potentialTri, smallTriangles);
				}
				//Case 4: The high endpoint from the first triangle and the low endpoint from the second
				if (highEndPoint == lowIndex) {
					//We will feed TriangleCompareAndAdd the two endpoints not used to ensure they are neighbors
					//The third point is an arbitrary selection from the other two endpoints since they are equal
					triangleCompareAndAdd(lowEndPoint, highIndex, highEndPoint,potentialTri, smallTriangles);
				}
				index++;
			}

		}
		//Check to see if the non-common nodes are in each others neighbor list
		//If so add the three lines of two into an array list (check that the triangle doesn't already exist)
		return smallTriangles;
	}

	private void triangleCompareAndAdd(Node vertex1, Node vertex2, Node vertex3, ArrayList<Line> potentialTri, ArrayList<Triangle> smallTriangles) {

		//Since vertex3 is the common node between both lines, the other two vertices need to be
		//neighbors in order for the three points to classify as a triangle.
		ArrayList<Node> vertex2Neighbors = vertex2.getNeighbors();
		if (vertex2Neighbors.contains(vertex1)) {

			//adds all three vertices to a triangle and adds that triangle to a list of triangles.
			Triangle compareTri = new Triangle();
			compareTri.addToTriangle(vertex1);
			compareTri.addToTriangle(vertex2);
			compareTri.addToTriangle(vertex3);
			if (!(smallTriangles.contains(compareTri))) compareTri.addToList(smallTriangles);
		}
	}

	//	N = populated Node
	//	
	//     N N
	//    N N N
	//     /|\
	//      |
	//	   This node is neighbors with all other points in the trapizoid. 
	//     Since we already store it in a line of three, 
	//     we just need to check to see if the nodes in a line of 2 are neighbors of the specified node.
	public ArrayList<ArrayList<Line>> getTrapizoids(MarbleColor color) {

		//gets a list of all the lines of size 2 and size three for short and long sides.
		ArrayList<Line> shortSides = _lineComputer.getLines(2, color);
		ArrayList<Line> longSides = _lineComputer.getLines(3, color);
		ArrayList<ArrayList<Line>> allTraps = new ArrayList<ArrayList<Line>>();

		//loops through all lines of size 2
		for (Line side : shortSides) {

			//within the loop for lines of size 2, loops through all lines of size 3.
			for (Line bigSide : longSides) {

				//checks to see if the lines are parallel
				if(side.isParallel(bigSide)) {

					//gets all the neighbors for the center piece of the large line. 
					ArrayList<Node> centerNeighborList = bigSide.get(1).getNeighbors();

					//if both nodes in the small line are neighbors of the center then we have a trapizoid
					if(centerNeighborList.contains(side.get(0)) && centerNeighborList.contains(side.get(0))&&  
					  (!(bigSide.contains(side.get(0)) && bigSide.contains(side.get(1))))) {

						//adds lines to trapizoids then adds the trapizoid to the list of trapizoids.
						ArrayList<Line> singleTrap = new ArrayList<Line>();
						_lineComputer.addToList(singleTrap, side);
						_lineComputer.addToList(singleTrap, bigSide);
						allTraps.add(singleTrap);
							}
				}
			}
		}
		return allTraps;
	}
	//
	//Three types of parallelograms we are interested in:	
	//	
	// Type1: two lines of two        Type2: two lines of 3	     Type3: three lines of 3
	//	    N N                             N N N                     N N N
	//	     N N                             N N N                     N N N 
	//	                                                                N N N 
	//	
	@SuppressWarnings("unchecked")
	public ArrayList<Parallelogram> getParallelograms(int type, MarbleColor color) {

		// We will structure the ArrayList so that we get all the parallelograms of a certain type
		// according the the Type structure outlined above
		if(color == MarbleColor.BLACK) {
			if(_parallelogramsBlack[1].isEmpty())_parallelogramsBlack[1] = type1Para(color);
			if(_parallelogramsBlack[2].isEmpty())_parallelogramsBlack[2] = type2Para(color);
			if(_parallelogramsBlack[3].isEmpty())_parallelogramsBlack[3] = type3Para(color);
			return _parallelogramsBlack[type];
		}
		else {
			if(_parallelogramsWhite[1].isEmpty())_parallelogramsWhite[1] = type1Para(color);
			if(_parallelogramsWhite[2].isEmpty())_parallelogramsWhite[2] = type2Para(color);
			if(_parallelogramsWhite[3].isEmpty())_parallelogramsWhite[3] = type3Para(color);
			return _parallelogramsWhite[type];
		}
	}
	
	public ArrayList<Parallelogram> type1Para(MarbleColor color) {
		//gets all lines of size two and initializes a list of parallelograms.
		ArrayList<Line> potentialshort = _lineComputer.getLines(2, color);
		ArrayList<Parallelogram> paraList = new ArrayList<Parallelogram>();

		//loops through all lines of size two to find two adjacent parallel lines.
		for (int i = 0; i < potentialshort.size(); i++) {

			//the index is i +1 so we only loop through line pairings we haven't seen. 
			//This should prevent duplicates
			int index = i + 1;
			while (index < potentialshort.size()) {
				Parallelogram singlePara = new Parallelogram();
				boolean isEqual = false;

				//Checks to see that both lines are parallel to each other and then checks to see that the nodes are neighbors
				//Since we have ordered the nodes, if this is a parallelogram, the smaller nodes are touching and the larger nodes are touching.
				//The last two conditions check to make sure the two lines are not on the same line on the board.
				if (potentialshort.get(i).isParallel(potentialshort.get(index)) &&
				   (potentialshort.get(i).get(0).getNeighbors().contains(potentialshort.get(index).get(0))) &&
				   (potentialshort.get(i).get(1).getNeighbors().contains(potentialshort.get(index).get(1))) &&
				   (!(potentialshort.get(i).contains(potentialshort.get(index).get(0)))) &&
				   (!(potentialshort.get(i).contains(potentialshort.get(index).get(1))))){

					//create the parallelogram and add it to the list
					singlePara.addToParallelogram(potentialshort.get(i));
					singlePara.addToParallelogram(potentialshort.get(index));
					
					for(Parallelogram para : paraList) {
						if (singlePara.equals(para)) isEqual = true;
					}
					
					if (!(isEqual)) singlePara.addToList(paraList);	
				}
				index ++;
			}
		}
		return paraList;
	}

	public ArrayList<Parallelogram> type2Para(MarbleColor color){

		//gets all lines of size three and creates a new list of parallelograms.
		ArrayList<Line> potentialMedium = _lineComputer.getLines(3, color);
		ArrayList<Parallelogram> paraList = new ArrayList<Parallelogram>();

		//loops through all lines of size 3 to find two adjacent parallel lines.
		for (int i = 0; i< potentialMedium.size(); i++) {

			//See type1Para for reason behind this initialization and loop
			int index = i + 1;
			while (index < potentialMedium.size()) {
				Parallelogram singlePara = new Parallelogram();

				//gets all the neighbors for the middle node of one line of 3. 
				//if the two middle nodes aren't neighbors, then the two lines don't make a parallelogram
				ArrayList<Node> middleNeighbors = potentialMedium.get(i).get(1).getNeighbors();
				if (potentialMedium.get(i).isParallel(potentialMedium.get(index)) &&
				   (middleNeighbors.contains(potentialMedium.get(index).get(1)))) {
					singlePara.addToParallelogram(potentialMedium.get(i));
					singlePara.addToParallelogram(potentialMedium.get(index));
					singlePara.addToList(paraList);
				}
				index++;
			}
		}
		return paraList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Parallelogram> type3Para(MarbleColor color){
		ArrayList<Parallelogram> class2Para = new ArrayList<Parallelogram>();
		//gets all parallelograms of type 2 
		if (color == MarbleColor.BLACK) { 
			class2Para = _parallelogramsBlack[2];
		}
		else {
			class2Para = _parallelogramsWhite[2];
		}
		ArrayList <Parallelogram> paraList = new ArrayList<Parallelogram>();

		//loops through all parallelograms of type 2 in order to find overlap between any.
		for (int i = 0; i< class2Para.size(); i++) {

			//see type1Para for reason behind this initialization and loop
			int index = i + 1;
			while (index < class2Para.size()) {
				Parallelogram singlePara = new Parallelogram();
				boolean isEqual = false;

				//if the upper line in the first parallelogram is a common line
				if (class2Para.get(index).contains(class2Para.get(i).get(0))){
					singlePara.addToParallelogram(class2Para.get(i).get(0));
					singlePara.addToParallelogram(class2Para.get(index).get(0));
					singlePara.addToParallelogram(class2Para.get(i).get(1));
					
					for(Parallelogram para : paraList) {
						if (singlePara.equals(para)) isEqual = true;
					}
					
					if (!(isEqual)) {
						singlePara.addToList(paraList);	
					}
				}

				//if the lower line in the first parallelogram is a common line.
				if (class2Para.get(index).contains(class2Para.get(i).get(1))) {
					singlePara.addToParallelogram(class2Para.get(i).get(0));
					singlePara.addToParallelogram(class2Para.get(index).get(1));
					singlePara.addToParallelogram(class2Para.get(i).get(1));
					
					for(Parallelogram para : paraList) {
						if (singlePara.equals(para)) isEqual = true;
					}
					
					if (!(isEqual)) {
						singlePara.addToList(paraList);	
					}
				}
				index++;
			}
		}
		return paraList;
	}
	//Other shapes to program: larger versions of these shapes
}

