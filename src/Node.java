public class Node {
	private int[][] grid;
	private Player player;
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
		player = playerT;
	
		p1Score = 0;
		p2Score = 0;
	}

	
	public int getP1Score() { return p1Score; }
	public int getP2Score() { return p2Score; }
	
	
	
	public Error makeMove(int row, int col)
	{
		if( row < 0 || row > grid.length || col < 0 || col > grid[0].length) {
			return Error.OUT_OF_BOUNDS;
		}
		
		if(!(row % 2 == 0 ^ col % 2 == 0))
			return Error.INVALID_SPACE;
		
		if(grid[row][col] > 0)
			return Error.SPACE_FILLED;
		
		grid[row][col] = 1;
						
		updateScore(row , col);
		if(player == Player.P1)
		{
			player = Player.P2;
		}
		else
		{
			player = Player.P1;
		}
		return Error.SUCCESSFUllMOVE;
	}
	
	
	private void updateScore(int row, int col){
		
		if (row % 2 == 0) {
			int up = row++;
			int down = row--;
			if(checkBox(up , col) == true ) {
				
				if (player == player.P1){
					p1Score++;
				}
				else {
					p2Score++;
				}			
			}
			else if(checkBox(down , col) == true ) {
				
				if (player == player.P1){
					p1Score++;
				}
				else {
					p2Score++;
				}			
			}
			
			
		}
		else {
			
			int left = col--;
			int right = col++;
			if(checkBox(row , left) == true ) {
				
				if (player == Player.P1){
					p1Score++;
				}
				else {
					p2Score++;
				}			
			}
			else if(checkBox(row , right) == true ) {
				
				if (player == Player.P1){
					p1Score++;
				}
				else {
					p2Score++;
				}			
			}
		}
		
		
	}
	
	
	
	//Checking the box up/below for a horizontal line move
	//checking the box left/right for a vertical line move
	private boolean checkBox(int row, int col)
	{
		if(row > 0 && row < grid.length && col > 0 && col < grid[row].length) {
			return grid[row - 1][col] > 0 && grid[row + 1][col] > 0 && grid[row][col - 1] > 0 && grid[row][col + 1] > 0;
		}	
		else return false;
	}
	

	

	public boolean gameOver()
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
