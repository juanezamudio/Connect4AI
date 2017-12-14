package lp2;

import lp2.GameState.Player;

public class AIC4Player implements GamePlayer<Integer> {
	private Player me;
	
	public AIC4Player(Player p) {
		me = p;
	}

	@Override
	public String getName() {
		// TODO Replace with a name for your AI (school appropriate please)
		return "Your AI's name";
	}
	
	private int eval(ConnectFour gs) {
		// TODO Implement a board evaluation function
		return 0;
	}
	
	@Override
	public Integer getMove(GameState<Integer> gs) {
		if(!(gs instanceof ConnectFour))
			throw new IllegalArgumentException("Cannot play non-Connect four game.");
		ConnectFour c4 = (ConnectFour)gs;
		
		// TODO Implement your Connect Four AI's behavior
		return c4.getMoves().get(0);
	}
}
