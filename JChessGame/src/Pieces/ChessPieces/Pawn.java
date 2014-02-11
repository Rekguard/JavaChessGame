package Pieces.ChessPieces;


import ChessBoard.BoardPosition;
import ChessBoard.ClickOutline;
import Pieces.ChessPiece;

public class Pawn extends ChessPiece {
	
		// Object reference for drawing the outlines around the box
		// 4 are created.
		// 1: Space infront.
		// 2: Space two spots ahead if firstmove = 0.
		// 3 and 4: are for the "shoulder" spots that the pawn might be able to take.
	ClickOutline [] pawnMoveOutline = new ClickOutline [4];
	
	public Pawn(char colour){
			// Inherited from the ChessPiece class
		chessPieceColour = colour;	// Defines between Black and White
			// For Purposes of knowing the name of the piece in a spot
		chessPieceName = "Pawn";	
		
			// Creates the actual objects for Outline Reference
		for (int i = 0; i < pawnMoveOutline.length;i++){
			pawnMoveOutline[i] = new ClickOutline("Orange");
		}		
	}
	
	
		
		// This Method is called in an outside class to load the textures to be viewed
	public void loadOutlineTexture(){
		for (int i = 0; i < pawnMoveOutline.length;i++){
			
			pawnMoveOutline[i].loadTexture();
		}
	}
	
		// The following Method is called outside of the class to show a pieces legal moves.
		/* 
		 * This is the encumbered method 
		 */
	public void showMove(BoardPosition[][] boardPosition){
		
		charPosition = this.piecePosition.charAt(0);
		numPosition = Character.getNumericValue(this.piecePosition.charAt(1));
	
			/*
			 *  The Board position argument takes the board grid I have set up to hold the positions
			 *  of all the pieces. 
			 *  
			 *  BoardPosition class has an object reference chessPiece
			 *  
			 *  If a spot is occupied boardPosition[x][y].chessPiece.isOccupied() will return true.
			 *  
			 */
			
			/*
			 * Each ChessPiece has a variable piecePosition which stores the pieces position for
			 * the purpose of working out where it exists on the board. 
			 * 
			 * This piece Position is a String with two Values eg. "A2"
			 * Its then broken down into two variables:
			 * A Char and an Int by the following two methods
			 * 
			 */
		
		/*
		 * The Piece Colour is defined through the parent class in an array of ChessPieces
		 */
		
		if(getPieceColour().equals("White")){
		showWhiteMoves(boardPosition,charPosition,numPosition);
		}
		
		if(getPieceColour().equals("Black")){
		showBlackMoves(boardPosition,charPosition,numPosition);
		}
		
	}
	
		/*
		 * To break down the method into smaller chunks I've created the following methods
		 */
		// Show Whites legal moves
	protected void showWhiteMoves(BoardPosition [][] boardPosition, char characterPosition, int numberPosition ){
		
			/*
			 * The following variable is used to work out if any piece is blocking the pawns path
			 * It is also used in the later method for "checking the pawns surroundings"
			 */
		BoardPosition[][] bpWShow = boardPosition;
		
			/*
			 * I convert the arguments characterPosition and numberPosition into ints so they can be
			 * fed properly into the BoardPosition Array
			 * 
			 * The char has the wrong numeric value.
			 * NumArray is just a redundant variable now that I look at it
			 */
		
		
		int chaArrayPos = Character.getNumericValue(characterPosition) - 10;
		int numArrayPos = numberPosition - 1;
		
			/*
			 * These two string variables convert the char and num combo into a string so that 
			 * the Outline class can draw the highlight box properly
			 * 
			 * ClickOutline.setOutline(String position)
			 * 		reads a string variable in, breaks it down and assigns it to the object
			 * 		then the following method draws it
			 * 
			 */
		
		String legalWhitePosition2 = "" + (characterPosition) + (numberPosition + 2);
	
		String legalWhitePosition1 = "" + (characterPosition) + (numberPosition + 1);
				// If the space infront is not occupied draw legalmove
		
		if (numArrayPos <= 7){
			if (bpWShow[chaArrayPos][numArrayPos+1].isOccupied() == false){
			pawnMoveOutline[0].setOutlinePositon(legalWhitePosition1);
			pawnMoveOutline[0].drawAtLocation();
			}
				// If Pawns first move, draw the secondary move
			if (moveCount == 0
					&& bpWShow[chaArrayPos][numArrayPos+2].isOccupied() == false ){
			pawnMoveOutline[1].setOutlinePositon(legalWhitePosition2);
			pawnMoveOutline[1].drawAtLocation();
			}
				// This method now checks for legal "Take" moves, or whatever you would call that move
			showWhiteSurroundings(boardPosition, characterPosition, numberPosition);
		}
	}
	
