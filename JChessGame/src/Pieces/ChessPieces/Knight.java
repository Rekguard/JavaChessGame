package Pieces.ChessPieces;

import ChessBoard.BoardPosition;
import ChessBoard.ClickOutline;
import Pieces.ChessPiece;

public class Knight extends ChessPiece {
	
	ClickOutline [] moveOutline =  new ClickOutline[1];

	public Knight(char colour){
		chessPieceColour = colour;
		chessPieceName = "Knight";
		for (int i = 0; i < moveOutline.length;i++){
			moveOutline[i] = new ClickOutline("Orange");
		}
	}
 
	public void loadOutlineTexture() {
		for (int i = 0; i < moveOutline.length;i++){
			moveOutline[i].loadTexture();
		}
	}

	public void showMove(BoardPosition[][] boardPosition) {
		charPosition = this.piecePosition.charAt(0);
		numPosition = Character.getNumericValue(this.piecePosition.charAt(1));
		
		showLegalMoves(boardPosition,charPosition,numPosition);	
	}
	
	protected void showLegalMoves(BoardPosition[][] boardPosition,
			char characterPosition, int numberPosition) {
		BoardPosition[][] bpShow = boardPosition;
		int chaArrayPos = Character.getNumericValue(characterPosition) - 10;
		int numArrayPos = numberPosition - 1;
		
		int farLD = 0;		// Far Left side and Down have the same value
		int clsLD = 1;		// Close Left side and Down have the same value
		int clsRU = 6;		// Close Right side and Up have the same value
		int farRU = 7;		// Far Right side and Up have the same value
		
		
		if(this.getPieceColour().equals("White")){
			oppositeColour = "Black";
		}else {
			oppositeColour = "White";
		}
		
		
			/* NNW */
		if(chaArrayPos > farLD && numArrayPos < clsRU){
			if(bpShow[chaArrayPos-1][numArrayPos+2].isOccupied() == false
					|| bpShow[chaArrayPos-1][numArrayPos+2].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos-1][numArrayPos+2].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* NWW */
		if(chaArrayPos > clsLD && numArrayPos < farRU){
			if(bpShow[chaArrayPos-2][numArrayPos+1].isOccupied() == false
					|| bpShow[chaArrayPos-2][numArrayPos+1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos-2][numArrayPos+1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* SWW */
		if(chaArrayPos > clsLD && numArrayPos > farLD){
			if(bpShow[chaArrayPos-2][numArrayPos-1].isOccupied() == false
					|| bpShow[chaArrayPos-2][numArrayPos-1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos-2][numArrayPos-1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* SSW */
		if(chaArrayPos > farLD && numArrayPos > clsLD){
			if(bpShow[chaArrayPos-1][numArrayPos-2].isOccupied() == false
					|| bpShow[chaArrayPos-1][numArrayPos-2].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos-1][numArrayPos-2].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* SSE */
		if(chaArrayPos < farRU && numArrayPos > clsLD){	
			if(bpShow[chaArrayPos+1][numArrayPos-2].isOccupied() == false
					|| bpShow[chaArrayPos+1][numArrayPos-2].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos+1][numArrayPos-2].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* SEE */
		if(chaArrayPos < clsRU && numArrayPos > farLD){
			if(bpShow[chaArrayPos+2][numArrayPos-1].isOccupied() == false
					|| bpShow[chaArrayPos+2][numArrayPos-1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos+2][numArrayPos-1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* NEE */
		if( chaArrayPos < clsRU && numArrayPos < farRU){
			if(bpShow[chaArrayPos+2][numArrayPos+1].isOccupied() == false
					|| bpShow[chaArrayPos+2][numArrayPos+1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos+2][numArrayPos+1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* NNE */
		if( chaArrayPos < farRU && numArrayPos < clsRU){
			if(bpShow[chaArrayPos+1][numArrayPos+2].isOccupied() == false
					|| bpShow[chaArrayPos+1][numArrayPos+2].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos+1][numArrayPos+2].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
	}
	
	protected void showSurroundings(BoardPosition[][] boardPosition,
			int cPosition, int nPosition) {
		System.out.println("Unneeded method");
	}

	public boolean checkMove(BoardPosition[][] boardPosition) {
		
		BoardPosition[][] bpShow = boardPosition;
		charPosition = this.piecePosition.charAt(0);
		numPosition = Character.getNumericValue(this.piecePosition.charAt(1))-1;
		int chaArrayPos = Character.getNumericValue(charPosition) - 10;
		int numArrayPos = numPosition - 1;
		
			/* The following variables are used to set out a perimeter
			 * that checks how close the knight is to the edge of the 
			 * board.
			 * 
			 * LD stands for Left/Down.
			 * RU stands for Right/Up.
			 * 
			 */
		
		int farLD = 0;		// Far Left side and Down have the same value
		int clsLD = 1;		// Close Left side and Down have the same value
		int clsRU = 6;		// Close Right side and Up have the same value
		int farRU = 7;		// Far Right side and Up have the same value
		
		
		if(this.getPieceColour().equals("White")){
			oppositeColour = "Black";
		}else {
			oppositeColour = "White";
		}
		
			/* NNW */
		if(chaArrayPos > farLD && numArrayPos < clsRU){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX-1][oPosY+2].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
		/* NWW */
		if(chaArrayPos > clsLD && numArrayPos < farRU){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX-2][oPosY+1].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
			/* SWW */
		if(chaArrayPos > clsLD && numArrayPos > farLD){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX-2][oPosY-1].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
			/* SSW */
		if(chaArrayPos > farLD && numArrayPos > clsLD){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX-1][oPosY-2].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
		/* SSE */
		if(chaArrayPos < farRU && numArrayPos > clsLD){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX+1][oPosY-2].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
			/* SEE */
		
		if(chaArrayPos < clsRU && numArrayPos > farLD){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX+2][oPosY-1].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
			/* NEE */
		if( chaArrayPos < clsRU && numArrayPos < farRU){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX+2][oPosY+1].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
			/* NNE */
		if( chaArrayPos < farRU && numArrayPos < clsRU){
			if(bpShow[nPosX][nPosY].getLocation().equals(bpShow[oPosX+1][oPosY+2].getLocation())
					&& bpShow[nPosX][nPosY].isOccupied()== false){
				return true;
			}
		}
		
		/* If nothing no move is available return false */
		return false;
	}
}
