package MCTS;

import board.Marble.MarbleColor;
import formation.computer.ShapeIdentifier;
import graph.AbaloneGraph;
import graph.Node;
import subgraph.BFS;

import java.util.ArrayList;

import board.Layer;

public class BoardStateAssessment {

	protected int _boardScore;
	protected MarbleColor _color;
	protected Layer _layer;
	protected ShapeIdentifier _shapeID;
	protected BFS _blobs;

	public BoardStateAssessment(Layer layer, MarbleColor color) {
		_boardScore = 0;
		_layer = layer;
		_color = color;
		_shapeID = new ShapeIdentifier(layer);
		_blobs = new BFS(layer, color);
	}

	public double determineScore() {
		_boardScore += calculateBoardOwnership(_color);
		_boardScore -= calculateBoardOwnership(_color.flipColor());
		_boardScore += blobCalculation(_color);
		_boardScore -= blobCalculation(_color.flipColor());
		if(_blobs.BFSOnMarbleFormations(_color).size() == 1) {
			_boardScore += cohesionScoreCalculator(_color);
		}
		
		//Max score would be 3 and 10/14. Represented as a mixed number that's (26/7)
		return _boardScore / (26 / 7);
		//	_boardScore += ShapeScore();
	}

	//Consider adjacent blobs, compactness, ownership of parts of the board

	//     1 1 0 2 2 
	//    1 1 1 2 2 2
	//   0 1 1 0 2 2 0
	//  3 3 0 4 4 0 5 5
	// 3 3 3 4 4 4 5 5 5   This map shows the different sections we are assigning to the board (1 - 7)
	//  3 3 0 4 4 0 5 5    There are some zeros which aren't part of any section. 
	//   0 6 6 0 7 7 0     The hope is to figure out who has control of which section and whether that 
	//    6 6 6 7 7 7      provides a strategic advantage. Each area has a 'center' which we can use
	//     6 6 0 7 7
	//
	//

	private int calculateBoardOwnership(MarbleColor color) {
		//runs through each section of the board and rewards a score based on ownership
		int ownerScore = 0;
		if (doesOwn(AbaloneGraph.get().getVertex('B', 2), color)) ownerScore += 1;
		if (doesOwn(AbaloneGraph.get().getVertex('E', 2), color)) ownerScore += 1;
		if (doesOwn(AbaloneGraph.get().getVertex('B', 5), color)) ownerScore += 1;
		//We add more importance to owning the center section since it's strategically more adventageous.
		if (doesOwn(AbaloneGraph.get().getVertex('E', 5), color)) ownerScore += 2;
		if (doesOwn(AbaloneGraph.get().getVertex('H', 5), color)) ownerScore += 1;
		if (doesOwn(AbaloneGraph.get().getVertex('E', 8), color)) ownerScore += 1;
		if (doesOwn(AbaloneGraph.get().getVertex('H', 8), color)) ownerScore += 1;

		return ownerScore / 8;
	}

	private boolean doesOwn(Node node, MarbleColor color) {
		//Determines if color is in control of a certain area defined by its center node
		if (_shapeID.isHex(node)) return true;
		if (determineDominance(node) == color) return true;

		return false;		
	}

	private MarbleColor determineDominance(Node node) {
		//Since the centers of the zones are predetermined, we don't need to check for invalidity.
		if (_layer.contains(node._col, node._row) == MarbleColor.EMPTY) {
			int black = 0;
			int white = 0;

			//If the center doesn't have a populated node then we will look around at the other nodes
			for (Node neighbor: node.getNeighbors()) {
				if (_layer.contains(neighbor._col, neighbor._row) == MarbleColor.WHITE) white++;
				if (_layer.contains(neighbor._col, neighbor._row) == MarbleColor.BLACK) black++;
			}

			//if one color has a stronger presence then they have dominance in a section
			if (white > black) return MarbleColor.WHITE;
			if (black > white) return MarbleColor.BLACK;
			if (black == white) return MarbleColor.EMPTY;
		}

		//Controlling the center node in a section means dominance.
		return _layer.contains(node._col, node._row);
	}

