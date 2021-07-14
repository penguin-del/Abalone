package globals;

import board.Marble.MarbleColor;

public class Constants
{
	public static final boolean DEBUG = false;
	
	public static final int MAX_LINE_LENGTH = 9;
	
	public static final MarbleColor PLAYER_1__STARTING_COLOR = MarbleColor.BLACK;
	
	public static final MarbleColor PLAYER_2__STARTING_COLOR = MarbleColor.WHITE;
	
	public static final String STANDARD_BOARD_FILE = "standardGraph.txt";
	
	public static final int NUM_STARTING_MARBLES = 14;
	
	public static final int UCB1_CONSTANT = 2;
	
	public static final int NUM_ITERATION_ON_TREE = 100;
}
