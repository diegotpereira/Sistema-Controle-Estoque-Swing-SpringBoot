package br.com.java.model;

public class Mercadoria {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Double preco;
	
	public Mercadoria(Integer id, String nome, String descricao, Integer quantidade, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Mercadoria [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", quantidade=" + quantidade
				+ ", preco=" + preco + "]";
	}
	
	

}
