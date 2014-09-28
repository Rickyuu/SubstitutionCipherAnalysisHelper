package com.ricky.tool;

import com.ricky.ui.MainWindow;

/**
 * @author rickyuu 
 * @version 2014年9月28日 下午11:27:47
 */
public class Substitutor {

	/**
	 * use the substitute characters to get the possible plain text 
	 * @param subChs substitute characters
	 * @param text cipher text
	 * @return possible plain text
	 */
	public static String substitute(char[] subChs, String cipherText) {
		String lowerCipherText = cipherText.toLowerCase();
		char[] cipherChs = lowerCipherText.toCharArray();
		for(int i=0; i<cipherChs.length; i++) {
			char ch = cipherChs[i];
			if(ch >= 'a' && ch <= 'z' && subChs[ch-'a'] != MainWindow.NULL_CHAR) {
				cipherChs[i] = subChs[ch-'a'];
			}
		}
		return new String(cipherChs);
	}
	
}
