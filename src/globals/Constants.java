package globals;

import board.Marble.MarbleColor;
import player.AbstractPlayer;
import player.MonteCarloPlayer;
import player.RandomPlayerAllMove;
import player.RandomPlayerPushMove;
import player.RandomPlayerTieredMove;

public class Constants
{
	public static final boolean DEBUG = false;
	
	public static final int MAX_LINE_LENGTH = 9;
	
	public static final MarbleColor PLAYER_1__STARTING_COLOR = MarbleColor.BLACK;
	
	public static final MarbleColor PLAYER_2__STARTING_COLOR = MarbleColor.WHITE;
	
	public static final String STANDARD_BOARD_FILE = "standardGraph.txt";
	
	public static final int NUM_STARTING_MARBLES = 14;
	
	public static double UCB1_CONSTANT = 0.05;
	
	public static int NUM_ITERATION_ON_TREE = 5000;
	
	
	//Time for each 
	public static long TIME_LIMIT_FOR_RUN = 60000;
	
	
	//number of moves that the game will play with CAN BE CHANGED TO HOWEVER MANY MOVES
	public static int MAX_NUM_MOVES = 800;
	
	public static AbstractPlayer PLAYER_1_TYPE = new RandomPlayerPushMove();
	
	public static AbstractPlayer PLAYER_2_TYPE = new RandomPlayerPushMove();
	
	public static final String DESTINATION_FILE = "destinationFile";
	
	public static final int NUM_FINAL_STAT_ITERATIONS = 5;
}
