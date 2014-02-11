package Pieces.TestPieces;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;


public class TPAlpha {
	
	
	public TPAlpha() {
	}
	
	public void drawPiece(String location){
		
	}
	

	public void drawSquarePiece(String colour) {
		
		switch (colour){
			case "red": 	glColor3f(1.0f,0.0f,0.0f);
							break;
			case "green": 	glColor3f(0.0f,1.0f,0.0f);
							break;
			case "blue":	glColor3f(0.0f,0.0f,1.0f);
							break;
		}
		
		glBegin(GL_QUADS);
			glVertex2f(0.0f,0.0f);
			glVertex2f(1.0f,0.0f);
			glVertex2f(1.0f,1.0f);
			glVertex2f(0.0f,1.0f);
		glEnd();
	}

	
}
