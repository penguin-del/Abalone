package test.subgraph;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import graph.Node;
import subgraph.BFS;

class BFSTest {

	public void test_small_triangle_Adder(Layer layer) {
		
		layer.addWhite('A', 1);
		layer.addWhite('A', 2);
		layer.addWhite('B', 2);
		layer.addWhite('C', 1);
		layer.addWhite('D', 1);
		layer.addWhite('D', 2);
		
//		Node node1 = graph.getVertex('A', 1);
//		Node node2 = graph.getVertex('A', 2);
//		Node node3 = graph.getVertex('B', 2);
//		Node node4 = graph.getVertex('C', 1);
//		Node node5 = graph.getVertex('D', 1);
//		Node node6 = graph.getVertex('D', 2);
//		
//		graph.getVertices().put(node1, MarbleColor.WHITE);
//		graph.getVertices().put(node2, MarbleColor.WHITE);
//		graph.getVertices().put(node3, MarbleColor.WHITE);
//		graph.getVertices().put(node4, MarbleColor.WHITE);
//		graph.getVertices().put(node5, MarbleColor.WHITE);
//		graph.getVertices().put(node6, MarbleColor.WHITE);
	}
	
	public void test_two_long_straight_lines_Adder(Layer layer) {
		
		layer.addBlack('A', 1);
		layer.addBlack('B', 2);
		layer.addBlack('C', 3);
		layer.addBlack('D', 4);
		layer.addBlack('G', 7);
		layer.addBlack('G', 6);
		layer.addBlack('G', 5);
		layer.addBlack('G', 4);

		
//		Node node1 = graph.getVertex('A', 1);
//		Node node2 = graph.getVertex('B', 2);
//		Node node3 = graph.getVertex('C', 3);
//		Node node4 = graph.getVertex('D', 4);
//		Node node5 = graph.getVertex('G', 7);
//		Node node6 = graph.getVertex('G', 6);
//		Node node7 = graph.getVertex('G', 5);
//		Node node8 = graph.getVertex('G', 4);
//		
//		graph.getVertices().put(node1, MarbleColor.BLACK);
//		graph.getVertices().put(node2, MarbleColor.BLACK);
//		graph.getVertices().put(node3, MarbleColor.BLACK);
//		graph.getVertices().put(node4, MarbleColor.BLACK);
//		graph.getVertices().put(node5, MarbleColor.BLACK);
//		graph.getVertices().put(node6, MarbleColor.BLACK);
//		graph.getVertices().put(node7, MarbleColor.BLACK);
//		graph.getVertices().put(node8, MarbleColor.BLACK);
	}
	
	public void test_two_small_blobs_Adder(Layer layer) {
		
		layer.addBlack('D', 4);
		layer.addBlack('C', 3);
		layer.addBlack('D', 3);
		layer.addBlack('D', 2);
		layer.addBlack('C', 1);
		layer.addWhite('F', 7);
		layer.addWhite('G', 7);
		layer.addWhite('H', 8);
		layer.addWhite('H', 9);
		layer.addWhite('G', 9);
//		Node node1 = graph.getVertex('D', 4);
//		Node node2 = graph.getVertex('C', 3);
//		Node node3 = graph.getVertex('D', 3);
//		Node node4 = graph.getVertex('D', 2);
//		Node node5 = graph.getVertex('C', 1);
//		Node node6 = graph.getVertex('F', 7);
//		Node node7 = graph.getVertex('G', 7);
//		Node node8 = graph.getVertex('H', 8);
//		Node node9 = graph.getVertex('H', 9);
//		Node node10 = graph.getVertex('G', 9);
//		
//		graph.getVertices().put(node1, MarbleColor.BLACK);
//		graph.getVertices().put(node2, MarbleColor.BLACK);
//		graph.getVertices().put(node3, MarbleColor.BLACK);
//		graph.getVertices().put(node4, MarbleColor.BLACK);
//		graph.getVertices().put(node5, MarbleColor.BLACK);
//		graph.getVertices().put(node6, MarbleColor.WHITE);
//		graph.getVertices().put(node7, MarbleColor.WHITE);
//		graph.getVertices().put(node8, MarbleColor.WHITE);
//		graph.getVertices().put(node9, MarbleColor.WHITE);
//		graph.getVertices().put(node10, MarbleColor.WHITE);
	}
	
