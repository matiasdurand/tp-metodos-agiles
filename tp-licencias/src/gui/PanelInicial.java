package gui;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import res.colors.Colors;
import javax.swing.SwingConstants;

import controllers.LicenseController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class PanelInicial extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableLicencias;
	private LicenseController controladorLicencia = LicenseController.getInstance();

	public PanelInicial() {
		super();
		initialize();
	}
	
	private void initialize() {
		this.setBounds(150, 60, 844, 605);
		this.setBackground(Colors.FONDO);
		this.setLayout(null);
		
		JLabel lblTituloPrincipal = new JLabel("Listado de licencias");
		lblTituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTituloPrincipal.setBounds(12, 15, 240, 25);
		this.add(lblTituloPrincipal);
		
		JCheckBox ckbVencidas = new JCheckBox(" Vencidas");
		ckbVencidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ckbVencidas.isSelected()){
					controladorLicencia.loadLicensesTable(tableLicencias, ckbVencidas.isSelected());
				}
				else
					controladorLicencia.loadLicensesTable(tableLicencias, ckbVencidas.isSelected());
			}
		});
		ckbVencidas.setFocusable(false);
		ckbVencidas.setHorizontalAlignment(SwingConstants.TRAILING);
		ckbVencidas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ckbVencidas.setBackground(Colors.FONDO);
		ckbVencidas.setBounds(729, 16, 103, 25);
		this.add(ckbVencidas);
		
		tableLicencias = new JTable();
		tableLicencias.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 17));
		tableLicencias.setBounds(new Rectangle(5, 0, 0, 5));
		controladorLicencia.loadLicensesTable(tableLicencias,ckbVencidas.isSelected());
		tableLicencias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane();
		scroll.setFocusable(false);
		scroll.setBounds(12, 55, 820, 537);
		scroll.setViewportView(tableLicencias);
		this.add(scroll);
		
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
