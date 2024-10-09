import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;
public TicTacToe()//constructor

{
	board = new char [3][3];
	initBoard();
}
void initBoard()// to initialize the board with spaces instead of getting unicode numbers
{
	for(int i=0;i<board.length;i++)
	{
		for(int j=0; j< board[i].length;j++)
		{
			board[i][j]=' ';
		}
	}
}
static void dispBoard()// to display the board on console
{
	System.out.println("-------------");
	for(int i=0;i<board.length;i++)
	{
		System.out.print("| ");
		
		for(int j=0; j< board[i].length;j++)
		{
			System.out.print(board[i][j] + " | ");
			
			
		}
		System.out.println();
		System.out.println("-------------");
	}
}

static void placeMark(int row, int column,char mark)// to mark x&o in the board
{
	if(row>=0&& row<=2 && column>=0&& column<=2) {
		
		board[row][column]= mark;	
	}
	else {
		System.out.println("Invalid Position");
	}
}
static boolean checkColWin()// column wise checking  win
{
	for( int j=0;j<=2;j++)
	{
		if (board[0][j] !=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
		{
			return true;
		}
	}
	return false;
}
static boolean checkRowWin()
{
	for( int i=0;i<=2;i++)
	{
		if (board[i][0] !=' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
		{
			return true;
		}
	}
	return false;	
}
static boolean checkDiagWin()
{
	if (board[0][0] !=' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
		||	board[0][2] !=' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
	{
		return true;
	}
	else
	{
		return false;	

	}
}
static boolean checkDraw()
{
	for( int i=0;i<=2;i++)
	{
		for (int j=0; j<=2;j++)
		{
			if(board[i][j]==' ')
			{
				return false;
			}
		}
	}return true;
}
}


abstract class Player
{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row, int column)
	   {
		   if(row>= 0 && row<=2 && column>=0 && column<=2)//checking whether the move made is in the boundary
		   {
			   if(TicTacToe.board[row][column]== ' ') // checking the board is empty or not to make a move 
			   {
				   return true;
			   }
		   }
		   return false;
	   }
	   
}




class HumanPlayer extends Player
{
	
   HumanPlayer(String name, char mark)//constructors initializing the name and mark
	{
		this.name=name;//shadowing is created so we are using this keyword
		this.mark=mark;
	}
   void makeMove()
   {
	  Scanner scan =  new Scanner (System.in );
	  int column;
	  int row;
	  do //as long as the player is making a move valid move 
	  {
		  System.out.println("Enter the row and column");
		  row= scan.nextInt();
		  column= scan.nextInt(); 
	  }
	  while(!isValidMove(row,column));//player is making an invalid move 
	   TicTacToe.placeMark(row, column, mark);
	  
	  
   }
 
   
   
}


class AIPlayer extends Player
{
   AIPlayer(String name, char mark)//constructors initializing the name and mark
	{
		this.name=name;//shadowing is created so we are using this keyword
		this.mark=mark;
	}
   void makeMove()
   {
	  Scanner scan =  new Scanner (System.in );
	  int column;
	  int row;
	  do // valid move code changes for Ai class
	  {
		  Random r = new Random();
		 row = r.nextInt(3);//generate a random integer from 0 to 3 but it will not include 3 because it excludes the boundary
	  column = r.nextInt(3);
	  }
	  while(!isValidMove(row,column));//player is making an invalid move 
	   TicTacToe.placeMark(row, column, mark);
	  
	  
   }
   
   
   
}



public class LaunchGame {

	public static void main(String[] args) {
TicTacToe t =new TicTacToe();
HumanPlayer p1 = new HumanPlayer ("Chaithra",'x');
HumanPlayer p2 = new HumanPlayer ("Gagana",'o');
//AIPlayer p2 = new AIPlayer("TAI", 'o');
Player cp;//reference for current player(reference type assignment)

cp=p1;
cp=p2;//for human player

while(true)
{
	System.out.println(cp.name+ " " + "turn");
	cp.makeMove();
	TicTacToe.dispBoard();
	if(TicTacToe.checkColWin() || TicTacToe.checkRowWin()||TicTacToe.checkDiagWin() ) 
	{
		System.out.println(cp.name + " "+"has won");
		break;//as long as one  of the player win the while loop will stop
	}
	else if(TicTacToe.checkDraw())
	{
		System.out.println("Game is draw");
		break;
	}
	
	else
	{
	 if(cp==p1)
	 {
	cp=p2;	 
	 }
	 else
	 {
		 cp=p1;
	 }
	}
}

  

	}

}
