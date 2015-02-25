import java.math.BigInteger;
import java.util.Scanner;

class AlgoAsgn3
{
    public static void main(String[] args) {
        BigInteger input1 = new BigInteger("0");
        BigInteger input2 = new BigInteger("0");
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if(input != "")
            {
                if(counter == 0)
                {
                    input1 = new BigInteger(input);
                }else
                {
                    input2 = new BigInteger(input);
                    System.out.println(prod2(input1, input2).toString());
                }
                counter ++;
                if(counter == 2)
                    counter = 0;
            }
        }
        scanner.close();
        
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
            p = prod2(x,w);
            q = prod2(y,z);
            return (long)(p * Math.pow(10, 2*m) + (r-p-q) * Math.pow(10,m) + q);
        }
    }
    public static BigInteger prod2(BigInteger u, BigInteger v)
    {
        BigInteger x,y,w,z,r,p,q;
        long n,m;

        n = u.max(v).toString().length();
        if( u.equals(0) || v.equals(0))
            return new BigInteger("0");
        else if( n <= 2)
            return u.multiply(v); //u*v
        else {
            m = n/2;
            x = u.divide(new BigInteger( Long.toString((long)Math.pow(10, m))));
            y = u.remainder(new BigInteger(Long.toString((long)Math.pow(10, m))));
            w = v.divide(new BigInteger(Long.toString((long)Math.pow(10, m))));
            z = v.remainder(new BigInteger(Long.toString((long)Math.pow(10, m))));

            r = prod2(x.add(y), w.add(z));
            p = prod2(x,w);
            q = prod2(y,z);
            //(p * Math.pow(10, 2*m) + (r-p-q) * Math.pow(10,m) + q);
            BigInteger temp1 = p.multiply(new BigInteger(Long.toString((long)Math.pow(10, 2 * m))));
            BigInteger temp2 = r.subtract(p).subtract(q);
            BigInteger temp3 = temp2.multiply(new BigInteger(Long.toString((long)Math.pow(10,m))));

            return temp1.add(temp3).add(q);
        }
    }

}