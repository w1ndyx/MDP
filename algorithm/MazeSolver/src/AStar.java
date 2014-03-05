


		import java.awt.Color;
import java.util.ArrayList;




public class AStar {


		// TODO Auto-generated method stub


		/*
		  Legend: 0 -> unaccessible
		  		  >0 -> accessible
		  		  -1 -> obstacle
		  		  -2 -> unaccessible
		  		  -7 -> shortest path
		  
		  GUI: -1 -> wall /obstacle
		  		0 -> unaccessible
		  		1> -> empty
		  		-7 -> route
		*/


			
			
			private static TwoDimGrid m = new TwoDimGrid();
			
			 private static final int length = 22;
			 private static  final int breath = 17;
			 private static  int start_pt_row = 1;   	
			 private static  int start_pt_col = 1;		
			 private static int goal_pt_row= 14;		// 14
			 private static  int goal_pt_col=19;			// 19
			 
			 
			 private static boolean firstgrid = true;
			 
			 private static int curr_row, curr_col;
			 private static int curr_row_2, curr_col_2;
			 
			 
			 private static Direction face;
			 
			 private static int [][]maze = new int [breath][length];
			 
			
			public AStar(TwoDimGrid m)
			{
				this.m=m;
				clonemap();
			}
			
			public AStar(TwoDimGrid m, int end_row, int end_col )				// for Termination function
			{
				this.m = m;
				clonemap();
				goal_pt_row = end_row;
				goal_pt_col = end_col;
			}
			
			public enum Direction
			{
				 North, South, East, West;
			}

			
			
			 /*
			 
			 public static int [][] maze ={
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,-1,0,-1,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};
					
			 */
			 private static int [][] fn= new int[breath][length];
			 
			 /*
			  Clone explored map over to Astar algorithm
			  */
			 public static void clonemap()			
			 {
				 try{
				
				 for (int i =0; i < breath; i++)
				 {
					 for (int j = 0; j<length; j++)
					 {
						 maze[i][j] = Integer.parseInt(m.labels[i][j].getText());
					 }
				 }
				 
				 }
				 catch(Exception e)					// incase label do not work
				 {
					 for (int i =0; i < breath; i++)
					 {
						 for (int j = 0; j<length; j++)
						 {
							 
							 if(m.grid[i][j].getBackground() == Color.black)
							 {
								 maze[i][j] = -1;
							 }
							 else
							 {
								 maze[i][j]=0;
							 }
							
						 }
					 }
				 }
				 
				 print();
				 
			 }
			 
			 
	public static void start_AStar() {
				 
				 int row, col;
				 for (row = 0 ; row < breath-1;row++)					
				 {
					 for ( col = 0; col< length -1;col++);
					 {
						fn[row][col] = 0;					// fn declaration
					 }
				 }
				
				 ArrayList<Integer> Al_row= new ArrayList<Integer>();
				 ArrayList<Integer> Al_col= new ArrayList<Integer>();
				 Al_row.add(start_pt_row);
				 Al_col.add(start_pt_col);
				 safety_grid();
				 print();
				 
				 
				 calculate(Al_row, Al_col);
				// print();
				
				 
				 directioncheck();
				 System.out.println(maze[curr_row][curr_col]);
				 retreat(maze[curr_row][curr_col]);			// trace back
				 
				 
				 
				 // turn back to be implemented
				 
				 int t_row, t_col;
				 
				 if(face == Direction.North)
				 	{
				 		face =Direction.South;
				 		
				 	}
				 	else if (face == Direction.West)
				 	{
				 		face = Direction.East;
				 	}
			/*	 t_row = curr_row;
		 		 t_col = curr_col;
		 		 curr_row= curr_row_2;
		 		 curr_col= curr_col_2;
		 		 curr_row_2 = t_row;
		 		 curr_col_2 = t_col;
				 */
				 
				 
				 speedup(curr_row,curr_col, curr_row_2, curr_col_2);			// pass in top left hand side of the grid
			 }
			 
				
			private static void safety_grid()
				 {
					 
					 for (int i = 1; i < breath -1;i++)
					 {
						 for(int j = 1; j<length - 1;j++)
						 {
							 if(maze[i][j] == -1)
							 {
								 for(int k = -1; k<2;k=k+2)
								 {

										 if(maze[i+k][j] != -1)
										 {
											 maze[i+k][j] = -2;
										 }
										 if(maze[i][j+k] != -1)
										 {
											 maze[i][j+k] = -2;
										 }
								 }
							 } 
							if(j >3 && j<length - 4)
							{
								 if(maze[1][j] != -1)
								 {
									 maze[1][j] = -2;
								 }
								 if (maze[breath -2][j] != -1)
								 {
									 maze[breath-2][j] = -2;
								 }
							}
							 
							 
						 }
						 
						 if(i > 3 && i< breath -4)
						 {
							 if (maze[i][1] != -1)
							 {
								 maze[i][1] = -2;
							 }
							 if (maze[i][length-2] != -1)
							 {
								 maze[i][length-2] = -2;
							 }
						 }
						 
						 
					 }
					 
					
					 
					 
					 
					 
					 
				 }
				 
				
				
