package main;

import gui.*;

public class Runner {
	
	public static void main(String[] args) {
		ABFrame mainFrame;

		mainFrame = new ABFrame();
		mainFrame.getContactList();
		mainFrame.setVisible(true);
	}

}
