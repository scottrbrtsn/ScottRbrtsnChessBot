package com.stephengware.java.games.chess.bot;

import com.stephengware.java.games.chess.state.*;

import java.util.Iterator;


/**
 * A utility function measures how desirable a given state is for some agent.
 * Because we are assuming a two player zero-sum game, we can say that player
 * X is trying to maximize utility and player O is trying to minimize utility.
 * 
 * 
 * @edited by Scott Robertson
 */
public class Utility {

	
	
	/**
	 * Returns the desirability of the current state for white.
	 * 
	 * @param state the current state of the game
	 * @return a positive or negative number or zero
	 */
	public static GameTree evaluate(GameTree tree, Iterator<Piece> it) {
		it = tree.state.board.iterator();

		//raw piece score
			double whiteScore = 0;
			Piece p = null;
		    double blackScore = 0;
			Piece king = tree.state.board.getKing(tree.state.player);
			Piece otherKing = tree.state.board.getKing(tree.state.player.other());
				while(it.hasNext()){
					p = it.next();
				
					if(tree.state.player.toString() == "WHITE"){//White's turn
						if(tree.state.check){whiteScore = whiteScore - 250;}
						if(tree.state.over){whiteScore = whiteScore - 1000000;}

						if (p.player.toString() == "WHITE"){//White owns the piece
							if(p.getClass() == Pawn.class){whiteScore = whiteScore + 10000;}
							
							if(p.getClass() == Bishop.class){whiteScore = whiteScore + 30000;
								if(Math.abs(p.file - otherKing.file) == Math.abs (p.rank - otherKing.rank))
										{whiteScore = whiteScore + 500;}
							}
							if(p.getClass() == Knight.class){whiteScore = whiteScore + 30050;
									if(p.rank >3){whiteScore = whiteScore + 100;
										if(Math.abs(p.rank - otherKing.rank) <3)
											{whiteScore = whiteScore + 250;}
										if(Math.abs(p.file - otherKing.file) <3)
											{whiteScore = whiteScore + 250;}
									}
							}
							
							if(p.getClass() == Rook.class){	whiteScore = whiteScore + 50000;
								if(p.file - otherKing.file <3 && p.file - otherKing.file > -3){whiteScore = whiteScore + 500;}
								if(p.rank - otherKing.rank <3 && p.rank - otherKing.rank > -3){whiteScore = whiteScore + 500;}
							}
							
							if(p.getClass() == Queen.class){whiteScore = whiteScore + 90000;
								if(Math.abs(p.file - otherKing.file) == Math.abs (p.rank - otherKing.rank))
									{whiteScore = whiteScore + 500;}
								if(Math.abs(p.rank - otherKing.rank) <3)
									{whiteScore = whiteScore + 500;}
								if(Math.abs(p.file - otherKing.file) <3)
									{whiteScore = whiteScore + 500;}
							}
							if(p.getClass() != King.class){if(p.rank >1){whiteScore = whiteScore + 100;}}
							else {if(p.rank >1){whiteScore = whiteScore - 500;}}//king is too far forward
				

							
						}
						if (p.player.toString() == "BLACK"){
							if(p.getClass() == Pawn.class){blackScore = blackScore + 1000;}
							
							if(p.getClass() == Bishop.class){blackScore = blackScore + 3000;
								if(Math.abs(p.file - king.file) == Math.abs (p.rank - king.rank))
									{whiteScore = whiteScore - 500;}
								if(Math.abs(p.rank - king.rank) <3)
									{whiteScore = whiteScore - 500;}
							}
							if(p.getClass() == Knight.class){blackScore = blackScore + 3050;
								if(p.rank <5){whiteScore = whiteScore - 100;
								if(Math.abs(p.rank - king.rank) <3)
								{whiteScore = whiteScore - 250;}
							if(Math.abs(p.file - king.file) <3)
								{whiteScore = whiteScore - 250;}}
							}
							if(p.getClass() == Rook.class){	blackScore = blackScore + 5000;}
							
							if(p.getClass() == Queen.class){blackScore = blackScore + 9000;
								if(p.file - king.file <3 && p.file - king.file > -3)
									{whiteScore = whiteScore - 500;}
								if(p.rank - king.rank <3 && p.rank - king.rank > -3)
									{whiteScore = whiteScore - 500;}
						}

						}
					}else{
						if(tree.state.check){blackScore = blackScore - 250;}
						if(tree.state.over){blackScore = blackScore - 1000000;}
						if (p.player.toString() == "WHITE"){
							if(p.getClass() == Pawn.class){whiteScore = whiteScore + 10000;}
							
							if(p.getClass() == Bishop.class){whiteScore = whiteScore + 30000;
								if(Math.abs(p.file - otherKing.file) == Math.abs (p.rank - otherKing.rank))
									{blackScore = blackScore - 500;}
								if(Math.abs(p.rank - otherKing.rank) <3)
									{blackScore = blackScore - 500;}
							}
							
							if(p.getClass() == Knight.class){whiteScore = whiteScore + 30050;
								if(p.file - king.file <3 && p.file - king.file > -3)
									{blackScore = blackScore - 500;}
								if(p.rank - king.rank <3 && p.rank - king.rank > -3)
									{blackScore = blackScore - 500;}
							}
							
							if(p.getClass() == Rook.class){	whiteScore = whiteScore + 50000;}
							if(p.getClass() == Queen.class){whiteScore = whiteScore + 90000;}

						}
						if (p.player.toString() == "BLACK"){
							if(p.getClass() == Pawn.class){blackScore = blackScore + 10000;}
							
							if(p.getClass() == Bishop.class){blackScore = blackScore + 30000;
								if(Math.abs(p.file - otherKing.file) == Math.abs (p.rank - otherKing.rank)){blackScore = blackScore + 500;}
								if(Math.abs(p.rank - otherKing.rank) <3){blackScore = blackScore + 500;}
							}
							
							if(p.getClass() == Knight.class){blackScore = blackScore + 30050;
								if(p.file - king.file <3 && p.file - king.file > -3)
									{blackScore = blackScore + 500;}
								if(p.rank - king.rank <3 && p.rank - king.rank > -3)
									{blackScore = blackScore + 500;}
							}
							if(p.getClass() == Rook.class){	blackScore = blackScore + 50000;
							if(p.file - king.file <3 && p.file - king.file > -3){blackScore = blackScore + 500;}
							if(p.rank - king.rank <3 && p.rank - king.rank > -3){blackScore = blackScore + 500;}
						}
							if(p.getClass() == Queen.class){blackScore = blackScore + 90000;
								if(Math.abs(p.file - king.file) == Math.abs (p.rank - king.rank))
									{blackScore = blackScore + 500;}
								if(Math.abs(p.rank - king.rank) <3)
									{blackScore = blackScore + 500;}
								if(Math.abs(p.file - king.file) <3)
									{blackScore = blackScore + 500;}
							}
							
							if(p.getClass() != King.class){if(p.rank <5){blackScore = blackScore + 100;}}
							else {if(p.rank <6){blackScore = blackScore - 500;}}//king is too far forward

							
						}
					}
					
					
				}
			
			
			
		
			
		    tree.value = whiteScore - blackScore;
		    if(tree.state.movesUntilDraw<6){tree.value = tree.value - 10000;}
		    return tree;

	}
		

}
