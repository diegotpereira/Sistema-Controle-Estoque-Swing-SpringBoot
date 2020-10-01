package br.com.java.view;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import static br.com.java.util.ApplicationProperties.getBuild;
import static br.com.java.util.ApplicationProperties.getDesenvolvidoPor;
import static br.com.java.util.ApplicationProperties.getSite;
import static br.com.java.util.ApplicationProperties.getTitulo;
import static br.com.java.util.ApplicationProperties.getVersao;
import static br.com.java.util.ApplicationProperties.getURLLogo;

public class SobreFrame extends JFrame{
	
	public SobreFrame(){
		setTitle("Sobre a aplicação");
		setSize(730,230);
		setLocationRelativeTo(null);
		setResizable(false);
		inicializaComponentes();
	}

	private void inicializaComponentes() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(montaLabelsSobre(), BorderLayout.CENTER);
		add(panel);
		
	}

	private Component montaLabelsSobre() {
		// TODO Auto-generated method stub
		 JPanel painelLabels = new JPanel();
	        painelLabels.setBorder(BorderFactory.createEtchedBorder());
	        GroupLayout panelLayout = new GroupLayout(painelLabels);
	        
	        JLabel l1 = new JLabel("Aplicação:");
	        JLabel l2 = new JLabel("Versão:");
	        JLabel l3 = new JLabel("Desenvolvido por:");
	        JLabel l4 = new JLabel("Build");
	        JLabel l5 = new JLabel("Site:");
	        
	        JLabel lTitulo = new JLabel(getTitulo());
	        JLabel lVersao = new JLabel(getVersao());
	        JLabel lDesenvPor = new JLabel(getDesenvolvidoPor());
	        JLabel lBuild = new JLabel(getBuild());
	        JLabel lSite = new JLabel(getSite());
	        JPanel logo = new JPanel(){
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                if (getURLLogo() != null) {
	                    ImageIcon icon = new ImageIcon(getURLLogo());
	                    g.drawImage(icon.getImage(), 0, 0, null);
	                }
	            }
	        };
	        logo.setPreferredSize(new Dimension(160, 80));
	 
	        painelLabels.setLayout(panelLayout);
	        panelLayout.setHorizontalGroup(
	            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(createHorizontalGroup(panelLayout, 33,
	            		groupComponents(panelLayout, l1, l2, l3, l4, l5), 
	            		groupComponents(panelLayout, lTitulo, lVersao, lDesenvPor, lBuild, lSite))
	            	.addComponent(logo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            )
	        );
	        
	        panelLayout.setVerticalGroup(
	            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(panelLayout.createSequentialGroup()
	                .addGap(21, 21, 21)
	                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                		.addComponent(logo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                		.addGroup(createVerticalGroup(panelLayout,
	                				groupComponents(panelLayout, l1, lTitulo),
	                				groupComponents(panelLayout, l2, lVersao),
	                				groupComponents(panelLayout, l3, lDesenvPor),
	                				groupComponents(panelLayout, l4, lBuild),
	                				groupComponents(panelLayout, l5, lSite)))
	                ).addContainerGap(203, Short.MAX_VALUE))
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(painelLabels, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(painelLabels, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );

	        return painelLabels;
	}

	private Group createVerticalGroup(GroupLayout layout, Group ... columns) {
		// TODO Auto-generated method stub
		SequentialGroup g = layout.createSequentialGroup();
		for (Group c: columns) {
			g.addGroup(c).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED);
		}
		return g;
	}

	private Group createHorizontalGroup(GroupLayout layout, int gap, Group ... columns) {
		// TODO Auto-generated method stub
		Group g = layout.createSequentialGroup().addContainerGap();
		for (Group c: columns) {
			g.addGroup(c).addGap(gap);
		}
		return g;
	}

	private Group groupComponents(GroupLayout layout, Component ... components) {
		// TODO Auto-generated method stub
		Group g = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		for (Component c: components) {
			g.addComponent(c);
		}
		return g;
	}

}
