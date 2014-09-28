package com.ricky.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ricky.tool.Analyzer;
import com.ricky.tool.Substitutor;

/**
 * @author rickyuu 
 * @version 2014年9月28日 下午7:04:16
 */
public class MainWindow {

	public static final int INPUT_OUTPUT_AREA_ROWS = 23;
	public static final int INPUT_OUTPUT_AREA_COLUMNS = 50;
	public static final int FREQUENCY_LABEL_SIZE = 26;
	public static final String INIT_FREQUENCY = "00.00";
	public static final int SUBSTITUTION_FIELD_SIZE = 5;
	public static final char NULL_CHAR = '0';
	
	private JTextArea inputArea;
	private JLabel[] frequencyAlphabets;
	private JLabel[] frequencyLabels;
	private JTextArea outputArea;
	private JLabel[] substitutionAlphabets;
	private JTextField[] substitutionFields;
	
	public static void main(String args[]) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.initComponents();
	}
	
	public void initComponents() {
		JLabel inputLabel = new JLabel("input your cipher text here:");
		inputArea = new JTextArea(INPUT_OUTPUT_AREA_ROWS, INPUT_OUTPUT_AREA_COLUMNS);
		inputArea.setLineWrap(true);
		JPanel inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(600, 500));
		inputPanel.add(inputLabel);
		inputPanel.add(inputArea);
		
		frequencyAlphabets = new JLabel[FREQUENCY_LABEL_SIZE];
		frequencyLabels = new JLabel[FREQUENCY_LABEL_SIZE];
		JPanel[] frequencyPanels = new JPanel[FREQUENCY_LABEL_SIZE];
		for(int i=0; i<frequencyAlphabets.length; i++) {
			char temp = (char) ('a' + i);
			frequencyAlphabets[i] = new JLabel(temp + ":");
			frequencyLabels[i] = new JLabel(INIT_FREQUENCY);
			frequencyPanels[i] = new JPanel();
			frequencyPanels[i].add(frequencyAlphabets[i]);
			frequencyPanels[i].add(frequencyLabels[i]);
			inputPanel.add(frequencyPanels[i]);
		}
		
		JButton analysisButton = new JButton("analyse");
		analysisButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cipherText = inputArea.getText();
				String[] frequencyStrings = Analyzer.analyze(cipherText);
				for(int i=0; i<frequencyStrings.length; i++) {
					frequencyLabels[i].setText(frequencyStrings[i]);
				}
			}
		});
		inputPanel.add(analysisButton);
		
		
		JLabel outputLabel = new JLabel("the plain text after substitution:");
		outputArea = new JTextArea(INPUT_OUTPUT_AREA_ROWS, INPUT_OUTPUT_AREA_COLUMNS);
		outputArea.setLineWrap(true);
		JPanel outputPanel = new JPanel();
		outputPanel.setPreferredSize(new Dimension(600, 500));
		outputPanel.add(outputLabel);
		outputPanel.add(outputArea);
		
		substitutionAlphabets = new JLabel[FREQUENCY_LABEL_SIZE];
		substitutionFields = new JTextField[FREQUENCY_LABEL_SIZE];
		JPanel[] substitutionPanels = new JPanel[FREQUENCY_LABEL_SIZE];
		for(int i=0; i<substitutionAlphabets.length; i++) {
			char temp = (char) ('a' + i);
			substitutionAlphabets[i] = new JLabel(temp + ":");
			substitutionFields[i] = new JTextField(SUBSTITUTION_FIELD_SIZE);
			substitutionPanels[i] = new JPanel();
			substitutionPanels[i].add(substitutionAlphabets[i]);
			substitutionPanels[i].add(substitutionFields[i]);
			outputPanel.add(substitutionPanels[i]);
		}
		
		JButton substitutionButton = new JButton("substitute");
		substitutionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] subChs = new char[FREQUENCY_LABEL_SIZE];
				for(int i=0; i<subChs.length; i++) {
					String subString = substitutionFields[i].getText();
					if(subString != null && !subString.equals("")) {
						if(subString.length() != 1) {
							JOptionPane.showMessageDialog(null, "Substitution content is more than 1 character!");
							return;
						} else {
							char ch = subString.charAt(0);
							if(ch >= 'a' && ch <= 'z') {
								subChs[i] = ch;
							} else if(ch >= 'A' && ch <= 'Z') {
								subChs[i] = (char) (ch-'A'+'a');
							} else {
								JOptionPane.showMessageDialog(null, "Substitution content is not a character!");
								return;
							}
						}
					} else {
						subChs[i] = NULL_CHAR;
					}
				}
				String cipherText = inputArea.getText();
				String subResult = Substitutor.substitute(subChs, cipherText);
				outputArea.setText(subResult);
			}
		});
		outputPanel.add(substitutionButton);

		JFrame frame = new JFrame();
		frame.setSize(1200, 700);
		frame.add(inputPanel, BorderLayout.WEST);
		frame.add(outputPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2);
		frame.setVisible(true);
	}
	
}
