package br.com.java.view;

import javax.swing.JTable;

import br.com.java.model.Mercadoria;




public class MercadoriaTable extends JTable {
	
	private MercadoriaTableModel modelo;
	
	public Mercadoria getMercadoriaSelected() {
		int i = getSelectedRow();
		if (i < 0) {
			return null;
		}
		return modelo.getMercadoriaAt(i);
	}

}
