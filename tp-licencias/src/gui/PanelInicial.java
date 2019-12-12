package gui;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import res.colors.Colors;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controllers.LicenseController;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class PanelInicial extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableLicencias;
	private DefaultTableModel model = new DefaultTableModel();
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
		
		JCheckBox ckbVencidas = new JCheckBox();
		ckbVencidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ckbVencidas.isSelected()){
					model = new DefaultTableModel();
					loadLicensesTableModel();
					controladorLicencia.loadExpiredLicensesTable(tableLicencias, model);
				}
				else {
					model = new DefaultTableModel();
					loadLicensesTableModel();
					controladorLicencia.loadNotExpiredLicensesTable(tableLicencias, model);
				}
					
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
		loadLicensesTableModel();
		controladorLicencia.loadNotExpiredLicensesTable(tableLicencias, model);
		tableLicencias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane();
		scroll.setFocusable(false);
		scroll.setBounds(12, 55, 820, 537);
		scroll.setViewportView(tableLicencias);
		this.add(scroll);
		
		JLabel lblFiltrarPor = new JLabel("Ver licencias expiradas: ");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFiltrarPor.setBounds(621, 15, 100, 25);
		add(lblFiltrarPor);
	}
	
	private void loadLicensesTableModel() {
		ArrayList<Object> columnsName = new ArrayList<Object>();
		
		columnsName.add("Tipo Licencia");
		columnsName.add("Fecha Emision"); 
		columnsName.add("Fecha Expiracion");
		columnsName.add("Observacion");
		columnsName.add("Apellido");
		columnsName.add("Nombre");
		
		for(Object o: columnsName) { //Agrego las columnas al TableModel
			model.addColumn(o);
		}
		
		tableLicencias.setModel(model);
	}
	
	/*public void reset() {
		this.removeAll();
		this.initialize();
	}*/
	
	public void updateLicensesTable() {
		model = new DefaultTableModel();
		loadLicensesTableModel();
		controladorLicencia.loadNotExpiredLicensesTable(tableLicencias, model);
	}
	
	
}
