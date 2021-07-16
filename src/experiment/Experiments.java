package experiment;

import java.io.IOException;

import player.MonteCarloPlayer;
import player.RandomPlayerAllMove;
import player.RandomPlayerPushMove;
import player.RandomPlayerTieredMove;

public class Experiments {

	
	
	public static void main(String[] args) throws IOException {
		
		TwoPushMovers();
		
	}
	
	public static void TwoPushMovers() throws IOException {
		Experiment_Parameters params = new Experiment_Parameters(new MonteCarloPlayer(), new RandomPlayerPushMove(), 500, 2);
		
		Experiment ex = new Experiment(params);
		
		ExperimentResults results = ex.run();
		
		System.out.print(results.toString());
	}
}
