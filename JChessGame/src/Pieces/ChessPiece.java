package Pieces;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import ChessBoard.BoardPosition;
import ChessBoard.ClickOutline;

public abstract class ChessPiece {

		// Variables
			// Objects
	public ClickOutline myChessPieceOutline = new ClickOutline("Orange");
	public Texture chessPieceTexture;
	
			// Integers
	protected int moveCount = 0;
	protected int oPosX;
	protected int oPosY;
	protected int nPosX;
	protected int nPosY;
	protected int numPosition; 
	
			// Chars
	protected char chessPieceColour;
	protected char charPosition;
	
			// Booleans
	public boolean isCaptureable;
	
			// Strings
	protected String chessPieceName;
	protected String piecePosition;
	protected String oppositeColour;
	
		// Abstract Methods
			// Method for loading border outline textures
	public abstract void loadOutlineTexture();
	
		// Method to show the border outlines
	public abstract void showMove(BoardPosition[][] boardPosition);
	protected abstract void showLegalMoves(BoardPosition[][] boardPosition,
			char characterPosition, int numberPosition);
	protected abstract void showSurroundings(BoardPosition[][] boardPosition,
			int cPosition, int nPosition);

		// Boolean which will return if a squares can make legal moves
	public abstract boolean checkMove(BoardPosition[][] boardPosition);
	
	
	
		// Getters
	public String getPieceName(){
		if (chessPieceName == null){
			return "This is a Chess Piece!";
		}else{
			return chessPieceName;
		}
	}
	
	public String getPieceColour(){
		if (chessPieceColour == ' '){
			return "This Piece has no colour";
		}else if(chessPieceColour == 'w' || chessPieceColour == 'W'){
			return "White";
		}else{
			return "Black";
		}
	}
	
	public String getPiecePosition(){
		return piecePosition;
	}
	
	// Setters
	
	public void setPiecePosition(String Position){
		piecePosition = Position;
	}
	
	public void setMoveCount(int count){
		moveCount = count;
	}
	
	public void setClickPosition(int oldPosX, int oldPosY, int newPosX, int newPosY){
		oPosX = oldPosX;
		oPosY = oldPosY;
		nPosX = newPosX;
		nPosY = newPosY;
	}
	
		// End Setters
	public void drawPiece(){
		glPushMatrix();
			drawPieceImage(64.0f);
		glPopMatrix();
	}

	/*
	 * 
	 * The Code following this comment needs brought up to date. 
	 * Fix the If statement or remove it. 
	 * Also update the rest of the catch statement. 
	 * 
	 */
	public void loadTexture(){
		
		String colourFolder;
		char ltColour = Character.toUpperCase(chessPieceColour);
		
			// Chose whether piece is white or black.
		if (ltColour == 'W'){
			colourFolder = "White";
		}else{
			colourFolder = "Black";
		}
		
			//Texture Loader with directory path included.
		try{
			chessPieceTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(
					"Images/" + colourFolder + "Pieces/"+ ltColour + chessPieceName + ".png"));
		}catch(FileNotFoundException e){
			System.out.println("Piece: " + chessPieceColour + " " + chessPieceName + " not found, file not found!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("Failed to load Piece texture!");
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * Draw the actual chess piece with scale adjustment
	 * with the proper texture loaded. 
	 * 
	 */
	private void drawPieceImage(float scale) {
			// Through the X Axis
		float width = chessPieceTexture.getTextureWidth()/scale;
			// Through the Y Axis
		float length = chessPieceTexture.getTextureHeight()/scale;
		
		chessPieceTexture.bind();
		
		glPushMatrix();
			glColor3f(1.0f,1.0f,1.0f);
			glBegin(GL_QUADS);
					glTexCoord2f(0.0f,1.0f);
				glVertex2f(0.0f,0.0f);			// Top Left
					glTexCoord2f(1.0f,1.0f);
				glVertex2f(width,0.0f);			// Top Right
					glTexCoord2f(1.0f,0.0f);
				glVertex2f(width,length);		// Bottom Right
					glTexCoord2f(0.0f,0.0f);
				glVertex2f(0.0f,length);		// Bottom Left
			glEnd();
		glPopMatrix();	
		
	}
	
	public void drawChessPieceAtLocation(){
		
		String location = getPiecePosition();
		char letter = location.charAt(0);
		int number = Character.getNumericValue(location.charAt(1));
		glPushMatrix();
		switch (letter){
			case 'A': 	glTranslatef(-7.5f,-9.5f + 2.0f*number, 0.0f);
						break;
			case 'B': 	glTranslatef(-5.5f,-9.5f + 2.0f*number, 0.0f);
						break;
			case 'C': 	glTranslatef(-3.5f,-9.5f + 2.0f*number, 0.0f);
						break;
			case 'D': 	glTranslatef(-1.5f,-9.5f + 2.0f*number, 0.0f);
						break;
			case 'E': 	glTranslatef(0.5f,-9.5f + 2.0f*number, 0.0f);
						break;
			case 'F': 	glTranslatef(2.5f,-9.5f + 2.0f*number, 0.0f);
						break;
			case 'G': 	glTranslatef(4.5f,-9.5f + 2.0f*number, 0.0f);
						break;
			case 'H': 	glTranslatef(6.5f,-9.5f + 2.0f*number, 0.0f);
						break;
		}	
		drawPiece();
		glPopMatrix();
	}

}
