package gui;

import javax.swing.JPanel;

import res.colors.Colors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import controllers.TaxPayerController;
import controllers.TitularController;

import javax.swing.JComboBox;

import domain.TypeId;
import dto.TaxPayerDTO;
import dto.TitularDTO;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class PanelModificarTitular extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TitularController controladorTitular = TitularController.getInstance();
	private TitularDTO titularDTO = new TitularDTO();
	
	private JLabel lblTitulo;
	private JComboBox<String> cmbTipoSangre;
	private JComboBox<TypeId> cmbTipoDoc;
	private JTextField tfNroDoc;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfFechaNac;
	private JTextField tfDireccion;
	private JButton btnBuscar;
	private JCheckBox ckbDonante;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");

	public PanelModificarTitular(int opcion) {
		super();
		initialize();
	}
	
	private void completarTitularDTO() {
		titularDTO.setAdress(tfDireccion.getText());
		titularDTO.setName(tfNombre.getText());
		titularDTO.setOrganDonor(ckbDonante.isSelected());
		titularDTO.setSurname(tfApellido.getText());
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

	private void initialize() {
		this.setBounds(150, 60, 844, 605);
		this.setBackground(Colors.FONDO);
		this.setLayout(null);
		
		lblTitulo = new JLabel("Modificar datos de titular");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(12, 15, 820, 25);
		add(lblTitulo);
		
		JLabel lblDocumento = new JLabel("Ingrese el documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDocumento.setBounds(12, 53, 436, 40);
		add(lblDocumento);
		
		JLabel lblTipoDoc = new JLabel("Tipo:");
		lblTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDoc.setBounds(15, 106, 53, 40);
		add(lblTipoDoc);
		
		cmbTipoDoc = new JComboBox<TypeId>();
		cmbTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbTipoDoc.setFocusable(false);
		cmbTipoDoc.setBounds(132, 106, 80, 40);
		add(cmbTipoDoc);
		
		JLabel lblNroDoc = new JLabel("Nro:");
		lblNroDoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNroDoc.setBounds(224, 106, 74, 40);
		add(lblNroDoc);
		
		tfNroDoc = new JTextField();
		tfNroDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNroDoc.setDisabledTextColor(Color.GRAY);
		tfNroDoc.setColumns(10);
		tfNroDoc.setBounds(274, 106, 177, 40);
		add(tfNroDoc);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon(PanelModificarTitular.class.getResource("/res/images/find_user_filled_30px.png")));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBuscar.setFocusable(false);
		btnBuscar.setBounds(572, 108, 175, 40);
		add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDoc(tfNroDoc.getText())) {
					titularDTO = controladorTitular.titularLocator((TypeId) cmbTipoDoc.getSelectedItem(), Long.valueOf(tfNroDoc.getText()));
					if(titularDTO != null) {
						cargarDatosTitular();
						bloquearComponentes(true);
					}
					else
						JOptionPane.showMessageDialog(null, "No se han encontrado resultados para este documento. Vuelva a intentarlo");
				}
				else
					JOptionPane.showMessageDialog(null, "Formato de documento incorrecto", "Error", JOptionPane.OK_OPTION);
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre/s:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(15, 159, 119, 40);
		add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setEnabled(false);
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNombre.setDisabledTextColor(Color.GRAY);
		tfNombre.setColumns(10);
		tfNombre.setBounds(132, 159, 319, 40);
		add(tfNombre);
		
		JLabel lblApellido = new JLabel("Apellido/s:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellido.setBounds(463, 159, 119, 40);
		add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setEnabled(false);
		tfApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfApellido.setDisabledTextColor(Color.GRAY);
		tfApellido.setColumns(10);
		tfApellido.setBounds(572, 159, 260, 40);
		add(tfApellido);
		
		JLabel lblFechaNac = new JLabel("Fecha nac.:");
		lblFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFechaNac.setBounds(15, 212, 119, 40);
		add(lblFechaNac);
		
		tfFechaNac = new JTextField();
		tfFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfFechaNac.setEnabled(false);
		tfFechaNac.setEditable(false);
		tfFechaNac.setDisabledTextColor(Color.GRAY);
		tfFechaNac.setColumns(10);
		tfFechaNac.setBounds(132, 212, 319, 40);
		add(tfFechaNac);
		
		JLabel lblTipoSangre = new JLabel("Tipo de sangre:");
		lblTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoSangre.setBounds(463, 212, 170, 40);
		add(lblTipoSangre);
		
		cmbTipoSangre = new JComboBox<String>();
		cmbTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbTipoSangre.setFocusable(false);
		cmbTipoSangre.setEnabled(false);
		cmbTipoSangre.setBounds(607, 212, 80, 40);
		add(cmbTipoSangre);
		
		ckbDonante = new JCheckBox(" Donante");
		ckbDonante.setEnabled(false);
		ckbDonante.setFocusPainted(false);
		ckbDonante.setSelected(true);
		ckbDonante.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ckbDonante.setFocusable(false);
		ckbDonante.setBackground(new Color(232, 234, 246));
		ckbDonante.setBounds(703, 212, 129, 40);
		add(ckbDonante);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDireccion.setBounds(15, 265, 119, 40);
		add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setEnabled(false);
		tfDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfDireccion.setDisabledTextColor(Color.GRAY);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(132, 265, 700, 40);
		add(tfDireccion);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAceptar.setFocusable(false);
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(463, 318, 175, 40);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completarTitularDTO();
				if(controladorTitular.validate(titularDTO).size()==0) {
					controladorTitular.modifyTitular(titularDTO, MenuPrincipal.menuPrincipal.usuarioDTO);
					JOptionPane.showMessageDialog(null, "El titular ha sido dado de alta correctamente");
					MenuPrincipal.menuPrincipal.cancelar(MenuPrincipal.PANEL_MODIFICAR_TITULAR);
				}
				else
					JOptionPane.showMessageDialog(null, "La información del titular ingresada no es válida");
			}
		});
		add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setFocusable(false);
		btnCancelar.setBounds(657, 318, 175, 40);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					MenuPrincipal.menuPrincipal.cancelar(MenuPrincipal.PANEL_MODIFICAR_TITULAR);
				}
			}
		});
		add(btnCancelar);
	}
	
	private void bloquearComponentes(boolean bloquear) {
		cmbTipoDoc.setEnabled(!bloquear);
		tfNroDoc.setEnabled(!bloquear);
		btnAceptar.setEnabled(!bloquear);
		btnBuscar.setEnabled(!bloquear);
		tfNombre.setEnabled(bloquear);
		tfApellido.setEnabled(bloquear);
		tfDireccion.setEnabled(bloquear);
		ckbDonante.setEnabled(bloquear);
	}

	private void cargarDatosTitular() {
		tfNombre.setText(titularDTO.getName());
		tfApellido.setText(titularDTO.getSurname());
		tfFechaNac.setText(formatoFecha.format(titularDTO.getBirthdate()));
		tfDireccion.setText(titularDTO.getAdress());
		cmbTipoSangre.setSelectedItem(titularDTO.getBloodType());
		ckbDonante.setSelected(titularDTO.getOrganDonor());
	}

	public void reset() {
		this.removeAll();
		this.initialize();
	}
}
