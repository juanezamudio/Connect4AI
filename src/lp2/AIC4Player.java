package lp2;

import java.util.List;

import lp2.ConnectFour.Color;
import lp2.GameState.Player;
import lp2.GameState.Status;

public class AIC4Player implements GamePlayer<Integer> {
	private Player me;
	
	public AIC4Player(Player p) {
		me = p;
	}

	@Override
	public String getName() {
		// TODO Replace with a name for your AI (school appropriate please)
		return "Tiny Rick";
	}
	
	private int eval(ConnectFour gs) {
		
		int sum = 0;
		
		Color meColor = Color.RED;
		Color oppColor = Color.BLACK;
		
		int[][] evalMatrix = {{2, 2, 3, 10, 3, 2, 2}, 
				              {8, 3, 3, 10, 4, 3, 2},
				              {-8, 3, 3, 15, 4, 3, 2}, 
				              {3, 3, 3, 15, 4, 4, 2},
				              {3, 4, 3, 15, 4, 3, 4},
				              {-25, -15, 8, 30, 8, -15, -25}};
		
		if(me != Player.ONE){
			meColor = Color.BLACK;
			oppColor = Color.RED;
		}
	
		
		for (int i = 0; i < gs.HEIGHT-1; i++) {
			
			for (int j = 0; j < gs.WIDTH-1; j++) {
				
				if (gs.getSquare(i,j).equals(meColor)) {
					sum += evalMatrix[i][j];
				} else if (gs.getSquare(i,j).equals(oppColor)) {
					sum += evalMatrix[i][j];
				}
			}
		}
		
		return sum;
	}
	
	@Override
	public Integer getMove(GameState<Integer> gs) {
		int minDepth = 6;
		int bestValue = Integer.MIN_VALUE;
		
		if (!(gs instanceof ConnectFour)) {
			throw new IllegalArgumentException("Cannot play non-Connect four game.");
		}
			
		ConnectFour c4 = (ConnectFour)gs;
		
			System.out.println("AI " + (me == Player.ONE ? "Tiny Rick" : "Pickle Rick") + "'s turn");
			
			List<Integer> listMoves = c4.getMoves();
			
			int move = listMoves.get(0);
			
			for(Integer moves: listMoves) {
				
				 ConnectFour copyCurrentState = (ConnectFour) c4.copyState();
				 copyCurrentState.playMove(moves);
				 
				 int minimaxValue = minimax(copyCurrentState, minDepth, false);
				 
				 if (minimaxValue >= bestValue) {
					 bestValue = minimaxValue;
					 move = moves;
				 }
			 }
		
		return move;
	}
	
	public int minimax(ConnectFour c4, int minDepth, Boolean isTurn){
		int bestValue;
		
		if (c4.getGameStatus() == Status.DRAW) {
			return 0;
		} else if ((c4.getGameStatus() == Status.ONEWIN && me == Player.ONE) 
							|| (c4.getGameStatus() == Status.TWOWIN && me == Player.TWO )){
			
			return Integer.MAX_VALUE - minDepth;
		} else if ((c4.getGameStatus() == Status.TWOWIN && me == Player.ONE) 
							|| (c4.getGameStatus() == Status.ONEWIN && me == Player.TWO)){
			
			return Integer.MIN_VALUE + minDepth;
		}
		
		
		if (minDepth == 0) {
			return eval(c4);
		}
		
		if (isTurn) {

			bestValue = Integer.MIN_VALUE;
			List<Integer> listMoves = c4.getMoves();

			for (Integer moves : listMoves) {

				ConnectFour copyCurrentState = (ConnectFour) c4.copyState();
				copyCurrentState.playMove(moves);

				int minimaxValue = minimax(copyCurrentState, minDepth - 1, !isTurn);

				if (minimaxValue > bestValue) {
					bestValue = minimaxValue;
				}
			}

			return bestValue;

		} else {

			bestValue = Integer.MAX_VALUE;
			List<Integer> listMoves = c4.getMoves();

			for (Integer moves : listMoves) {

				ConnectFour copyStates = (ConnectFour) c4.copyState();
				copyStates.playMove(moves);

				int minimaxValue = minimax(copyStates, minDepth - 1, !isTurn);

				if (minimaxValue < bestValue) {
					bestValue = minimaxValue;
				}
			}

			return bestValue;
		}
	}
}
