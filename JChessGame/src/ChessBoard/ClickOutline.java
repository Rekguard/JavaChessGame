package ChessBoard;

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

public class ClickOutline {

		// Variables
	protected String outlineColour;
	public Texture outlineTexture;
	private String location;
	
	public ClickOutline(String colour){
		outlineColour = colour;
	}
	
	public String getLocation(){
		return location;
	}
	
	private void drawOutline(){
		glPushMatrix();
			drawOutlineImage(32.0f);
		glPopMatrix();
	}
	
	public void setOutlinePositon(String position){
		location = position;
	}
	
	public void drawAtLocation(){
		
		char letter = location.charAt(0);
		int number = Character.getNumericValue(location.charAt(1));
		glPushMatrix();
			switch (letter){
				case 'A': 	glTranslatef(-8.0f,-10.0f + 2.0f*number, 0.0f);
							break;
				case 'B': 	glTranslatef(-6.0f,-10.0f + 2.0f*number, 0.0f);
							break;
				case 'C': 	glTranslatef(-4.0f,-10.0f + 2.0f*number, 0.0f);
							break;
				case 'D': 	glTranslatef(-2.0f,-10.0f + 2.0f*number, 0.0f);
							break;
				case 'E': 	glTranslatef(0.0f,-10.0f + 2.0f*number, 0.0f);
							break;
				case 'F': 	glTranslatef(2.0f,-10.0f + 2.0f*number, 0.0f);
							break;
				case 'G': 	glTranslatef(4.0f,-10.0f + 2.0f*number, 0.0f);
							break;
				case 'H': 	glTranslatef(6.0f,-10.0f + 2.0f*number, 0.0f);
							break;
			}	
			drawOutline();
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
		
			//Texture Loader with directory path included.
		try{
			outlineTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(
					"Images/Movement_Outlines/" + outlineColour + "_Outline.png"));			
		}catch(FileNotFoundException e){
			System.out.println(" file not found!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("Failed to load Piece texture!");
			e.printStackTrace();
		}
	}
	
	private void drawOutlineImage(float scale) {
			// Through the X Axis
		float width = outlineTexture.getTextureWidth()/scale;
			// Through the Y Axis
		float length = outlineTexture.getTextureHeight()/scale;
		
		outlineTexture.bind();
		
		glPushMatrix();
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
