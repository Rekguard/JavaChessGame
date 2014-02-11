package Pieces.ChessPieces;

import ChessBoard.BoardPosition;
import ChessBoard.ClickOutline;
import Pieces.ChessPiece;

public class Queen extends ChessPiece {
	
	ClickOutline [] moveOutline =  new ClickOutline[8];

	public Queen(char colour){
		chessPieceColour = colour;
		chessPieceName = "Queen";
		
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
		for(int i = (chaArrayPos-1); i >= 0; i--){
			if (bpShow[i][numArrayPos].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[i][numArrayPos].getLocation());
				moveOutline[i].drawAtLocation();
			}else{
				break;
			}
		}
				/* West */
		for(int i = (chaArrayPos+1); i < 8; i++){
			if (bpShow[i][numArrayPos].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[i][numArrayPos].getLocation());
				moveOutline[i].drawAtLocation();
			}else{
				break;
			}
		}
		
			/* Show Vertical Moves */
				/* South */
		for(int i = (numArrayPos-1); i >= 0; i--){
			if (bpShow[chaArrayPos][i].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[chaArrayPos][i].getLocation());
				moveOutline[i].drawAtLocation();
			}else{
				break;
			}
		}
				/* North */
		for(int i = (numArrayPos+1); i < 8; i++){
			if (bpShow[chaArrayPos][i].isOccupied() == false){
				moveOutline[i].setOutlinePositon(bpShow[chaArrayPos][i].getLocation());
			moveOutline[i].drawAtLocation();
			}else{
				break;
			}
		}
		
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
		
			/* Show Horizontal Moves */
				/* Left */
		for(int i = (cPosition-1); i >= 0; i--){
			if (bpShow[i][nPosition].isOccupied() 
					&& bpShow[i][nPosition].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[i][nPosition].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			} else if (bpShow[i][nPosition].isOccupied() 
					&& bpShow[i][nPosition].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
		}
				/* Right */
		for(int i = (cPosition+1); i < 8; i++){
			if (bpShow[i][nPosition].isOccupied()
					&& bpShow[i][nPosition].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[i][nPosition].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			} else if (bpShow[i][nPosition].isOccupied()
					&& bpShow[i][nPosition].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
		}
		
			/* Show Vertical Moves */
				/* South */
		for(int i = (nPosition-1); i >= 0; i--){
			if (bpShow[cPosition][i].isOccupied()
					&& bpShow[cPosition][i].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[cPosition][i].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			}else if(bpShow[cPosition][i].isOccupied()
					&& bpShow[cPosition][i].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
		}
		
				/* North */
		for(int i = (nPosition+1); i < 8; i++){
			
			if (bpShow[cPosition][i].isOccupied()
					&& bpShow[cPosition][i].chessPiece.getPieceColour().equals(oppositeColour)){
				moveOutline[i].setOutlinePositon(bpShow[cPosition][i].getLocation());
				moveOutline[i].drawAtLocation();
				break;
			}else if(bpShow[cPosition][i].isOccupied() 
					&& bpShow[cPosition][i].chessPiece.getPieceColour().equals(this.getPieceColour())){
				break;
			}
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
			/* Local Variables*/
		
		charPosition = this.piecePosition.charAt(0);
		numPosition = Character.getNumericValue(this.piecePosition.charAt(1))-1;
		
		int chaArrayPos = Character.getNumericValue(charPosition) - 10;
		int numArrayPos = numPosition;
		
				/* North */
		
			if (nPosX == oPosX && nPosY > oPosY){
					/* North */
				for(int i = (numArrayPos+1); i < 8; i++){
					if (boardPosition[nPosX][nPosY].isOccupied() || boardPosition[oPosX][i].isOccupied()){
						break;
					}
					if (boardPosition[oPosX][i].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
						return true;
					}
				}
				return false;
			}else if (nPosX == oPosX && nPosY < oPosY){
					/* South */
				for(int i = (numArrayPos-1); i >= 0; i--){
					if (boardPosition[nPosX][nPosY].isOccupied() || boardPosition[oPosX][i].isOccupied()){
						break;
					}
					if (boardPosition[oPosX][i].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
						return true;
					}
				}
				return false;
			}else if (nPosY == oPosY && nPosX > oPosX) {
					/* East */
				for(int i = (chaArrayPos+1); i < 8; i++){
					if (boardPosition[nPosX][nPosY].isOccupied() || boardPosition[i][oPosY].isOccupied()){
						break;
					}
					if (boardPosition[i][oPosY].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
						return true;
					}
				}
				return false;
			}else if (nPosY == oPosY && nPosX < oPosX) {
					/* West */
				for(int i = (chaArrayPos-1); i >= 0; i--){
					if (boardPosition[nPosX][nPosY].isOccupied() || boardPosition[i][oPosY].isOccupied()){
						System.out.println("Waffle");
						break;
					}
					if (boardPosition[i][oPosY].getLocation().equals(boardPosition[nPosX][nPosY].getLocation())){
						System.out.println("Syrup");
						return true;
					}
				}
				System.out.println("Pancake");
				return false;
			}else if (nPosX < oPosX && nPosY > oPosY){
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
