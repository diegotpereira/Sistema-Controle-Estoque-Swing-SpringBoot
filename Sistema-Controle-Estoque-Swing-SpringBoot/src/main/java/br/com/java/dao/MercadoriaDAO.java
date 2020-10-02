package br.com.java.dao;


import java.util.List;

import br.com.java.model.Mercadoria;

public interface MercadoriaDAO  {

	void save(Mercadoria m);
	
	List<Mercadoria> getAll();

	void init();

}
