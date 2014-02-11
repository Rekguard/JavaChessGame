package Pieces.ChessPieces;

import ChessBoard.BoardPosition;
import ChessBoard.ClickOutline;
import Pieces.ChessPiece;

public class Bishop extends ChessPiece {
	
	ClickOutline [] moveOutline =  new ClickOutline[8];
	
	public Bishop(char colour){
		chessPieceColour = colour;
		chessPieceName = "Bishop";
		
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
		charPosition = this.piecePosition.charAt(0);
		numPosition = Character.getNumericValue(this.piecePosition.charAt(1))-1;
		
		int chaArrayPos = Character.getNumericValue(charPosition) - 10;
		int numArrayPos = numPosition;
			/* Diagonal */
				/* NorthWest */
		for(int i = (chaArrayPos-1), j = (numArrayPos+1); i >= 0 && j <=7 ; i--, j++){
			if (bpShow[i][j].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
			}else{
				break;
			}
		}
		
				/* SouthWest */
		for(int i = (chaArrayPos-1), j = (numArrayPos-1); i >= 0 && j >=0 ; i--, j--){
			if (bpShow[i][j].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
			}else{
				break;
			}
		}
		
				/* NorthEast */
		for(int i = (chaArrayPos+1), j = (numArrayPos+1); i <= 7 && j <=7 ; i++, j++){
			if (bpShow[i][j].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
			}else{
				break;
			}
		}
		
				/* SouthEast */
		for(int i = (chaArrayPos+1), j = (numArrayPos-1); i <= 7 && j >=0 ; i++, j--){
			if (bpShow[i][j].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
			}else{
				break;
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
		
			/* Diagonal */
				/* NorthWest */
		for(int i = (cPosition-1), j = (nPosition+1); i >= 0 && j <=7 ; i--, j++){
			if (bpShow[i][j].isOccupied()
					&& bpShow[i][j].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			}else if (bpShow[i][j].isOccupied()
				&& bpShow[i][j].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
		}
		
				/* SouthWest */
		for(int i = (cPosition-1), j = (nPosition-1); i >= 0 && j >=0 ; i--, j--){
			if (bpShow[i][j].isOccupied()
					&& bpShow[i][j].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			}else if(bpShow[i][j].isOccupied()
					&& bpShow[i][j].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
		}
		
				/* NorthEast */
		for(int i = (cPosition+1), j = (nPosition+1); i <= 7 && j <=7 ; i++, j++){
			if (bpShow[i][j].isOccupied()
					&& bpShow[i][j].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			}else if (bpShow[i][j].isOccupied()
					&& bpShow[i][j].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
		}
		
				/* SouthWest */
		for(int i = (cPosition+1), j = (nPosition-1); i <= 7 && j >=0 ; i++, j--){
			if (bpShow[i][j].isOccupied()
					&& bpShow[i][j].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[i][j].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			}else if(bpShow[i][j].isOccupied()
					&& bpShow[i][j].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
		}
		
	}

	public boolean checkMove(BoardPosition[][] boardPosition) {
		charPosition = this.piecePosition.charAt(0);
		numPosition = Character.getNumericValue(this.piecePosition.charAt(1))-1;
		
		int chaArrayPos = Character.getNumericValue(charPosition) - 10;
		int numArrayPos = numPosition;
		
		if (nPosX < oPosX && nPosY > oPosY){
				/* NorthWest */
			for(int i = (chaArrayPos-1), j = (numArrayPos+1); i >= 0 && j <=7 ; i--, j++){
				if (boardPosition[i][j].isOccupied()){
					break;
				}
				if (boardPosition[i][j].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
					return true;
				}
			}
			return false;
		}else if (nPosX < oPosX && nPosY < oPosY){
				/* SouthWest */
			for(int i = (chaArrayPos-1), j = (numArrayPos-1); i >= 0 && j >=0 ; i--, j--){
				if (boardPosition[i][j].isOccupied()){
					break;
				}
				if (boardPosition[i][j].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
					return true;
				}
			}
			return false;
		}else if (nPosX > oPosX && nPosY > oPosY){
				/* NorthEast */
			for(int i = (chaArrayPos+1), j = (numArrayPos+1); i <= 7 && j <=7 ; i++, j++){
				if (boardPosition[i][j].isOccupied()){
					break;
				}
				if (boardPosition[i][j].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
					return true;
				}
			}
			return false;
		}else if (nPosX > oPosX && nPosY < oPosY){
				/* SouthEast */
			for(int i = (chaArrayPos+1), j = (numArrayPos-1); i <= 7 && j >=0 ; i++, j--){
				if (boardPosition[i][j].isOccupied()){
					break;
				}
				if (boardPosition[i][j].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}
	
}
