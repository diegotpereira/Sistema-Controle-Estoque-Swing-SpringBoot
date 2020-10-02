package br.com.java.dao;


import java.util.List;

import br.com.java.model.Mercadoria;

public interface MercadoriaDAO  {

	void save(Mercadoria m);
	
	List<Mercadoria> getAll();
	
	Mercadoria findById(Integer id);

	void init();

	List<Mercadoria> getMercadoriasByNome(String nome);

}
