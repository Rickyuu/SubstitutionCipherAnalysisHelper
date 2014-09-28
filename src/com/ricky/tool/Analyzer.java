package com.ricky.tool;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.ricky.ui.MainWindow;

/**
 * @author rickyuu 
 * @version 2014年9月28日 下午11:27:27
 */
public class Analyzer {

	
	private static NumberFormat numberFormat = new DecimalFormat("00.00");
	/**
	 * analyze the cipher text and return a String array with frequency
	 * @param text the cipher text 
	 * @return the frequency array
	 */
	public static String[] analyze(String text) {
		int[] counts = new int[MainWindow.FREQUENCY_LABEL_SIZE];
		int countAll = 0;
		for(int i=0; i<counts.length; i++) {
			counts[i] = 0;
		}
		String lowerText = text.toLowerCase();
		char[] textChs = lowerText.toCharArray();
		for(char ch : textChs) {
			if(ch >= 'a' && ch <= 'z') {
				counts[ch-'a'] ++;
				countAll ++;
			}
		}
		
		String[] frequencyStrings = new String[MainWindow.FREQUENCY_LABEL_SIZE];
		// if all the count are 0, result a string array of 16 '00.00'
		if(countAll == 0) {
			for(int i=0; i<frequencyStrings.length; i++) {
				frequencyStrings[i] = numberFormat.format(0);
			}
			return frequencyStrings;
		} else {
			for(int i=0; i<frequencyStrings.length; i++) {
				frequencyStrings[i] = numberFormat.format(counts[i]*100.0/countAll);
			}
			return frequencyStrings;
		}
	}
	
}
