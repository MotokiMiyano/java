package Paillier;

import java.math.BigInteger;
import java.util.Random;

import ElGamal.anngouka;
import ElGamal.jouhougyakugenn;

public class Paillieranngou {
	public static void main(String[]args){
		BigInteger M = new BigInteger("12345");
		BigInteger M2 = new BigInteger("12345");
		BigInteger[] key = new BigInteger[4];
		key = Paillie();
		System.out.print(M.add(M2).mod(key[0]));
		BigInteger c= anngou(key[0],key[1], M);
		BigInteger c2= anngou(key[0],key[1], M2);
		BigInteger c3 = c.multiply(c2).mod(key[0].pow(2));
		M = fukugou(c3,key[2],key[0],key[3]);
		
	}
	
	static BigInteger[] Paillie(){
		BigInteger p,q,n,λ,g,μ;
		Random rnd = new Random(); 
		
		p = BigInteger.probablePrime(1024, rnd);
		q = BigInteger.probablePrime(1024, rnd);
		
		BigInteger pm1 = p.subtract(BigInteger.ONE);
		BigInteger qm1 = q.subtract(BigInteger.ONE);
		BigInteger pmqm = pm1.multiply(qm1);
		
		n = p.multiply(q);
		λ = pmqm.divide(pm1.gcd(qm1));
		
		g = n.add(BigInteger.ONE);
		
		μ = jouhougyakugenn.Inv(L(g.modPow(λ, n.pow(2)),n), n);
		
		BigInteger[] key = new BigInteger [4]; 
		
		key[0] = n;
		key[1] = g;
		key[2] = λ;
		key[3] = μ;
		
		
		
		return key;
	}
	
	static BigInteger L(BigInteger u, BigInteger n){
		return (u.subtract(BigInteger.ONE)).divide(n);
	}
	
	static BigInteger anngou(BigInteger n, BigInteger g, BigInteger M){
		BigInteger r,c;
		
		r = anngouka.RandomBigIntegergenerate(n);
		
		c = (g.modPow(M, n.pow(2)).multiply(r.modPow(n, n.pow(2)))).mod(n.pow(2));
		System.out.println("C:"+c);
		
		return c;
	}
	
	static BigInteger fukugou(BigInteger c, BigInteger λ, BigInteger n, BigInteger μ){
		BigInteger M = (L(c.modPow(λ, n.pow(2)),n).multiply(μ)).mod(n);
		System.out.println("M:"+M);
		return M;
	}

}