	private int blobCalculation(MarbleColor color) {
		int blobScore = 0;
		ArrayList<ArrayList<Node>> blob = _blobs.BFSOnMarbleFormations(color);
		//		ArrayList<ArrayList<Node>> opponentBlob = _blobs.BFSOnMarbleFormations(_color.flipColor());
		//
		//		if (blob.size() < opponentBlob.size()) {
		//			blobScore += 5;
		//			if (isInterceeding(blob, opponentBlob)) blobScore += 10;		
		//		}
		//
		//		if (blob.size() == 1) blobScore += cohesionScoreCalculator(blob.get(0));
		if (blob.size() == 1) return 1;
		if (blob.size() == 2) return 0;
		if (blob.size() == 3) return 0;
		if (blob.size() == 4) return -1;

		return blobScore;
	}

	private boolean isInterceeding(ArrayList<ArrayList<Node>> blob, ArrayList<ArrayList<Node>> opponentBlob) {

		for (ArrayList<Node> formation: blob) {
			for (int i = 0; i < opponentBlob.size(); i++) {
				//set index because we need to check agianst two blobs of the opposite color.
				int index = i+1;
				while(index < opponentBlob.size()) {
					//if a formation is adjacent to two other formations of the opposite color, it is interceeding.
					if ((areAdjacent(formation, opponentBlob.get(i))) &&
							(areAdjacent(formation, opponentBlob.get(index)))){
						return true;
					}
					index++;
				}	
			}
		}
		return false;
	}

	private boolean areAdjacent(ArrayList<Node> formation, ArrayList<Node> oppForm) {

		for(Node node: formation) {
			for (Node oppNode: oppForm) {

				//if nodes in different formations are neighbors then they are adjacent.
				if (node.getNeighbors().contains(oppNode)) return true;
			}
		}
		return false;
	}

	private double cohesionScoreCalculator(MarbleColor color) {

		ArrayList<Node> form = _blobs.BFSOnMarbleFormations(color).get(0);
		double distance = _blobs.BFSDiameter(form);
	//	double distance = calculateDistance(form.get(0), form.get(form.size()-1));
		return 1 - (distance/14);
		

	}

	//Since we don't have right triangles, we will need to use Law of cosines to get our distance
	public double calculateDistance(Node smallest, Node endpoint) {
		// If the two points are on the same line then the distance simple subtraction
		if ((int)endpoint._col == (int) smallest._col) return endpoint._row - smallest._row;
		if (endpoint._row == smallest._row) return (int)endpoint._col - (int) smallest._col;

		//Otherwise we need to use the law of cosines
		if ((int)endpoint._col > (int) smallest._col) {
			int a = (int)endpoint._col - (int) smallest._col;
			int b = endpoint._row - smallest._row;
			// c^2 = a^2 + b^2 - 2abcos(C) --- C will always be 60 in this case
			return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - 2*a*b*(float)Math.cos(Math.PI/3));
		}
		else {
			int a = (int)smallest._col - (int) endpoint._col;
			int b = endpoint._row - smallest._row;
			// c^2 = a^2 + b^2 - 2abcos(C) --- C will always be 120 in this case
			return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - 2*a*b*(float)Math.cos(2*Math.PI / 3));
		}
	}

	//	private int ShapeScore() {
	//		int ShapeScore = 0;
	//		ShapeScore += _shapeID.getSmallTriangles(_color).size();
	//		ShapeScore += _shapeID.getVs(_color).size();
	//		ShapeScore += _shapeID.getParallelograms(1, _color).size();
	//		ShapeScore += _shapeID.getParallelograms(2, _color).size();
	//		ShapeScore += _shapeID.getParallelograms(3, _color).size();
	//		ShapeScore += _shapeID.getTrapizoids(_color).size();
	//		
	//		return ShapeScore;
	//	}

}
