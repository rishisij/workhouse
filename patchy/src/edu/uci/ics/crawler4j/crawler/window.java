package edu.uci.ics.crawler4j.crawler;

import java.io.*;
import javax.swing.*;
public class window extends JPanel {
	
	public static void main(String[] args) {
		JFileChooser fc = new JFileChooser();        
    	fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    	fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        System.out.println(file.getPath());
	}

}
