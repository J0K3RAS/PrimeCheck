import java.util.Random;   
import java.math.BigInteger; 

/**
 * This class checks if numbers 2^(100i) -1, where i belongs to [1,10] is prime
 *
 * @author Charalampos Bekiaris
 * @version 7-1-2021
 */
public class prime_check
{
    /**
     * Constructor for objects of class prime_check
     */
    public prime_check()
    {
        BigInteger b = BigInteger.ZERO;
        BigInteger two = new BigInteger("2");
        int[] powers = {127, 521, 1279, 146, 415, 1194};
        
        for (int n: powers){
            b = two.pow(n).subtract(BigInteger.ONE); // 2^n -1
            System.out.println("Checking if 2^"+n+" -1 is prime: "+is_prime(b,n));
        }
    }

    /**
     * Calculate a^(n-1) modn
     *
     * @param  a  base
     * @param  n  power
     * @param  p  divisor
     * @return    a to the power n-1 modulo n
     */
    public BigInteger fastmodpower(BigInteger a, BigInteger n, BigInteger p)
    {
        BigInteger one = BigInteger.ONE;
        BigInteger res = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        
        while (n.compareTo(BigInteger.ZERO)>0){
            if (n.mod(two).equals(one))
                res = res.multiply(a).mod(p);
            n = n.divide(two);
            a = a.multiply(a).mod(p);
        }
        
        
        return res;
    }
    
    /**
     * Given n check if 2^n-1 is prime
     *
     * @param  p  the number to check for. Should be >40
     * @param  n  the power to which 2 is raised. Required to generate a random number
     * @return    true or false
     */
    public boolean is_prime(BigInteger p, int n)
    {
        BigInteger one = BigInteger.ONE;
        BigInteger q = p.subtract(one); // (2^n -1) -1
        
        for (int i = 1; i<=40; i++){
            BigInteger a = getRandomBigInteger(n,p);
            
            if (!(fastmodpower(a,q,p).equals(one)))
                return false;
        }
        
        return true;
    }
    
    /**
     * Generates a random number between 1, 2^n - 2 
     *
     * @param  n  power to raise 2
     * @param  b  UpperBound
     * @return    The random number
     */
    public static BigInteger getRandomBigInteger(int n,BigInteger b) {
        Random rand = new Random();
        BigInteger result = new BigInteger(n, rand); // random number in [0,2^n -1] 
        
        if (result.equals(BigInteger.ZERO) || result.equals(b))  
            return getRandomBigInteger(n,b);                     
        
        return result;
        }
}