/*
 * Name : Vishnu Vijayan
 * Roll no : CS15M055
 * Assignment - 3
 * CS-5820
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import org.xml.sax.ext.LexicalHandler;

public class CS15MO55
{
	public static final int INPUT = 10000;
	public static final int NO_OF_ROUNDS = 100;
	
	static double[] getRandomRating(int n)
	{
		double temp[] = new double[n];
		for(int i=0; i<n; ++i)
			temp[i] = Math.random();
		return temp;
	}
	
	static double getMedian(double[] numbers)
	{
		Arrays.sort(numbers);
		int arrayLength = numbers.length;
		return numbers[arrayLength/2];
	}
	
	static double[] getMembersOfSetR(double[] numbers)
	{
		int sqrtN = (int)Math.sqrt(INPUT);
		int ssqrtN = (int)Math.sqrt(sqrtN);
		int usedN = (int)Math.pow(ssqrtN, 3);
		int[] selectedIndex = new int[usedN];
		//System.out.println(usedN);
		double[] selectedNumbers = new double[usedN];
		int i=0;
		while(i < usedN)
		{
			int temp = (int)(Math.random()*INPUT);
			System.out.println(temp);
			selectedIndex[i] = temp;
			++i;
		}
		for(i=0;i<usedN; ++i)
			selectedNumbers[i] = numbers[selectedIndex[i]];
		return selectedNumbers;
	}
	
	static double randomizedAlgorithm(double[] numbers)
	{
		double[] setR = getMembersOfSetR(numbers);
		int lengthR = setR.length;
		Arrays.sort(setR);
		int sqrtN = (int)Math.sqrt(INPUT);
		double d = setR[lengthR/2 - sqrtN];
		double u = setR[lengthR/2 + sqrtN];
		int lengthD = 0;
		int lengthU = 0;
		//System.out.println(d+"   "+u);
		ArrayList<Double> setC = new ArrayList<Double>();
		for(int i=0;i<INPUT; ++i)
		{
			if(numbers[i] < d)
				++lengthD;
			else if(numbers[i] > u)
				++lengthU;
			else
				setC.add(numbers[i]);
		}
		Collections.sort(setC);
		int ssqrtN = (int)Math.sqrt(sqrtN);
		int usedN = (int)Math.pow(ssqrtN, 3);
		//System.out.println(lengthD+"   "+lengthU);
		if(lengthD > INPUT/2 || lengthU > INPUT/2)
			return -1;
		else if(setC.size() > 4*usedN)
			return -1;
		else
			return setC.get(INPUT/2 - lengthD);
	}
	
	public static void main(String args[])
	{
		int i = 0;
		int count = 0;
		while(i < NO_OF_ROUNDS)
		{
			double[] numbers = getRandomRating(INPUT);
			double median = randomizedAlgorithm(numbers);
			double originalMedian = getMedian(numbers);
			if(median == -1 || median != originalMedian)
				++count;
			//System.out.println(median+" ---> "+originalMedian);
			++i;
		}
		System.out.println("Count of unspotted correct Median : "+count);
	}
}