package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;


import br.com.java.exception.PersistenceException;
import br.com.java.model.Mercadoria;






public class MercadoriaDAOJDBC implements MercadoriaDAO {
	
	private final static String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS mercadoria (id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, nome VARCHAR(20) NOT NULL, descricao varchar(80) NOT NULL, preco decimal(10,2) NOT NULL, quantidade INTEGER NOT NULL)";
	private final static String INSERT_MERCADORIA = "INSERT INTO mercadoria (nome,descricao,preco,quantidade) VALUES (?,?,?,?)";
	private final static String UPDATE_MERCADORIA = "UPDATE mercadoria SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
	private final static String DELETE_MERCADORIA = "DELETE FROM mercadoria WHERE id = ?";
	private final static String GET_ALL_MERCADORIAS = "SELECT * FROM mercadoria";
	private final static String GET_MERCADORIAS_BY_NOME = "SELECT * FROM mercadoria WHERE nome like ?";
	private final static String GET_MERCADORIA_BY_ID = "SELECT * FROM mercadoria WHERE id = ?";
	
	private static Logger log = Logger.getLogger(MercadoriaDAOJDBC.class);
	
	@Override
	public void init() throws PersistenceException {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			int r = stmt.executeUpdate(CREATE_TABLE);
			
			if (r > 0) {
				log.info("Criou a tabela 'mercadoria'");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			log.error(e);
			throw new PersistenceException("Não foi possivel inicializar o banco de dados: " + CREATE_TABLE, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt);
		
		}		
	}

	@Override
	public void save(Mercadoria m) throws PersistenceException{
		// TODO Auto-generated method stub
		
		if (m== null) {
			
			throw new PersistenceException("Informe a Mercadoria para salvar!");
			
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			
			conn = ConnectionManager.getConnection();
			if (m.getId() == null) {
				stmt = getStatementInsert(conn, m);
			} else {
				stmt = getStatementUpdate(conn, m);
			}
			stmt.executeUpdate();
			conn.commit();
			log.debug("Mercadoria foi salva");
			
		} catch (SQLException e) {
			// TODO: handle exception
			log.error(e);
			throw new PersistenceException("Não foi possivel inicializar o banco de dados: " + CREATE_TABLE, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt);
		}
			
		}

	private PreparedStatement getStatementUpdate(Connection conn, Mercadoria m) throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement stmt = createStatementWithLog(conn, UPDATE_MERCADORIA);
		stmt.setString(1, m.getNome());
		stmt.setString(2, m.getDescricao());
		stmt.setDouble(3, m.getPreco());
		stmt.setInt(4, m.getQuantidade());
		stmt.setInt(5, m.getId());
		return stmt;
	}

	private PreparedStatement getStatementInsert(Connection conn, Mercadoria m) throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement stmt = createStatementWithLog(conn, INSERT_MERCADORIA);
		stmt.setString(1, m.getNome());
		stmt.setString(2, m.getDescricao());
		stmt.setDouble(3, m.getPreco());
		stmt.setInt(4, m.getQuantidade());
		return stmt;
	}
	private PreparedStatement createStatementWithLog(Connection conn, String sql) throws SQLException {
		// TODO Auto-generated method stub
		if (conn == null)
			return null;
		
		log.debug("SQL: "+sql);
		return conn.prepareStatement(sql);
	}

	@Override
	public List<Mercadoria> getAll()throws PersistenceException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = createStatementWithLog(conn, GET_ALL_MERCADORIAS);
			rs = stmt.executeQuery();
			
			return toMercadorias(rs);
		} catch (SQLException e) {
			String errorMsg = "Erro ao consultar todas as mercadorias!";
			log.error(errorMsg, e);
			throw new PersistenceException(errorMsg, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt, rs);
		}
	}

	private List<Mercadoria> toMercadorias(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Mercadoria> lista = new ArrayList<Mercadoria>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String descricao = rs.getString("descricao");
			int qtde = rs.getInt("quantidade");
			double preco = rs.getDouble("preco");
			
			lista.add(new Mercadoria(id, nome, descricao, qtde, preco));
		}
		return lista;
	}

	@Override
	public Mercadoria findById(Integer id) throws PersistenceException{
		// TODO Auto-generated method stub
		
		if (id == null || id.intValue() <= 0) {
			throw new PersistenceException("Informe o id válido para fazer a busca!");
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Mercadoria m = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = createStatementWithLog(conn, GET_MERCADORIA_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				int qtde = rs.getInt("quantidade");
				double preco = rs.getDouble("preco");
				
				m = new Mercadoria(id, nome, descricao, qtde, preco);
			}
			return m;
			
		} catch (SQLException e) {
			// TODO: handle exception
			String errorMsg = "Erro ao consultar mercadoria por id!";
			log.error(errorMsg, e);
			throw new PersistenceException(errorMsg, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt, rs);
		}
	}

	@Override
	public List<Mercadoria> getMercadoriasByNome(String nome) throws PersistenceException{
		// TODO Auto-generated method stub
		
		if (nome == null || nome.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = createStatementWithLog(conn, GET_MERCADORIAS_BY_NOME);
			stmt.setString(1, nome + "%");
			rs = stmt.executeQuery();
			
			return toMercadorias(rs);
		} catch (SQLException e) {
			// TODO: handle exception
			String errorMsg = "Erro ao consultar mercadorias por nome!";
			log.error(errorMsg, e);
			throw new PersistenceException(errorMsg, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt, rs);
		}
	}	
}
