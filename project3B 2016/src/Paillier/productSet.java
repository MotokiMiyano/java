package Paillier;

import java.math.BigInteger;
import java.util.Random;

public class productSet {
	public static void main(String[]args){
		BigInteger[] ei = productA();
		BigInteger[] dj = productB(ei);

		//復号
		for(int i = 0;i<dj.length;i++){
			System.out.println(Paillieranngou.fukugou(dj[i],λ,N,μ));

		}
	}

	//公開鍵
	static BigInteger N;
	static BigInteger g;
	//秘密鍵
	static BigInteger λ;
	static BigInteger μ;

	/**
	 * Aの操作
	 * @return 各定数を暗号化した配列
	 */
	static BigInteger[] productA(){
		int n = 4;
		BigInteger[] Sa = new BigInteger[n];
		//初期化
		for(int i = 0;i<Sa.length;i++){
			Sa[i] = BigInteger.ZERO;
		}
		//Saの決定
		Sa[0] = new BigInteger("1");
		Sa[1] = new BigInteger("2");
		Sa[2] = new BigInteger("4");
		Sa[3] = new BigInteger("11");

		//多項式計算ciを結果とする。配列番号が次数
		BigInteger [] ci = new BigInteger[n+1];
		BigInteger [] sci = new BigInteger[n+1];
		//初期化
		for(int i = 0;i<ci.length;i++){
			ci[i] = BigInteger.ZERO;
			sci[i]= BigInteger.ZERO;
		}
		//配列の最初（定数）に１を代入
		ci[0] = BigInteger.ONE;

		for(int i = 0 ;i<n;i++){
			//ciの値を別の配列に保存
			for(int k = 0;k<ci.length;k++){
				sci[k] = ci[k];
			}
			//次数を増やす
		    for(int j = 0;j < n;j++){
		    	ci[j+1] = sci[j];
		    }
		    //次数を増やしたので定数に0を代入
		    ci[0] = BigInteger.ZERO;
		    //各項を計算
		    for(int j = 0;j<ci.length;j++){
		    	ci[j] = ci[j].add(sci[j].multiply(Sa[i].negate()));
		    }
		}

		/*
		//結果表示（確認用）
		for(int j = 0;j<ci.length;j++){
			System.out.println(ci[j]);
		}
		*/

		//暗号化後配列用意
		BigInteger[] Enc = new BigInteger[n+1];
		//秘密鍵、公開鍵取得
		BigInteger[] key =  Paillieranngou.Paillie();
		N = key[0];
		g = key[1];
		λ = key[2];
		μ = key[3];

		//暗号化
		for(int i =0;i<Enc.length;i++){
			Enc[i] = Paillieranngou.anngou(N, g, ci[i]);
		}
		for(int i = 0;i<Enc.length;i++){
			System.out.println("Enc:"+Enc[i]);
		}
		return Enc;
	}
	/**
	 * Bの操作
	 * @param ei Aから受け取った暗号化された配列
	 * @return 計算結果dj配列
	 */
	static BigInteger[] productB(BigInteger[] ei) {
		int m = 8;
		BigInteger [] Sb = new BigInteger[m];
		for(int i = 0;i<Sb.length;i++){
			Sb[i] = BigInteger.ZERO;
		}
		//Sbの決定
		Sb[0] = new BigInteger("2");
		Sb[1] = new BigInteger("4");
		Sb[2] = new BigInteger("5");
		Sb[3] = new BigInteger("6");
		Sb[4] = new BigInteger("7");
		Sb[5] = new BigInteger("8");
		Sb[6] = new BigInteger("9");
		Sb[7] = new BigInteger("10");

		//送信用配列用意
		BigInteger[] dj = new BigInteger [m];

		//djを計算
		for(int j = 0;j<m;j++){
			//暗号化数値
			BigInteger Enc = Paillieranngou.anngou(N, g, Sb[j]);
			//Π内の計算
			BigInteger mul = multi(ei,Sb[j]);
			//乱数生成
			BigInteger rnd = new BigInteger(N.bitLength(),new Random());
			//結果を配列に代入
			dj[j] = (mul.modPow(rnd,N.pow(2)).multiply(Enc));
		}

		return dj;
	}

	/**
	 * Π内の計算
	 * @param ei Aから受け取った暗号配列
	 * @param bi Sbの要素
	 * @return
	 */
	static BigInteger multi(BigInteger[]ei,BigInteger bi){
		BigInteger mul = BigInteger.ONE;
		for(int i = 0;i<ei.length;i++){
			mul = (mul.multiply(ei[i].modPow(bi.pow(i),N.pow(2))));
		}
		return mul;
	}






}