		/*
		 * The following method will be similar to the previous method
		 * however variables will be changed for Black pieces
		 * 
		 */
	
	protected void showBlackMoves(BoardPosition [][] boardPosition, char characterPosition, int numberPosition){
		
		BoardPosition[][] bpBShow = boardPosition;
		
		int chaArrayPos = Character.getNumericValue(characterPosition) - 10;
			// The array integer needs to be two behind as the array is zero based
		int numArrayPos = numberPosition - 2;
		
		String legalBlackPosition1 = "" + (characterPosition) + (numberPosition - 1);
		String legalBlackPosition2 = "" + (characterPosition) + (numberPosition - 2);
		
		if(numArrayPos >= 0){
			if (bpBShow[chaArrayPos][numArrayPos].isOccupied() == false){
				pawnMoveOutline[0].setOutlinePositon(legalBlackPosition1);
				pawnMoveOutline[0].drawAtLocation();
			}
			
			if (moveCount == 0){
				pawnMoveOutline[1].setOutlinePositon(legalBlackPosition2);
				pawnMoveOutline[1].drawAtLocation();
			}
			
			showBlackSurroundings(boardPosition, characterPosition, numberPosition);
		}
			
	}
	
		/*
		 * Check surroundings method for checking if there are any pieces that can be taken by a pawn
		 * 
		 */
	
	protected void showWhiteSurroundings(BoardPosition[][] boardPositon, int cPosition, int nPosition){
		BoardPosition [][] bpCheckSur = boardPositon;
		
		// White Variables
			// This Variable is just the pieces position +1 in front
		int wRowPosition = nPosition+1;
		
			// Right Shoulder Variables
				// The char value is needed for the actual drawing of the orange box
		char wRightShoulderPosition = (char) (cPosition +1);
				// The integer value is needed for feeding the right position into the BoardPosition Array
		int wRSIntegerValue = Character.getNumericValue(cPosition) - 9;
		
			// Left Shoulder Variables
				// Same case as the right Shoulder variables
		char wLeftShoulderPosition = (char) (cPosition -1);
		int wLSIntegerValue = Character.getNumericValue(cPosition) - 11;
		
			// String White Legal Moves
				// This is where the char values are needed to make the string position properly
		String wLegalRSMove = "" + wRightShoulderPosition + wRowPosition;
		String wLegalLSMove = "" + wLeftShoulderPosition + wRowPosition;
		
		// Logic statements
				// To prevent an ArrayOutOfBounds Exception and because there are no positions pass H
			if (cPosition != 'H'){
				/*
				 * Firstly check if the position on the right shoulder is occupied
				 * and
				 * Secondly check if the piece colour is black
				 * 
				 * If both conditions are true then draw an orange box around that square
				 */
				if (bpCheckSur[wRSIntegerValue][(nPosition)].isOccupied() &&
						bpCheckSur[wRSIntegerValue][(nPosition)].chessPiece.getPieceColour().equals("Black")){
					pawnMoveOutline[2].setOutlinePositon(wLegalRSMove);
					pawnMoveOutline[2].drawAtLocation();
				}
			}
			// To prevent an ArrayOutOfBounds Exception and because there are no positions below A
			if (cPosition != 'A'){
				/*
				 * Firstly check if the position on the left shoulder is occupied
				 * and
				 * Secondly check if the piece colour is black
				 * 
				 * If both conditions are true then draw an orange box around that square
				 */
				if (bpCheckSur[wLSIntegerValue][(nPosition)].isOccupied() &&
						bpCheckSur[wLSIntegerValue][(nPosition)].chessPiece.getPieceColour().equals("Black")){
					pawnMoveOutline[2].setOutlinePositon(wLegalLSMove);
					pawnMoveOutline[2].drawAtLocation();
				}
			}
		
	}
	
