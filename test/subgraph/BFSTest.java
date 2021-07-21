package subgraph;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import graph.Node;

class BFSTest {

	public void test_small_triangle_Adder(Layer layer) {
		
		layer.addWhite('A', 1);
		layer.addWhite('A', 2);
		layer.addWhite('B', 2);
		layer.addWhite('C', 1);
		layer.addWhite('D', 1);
		layer.addWhite('D', 2);
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
	}
	
	public void nodes_but_not_formations_Adder(Layer layer) {
		layer.addWhite('B', 6);
		layer.addWhite('C', 6);
		layer.addBlack('D', 6);
		layer.addBlack('E', 7);
		layer.addWhite('F', 8);
		layer.addBlack('C', 3);
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
		whiteBlob.add(thirdblob);
		whiteBlob.add(firstblob);
		
		
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
	
	@Test
	void test_diameter_of_Wide_formation() {
		Layer layer = new Layer();
		test_one_large_blob_Adder(layer);
		BFS diameter = new BFS(layer, MarbleColor.BLACK);
		BFS blackFormations = new BFS(layer, MarbleColor.BLACK);
		assertEquals(diameter.BFSDiameter(blackFormations.BFSOnMarbleFormations(MarbleColor.BLACK).get(0)), 7);
	}
	
	@Test
	void test_diameter_Of_Compact_formation() {
		Layer layer = new Layer();
		add_second_large_blob(layer);
		BFS diameter = new BFS(layer, MarbleColor.WHITE);
		BFS whiteFormations = new BFS(layer, MarbleColor.WHITE);
		assertEquals(diameter.BFSDiameter(whiteFormations.BFSOnMarbleFormations(MarbleColor.WHITE).get(0)), 3);
	}
	
	
}
