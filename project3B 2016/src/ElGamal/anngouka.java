package ElGamal;

import java.math.*;
import java.util.Random;

public class anngouka {
	public static void main(String[]args){
		
		BigInteger p = new BigInteger("136834894912985400887780161131956490657484067"
				+ "26964977959778869049542442702920703566172969906"
				+ "84914037262959288638560474422177917106347442142"
				+ "65844786314272138577534377095876945276011170658"
				+ "26976221013729636185460748996610234365948429356"
				+ "76121761452599087375691965635977079594440396933"
				+ "93628375648677946351206178951");
		
		BigInteger g = new BigInteger("123456789123456789123456789123456789123456789"
				+ "12345678912345678912345678912345678912345678912"
				+ "34567891234567891234567891234567891234567891234"
				+ "56789123456789123456789123456789123456789123456"
				+ "78912345678912345678912345678912345678912345678"
				+ "91234567891234567891234567891234567891234567891"
				+ "23456789123456789123456789");
		
		BigInteger x = RandomBigIntegergenerate(p.subtract(new BigInteger("2"))).add(BigInteger.ONE);
		
	    BigInteger y = g.modPow(x, p);
		
		anngou(p, g, y, x);
	}
	
	
	
	public static BigInteger RandomBigIntegergenerate(BigInteger p) {
		int bit;
	    BigInteger num;
	    BigInteger max = p;

	    for (bit = 1; max.compareTo(BigInteger.ZERO) > 0; 
	         max = max.divide(new BigInteger("2"))) {
	      bit++;
	    }

	    num = new BigInteger(bit, new Random());
	    num = num.remainder(p);
		return num;
	}

	static void anngou(BigInteger p, BigInteger g, BigInteger y, BigInteger x){
		BigInteger M = new BigInteger("12345");
		BigInteger r = RandomBigIntegergenerate(p.subtract(new BigInteger("1"))).add(BigInteger.ONE);
		BigInteger C1 = g.modPow(r, p);
		BigInteger C2 = (M.multiply(y.modPow(r, p))).mod(p);
		System.out.println("C1:"+C1+"\n"+"C2:"+C2);
		fukugou(C1,C2,p,x);
	}
	
	static void fukugou(BigInteger C1, BigInteger C2, BigInteger p, BigInteger x){
		BigInteger Inv = C1.modPow(x, p);
		BigInteger D = Inv.modInverse(p);
		BigInteger M = (C2.multiply(D)).mod(p);
		System.out.println("M:"+M);
	}

}
