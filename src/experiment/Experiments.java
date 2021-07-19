package experiment;

import java.io.IOException;

import player.MonteCarloPlayer;
import player.RandomPlayerAllMove;
import player.RandomPlayerPushMove;
import player.RandomPlayerTieredMove;
import player.TimeBasedPlayer;

public class Experiments {

	
	
	public static void main(String[] args) throws IOException {
		
		TwoPushMovers();
		
	}
	
	public static void TwoPushMovers() throws IOException {

		Experiment_Parameters params = new Experiment_Parameters(new TimeBasedPlayer(), new MonteCarloPlayer(), 500, 1);
		
		Experiment ex = new Experiment(params);
		
		ExperimentResults results = ex.run();
		
		System.out.print(results.toString());
	}
}
