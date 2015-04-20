import java.util.*;
import java.io.*;
class AlgoAsgn10 {

  private static LinkedList[] q = {
      new LinkedList<Integer>(), // 0
      new LinkedList<Integer>(), // 1
      new LinkedList<Integer>(), // 2
      new LinkedList<Integer>(), // 3
      new LinkedList<Integer>(), // 4
      new LinkedList<Integer>(), // 5
      new LinkedList<Integer>(), // 6
      new LinkedList<Integer>(), // 7
      new LinkedList<Integer>(), // 8
      new LinkedList<Integer>() // 9
    };

    public static void main(String[] args) throws IOException
    {
      Scanner scanner = new Scanner(System.in);

      int index =0;
      int size =0;
      Integer[] list = null;
      while (scanner.hasNext())
      {
        String input = scanner.nextLine();
        if(!input.equals(""))
        {
          if(index==0)
          {
            size = Integer.parseInt(input);
            list = new Integer[size];
          }
          else
          {
            list[index-1]  = Integer.parseInt(input);
          }
          index++;
        }
      }



      //Sorting using LinkedList
      long startTime = System.nanoTime();
      Object[] sortedList = radixSortLinkedList(list);
      long endTime = System.nanoTime();

      System.out.println("LL: " + Long.toString(endTime - startTime));
      startTime = System.nanoTime();
      Object[] sortedCSList = radix_sort(list, list.length, maxNumberOfDigitsInList(list));
      endTime = System.nanoTime();
      System.out.println("CS: " + Long.toString(endTime - startTime));


      FileWriter writer = new FileWriter("outputLL.txt");
      for(int i=0; i < sortedList.length; i++){
        writer.write(sortedList[i].toString() + System.getProperty("line.separator"));
      }

      FileWriter writer2 = new FileWriter("outputCS.txt");
      for(int i=0; i < sortedCSList .length; i++){
        writer2.write(sortedCSList[i].toString() + System.getProperty("line.separator"));
      }

    }

  /**
   * Sorts an array of objects (integers) using radix sort.
   * @param list  The unsorted list.
   * @return    The sorted list.
   */
  public static Object[] radixSortLinkedList(Object[] masterlist)
  {
    //Going through each column
    for(int i=1; i <=  maxNumberOfDigitsInList(masterlist); i++){

      masterlist = distribute(masterlist, i);

      masterlist = coalesce(masterlist);
    }
    return masterlist;
  }

  public static Object[] distribute(Object[] masterlist, int i)
  {
    for(int n=0; n < masterlist.length; n++)
    {
        //value of the ith digit from the right in p-> key
      int radix = getDigitAt(Integer.parseInt(masterlist[n].toString()), i);
        //this is giving an unsafe operation error
      q[radix].add(masterlist[n]);
    }
    return masterlist;
  }
  
  public static Object[] coalesce (Object[] masterlist)
  {
    int a=0;
    for(int k=0; k < q.length; k++){
      // Go through every element in the linked masterlist.
      while(q[k].peek() != null){
        //unsafe operation error
        masterlist[a++] = q[k].poll();
      }
    }
    return masterlist;
  }

  public static int maxNumberOfDigitsInList(Object list[])
  {
    int maxDigits = 0;
    for(int i=0; i < list.length; i++){
      int digits = list[i].toString().length();
      if(digits > maxDigits)
        maxDigits = digits;
    }
    return maxDigits;
  }

  public static int getDigitAt(int number, int index)
  {
    return (int)(number / Math.pow(10,index-1)) % 10;
  }

  public static Object[] radix_sort(Integer a[], int a_len, int maxDigits)
  {
    int i;
    Integer[] returnMatrix = new Integer[a_len];
    int expn = 1;

    for (i = 0; i != maxDigits; ++i)
    {
      int j;
      int[] bucket = new int[10];
      //Initial counts
      for (j = 0; j != a_len; j++)
        bucket[a[j]/expn % 10]++;

      //adding previous element
      for (j = 1; j != 10; ++j)
        bucket[j] += bucket[j - 1];

      
      for (j = a_len - 1; j >= 0; --j)
        returnMatrix[--bucket[a[j] / expn % 10]] = a[j];
      
      for (j = 0; j != a_len; ++j)
        a[j] = returnMatrix[j];

      expn *= 10;
    }
    return returnMatrix;
  }
}