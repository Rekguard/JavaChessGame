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
import Pieces.ChessPiece;
import Pieces.ChessPieces.*;

public class Screen {
	
	/*
	 * 
	 * 
	 * GitHub TEST Massive Comment
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	int screenSizeW;
	int screenSizeH;
	
	ChessBoard cb = new ChessBoard();
	
	/*
	 * 
	 * Board Position is currently not tied with the pieces,
	 * its not used for anything
	 * 
	 */
	
	BoardPosition [][] bpos = new BoardPosition[8][8];
	
	ChessPiece [] whitePieces = new ChessPiece[16];
	ChessPiece [] blackPieces = new ChessPiece[16];
	
	ClickOutline my1stClick = new ClickOutline("Blue");
	ClickOutline my2ndClick = new ClickOutline("Green");
	ClickOutline myWrongClick = new ClickOutline("Red");
	
	private int firstClick = 0;
	private int [] chaX = new int [2];
	private int [] intY = new int [2];
	
	char letterPosition = 'A';
	int numberPosition = 1;
	
	
	
	public Screen(int screenSizeWidth, int screenSizeHeight){
		screenSizeW = screenSizeWidth;
		screenSizeH = screenSizeHeight;
		screenSetup(screenSizeWidth,screenSizeHeight);
	}
	
