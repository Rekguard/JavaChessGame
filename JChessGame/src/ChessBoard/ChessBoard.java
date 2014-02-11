package ChessBoard;

import static org.lwjgl.opengl.GL11.GL_LINES;
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


public class ChessBoard {
	
		// Variables
	
	Texture [] boardLetAtoH= new Texture[8];
	Texture [] boardNum1to8= new Texture[8];

	/*
	 *  Initialise Elements
	 *  
	 */
	
	public void init(){
		loadBorderTextures(false);
	}
	
	/*
	 *  Draw Method
	 * 
	 */
	
	public void drawComponents(float size){
		glPushMatrix();
			drawBoard(size);
		glPopMatrix();
	}
	
	/*
	 * 	Draw Board
	 * 
	 */
	private void drawBoard(float squareSize){
		
			// drawBoard Variables
		float sqSize = squareSize;
		float boardSize = squareSize*8.0f;
		float perimeterSize = boardSize + sqSize;
		
		glColor3f(1.0f,1.0f,1.0f);
		glPushMatrix();													// Push Matrix (_1_)
					/* Note ! */
				// This translation moves the board backwards through the x and y
				// axis to centre the board in the middle of the screen.
			glTranslatef(-boardSize/2,-boardSize/2,0.0f);
		
				// Draw Grid outline for chess board.
			for (float i = 0.0f; i < perimeterSize;i += squareSize){
				glBegin(GL_LINES);
					glVertex2f(0.0f,i);
						glVertex2f(boardSize,i);
					glVertex2f(i,0.0f);
						glVertex2f(i,boardSize);
				glEnd();
			}
			
				// Draw Squares
			glPushMatrix();												// Push Matrix (_2_)
			for (float sy = 0.0f; sy < 8.0f; sy++){
				
				for (float sx = 0.0f; sx < 4.0f; sx++){
					
					if (sy%2 != 1){
						drawSquare(squareSize,"black");
						glTranslatef(squareSize,0.0f,0.0f);
						drawSquare(squareSize,"white");
						glTranslatef(squareSize,0.0f,0.0f);
					}else{
						drawSquare(squareSize,"white");
						glTranslatef(squareSize,0.0f,0.0f);
						drawSquare(squareSize,"black");
						glTranslatef(squareSize,0.0f,0.0f);
					}
				}
				
				glTranslatef(-boardSize,0.0f,0.0f);
				glTranslatef(0.0f,squareSize,0.0f);
			}
			
			glPopMatrix();												// Pop Matrix (_2_)
				// Draw Border Elements
			
			drawBorderLetters(squareSize);
			drawBorderNumbers(squareSize);
			
				// Opposite border letters.
			glPushMatrix();												// Push Matrix (_3_)
				glTranslatef(0.0f,boardSize + squareSize/2,0.0f);
				drawBorderLetters(squareSize);
			glPopMatrix();												// Pop Matrix (_3_)

				// Opposite border Numbers.
			glPushMatrix();												// Push Matrix (_4_)
				glTranslatef(boardSize + squareSize/2,0.0f,0.0f);
				drawBorderNumbers(squareSize);
			glPopMatrix();												// Pop Matrix (_4_)
			
				// Table Outline ( Just to clean up the edges)
			glColor3f(0.0f,0.0f,0.0f);
			glBegin(GL_LINES);
				glVertex2f(0.0f,0.0f);
					glVertex2f(boardSize,0.0f);
				glVertex2f(boardSize,0.0f);
					glVertex2f(boardSize,boardSize);
				glVertex2f(boardSize,boardSize);
					glVertex2f(0.0f,boardSize);
				glVertex2f(0.0f,boardSize);
					glVertex2f(0.0f,0.0f);

			glEnd();
		
			// After this pop the centre of the screen moves
		glPopMatrix();													// Pop Matrix (_1_)
			
	}
	
	private void drawBorderLetters(float dblSquareSize) {
			// Local Variables
		char dbLetter = 'A';
			// Draw Border Letters
		glPushMatrix();														// Push Matrix (_1_)
			glTranslatef(0.0f,-dblSquareSize/2,0.0f);
			for(int i = 1; i <boardLetAtoH.length + 1; i++){
				drawLetter(dbLetter, 128.0f / dblSquareSize);
					// Black Line Divider
				glColor3f(0.0f,0.0f,0.0f);
				glBegin(GL_LINES);
					glVertex2f(0.0f,0.0f);
						glVertex2f(0.0f,boardLetAtoH[0].getHeight());
				glEnd();
				glTranslatef(dblSquareSize,0.0f,0.0f);
				
				dbLetter++;
			}
		glPopMatrix();														// Pop Matrix (_1_)
		
	}
	
