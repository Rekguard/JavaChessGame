package Pieces;

public abstract class Piece {
		// Variables
	String piecePosition;
		// Abstract Methods
	public abstract void drawPiece();
	
		// Methods.
			// Getters.
	public String getPosition(){
		return piecePosition;
	}
	
			// Setters.
	public void setPosition(String setLocation){
		piecePosition = setLocation;
	}
	
			// Other Methods.
	public void changePosition(String cPosition){
		piecePosition = cPosition;
	}
	
}
