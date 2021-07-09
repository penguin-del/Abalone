package test.graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import graph.Node;

class AbaloneGraphTest {


// NO LONGER VALID TESTS
//	@Test
//	public void Test_Simple_String()  {	
//
//		BufferedReader br = new BufferedReader(new StringReader("(A 1):(B 1)\n(A 2):(B 3)"));
//		AbaloneGraph graph = AbaloneGraphReader.parse(new AbaloneGraph(), );
//		assertEquals(4, graph.numVerticies());
//		assertTrue(graph.hasVertex('A', 1));
//		assertTrue(graph.hasVertex('B', 1));
//		assertTrue(graph.hasVertex('A', 2));
//		assertTrue(graph.hasVertex('B', 3));
//		assertFalse(graph.hasVertex('C', 5));
//		assertFalse(graph.hasVertex('B', 7));
//
//	}
//
//	@Test
//	public void Test_Simple_String2()  {
//
//		BufferedReader br = new BufferedReader(new StringReader("(A 8):(B 8) (A 7)\n(B 8):(C 8) (A 8)"));
//		AbaloneGraph graph = AbaloneGraphReader.parse(br);
//		assertTrue(graph.hasVertex('A', 8));
//		assertFalse(graph.hasVertex('B', 7));
//		assertTrue(graph.hasVertex('B', 8));
//		assertFalse(graph.hasVertex('A', 4));
//		assertTrue(graph.hasVertex('C', 8));
//		assertEquals(4, graph.numVerticies());
//
//	}
//
//	@Test
//	public void Test_Simple_String3()  {
//		BufferedReader br = new BufferedReader(new StringReader("(A 8):(A 7)"));
//		AbaloneGraph graph = AbaloneGraphReader.parse(br);
//		assertTrue(graph.hasVertex('A', 8));
//		assertFalse(graph.hasVertex('A', 4));
//		assertFalse(graph.hasVertex('B', 8));
//		assertEquals(2, graph.numVerticies());
//	}
//
//	@Test
//	public void Test_Simple_String4()  {
//		BufferedReader br = new BufferedReader(new StringReader("(A 8):(B 7)\n(B 7):(A 8)"));
//		AbaloneGraph graph = AbaloneGraphReader.parse(br);
//		assertTrue(graph.hasVertex('A', 8));
//		assertTrue(graph.hasVertex('B', 7));
//		assertFalse(graph.hasVertex('A', 7));
//		assertFalse(graph.hasVertex('B', 8));
//		assertEquals(2, graph.numVerticies());
//	}

	@Test
	public void Test_Standard_Graph()  {
		//assertEquals(61, graph.numVerticies());
		assertTrue(AbaloneGraph.get().hasVertex('I',9));
		assertTrue(AbaloneGraph.get().hasVertex('A',1));
		assertTrue(AbaloneGraph.get().hasVertex('E',1));
		assertTrue(AbaloneGraph.get().hasVertex('A',5));
		assertTrue(AbaloneGraph.get().hasVertex('I',5));
		assertTrue(AbaloneGraph.get().hasVertex('E',9));
		assertTrue(AbaloneGraph.get().hasVertex('E',5));

		assertFalse(AbaloneGraph.get().hasVertex('A',6));
		assertFalse(AbaloneGraph.get().hasVertex('B',9));
		assertTrue(AbaloneGraph.get().hasVertex('F',5));
		assertTrue(AbaloneGraph.get().hasVertex('C',3));
		assertFalse(AbaloneGraph.get().hasVertex('K',11));

	}
	@Test
	public void Test_Get_Neighbor()  {

		Node v = AbaloneGraph.get().getVertex(new Node('D',4));
		Node node1= AbaloneGraph.get().getVertex(new Node('E',5));

		assertEquals(v.getNeighbor(node1),node1);

		node1= new Node('E',5);
		assertEquals(v.getNeighbor(node1),node1);

		node1= new Node('D',5);
		assertEquals(v.getNeighbor(node1),node1);

		node1= new Node('C',4);
		assertEquals(v.getNeighbor(node1),node1);

		node1= new Node('C',3);
		assertEquals(v.getNeighbor(node1),node1);

		node1= new Node('D',3);
		assertEquals(v.getNeighbor(node1),node1);

		node1= new Node('E',4);
		assertEquals(v.getNeighbor(node1),node1);
		
		node1= new Node('G',6);{
		assertEquals(v.getNeighbor(node1),null);
		}
	}

	@Test
	public void Test_Get_Neighbor_Null()  {

		Node node= new Node('E',1);
		Node v = AbaloneGraph.get().getVertex(node);
		Node node1= new Node('F',1);
		assertEquals(v.getNeighbor(node1),null);
	}

	@Test
	public void Test_get_NorthEast()  {

		Node node= new Node('E',4);
		Node v = AbaloneGraph.get().getVertex(node);
		Node nodeNE= v.getNorthEast();
		assertEquals(nodeNE,new Node('E',3));
	}

	@Test
	public void Test_Get_East() {
		
		Node node= new Node('E',4);
		Node v = AbaloneGraph.get().getVertex(node);
		Node nodeE= v.getEast();
		assertEquals(nodeE,new Node('F',4));
	}

	@Test
	public void Test_Get_SouthEast() {

		Node node= new Node('E',4);
		Node v = AbaloneGraph.get().getVertex(node);
		Node nodeSE= v.getSouthEast();
		assertEquals(nodeSE,new Node('F',5));
	}

