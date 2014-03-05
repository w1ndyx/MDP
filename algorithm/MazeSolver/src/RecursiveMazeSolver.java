//package src;
/**
 * A program to solve a 2D maze.  The solution starts in the top-left corner.
 * It is considered solved when the bottom-right corner is reached.
 * 
 * This solver uses recursion to find a path.  Starting at a given square,
 * it tentatively places that square on the path and then searches for a 
 * path from a neighboring square to the destination.  If it can't find
 * a path recursively, it marks this square as a dead-end and backtracks.
 * 
 * @author Barbara Lerner
 * @version Apr 28, 2010
 *
 */
public class RecursiveMazeSolver implements Runnable {
	private static final int SLEEP_TIME = 300;

	// The maze
	private TwoDimGrid maze;
	
	/**
	 * Create a maze solver for a grid
	 * @param m the maze to solve
	 */
	public RecursiveMazeSolver(TwoDimGrid m) {
		maze = m;
	}
	
	/**
	 * Find a path in the maze starting at the top left corner and
	 * searching for the bottom right corner
	 * @return true if a path is found
	 * @throws InterruptedException if the thread that the solver is
	 * 	running in is interrupted.
	 */
	public boolean findMazePath() throws InterruptedException {
		return findMazePath(1, 2, 0);
	}
	
	/**
	 * Find a path in the maze from position x, y
	 * @param x the x coordinate to solve from
	 * @param y the y coordinate to solve from
	 * @return true if a path is found from here to the bottom right corner of the maze
	 * @throws InterruptedException if the thread that the solver is running in is interrupted
	 */
	private boolean findMazePath(int x, int y, int distance) throws InterruptedException {
		// Attempting to look at a non-existent location
		if (x < 0 || y < 0 || x >= maze.getNumCols() || y >= maze.getNumRows()) {
			return false;
		}
		
		// Attempting to go to a square that represents a wall
		else if (maze.isWall(x, y)) {
			return false;
		}
		
		// Already explored
		else if (maze.alreadyVisited(x, y)) {
			return false;
		}
		
		// Found the bottom right corner!
		else if (x == maze.getNumCols() - 1 && y == maze.getNumRows() - 1) {
			// Color the square to show that it is part of the solution.
			maze.onPath (x, y);
			maze.setLabel(x, y, "" + distance);
			Thread.sleep(SLEEP_TIME);
			return true;
		}
		
		else {
			// Color this square to indicate it is being explored.  Sleep so user can see the maze update. 
			maze.explore (x, y);
			maze.setLabel(x, y, "" + distance);
			Thread.sleep(SLEEP_TIME);
			
			// Recursively search in all 4 directions.
			/*if ( findMazePath(x+1, y, distance+1)  || findMazePath(x, y+1, distance+1)|| findMazePath(x, y-1, distance+1)||findMazePath (x-1, y, distance+1) ) {
				// One of the 4 paths was successful!  Color the square as part of the solution.
				maze.onPath(x, y);
				Thread.sleep(SLEEP_TIME);
		
				return true;
			}*/
			if ( findMazePath(x+1, y, distance+1) ) {
				// One of the 4 paths was successful!  Color the square as part of the solution.
				maze.onPath(x, y);
				Thread.sleep(SLEEP_TIME);
		
				return true;
			}
			if ( findMazePath(x, y+1, distance+1) ) {
				// One of the 4 paths was successful!  Color the square as part of the solution.
				maze.onPath(x, y);
				Thread.sleep(SLEEP_TIME);
		
				return true;
			}
			if ( findMazePath(x, y-1, distance+1) ) {
				// One of the 4 paths was successful!  Color the square as part of the solution.
				maze.onPath(x, y);
				Thread.sleep(SLEEP_TIME);
		
				return true;
			}
			if ( findMazePath(x-1, y, distance+1) ) {
				// One of the 4 paths was successful!  Color the square as part of the solution.
				maze.onPath(x, y);
				Thread.sleep(SLEEP_TIME);	
				return true;
			}
			
			
			else {
				// None of the 4 paths worked out.  Color this square as a dead end.  Sleep
				// so the user sees the animation.
				maze.deadEnd(x, y);
				Thread.sleep(SLEEP_TIME);
				return false;
			}
		}
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
}
