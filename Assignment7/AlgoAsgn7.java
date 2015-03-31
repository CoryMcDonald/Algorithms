import java.util.Scanner;
import java.util.Arrays;

public class AlgoAsgn7 {

    private static int[] P;
    private static int[] W;
    private static int N;
    private static int[][] dp;
    
    public static void main(String[] args) {
        
        dp = new int[1010][40];
        
        Scanner scanner = new Scanner(System.in);

        int testCases = Integer.parseInt(scanner.nextLine());
        for(int a = testCases; a > 0; a--)
        {
            String input = scanner.nextLine();
            N = Integer.parseInt(input);
            P = new int[N];
            W = new int[N];

            for (int i = 0; i < N; ++i) 
            {
                String newLine = scanner.nextLine();
                String[] itemsAndWeights = newLine.split(" ");
                P[i] = Integer.parseInt(itemsAndWeights[0]);
                W[i] = Integer.parseInt(itemsAndWeights[1]);
            }

            int G = Integer.parseInt(scanner.nextLine());
            
            int answer = 0;
            for (int i = 0; i < G; ++i) 
            {
                int S = Integer.parseInt(scanner.nextLine());
                for (int j = 0; j < 1010; ++j)
                {
                    Arrays.fill(dp[j], -1);
                }                
                answer += solve(0, S);
            }
            System.out.println(answer);
        }

    }

    private static int solve(int i, int V) 
    {
        if (dp[i][V] != -1)
        {
            return dp[i][V];
        }
        if (V == 0)
        {
            return dp[i][V] = 0;
        }
        if (i == N)
        {
            return dp[i][V] = 0;
        }
        if (W[i] <= V)
        {
            return dp[i][V] = Math.max(solve(i + 1, V), P[i] + solve(i + 1, V - W[i]));
        }
        else
        {
            return dp[i][V] =  solve(i + 1, V);
        }
    }
}