	protected void showBlackSurroundings(BoardPosition[][] boardPositon, int cPosition, int nPosition){
		
			/* Local Variables*/
		BoardPosition [][] bpCheckSur = boardPositon;
		int bRowPosition = nPosition-1;
		char bRightShoulderPosition = (char) (cPosition +1);
		int bRSIntegerValue = Character.getNumericValue(cPosition) - 9;
		char bLeftShoulderPosition = (char) (cPosition -1);
		int bLSIntegerValue = Character.getNumericValue(cPosition) - 11;
		
		String bLegalRSMove = "" + bRightShoulderPosition + bRowPosition;
		String bLegalLSMove = "" + bLeftShoulderPosition + bRowPosition;
		
		// Logic statements
				// To prevent an ArrayOutOfBounds Exception and because there are no positions pass H
			if (cPosition != 'H'){
				if (bpCheckSur[bRSIntegerValue][(nPosition-2)].isOccupied() &&
						bpCheckSur[bRSIntegerValue][(nPosition-2)].chessPiece.getPieceColour().equals("White")){
					pawnMoveOutline[2].setOutlinePositon(bLegalRSMove);
					pawnMoveOutline[2].drawAtLocation();
				}
			}
			// To prevent an ArrayOutOfBounds Exception and because there are no positions beside A
			if (cPosition != 'A'){
				if (bpCheckSur[bLSIntegerValue][(nPosition-2)].isOccupied() &&
						bpCheckSur[bLSIntegerValue][(nPosition-2)].chessPiece.getPieceColour().equals("White")){
					pawnMoveOutline[2].setOutlinePositon(bLegalLSMove);
					pawnMoveOutline[2].drawAtLocation();
				}
			}
		
	}
	
	/*
	 * Logic check for moves
	 */
	public boolean checkMove(BoardPosition [][] boardPosition){
		
			/* Local Variables*/
		charPosition = this.piecePosition.charAt(0);
		numPosition = Character.getNumericValue(this.piecePosition.charAt(1))-1;
		
			/* Logic Statements*/
		if(this.getPieceColour().equals("White")){
			if (checkWhiteMove(boardPosition,charPosition,numPosition)){
				return true;
			}else{
				return false;
			}
		}else if(this.getPieceColour().equals("Black")){
			if (checkBlackMove(boardPosition,charPosition,numPosition)){
				return true;
			}else{
				return false;
			}
		}else{
			System.out.println("Cant check this piece as it is not white");
			return false;
		}
	}
	
	/*
	 * Check White legal moves
	 */
	
	protected boolean checkWhiteMove(BoardPosition [][] boardPosition,char characterPosition, int numberPosition){
		
			/* Local Variables*/
		BoardPosition[][] bpWShow = boardPosition;
		int numArrayPos = numberPosition;

		if (numArrayPos < 7){
			if(bpWShow[nPosX][nPosY].getLocation().equals(bpWShow[oPosX][oPosY+1].getLocation())
					&& bpWShow[nPosX][nPosY].isOccupied() == false){
				System.out.println("Inside pos 1.1.1");
				return true;
			}else if (moveCount == 0 
					&& bpWShow[oPosX][oPosY+2].isOccupied() == false
						&& bpWShow[nPosX][nPosY].getLocation().equals(bpWShow[oPosX][oPosY+2].getLocation())){
				System.out.println("Inside If position 1.1.2");
				return true;
			}else if(checkWhiteSurroundings(boardPosition, characterPosition, numberPosition)){
				System.out.println("Inside If position 1.1.3");
				return true;
			}else{
				System.out.println("Inside If position 1.1 FalseStatement");
				return false;
			}
		}else{
			System.out.println("Inside If position 1 FalseStatement");
			return false;
		}

	}
	
