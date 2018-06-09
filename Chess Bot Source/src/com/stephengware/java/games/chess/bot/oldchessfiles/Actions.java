package com.stephengware.java.games.chess.bot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.stephengware.java.games.chess.bot.*;
import com.stephengware.java.games.chess.state.*;
import java.util.Comparator;
import java.util.HashSet;




public class Actions{
	
		private Random random;
		private StateSensors currentSensors;
		private StateSensors oppSensors;
		private StateSensors myNextTurnSensors;


		public Actions(){
			this.random = new Random(0);
			this.currentSensors = new StateSensors();
			this.oppSensors = new StateSensors();
			this.myNextTurnSensors = new StateSensors();

		}
		
		
//		public State search(State currentState){
//		//	PriorityQueue<State> frontier = new PriorityQueue<State>();
////			frontier.push(currentState, 0);
////		//	currentSensors = currentSensors.setupStateDetails(currentState);
////			
////			State current = frontier.pop();
////	
////				for(State child : current.next()){ //list of other player's turn states
////					frontier.push(child, minimax(child, 2, true));//or false?  not sure
////				}
////		
////			return frontier.pop();
//		}
		
		public int minimax(State node, int depth, Boolean maximizingPlayer){
			int bestValue = Integer.MAX_VALUE;
			int v = 0;
		     if (depth == 0 || node.over){
		         return getH(node);
		     }

		     if (maximizingPlayer){
		         bestValue = Integer.MIN_VALUE;
		         for(State child : node.next()){
		             v = minimax(child, depth--, !maximizingPlayer);
		             bestValue = Math.max(bestValue, v);
		         }
		         return bestValue;
		     }else {//minimizing player
		         bestValue = Integer.MAX_VALUE;
		         for (State child : node.next()){ 
		             v = minimax(child, depth--, maximizingPlayer);
		             bestValue = Math.min(bestValue, v);
		         }
		     }
		         return bestValue;
		}
		
		private int getH(State state){
			int h = 0;
//			h = h + gameover();
//			h = h + myKingStatus();
//			h = h + oppKingStatus();
//			
//			h = h + myPieces();
//			h = h + opponentsPieces();
			
			
			
			
			return h;
		}
		
		private int details(State state) { 
			//queen(9) + rookX2(5), knightX2(4), bishopX2(3), 
	//		oppSensors = oppSensors.setupStateDetails(state);
			Piece myKing = state.board.getKing(state.player);

			int h = 0;
			if(state.over){
				return 0;
			}
			//did my king move?
			if(myKing.rank == 0){
				h = h-10000;
			}
			if(state.check){h = h - 3;}
			//taken pieces?
//			if(oppSensors.myNumRooks == 0){h = h - 10;}
//			if(oppSensors.myQueenIsMissing){h = h - 9;}
//			if(oppSensors.myNumKnights == 0){h = h - 8;}
//			if(oppSensors.myNumBishops == 0){h = h - 6;}
//			if(oppSensors.myNumRooks == 1){h = h - 5;}
//			if(oppSensors.myNumKnights ==1){h = h - 4;}
//			if(oppSensors.myNumBishops ==1){h = h - 3;}
//			h = h + oppSensors.myNumPawns;
			
//			//control of the center?
//			if (oppSensors.row4.get(3) != null){
//				if (oppSensors.row4.get(3).player.toString() == currentSensors.myColor){
//					h--;
//				}
//			}
//			if (oppSensors.row4.get(4) != null){
//				if (oppSensors.row4.get(4).player.toString() == currentSensors.myColor){
//					h--;
//				}
//			}
//			if (oppSensors.row5.get(3) != null){
//				if (oppSensors.row5.get(3).player.toString() == currentSensors.myColor){
//					h--;
//				}
//			}
//			if (oppSensors.row5.get(4) != null){
//				if (oppSensors.row5.get(4).player.toString() == currentSensors.myColor){
//					h--;
//				}
//			}

			//h = h - myNextTurnFeedback(state);
			
			
			return h;
		}
		
		private int myNextTurnFeedback(State state){
			int x = 0;
			
					for(State child : state.next()){ //list of my next turn states
						//taken pieces?
//				myNextTurnSensors = myNextTurnSensors.setupStateDetails(child);
//						if(myNextTurnSensors.myNumRooks == 0){x = x + 2000;}
//						if(myNextTurnSensors.myQueenIsMissing){x = x + 1900;}
//						if(myNextTurnSensors.myNumKnights == 0){x = x + 1800;}
//						if(myNextTurnSensors.myNumBishops == 0){x = x + 1600;}
//						if(myNextTurnSensors.myNumRooks == 1){x = x + 1500;}
//						if(myNextTurnSensors.myNumKnights ==1){x = x + 1400;}
//						if(myNextTurnSensors.myNumBishops ==1){x = x + 1300;}
//						x = x - myNextTurnSensors.myNumPawns;
					}
			return x;
		}
		
		
		public State randomMove(ArrayList<State> states){
			return states.get(random.nextInt(states.size()));
		}
	
		
		public class StateComparator implements Comparator<State>
		{
		    @Override
		    public int compare(State x, State y)
		    {
		        // Assume neither string is null. Real code should
		        // probably be more robust
		        // You could also just return x.length() - y.length(),
		        // which would be more efficient.
		        if (y.over)//y is better
		        {
		            return -1;
		        }
		        if (x.over)//x is better
		        {
		            return 1;
		        }
		        return 0;//they are equal:  tie-breaker
		    }
		}
}
