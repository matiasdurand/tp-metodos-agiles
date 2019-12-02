package gui;

import javax.swing.JPanel;

import res.colors.Colors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

import controllers.LicenseController;
import controllers.PanelController;
import controllers.TitularController;

import javax.swing.JComboBox;

import domain.LicenseType;
import domain.TypeId;
import dto.LicenseDTO;
import dto.TitularDTO;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;

public class PanelRenovar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static  String _DESTINO = System.getProperty("user.home") + "\\Desktop\\" + "licencia.pdf";
	
	private JComboBox<String> cmbTipoSangre;
	private JComboBox<TypeId> cmbTipoDoc;
	private JComboBox<LicenseType> cmbClase;
	private JTextField tfNroDoc;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfFechaNac;
	private JTextField tfDireccion;
	private JCheckBox ckbDonante;
	private JTable tLicencias;
	private JPanel panelTablaLicencias;
	private JPanel panelRenovarLicencia;
	private JTextField tfVigencia;
	private JTextField tfCosto;
	private JTextArea taObservaciones;
	private JButton btnRenovar;
	private JButton btnAceptar;
	
	private TitularDTO titularDTO = new TitularDTO();
	private LicenseController controladorLicencia = LicenseController.getInstance();
	private TitularController controladorTitular = TitularController.getInstance();
	
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
	
	public PanelRenovar() {
		super();
		initialize();
		inicializarPanelRenovarLicencia();
		inicializarPanelTablaLicencias();
	}

	private void initialize() {
		this.setBounds(150, 60, 844, 605);
		this.setBackground(Colors.FONDO);
		this.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Renovar licencia");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(12, 15, 820, 25);
		add(lblTitulo);
		
		JLabel label_1 = new JLabel("Ingrese el documento:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(12, 53, 436, 40);
		add(label_1);
		
		JLabel label_2 = new JLabel("Tipo:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(15, 106, 53, 40);
		add(label_2);
		
		cmbTipoDoc = new JComboBox<TypeId>();
		cmbTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbTipoDoc.setFocusable(false);
		cmbTipoDoc.setBounds(132, 106, 80, 40);
		add(cmbTipoDoc);
		
		tfNroDoc = new JTextField();
		tfNroDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNroDoc.setDisabledTextColor(Color.GRAY);
		tfNroDoc.setColumns(10);
		tfNroDoc.setBounds(274, 106, 177, 40);
		tfNroDoc.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				else {
					//Ingrese solo numeros sin puntos
				}
			}
		});
		add(tfNroDoc);
		
		JLabel label_3 = new JLabel("Nro:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(224, 106, 74, 40);
		add(label_3);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon(PanelRenovar.class.getResource("/res/images/find_user_filled_30px.png")));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBuscar.setFocusable(false);
		btnBuscar.setBounds(572, 108, 175, 40);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDoc(tfNroDoc.getText())) {
					titularDTO = controladorTitular.titularLocator((TypeId) cmbTipoDoc.getSelectedItem(), Long.valueOf(tfNroDoc.getText()));
					if(titularDTO == null)
						JOptionPane.showMessageDialog(null, "No se encontraron resultados, vuelva a intentarlo");
					else{
						cargarDatosTitular(titularDTO);
						btnRenovar.setEnabled(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Formato de documento incorrecto", "Error", JOptionPane.OK_OPTION);
				}
			}
		});
		add(btnBuscar);
		
		JLabel label_4 = new JLabel("Nombre/s:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4.setBounds(15, 159, 119, 40);
		add(label_4);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNombre.setEnabled(false);
		tfNombre.setEditable(false);
		tfNombre.setDisabledTextColor(Color.GRAY);
		tfNombre.setColumns(10);
		tfNombre.setBounds(132, 159, 319, 40);
		add(tfNombre);
		
		JLabel label_5 = new JLabel("Apellido/s:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(463, 161, 119, 40);
		add(label_5);
		
		tfApellido = new JTextField();
		tfApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfApellido.setEnabled(false);
		tfApellido.setEditable(false);
		tfApellido.setDisabledTextColor(Color.GRAY);
		tfApellido.setColumns(10);
		tfApellido.setBounds(572, 160, 260, 40);
		add(tfApellido);
		
		JLabel label_6 = new JLabel("Fecha nac.:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_6.setBounds(15, 212, 119, 40);
		add(label_6);
		
		tfFechaNac = new JTextField();
		tfFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfFechaNac.setEnabled(false);
		tfFechaNac.setEditable(false);
		tfFechaNac.setDisabledTextColor(Color.GRAY);
		tfFechaNac.setColumns(10);
		tfFechaNac.setBounds(132, 212, 319, 40);
		add(tfFechaNac);
		
		JLabel label_7 = new JLabel("Tipo de sangre:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_7.setBounds(463, 212, 170, 40);
		add(label_7);
		
		cmbTipoSangre = new JComboBox<String>();
		cmbTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbTipoSangre.setFocusable(false);
		cmbTipoSangre.setEnabled(false);
		cmbTipoSangre.setBounds(607, 212, 80, 40);
		add(cmbTipoSangre);
		
		ckbDonante = new JCheckBox(" Donante");
		ckbDonante.setSelected(true);
		ckbDonante.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ckbDonante.setFocusable(false);
		ckbDonante.setEnabled(false);
		ckbDonante.setBackground(new Color(232, 234, 246));
		ckbDonante.setBounds(703, 212, 129, 40);
		add(ckbDonante);
		
		JLabel label_8 = new JLabel("Direcci\u00F3n:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_8.setBounds(15, 265, 119, 40);
		add(label_8);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfDireccion.setEnabled(false);
		tfDireccion.setEditable(false);
		tfDireccion.setDisabledTextColor(Color.GRAY);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(132, 265, 700, 40);
		add(tfDireccion);
	}
	
	private void inicializarPanelTablaLicencias() {
		panelTablaLicencias = new JPanel();
		panelTablaLicencias.setBounds(0, 321, 844, 284);
		panelTablaLicencias.setBackground(Colors.FONDO);
		add(panelTablaLicencias);
		panelTablaLicencias.setLayout(null);
		
		JButton btnRenovar = new JButton("RENOVAR");
		btnRenovar.setBounds(451, 234, 175, 40);
		panelTablaLicencias.add(btnRenovar);
		btnRenovar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRenovar.setFocusable(false);
		btnRenovar.setEnabled(false);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(645, 234, 175, 40);
		panelTablaLicencias.add(btnCancelar);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setFocusable(false);
		
		tLicencias = new JTable();
		tLicencias.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tLicencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tLicencias.setBounds(0, 0, 820, 218);
		panelTablaLicencias.add(tLicencias);
	}
	
	private void inicializarPanelRenovarLicencia() {
		panelRenovarLicencia = new JPanel();
		panelRenovarLicencia.setBounds(0, 321, 844, 284);
		panelRenovarLicencia.setBackground(Colors.FONDO);
		add(panelRenovarLicencia);
		panelRenovarLicencia.setLayout(null);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(15, 0, 150, 40);
		panelRenovarLicencia.add(lblObservaciones);
		lblObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		taObservaciones = new JTextArea();
		taObservaciones.setBounds(15, 53, 433, 146);
		taObservaciones.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), 
	            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		panelRenovarLicencia.add(taObservaciones);
		taObservaciones.setLineWrap(true);
		taObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		tfCosto = new JTextField();
		tfCosto.setBounds(572, 159, 260, 40);
		panelRenovarLicencia.add(tfCosto);
		tfCosto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfCosto.setEditable(false);
		tfCosto.setColumns(10);
		
		JLabel lblClase = new JLabel("Clase:");
		lblClase.setBounds(460, 53, 119, 40);
		panelRenovarLicencia.add(lblClase);
		lblClase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		cmbClase = new JComboBox<>();
		cmbClase.setEnabled(false);
		Date expiryDate = controladorLicencia.calculateExpiryDate(titularDTO);
		cmbClase.setBounds(572, 53, 80, 40);
		cmbClase.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelRenovarLicencia.add(cmbClase);
		
		JLabel lblVigencia = new JLabel("Vigencia:");
		lblVigencia.setBounds(460, 106, 119, 40);
		panelRenovarLicencia.add(lblVigencia);
		lblVigencia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		tfVigencia = new JTextField();
		tfVigencia.setBounds(572, 105, 260, 40);
		panelRenovarLicencia.add(tfVigencia);
		tfVigencia.setText(formatoFecha.format(expiryDate));
		tfVigencia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfVigencia.setEditable(false);
		tfVigencia.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(460, 159, 119, 40);
		panelRenovarLicencia.add(lblCosto);
		lblCosto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFocusable(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LicenseDTO licenciaDTO = new LicenseDTO();
				licenciaDTO.setLicenseType((LicenseType) cmbClase.getSelectedItem());
				licenciaDTO.setObservation(taObservaciones.getText());
				licenciaDTO.setEmisionDate(new Date());
				licenciaDTO.setExpiryDate(expiryDate);
				//TODO solicitar al controlador de licencia una renovacion
							
				if(JOptionPane.showConfirmDialog(null, "Licencia renovada exitosamente, ¿Desea imprimirla ahora?", "Éxito", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					try {
						PanelController.getImprimir(titularDTO, licenciaDTO, _DESTINO);
					}catch(Exception ex) {}
				}
				MenuPrincipal.menuPrincipal.cancelar(MenuPrincipal.PANEL_RENOVAR);
			}
		});
		btnAceptar.setBounds(463, 231, 175, 40);
		panelRenovarLicencia.add(btnAceptar);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFocusable(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					panelRenovarLicencia.setVisible(false);
					panelTablaLicencias.setVisible(true);
				}
			}
		});
		btnCancelar.setBounds(657, 231, 175, 40);
		panelRenovarLicencia.add(btnCancelar);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
	
	private void cargarDatosTitular(TitularDTO dto) {
		tfNombre.setText(dto.getName());
		tfApellido.setText(dto.getSurname());
		tfDireccion.setText(dto.getAdress());
		tfFechaNac.setText(formatoFecha.format(dto.getBirthdate()));
		cmbTipoSangre.setSelectedItem(dto.getBloodType());
		ckbDonante.setSelected(dto.getOrganDonor());
	}
	
	//Validamos el numero de doc ingresado
	private boolean validarDoc(String doc) {
        boolean resultado;
        try {
            Integer.parseInt(doc);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
	}
	
	public void reset() {
		this.removeAll();
		this.initialize();
	}
}
