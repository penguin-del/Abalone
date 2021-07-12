package test.move;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import graph.Node;
import move.generator.NonPushMoveGenerator;
import move.representation.SimpleMove;
import utilities.Equals;

class SimpleMoveGeneratorTest {

	@Test
	void Test_Create_Singlenode()  {
		Layer layer = Layer.getDefaultBoard();
		assertEquals(layer.contains('I', 9),MarbleColor.WHITE);
	}

	@Test
	void Test_Possible_Simple_Moves()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);

		layer.addBlack('A', 1);

		//adds simplemoves to testList
		ArrayList<SimpleMove> testList = new ArrayList<SimpleMove>();
		SimpleMove simple= new SimpleMove(AbaloneGraph.get().getVertex('A',1), AbaloneGraph.get().getVertex('A',2));
		testList.add(simple);
		simple= new SimpleMove(AbaloneGraph.get().getVertex('A',1), AbaloneGraph.get().getVertex('B',1));
		testList.add(simple);
		simple= new SimpleMove(AbaloneGraph.get().getVertex('A',1), AbaloneGraph.get().getVertex('B',2));
		testList.add(simple);

		ArrayList<SimpleMove> moveList= mg.computeAllSimple(MarbleColor.BLACK);

		assertTrue(Equals.subsetEq(testList, moveList));
		assertTrue(moveList.contains(simple));

	}

	@Test
	void Test_AllPossible_Simple_Moves()  {

		Layer layer = Layer.getDefaultBoard();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		ArrayList<SimpleMove> blackList = new ArrayList<SimpleMove>();
		ArrayList<SimpleMove> whiteList = new ArrayList<SimpleMove>();

		Node node= AbaloneGraph.get().getVertex('A',2);
		Node node2= AbaloneGraph.get().getVertex('A',3);
		SimpleMove simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('A',2);

		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('B',2);

		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);

		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);

		node2= AbaloneGraph.get().getVertex('C',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);

		node2= AbaloneGraph.get().getVertex('D',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('D',3);

		node2= AbaloneGraph.get().getVertex('D',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('D',3);

		node2= AbaloneGraph.get().getVertex('E',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);

		node2= AbaloneGraph.get().getVertex('E',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);

		node2= AbaloneGraph.get().getVertex('F',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);

		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',2);

		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('F',2);

		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('F',2);

		node2= AbaloneGraph.get().getVertex('G',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);


		node= AbaloneGraph.get().getVertex('D',8);

		node2= AbaloneGraph.get().getVertex('C',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('D',8);

		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',8);

		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);

		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);

		node2= AbaloneGraph.get().getVertex('D',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);

		node2= AbaloneGraph.get().getVertex('E',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('F',7);

		node2= AbaloneGraph.get().getVertex('E',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('F',7);

		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);

		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);

		node2= AbaloneGraph.get().getVertex('G',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);

		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('H',8);

		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('I',8);

		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('I',8);

		node2= AbaloneGraph.get().getVertex('I',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);


		assertTrue(whiteList.size()==14);
		assertTrue(blackList.size()==14);



		ArrayList<SimpleMove> whiteMoveList = mg.computeAllSimple(MarbleColor.WHITE);

		ArrayList<SimpleMove> blackMoveList = mg.computeAllSimple(MarbleColor.BLACK);



		assertTrue(Equals.subsetEq(whiteMoveList, whiteList));
		assertTrue(Equals.subsetEq(blackMoveList, blackList));

	}

}
