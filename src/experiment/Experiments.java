package experiment;

import java.io.IOException;

import player.RandomPlayerAllMove;

public class Experiments {

	
	
	public static void main(String[] args) throws IOException {
		
		TwoPushMovers();
		
	}
	
	public static void TwoPushMovers() throws IOException {
		Experiment_Parameters params = new Experiment_Parameters(new RandomPlayerAllMove(), new RandomPlayerAllMove(), 1500, 1);
		
		Experiment ex = new Experiment(params);
		
		ExperimentResults results = ex.run();
		
		System.out.print(results.toString());
	}
}
