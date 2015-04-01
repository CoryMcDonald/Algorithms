import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class AlgoAsgn9
{
    

    public static void main(String... arg)
    {
        Scanner scanner = new Scanner(System.in);
        int count =0;
        int temp=1;
        int adjacency_matrix[][] = new int[1][1];

        // while (scanner.hasNext())   
        // {
        //     String input = scanner.nextLine();
        //     if(count > 0 && !input.equals(""))
        //     {
        //         String[] split = input.split(" ");
        //         if(split.length == 1)
        //         {
        //             int newSize =  Integer.parseInt(split[0])+1;
        //             adjacency_matrix = new int[newSize][newSize];
        //             temp = 1;
        //         }else if(split.length > 1)
        //         {
        //             for(int j=1; j<=split.length; j++)
        //             {
        //                 adjacency_matrix[temp][j] = Integer.parseInt(split[j-1]);
        //             }
        //             temp++;
        //         }
        //     }
        //     if(count != 1 && (input.equals("") || !scanner.hasNext()))
        //     {
        //         for (int i = 1; i <= adjacency_matrix[0].length-1; i++)
        //         {
        //             for (int j = 1; j <= adjacency_matrix[0].length-1; j++)
        //             {
        //                 if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
        //                 {
        //                     adjacency_matrix[j][i] = 1;
        //                 }
        //             }
        //         }
        //         System.out.println(fdsa(adjacency_matrix));
        //     }
        //     count++;
        // }
        // System.out.println(scanner.hasNext());
        // for (int i = 1; i <= adjacency_matrix[0].length-1; i++)
        // {
        //     for (int j = 1; j <= adjacency_matrix[0].length-1; j++)
        //     {
        //         System.out.print(adjacency_matrix[i][j] + " ");
        //     }
        //     System.out.print("\n");
        // }

        // for (int i = 1; i <= 4; i++)
        // {
        //     for (int j = 1; j <= 4; j++)
        //     {
        //         if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
        //         {
        //             adjacency_matrix[j][i] = 1;

        //         }
        //     }
        // }
        
        int number_of_nodes = scanner.nextInt();
        adjacency_matrix = new int[number_of_nodes+ 1][number_of_nodes+1];
        for (int i = 1; i <= number_of_nodes; i++)
        {
            for (int j = 1; j <= number_of_nodes; j++)
            {
                adjacency_matrix[i][j] = scanner.nextInt();
            }
            // System.out.println("");
        }

        //do some stuff
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
        for (int i = 1; i <= adjacency_matrix[0].length-1; i++)
        {
            for (int j = 1; j <= adjacency_matrix[0].length-1; j++)
            {
                System.out.print(adjacency_matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
                System.out.println(tsp(adjacency_matrix));
                // System.out.println(fdsa(adjacency_matrix));

        scanner.close();
    }
}