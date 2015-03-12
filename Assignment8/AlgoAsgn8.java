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
    testCases.add(new Point(1,2));
    testCases.add(new Point(6,7));
    testCases.add(new Point(7,8));
    testCases.add(new Point(8,8));
    testCases.add(new Point(2,2));
    testCases.add(new Point(2,4));
    testCases.add(new Point(1,7));
    testCases.add(new Point(8,3));
    

    for(Point testCase : testCases)
    {
      boolean[][] board = new boolean[8][8];
      board[testCase.x][testCase.y] = true;
      solveNQUtil(board, 0, testCase);
      System.out.println(numberOfSolutions);
      System.out.println("");
      numberOfSolutions = 0;
    }

  }
  private static void printSolution(boolean[][] board, int numOfTabs)
  {
    int N = 8;
    for (int i = 0; i < N; i++)
    {
      for(int tabs = 0; tabs < numOfTabs; tabs++)
        System.out.print("\t");
      for (int j = 0; j < N; j++)
        if(board[i][j]) 
          System.out.print("Q|");
        else
          System.out.print(" |");
      System.out.print("\n");
    }
    System.out.print("\n");
  }
  

  private static boolean solveNQUtil(boolean[][] board, int col, Point goal)
  {
    int N = 8;
    boolean res = false;
    int numOfTabs = col;

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
          res = true;
        }

        if(i != goal.x && col != goal.y)
          board[i][col] = false; // BACKTRACK

      }
    }
    return res;

   }
   private static boolean isSafe(boolean[][] board, int row, int col)
   {
    int i, j;
    int N = 8;

    String reason = "";
    boolean returnValue = true;

    /* Check this row on left side */
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
    /* Check upper diagonal on left side */
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
    {
      if (board[i][j])
        return false;
    }

    //Check lower diagonal on left side 
    for (i = row, j = col; j >= 0 && i < N; i++, j--)
    {
      if (board[i][j])
        return false;
    }

    return true;
  }
  
}