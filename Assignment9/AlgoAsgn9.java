import java.util.*;

class AlgoAsgn9
{
    public static int length(Point u, int adjacency_matrix[][])
    {        
        int length =0;
        int prevVertex = -1;
        for(int p : u.parents)
        {
            if(prevVertex != -1)
            {
                length += adjacency_matrix[prevVertex-1][p-1];
            }else if(prevVertex == -1)
            {
            }
            prevVertex = p;
        }
        return length;
        // return u.parents.size();
    }
    public static int bound(Point u, int adjacency_matrix[][])
    {
        //so we need to go through column row that isn't in parents
        //so
        int bound = length(u, adjacency_matrix);
        
        for(int i=0; i<adjacency_matrix[0].length; i++) //each row
        {
            boolean addFlag = true;
            //Need to consider that the parents prior to 
            if(!u.parents.contains(i+1) || (u.parents.size() >= 1 && i+1 == u.parents.get(u.parents.size()-1)))
            {
                int minForRow = Integer.MAX_VALUE;

                for(int j=0; j<adjacency_matrix[0].length; j++) //each column
                {    
                    //we can't consider any of the parents where row = last parent
                    //otherwise we can't consider any of the parents except for 1
                    
                    if (adjacency_matrix[i][j] < minForRow && adjacency_matrix[i][j] != 0)
                    {
                        if(u.parents.size() >= 1 && i+1 == u.parents.get(u.parents.size()-1) && !u.parents.contains(j+1))
                        {
                            minForRow = adjacency_matrix[i][j];                         
                        }
                        else if((!u.parents.contains(j+1) || j==0) && (i+1 != u.parents.get(u.parents.size()-1)))
                        {
                            minForRow = adjacency_matrix[i][j];                         
                        }
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
        Point minPoint = null;
        int minlength = Integer.MAX_VALUE;
        Point v = new Point();
        v.level = 0;
        v.parents.add(1); 
        v.bound = bound(v, adjacency_matrix);

        queue.add(v);
        while(queue.size() > 0) {
            Point current = queue.remove();
            if(current.bound < minlength)
            {
                for ( int i =2; i <= n ; i++)
                {
                    if(!current.parents.contains(i))
                    {
                        Point u = new Point(current.parents);
                        u.level = current.level +1;
                        u.parents.add(i);
                        u.bound = v.bound;
                        // for(int p : u.parents)
                        //     System.out.print(p + " ");
                        if(u.level == n-2)
                        {
                            //put inde of only vertex remaing at the end of u.parents
                            for(int temp = 1; temp <= n; temp++)
                            {
                                if(!u.parents.contains(temp))
                                    u.parents.add(temp);
                            }
                            u.parents.add(1);
                            if(length(u, adjacency_matrix) < minlength)
                            {
                                minlength = length(u, adjacency_matrix);
                                minPoint = u;
                                opttour = u.parents;
                            }
                        }else
                        {
                            u.bound = bound(u, adjacency_matrix);
                            if(u.bound < minlength)
                            {
                                queue.add(u);
                            }
                        }
                    }
                }
            }
        }
        if(minPoint !=null)
            System.out.println(length(minPoint, adjacency_matrix));
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
                    int newSize =  Integer.parseInt(split[0]);
                    adjacency_matrix = new int[newSize][newSize];
                    temp = 0;
                }else if(split.length > 1)
                {
                    for(int j=0; j < split.length; j++)
                    {
                        adjacency_matrix[temp][j] = Integer.parseInt(split[j]);
                    }
                    temp++;
                }
            }
            if(count != 1 && (input.equals("") || !scanner.hasNext()))
            {
                Travel(adjacency_matrix);
            }
            count++;
        }
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