	public void test_one_large_blob_Adder(Layer layer){
		
		layer.addBlack('C', 5);
		layer.addBlack('D', 5);
		layer.addBlack('G', 5);
		layer.addBlack('H', 5);
		layer.addBlack('D', 6);
		layer.addBlack('E', 6);
		layer.addBlack('G', 6);
		layer.addBlack('H', 6);
		layer.addBlack('F', 7);
		layer.addBlack('G', 7);
		
//		Node node1 = graph.getVertex('C', 5);
//		Node node2 = graph.getVertex('D', 5);
//		Node node3 = graph.getVertex('G', 5);
//		Node node4 = graph.getVertex('H', 5);
//		Node node5 = graph.getVertex('D', 6);
//		Node node6 = graph.getVertex('E', 6);
//		Node node7 = graph.getVertex('G', 6);
//		Node node8 = graph.getVertex('H', 6);
//		Node node9 = graph.getVertex('F', 7);
//		Node node10 = graph.getVertex('G', 7);
//		
//		graph.getVertices().put(node1, MarbleColor.BLACK);
//		graph.getVertices().put(node2, MarbleColor.BLACK);
//		graph.getVertices().put(node3, MarbleColor.BLACK);
//		graph.getVertices().put(node4, MarbleColor.BLACK);
//		graph.getVertices().put(node5, MarbleColor.BLACK);
//		graph.getVertices().put(node6, MarbleColor.BLACK);
//		graph.getVertices().put(node7, MarbleColor.BLACK);
//		graph.getVertices().put(node8, MarbleColor.BLACK);
//		graph.getVertices().put(node9, MarbleColor.BLACK);
//		graph.getVertices().put(node10, MarbleColor.BLACK);
	}
	
	public void add_second_large_blob(Layer layer) {
		layer.addWhite('D', 3);
		layer.addWhite('E', 3);
		layer.addWhite('D', 4);
		layer.addWhite('E', 4);
		layer.addWhite('F', 4);
		layer.addWhite('E', 5);
		layer.addWhite('F', 5);
		layer.addWhite('F', 6);
		
//		Node node1 = graph.getVertex('D', 3);
//		Node node2 = graph.getVertex('E', 3);
//		Node node3 = graph.getVertex('D', 4);
//		Node node4 = graph.getVertex('E', 4);
//		Node node5 = graph.getVertex('F', 4);
//		Node node6 = graph.getVertex('E', 5);
//		Node node7 = graph.getVertex('F', 5);
//		Node node8 = graph.getVertex('F', 6);
//		
//		graph.getVertices().put(node1, MarbleColor.WHITE);
//		graph.getVertices().put(node2, MarbleColor.WHITE);
//		graph.getVertices().put(node3, MarbleColor.WHITE);
//		graph.getVertices().put(node4, MarbleColor.WHITE);
//		graph.getVertices().put(node5, MarbleColor.WHITE);
//		graph.getVertices().put(node6, MarbleColor.WHITE);
//		graph.getVertices().put(node7, MarbleColor.WHITE);
//		graph.getVertices().put(node8, MarbleColor.WHITE);
	}
	
	public void many_small_formations(Layer layer) {
		
		layer.addWhite('B', 6);
		layer.addWhite('C', 6);
		layer.addWhite('D', 6);
		layer.addWhite('E', 7);
		layer.addWhite('F', 8);
		layer.addBlack('C', 3);
		layer.addBlack('D', 4);
		layer.addBlack('D', 5);
		layer.addBlack('C', 4);
		layer.addWhite('C', 2);
		layer.addWhite('D', 2);
		layer.addWhite('E', 3);
		layer.addBlack('D', 7);
		layer.addBlack('D', 8);
		layer.addBlack('E', 8);
		
//		graph.getVertices().put(graph.getVertex('B', 6), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('C', 6), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('D', 6), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('E', 7), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('F', 8), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('C', 3), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('D', 4), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('D', 5), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('C', 4), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('C', 2), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('D', 2), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('E', 3), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('D', 7), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('D', 8), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('E', 8), MarbleColor.BLACK);
	}
	
