import java.util.Random;
import java.util.Scanner;


public class DotBoxGame {

	public DotBoxGame() {
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to Dot Box Game");
		int width = 0;
		int height = 0;
		int playerSelection = 0;
		Player user;
		Node root;
		int[] move = new int[2];
		String[] moveStr;
		Error result = null;
		
		while(width == 0)
		{
			System.out.print("Please enter the width of the grid: ");
			try {
				width = Integer.parseInt(s.nextLine());
			} catch(NumberFormatException e)
			{
				System.out.print("\nThat is not a valid width. Please try again.");
			}
		}
		while(height == 0)
		{
			System.out.print("Please enter the height of the grid: ");
			try {
				height = Integer.parseInt(s.nextLine());
			} catch(NumberFormatException e)
			{
				System.out.print("\nThat is not a valid height. Please try again.");
			}
		}
		
		while(playerSelection == 0)
		{
			System.out.print("Please select which player will go first: \n");
			System.out.println("1. Player1\n2. Player2\n3. Random");
			System.out.print("Answer: ");
			try {
				playerSelection = Integer.parseInt(s.nextLine());
			} catch(NumberFormatException e)
			{
				System.out.print("\nThat is not a valid choice. Please try again.");
			}
			if(playerSelection < 1 || playerSelection > 3)
			{
				playerSelection = 0;
				System.out.print("\nThat is not a valid selection. Please try again.");
			}
		}
		System.out.print("\n");
		
		if(playerSelection == 1)
		{
			user = Player.P1;
		}
		else if(playerSelection == 2)
		{
			user = Player.P2;
		}
		else
		{
			Random rand = new Random(System.currentTimeMillis());
			boolean coinToss = rand.nextBoolean();
			if(coinToss)
				user = Player.P1;
			else
				user = Player.P2;
		}
		
		root = new Node(height, width, user);
		
		System.out.println("Please enter the coordinates of your moves in column, row order."
				+ "\nCoordinates should be separated by a comma.\n");
		
		root.printNode();

		if(user == Player.P2) {
			do{
				System.out.print("Player2's move: ");
				moveStr = s.nextLine().split(",");
				result = Error.SUCCESSFUllMOVE;
				if(moveStr.length < 2)
				{
					System.out.println("That move is invalid. Please enter the desired move in <column>,<row> format.");
					result = Error.INVALID_NUMBER;
				} else
				{
					for(int i = 0; i < 2; i++)
					{
						try {
							move[i] = Integer.parseInt(moveStr[i]);
						} catch (NumberFormatException e)
						{
							System.out.println("That move is invalid. Please enter the desired move in <column>,<row> format.");
							result = Error.INVALID_NUMBER;
						}
					}
				}
				if(result != Error.INVALID_NUMBER)
					result = root.makeMove(move[0], move[1]);
				if(result == Error.INVALID_SPACE)
					System.out.println("You cannot draw a line on a node.");
				if(result == Error.OUT_OF_BOUNDS)
					System.out.println("That coordinate is outside the boundaries of the grid.");
				if(result == Error.SPACE_FILLED)
					System.out.println("That space already has a line drawn in it.");
			} while (result != Error.SUCCESSFUllMOVE);
		System.out.print("\n");
		root.printNode();
		}
		
		while(!root.gameOver())
		{
			do {
				System.out.print("Player1's move: ");
				moveStr = s.nextLine().split(",");
				result = Error.SUCCESSFUllMOVE;
				if(moveStr.length < 2)
				{
					System.out.println("That move is invalid. Please enter the desired move in <column>,<row> format.");
					result = Error.INVALID_NUMBER;
				} else
				{
					for(int i = 0; i < 2; i++)
					{
						try {
							move[i] = Integer.parseInt(moveStr[i]);
						} catch (NumberFormatException e)
						{
							System.out.println("That move is invalid. Please enter the desired move in <column>,<row> format.");
							result = Error.INVALID_NUMBER;
						}
					}
				}
				if(result != Error.INVALID_NUMBER)
					result = root.makeMove(move[0], move[1]);
				if(result == Error.INVALID_SPACE)
					System.out.println("You cannot draw a line there.");
				if(result == Error.OUT_OF_BOUNDS)
					System.out.println("That coordinate is outside the boundaries of the grid.");
				if(result == Error.SPACE_FILLED)
					System.out.println("That space already has a line drawn in it.");
				if(result == Error.P1SCORE)
					System.out.println("Player 1 scored. You win a bonus move.");
			} while (result != Error.SUCCESSFUllMOVE);
			
			System.out.print("\n");
			root.printNode();
			
			do{
				System.out.print("Player2's move: ");
				moveStr = s.nextLine().split(",");
				result = Error.SUCCESSFUllMOVE;
				if(moveStr.length < 2)
				{
					System.out.println("That move is invalid. Please enter the desired move in <column>,<row> format.");
					result = Error.INVALID_NUMBER;
				} else
				{
					for(int i = 0; i < 2; i++)
					{
						try {
							move[i] = Integer.parseInt(moveStr[i]);
						} catch (NumberFormatException e)
						{
							System.out.println("That move is invalid. Please enter the desired move in <column>,<row> format.");
							result = Error.INVALID_NUMBER;
						}
					}
				}
				if(result != Error.INVALID_NUMBER)
					result = root.makeMove(move[0], move[1]);
				if(result == Error.INVALID_SPACE)
					System.out.println("You cannot draw a line there.");
				if(result == Error.OUT_OF_BOUNDS)
					System.out.println("That coordinate is outside the boundaries of the grid.");
				if(result == Error.SPACE_FILLED)
					System.out.println("That space already has a line drawn in it.");
			} while (result != Error.SUCCESSFUllMOVE);
			System.out.print("\n");
			root.printNode();
					
		}
		
		System.out.println(".............\nFinal Score\n..............");
		System.out.println("Player 1 score: " + root.getP1Score());
		System.out.println("Player 2 score: " + root.getP2Score());
		
		if(root.getP2Score() > root.getP1Score())
			System.out.println("Winner: Player2");
		else if(root.getP1Score() > root.getP2Score())
			System.out.println("Winner: Player1");
		else
			System.out.println("\n The game is a draw.");
	
		System.out.println(" Thank you for playing.");	
		s.close();

	}

}
