package MCTS;

import java.util.ArrayList;
import java.util.Arrays;

import board.Layer;
import board.Marble.MarbleColor;
import globals.Constants;
import player.AbstractPlayer;
import player.RandomPlayerAllMove;
import player.RandomPlayerPushMove;
import player.RandomPlayerTieredMove;
import utilities.Timer;

public class loopData {



	public static void main(String[] args) {
		TreeNode root = new TreeNode(Layer.getDefaultBoard(), null);
		ArrayList<Integer> moves = new ArrayList<> (Arrays.asList(25, 50, 75));
		ArrayList<Integer> iterations = new ArrayList<> (Arrays.asList(5000, 10000, 15000));
		ArrayList<Double> UCB1 = new ArrayList<Double> (Arrays.asList(0.0, 0.1, 0.5));
		ArrayList<AbstractPlayer> competetors = new ArrayList<AbstractPlayer>();
		competetors.add(new RandomPlayerAllMove());
		competetors.add(new RandomPlayerPushMove());
		competetors.add(new RandomPlayerTieredMove());

//		System.out.println(moves);
//		System.out.println(iterations);
//		System.out.println(UCB1);
//		System.out.println(competetors);

		for(AbstractPlayer contestant: competetors) {
			Constants.PLAYER_1_TYPE = contestant;
			Constants.PLAYER_2_TYPE = contestant;
			for(int numMove: moves) {
				Constants.MAX_NUM_MOVES = numMove;
				for (int numIter : iterations) {
					Constants.NUM_ITERATION_ON_TREE = numIter;
					for (double constant: UCB1) {
						Constants.UCB1_CONSTANT = constant;
						int height = 0;
						int width = 0;
						int size = 0;
						Timer time = new Timer();
						time.start();
						for (int i = 0; i < 5; i++) {
							MonteCarloTree testTree= new MonteCarloTree(root._layer, MarbleColor.BLACK);							
							testTree.run();
							height += testTree._root.height();
							width += testTree._root.width();
							size += testTree.getSize(); 							
						}	
						time.stop();
						float averageHeight = average(height, 5);
						float averageWidth = average(width, 5);
						float averageSize = average(size, 5);
						System.out.println("NEW TEST///////////////////////////////////////////");
						System.out.println("Player: "+ contestant);
						System.out.println("Max number moves: "+ numMove);
						System.out.println("Iterations: "+ numIter);
						System.out.println("UCB1: "+ constant);
						System.out.println("Average Height over 5 tests :" +averageHeight);
						System.out.println("Average Width over 5 tests :" +averageWidth);
						System.out.println("Average Size over 5 tests :" +averageSize);
						System.out.println("Time for 5 tests :" +time+"\n");
					}
				}
			}
		}
	}
	
	public static float average(int dataType, int iteration) {
		return (float) dataType / iteration;
	}
}
