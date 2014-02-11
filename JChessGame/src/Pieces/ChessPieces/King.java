package Pieces.ChessPieces;

import ChessBoard.BoardPosition;
import ChessBoard.ClickOutline;
import Pieces.ChessPiece;

public class King extends ChessPiece {
	
	ClickOutline [] moveOutline =  new ClickOutline[1];
	
	public King(char colour){
		chessPieceColour = colour;
		chessPieceName = "King";
		
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
		
			/* Show Horizontal Moves */
				/* East */
		
		if(chaArrayPos < 7){
			if (bpShow[chaArrayPos + 1][numArrayPos].isOccupied() == false){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos + 1][numArrayPos].getLocation());
				moveOutline[0].drawAtLocation();
				
			}
		}
				/* West */
		if(chaArrayPos > 0){
			if (bpShow[chaArrayPos - 1][numArrayPos].isOccupied() == false){
					moveOutline[0].setOutlinePositon(bpShow[chaArrayPos - 1][numArrayPos].getLocation());
					moveOutline[0].drawAtLocation();
			}
		}
		
			/* Show Vertical Moves */
				/* South */
		if(numArrayPos > 0) {
			if (bpShow[chaArrayPos][numArrayPos - 1].isOccupied() == false){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos][numArrayPos - 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
				/* North */
		if(numArrayPos < 7) {
			if (bpShow[chaArrayPos][numArrayPos + 1].isOccupied() == false){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos][numArrayPos + 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
		/* Diagonal */
			/* NorthWest */
		if(numArrayPos < 7 && chaArrayPos > 0) {
			if (bpShow[chaArrayPos - 1][numArrayPos + 1].isOccupied() == false){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos - 1][numArrayPos + 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* SouthWest */
		if(numArrayPos > 0 && chaArrayPos > 0) {
			if (bpShow[chaArrayPos - 1][numArrayPos - 1].isOccupied() == false){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos - 1][numArrayPos - 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* NorthEast */
		if(numArrayPos < 7 && chaArrayPos < 7) {
			if (bpShow[chaArrayPos + 1][numArrayPos + 1].isOccupied() == false){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos + 1][numArrayPos + 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* SouthEast */
		if(numArrayPos > 0 && chaArrayPos < 7) {
			if (bpShow[chaArrayPos + 1][numArrayPos - 1].isOccupied() == false){
				moveOutline[0].setOutlinePositon(bpShow[chaArrayPos + 1][numArrayPos - 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}

		showSurroundings(bpShow,chaArrayPos,numArrayPos);
		
	}
	
	protected void showSurroundings(BoardPosition[][] boardPosition,
			int cPosition, int nPosition) {
		BoardPosition[][] bpShow = boardPosition;
		
		if(this.getPieceColour().equals("White")){
			oppositeColour = "Black";
		}else {
			oppositeColour = "White";
		}
			/* Show Horizontal Moves */
				/* East */
		if(cPosition < 7){
			if (bpShow[cPosition + 1][nPosition].isOccupied() 
					&& bpShow[cPosition + 1][nPosition].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition + 1][nPosition].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
				/* West */
		if(cPosition > 0){
			if (bpShow[cPosition - 1][nPosition].isOccupied() 
					&& bpShow[cPosition - 1][nPosition].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition - 1][nPosition].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* Show Vertical Moves */
				/* South */
		if(nPosition > 0){
			if (bpShow[cPosition][nPosition-1].isOccupied() 
					&& bpShow[cPosition][nPosition-1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition][nPosition-1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
				/* North */
		if(nPosition < 7){
			if (bpShow[cPosition][nPosition+1].isOccupied() 
					&& bpShow[cPosition][nPosition+1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition][nPosition+1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
		/* Diagonal */
			/* NorthWest */
		
		if(cPosition > 0 && nPosition < 7) {
			if (bpShow[cPosition - 1][nPosition + 1].isOccupied() 
					&& bpShow[cPosition-1][nPosition+1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition - 1][nPosition + 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
		
			/* SouthWest */
		if(cPosition > 0 && nPosition > 0) {
			if (bpShow[cPosition - 1][nPosition - 1].isOccupied() 
					&& bpShow[cPosition-1][nPosition-1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition - 1][nPosition - 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* NorthEast */
		if(cPosition < 7 && nPosition < 7) {
			if (bpShow[cPosition + 1][nPosition + 1].isOccupied() 
					&& bpShow[cPosition+1][nPosition+1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition + 1][nPosition + 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
			/* SouthWest */
		if(cPosition < 7 && nPosition > 0) {
			if (bpShow[cPosition + 1][nPosition - 1].isOccupied() 
					&& bpShow[cPosition+1][nPosition-1].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[0].setOutlinePositon(bpShow[cPosition + 1][nPosition - 1].getLocation());
				moveOutline[0].drawAtLocation();
			}
		}
		
	}

	public boolean checkMove(BoardPosition[][] boardPosition) {
			
		if (oPosY < 7
				&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX][oPosY+1].getLocation())
				&& boardPosition[nPosX][nPosY].isOccupied() == false){
				/* North */
			return true;
		}else if (oPosY > 0
				&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX][oPosY-1].getLocation())
				&& boardPosition[nPosX][nPosY].isOccupied() == false) {
				/* South */
			return true;
		}else if (oPosX < 7
				&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX+1][oPosY].getLocation())
				&& boardPosition[nPosX][nPosY].isOccupied() == false) {
				/* East */
			return true;
		}else if (oPosX > 0
				&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX-1][oPosY].getLocation())
				&& boardPosition[nPosX][nPosY].isOccupied() == false) {
				/* West */
			return true;
		}else if (oPosX > 0 && oPosY < 7
					&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX-1][oPosY+1].getLocation())
					&& boardPosition[nPosX][nPosY].isOccupied() == false) {
					/* NorthWest */
				return true;
		}else if (oPosX > 0 && oPosY > 0
				&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX-1][oPosY-1].getLocation())
				&& boardPosition[nPosX][nPosY].isOccupied() == false) {
				/* SouthWest */
			return true;
		}else if (oPosX < 7 && oPosY < 7
				&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX+1][oPosY+1].getLocation())
				&& boardPosition[nPosX][nPosY].isOccupied() == false) {
				/* NorthEast */
			return true;
		}else if (oPosX < 7 && oPosY > 0
				&& boardPosition[nPosX][nPosY].getLocation().equals(boardPosition[oPosX+1][oPosY-1].getLocation())
				&& boardPosition[nPosX][nPosY].isOccupied() == false) {
				/* SouthEast */
			return true;
		}else{
			return false;
		}
	}
}
