package br.com.java.view;

import java.text.ParseException;

import br.com.java.model.Mercadoria;

public class EditarMercadoriaFrame extends IncluirMercadoriaFrame {

	public EditarMercadoriaFrame(ListaMercadoriasFrame framePrincipal) {
		// TODO Auto-generated constructor stub
		super(framePrincipal);
		setTitle("Editar Mercadoria");
		bExcluir.setVisible(true);
	}
	protected Mercadoria loadMercadoriaFromPanel() throws ParseException  {
		Mercadoria m = super.loadMercadoriaFromPanel();
		m.setId(getIdMercadoria());
		return m;
	}

}
