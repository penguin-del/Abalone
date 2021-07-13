package move.generator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import graph.AbaloneGraph;
import graph.Node;
import move.representation.LinePushLine;
import move.representation.LinePushNode;
import utilities.Equals;

class LinePushNodeGeneratorTest {

	@Test
	void Test_Compute_Line_Push_Node_Horizontal()  { //Move
		Layer layer = new Layer();
		Line line= new Line();
		Line line2 = new Line();
		Line line3= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);


		Node node= AbaloneGraph.get().getVertex('C',4);
		layer.addBlack('C', 4);
		line.addToLine(node);
		line2.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',4);
		layer.addBlack('D', 4);
		line.addToLine(node);
		line2.addToLine(node);
		line3.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);
		line3.addToLine(node);

		ArrayList<LinePushNode> linePushTest = new ArrayList<LinePushNode>();


		Node pushNode= AbaloneGraph.get().getVertex('B',4);
		Node pushNode2= AbaloneGraph.get().getVertex('F',4);

		Node destination= AbaloneGraph.get().getVertex('A',4);
		Node destination2= AbaloneGraph.get().getVertex('G',4);

		layer.remove('A', 4);
		layer.remove('G', 4);

		layer.addWhite('B', 4);
		layer.addWhite('F', 4);


		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.BLACK);
		ArrayList<LinePushNode> pushWhiteList= pmg.computeAllLinePushNodes(MarbleColor.WHITE);
		

		LinePushNode lpn= new LinePushNode(line, pushNode, destination);
		linePushTest.add(lpn);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);
		linePushTest.add(lpn2);

		lpn= new LinePushNode(line2, pushNode, destination);
		linePushTest.add(lpn);

		lpn = new LinePushNode(line3, pushNode2, destination2);
		linePushTest.add(lpn);

		assertTrue(pushWhiteList.isEmpty());
		assertTrue(Equals.subsetEq(pushList, linePushTest));
	}
	
	@Test
	void Test_Compute_Line_Push_Node_DefaultBoard() {
		Layer layer = Layer.getDefaultBoard();
		LinePushNodeMoveGenerator pmg = new LinePushNodeMoveGenerator(layer);
		
		ArrayList<LinePushNode> lpn = new ArrayList<LinePushNode>();
		ArrayList<LinePushNode> blackPushNode = pmg.computeAllLinePushNodes(MarbleColor.BLACK);
		
		ArrayList<LinePushNode> whitePushNode = pmg.computeAllLinePushNodes(MarbleColor.WHITE);
		
		assertTrue(Equals.subsetEq(blackPushNode, lpn));
		assertTrue(Equals.subsetEq(whitePushNode, lpn));
	}
	
	@Test
	void Test_Compute_Line_Push_Line_DefaultBoard() {
		Layer layer = Layer.getDefaultBoard();
		LinePushLineMoveGenerator lplmg = new LinePushLineMoveGenerator(layer);
		
		
		ArrayList<LinePushLine> lpn = new ArrayList<LinePushLine>();
		ArrayList<LinePushLine> blackPushNode = lplmg.computeAllLinePushLine(MarbleColor.BLACK);
		ArrayList<LinePushLine> whitePushNode = lplmg.computeAllLinePushLine(MarbleColor.WHITE);
		
		assertTrue(Equals.subsetEq(blackPushNode, lpn));
		assertTrue(Equals.subsetEq(whitePushNode, lpn));
		
	}
	@Test
	void Test_Compute_Line_Push_Line_Itself() {
		Layer layer = new Layer();
		LinePushLineMoveGenerator pmg = new LinePushLineMoveGenerator(layer);
		
		
		layer.addWhite('E', 5);
		layer.addWhite('D', 5);
		layer.addWhite('F', 5);
		layer.addWhite('E', 4);
		layer.addWhite('E', 6);
		layer.addWhite('E', 3);
		layer.addWhite('E', 7);
		layer.addWhite('G', 5);
		layer.addWhite('C', 5);
		
		ArrayList<LinePushLine> lpn = new ArrayList<LinePushLine>();
		ArrayList<LinePushLine> whitePushNodeTest= pmg.computeAllLinePushLine(MarbleColor.BLACK);
		assertTrue(Equals.subsetEq(lpn, whitePushNodeTest));
	}
	
	
	@Test
	void Test_Compute_Line_Push_Node_Itself() {
		Layer layer = new Layer();
		LinePushNodeMoveGenerator pmg = new LinePushNodeMoveGenerator(layer);
		
		
		layer.addWhite('E', 5);
		layer.addWhite('D', 5);
		layer.addWhite('F', 5);
		layer.addWhite('E', 4);
		layer.addWhite('E', 6);
		
		
		ArrayList<LinePushNode> lpn = new ArrayList<LinePushNode>();
		ArrayList<LinePushNode> whitePushNodeTest= pmg.computeAllLinePushNodes(MarbleColor.BLACK);
		assertTrue(Equals.subsetEq(lpn, whitePushNodeTest));
	}
	

	@Test
	void Test_Compute_Line_Push_Node_RightToLeft()  { //Move
		Layer layer = new Layer();
		Line line= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);


		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addWhite('D', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',4);
		layer.addWhite('D', 4);
		line.addToLine(node);



		ArrayList<LinePushNode> linePushTest = new ArrayList<LinePushNode>();

		Node destination= AbaloneGraph.get().getVertex('D',1);
		Node destination2= AbaloneGraph.get().getVertex('D',6);

		layer.remove('D', 1);
		layer.remove('D', 6);


		Node pushNode= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		LinePushNode lpn= new LinePushNode(line, pushNode,destination);
		linePushTest.add(lpn);

		Node pushNode2= AbaloneGraph.get().getVertex('D',5);
		layer.addBlack('D', 5);
		lpn= new LinePushNode(line, pushNode2,destination2);
		linePushTest.add(lpn);


		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.WHITE);
		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}

	@Test
	void Test_Compute_Line_Push_Node_LeftToRight()  { //Move
		Layer layer = new Layer();
		Line line= new Line();
		Line line2= new Line();
		Line line3= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('C',4);
		layer.addWhite('C', 4);
		line.addToLine(node);
		line2.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',5);
		layer.addWhite('D', 5);
		line.addToLine(node);
		line2.addToLine(node);
		line3.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',6);
		layer.addWhite('E', 6);
		line.addToLine(node);
		line3.addToLine(node);

		ArrayList<LinePushNode> linePushTest= new ArrayList<LinePushNode>();

		Node destination= AbaloneGraph.get().getVertex('A',2);
		Node destination2= AbaloneGraph.get().getVertex('G',8);

		Node pushNode= AbaloneGraph.get().getVertex('B',3);
		layer.addBlack('B', 3);
		LinePushNode lpn= new LinePushNode(line, pushNode,destination);
		linePushTest.add(lpn);

		Node pushNode2= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);
		linePushTest.add(lpn2);


		lpn= new LinePushNode(line2, pushNode, destination);
		linePushTest.add(lpn);

		lpn = new LinePushNode(line3, pushNode2, destination2);
		linePushTest.add(lpn);

		layer.remove('A', 2);
		layer.remove('G', 8);

		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.WHITE);

		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}

	@Test
	void Test_Compute_Line_Push_Node_Horizontal_Off_Board()  { //Move
		Layer layer = new Layer();
		Line line= new Line();
		Line line2= new Line();
		Line line3 = new Line();
		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);


		Node node= AbaloneGraph.get().getVertex('B',1);
		layer.addBlack('B', 1);
		line.addToLine(node);
		line2.addToLine(node);

		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line.addToLine(node);
		line2.addToLine(node);
		line3.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',1);
		layer.addBlack('D', 1);
		line.addToLine(node);
		line3.addToLine(node);


		ArrayList<LinePushNode> linePushTest = new ArrayList<LinePushNode>();


		Node pushNode= AbaloneGraph.get().getVertex('A',1);
		Node pushNode2= AbaloneGraph.get().getVertex('E',1);

		layer.addWhite('A', 1);
		layer.addWhite('E', 1);

		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.BLACK);


		LinePushNode lpn= new LinePushNode(line, pushNode,null);
		linePushTest.add(lpn);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,null);
		linePushTest.add(lpn2);

		lpn = new LinePushNode(line2, pushNode, null);
		linePushTest.add(lpn);

		lpn = new LinePushNode(line3, pushNode2, null);
		linePushTest.add(lpn);

		assertTrue(Equals.subsetEq(pushList, linePushTest));


	}

}
