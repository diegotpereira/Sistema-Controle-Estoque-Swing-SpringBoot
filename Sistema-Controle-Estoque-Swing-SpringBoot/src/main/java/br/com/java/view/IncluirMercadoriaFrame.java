package br.com.java.view;

import javax.swing.JFrame;



public class IncluirMercadoriaFrame extends JFrame{
	
	public IncluirMercadoriaFrame(ListaMercadoriasFrame framePrincipal) {
		this.framePrincipal = framePrincipal;
		setTitle("Incluir Mercadoria");
		setSize(300,250);
		setLocationRelativeTo(null);
		setResizable(false);
		inicializaComponentes();
		resetForm();
	}

}