	private void drawBorderNumbers(float dbnSquareSize){
			// Local Variables
		int dbNumber = 1;
			// Numbers
		glPushMatrix();
			glTranslatef(-dbnSquareSize/2,0.0f,0.0f);
			for(int i = 1; i <boardNum1to8.length + 1; i++){
				drawNumber(dbNumber, 128.0f / dbnSquareSize);
					// Black Line Divider
				glColor3f(0.0f,0.0f,0.0f);
				glBegin(GL_LINES);
					glVertex2f(0.0f,0.0f);
						glVertex2f(boardNum1to8[0].getHeight(),0.0f);
				glEnd();
				glTranslatef(0.0f,dbnSquareSize,0.0f);
				
				dbNumber++;
			}
		glPopMatrix();
	}
	
	/*
	 *  Draw Squares
	 */
	private void drawSquare(float squareSize, String tileColour){
		
		float black = 0.25f;
		float white = 0.9f;
		
		if (tileColour.toLowerCase() == "black"){
			glColor3f(black,black,black);
		}else if(tileColour.toLowerCase() == "white"){
			glColor3f(white,white,white);
		}else{
			glColor3f(1.0f,0.0f,0.0f);
		}
		glBegin(GL_QUADS);
			glVertex2f(0.0f,0.0f);
			glVertex2f(squareSize,0.0f);
			glVertex2f(squareSize,squareSize);
			glVertex2f(0.0f,squareSize);
		glEnd();
	}
	
	/*
	 * Border Load Textures Letter and Numbers
	 */
	
	private void loadBorderTextures(boolean outputResult){
			// Local Variables
		char alphabet = 'A';
		int number = 1;
		
			// Letters
		for(int i = 0; i < boardLetAtoH.length;i++){
			try{
				boardLetAtoH[i] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(
						"Images/BorderLetters/"+ alphabet + ".png"));
				if (outputResult){
					System.out.println("Letter texture " + alphabet + " successfully loaded!");
				}
			}catch(FileNotFoundException e){
				System.out.println("Letter texture" + alphabet + " not found, file not found!");
				e.printStackTrace();
			}catch(IOException e){
				System.out.println("Failed to load Letter texture!");
				e.printStackTrace();
			}
			alphabet++;
		}
		
			// Numbers
		for(int i = 0; i < boardNum1to8.length;i++){
			try{
				boardNum1to8[i] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(
						"Images/BorderNumbers/No"+ number + ".png"));
				if(outputResult){
					System.out.println("Number texture " + number + " successfully loaded!");
				}
			}catch(FileNotFoundException e){
				System.out.println("Number texture " + number + " not found, file not found!");
				e.printStackTrace();
			}catch(IOException e){
				System.out.println("Failed to load Number texture!");
				e.printStackTrace();
			}
			number++;
		}
	}
	
	/*
	 * Draw Boarder Letters
	 */
	
	private void drawLetter(char character, float scale) {
		int x = 0;
		switch (character){
			case 'A': x = 0;
				break;
			case 'B': x = 1;
			break;
			case 'C': x = 2;
			break;
			case 'D': x = 3;
			break;
			case 'E': x = 4;
			break;
			case 'F': x = 5;
			break;
			case 'G': x = 6;
			break;
			case 'H': x = 7;
			break;	
		}

			// Through the X Axis
		float width = boardLetAtoH[x].getTextureWidth()/scale;
			// Through the Y Axis
		float length = boardLetAtoH[x].getTextureHeight()/scale;
		
		boardLetAtoH[x].bind();
		
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
	
	/*
	 * Draw Board Numbers
	 */
	private void drawNumber(int number, float scale) {
		
		int x = 0;
		switch (number){
			case 1: x = 0;
			break;
			case 2: x = 1;
			break;
			case 3: x = 2;
			break;
			case 4: x = 3;
			break;
			case 5: x = 4;
			break;
			case 6: x = 5;
			break;
			case 7: x = 6;
			break;
			case 8: x = 7;
			break;	
		}

			// Through the X Axis
		float width = boardNum1to8[x].getTextureWidth()/scale;
			// Through the Y Axis
		float length = boardNum1to8[x].getTextureHeight()/scale;
		
		boardNum1to8[x].bind();
		
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
	
}
