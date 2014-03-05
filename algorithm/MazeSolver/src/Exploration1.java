
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;

/**
 * 
 * @author stoh003
 */
public class Exploration1 implements Runnable {
	protected static final int SLEEP_TIME = 100;
	private static TwoDimGrid maze;
	private static updatedMaze updatemaze;
	private static Maze mazemaze;
	private static Reminder reminder;

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

	public void mazeTraverse(updatedMaze n, TwoDimGrid maze2, int direction,
			int x, int y) throws InterruptedException {
		
		int frontLeft, frontMiddle, frontRight, left, right;
		frontLeft = frontMiddle = frontRight = 4;
		left = 4;
		right = 8;
		//read from the arduino sensor signals
		
	
		// System.out.println(count);
		if (maze2.gettraversedPath(15, 20)) {

			front(n, maze2, direction, x, y, frontLeft,  frontMiddle, frontRight);
			leftSensor(n, maze2, direction, x, y, left);
			rightSensor(n, maze2, direction, x, y, right);

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
			Thread.sleep(8000);
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

		

		maze2.updateCurrentPosition(direction, x, y);// set current robot
														// position 2*2 to
														// yellow
		n.updateCurrentPosition(x, y);// set the number grid

		Thread.sleep(SLEEP_TIME/2);
		
		front(n, maze2, direction, x, y, frontLeft,  frontMiddle, frontRight);
		leftSensor(n, maze2, direction, x, y, left);
		rightSensor(n, maze2, direction, x, y, right);
		
		Thread.sleep(SLEEP_TIME);// freeze time

		maze2.traversedPath(x, y);// mark position as traversedPath

		// start recursion
		if (direction == 4) {// west , should go down, the go up, then go left

			// go to the south
			if (maze2.isUnexplored(x + 2, y)
					&& maze2.isUnexplored(x + 2, y - 1)) {//south unexplored

				mazeTraverse(n, maze2, 1, x, y - 1);
				traversedDirection[1] = true;

			}

			// go to north
			if (maze2.isUnexplored(x - 1, y)
					&& maze2.isUnexplored(x - 1, y - 1)) {//north unexplored
				mazeTraverse(n, maze2, 3, x + 1, y);
				traversedDirection[3] = true;

			}

			// go to north
			if ((maze2.isWall(x, y - 2) || maze2.isWall(x + 1, y - 2))
					&& ((maze2.isWall(x + 2, y) || maze2.isWall(x + 2, y - 1)))
					&& (!maze2.isWall(x - 1, y) && !maze2.isWall(x - 1, y - 1))) {
				if (!traversedDirection[3]) {//there is either wall at west and either wall at south and north dont have wall
					mazeTraverse(n, maze2, 3, x + 1, y);
					traversedDirection[3] = true;
				}

			}

			// go to south
			if (maze2.isWall(x + 2, y + 1) && !maze2.isWall(x + 2, y)
					&& !maze2.isWall(x + 2, y - 1) && y != 20) {//turn around the obstacle and move south if south no walls
				if (!traversedDirection[1]) {
					mazeTraverse(n, maze2, 1, x, y - 1);
					traversedDirection[1] = true;
				}
			}

			// go to north
			if (maze2.isWall(x - 1, y + 1) && !maze2.isWall(x - 1, y)
					&& !maze2.isWall(x - 1, y - 1) && (y != 20)) {//turn around the obstacle and move north if north no walls
				if (!traversedDirection[3]) {
					mazeTraverse(n, maze2, 3, x, y - 1);
					traversedDirection[3] = true;
				}

			}

			// go to west
			if (!maze2.isWall(x, y - 2) && !maze2.isWall(x + 1, y - 2))// keep moving forward
			{
				mazeTraverse(n, maze2, 4, x, y - 1);
				traversedDirection[4] = true;

			}

			// go to south
			if (maze2.isWall(x, y - 2) && maze2.isWall(x + 1, y - 2)
					&& maze2.isWall(x - 1, y) && maze2.isWall(x - 1, y - 1)) {//there is either wall at west and either wall at south and north dont have wall
				if (!traversedDirection[1]) {
					mazeTraverse(n, maze2, 1, x, y - 1);
					traversedDirection[1] = true;
				}
			}

			// go to south
			if (maze2.isWall(x, y - 2) && maze2.isWall(x + 1, y - 2)
					|| maze2.isWall(x, y - 2) || maze2.isWall(x + 1, y - 2)) {//if there's both or either wall at west, turn default south
				if (!traversedDirection[1]) {
					mazeTraverse(n, maze2, 1, x, y - 1);
					traversedDirection[1] = true;
				}
			}

		} else if (direction == 2) {

			// north
			if (maze2.isUnexplored(x - 2, y)
					&& maze2.isUnexplored(x - 2, y + 1))  //north unexplored
			{
				mazeTraverse(n, maze2, 3, x, y + 1);
				traversedDirection[3] = true;
			}

			// south
			if (maze2.isUnexplored(x + 1, y)
					&& maze2.isUnexplored(x + 1, y + 1)) {//south unexplored
				mazeTraverse(n, maze2, 1, x - 1, y);
				traversedDirection[1] = true;
			}

			// south
			if (((maze2.isWall(x, y + 2) && maze2.isWall(x - 2, y + 1)) || (maze2
					.isWall(x - 1, y + 2) && maze2.isWall(x - 2, y + 1)))
					&& x != 15) {//there is either wall at east and either wall at north and south dont have wall and not at the bottom of the maze

				if (!traversedDirection[1]) {
					mazeTraverse(n, maze2, 1, x - 1, y);
					traversedDirection[1] = true;
				}

			}

			
			// north
			if ((maze2.isWall(x, y + 2) && maze2.isWall(x - 2, y + 1))
					|| (maze2.isWall(x - 1, y + 2) && maze2
							.isWall(x - 2, y + 1))) {//there is either wall at east and either wall at north and south dont have wall and at the bottom of the maze
				if (!traversedDirection[3]) {
					mazeTraverse(n, maze2, 3, x, y + 1);
					traversedDirection[3] = true;
				}
			}

			// south
			if (((maze2.isWall(x, y + 2) && maze2.isWall(x - 1, y + 2))
					|| maze2.isWall(x, y + 2) || maze2.isWall(x - 1, y + 2))
					&& y == 19) {//if there is both wall or either wall near the right edge of the maze. then turn south
				System.out.println("go here:" + x + " " + y);
				if (!traversedDirection[1]) {
					mazeTraverse(n, maze2, 1, x - 1, y);
					traversedDirection[1] = true;
				}
			}
						
			// north
			if ((maze2.isWall(x, y + 2) && maze2.isWall(x - 1, y + 2))
					|| maze2.isWall(x, y + 2) || maze2.isWall(x - 1, y + 2)) {//if there is both wall or either wall at east, default turn north
				if (!traversedDirection[3]) {
					mazeTraverse(n, maze2, 3, x, y + 1);
					traversedDirection[3] = true;
				}
			}

			// east
			if (maze2.isWall(x - 2, y - 1) && !maze2.isWall(x - 2, y)
					&& !maze2.isWall(x - 2, y + 1) && x == 15) {
				mazeTraverse(n, maze2, 2, x, y + 1);
				traversedDirection[2] = true;
			}

			// north
			if (maze2.isWall(x - 2, y - 1) && !maze2.isWall(x - 2, y)
					&& !maze2.isWall(x - 2, y + 1)) {
				if (!traversedDirection[3]) {
					mazeTraverse(n, maze2, 3, x, y + 1);
					traversedDirection[3] = true;
				}
			}

			// east
			if (!maze2.isWall(x, y + 2) && !maze2.isWall(x - 1, y + 2)) {
				if (!traversedDirection[2]) {
					mazeTraverse(n, maze2, 2, x, y + 1);
					traversedDirection[2] = true;
				}
			}

			// traverse all other directions

		} else if (direction == 1) {
			
			// east
			if (maze2.gettraversedPath(x + 2, y)
					&& maze2.gettraversedPath(x + 2, y + 1)
					&& maze2.isWall(x - 1, y + 2)
					&& (!maze2.isWall(x, y + 2) && !maze2.isWall(x + 1, y + 2))) {
				mazeTraverse(n, maze2, 2, x + 1, y);
				traversedDirection[2] = true;
			}
			
			// south
			if (!maze2.isWall(x + 2, y + 1) && !maze2.isWall(x + 2, y)) {
				
				System.out.println("go here south and south"+x+"   "+y);
				mazeTraverse(n, maze2, 1, x + 1, y);
				traversedDirection[1] = true;
			}

			// east
			if ((((maze2.isWall(x + 2, y) && maze2.isWall(x + 2, y + 1)) && maze2
					.isWall(x, y + 2)) || (maze2.isWall(x + 2, y)
					&& maze2.isWall(x + 2, y + 1) && maze2.isWall(x + 1, y + 2)))
					&& x == 14) {
				if (!traversedDirection[2]) {
					mazeTraverse(n, maze2, 2, x + 1, y);
					traversedDirection[2] = true;
				}
			}

			// west
			if (maze2.isWall(x + 2, y) && maze2.isWall(x + 2, y + 1)
					&& maze2.isWall(x, y + 2) && maze2.isWall(x + 1, y + 2)) {
				mazeTraverse(n, maze2, 4, x, y + 1);
				traversedDirection[4] = true;
			}

			// west
			if (maze2.isWall(x + 2, y) && maze2.isWall(x, y + 2)
					&& maze2.isWall(x + 1, y + 2) || maze2.isWall(x + 2, y + 1)
					&& maze2.isWall(x, y + 2) && maze2.isWall(x + 1, y + 2)) {
				if (!traversedDirection[4]) {
					mazeTraverse(n, maze2, 4, x, y + 1);
					traversedDirection[4] = true;
				}
			}

			// east
			if (maze2.isWall(x + 2, y) && maze2.isWall(x + 2, y + 1)
					|| maze2.isWall(x + 2, y) || maze2.isWall(x + 2, y + 1)) {
				if (!traversedDirection[2]) {
					mazeTraverse(n, maze2, 2, x + 1, y);
					traversedDirection[2] = true;
				}
			}

		} else if (direction == 3) {

			// north
			if (maze2.gettraversedPath(x - 2, y)
					&& maze2.gettraversedPath(x - 2, y - 1)
					&& maze2.isWall(x + 1, y + 1)
					&& (!maze2.isWall(x, y + 1) && !maze2.isWall(x - 1, y + 1))) {
				mazeTraverse(n, maze2, 2, x, y - 1);
				traversedDirection[3] = true;
			}
			// north
			if (!maze2.isWall(x - 2, y) && !maze2.isWall(x - 2, y - 1)) {
				if (!traversedDirection[3]) {
					mazeTraverse(n, maze2, 3, x - 1, y);
					traversedDirection[3] = true;
				}
			}

			// east
			if (((maze2.isWall(x - 2, y) && maze2.isWall(x - 2, y - 1))
					|| maze2.isWall(x - 2, y) || maze2.isWall(x - 2, y - 1))
					&& !maze2.isWall(x, y + 1) && !maze2.isWall(x - 1, y + 1))// available
																				// space
																				// on
																				// right
																				// and
																				// wall
																				// ahead
			{
				mazeTraverse(n, maze2, 2, x, y - 1);
				traversedDirection[2] = true;
			}

			// north
			if (maze2.isWall(x, y - 2) && !maze2.isWall(x - 2, y)
					&& !maze2.isWall(x - 2, y - 1)) {
				if (!traversedDirection[3]) {
					mazeTraverse(n, maze2, 3, x - 1, y);
					traversedDirection[3] = true;
				}
			}

			// east
			if (maze2.isWall(x + 1, y + 1) && !maze2.isWall(x, y + 1)
					&& !maze2.isWall(x - 1, y + 1) && x != 15) {
				if (!traversedDirection[2]) {
					mazeTraverse(n, maze2, 2, x, y - 1);
					traversedDirection[2] = true;
				}
			}

			// east
			if (maze2.isWall(x - 2, y) && maze2.isWall(x - 2, y - 1)
					&& maze2.isWall(x, y + 1) && maze2.isWall(x - 1, y + 1)
					&& x == 2) {
				if (!traversedDirection[2]) {
					mazeTraverse(n, maze2, 2, x, y - 1);
					traversedDirection[2] = true;
				}
			}

			// west
			if (maze2.isWall(x - 2, y) && maze2.isWall(x - 2, y - 1)
					&& maze2.isWall(x, y + 1) && maze2.isWall(x - 1, y + 1)
					&& x != 2) {
				mazeTraverse(n, maze2, 4, x - 1, y);
				traversedDirection[4] = true;
			}

			// west
			if ((maze2.isWall(x, y + 1) && maze2.isWall(x - 2, y))
					|| (maze2.isWall(x - 1, y + 1) && maze2.isWall(x - 2, y))
					&& x != 2) {
				if (!traversedDirection[4]) {
					mazeTraverse(n, maze2, 4, x - 1, y);
					traversedDirection[4] = true;
				}
			}

			// west
			if ((maze2.isWall(x - 2, y - 1) && maze2.isWall(x - 1, y + 1))
					|| (maze2.isWall(x - 2, y - 1) && maze2.isWall(x, y + 1))
					&& x != 2) {
				if (!traversedDirection[4]) {
					mazeTraverse(n, maze2, 4, x - 1, y);
					traversedDirection[4] = true;
				}
			}

			// traverse the map
		}

		// move back the robot position
		// mark it as traversed
		// remove from the StackArray

	}

	public void findMazePath() throws InterruptedException {
		mazeTraverse(updatemaze, maze, 1, 1, 2);
		mazeTraverse(updatemaze, maze, 2, 1, 2);// to return no path only when
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
