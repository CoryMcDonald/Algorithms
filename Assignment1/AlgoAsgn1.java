import java.util.Scanner;

class AlgoAsgn1 {

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			System.out.println(Encrypt(scanner.nextLine()));
		}
		scanner.close(); // Not required for stdin but good practice
	}
	public static String Encrypt (String input)
	{
		String returnValue = "";
		if (input != null && !input.isEmpty())
		{
			String[] array = input.split(" ");
			if( array.length > 1 )
			{
				int rotate = Integer.parseInt(array[0]);
				StringBuilder stringInput = new StringBuilder(array[1]);
				stringInput = stringInput.reverse();

				char letters[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z', '_', '.' };

				int index =0;
				for(int i=0; i < stringInput.length(); i++)
				{
					for(int j=0; j < letters.length; j++)
					{
						if(letters[j] == stringInput.toString().charAt(i))
						{
							index = j+rotate;
							if(j+rotate > letters.length-1)
								index -= letters.length;
							stringInput.setCharAt(i, letters[index]);
						break; //If we have an offset of one it'll break
						}
					}
				}
				returnValue = (stringInput.toString());
			}
		}
		return returnValue;
	}
}