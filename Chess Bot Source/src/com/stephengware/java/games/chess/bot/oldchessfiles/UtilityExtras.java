package com.stephengware.java.games.chess.bot;

import com.stephengware.java.games.chess.state.*;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A utility function measures how desirable a given state is for some agent.
 * Because we are assuming a two player zero-sum game, we can say that player
 * X is trying to maximize utility and player O is trying to minimize utility.
 * 
 * @author Stephen G. Ware
 */
public class UtilityExtras {


	
	/**
	 * Returns the desirability of the current state for white.
	 * 
	 * @param state the current state of the game
	 * @return a positive or negative number or zero
	 */
	public static GameTree evaluate(GameTree tree) {
		Iterator<Piece> it = tree.state.board.iterator();

		//raw piece score
			double whiteScore = 0;
			Piece p = null;
				while(it.hasNext()){
					p = it.next();
					if (p.player.toString() == "WHITE"){
						if(p.getClass() == Pawn.class){whiteScore = whiteScore + 1;}
						if(p.getClass() == Bishop.class){whiteScore = whiteScore + 3;}
						if(p.getClass() == Knight.class){whiteScore = whiteScore + 3;}
						if(p.getClass() == Rook.class){	whiteScore = whiteScore + 5;}
						if(p.getClass() == Queen.class){whiteScore = whiteScore + 9;}
				//		if(tree.state.check){whiteScore = whiteScore - 5;}
						if(tree.state.over){whiteScore = whiteScore - 100;}

					}
				}

		    double blackScore =0;
			while(it.hasNext()){
				p = it.next();
				if (p.player.toString() == "BLACK"){
					if(p.getClass() == Pawn.class){blackScore = blackScore + 1;}
					if(p.getClass() == Bishop.class){blackScore = blackScore + 3;}
					if(p.getClass() == Knight.class){blackScore = blackScore + 3;}
					if(p.getClass() == Rook.class){	blackScore = blackScore + 5;}
					if(p.getClass() == Queen.class){blackScore = blackScore + 9;}
				//	if(tree.state.check){blackScore = blackScore - 5;}
					if(tree.state.over){blackScore = blackScore - 100;}

				}
			}
		    tree.value = whiteScore - blackScore;
		    return tree;

	}
		
////		total = blackScore - whiteScore;
//
////		if(captureHappened(state)){
////			Piece p = wasCaptured(state);
////			if(state.player.toString() == "WHITE"){
////				 if(p.getClass() == Pawn.class){
////					 total = total - 1;
////				 }
////				 if(p.getClass() == Bishop.class){
////					total = total - 3;
////							 }
////				 if(p.getClass() == Knight.class){
////					 total = total - 3.5;
////				 }
////				 if(p.getClass() == Rook.class){
////					 total = total - 5;
////				 }
////				 if(p.getClass() == Queen.class){
////					 total = total - 9;
////				 }
////			}else{
////				if(p.getClass() == Pawn.class){
////					 total = total + 1;
////				 }
////				 if(p.getClass() == Bishop.class){
////					total = total + 3;
////							 }
////				 if(p.getClass() == Knight.class){
////					 total = total + 3.5;
////				 }
////				 if(p.getClass() == Rook.class){
////					 total = total + 5;
////				 }
////				 if(p.getClass() == Queen.class){
////					 total = total + 9;
////				 }
////			}
////		}else{
////			total = total + 10;
////			
////		}
////		
////		total = total 
////				///- movesUntilDraw(state)
////				+ whitePosition(state)
////				- blackPosition(state) 
////				- blackKingDefenseScore(state); 
//				
//		//-position for black
//		//+king safety for white   pieces around or center if alone
//		//-king safety for black
//		//+q mobility for white
//		//-q mobility for black
//		//bishop square colors :  for pieces, if bishop, if even/even = black, if odd/odd = black, if even/odd = white
//		  //or, if rank + file mod 2 == 0 black
//		
//		
//	
//	
//			
//		
//		
//		
//		
//		
////		ArrayList<Piece>layout = StateSensors.getPieceLayout(state.board);
////
////		double whiteScore = StateSensors.numRooks(layout, "WHITE")*5 +
////				StateSensors.numKnights(layout, "WHITE")*3 +
////				StateSensors.numBishops(layout, "WHITE")*3 +
////				StateSensors.numPawns(layout, "WHITE")*1;
////				//state.countDescendants()*.1;
////		
////		if(!StateSensors.isQueenMissing(layout, "WHITE")){
////			whiteScore = whiteScore + 9;
////		}
////
////		if(state.board.getKing(state.player) == null){
////			if(state.player.toString() == "WHITE"){
////				whiteScore = whiteScore + Double.MAX_VALUE;
////			}
////		}
//		
////		if(state.player.toString() == "BLACK"
////				&& state.over){
////			whiteScore = whiteScore + Double.MAX_VALUE;
////		}
//		
////		if(state.player.toString() == "BLACK"
////				&& state.check){
////			whiteScore = whiteScore + 1;
////		}
////		//pairs bonus
////		if (StateSensors.numBishops(layout, "WHITE") == 2){
////			whiteScore = whiteScore + .2;
////		}
////		if(StateSensors.numKnights(layout, "WHITE") == 2){
////			whiteScore = whiteScore - .1;
////		}
////		if(StateSensors.numRooks(layout, "WHITE") == 1){
////			whiteScore = whiteScore - .1;
////		}
//		
//	
//	
////		if(state.player.toString() == "WHITE"
////				&& state.over){
////			blackScore = blackScore + Double.MAX_VALUE;
////		}
////		if(state.player.toString() == "WHITE"
////				&& state.check){
////			blackScore = blackScore + 1;
////		}
////		if (StateSensors.numBishops(layout, "BLACK") == 2){
////			blackScore = blackScore + 2;
////		}
////		if(StateSensors.numKnights(layout, "BLACK") == 1){
////			blackScore = blackScore + 2;
////		}
////		if(StateSensors.numRooks(layout, "BLACK") == 1){
////			blackScore = blackScore - 2;
////		}
//	
//	
//	
//	private static Piece wasCaptured(State state){
//		ArrayList<Piece> layout = StateSensors.getPieceLayout(state.board);
//		ArrayList<Piece> prevLayout =  StateSensors.getPieceLayout(state.previous.board);
//		prevLayout.removeAll(layout);
//
//		
//		return prevLayout.get(0);
//	}
//
//	
//	private static boolean captureHappened(State state){
//		boolean captureHappened = false;
//		if(state.previous.board.countPieces() > state.board.countPieces()){
//			captureHappened = true;
//		}
//		return captureHappened;
//	}
//	
//	
//	
//	private static double movesUntilDraw(State state){
//		double toReturn = .1* state.movesUntilDraw;
//		if(state.player.toString() == "WHITE"){
//		}else{
//			toReturn = toReturn*(-1);
//		}
//		
//		return toReturn;
//	}
//	private static double whitePosition(State state){
//		double toReturn = 0;
//		Piece centerPawnL = state.board.getPieceAt (3, 3);
//		Piece centerPawnR = state.board.getPieceAt (4, 3);
//		Piece kingPawn8 = state.board.getPieceAt (7, 2);
//		Piece queenKnight = state.board.getPieceAt (2, 2);
//		
//		if(centerPawnL != null){
//			if (centerPawnL.getClass() == Pawn.class && centerPawnL.player.toString() == "WHITE"){
//				toReturn = toReturn + .1;
//			}
//		}
//		if(centerPawnR != null){
//			if (centerPawnR.getClass() == Pawn.class && centerPawnR.player.toString() == "WHITE"){
//				toReturn = toReturn + .1;
//			}
//		}
//		if(kingPawn8 != null){
//			if (kingPawn8.getClass() == Pawn.class && kingPawn8.player.toString() == "WHITE"){
//				toReturn = toReturn + .1;
//			}
//		}
//		if(queenKnight != null){
//			if (queenKnight.getClass() == Knight.class && queenKnight.player.toString() == "WHITE"){
//				toReturn = toReturn + .1;
//			}
//		}
//		
////		if(state.turn < 20){//penalty for advancing queen/bishop too early.
////			ArrayList<Piece>layout = StateSensors.getPieceLayout(state.board);
////			
////			Piece queen = StateSensors.getWhiteQueen(layout);
////			ArrayList<Piece> bishops = StateSensors.getBishops(layout, "WHITE");
////			if(queen !=null){
////				if (queen.file > 3){
////					toReturn = toReturn - 2;
////				}
////			}
////			for(Piece bishop : bishops){
////				if(bishop !=null){
////					if (bishop.file > 3){
////						toReturn = toReturn - 2;
////					}
////				}
////			}
////		}
//		return toReturn;
//	}
//	
//	private static double blackPosition(State state){
//		double toReturn = 0;
//		Piece centerPawnL = state.board.getPieceAt (3, 5);
//		Piece centerPawnR = state.board.getPieceAt (4, 3);
//		Piece rookPawn0 = state.board.getPieceAt (0, 5);
////		Piece centerBishop = state.board.getPieceAt (0, 5);
//
////		Piece queenKnight = state.board.getPieceAt (2, 2);
//		
//		if(centerPawnL != null){
//			if (centerPawnL.getClass() == Pawn.class && centerPawnL.player.toString() == "BLACK"){
//				toReturn = toReturn + .1;
//			}
//		}
//		if(centerPawnR != null){
//			if (centerPawnR.getClass() == Pawn.class && centerPawnR.player.toString() == "BLACK"){
//				toReturn = toReturn + .1;
//			}
//		}
//		if(rookPawn0 != null){
//			if (rookPawn0.getClass() == Pawn.class && rookPawn0.player.toString() == "BLACK"){
//				toReturn = toReturn + 1.1;
//			}
//		}
//		return toReturn;
//	}
//	private static double blackKingDefenseScore(State state){
//		double score = 0;
//			King blackKing = state.board.getKing(Player.BLACK);
//			ArrayList<Piece> surroundingTheKing  = new ArrayList<>();
//			for(int i = 1; i <3; i ++){
//				surroundingTheKing.add(state.board.getPieceAt(blackKing.file - i, blackKing.rank-i));
//				surroundingTheKing.add(state.board.getPieceAt(blackKing.file + i, blackKing.rank-i));
//				surroundingTheKing.add(state.board.getPieceAt(blackKing.file - i, blackKing.rank+i));
//				surroundingTheKing.add(state.board.getPieceAt(blackKing.file + i, blackKing.rank +i));
//			}
//			for (Piece p : surroundingTheKing){
//				if(p != null){
//					if (p.getClass() == Queen.class && p.player.toString() == "BLACK"){
//						score = score + .1;
//					}
//				}
//				if(p != null){
//					if (p.getClass() == Rook.class && p.player.toString() == "BLACK"){
//						score = score + .1;
//					}
//				}
//				if(p != null){
//					if (p.getClass() == Knight.class && p.player.toString() == "BLACK"){
//						score = score + .1;
//					}
//				}
//				if(p != null){
//					if (p.getClass() == Bishop.class && p.player.toString() == "BLACK"){
//						score = score + .1;
//					}
//				}
//				if(p != null){
//					if (p.getClass() == Pawn.class && p.player.toString() == "BLACK"){
//						score = score + .1;
//					}
//				}
//			}
//			
//			return 0;
//	}
//	
//	
//	

}
