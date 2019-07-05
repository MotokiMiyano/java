package ElGamal;

import java.math.BigInteger;

public class jouhougyakugenn {
	public static void main(String []args){
		BigInteger x = Inv(new BigInteger("11"),new BigInteger("97"));
		System.out.println(x);
	}
	public static BigInteger Inv(BigInteger x, BigInteger p){
		BigInteger q;
		
		BigInteger[] A = new BigInteger [2];
		BigInteger[] B = new BigInteger [2];
		
		int b = 0;
		q = BigInteger.ZERO;
		
		
		A[0] = p;
		A[1] = x;
		
		B[0] = BigInteger.ZERO;
		B[1] = BigInteger.ONE;
		
		while(A[1-b].compareTo(BigInteger.ONE) != 0){
			q = A[b].divide(A[1-b]);
			A[b] = A[b].mod(A[1-b]);
			B[b] = (B[b].subtract(q.multiply(B[1-b]))).mod(p);
			b = 1 - b;
		}
		
		if(B[1-b].compareTo(BigInteger.ZERO) < 0){
			B[1-b] = B[1-b].add(p);
		}
		
		return B[1-b];
		
	}
	
}
