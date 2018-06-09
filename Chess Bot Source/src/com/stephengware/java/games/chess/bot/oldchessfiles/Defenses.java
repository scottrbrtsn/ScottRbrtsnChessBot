package com.stephengware.java.games.chess.bot;


import java.util.ArrayList;
import java.util.Iterator;

import com.stephengware.java.games.chess.bot.Bot;
import com.stephengware.java.games.chess.state.*;

public class Defenses {
		
	public static State defendTheCenter(State state){
		
		State toReturn = new State();

		
		Piece kingPawn = state.board.getPieceAt(4, 6);
		Piece queenPawn = state.board.getPieceAt(3, 6);
		Piece kingBishPawn = state.board.getPieceAt(5, 6);
		Piece queenBish = state.board.getPieceAt(2, 7);
		Piece queenKnight = state.board.getPieceAt(1, 7);
		Piece queen = state.board.getPieceAt(3, 7);


		if (kingPawn != null && kingPawn.getClass() == Pawn.class){
			toReturn = state.next(kingPawn, kingPawn.move(0, -2));
		}else
		if (queenPawn != null && queenPawn.getClass() == Pawn.class){
			toReturn = state.next(queenPawn, queenPawn.move(0, -1));
		}else
		if (kingBishPawn != null && kingBishPawn.getClass() == Pawn.class){
			toReturn = state.next(kingBishPawn, kingBishPawn.move(0, -1));
		}else
		if (queenBish != null && queenBish.getClass() == Bishop.class){
			toReturn = state.next(queenBish, queenBish.move(2, -2));
		}else
		if (queenKnight != null && queenKnight.getClass() == Knight.class){
			toReturn = state.next(queenKnight, queenKnight.move(1, -2));
		}else
		if (queen != null && queen.getClass() == Queen.class){
				toReturn = state.next(queen, queen.move(0, -1));
			}
		return toReturn;
	}
}
