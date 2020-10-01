package br.com.java.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;






public class ListaMercadoriasFrame extends JFrame{
	
	//private MercadoriaTable tabela;
	private JScrollPane scrollPane;
	private JButton bNovaMercadoria;
	private JButton bBuscarMercadoria;
	private JButton bAtualizaLista;
	private JMenuBar menubar;
	
	
	public ListaMercadoriasFrame() {
		//setTitle("Lista de Mercadoria");
        setTitle("Lista de Mercadoria");
		
		inicializaComponentes();
		adicionaComponentes();
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}


	private void inicializaComponentes() {
		// TODO Auto-generated method stub
//		tabela = new MercadoriaTable();
//		tabela.addMouseListener(new EditarMercadoriaListener());
//		scrollPane = new JScrollPane();
//		scrollPane.setViewportView(tabela);

		bNovaMercadoria = new JButton();
		bNovaMercadoria.setText("Nova");
		bNovaMercadoria.setMnemonic(KeyEvent.VK_N);
//		bNovaMercadoria.addActionListener(new IncluirMercadoriaListener());

		bBuscarMercadoria = new JButton();
		bBuscarMercadoria.setText("Buscar");
		bBuscarMercadoria.setMnemonic(KeyEvent.VK_B);
		bBuscarMercadoria.addActionListener(new BuscarMercadoriaListener());
		
		bAtualizaLista = new JButton();
		bAtualizaLista.setText("Atualizar");
		bAtualizaLista.setMnemonic(KeyEvent.VK_A);
		bAtualizaLista.addActionListener(new AtualizarListaListener());
		
		menubar = new JMenuBar();
//		MenuF1 mAjuda = new MenuF1("Ajuda");
//		mAjuda.setMnemonic(KeyEvent.VK_J);
//        JMenuItem miSobre = new JMenuItem("Sobre    - F1");
//        miSobre.addActionListener(new SobreMenuListener());
//        mAjuda.addListener(new SobreMenuListener());
//        mAjuda.add(miSobre);
//        menubar.add(mAjuda);
//        setJMenuBar(menubar);
		
//		incluirFrame = new IncluirMercadoriaFrame(this);
//		editarFrame = new EditarMercadoriaFrame(this);
//		buscaFrame = new BuscaMercadoriaFrame(this);
//		sobreFrame = new SobreFrame();
//		
//		inicializaDB();
	}


	private void adicionaComponentes() {
		// TODO Auto-generated method stub
		
	}
	private class BuscarMercadoriaListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//buscaFrame.setVisible(true);
		}
	}
	private class AtualizarListaListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(newAtualizaMercadoriasAction());
		}
	}
	public Runnable newAtualizaMercadoriasAction() {
		return new Runnable() {
			public void run() {
				try {
//					MercadoriaDAO dao = new MercadoriaDAOJDBC();
//					tabela.reload(dao.getAll());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ListaMercadoriasFrame.this,
							ex.getMessage(), "Erro ao consultar Mercadoria(s)", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}



}