	@Test
	public void Test_Get_SouthWest() {
		
		Node node= new Node('E',4);
		Node v = AbaloneGraph.get().getVertex(node);
		Node nodeSW= v.getSouthWest();
		assertEquals(nodeSW,new Node('E',5));
	}

	@Test
	public void Test_Get_West() {
		
		Node node= new Node('E',4);
		Node v = AbaloneGraph.get().getVertex(node);
		Node nodeW= v.getWest();
		assertEquals(nodeW,new Node('D',4));
	}

	@Test
	public void Test_Get_NorthWest() {

		Node node= new Node('E',4);
		Node v = AbaloneGraph.get().getVertex(node);
		Node nodeNW= v.getNorthWest();
		assertEquals(nodeNW,new Node('D',3));

	}

	@Test
	public void Test_Black_Marble_Creation()  {

		Layer layer = Layer.getDefaultBoard();

		//testing black marbles
		assertEquals(layer.contains('A', 1), MarbleColor.BLACK);
		assertNotEquals(layer.contains('A', 1), MarbleColor.WHITE);
		assertNotEquals(layer.contains('A', 1), MarbleColor.EMPTY);

		assertEquals(layer.contains('E', 2), MarbleColor.BLACK);
		assertNotEquals(layer.contains('E', 2), MarbleColor.WHITE);
		assertNotEquals(layer.contains('E', 2), MarbleColor.EMPTY);

		assertEquals(layer.contains('E', 3), MarbleColor.BLACK);
		assertNotEquals(layer.contains('E', 3), MarbleColor.WHITE);
		assertNotEquals(layer.contains('E', 3), MarbleColor.EMPTY);

		assertEquals(layer.contains('B', 3), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('B', 3), MarbleColor.WHITE);
		assertNotEquals(layer.contains('B', 3), MarbleColor.BLACK);

	}
	@Test
	public void Test_Default_Empty_Spaces()  {
		Layer layer = Layer.getDefaultBoard();

		assertEquals(layer.contains('G', 5), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('G', 5), MarbleColor.WHITE);
		assertNotEquals(layer.contains('G', 5), MarbleColor.BLACK);
	}
	@Test
	public void Test_White_Marble_Creation()  {
		Layer layer = Layer.getDefaultBoard();
	
		assertEquals(layer.contains('F', 7), MarbleColor.WHITE);
		assertNotEquals(layer.contains('F', 7), MarbleColor.BLACK);
		assertNotEquals(layer.contains('F', 7), MarbleColor.EMPTY);

		assertEquals(layer.contains('G', 8), MarbleColor.WHITE);
		assertNotEquals(layer.contains('G', 8), MarbleColor.BLACK);
		assertNotEquals(layer.contains('G', 8), MarbleColor.EMPTY);

		assertEquals(layer.contains('H', 9), MarbleColor.WHITE);
		assertNotEquals(layer.contains('H', 9), MarbleColor.BLACK);
		assertNotEquals(layer.contains('H', 9), MarbleColor.EMPTY);
	}
	@Test
	public void Test_Nonexistent_Space()  {
		Layer layer = Layer.getDefaultBoard();
		
		//nonexistent spaces
		assertEquals(layer.contains('H', 11), MarbleColor.INVALID);
		assertNotEquals(layer.contains('H', 11), MarbleColor.BLACK);
		assertNotEquals(layer.contains('H', 11), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('H', 11), MarbleColor.WHITE);

		assertEquals(layer.contains('I', 3), MarbleColor.INVALID);
		assertNotEquals(layer.contains('I', 3), MarbleColor.BLACK);
		assertNotEquals(layer.contains('I', 3), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('I', 3), MarbleColor.WHITE);
	}
	@Test
	public void Test_Additional_Marbles()  {
		Layer layer = Layer.getDefaultBoard();

		assertEquals(layer.contains('F', 2), MarbleColor.BLACK);

		assertEquals(layer.contains('D', 8),MarbleColor.WHITE);
	}
	@Test
	public void Test_Empty_BoardValues()  {
		//tests EmptyBoard
		Layer layer = new Layer();
		
		assertEquals(layer.contains('A', 1), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('A', 1), MarbleColor.BLACK);
		assertNotEquals(layer.contains('A', 1), MarbleColor.WHITE);

		assertEquals(layer.contains('B', 2), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('B', 2), MarbleColor.BLACK);
		assertNotEquals(layer.contains('B', 2), MarbleColor.WHITE);

		assertEquals(layer.contains('D', 3), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('D', 3), MarbleColor.BLACK);
		assertNotEquals(layer.contains('D', 3), MarbleColor.WHITE);

		assertEquals(layer.contains('E', 7), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('E', 7), MarbleColor.BLACK);
		assertNotEquals(layer.contains('E', 7), MarbleColor.WHITE);

		assertEquals(layer.contains('E', 5), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('E', 5), MarbleColor.BLACK);
		assertNotEquals(layer.contains('E', 5), MarbleColor.WHITE);
	}
	
	@Test
	public void Test_contains_Opposite_Color() {
		Layer layer = new Layer();
		
		layer.addBlack('A',1);
		assertTrue(layer.contains('A',1)==MarbleColor.BLACK);

		assertTrue(layer.containsOppositeColor('A',1, MarbleColor.WHITE));
		
		layer.removeBlack('A', 1);
		layer.addWhite('A', 1);

		assertTrue(layer.containsOppositeColor('A',1, MarbleColor.BLACK));
		
	}


}
