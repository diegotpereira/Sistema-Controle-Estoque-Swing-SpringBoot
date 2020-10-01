package br.com.java.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
				
			} catch (Exception e2) {
				// TODO: handle exception
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

}
