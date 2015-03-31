import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class AlgoAsgn9
{
    private static int numberOfNodes;

    public static int tsp(int adjacencyMatrix[][])
    {
        Stack<Integer> stack = new Stack<Integer>();
        int returnValue = 0;
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;

        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                //Adding twice because we will have to return back to the beginning
                visited[dst] = 1;
                returnValue += min;
                stack.push(dst);
                // System.out.print(dst + "\t");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
        return returnValue+adjacencyMatrix[dst][1];
    }

    public static void main(String... arg)
    {
        int number_of_nodes;
        Scanner scanner = null;
        
        scanner = new Scanner(System.in);
        number_of_nodes = scanner.nextInt();
        int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
        for (int i = 1; i <= number_of_nodes; i++)
        {
            for (int j = 1; j <= number_of_nodes; j++)
            {
                adjacency_matrix[i][j] = scanner.nextInt();
            }
        }
        for (int i = 1; i <= number_of_nodes; i++)
        {
            for (int j = 1; j <= number_of_nodes; j++)
            {
                if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
                {
                    adjacency_matrix[j][i] = 1;
                }
            }
        }
        System.out.println("\n" + tsp(adjacency_matrix));
        scanner.close();
    }
}