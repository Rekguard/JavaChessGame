package ChessBoard;

import Pieces.ChessPiece;

public class BoardPosition {

	private int numberBoardPosition;
	private char letterBoardPosition;
	
	public String pos;
	
	public ChessPiece chessPiece;
	
	public BoardPosition(char letterPosition, int numberPosition) {
		letterBoardPosition = letterPosition;
		numberBoardPosition = numberPosition;
		
		pos = "" + letterPosition + numberPosition;
	}

	public String getLocation(){
		
		String s = "" + letterBoardPosition + numberBoardPosition;
		
		return s;
	}

	public void getOccupationStatus() {
		if (chessPiece == null){
			System.out.println("The current spot you selected: " 
								+ getLocation() + " is not occupied!" ); 
		}else{
			System.out.println("The current spot you selected: " 
					+ getLocation() + " is currently occupied by: " 
						+ chessPiece.getPieceColour() + " " + chessPiece.getPieceName());
		}
	}
	
	public boolean isOccupied(){
		if(chessPiece == null){
			return false;
		}else{
			return true;
		}
	}
	
	
	public void setOccupiedPiece(ChessPiece cp){
		chessPiece = cp;
	}
}
