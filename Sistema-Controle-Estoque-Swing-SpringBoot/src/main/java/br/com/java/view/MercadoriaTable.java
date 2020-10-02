package br.com.java.view;

import java.util.List;

import javax.swing.JTable;

import br.com.java.model.Mercadoria;





public class MercadoriaTable extends JTable {
	
	private MercadoriaTableModel modelo;
	
	public MercadoriaTable() {
		modelo = new MercadoriaTableModel();
		setModel(modelo);
	}
	
	public Mercadoria getMercadoriaSelected() {
		int i = getSelectedRow();
		if (i < 0) {
			return null;
		}
		return modelo.getMercadoriaAt(i);
	}
	public void reload(List<Mercadoria> mercadorias) {
		modelo.reload(mercadorias);
	}

}
