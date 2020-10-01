package br.com.java.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.java.dao.MercadoriaDAO;
import br.com.java.dao.MercadoriaDAOJDBC;
import br.com.java.model.Mercadoria;

import java.text.ParseException;


public class IncluirMercadoriaFrame extends JFrame{
	
	private JTextField tfNome;
	private JFormattedTextField tfQuantidade;
	private JTextField tfDescricao;
	private JTextField tfPreco;
	private JFormattedTextField tfId;
	
	private JButton bSalvar;
	private JButton bCancelar;
	protected JButton bExcluir;
	
	private ListaMercadoriasFrame framePrincipal;
	
	public IncluirMercadoriaFrame(ListaMercadoriasFrame framePrincipal) {
		this.framePrincipal = framePrincipal;
		setTitle("Incluir Mercadoria");
		setSize(300,250);
		setLocationRelativeTo(null);
		setResizable(false);
		inicializaComponentes();
		resetForm();
	}

	private void inicializaComponentes() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(montaPanelEditarMercadoria(), BorderLayout.CENTER);
		panel.add(montaPanelBotoesEditar(), BorderLayout.SOUTH);
		add(panel);
		
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
		
	}
	private Component montaPanelBotoesEditar() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();

		bSalvar = new JButton("Salvar");
		bSalvar.setMnemonic(KeyEvent.VK_S);
		bSalvar.addActionListener(new SalvarMercadoriaListener());
		panel.add(bSalvar);

		bCancelar = new JButton("Cancelar");
		bCancelar.setMnemonic(KeyEvent.VK_C);
		bCancelar.addActionListener(new CancelarListener());
		panel.add(bCancelar);
		
		bExcluir = new JButton();
		bExcluir.setText("Excluir");
		bExcluir.setMnemonic(KeyEvent.VK_E);
		bExcluir.addActionListener(new ExcluirMercadoriaListener());
		bExcluir.setVisible(false);
		panel.add(bExcluir);

		return panel;
	}

	private Component montaPanelEditarMercadoria() {
		// TODO Auto-generated method stub
		JPanel painelEditarMercadoria = new JPanel();
		painelEditarMercadoria.setLayout(new GridLayout(8, 1));
		
		tfNome = new JTextField();
		tfDescricao = new JTextField();
		tfPreco = new JTextField();
		tfQuantidade = new JFormattedTextField();
		tfId = new JFormattedTextField();
		tfId.setEnabled(false);

		painelEditarMercadoria.add(new JLabel("Nome:"));
		painelEditarMercadoria.add(tfNome);
		painelEditarMercadoria.add(new JLabel("Descrição:"));
		painelEditarMercadoria.add(tfDescricao);
		painelEditarMercadoria.add(new JLabel("Preço:"));
		painelEditarMercadoria.add(tfPreco);
		painelEditarMercadoria.add(new JLabel("Quantidade:"));
		painelEditarMercadoria.add(tfQuantidade);
		painelEditarMercadoria.add(new JLabel("Id:"));
		painelEditarMercadoria.add(tfId);
		
		return painelEditarMercadoria;
	}

	private void resetForm() {
		// TODO Auto-generated method stub
		tfId.setValue(null);
		tfNome.setText("");
		tfDescricao.setText("");
		tfPreco.setText("");
		tfQuantidade.setValue(new Integer(1));
		
	}
	private class SalvarMercadoriaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				
				Mercadoria m = loadMercadoriaFromPanel();
				MercadoriaDAO dao = new MercadoriaDAOJDBC();
				dao.save(m);
				
				setVisible(false);
				resetForm();
				SwingUtilities.invokeLater(framePrincipal.newAtualizaMercadoriasAction());
				
			} catch (Exception ex) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(IncluirMercadoriaFrame.this, 
						ex.getMessage(), "Erro ao incluir Mercadoria", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	private class CancelarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class ExcluirMercadoriaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	public Mercadoria loadMercadoriaFromPanel() throws ParseException {
		// TODO Auto-generated method stub
		String msg = validador();
		
		if (!msg.isEmpty()) {
			throw new RuntimeException("Informe o(s) campo(s): "+msg);
		}
		
		String nome = tfNome.getText().trim();
		String descricao = tfDescricao.getText().trim();
		
		if (nome.length() < 5) {
			throw new RuntimeException("O nome deve conter no mínimo 5 caracteres!");
		}
		
		Integer quantidade = null;
		
		try {
			quantidade = Integer.valueOf(tfQuantidade.getText());
		} catch (NumberFormatException nex) {
			// TODO: handle exception
			throw new RuntimeException("Erro durante a conversão do campo quantidade (Integer).\nConteudo inválido!");
		}
		
		if (quantidade < 1) {
			throw new RuntimeException("O valor mínimo da quantidade deve ser 1!");
		}
		
		Double preco = null;
		
		preco = Mercadoria.formatStringToPreco(tfPreco.getText());
		
		if (preco < 1) {
			throw new RuntimeException("O valor mínimo do preço deve ser 1!");
		}
		
		return new Mercadoria(null, nome, descricao, quantidade, preco);
	}
	
	
	
	
	
	
	
	private String validador() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(tfNome.getText() == null || "".equals(tfNome.getText().trim()) ? "Nome, " : "");
		sb.append(tfPreco.getText() == null || "".equals(tfPreco.getText().trim()) ? "Preço, " : "");
		sb.append(tfQuantidade.getText() == null || "".equals(tfQuantidade.getText().trim()) ? "Quantidade, " : "");
		
		if (!sb.toString().isEmpty()) {
			sb.delete(sb.toString().length()-2, sb.toString().length());
		}
		return sb.toString();
	}

	public void setMercadoria(Mercadoria m){
		resetForm();
		if (m != null) {
			populaTextFields(m);
		}
	}

	private void populaTextFields(Mercadoria m) {
		// TODO Auto-generated method stub
		tfId.setValue(m.getId());
		tfNome.setText(m.getNome());
		tfDescricao.setText(m.getDescricao());
		tfQuantidade.setValue(m.getQuantidade());
		tfPreco.setText(Mercadoria.convertPrecoToString(m.getPreco()));
		
	}

}
