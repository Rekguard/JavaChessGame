package Screen;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import ChessBoard.BoardPosition;
import ChessBoard.ChessBoard;
import ChessBoard.ClickOutline;
import Controls.MouseControl;
import Pieces.ChessPiece;
import Pieces.ChessPieces.*;

public class Screen {
	
	int screenSizeW;
	int screenSizeH;
	
	ChessBoard cb = new ChessBoard();
	MouseControl mc = new MouseControl();
	
	/*
	 * 
	 * Board Position is currently not tied with the pieces,
	 * its not used for anything
	 * 
	 */
	
	BoardPosition [][] bpos = new BoardPosition[8][8];
	
	ChessPiece [] whitePieces = new ChessPiece[16];
	ChessPiece [] blackPieces = new ChessPiece[16];
	
//	ClickOutline my1stClick = new ClickOutline("Blue");
//	ClickOutline my2ndClick = new ClickOutline("Green");
//	ClickOutline myWrongClick = new ClickOutline("Red");
	
	ClickOutline [] clickArray = new ClickOutline[2];
	
	//private int firstClick = 0;
//	private int [] chaX = new int [2];
//	private int [] intY = new int [2];
	
	char letterPosition = 'A';
	int numberPosition = 1;
	
	
	
	public Screen(int screenSizeWidth, int screenSizeHeight){
		screenSizeW = screenSizeWidth;
		screenSizeH = screenSizeHeight;
		
		clickArray[0] = new ClickOutline("Blue");
		clickArray[1] = new ClickOutline("Green");
		
		screenSetup(screenSizeWidth,screenSizeHeight);
	}
	
	private void screenSetup(int screenWidth, int screenHeight) {
		{
				// Calculate Aspect Ratio
			float aspectRatio = (float) screenWidth/screenHeight;
				// Load Frame
			try {
				Display.setDisplayMode(new DisplayMode(screenWidth,screenHeight));
				Display.setTitle("..Chess..");
				Display.create();
			} catch (LWJGLException e){
				e.printStackTrace();
			}
			
				/* Initialise Screen Properties */
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND); 
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(50.0f,aspectRatio, 0.001f, 1000.0f);
			
			glMatrixMode(GL_MODELVIEW);
			glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
			glTranslatef(0.0f,0.0f,-20.0f);
			
				/* Initialise Components */
			init();
			
			
				/* Display */
			while(!Display.isCloseRequested()) {
					/* Render Images */
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
				updatePiecePosition();
				
				mc.input(screenSizeH,bpos,clickArray);
				draw();
				//System.out.println(whitePieces[0].getPiecePosition());
				Display.update();
				Display.sync(60);				
			}
				// Close Window
			Display.destroy();									
			
		}
	
	}
	
	private void updatePiecePosition(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j< 8; j++){
				if(bpos[i][j].chessPiece == null){ // Do Nothing
				}else{
					bpos[i][j].chessPiece.setPiecePosition(bpos[i][j].getLocation());
				}
			}
		}
	}

	/*
	 * 		Init
	 * 
	 * Initialise everything method.
	 * 
	 */
	
	private void init(){
		cb.init();
		initBoardGrid();
		setUpPieces();
		loadAllGameTextures();
	}
	
	/* 
	 * 		Draw
	 * 
	 * Draw all components
	 * 
	 */
	private void draw(){
		cb.drawComponents(2.0f);
		drawComponents();
	}
	
	
	/*
	 * Load Textures
	 */
	
	private void loadAllGameTextures(){
		glEnable(GL_TEXTURE_2D);
		clickArray[0].loadTexture();
		clickArray[1].loadTexture();
		
		for (int i = 0; i < 16; i++){
			whitePieces[i].loadTexture();
			whitePieces[i].loadOutlineTexture();
			blackPieces[i].loadTexture();
			blackPieces[i].loadOutlineTexture();
		}
		glDisable(GL_TEXTURE_2D);
	}
	
	private void drawComponents(){
		
			/* Enable Texturing */
		glEnable(GL_TEXTURE_2D);
		
				/* 
				 * For loop loads all piece texture images on the board.
				 */
		
			for (int i = 0; i <= 7; i++){
				for(int j = 0; j <= 7; j++){
					if (bpos[i][j].chessPiece != null){
						bpos[i][j].chessPiece.drawChessPieceAtLocation();
					}
				}
			}
			
			/*
			 * Setting showLegalMove to "true" will show orange squares around
			 * squares where a square can move towards. 	
			 * 
			 * Setting it to false turns this off.
			 */
			showLegalMove(true);
			
			/*
			 * my2ndClick displays a green box that shows what square was previously
			 * clicked on when a piece has been selected. 
			 * 
			 * This code snippet is placed higher than my1stClick to give 1st 
			 * click priority over 2nd click when being drawn to the screen. 
			 */
			
			if(clickArray[1].getLocation() != null){
				clickArray[1].drawAtLocation();
			}
			
			/*
			 * my1stClick displays a blue box on the square where the 1st click
			 * has been made. If the first click was on an occupied spot the blue
			 * box will remain around that square until it is "unclicked" or a
			 * legal move has been made.
			 */
			
			if(clickArray[0].getLocation() != null){
				clickArray[0].drawAtLocation();}		
			/* Disable Texturing */
		glDisable(GL_TEXTURE_2D);
	}
	
	private void showLegalMove(boolean enable){
		/*
		 * Shows orange squares for the legal move positions of the current piece
		 * selected. 
		 */
		
		if (enable){
			if (bpos[mc.getChaX(0)][mc.getIntY(0)].chessPiece == null){
			}else{
				bpos[mc.getChaX(0)][mc.getIntY(0)].chessPiece.showMove(bpos);
			}
		}
	}
	
	private void setUpPieces(){
		
		/* 
		 * Fills up the different arrays with appropriate pieces 
		 */
		
			/*
			 * White Pieces
			 */
		for (int i = 0; i < 8; i++){
			whitePieces [i] = new Pawn('w');
		}
		whitePieces [8] = new Rook('w');
		whitePieces [9] = new Knight('w');
		whitePieces [10] = new Bishop('w');
		whitePieces [11] = new Queen('w');
		whitePieces [12] = new King('w');
		whitePieces [13] = new Bishop('w');
		whitePieces [14] = new Knight('w');
		whitePieces [15] = new Rook('w');
		
			/*
			 * Black Pieces
			 */
		for (int j = 0; j < 8; j++){
			blackPieces [j] = new Pawn('b');
		}
		blackPieces [8] = new Rook('b');
		blackPieces [9] = new Knight('b');
		blackPieces [10] = new Bishop('b');
		blackPieces [11] = new Queen('b');
		blackPieces [12] = new King('b');
		blackPieces [13] = new Bishop('b');
		blackPieces [14] = new Knight('b');
		blackPieces [15] = new Rook('b');
		
		/*
		 * The following for loop loads all  the pieces into the board
		 * position array.  
		 */
		
		for (int x = 0; x <=7; x++){
			bpos[x][6].setOccupiedPiece(blackPieces[x]);
			bpos[x][7].setOccupiedPiece(blackPieces[x+8]);
			bpos[x][1].setOccupiedPiece(whitePieces[x]);
			bpos[x][0].setOccupiedPiece(whitePieces[x+8]);
		}
	}
	
	private void initBoardGrid(){
		for (int i = 0; i < 8;i++){
			for (int j = 0; j < 8;j++) {
				bpos [i][j] = new BoardPosition(letterPosition,numberPosition);
				numberPosition++;
			}
			letterPosition++;
			numberPosition = 1;
		}
	}
}
