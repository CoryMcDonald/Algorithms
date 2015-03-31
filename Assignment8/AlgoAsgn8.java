import java.util.*;

public class AlgoAsgn8
{
  private static class Point{
    int x; 
    int y;
    public Point(int x, int y)
    {
      this.x = x-1;
      this.y = y-1;
    }
  }
  private static int numberOfSolutions = 0;

  public static void main(String[] args)
  {
    ArrayList<Point> testCases = new ArrayList<Point>();

    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext())   
    {
      String input = scanner.nextLine();
      if(input.contains(" "))
      {
        String[] stringPoint = input.split(" ");
        if(stringPoint.length == 2)
        {
          testCases.add(new Point(Integer.parseInt(stringPoint[0]),Integer.parseInt(stringPoint[1])));
        }
      }
    }    

    for(Point testCase : testCases)
    {
      boolean[][] board = new boolean[8][8];
      board[testCase.x][testCase.y] = true;
      solveNQUtil(board, 0, testCase);
      System.out.println(numberOfSolutions + "\n");
      numberOfSolutions = 0;
    }

  }

  private static boolean solveNQUtil(boolean[][] board, int col, Point goal)
  {
    int N = 8;
    boolean continueWithExecution = false;

    if (col >= N)
    {
      numberOfSolutions++;
      return true;
    }

    for (int i = 0; i < N; i++)
    {
      if (board[i][col] || (isSafe(board, i, col) && col != goal.y))
      {
        board[i][col] = true;
        if ( solveNQUtil(board, col + 1, goal) == true )
        {
          continueWithExecution = true;
        }

        if(i != goal.x && col != goal.y)
          board[i][col] = false; 

      }
    }
    return continueWithExecution;

  }
  private static boolean isSafe(boolean[][] board, int row, int col)
  {
    int i, j;
    int N = 8;

    String reason = "";
    boolean returnValue = true;

    for (i = 0; i < N; i++)
    {
      if (board[row][i])
      {
        return false;
      }
    }

    for(i=0; i < N; i++)
    {
      for(j=0; j< N; j++)
      {
        if (board[i][j] && i != row && j != col)
        {
          if(Math.abs(i-row) == Math.abs(j-col))
            return false;
        }
      }      
    }
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
    {
      if (board[i][j])
        return false;
    }

    for (i = row, j = col; j >= 0 && i < N; i++, j--)
    {
      if (board[i][j])
        return false;
    }

    return true;
  }
  
}