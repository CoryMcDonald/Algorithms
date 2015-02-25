import java.util.Scanner;

class AlgoAsgn4 {
  public static void main(String[] args)
  {
    int counter = 0;
    int T = 0;
    int n = 0;
    String[] stringInput;
    int set[] = new int[0];
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
        String input = scanner.nextLine();
        if(input != "")
        {
            if(counter == 1)
            {
              stringInput = input.split(" ");
              set = new int[stringInput.length];
              for(int i=0; i< stringInput.length; i++)
              {
                set[i] = Integer.parseInt(stringInput[i]);
              }
            }else if (counter ==2 )
            {
              T = Integer.parseInt(input);
            }
            counter ++;
            if(counter == 3)
            {
              n = set.length;
              System.out.println(isSubsetSum(set, n, T));
              counter = 0;
            }
        }
    }
    scanner.close();
    
    
    
  }


  private static boolean isSubsetSum(int set[], int n, int T)
  {
    //Sources for algorithm :)
    //https://www.cs.cmu.edu/~ckingsf/class/02713-s13/lectures/lec15-subsetsum.pdf
    //http://crab.rutgers.edu/~guyk/ex/part.pdf

    boolean subset[][] = new boolean[T+1][n+1];
    for (int i = 0; i <= n; i++)
    {
      subset[0][i] = true;
    }

    for (int i = 1; i <= T; i++) //For every row
    {
      for (int j = 1; j <= n; j++) //For every column
      {
        subset[i][j] = subset[i][j-1]; //Moving the value up
        if (i >= set[j-1])
          subset[i][j] = subset[i][j] || subset[i - set[j-1]][j-1]; //ORing the operation together
      }
    }
  
    return subset[T][n]; //The value from the table at the location of the SUM
 }
}