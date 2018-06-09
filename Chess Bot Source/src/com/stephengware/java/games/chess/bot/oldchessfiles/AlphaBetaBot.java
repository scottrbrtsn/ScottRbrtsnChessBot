package com.stephengware.java.games.chess.bot;
import com.stephengware.java.games.chess.state.State;
import java.util.ArrayList;
import java.util.Iterator;

import com.stephengware.java.games.chess.state.*;


/**
 * This bot performs just as well as {@link MinMaxBot} but expands
 * significantly fewer nodes by intelligently pruning the tree.
 * 
 * @author Stephen G. Ware
 */
public class AlphaBetaBot extends Bot {

	private String myColor;
	
	public AlphaBetaBot() {
		super("srobert6");

		
	}

	@Override
	public State chooseMove(State state) {
		State stateToReturn = new State();
		//root.setValue(Utility.evaluate(root.state));
		myColor = state.player.toString();
		if (myColor == "WHITE"){
			stateToReturn = IDFS(state, myColor);//middleGame

//			
//				if(Openings.scholarsIsOpen(state.board) 
//						&& state.board.countPieces() == 32 ){	//switch to boolean, not turned based	
//					stateToReturn = Openings.scholarsMate(state);	
//				}
//				else if (!Openings.opened(state.board) && state.board.countPieces() == 32 ){//startGame
//					stateToReturn = Openings.scholarsFailed(state);
//				}
//				else{
//						stateToReturn = IDFS(state, myColor);//middleGame
//					}
				
		}else{//I am BLACK			
//			if (state.turn < 6 && state.board.countPieces() == 32){
//				stateToReturn = Defenses.defendTheCenter(state);
//			}else{
			stateToReturn = IDFS(state, myColor);
				//}
		}
		
		return stateToReturn;
	}

	private State IDFS (State root, String myColor){
		State toReturn = new State();
		for(int depth = 2; depth<=4; depth= depth+2){
			GameTree tree = new GameTree(root);
			//GameTree best = new GameTree(root);
			double alpha = Double.NEGATIVE_INFINITY;
			double beta = Double.POSITIVE_INFINITY;
	
			//int depth = 2;
			
				if (myColor == "WHITE"){
				tree = findMax(depth, tree, alpha, beta);//even numbers for depth!
				}else{
					tree = findMin(depth, tree, alpha, beta);
				}
							
				while(tree.state.previous != root){
					if(tree.state.previous != null){
					tree.state = tree.state.previous;
					}else{break;}
				}
				toReturn = tree.state;
		}
				
		return toReturn;
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


		if (depth == 0){return Utility.evaluate(tree);}

		GameTree max = new GameTree(tree.state);
	//	max.parent = tree.parent;

		max.value = Double.NEGATIVE_INFINITY;

		while(tree.hasNextChild() &&  depth>0 && !tree.state.searchLimitReached()) {
			GameTree child = tree.getNextChild();
	//		child.parent = tree;
			child = findMin(depth-1, child, alpha, beta);
			
			if (max.value >= child.value){//if >= then pruned branch will be added...I think
				//max = max;
			}else{
				max = child;
			}
			
			if(max.value >= beta)//if <, works right for depth 2 but not 4
				return max;
			// Update alpha to be the lowest value discovered so far.
			if(alpha > max.value){
				//alpha = alpha;
			}else{
				alpha = max.value;
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
	//	min.parent = tree.parent;
		min.value = Double.POSITIVE_INFINITY;

		while(tree.hasNextChild() &&  depth>0 && !tree.state.searchLimitReached()) {
			GameTree child = tree.getNextChild();
		//	child.parent = tree;
			child = findMax(depth-1, child, alpha, beta);
			
			if(min.value <= child.value){//if <= then pruned branch will be added...I think
				//min = min;
			}else{
				min = child;
			}
						
			if(min.value <= alpha)//if <, works right for depth 2 but not 4
				return min;
			// Update beta to be the highest value discovered so far.
			if(beta < min.value){
				//beta = beta;
			}else{
				beta = min.value;
				}
			
		}
		return min;
	}
	
	
//	private PriorityQueue<State> getChildren(State currentState){			
//		PriorityQueue<State> reorderedMoves = new PriorityQueue<>();
//
//		//next row of possible states
//					Iterator<State> currentIterator = currentState.next().iterator();
//					State toAdd = new State();
//			while(!currentState.searchLimitReached() && currentIterator.hasNext()){
//				toAdd = currentIterator.next();
//				reorderedMoves.push(toAdd, toAdd.board.countPieces() );		
//			}
//			return reorderedMoves;
//	}
}
