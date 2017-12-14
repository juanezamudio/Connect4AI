package lp2;

import lp2.GameState.Player;

public class AIReversiPlayer implements GamePlayer<Pair<Integer>> {
	private Player me;
	
	public AIReversiPlayer(Player p) {
		me = p;
	}

	@Override
	public String getName() {
		// TODO Replace with a name for your AI (school appropriate please)
		return "Your AI's name";
	}
	
	private int eval(Reversi gs) {
		// TODO Implement a board evaluation function
		return 0;
	}
	
	@Override
	public Pair<Integer> getMove(GameState<Pair<Integer>> gs) {
		if(!(gs instanceof Reversi))
			throw new IllegalArgumentException("Cannot play non-Reversi game.");
		long startTime = System.currentTimeMillis();
		Reversi r = (Reversi)gs;
		
		// TODO Implement your Reversi AI's behavior
		
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		//You should make sure duration is less than 5 seconds
		//The AI tournament game runner will enforce this constraint
		//The current one does not because one player may be a human
		return r.getMoves().get(0);
	}
}