	public void nodes_but_not_formations_Adder(Layer layer) {
		layer.addWhite('B', 6);
		layer.addWhite('C', 6);
		layer.addBlack('D', 6);
		layer.addBlack('E', 7);
		layer.addWhite('F', 8);
		layer.addBlack('C', 3);
//		graph.getVertices().put(graph.getVertex('B', 6), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('C', 6), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('D', 6), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('E', 7), MarbleColor.BLACK);
//		graph.getVertices().put(graph.getVertex('F', 8), MarbleColor.WHITE);
//		graph.getVertices().put(graph.getVertex('C', 3), MarbleColor.BLACK);
	}
	
	@Test
	void test_2_small_triangles() throws IOException {
		Layer layer = new Layer();
		test_small_triangle_Adder(layer);
		BFS formations = new BFS(layer, MarbleColor.WHITE);
		
		
		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('A', 2);
		Node node3 = AbaloneGraph.get().getVertex('B', 2);
		Node node4 = AbaloneGraph.get().getVertex('C', 1);
		Node node5 = AbaloneGraph.get().getVertex('D', 1);
		Node node6 = AbaloneGraph.get().getVertex('D', 2);
		
		ArrayList<Node> firstblob = new ArrayList<Node>();
		ArrayList<Node> secondblob = new ArrayList<Node>();
		ArrayList<ArrayList<Node>> blobs = new ArrayList<ArrayList<Node>>();
		
		firstblob.add(node1);
		firstblob.add(node2);
		firstblob.add(node3);
		secondblob.add(node4);
		secondblob.add(node5);
		secondblob.add(node6);
		blobs.add(firstblob);
		blobs.add(secondblob);
		
		
		assertEquals(formations.BFSOnMarbleFormations(MarbleColor.WHITE), blobs);
		
		
	}
	
	@Test
	void test_two_long_straight_lines() throws IOException {
		Layer layer = new Layer();
		test_two_long_straight_lines_Adder(layer);
		BFS formations = new BFS(layer, MarbleColor.BLACK);
		
		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 2);
		Node node3 = AbaloneGraph.get().getVertex('C', 3);
		Node node4 = AbaloneGraph.get().getVertex('D', 4);
		Node node5 = AbaloneGraph.get().getVertex('G', 7);
		Node node6 = AbaloneGraph.get().getVertex('G', 6);
		Node node7 = AbaloneGraph.get().getVertex('G', 5);
		Node node8 = AbaloneGraph.get().getVertex('G', 4);
		
		ArrayList<Node> firstblob = new ArrayList<Node>();
		ArrayList<Node> secondblob = new ArrayList<Node>();
		ArrayList<ArrayList<Node>> blobs = new ArrayList<ArrayList<Node>>();
		
		firstblob.add(node1);
		firstblob.add(node2);
		firstblob.add(node3);
		firstblob.add(node4);
		secondblob.add(node8);
		secondblob.add(node7);
		secondblob.add(node6);
		secondblob.add(node5);
		blobs.add(firstblob);
		blobs.add(secondblob);
		
