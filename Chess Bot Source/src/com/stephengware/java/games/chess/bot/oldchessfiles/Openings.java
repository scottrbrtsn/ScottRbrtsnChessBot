package com.stephengware.java.games.chess.bot;

import com.stephengware.java.games.chess.state.*;

public class Openings {

	
	public static State scholarsFailed(State currentState){
		State toReturn = currentState;
		
		Piece queen = currentState.board.getPieceAt(5, 2);
		Piece queenKnight = currentState.board.getPieceAt (1, 0);
		Piece kingKnight = currentState.board.getPieceAt (6, 0); 
		Piece king = currentState.board.getPieceAt (4, 0); 
		Piece rookPawn = currentState.board.getPieceAt (7, 1); 
		Piece blackPawn1 = currentState.board.getPieceAt (3, 4); 
		Piece bishopMoved = currentState.board.getPieceAt (2, 3);

		Piece kn1 = currentState.board.getPieceAt(2, 2);
		
		
		if (blackPawn1 != null && blackPawn1.getClass() == Pawn.class && blackPawn1.player.toString() == "BLACK"  //pawn advances beyond comfort
				&& bishopMoved != null && bishopMoved.getClass() == Bishop.class && bishopMoved.player.toString() == "WHITE"){
			toReturn = currentState.next(bishopMoved, bishopMoved.move(1, -1));
		}else if (queen !=null && queen.getClass() == Queen.class){
			toReturn = currentState.next(queen, queen.move(-2, -2));
		}else if (queenKnight != null && queenKnight.getClass() == Knight.class){ 
			toReturn = currentState.next(queenKnight, queenKnight.move(1, 2));
		}else if(kingKnight != null && kingKnight.getClass() == Knight.class){
			toReturn = currentState.next(kingKnight, kingKnight.move(-1, 2));
		}else if (king != null && king.getClass() == King.class){
			toReturn = currentState.next(king, king.move(2, 0));
		}else if (rookPawn != null && rookPawn.getClass() == Pawn.class){
			toReturn = currentState.next(rookPawn, rookPawn.move(0, 1));
		}
		
		
		return toReturn;
	}
	
	/**
	 * Win in 4 moves
	 * @param currentState
	 * @return
	 */
	public static State scholarsMate(State currentState){
		//rank a-0 b-1 c-2 d-3 e-4 f-5 g-6 h-7
		State toReturn = currentState;
		Piece firstPawn = currentState.board.getPieceAt (4, 1); //pawn starts at e2
		Piece queenStart = currentState.board.getPieceAt (3, 0); //queen starts at d1
		Piece bishopStart = currentState.board.getPieceAt (5, 0); //bishop starts at f1
		Piece queenInPosition = currentState.board.getPieceAt(5, 2);
		Piece blackPawn0 = currentState.board.getPieceAt (0, 4); 

		Piece knightPawn = currentState.board.getPieceAt (1, 1); 

		if (firstPawn != null){ //if first move
			//move pawn from e2 to e5
			toReturn = currentState.next(firstPawn, firstPawn.move(2, 0));
		}else if (blackPawn0 != null && blackPawn0.getClass() == Pawn.class && blackPawn0.player.toString() == "BLACK"  //pawn advances beyond comfort
				&& knightPawn != null && knightPawn.getClass() == Pawn.class && knightPawn.player.toString() == "WHITE"){
			toReturn = currentState.next(knightPawn, knightPawn.move(0, 1));
		}else if(queenStart != null){ //if queen is still in starting point
			//move queen to h5
			toReturn = currentState.next(queenStart, queenStart.move(2, 2));
		}else if(bishopStart != null){ //if bishop is still in starting point
			//move bishop to c4
			toReturn = currentState.next(bishopStart, bishopStart.move(-3, 3));
		}else if (queenInPosition != null){//move queen to win
			toReturn = currentState.next(queenInPosition, queenInPosition.move(0, 4));
		}
		
		return toReturn;
		//maybe randomize this to throw off the defender.
		
	}
	
	public static boolean opened (Board board){
		boolean opened = true;
		Piece rookPawn = board.getPieceAt (7, 1); 
		if (rookPawn != null){
			if(rookPawn.getClass() == Pawn.class){
				if (rookPawn.player.toString() == "WHITE"){
					opened = false;
				}
			}
		}
		
		return opened;
		
	}
	
	public static boolean scholarsIsOpen(Board board){
		//rank a-0 b-1 c-2 d-3 e-4 f-5 g-6 h-7
		Piece p1 = board.getPieceAt(6, 5);
		Piece b1 = board.getPieceAt(3, 4);
		Piece b2 = board.getPieceAt(4, 5);
		Piece p2 = board.getPieceAt(3, 6);
		boolean toReturn = true;
		
		if(p1 != null || //queen is blocked at g6
				b1 != null ||//d5 bishop is blocked and likely threatened
				 	b2 != null ||//e6 bishop is blocked
				 	   p2 == null) //king can get out
		{
				toReturn =  false;
		}else{
			toReturn =   true; //scholarsMate is still OPEN
		}
		
		if(p2 !=null)
			if (p2.getClass() != Pawn.class){
				toReturn =  false;
			}
		
		return toReturn;
	}
}
	