			private static void calculate(ArrayList<Integer> Al_row, ArrayList<Integer> Al_col)
			 {
				 int temp_fn;
				
				 while(!Al_row.isEmpty())
				 {
					 
					 temp_fn = 100;
					 curr_row = Al_row.get(0);
					 curr_col = Al_col.get(0);
					 
					 if (maze[curr_row][curr_col]== 0)			// skip duplicate
					 {
						 
						 if(squeeze())
						 {
						 
							 for(int i = -1; i<=1;i=i+2)
							 {
								 
							// System.out.println(fn[curr_row +i][curr_col] < temp_fn);
								 if ( fn[curr_row +i][curr_col]>0 &&  fn[curr_row +i][curr_col] < temp_fn)
								 {
									 temp_fn= fn[curr_row+i][curr_col];
								 }
								 
								 //System.out.println( fn[curr_row][curr_col+i] < temp_fn );
								 
								 if ( fn[curr_row ][curr_col+i]>0 &&  fn[curr_row][curr_col+i] < temp_fn)
								 {
									 temp_fn= fn[curr_row][curr_col+i];
								 }
							 
							 }
							 
							 
							 if (firstgrid)//curr_row == start_pt_row && curr_col == start_pt_col)
							 {
								 firstgrid = false;
									fn[curr_row][curr_col] = 1;
							 }
							 else
								 {
								 fn[curr_row][curr_col] = temp_fn+1;
								 }
							 
							 maze[curr_row][curr_col]= fn[curr_row][curr_col] + calculate_gn(curr_row,curr_col);
							 
							 for( int i = -1; i<=1;i=i+2)
							 {
								if (maze[curr_row+i][curr_col] == 0)
								{
									Al_row.add(curr_row+i);
									Al_col.add(curr_col);
									//System.out.println(curr_row+i +"," + curr_col +" added");
									
								}
								if (maze[curr_row][curr_col+i]==0)
								{
									Al_row.add(curr_row);
									Al_col.add(curr_col+i);
									//System.out.println(curr_row +"," + (curr_col+i) +" added");
								}
							 }
						 }
					 }
						 Al_row.remove(0);
						 Al_col.remove(0);
					 
				 }
				
			 }
			 
			
			 private static boolean squeeze()
			 {
				 if( ! firstgrid)
				 {
					 if(maze[curr_row][curr_col-1] ==-1 && maze[curr_row][curr_col+1] ==-1) // check left right <0 shows only 1 slot
					 {
						 maze[curr_row][curr_col] = -3;
						 return false;
						 
					 }
					 else if (maze[curr_row-1][curr_col]==-1 && maze[curr_row+1][curr_col]==-1)	//check up down <0 shows only 1 slot 
					 {
						 maze[curr_row][curr_col] = -3;
						 return false;
					 }
					 
					 //
					 
					/* else if (maze[curr_row - 1][curr_col+ 1]==-1 && maze[curr_row+ 1][curr_col -1]==-1)		// check diagonal< 0 (/) shows only 1 slot
					 {
						 maze[curr_row][curr_col] = -3;
						 return false;
					 }
					 else if (maze[curr_row-1][curr_col-1]==-1 && maze[curr_row +1][curr_col+1] ==-1)// check diagonal< 0 (\) shows only 1 slot
					 {
						 maze[curr_row][curr_col] = -3;
						 return false;
					 }*/
				 }
				 return true;
			 }
			 
		
			 private static int calculate_gn(int temp_row,int temp_col)
			 {
				 int gn =0;
				 
				 gn = goal_pt_row - temp_row;
				 gn+= goal_pt_col - temp_col;
				 
				 return gn;
			 }

			 
			 public static void print()
				{
					for (int i = 0; i <breath; i ++)
					{
						for (int j = 0 ; j < length;j++)
						{
							if(j == length -1)
							{
								System.out.println(" "+maze[i][j]);
							}
							else
							{
								if(maze[i][j] <0 || maze[i][j]>9)
								{
									System.out.print(" "+ maze[i][j]);
								}
								else
								{
									System.out.print("  "+ maze[i][j]);
								}
							}
						}
					}
					
					System.out.println("");
				};
			
