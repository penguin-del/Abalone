package MCTS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import experiment.Experiment;
import experiment.ExperimentResults;
import experiment.Experiment_Parameters;
import globals.Constants;
import player.AbstractPlayer;
import player.HybridPlayerMoveFirst;
import player.HybridPlayerTimeFirst;
import player.MonteCarloPlayer;
import player.RandomPlayerAllMove;
import player.RandomPlayerPushMove;
import player.RandomPlayerTieredMove;
import player.TimeBasedPlayer;

public class FinalStatistics {
	
	public static void determineFinalStatistics() throws IOException {
		ArrayList<AbstractPlayer> _players = new ArrayList<AbstractPlayer>();
		//_players.add(new RandomPlayerAllMove());
		//_players.add(new RandomPlayerPushMove());
		//_players.add(new RandomPlayerTieredMove());
		_players.add(new MonteCarloPlayer());
		_players.add(new TimeBasedPlayer());
		_players.add(new HybridPlayerTimeFirst());
		_players.add(new HybridPlayerMoveFirst());
		String destination = Constants.DESTINATION_FILE;
		try(PrintStream ps = new PrintStream(destination)){
			for (AbstractPlayer player : _players) {
				for (AbstractPlayer player2 : _players) {
					Experiment_Parameters params = new Experiment_Parameters(player, player2, 1000, 4);
					Experiment exp = new Experiment(params);
					ExperimentResults results = exp.run();
					float winRatio = ((float) results._player1Wins /(float) 4)*100;
					ps.print(winRatio+"% ,");
				}
				ps.print('\n');
			}
			ps.flush();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		determineFinalStatistics();
	}

}
