import java.util.ArrayList;

class AlgoAsgn3
{
    public static void main(String[] args)
    {
        int[] a = { 1,2,3 };
        int[] b = { 1,8 };
        for(int i =0; i<= 1; i++)
            for(int j=0;j<=2000000; j++) {
                String temp = Integer.toString(i);
                int[] newGuess = new int[temp.length()];
                for (int tempI = 0; tempI < temp.length(); tempI++)
                {
                    newGuess[tempI] = temp.charAt(tempI) - '0';
                }
                String temp2 = Integer.toString(j);
                int[] newGuess2 = new int[temp2.length()];
                for (int tempI = 0; tempI < temp2.length(); tempI++)
                {
                    newGuess2[tempI] = temp2.charAt(tempI) - '0';
                }

                if (addArray(newGuess, newGuess2, false) != i+j)
                {
//                    for(int asdf : newGuess2)
//                    {
//                        System.out.print(asdf);
//                    }
//                    System.out.println();
//                    System.out.println(j);
                    System.out.println(i + " + " + j + " = " + addArray(newGuess, newGuess2, false));
                }
            }

//        System.out.println(addArray(a,b));
        System.out.println("Complete");
        long c = 444455555;
        long d = 5;


//        System.out.println(prod2(c,d));
        // System.out.println(prod3(a,b));
    }
    private static int addArray(int[] a, int[] b, boolean subtract)
    {
        int ans =0;
        int carry = 0;
        int [] smaller, larger;
        if(a.length > b.length)
        {
            larger = a;
            smaller = b;
        }else
        {
            larger = b;
            smaller =a;
        }
        ArrayList<Integer> answer = new ArrayList<Integer>();


        for(int i = larger.length-1; i>=0; i--)
        {
            int temp = larger[i];
            if (carry != 0)
            {
                temp += carry;
                carry = 0;
                if (temp >= 10) {
                    temp = temp % 10 ;
                    carry++;
                }
            }
            int j = (smaller.length-(larger.length-i) );
            if(j >= 0)
            {
                temp += smaller[j];
//                System.out.println(temp);
                if (temp >= 10) {
                    temp = temp % 10 ;
                    carry++;

                }
            }
            answer.add(0,temp);
        }
        if(carry != 0)
        {
            answer.add(0,carry);
        }

        String stringAns = "";
        for (int n : answer)
        {
            stringAns += n;
        }

        return Integer.parseInt(stringAns);
    }
    public static long  prod2(long u, long v)
    {
        long x,y,w,z,r,p,q;
        long n,m;
        n = String.valueOf(Math.max(u,v)).length();
        if( u==0 || v==0)
            return 0;
        else if( n <= 2)
            return u*v;
        else {
            m = n/2;
            x = u / (long)Math.pow(10,m);
            y = u % (long)Math.pow(10,m);
            w = v / (long)Math.pow(10,m);
            z = v % (long)Math.pow(10,m);

            r = prod2(x+y, w+z);
            p = prod2(x,y);
            q = prod2(y,z);
            return (long)(p * Math.pow(10, 2*m) + (r-p-q) * Math.pow(10,m) + q);
        }
    }
    public static int[] prod3(int[] u, int[] v)
    {
        // 	long x,y,w,z,r,p,q;
        // 	int n,m;
        // 	//Determine bigger number, set n to be the digits in the larger one
        // 	if(u.length() == v.length())
        // 	  n = u.length();
        // 	else if (u.length() > v.length())
        // 	  n = u.length();
        // 	else if (u.length < v.length())
        //     n = v.length();

        // 	if( (u.length() == 1 && u[0] ==0) || (v.length() == 1 && v[0]==0))
        // 	{
        // 		return new int[] { 0 };
        // 	}
        // 	else if( n <= 2)
        // 	{
        // 	  String uTemp = "";
        // 	  String vTemp = "";
        //     for(int temp : u)
        //       uTemp += temp;
        //     for(int temp : v)
        //       vTemp += temp;

        // 	  return Integer.parseInt(u)*Integer.parseInt(v);
        // 	}
        // 	else {
        // 		m = n/2;
        // 		x = u / (long)Math.pow(10,m); //Base
        // 		y = u % (long)Math.pow(10,m); //Remainder
        // 		w = v / (long)Math.pow(10,m);
        // 		z = v % (long)Math.pow(10,m);

        //     //What the heck am I going to do here?
        // 		r = prod3(x+y, w+z);
        // 		p = prod3(x,y);
        // 		q = prod3(y,z);
        // 		return (long)(p * Math.pow(10, 2*m) + (r-p-q) * Math.pow(10,m) + q);
        // 	}
        return new int[] { 1,2,3} ;
    }


}