				public static void directioncheck()
				{
					/* use of sensor to check direction
					 
					 This part will be modified again based on the sensor detections
					 
					*/ 
					 
					 
					 curr_row =goal_pt_row;
					 curr_col= goal_pt_col;				//left side
					 
					 curr_row_2 = goal_pt_row;			
					 curr_col_2 = goal_pt_col+1;		// right side
					 
					
					
					 
					face = Direction.North;
				}

				 public static void retreat(int cur_value)
				 {
					 
					// add face direction
					 
					 int temp_value;
					 
					
					 if( (curr_row == start_pt_row+1 && curr_col == start_pt_col+1 )||(curr_row_2 == start_pt_row+1 && curr_col_2 == start_pt_col+1 ))		// stop recursive
					 {
						 route(face);
						 print();
					
						 System.out.println("Start Point Reached");
						 System.out.println("Ready for RACE~!!");
						 	return;
					 }
					 
					 
					 switch(face)
					 {
					 
					 case North:
						 
						if(  maze[curr_row-1][curr_col] != -1 && maze[curr_row-1][curr_col] <= cur_value  && maze[curr_row_2-1][curr_col_2] != -1 && maze[curr_row_2-1][curr_col_2]<= cur_value && (maze[curr_row_2-1][curr_col_2] > 0 || maze[curr_row-1][curr_col] >0) ) 			//north
						{			
							route(Direction.North);
							 temp_value = max(maze[curr_row-1][curr_col], maze[curr_row_2-1][curr_col_2]);
							 curr_row -= 1;
							 curr_row_2 -= 1;
							// print();
							retreat(temp_value);
						
						}
						else if (maze[curr_row][curr_col-1] != -1 && maze[curr_row][curr_col-1]<=cur_value  && maze[curr_row_2][curr_col_2-1] != -1 && maze[curr_row_2][curr_col_2-1]<=cur_value && (maze[curr_row_2][curr_col_2-1] > 0 || maze[curr_row][curr_col -1] >0))			//west
						{
							
							 route(Direction.West);
							 temp_value = max(maze[curr_row][curr_col-1], maze[curr_row_2][curr_col_2 -1]);
							curr_col_2-=1;
							curr_row +=1;
							
							//print();
							retreat(temp_value);
						}
					/*	else if (maze[curr_row+1][curr_col] != -1 &&maze[curr_row+1][curr_col]<=cur_value)			//  south (redundant)
						{
							 maze[curr_row][curr_col] = -2;
							curr_row+=1;
							retreat(maze[curr_row+1][curr_col]);
						}
						else if (maze[curr_row][curr_col-1] != -1 && maze[curr_row][curr_col-1] <= cur_value)		// west (redundant)
						{
							 maze[curr_row][curr_col] = -2;
							curr_col-=1;
							retreat(maze[curr_row][curr_col-1]);
						}
						*/
						
						break;
						
						
					 case West:
						 
						 if (maze[curr_row][curr_col-1] != -1 && maze[curr_row][curr_col-1]<=cur_value  && maze[curr_row_2][curr_col_2-1] != -1 && maze[curr_row_2][curr_col_2-1]<=cur_value && (maze[curr_row_2][curr_col_2-1] > 0 || maze[curr_row][curr_col-1] >0))			//west
							{
							 	route(Direction.West);
								 temp_value = max(maze[curr_row][curr_col-1], maze[curr_row_2][curr_col_2-1]);
								curr_col-=1;
								curr_col_2 -=1;
							//	print();

								retreat(temp_value);
							}
						 
						 else if( maze[curr_row-1][curr_col] != -1 && maze[curr_row-1][curr_col] <= cur_value && maze[curr_row_2-1][curr_col_2] != -1 && maze[curr_row_2-1][curr_col_2] <= cur_value && (maze[curr_row_2-1][curr_col_2] > 0 || maze[curr_row-1][curr_col] >0) ) 			//north
							{			
							

								route(Direction.North);
								 temp_value = max(maze[curr_row_2-1][curr_col_2], maze[curr_row-1][curr_col]);
								 curr_col_2 += 1;
								 curr_row -= 1;
							//print();
								retreat(temp_value);
							
							}
						 break;
					 }
				 }
				 