	protected boolean checkWhiteSurroundings(BoardPosition[][] boardPositon, int cPosition, int nPosition){
		
			/* Local Variables*/
		BoardPosition [][] bpCheckSur = boardPositon;
		int wRSIntegerValue = Character.getNumericValue(cPosition) - 9;
		int wLSIntegerValue = Character.getNumericValue(cPosition) - 11;
		
			if (cPosition != 'H' && cPosition != 'A'){
				if (bpCheckSur[wRSIntegerValue][(nPosition)].isOccupied() &&
						bpCheckSur[wRSIntegerValue][(nPosition)].chessPiece.getPieceColour().equals("Black")){
					return true;
				}else if (bpCheckSur[wLSIntegerValue][(nPosition)].isOccupied() &&
						bpCheckSur[wLSIntegerValue][(nPosition)].chessPiece.getPieceColour().equals("Black")){
					return true;
				}
				else{
					return false;
				}
			}else{
				return false;
			}
		
	}
	
	/*
	 * Check Black legal moves
	 */
	protected boolean checkBlackMove(BoardPosition [][] boardPosition,char characterPosition, int numberPosition ){
		
			/* Local Variables*/
		BoardPosition[][] bpWShow = boardPosition;
		int numArrayPos = numberPosition;

			// If the space infront is not occupied draw legalmove
		if (numArrayPos < 7){
			if(bpWShow[nPosX][nPosY].getLocation().equals(bpWShow[oPosX][oPosY-1].getLocation())
					&& bpWShow[nPosX][nPosY].isOccupied() == false){
				System.out.println("Inside pos 1.1.1");
				return true;
			}else if (moveCount == 0 
					&& bpWShow[oPosX][oPosY-2].isOccupied() == false
						&& bpWShow[nPosX][nPosY].getLocation().equals(bpWShow[oPosX][oPosY-2].getLocation())){
				System.out.println("Inside If position 1.1.2");
				return true;
			}else if(checkBlackSurroundings(boardPosition, characterPosition, numberPosition)){
				System.out.println("Inside If position 1.1.3");
				return true;
			}else{
				System.out.println("Inside If position 1.1 FalseStatement");
				return false;
			}
		}else{
			System.out.println("Inside If position 1 FalseStatement");
			return false;
		}

	}
	
	protected boolean checkBlackSurroundings(BoardPosition[][] boardPositon, int cPosition, int nPosition){
		
			/* Local Variables*/
		BoardPosition [][] bpCheckSur = boardPositon;
		int wRSIntegerValue = Character.getNumericValue(cPosition) - 9;
		int wLSIntegerValue = Character.getNumericValue(cPosition) - 11;
		
			if (cPosition != 'H' && cPosition != 'A'){
				if (bpCheckSur[wRSIntegerValue][nPosition-1].isOccupied() &&
						bpCheckSur[wRSIntegerValue][nPosition-1].chessPiece.getPieceColour().equals("White")){
					return true;
				}else if (bpCheckSur[wLSIntegerValue][nPosition-1].isOccupied() &&
						bpCheckSur[wLSIntegerValue][nPosition-1].chessPiece.getPieceColour().equals("White")){
					return true;
				}
				else{
					return false;
				}
			}else{
				return false;
			}
		
	}



	@Override
	protected void showLegalMoves(BoardPosition[][] boardPosition,
			char characterPosition, int numberPosition) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void showSurroundings(BoardPosition[][] boardPosition,
			int cPosition, int nPosition) {
		// TODO Auto-generated method stub
		
	}
}
