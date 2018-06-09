package com.stephengware.java.games.chess.bot;

import java.util.ArrayList;
import java.util.Iterator;

import com.stephengware.java.games.chess.bot.Bot;
import com.stephengware.java.games.chess.state.*;

public class Predictions {

	
			public Predictions(){
				
			}
			
			
			public ArrayList<State> takeAPiece(ArrayList<State> childrenStates, int numPieces){
				ArrayList<State> greedyStates = new ArrayList<State>();
				
				for (State state : childrenStates){
					if(state.board.countPieces()< numPieces){//I can take a piece during this state
						greedyStates.add(state);
					}
				}
				
				return greedyStates;
			}
}
