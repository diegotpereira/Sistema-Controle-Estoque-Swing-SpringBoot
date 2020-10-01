package br.com.java.view;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.KeyStroke;

public class MenuF1 extends JMenu {
	
	public MenuF1(String title) {
		super(title);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"),"click");
	}

}
