package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import br.com.java.dao.MercadoriaDAO;
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
	public void save(Mercadoria mercadoria) throws PersistenceException{
		// TODO Auto-generated method stub
		
		if (mercadoria == null) {
			
			throw new PersistenceException("Informe a Mercadoria para salvar!");
			
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			
			conn = ConnectionManager.getConnection();
			if (mercadoria.getId() == null) {
				stmt = getStatementInsert(conn, mercadoria);
			} else {
				stmt = getStatementUpdate(conn, mercadoria);
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
}