	private void screenSetup(int screenWidth, int screenHeight) {
		{
				// Calculate Aspect Ratio
			float aspect = (float) screenWidth/screenHeight;
				// Load Frame
			try {
				Display.setDisplayMode(new DisplayMode(screenWidth,screenHeight));
				Display.setTitle("..Chess..");
				Display.create();
			} catch (LWJGLException e){
				e.printStackTrace();
			}
			
			// Initialise Screen code
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND); 
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(50.0f,aspect, 0.001f, 1000.0f);
			
			glMatrixMode(GL_MODELVIEW);
			glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
			glTranslatef(0.0f,0.0f,-20.0f);
			
			// Initialise Components code
			init();
			
			
			while(!Display.isCloseRequested()) {
					// Render
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
				updatePiecePosition();
				
				input();
				drawEverything();
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
	 * Initialise everything method.
	 */
	
	private void init(){
		cb.init();
		initBoardGrid();
		setUpPieces();
		loadAllGameTextures();
		
		
		bpos[4][4].chessPiece = bpos[1][0].chessPiece;
		bpos[1][0].chessPiece = null;
		
		//System.out.println(bpos[3][0].chessPiece.getPieceName());
		
	}	
	
	/*
	 * Load Textures
	 */
	
	private void loadAllGameTextures(){
		glEnable(GL_TEXTURE_2D);
		my1stClick.loadTexture();
		my2ndClick.loadTexture();
		myWrongClick.loadTexture();
		
		for (int i = 0; i < 16; i++){
			whitePieces[i].loadTexture();
			whitePieces[i].loadOutlineTexture();
			blackPieces[i].loadTexture();
			blackPieces[i].loadOutlineTexture();
		}
		glDisable(GL_TEXTURE_2D);
	}
	
	/* 
	 * Draw everything method. 
	 */
	
	private void drawEverything(){
		cb.drawComponents(2.0f);
		drawComponents();
	}
	
	private void drawComponents(){
		glEnable(GL_TEXTURE_2D);
		for (int i = 0; i <= 7; i++){
			for(int j = 0; j <= 7; j++){
				if (bpos[i][j].chessPiece != null){
					bpos[i][j].chessPiece.drawChessPieceAtLocation();
				}
			}
		}
		
		showLegalMove(true);
		
		if(my2ndClick.getLocation() != null){
			my2ndClick.drawAtLocation();
		}
		
		if(myWrongClick.getLocation() != null){
			myWrongClick.drawAtLocation();
		}
		
		if(my1stClick.getLocation() != null){
			my1stClick.drawAtLocation();}		           
		
		
		glDisable(GL_TEXTURE_2D);
	}
	
	private void showLegalMove(boolean enabled){
		if (enabled){
			if (bpos[chaX[0]][intY[0]].chessPiece == null){
			}else{
				bpos[chaX[0]][intY[0]].chessPiece.showMove(bpos);
			}
		}
	}
	
	
	private void setUpPieces(){
		
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
	
	public void movePiece(String oldpos, String newpos){
	
		int nx = Character.getNumericValue(newpos.charAt(0))-10;
		int ny = Character.getNumericValue(newpos.charAt(1))-1;
	
		int ox = Character.getNumericValue(oldpos.charAt(0))-10;
		int oy = Character.getNumericValue(oldpos.charAt(1))-1;
		
		bpos[ox][oy].chessPiece.setClickPosition(ox,oy,nx,ny);
		
		if(bpos[ox][oy].chessPiece.checkMove(bpos)){
			System.out.println(bpos[ox][oy].chessPiece.checkMove(bpos));
			System.out.println("Yep thats a legal move!");
			bpos[ox][oy].chessPiece.setMoveCount(1);
			bpos[nx][ny].chessPiece = bpos[ox][oy].chessPiece;
			bpos[ox][oy].chessPiece = null;
			firstClick--;
		}else{
			System.out.println(bpos[ox][oy].chessPiece.checkMove(bpos));
			System.out.println("That is an illegal move for that piece!");
		}
	
	}
	
	public void input(){
		// Mouse Events
		int divide = 77;
		int mouseX = Mouse.getX();
			// OpenGL Y-Axis is always inverted!
		int mouseY = (screenSizeH-1)- Mouse.getY();
		String mousePosition;
			try {
				if (Mouse.isButtonDown(0)) {
						// Check if the click is within the boards boundary
					if(mouseX > 190 && mouseX <= (191 + divide*8) && mouseY >52  && mouseY <= (52 + divide*8)){
							mousePosition = checkMouseClickPositionX(mouseX, divide) + 
									 checkMouseClickPositionY(mouseY, divide);
							
							chaX[firstClick] = Character.getNumericValue(mousePosition.charAt(0)) -10;
							intY[firstClick] = Character.getNumericValue(mousePosition.charAt(1)-1);
							//System.out.println(mousePosition);
								// Check if position clicked is occupied
							if(bpos[chaX[0]][intY[0]].isOccupied() == false){
								System.out.println("This place is not occupied!");
									// If not occupied highlight the clicked box
									// and unhighlight any second click boxes
								my1stClick.setOutlinePositon(mousePosition);
								my2ndClick.setOutlinePositon(null);
								
							}	// If not occupied...
							else{
									// Check if this is the first click
								if (firstClick == 0){
									System.out.println("\n" + "First Click!");
									System.out.println();
									my1stClick.setOutlinePositon(mousePosition);
									my2ndClick.setOutlinePositon(null);
									myWrongClick.setOutlinePositon(null);
									firstClick++;
								}else if(bpos[chaX[1]][intY[1]].isOccupied() && 
										bpos[chaX[0]][intY[0]].chessPiece == bpos[chaX[1]][intY[1]].chessPiece){
									System.out.println("\n" + "This is the same spot!");
									System.out.println();
									my2ndClick.setOutlinePositon(null);
									myWrongClick.setOutlinePositon(null);
									firstClick--;
									System.out.println("\n" + " Out of else if 2");
//								}else if (bpos[chaX[1]][intY[1]].isOccupied() &&
//										bpos[chaX[0]][intY[0]].chessPiece != bpos[chaX[1]][intY[1]].chessPiece){
//									System.out.println("Second Click!");
//									my2ndClick.setOutlinePositon(mousePosition);
//									System.out.println("That spot is occupied by another piece!");
//									myWrongClick.setOutlinePositon(mousePosition);
//									//firstClick--;
								}else{
									System.out.println("\n" + "Second Click!");
									System.out.println();
									my2ndClick.setOutlinePositon(mousePosition);
									myWrongClick.setOutlinePositon(null);
									movePiece(bpos[chaX[0]][intY[0]].getLocation() , bpos[chaX[1]][intY[1]].getLocation());
									//firstClick--;
								}
							}
								// Print out the positions of the first and second click
							System.out.println("Position 1 = " + bpos[chaX[0]][intY[0]].getLocation());
							System.out.println("Position 2 = " + bpos[chaX[1]][intY[1]].getLocation());
							System.out.println();
							
					}else{
						System.out.println("Off Board Click");
					}
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	}
	
	public String checkMouseClickPositionX(int x, int divide){
		if (x > 190 && x <= (191 + divide)){
			return "A";
		}else if(x > (191 + divide) && x <= (191 + divide*2)){
			return "B";
		}else if(x > (191 + divide*2) && x <= (191 + divide*3)){
			return "C";
		}else if(x > (191 + divide*3) && x <= (191 + divide*4)){
			return "D";
		}else if(x > (191 + divide*4) && x <= (191 + divide*5)){
			return "E";
		}else if(x > (191 + divide*5) && x <= (191 + divide*6)){
			return "F";
		}else if(x > (191 + divide*6) && x <= (191 + divide*7)){
			return "G";
		}else if(x > (191 + divide*7) && x <= (191 + divide*8)){
			return "H";
		}else{
			return "This position is not on the chess board!";
		}
	}
	
	public String checkMouseClickPositionY(int y, int divide){
			
		if (y > 52 && y <= (52 + divide)){
			return "8";
		}else if(y > (52 + divide) && y <= (52 + divide*2)){
			return "7";
		}else if(y > (52 + divide*2) && y <= (52 + divide*3)){
			return "6";
		}else if(y > (52 + divide*3) && y <= (52 + divide*4)){
			return "5";
		}else if(y > (52 + divide*4) && y <= (52 + divide*5)){
			return "4";
		}else if(y > (52 + divide*5) && y <= (52 + divide*6)){
			return "3";
		}else if(y > (52 + divide*6) && y <= (52 + divide*7)){
			return "2";
		}else if(y > (52 + divide*7) && y <= (52 + divide*8)){
			return "1";
		}else{
			return "This position is not on the chess board!";
		}
		
	}
}