		assertEquals(formations.BFSOnMarbleFormations(MarbleColor.BLACK), blobs);
	}
	
	@Test
	void test_two_small_blobs() throws IOException {
		Layer layer = new Layer();
		test_two_small_blobs_Adder(layer);
		BFS blackFormations = new BFS(layer, MarbleColor.BLACK);
		BFS whiteFormations = new BFS(layer, MarbleColor.WHITE);
		
		Node node1 = AbaloneGraph.get().getVertex('D', 4);
		Node node2 = AbaloneGraph.get().getVertex('C', 3);
		Node node3 = AbaloneGraph.get().getVertex('D', 3);
		Node node4 = AbaloneGraph.get().getVertex('D', 2);
		Node node5 = AbaloneGraph.get().getVertex('C', 1);
		Node node6 = AbaloneGraph.get().getVertex('F', 7);
		Node node7 = AbaloneGraph.get().getVertex('G', 7);
		Node node8 = AbaloneGraph.get().getVertex('H', 8);
		Node node9 = AbaloneGraph.get().getVertex('H', 9);
		Node node10 = AbaloneGraph.get().getVertex('G', 9);
		
		ArrayList<Node> firstblob = new ArrayList<Node>();
		ArrayList<Node> secondblob = new ArrayList<Node>();
		ArrayList<ArrayList<Node>> blackBlob = new ArrayList<ArrayList<Node>>();
		ArrayList<ArrayList<Node>> whiteBlob = new ArrayList<ArrayList<Node>>();
		
		firstblob.add(node5);
		firstblob.add(node4);
		firstblob.add(node2);
		firstblob.add(node3);
		firstblob.add(node1);
		secondblob.add(node6);
		secondblob.add(node7);
		secondblob.add(node8);
		secondblob.add(node10);
		secondblob.add(node9);
		
		blackBlob.add(firstblob);
		whiteBlob.add(secondblob);
		
		assertEquals(blackFormations.BFSOnMarbleFormations(MarbleColor.BLACK), blackBlob);
		assertEquals(whiteFormations.BFSOnMarbleFormations(MarbleColor.WHITE), whiteBlob);
			
	}
	
	@Test
	void test_one_large_blob() throws IOException {
		Layer layer = new Layer();
		test_one_large_blob_Adder(layer);
		BFS blackFormations = new BFS(layer, MarbleColor.BLACK);
		BFS whiteFormations = new BFS(layer, MarbleColor.WHITE);
		
		ArrayList<Node> firstblob = new ArrayList<Node>();
		ArrayList<ArrayList<Node>> blackBlob = new ArrayList<ArrayList<Node>>();
		
		firstblob.add(AbaloneGraph.get().getVertex('C', 5));
		firstblob.add(AbaloneGraph.get().getVertex('D', 5));
		firstblob.add(AbaloneGraph.get().getVertex('G', 5));
		firstblob.add(AbaloneGraph.get().getVertex('H', 5));
		firstblob.add(AbaloneGraph.get().getVertex('D', 6));
		firstblob.add(AbaloneGraph.get().getVertex('E', 6));
		firstblob.add(AbaloneGraph.get().getVertex('G', 6));
		firstblob.add(AbaloneGraph.get().getVertex('H', 6));
		firstblob.add(AbaloneGraph.get().getVertex('F', 7));
		firstblob.add(AbaloneGraph.get().getVertex('G', 7));
		
		blackBlob.add(firstblob);
		
		assertEquals(blackFormations.BFSOnMarbleFormations(MarbleColor.BLACK), blackBlob);
		assertEquals(whiteFormations.BFSOnMarbleFormations(MarbleColor.WHITE), new ArrayList<ArrayList<Node>>());
	}

	@Test
	void test_two_touching_blobs() throws IOException {
		Layer layer = new Layer();
		test_one_large_blob_Adder(layer);
		add_second_large_blob(layer);
		BFS blackFormations = new BFS(layer, MarbleColor.BLACK);
		BFS whiteFormations = new BFS(layer, MarbleColor.WHITE);
		
		
		ArrayList<Node> firstblob = new ArrayList<Node>();
		ArrayList<Node> secondblob = new ArrayList<Node>();
		ArrayList<ArrayList<Node>> blackBlob = new ArrayList<ArrayList<Node>>();
		ArrayList<ArrayList<Node>> whiteBlob = new ArrayList<ArrayList<Node>>();
		
		firstblob.add(AbaloneGraph.get().getVertex('C', 5));
		firstblob.add(AbaloneGraph.get().getVertex('D', 5));
		firstblob.add(AbaloneGraph.get().getVertex('G', 5));
		firstblob.add(AbaloneGraph.get().getVertex('H', 5));
		firstblob.add(AbaloneGraph.get().getVertex('D', 6));
		firstblob.add(AbaloneGraph.get().getVertex('E', 6));
		firstblob.add(AbaloneGraph.get().getVertex('G', 6));
		firstblob.add(AbaloneGraph.get().getVertex('H', 6));
		firstblob.add(AbaloneGraph.get().getVertex('F', 7));
		firstblob.add(AbaloneGraph.get().getVertex('G', 7));
		
		secondblob.add(AbaloneGraph.get().getVertex('D', 3));
		secondblob.add(AbaloneGraph.get().getVertex('E', 3));
		secondblob.add(AbaloneGraph.get().getVertex('D', 4));
		secondblob.add(AbaloneGraph.get().getVertex('E', 4));
		secondblob.add(AbaloneGraph.get().getVertex('F', 4));
		secondblob.add(AbaloneGraph.get().getVertex('E', 5));
		secondblob.add(AbaloneGraph.get().getVertex('F', 5));
		secondblob.add(AbaloneGraph.get().getVertex('F', 6));
		
		blackBlob.add(firstblob);
		whiteBlob.add(secondblob);
		
		assertEquals(blackFormations.BFSOnMarbleFormations(MarbleColor.BLACK), blackBlob);
		assertEquals(whiteFormations.BFSOnMarbleFormations(MarbleColor.WHITE), whiteBlob);
	}
	
	@Test
	void test_many_small_formations() throws IOException {
		Layer layer = new Layer();
		many_small_formations(layer);
		BFS blackFormations = new BFS(layer, MarbleColor.BLACK);
		BFS whiteFormations = new BFS(layer, MarbleColor.WHITE);
		
		ArrayList<Node> firstblob = new ArrayList<Node>();
		ArrayList<Node> secondblob = new ArrayList<Node>();
		ArrayList<Node> thirdblob = new ArrayList<Node>();
		ArrayList<Node> fourthblob = new ArrayList<Node>();
		
		ArrayList<ArrayList<Node>> blackBlob = new ArrayList<ArrayList<Node>>();
		ArrayList<ArrayList<Node>> whiteBlob = new ArrayList<ArrayList<Node>>();
		
		firstblob.add(AbaloneGraph.get().getVertex('B', 6));
		firstblob.add(AbaloneGraph.get().getVertex('C', 6));
		firstblob.add(AbaloneGraph.get().getVertex('D', 6));
		firstblob.add(AbaloneGraph.get().getVertex('E', 7));
		firstblob.add(AbaloneGraph.get().getVertex('F', 8));
		secondblob.add(AbaloneGraph.get().getVertex('C', 3));
		secondblob.add(AbaloneGraph.get().getVertex('C', 4));
		secondblob.add(AbaloneGraph.get().getVertex('D', 4));
		secondblob.add(AbaloneGraph.get().getVertex('D', 5));
		thirdblob.add(AbaloneGraph.get().getVertex('C', 2));
		thirdblob.add(AbaloneGraph.get().getVertex('D', 2));
		thirdblob.add(AbaloneGraph.get().getVertex('E', 3));
		fourthblob.add(AbaloneGraph.get().getVertex('D', 7));
		fourthblob.add(AbaloneGraph.get().getVertex('D', 8));
		fourthblob.add(AbaloneGraph.get().getVertex('E', 8));
		
		blackBlob.add(secondblob);
		blackBlob.add(fourthblob);
		whiteBlob.add(firstblob);
		whiteBlob.add(thirdblob);
		
		
		assertEquals(blackFormations.BFSOnMarbleFormations(MarbleColor.BLACK), blackBlob);
		assertEquals(whiteFormations.BFSOnMarbleFormations(MarbleColor.WHITE), whiteBlob);
	}
	
	@Test
	void test_groups_that_are_not_large_enough_to_be_formations() throws IOException {
		Layer layer = new Layer();
		nodes_but_not_formations_Adder(layer);
		BFS blackFormations = new BFS(layer, MarbleColor.BLACK);
		BFS whiteFormations = new BFS(layer, MarbleColor.WHITE);
		
		assertEquals(blackFormations.BFSOnMarbleFormations(MarbleColor.BLACK), new ArrayList<ArrayList<Node>>());
		assertEquals(whiteFormations.BFSOnMarbleFormations(MarbleColor.WHITE), new ArrayList<ArrayList<Node>>());
	}
}
