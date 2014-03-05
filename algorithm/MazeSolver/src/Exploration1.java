
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.swing.JLabel;

/**
 * 
 * @author stoh003
 */
public class Exploration1 implements Runnable {
	protected static final int SLEEP_TIME = 50;
	private static TwoDimGrid maze;
	private static updatedMaze updatemaze;
	private static Maze mazemaze;
	private static Reminder reminder;
	Stack Path = new Stack();

	public Exploration1(TwoDimGrid m) {
		maze = m;

	}

	public Exploration1(updatedMaze n) {
		updatemaze = n;

	}

	public static int count = 0;

	public static void front(updatedMaze n, TwoDimGrid maze2,
			int direction, int x, int y, int frontLeft,  int frontMiddle, int frontRight) {
		if (direction == 1) {// south
			try {
				for (int i = 1; i < frontLeft; i++)// left sensor
				{
					if (maze2.isWall(x + 1 + i, y + 1)) {
						n.isWall(x + 1 + i, y + 1);
						break;
					}
					
					if (!maze2.isWall(x + 1 + i, y + 1)
							&& !maze2.gettraversedPath(x + 1 + i, y + 1)) {
						maze2.isSensed(x + 1 + i, y + 1);
						n.isExplored(x + 1 + i, y + 1);
						// printField(field);
					}

				}
				
				for (int i = 1; i < frontMiddle; i++)// left sensor
				{
					if (maze2.isWall(x + 1 + i, y)) {
						n.isWall(x + 1 + i, y);
						break;
					}
					
					if (!maze2.isWall(x + 1 + i, y )
							&& !maze2.gettraversedPath(x + 1 + i, y)) {
						maze2.isSensed(x + 1 + i, y);
						n.isExplored(x + 1 + i, y );
						// printField(field);
					}

				}
				
				for (int i = 1; i < frontRight; i++) {// right sensor
					if (maze2.isWall(x + 1 + i, y - 1)) {
						n.isWall(x + 1 + i, y -1);
						break;
					}

					if (!maze2.isWall(x + 1 + i, y - 1)
							&& !maze2.gettraversedPath(x + 1 + i, y -1)) {
						maze2.isSensed(x + i + 1, y - 1);
						// printField(field);
						n.isExplored(x + 1 + i, y - 1);
					}
				}

				
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
		if (direction == 3) {// north
			try {
				for (int i = 1; i < frontLeft; i++)// left sensor
				{
					if (maze2.isWall(x - 1 - i, y - 1)) {
						n.isWall(x - 1 - i, y - 1);
						break;
					}

					if (!maze2.isWall(x - 1 - i, y - 1)
							&& !maze2.gettraversedPath(x - 1 - i, y - 1)) {
						maze2.isSensed(x - 1 - i, y - 1);
						n.isExplored(x - 1 - i, y - 1);
						// printField(field);
					}

				}
				
				for (int i = 1; i < frontMiddle; i++)// left sensor
				{
					if (maze2.isWall(x - 1 - i, y )) {
						n.isWall(x - 1 - i, y  );
						break;
					}
					
					if (!maze2.isWall(x + 1 + i, y )
							&& !maze2.gettraversedPath(x - 1 - i, y )) {
						maze2.isSensed(x - 1 - i, y);
						n.isExplored(x - 1 - i, y );
						// printField(field);
					}

				}
				
				
				for (int i = 1; i < frontRight; i++) {// right sensor
					if (maze2.isWall(x - 1 -i, y+1)) {
						n.isWall(x - 1 -i, y+1);
						break;
					}
					if (!maze2.isWall(x - 1 -i, y+1)
							&& !maze2.gettraversedPath(x - 1 -i, y+1)) {
						maze2.isSensed(x - 1 -i, y+1);
						// printField(field);
						n.isExplored(x - 1 -i, y+1);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
		if (direction == 2) {// east
			try {
				for (int i = 1; i < frontLeft; i++)// left sensor
				{
					if (maze2.isWall(x - 1, y + 1 + i)) {
						n.isWall(x - 1, y + 1 + i);
						break;
					}
					if (!maze2.isWall(x - 1, y + 1 + i)
							&& !maze2.gettraversedPath(x - 1, y + i + 1)) {
						maze2.isSensed(x - 1, y + 1 + i);
						n.isExplored(x - 1, y + 1 + i);
						// printField(field);
					}

				}
				
				for (int i = 1; i < frontMiddle; i++)// left sensor
				{
					if (maze2.isWall(x , y + 1 + i)) {
						n.isWall(x , y + 1 + i);
						break;
					}
					
					if (!maze2.isWall(x , y + 1 + i)
							&& !maze2.gettraversedPath(x , y + 1 + i)) {
						maze2.isSensed(x , y + 1 + i);
						n.isExplored(x , y + 1 + i);
						// printField(field);
					}

				}
				
				for (int i = 1; i < frontRight; i++) {// right sensor
					if (maze2.isWall(x + 1, y + 1 + i)) {
						n.isWall(x + 1, y + 1 + i);
						break;
					}
					if (!maze2.isWall(x + 1, y + 1 + i)
							&& !maze2.gettraversedPath(x + 1, y + 1 + i)) {
						maze2.isSensed(x + 1, y + 1 + i);
						// printField(field);
						n.isExplored(x + 1, y + 1 + i);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
		if (direction == 4) {//west
			try {
				for (int i = 1; i < frontLeft; i++)// left sensor
				{
					if (maze2.isWall(x + 1, y - 1 - i)) {
						n.isWall(x + 1, y - 1 - i);
						break;
					}
					if (!maze2.isWall(x + 1, y - 1 - i)
							&& !maze2.gettraversedPath(x + 1, y - 1 - i)) {
						maze2.isSensed(x + 1, y - 1 - i);
						// printField(field);
						n.isExplored(x + 1, y - 1 - i);
					}

				}
				
				for (int i = 1; i < frontMiddle; i++)// left sensor
				{
					if (maze2.isWall(x , y - 1 - i)) {
						n.isWall(x , y - 1 - i);
						break;
					}
					
					if (!maze2.isWall(x , y - 1 - i)
							&& !maze2.gettraversedPath(x , y - 1 - i)) {
						maze2.isSensed(x , y - 1 - i);
						n.isExplored(x , y - 1 - i);
						// printField(field);
					}

				}
				
				for (int i = 1; i < frontRight; i++) {// right sensor
					if (maze2.isWall(x + 1, y - 1 - i)) {
						n.isWall(x + 1, y - 1 - i);
						break;
					}
					if (!maze2.isWall(x + 1, y - 1 - i)
							&& !maze2.gettraversedPath(x + 1, y - 1 - i)) {
						maze2.isSensed(x + 1, y - 1 - i);
						// printField(field);
						n.isExplored(x + 1, y - 1 - i);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
	}

	public static void leftSensor(updatedMaze n,TwoDimGrid maze2, int direction, int x, int y, int left){
	    if(direction==1){//south
	    try{
	        for (int i=1;i<left;i++)
	        {
	          if (maze2.isWall(x,y+i+1)){
	        	  n.isWall(x, y+i+1);
	              break;}
	          if (!maze2.isWall(x,y+i+1)&&!maze2.gettraversedPath(x,y+1+i)){
	        	  maze2.isSensed(x,y+i+1);
	             //printField(field);
	        	  n.isExplored(x, y+1+i);
	         }
	         
	        }}
	        catch(ArrayIndexOutOfBoundsException e){
		            return;
		        }}
	    if(direction==3){//north
	    try{
	        for (int i=1;i<left;i++)
	        {
	          if (maze2.isWall(x,y-i-1)){
	        	  n.isWall(x, y-1-i);
	              break;}
	          if (!maze2.isWall(x,y-i-1)&&!maze2.gettraversedPath(x,y-i-1)){
	        	  maze2.isSensed(x,y-i-1);
	             //printField(field);
	        	  n.isExplored(x, y-i-1);
	         }
	         
	        }}
	        catch(ArrayIndexOutOfBoundsException e){
		            return;
		        }}
	    if (direction==2){//east
	        try{
	          for (int i=1;i<left;i++){
	            if (maze2.isWall(x-1-i,y)){
	            	 n.isWall(x-1-i, y);
	              break;}
	          if (!maze2.isWall(x-1-i,y)&&!maze2.gettraversedPath(x-1-i,y)){
	        	  maze2.isSensed(x-1-i,y);
	        	  n.isExplored(x-1-i, y);
	             //printField(field);
	         }
	          
	        }}
	        catch(ArrayIndexOutOfBoundsException e){
		            return;
		        }}
	    if (direction==4){//west
	        try{
	          for (int i=1;i<left;i++){//right sensor
	            if (maze2.isWall(x+1+i,y)){
	            	 n.isWall(x+1+i, y);
	              break;}
	          if (!maze2.isWall(x+1+i,y)&&!maze2.gettraversedPath(x+1+i,y)){
	        	  maze2.isSensed(x+1+i,y);
	        	  n.isExplored(x+1+i, y);
	             //printField(field);
	         }
	          
	        }}
	        catch(ArrayIndexOutOfBoundsException e){
		            return;
		        }}
	}
	
	
	public static void rightSensor(updatedMaze n, TwoDimGrid maze2,
			int direction, int x, int y, int right) {
		if (direction == 1) {// south
			try {
				for (int i = 1; i < right; i++) {// right sensor
					if (maze2.isWall(x, y - 1- i)) {
						n.isWall(x, y - 1- i);
						break;
					}

					if (!maze2.isWall(x, y -1 - i)
							&& !maze2.gettraversedPath(x, y -1 - i)) {
						maze2.isSensed(x, y -1 - i);
						n.isExplored(x, y - 1- i);
						// printField(field);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
		if (direction == 3) {// north
			try {
				for (int i = 1; i < right; i++)// left sensor
				{
					if (maze2.isWall(x, y + i + 1)) {
						n.isWall(x, y + i +1);
						break;
					}
					if (!maze2.isWall(x, y + i +1)
							&& !maze2.gettraversedPath(x, y + i + 1)) {
						maze2.isSensed(x, y + i + 1);
						n.isExplored(x, y + i +1);
						// printField(field);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
		if (direction == 2) {// east
			try {
				for (int i = 1; i < right; i++) {// right sensor
					if (maze2.isWall(x + i +1, y)) {
						n.isWall(x + i +1, y);
						break;
					}
					if (!maze2.isWall(x + i +1, y)
							&& !maze2.gettraversedPath(x + i +1, y)) {
						maze2.isSensed(x + i +1, y);
						n.isExplored(x + i +1, y);
						// printField(field);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
		if (direction == 4) {// west
			try {
				for (int i = 1; i < right; i++) {// right sensor
					if (maze2.isWall(x - i -1, y)) {
						n.isWall(x - i -1, y);
						break;
					}
					if (!maze2.isWall(x - i -1, y)
							&& !maze2.gettraversedPath(x - i -1, y)) {
						maze2.isSensed(x - i -1, y);
						n.isExplored(x - i -1, y);
						// printField(field);
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
	}

	public void updateSensor(updatedMaze n, TwoDimGrid maze2, int direction, int x, int y){
		int frontLeft, frontMiddle, frontRight, left, right;
		frontLeft = frontMiddle = frontRight = 4;
		left = 4;
		right = 8;
		//read from the arduino sensor signals
		front(n, maze2, direction, x, y, frontLeft,  frontMiddle, frontRight);
		leftSensor(n, maze2, direction, x, y, left);
		rightSensor(n, maze2, direction, x, y, right);
	}
	
	public void mazeTraverseTurn(updatedMaze n, TwoDimGrid maze2, int direction,
			int x, int y) throws InterruptedException {
	
		maze2.updateCurrentPosition(direction, x, y);// set current robot
														// position 2*2 to
														// yellow
		n.updateCurrentPosition(x, y);// set the number grid

		Thread.sleep(SLEEP_TIME/2);
		
		
		updateSensor(n, maze2, direction, x, y);
		
		Thread.sleep(SLEEP_TIME);// freeze time
        
		maze2.traversedPath(x, y);
		
		switch (direction){
		case 1:
			mazeTraverse(n, maze, direction,x+1, y);
			break;
		case 3:
			mazeTraverse(n, maze, direction,x-1, y);
			break;
		case 2:
			mazeTraverse(n, maze, direction,x, y+1);
			break;
		case 4:
			mazeTraverse(n, maze, direction,x, y-1);
			break;
		}
	}
	
	public void mazeTraverse(updatedMaze n, TwoDimGrid maze2, int direction,
			int x, int y) throws InterruptedException {
	
		maze2.updateCurrentPosition(direction, x, y);// set current robot
														// position 2*2 to
														// yellow
		n.updateCurrentPosition(x, y);// set the number grid

		Thread.sleep(SLEEP_TIME/2);
		
		
		updateSensor(n, maze2, direction, x, y);
		
		Thread.sleep(SLEEP_TIME);// freeze time

		maze2.traversedPath(x, y);// mark position as traversedPath

		
	
		// System.out.println(count);
		if (maze2.gettraversedPath(15, 20)) {


			System.out.println("Maze explored");

			for (int i = 1; i < 16; i++) {
				for (int j = 1; j < 21; j++) {

					n.checkUnExplored(i, j);

				}

			}

			// Thread.sleep(5000);
			String s = "11";
			for (int i = 1; i < 16; i++) {
				for (int j = 1; j < 21; j++) {

					s = s.concat(n.getText(i, j));

				}

			}
			s = s.concat("11");
			System.out.println(s);
			BigInteger b = new BigInteger(s, 2);
			// System.out.println(b.toString(6));
			// BigInteger toHex=new BigInteger(s,10);
			String toHex = b.toString(16);
			// int foo = Integer.parseInt(s, 2);
			System.out.println("The value in Hex is: " + toHex);
			// System.out.println(Integer.toHexString(Integer.parseInt(s,6)));
			// System.exit(0);
			for (int i = 1; i < 16; i++) {
				for (int j = 1; j < 21; j++) {
					if (n.getExplored(i, j) == true)
						n.setZero(i, j);
					else
						n.clearUnExplored(i, j);
				}
			}
			
			String h = "";
			for (int i = 1; i < 16; i++) {
				for (int j = 1; j < 21; j++) {

					h = h.concat(n.getText(i, j));

				}

			}
			System.out.println(h);

			BigInteger toHex1 = new BigInteger(h, 2);
			String toHex2 = toHex1.toString(16);
			System.out.println("The value in Hex is: " + toHex2);

			System.out.println("exploreEnds");

			// Astar algorithm

			AStar AS = new AStar(maze2);

			AS.start_AStar();
			mazemaze.solvingThread.stop();
			mazemaze.solvingThread1.stop();

			return;

		}

		// traversedDirection[4] to mark whether traversed this direction
		Boolean[] traversedDirection = new Boolean[5]; // set the array together
														// with the cordinate
		Arrays.fill(traversedDirection, Boolean.FALSE);
		traversedDirection[(direction + 1) % 4 + 1] = true;// no need to go the
		// reverse direction
		
		
		// start recursion
		//mazeTraverse(n, maze2, 2, x, y - 1);
		//traversedDirection[3] = true;
		
		switch (direction) {
		case 1:
			if (maze2.isWalkable(2, x, y)){
				mazeTraverseTurn(n, maze2, 2, x, y);
			}
			else if (maze2.isWalkable(1, x, y)){
				mazeTraverse(n, maze2, 1, x+1, y);
			}
			break;
		case 2:
			 if (maze2.isWalkable(3, x, y)){
					mazeTraverseTurn(n, maze2, 3, x, y);
			 }
			else if (maze2.isWalkable(2, x, y)){
				mazeTraverse(n, maze2, 2, x, y + 1);
			}
			
			else if (maze2.isWalkable(1, x, y)){
				mazeTraverseTurn(n, maze2, 1, x, y);
			}
			break;
		case 3:
			if (maze2.isWalkable(3, x, y)){
				mazeTraverse(n, maze2, 3, x - 1, y);
			}
			else if (maze2.isWalkable(2, x, y)){
				mazeTraverseTurn(n, maze2, 2, x, y+1);
			}
			break;
		case 4:
			if (maze2.isWalkable(1, x, y)){
				mazeTraverseTurn(n, maze2, 1, x, y);
			}
			else if (maze2.isWalkable(4, x, y)){
				mazeTraverse(n, maze2, 4, x, y-1);
			}
			break;

		}
		
		//check all direction see if traversed
		
		
		// move back the robot position
		// mark it as traversed
		// remove from the StackArray

	}

	public void findMazePath() throws InterruptedException {
		mazeTraverse(updatemaze, maze, 2, 2, 2);
		//mazeTraverse2(updatemaze, maze, 1, 2, 2);// to return no path only when
												// both two no path
	}

	/**
	 * Solve the maze.
	 */
	public void run() {
		try {

			findMazePath();

		} catch (InterruptedException e) {
			// Thread stops.
		}
	}

	public static void printField(char[][] field) {
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 20; y++) {
				System.out.print(field[x][y]);
			}
			System.out.println();
		}
		System.out.print("\n\n");
	}
}
