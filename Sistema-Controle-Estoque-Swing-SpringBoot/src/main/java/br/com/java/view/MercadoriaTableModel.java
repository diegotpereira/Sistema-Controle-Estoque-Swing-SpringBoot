package br.com.java.view;

import java.util.List;

import br.com.java.model.Mercadoria;

public class MercadoriaTableModel {
	
	private List<Mercadoria> mercadorias;
	
	public Mercadoria getMercadoriaAt(int index) {
		return mercadorias.get(index);
	}

}
