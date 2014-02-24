package Controls;

import org.lwjgl.input.Mouse;

import ChessBoard.BoardPosition;
import ChessBoard.ClickOutline;

public class MouseControl {
	
	private int firstClick = 0;
	private int [] chaX = new int [2];
	private int [] intY = new int [2];
	
	
	public int getChaX(int arrayPosition){
		return chaX[arrayPosition];
	}
	
	public int getIntY(int arrayPosition){
		return intY[arrayPosition];
	}
	
	
	public void input(int screenSizeHeight, BoardPosition [][] bp, ClickOutline [] outline){
			/* Mouse Events */
		int divide = 77;
		int mouseX = Mouse.getX();
			// OpenGL Y-Axis is always inverted!
		int mouseY = (screenSizeHeight-1)- Mouse.getY();
		String mousePosition;
			try {
				if (Mouse.isButtonDown(0)) {
						// Check if the click is within the boards boundary
					if(mouseX > 190 && mouseX <= (191 + divide*8) && mouseY >52  && mouseY <= (52 + divide*8)){
							mousePosition = checkMouseClickPositionX(mouseX, divide) + 
									 checkMouseClickPositionY(mouseY, divide);
							
							chaX[firstClick] = Character.getNumericValue(mousePosition.charAt(0)) -10;
							intY[firstClick] = Character.getNumericValue(mousePosition.charAt(1)-1);

								// Check if position clicked is occupied
							if(bp[chaX[0]][intY[0]].isOccupied() == false){
									/*
									 * 	If not occupied highlight the clicked box
									 *  and unhighlight any second click boxes
									 */
								outline[0].setOutlinePositon(mousePosition);
								outline[1].setOutlinePositon(null);
								
							}	// If not occupied...
							else{
									// Check if this is the first click
								if (firstClick == 0){
									outline[0].setOutlinePositon(mousePosition);
									outline[1].setOutlinePositon(null);
									firstClick++;
								}else if(bp[chaX[1]][intY[1]].isOccupied() && 
										bp[chaX[0]][intY[0]].chessPiece == bp[chaX[1]][intY[1]].chessPiece){
									outline[1].setOutlinePositon(null);
									firstClick--;
	//							}else if (bpos[chaX[1]][intY[1]].isOccupied() &&
	//									bpos[chaX[0]][intY[0]].chessPiece != bpos[chaX[1]][intY[1]].chessPiece){
	//								System.out.println("Second Click!");
	//								my2ndClick.setOutlinePositon(mousePosition);
	//								System.out.println("That spot is occupied by another piece!");
	//								myWrongClick.setOutlinePositon(mousePosition);
	//								//firstClick--;
								}else{
									System.out.println();
									outline[1].setOutlinePositon(mousePosition);
									movePiece(bp,bp[chaX[0]][intY[0]].getLocation() , bp[chaX[1]][intY[1]].getLocation());
								}
							}
					}else{
						System.out.println("Off Board Click");
					}
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	}
	
	private void movePiece(BoardPosition [][] bp, String oldpos, String newpos){
		
		int nx = Character.getNumericValue(newpos.charAt(0))-10;
		int ny = Character.getNumericValue(newpos.charAt(1))-1;
	
		int ox = Character.getNumericValue(oldpos.charAt(0))-10;
		int oy = Character.getNumericValue(oldpos.charAt(1))-1;
		
		bp[ox][oy].chessPiece.setClickPosition(ox,oy,nx,ny);
		
		if(bp[ox][oy].chessPiece.checkMove(bp)){
			System.out.println(bp[ox][oy].chessPiece.checkMove(bp));
			bp[ox][oy].chessPiece.setMoveCount(1);
			bp[nx][ny].chessPiece = bp[ox][oy].chessPiece;
			bp[ox][oy].chessPiece = null;
			firstClick--;
		}else{
			System.out.println(bp[ox][oy].chessPiece.checkMove(bp));
			System.out.println("That is an illegal move for that piece!");
		}
	
	}
	
	
	
	private String checkMouseClickPositionX(int x, int divide){
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
	
	private String checkMouseClickPositionY(int y, int divide){
			
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
