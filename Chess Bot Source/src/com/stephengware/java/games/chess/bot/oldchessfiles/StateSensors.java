package com.stephengware.java.games.chess.bot;

import java.util.ArrayList;

import com.stephengware.java.games.chess.state.*;

public class StateSensors {
	
	public StateSensors(){
	}
	
	public static ArrayList<Piece> getPieceLayout(Board board){
	
		ArrayList<Piece> layout = new ArrayList<Piece>();
		Piece p = null;
		for(int i = 0; i<8; i++){ //column, starting with a
			for(int j = 7; j>=0; j--){//row, starting with 8
				p = board.getPieceAt(j, i); 
				if(p!=null){
					layout.add(p);
				}
			}
		}
		return layout;
	}
	
	public static ArrayList<Piece> squaresAroundKing (Board board){
		ArrayList<Piece> layout = new ArrayList<Piece>();
		layout = StateSensors.getPieceLayout (board);
		for (Piece p : layout){
			if (p.getClass().equals(King.class)){
				
			}
		}
		
		return layout;
	}
	
	public static ArrayList<Piece> getRow(Board board, int row){
			ArrayList<Piece> rowToReturn = new ArrayList<Piece>();
			row = row -1; //because of storage convention
		for (int i = 0; i < 8; i++){
			rowToReturn.add(board.getPieceAt(i, row));
		}
			return rowToReturn;
	}
		
	public static ArrayList<Piece> whitePieces (ArrayList<Piece> layout){
		ArrayList<Piece> whitePieces = new ArrayList<Piece>();
		
		for (Piece p : layout){
			if (p.player.toString() == "WHITE"){
				whitePieces.add(p);
			}
		}
		return whitePieces;
	}
	public static ArrayList<Piece> blackPieces (ArrayList<Piece> layout){
		ArrayList<Piece> blackPieces = new ArrayList<Piece>();
		
		for (Piece p : layout){
			if (p.player.toString() == "BLACK"){
				blackPieces.add(p);
			}
		}
		return blackPieces;
	}
	public static int numWhitePieces(ArrayList<Piece> layout){
		int total = 0;
		for (Piece p : layout){
			if (p != null){
				if (p.player.toString() == "WHITE"){
					total++;
				}
			}
		}
		return total;
	}
	
	public static int numBlackPieces(ArrayList<Piece> layout){
		int total = 0;
		for (Piece p : layout){
			if (p != null){
				if (p.player.toString() == "BLACK"){
					total++;
				}
			}
		}
		return total;
	}
	public static boolean isQueenMissing(ArrayList<Piece> layout, String player){
		boolean isMissing = true;
		for (Piece p : layout){
			 if(p != null){
			  if (p.getClass() == Queen.class &&
					  p.player.toString()== player){
				  isMissing = false;
			  }
			 }
		}
		return isMissing;
	}
	public static int numRooks(ArrayList<Piece> layout, String player){
		int num = 0;
		for (Piece p : layout){
			 if(p != null){
			  if (p.getClass() == Rook.class &&
					  p.player.toString()== player){
				  num++;
			  }
			 }
		}
		return num;
	}
	
	public static int numKnights(ArrayList<Piece> layout, String player){
		int num = 0;
		for (Piece p : layout){
			 if(p != null){
			  if (p.getClass() == Knight.class &&
					  p.player.toString()== player){
				  num++;
			  }
			 }
		}
		return num;
	}
	
	public static Piece getWhiteQueen(ArrayList<Piece> layout){
		Piece queen = null;
		
		for (Piece p : layout){
			if (p != null){
				if (p.getClass() == Queen.class && p.player.toString() == "WHITE"){
					queen = p;
				}
			}
		}
		
		return queen;
	}
	
	public static ArrayList<Piece> getBishops (ArrayList<Piece> layout, String player){
		ArrayList <Piece> bishops = new ArrayList<> ();
		for (Piece p : layout){
			if (p != null){
				if (p.getClass() == Bishop.class && p.player.toString() == player){
					bishops.add(p);
				}
			}
		}
		return bishops;
	}
	
	public static int numBishops(ArrayList<Piece> layout, String player){
		int num = 0;
		for (Piece p : layout){
			 if(p != null){
			  if (p.getClass() == Bishop.class &&
					  p.player.toString()== player){
				  num++;
			  }
			 }
		}
		return num;
	}
	
	public static int numPawns(ArrayList<Piece> layout, String player){
		int num = 0;
		for (Piece p : layout){
			 if(p != null){
			  if (p.getClass() == Pawn.class &&
					  p.player.toString()== player){
				  num ++;
			  }
			 }
		}
		return num;
		
	}
	
}
