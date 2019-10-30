package gui;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import res.colors.Colors;
import javax.swing.SwingConstants;

public class PanelInicial extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableLicencias;

	public PanelInicial() {
		super();
		initialize();
	}
	
	private void initialize() {
		this.setBounds(150, 60, 844, 605);
		this.setBackground(Colors.FONDO);
		this.setLayout(null);
		
		JLabel lblTituloPrincipal = new JLabel("Licencias");
		lblTituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTituloPrincipal.setBounds(15, 15, 100, 25);
		this.add(lblTituloPrincipal);
		
		JCheckBox ckbxVencidas = new JCheckBox(" Vencidas");
		ckbxVencidas.setFocusable(false);
		ckbxVencidas.setHorizontalAlignment(SwingConstants.TRAILING);
		ckbxVencidas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ckbxVencidas.setBackground(Colors.FONDO);
		ckbxVencidas.setBounds(729, 16, 103, 25);
		this.add(ckbxVencidas);
		
		tableLicencias = new JTable();
		tableLicencias.setBounds(15, 55, 817, 537);
		this.add(tableLicencias);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFiltrarPor.setBounds(621, 15, 100, 25);
		add(lblFiltrarPor);
	}
	
	public void reset() {
		this.removeAll();
		this.initialize();
	}
}
