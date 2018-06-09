package com.stephengware.java.games.chess.bot;


import com.stephengware.java.games.chess.state.*;



/**
 * A chess bot which selects its next move at random.
 * 
 * @author Scott Robertson
 */
public class Srobert6Bot extends Bot {

		private String myColor;
		
		public Srobert6Bot() {
			super("srobert6");
		
		}

		@Override
		public State chooseMove(State state) {
			State stateToReturn = new State();
			myColor = state.player.toString();
			if (myColor == "WHITE"){
					if(Openings.scholarsIsOpen(state.board) 
							&& state.board.countPieces() == 32 ){	//switch to boolean, not turned based	
						stateToReturn = Openings.scholarsMate(state);	
					}
					else if (!Openings.opened(state.board) && state.board.countPieces() == 32 ){//startGame
						stateToReturn = Openings.scholarsFailed(state);
					}
					else{
							stateToReturn = IDFS(state, myColor);//middleGame
						}
					
			}else{//I am BLACK			
				if (state.turn < 6 && state.board.countPieces() == 32){
					stateToReturn = Defenses.defendTheCenter(state);
				}else{
				stateToReturn = IDFS(state, myColor);
					}
			}
			
			return stateToReturn;
		}

		private State IDFS (State root, String myColor){
			GameTree tree = new GameTree(root);

			for(int depth = 2; depth<=4; depth= depth+2){
				tree = new GameTree(root);//reset the tree
				//AB min/max
					if (myColor == "WHITE"){
					tree = findMax(depth, tree,  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);//even numbers for depth!
					}else{
						tree = findMin(depth, tree,  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
					}
			}
//			//make sure this is a child of the current state	
//			if(tree.parent.state != null){
//				while(tree.parent.state != root ){
//					tree = tree.parent;
//				}
//			}
			return tree.state;
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
		private GameTree findMax(int depth, GameTree tree, double alpha, double beta) {

			//if leaf, evaluate and assign the value to the tree
			//return the node with its new value
			//its parent should already be assigned
			if (depth == 0){return Utility.evaluate(tree);}

			//placeholder for the max node to return
			GameTree max = new GameTree(tree.state);
		//	max.parent = tree;
			max.value = Double.NEGATIVE_INFINITY;
			
			GameTree nextChild = new GameTree(tree.state);//initialize outside of loop to save memory 
			while(tree.hasNextChild() &&  depth>0 && !tree.state.searchLimitReached()) {
			 nextChild = tree.getNextChild();
			//	nextChild.parent = tree;
				nextChild = findMin(depth-1, nextChild, alpha, beta);
				
				if (max.value < nextChild.value){//nextChild is bigger
					max = nextChild;//new maximum node to return
				}else{
				}
				
				if(max.value >= beta)//if <, works right for depth 2 but not 4
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
		private GameTree findMin(int depth, GameTree tree, double alpha, double beta) {
			// This method is simply the opposite of #findMax.

			if (depth == 0){return Utility.evaluate(tree);}

			GameTree min = new GameTree(tree.state);
		//	min.parent = tree;
			min.value = Double.POSITIVE_INFINITY;
			
			GameTree nextChild = new GameTree(tree.state);
			while(tree.hasNextChild() &&  depth>0 && !tree.state.searchLimitReached()) {
				nextChild = tree.getNextChild();
			//	nextChild.parent = tree;
				nextChild = findMax(depth-1, nextChild, alpha, beta);
				
				if(min.value > nextChild.value){//nextChild is smaller
					min = nextChild;
				}else{
				}
							
				if(min.value <= alpha)//if <, works right for depth 2 but not 4
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