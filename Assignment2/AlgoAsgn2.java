import java.util.Scanner;

class AlgoAsgn2 
{
	public static void main(String [] args)
	{
		Scanner scanner = new Scanner(System.in);
		int size = 0;
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if(input.length() == 1)
			{
				size = Integer.parseInt(input);
			}else
			{
				int[] inputArray = new int[size];
				int[] temp = new int[size];
				String[] tempArray = input.split(" ");
				for(int i=0; i<size; i++)
				{
					inputArray[i] = Integer.parseInt(tempArray[i]);
				}		    
				System.out.println(mergeSort2(inputArray,temp, 0, size-1));
			
			}
		}
		scanner.close(); // Not required for stdin but good practice
	}
	public static int test = 0;
	public static int mergeSort2(int[] input, int[] temp, int low, int high)
	{
		int mid, numOfInversion = 0;
		if (low < high) {
			mid = low + (high - low) / 2;
			numOfInversion = mergeSort2(input, temp, low, mid);
			numOfInversion += mergeSort2(input, temp, mid + 1, high);
			numOfInversion += merge(input, temp, low, mid, high);
		}
		return numOfInversion;
	}

	public static int merge(int[] input, int[] temp, int low, int mid, int high) {
		int i = low; 
		int j = mid+1; 
		int k = low;
		int numOfInversion = 0;

		while (i <= mid && j <= high)  {
			if(input[i] < input[j])
			{
				temp[k] = input[i];
				i++;
			}else
			{
				temp[k] = input[j];
				j++;
				numOfInversion = numOfInversion + (mid+1-i);
			}
			k++;
		}
		if (i> mid) {
			while (j <= high) {
				temp[k] = input[j];
				k++;
				j++;
			}
			
		}else {
			while (i <= mid) {
				temp[k] = input[i];
				k++;
				i++;
			}
		}

		for (i=low; i <= high; i++)
			input[i] = temp[i];

		return numOfInversion;
	}
}