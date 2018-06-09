package com.stephengware.java.games.chess.bot;


import java.util.Iterator;

import com.stephengware.java.games.chess.state.*;



/**
 * A chess bot which selects its next move using the Utility Function
 * 
 * @author Scott Robertson
 */
public class Srobert6BotTrimmed extends Bot {
	
		public Srobert6BotTrimmed() {
			super("srobert6");
		
		}

		@Override
		public State chooseMove(State state) {
			return IDFS (state, state.player.toString());
		}

		private State IDFS (State root, String myColor){
			GameTree toReturn = null;
			Iterator<Piece> it = null;
		
			for(int depth = 2; depth<=4; depth= depth+2){
				toReturn = new GameTree(root);//reset the tree
				//AB min/max
					if (myColor == "WHITE"){
				toReturn = findMax(it, depth, toReturn,  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);//even numbers for depth!
						//set value of toReturn to max double, 
						//if less than toReturn, toReturn = min
			
					}else{
						toReturn = findMin(it, depth, toReturn,  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
					}
			}
			Iterator <State> currentIterator = root.next().iterator();
			GameTree nextChild = new GameTree(root);

				while(toReturn.state.previous != root){
					toReturn.state = toReturn.state.previous;
					if(toReturn.state.previous == null){//rare bug fix
						nextChild = new GameTree(currentIterator.next());
						return nextChild.state;
					}
				}
				return toReturn.state;
			
	}
		
		
		/**
		 * Given a {@link GameTree} node, expand its children (if any) to find the
		 * node with the highest minimum utility value.
		 * 
		 * @param tree the node whose children need to be expanded
		 * @param alpha the highest utility value discovered so far in this branch of the tree (i.e. best for X)
		 * @param beta the lowest utility value discovered so far in this branch of the tree (i.e. best for O)
		 * @return the utility value of the node with the highest minimum utility
		 */
		private GameTree findMax(Iterator<Piece> it , int depth, GameTree tree, double alpha, double beta) {

			//if leaf, evaluate and assign the value to the tree
			//return the node with its new value
			//its parent should already be assigned
			if (depth == 0){return Utility.evaluate(tree, it);}

			//placeholder for the max node to return
			GameTree max = new GameTree(tree.state);
			max.value = Double.NEGATIVE_INFINITY;
			
			GameTree nextChild = new GameTree(tree.state);//initialize outside of loop to save memory 
			while(tree.hasNextChild() &&  depth>0 && !tree.state.searchLimitReached()) {
			 nextChild = tree.getNextChild();
			 if(tree.state.searchLimitReached()){return max;}
				nextChild = findMin(it, depth-1, nextChild, alpha, beta);
				
				if (max.value < nextChild.value){//nextChild is bigger
					max = nextChild;//new maximum node to return
				}else{
				}
				
				if(max.value >= beta)//value is higher than or equal to beta so discard and return
					return max;
				// Update alpha to be the lowest value discovered so far.
				if(alpha < max.value){
					alpha = max.value;
				}else{
					}
				
			}
			return max;
		}
		
		/**
		 * Given a {@link GameTree} node, expand its children (if any) to find the
		 * node with the lowest maximum utility value.
		 * 
		 * @param tree the node whose children need to be expanded
		 * @param alpha the highest utility value discovered so far in this branch of the tree (i.e. best for X)
		 * @param beta the lowest utility value discovered so far in this branch of the tree (i.e. best for O)
		 * @return the utility value of the node with the lowest maximum utility
		 */
		private GameTree findMin(Iterator<Piece> it, int depth, GameTree tree, double alpha, double beta) {
			// This method is simply the opposite of #findMax.

			if (depth == 0){return Utility.evaluate(tree, it);}

			GameTree min = new GameTree(tree.state);
			min.value = Double.POSITIVE_INFINITY;
			
			GameTree nextChild = new GameTree(tree.state);
			while(tree.hasNextChild() &&  depth>0 && !tree.state.searchLimitReached()) {
				 if(tree.state.searchLimitReached()){return min;}

				nextChild = tree.getNextChild();
				nextChild = findMax(it, depth-1, nextChild, alpha, beta);
				
				if(min.value > nextChild.value){//nextChild is smaller
					min = nextChild;
				}else{
				}
							
				if(min.value <= alpha) //value is lesser than or equal to alpha so discard and return
					return min;
				// Update beta to be the highest value discovered so far.
				if(beta > min.value){
					beta = min.value;
				}else{
					}
				
			}
			return min;
		}
}
