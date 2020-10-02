package br.com.java.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.java.model.Mercadoria;

public class MercadoriaTableModel extends AbstractTableModel{
	
	private List<Mercadoria> mercadorias;
	
	private String[] colNomes = { "Nome", "Descricao", "Preco", "Quantidade" };
	private Class<?>[] colTipos = { String.class, String.class, String.class, Integer.class };
	
	public MercadoriaTableModel(){
	}
	
	public void reload(List<Mercadoria> mercadorias) {
		this.mercadorias = mercadorias;
		fireTableDataChanged();
	}
	
	

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (mercadorias == null){
			return 0;
		}
		return mercadorias.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		Mercadoria m = mercadorias.get(linha);
		switch (coluna) {
		case 0:
			return m.getNome();
		case 1:
			return m.getDescricao();
		case 2:
			return Mercadoria.convertPrecoToString(m.getPreco());
		case 3:
			return m.getQuantidade();
		default:
			return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Mercadoria getMercadoriaAt(int index) {
		return mercadorias.get(index);
	}
	@Override
	public String getColumnName(int coluna) {
		return colNomes[coluna];
	}
	@Override
	public Class<?> getColumnClass(int coluna) {
		return colTipos[coluna];
	}

}