				 private static int max(int a, int b)
				 {
					 if( a >= b)
					 {
						 return a;
					 }
					 else 
						 return b;
				 }
				 
			 
			 
			 public static void route(Direction nextdirection)			// plot the route back
			 {
				 
				 /* format
				  send(direction - no.ofblk - speed)
				  forward - f				no.of blk - 00/ 01  		speed - 0 (slow) / 1 (fast)
				  left - l
				  right - r
				  */
				 
				 if(face == nextdirection)
				 {
					 	// insert command to RPI
					 	// move straight
					 //send("f010");
				 }
				 else if (nextdirection == Direction.North ) 
				 {
					 //insert command to RPI
					 //turn right
					 //send("r010");
					 face = Direction.North;
				 }
				 else if( nextdirection == Direction.West)
				 {
					 //insert command to RPI
					 // turn left
					 //send("l010");
					 face = Direction.West;
				 }
				 
				 maze[curr_row][curr_col] = -7;
				maze[curr_row_2][curr_col_2] = -7;
				m.tracer(curr_row, curr_col, curr_row_2,curr_col_2);// plot the GUI
				print();
			 }
			 
			 static int counter = 0;
			 public static void speedup(int temp_row, int temp_col, int temp_row2, int temp_col2)
			 {		  
				 if ((temp_row == goal_pt_row && temp_col == goal_pt_col) || (temp_row2 == goal_pt_row && temp_col2 == goal_pt_col))
				 {
					 direc(face);						 
						
					 System.out.println("Race Ended");
					 return;
				 }
				 
				 if( maze[temp_row2 +1][temp_col2] == -7 && face == Direction.South)		// go straight south
				 {
					 counter ++;
					 speedup(temp_row+1, temp_col, temp_row2+1, temp_col2);
					 
				 }
				 else if  (maze[temp_row][temp_col+1] == -7 && face == Direction.East)		// go straight east
				 {
					 counter ++;
					 speedup(temp_row,temp_col+1,temp_row2,temp_col2+1);
				 }
				 else
				 {
					 if(face == Direction.South)
					 {
						
						 if(counter >9)
						 {
						 //rpi.send("f"+counter+"1");
						 }
						 else
						 {
							 //rpi.send("f0"+counter+"1");
						 }
						 // rpi.send("l000");
						direc(face);
						 
						 face = Direction.East;
						 counter = 0;
						 
						 curr_col+=1;
						 curr_row_2-=1;
						 
						 
						 speedup(temp_row,temp_col+1, temp_row2-1, temp_col2);
						 
						 
					 }
					 else if (face == Direction.East)
					 {
						 if(counter >9)
						 {
						 //rpi.send("f"+counter+"1");
							 
							 
						 }
						 else
						 {
							 //rpi.send("f0"+counter+"1");
						 }
						 // rpi.send("r000");	//turn right on the spot
						
						
						 direc(face);
						 
						 face = Direction.South;
						 counter = 0;
						 curr_col -=1;
						 curr_row_2 +=1;
						 
						 speedup(temp_row,temp_col -1, temp_row2+1,temp_col2);
					 }

					 
				 }
				 
				 
			 }

		private static void direc(Direction inp)
		{
			if (inp == Direction.East)
			{
				 for ( int i = 0 ; i <= counter; i ++)
				 {
					 m.speeding(curr_row , curr_col + i, curr_row_2, curr_col_2 +i );
 
				 }
				 curr_col+= counter;
				 curr_col_2+= counter;
			}
			else if (inp == Direction.South)
			{
				 
				 for ( int i = 0 ; i <= counter; i ++)
				 {
					 m.speeding(curr_row+i , curr_col , curr_row_2 + i, curr_col_2);
					 
				 }
				 curr_row += counter;
				 curr_row_2 += counter;
			}
		}

		
	}

