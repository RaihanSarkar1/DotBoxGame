
public class Node {
	private int[][] grid;
	private Player type;
	private int p1Score;
	private int p2Score;
	
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	

	
	public Node(int rows, int cols, Player playerT)
	{
		int row = (rows * 2) + 1;
		int col = (cols * 2) + 1;
		grid = new int[row][col];
		type = playerT;
	
		p1Score = 0;
		p2Score = 0;
	}

	
	public int getP1Score() { return p1Score; }
	public int getP2Score() { return p2Score; }
	public Player getType() { return type; }
	
	
	
	
	public Error makeMove(int row, int col, Player playerT)
	{
		if( row < 0 || row > grid.length || col < 0 || col > grid[0].length) {
			return Error.OUT_OF_BOUNDS;
		}
		
		if(!(row % 2 == 0 ^ col % 2 == 0))
			return Error.INVALID_SPACE;
		
		if(grid[row][col] > 0)
			return Error.SPACE_FILLED;
		
		grid[row][col] = 1;
		
		type = playerT;
		
		//updateScore(row, col);
		
		return Error.SUCCESS;
	}
	
	

	
	private void updateScore(int row, int col)
	{
		
	}
	

	private boolean isSurrounded(int row, int col)
	{
		
	}
	

	

	public boolean isOver()
	{
		for(int row = 0; row < grid.length; row++)
		{
			if(row % 2 == 0)
			{
				for(int col = 1; col < grid[row].length; col += 2)
				{
					if(grid[row][col] == 0)
						return false;
				}
			}
			else
			{
				for(int col = 0; col < grid[row].length; col += 2)
					if(grid[row][col] == 0)
						return false;
			}
		}
		return true;
	}
	
	
	public void printNode()
	{
		System.out.print(" ");
		for(int col = 0; col < grid[0].length; col++)
		{
			System.out.print(" " + col);
		}
		System.out.print("\n");
		for(int row = 0; row < grid.length; row++)
		{
			System.out.print(row + " ");
			if(row % 2 == 0)
			{
				for(int col = 0; col < grid[row].length; col++)
				{
					if(col % 2 == 0)
						System.out.print("o");
					else if(grid[row][col] > 0)
						System.out.print("———");
					else
						System.out.print("   ");
				}
			}
			else
			{
				for(int col = 0; col < grid[row].length; col++)
				{
					if(col % 2 != 0)
						System.out.print("   ");
					else if(grid[row][col] > 0)
						System.out.print("|");
					
					else
						System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
		System.out.println("\n......Score.....");
		System.out.println("Player1: " + p1Score);
		System.out.println("Player2: " + p2Score + "\n");
	}
}
