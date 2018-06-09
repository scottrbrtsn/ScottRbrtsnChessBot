package com.stephengware.java.games.chess.bot;


import java.util.ArrayList;
import java.util.Iterator;

import com.stephengware.java.games.chess.state.*;



/**
 * A chess bot which selects its next move at random.
 * 
 * @author Scott Robertson
 */
public class Srobert6BotNoNode extends Bot {
	private MinPriorityQueue<GameTree> nextMovesBlack; 
	private MaxPriorityQueue<GameTree> nextMovesWhite;
	
		public Srobert6BotNoNode() {
			super("srobert6");
		
		}

		@Override
		public State chooseMove(State state) {
			return IDFS (state, state.player.toString());
		}

		private State IDFS (State root, String myColor){
			GameTree temp = new GameTree(root);
			GameTree toReturn = temp;
			Iterator<Piece> it = null;

			//gather all the possible next moves
			//reorder in a priority queue to consider best moves first.
			setupChildren(it, root, myColor, toReturn);
	
	//To consider best moves first.
    //Flip min and max, for black and white, and for all children, run the loop below
			for(int depth = 1; depth<=15; depth= depth+2){
				toReturn = new GameTree(root);//reset the tree
				//AB min/max
					if (myColor == "WHITE"){
						//set value of toReturn to max double, 
						toReturn.value = Double.NEGATIVE_INFINITY;
						//if less than toReturn, toReturn = min
						
						for (GameTree childNode: nextMovesWhite){
							temp = findMin(it, depth, childNode,  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);//even numbers for depth!
							if (temp.value > toReturn.value)
							{
								toReturn = childNode;
							}
						}
						
					}else{
					//	toReturn = findMin(depth, tree,  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
						
						
						//set value of toReturn to min double, 
						toReturn.value = Double.POSITIVE_INFINITY;
						//if greater than toReturn, toReturn = max
						for (GameTree childNode: nextMovesBlack){
						temp = findMax(it, depth, childNode,  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);//even numbers for depth!
						if (temp.value < toReturn.value)
						{
							toReturn = childNode;
						}
						}
					//if tree.state.value > min.value, min.value = tree.state.value
					}
			}
				
			return toReturn.state;
		}
		/**
		 * reorder moves so that best/greedy moves are considered first
		 */
		private void setupChildren(Iterator<Piece> it, State root, String myColor, GameTree tree){
		nextMovesBlack = new MinPriorityQueue<>(); 
		nextMovesWhite = new MaxPriorityQueue<>(); 

		Iterator <State> currentIterator = root.next().iterator();
		GameTree nextChild = new GameTree(root);
				while(!root.searchLimitReached() && currentIterator.hasNext()){
					nextChild = new GameTree(currentIterator.next());
					nextChild = Utility.evaluate(nextChild, it);
					if (myColor == "WHITE"){
						nextMovesWhite.push(nextChild, nextChild.value);
					}else{
						nextMovesBlack.push(nextChild, nextChild.value);
					}
				}

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
		private GameTree findMax(Iterator<Piece> it, int depth, GameTree tree, double alpha, double beta) {

			//if leaf, evaluate and assign the value to the tree
			//return the node with its new value
			//its parent should already be assigned
			if (depth == 0){return Utility.evaluate(tree, it);}

			//placeholder for the max node to return
			GameTree max = new GameTree(tree.state);
			max.value = Double.NEGATIVE_INFINITY;
			
			//trim gametree down?, and add an iterator here
			GameTree nextChild = new GameTree(tree.state);//initialize outside of loop to save memory 
			while(tree.hasNextChild() &&  depth>0 && !tree.state.searchLimitReached()) {
			 nextChild = tree.getNextChild();
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