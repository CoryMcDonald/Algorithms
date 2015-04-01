import java.util.*;

class AlgoAsgn9
{
    public static int length(Point u, int adjacency_matrix[][])
    {        
        int length =0;
        int prevVertex = -1;
        for(int p : u.parents)
        {
            System.out.print(p + ", ");
            if(prevVertex != -1)
            {
                length += adjacency_matrix[prevVertex][p+1];
                System.out.println("\t" + adjacency_matrix[prevVertex][p+1]);
            }
            prevVertex = p;
        }
        System.out.println("Length: " + length);
        System.out.print("\n");
        return length;
        // return u.parents.size();
    }
    public static int bound(Point u, int adjacency_matrix[][])
    {
        //so we need to go through column row that isn't in parents
        //so
        int bound = length(u, adjacency_matrix);
        // System.out.println("2,2: " + adjacency_matrix[1][4]);
        for(int i=0; i<adjacency_matrix[0].length; i++) //each row
        {
            if(!u.parents.contains(i) || (u.parents.size() >= 1 && i == u.parents.get(u.parents.size()-1)))
            {
                int minForRow = Integer.MAX_VALUE;
                for(int j=0; j<adjacency_matrix[0].length; j++) //each column
                {
                    if (adjacency_matrix[i][j] < minForRow && adjacency_matrix[i][j] != 0)
                    {
                        minForRow = adjacency_matrix[i][j];                         
                    }
                }
                if(minForRow != Integer.MAX_VALUE)
                {
                    bound += minForRow;

                }
            }
        }
        return bound;
    }
    static void Travel(int adjacency_matrix[][])
    {
        int n = adjacency_matrix[0].length; //the count of how many elements are in the matrix
        PriorityQueue<Point> queue = new PriorityQueue<Point>();
        ArrayList<Integer> opttour = new ArrayList<Integer>();
        int minlength = Integer.MAX_VALUE;
        Point v = new Point();
        v.level = 0;
        v.parents.add(1); //path so far
        v.bound = bound(v, adjacency_matrix);

        queue.add(v);
        //todo insert the vertex into it
        while(queue.size() > 0) {
            Point current = queue.remove();
            if(current.bound < minlength)
            {
                for ( int i =2; i < n && !current.parents.contains(i); i++)
                {
                    Point u = new Point(current.parents);
                    u.level = current.level +1;
                    u.parents.add(i);
                    u.bound = v.bound;
                    if(u.level == n-2)
                    {
                        //put inde of only vertex remaing at the end of u.parents
                        //put 1 at the end of u.path
                        if(length(u, adjacency_matrix) < minlength)
                        {
                            System.out.println("circuit found");
                            minlength = length(u, adjacency_matrix);
                            opttour = u.parents;
                        }
                    }else
                    {
                        u.bound = bound(u, adjacency_matrix);
                        if(u.bound < minlength)
                        {
                            System.out.println("New bound = " + u.bound);
                            // queue.add(u);
                        }
                    }
                }
                // for(int i =0; )
            }
        }
    }

    public static void main(String... arg)
    {
        Scanner scanner = new Scanner(System.in);
        int count =0;
        int temp=1;
        int adjacency_matrix[][] = new int[1][1];

        while (scanner.hasNext())   
        {
            String input = scanner.nextLine();
            if(count > 0 && !input.equals(""))
            {
                String[] split = input.split(" ");
                if(split.length == 1)
                {
                    int newSize =  Integer.parseInt(split[0])+1;
                    adjacency_matrix = new int[newSize][newSize];
                    temp = 1;
                }else if(split.length > 1)
                {
                    for(int j=1; j<=split.length; j++)
                    {
                        adjacency_matrix[temp][j] = Integer.parseInt(split[j-1]);
                    }
                    temp++;
                }
            }
            if(count != 1 && (input.equals("") || !scanner.hasNext()))
            {
                for (int i = 1; i <= adjacency_matrix[0].length-1; i++)
                {
                    for (int j = 1; j <= adjacency_matrix[0].length-1; j++)
                    {
                        if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
                        {
                            adjacency_matrix[j][i] = 1;
                        }
                    }
                }
                Travel(adjacency_matrix);
            }
            count++;
        }
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
    
        scanner.close();
    }
}
 class Point implements Comparable<Point>
{
    int edge;
    int level;
    int bound;
    ArrayList<Integer> parents = new ArrayList<Integer>();

    public Point()
    {

    }
    public Point(ArrayList<Integer> parents)
    {
        for(Integer p : parents)
            this.parents.add(p);
    }

    @Override
    public int compareTo(Point other) {
        if(bound > other.bound) {
            return 1;
        }
        else if(bound == other.bound)
            return 0;

        return -1;
    }
}
