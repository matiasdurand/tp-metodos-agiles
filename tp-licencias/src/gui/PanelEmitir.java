 package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import res.colors.Colors;

import controllers.TitularController;
import domain.LicenseType;
import domain.TypeId;
import controllers.TaxPayerController;
import controllers.LicenseController;
import controllers.PanelController;
import dto.TitularDTO;
import dto.TaxPayerDTO;
import dto.LicenseDTO;
import java.awt.Color;

public class PanelEmitir extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> cbTipoSangre;
	private JComboBox<TypeId> cbTipoDoc;
	private JComboBox<LicenseType> cbClase;
	private JTextField tfNroDoc;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfFechaNac;
	private JTextField tfDireccion;
	private JButton btnBuscar;
	private JCheckBox ckbDonante;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private Boolean altaTitular; //La utilizamos para diferenciar si da de alta un titular o ya existe
	private JTextField tfVigencia;
	private JTextField tfCosto;
	private JTextArea taObservaciones;
	private JPanel panelEmitirLicencia;
	
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
	private static SimpleDateFormat formatoFechaNombreArchivo = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	
	//Direccion donde se guarda el pdf de la licencia emitida
	private static String _DESTINO = System.getProperty("user.home") + "\\Desktop\\" + "licencia" + formatoFechaNombreArchivo.format(new Date()) + ".pdf";
	
	private TitularDTO titularDTO = new TitularDTO();
	private TaxPayerDTO contribuyenteDTO = new TaxPayerDTO();
	private LicenseController controladorLicencia = LicenseController.getInstance();
	private TitularController controladorTitular = TitularController.getInstance();
	private TaxPayerController controladorContribuyente = TaxPayerController.getInstance();
		
	public PanelEmitir() {
		super();
		initialize();
	}

	private void initialize() {
		altaTitular=false;
		
		this.setBounds(150, 60, 844, 605);
		this.setBackground(Colors.FONDO);
		this.setLayout(null);
		
		JLabel lblTituloBuscarTitular = new JLabel("Emitir licencia");
		lblTituloBuscarTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloBuscarTitular.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTituloBuscarTitular.setBounds(12, 15, 817, 25);
		this.add(lblTituloBuscarTitular);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setFocusable(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDoc(tfNroDoc.getText())) {
					titularDTO = controladorTitular.titularLocator((TypeId) cbTipoDoc.getSelectedItem(), Long.valueOf(tfNroDoc.getText()));
					if(titularDTO == null) {
						//Como el titular no existe, consultamos si desea darlo de alta
						if(JOptionPane.showConfirmDialog(null, "No se encontraron resultados, ¿Desea dar de alta al titular?", "Titular no encontrado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)
							== JOptionPane.YES_OPTION)
						{
							contribuyenteDTO = controladorContribuyente.taxPayerLocator((TypeId) cbTipoDoc.getSelectedItem(), Long.valueOf(tfNroDoc.getText()));
							if(contribuyenteDTO == null){
								JOptionPane.showMessageDialog(null, "El documento ingresado no se corresponde a ningún contribuyente registrado");
							}
							else{
								altaTitular=true;
								bloquearComponentes(altaTitular);
								cargarDatosContribuyente(contribuyenteDTO);
								btnAceptar.setEnabled(true);
							}
						}
					}
					else{
						altaTitular=false;
						bloquearComponentes(altaTitular);
						cargarDatosTitular(titularDTO);
						btnAceptar.setEnabled(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Formato de documento incorrecto", "Error", JOptionPane.OK_OPTION);
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBuscar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/find_user_filled_30px.png")));
		btnBuscar.setBounds(572, 111, 130, 40);
		this.add(btnBuscar);
		
		JLabel lblTipoDoc = new JLabel("Tipo:");
		lblTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDoc.setBounds(15, 109, 53, 40);
		this.add(lblTipoDoc);
		
		cbTipoDoc = new JComboBox<>();
		cbTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbTipoDoc.setFocusable(false);
		controladorTitular.loadTypeIdComboBox(cbTipoDoc);
		cbTipoDoc.setBounds(132, 109, 80, 40);
		this.add(cbTipoDoc);
		
		JLabel lblNumero = new JLabel("Nro:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumero.setBounds(224, 109, 74, 40);
		this.add(lblNumero);
		
		JLabel lblDocumento = new JLabel("Ingrese el documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDocumento.setBounds(12, 56, 436, 40);
		this.add(lblDocumento);
		
		tfNroDoc = new JTextField();
		tfNroDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNroDoc.setDisabledTextColor(Color.GRAY);
		tfNroDoc.setBounds(274, 109, 177, 40);
		
		//Configuramos que solo permita ingresar digitos
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
		this.add(tfNroDoc);
		tfNroDoc.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre/s:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(15, 162, 119, 40);
		this.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido/s:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellido.setBounds(463, 162, 119, 40);
		this.add(lblApellido);
		
		tfNombre = new JTextField();
		tfNombre.setDisabledTextColor(Color.GRAY);
		tfNombre.setEnabled(false);
		tfNombre.setEditable(false);
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNombre.setColumns(10);
		tfNombre.setBounds(132, 162, 319, 40);
		this.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setDisabledTextColor(Color.GRAY);
		tfApellido.setEnabled(false);
		tfApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfApellido.setEditable(false);
		tfApellido.setColumns(10);
		tfApellido.setBounds(572, 164, 260, 40);
		this.add(tfApellido);
		
		JLabel lblFechaNac = new JLabel("Fecha nac.:");
		lblFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFechaNac.setBounds(15, 215, 119, 40);
		this.add(lblFechaNac);
		tfFechaNac = new JTextField();
		tfFechaNac.setDisabledTextColor(Color.GRAY);
		tfFechaNac.setEnabled(false);
		tfFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfFechaNac.setEditable(false);
		tfFechaNac.setColumns(10);
		tfFechaNac.setBounds(132, 215, 319, 40);
		this.add(tfFechaNac);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDireccion.setBounds(15, 268, 119, 40);
		this.add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setDisabledTextColor(Color.GRAY);
		tfDireccion.setEnabled(false);
		tfDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(132, 268, 700, 40);
		this.add(tfDireccion);
		
		JLabel lblGrupoSanguineo = new JLabel("Tipo de sangre:");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGrupoSanguineo.setBounds(463, 215, 170, 40);
		this.add(lblGrupoSanguineo);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFocusable(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(altaTitular) {
					completarTitularDTO(contribuyenteDTO);
				}
				initializeEmitirLicencia();
				panelEmitirLicencia.setVisible(true);
				cbTipoSangre.setEnabled(false);
				ckbDonante.setEnabled(false);
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAceptar.setBounds(463, 321, 175, 40);
		this.add(btnAceptar);
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFocusable(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaTitular=false;
				MenuPrincipal.menuPrincipal.cancelar(MenuPrincipal.PANEL_EMITIR);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setBounds(657, 321, 175, 40);
		this.add(btnCancelar);
		
		cbTipoSangre = new JComboBox<>();
		cbTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbTipoSangre.setFocusable(false);
		controladorTitular.loadBloodTypeComboBox(cbTipoSangre);
		cbTipoSangre.setEnabled(false);
		cbTipoSangre.setBounds(607, 215, 80, 40);
		this.add(cbTipoSangre);
		
		ckbDonante = new JCheckBox(" Donante");
		ckbDonante.setFocusable(false);
		ckbDonante.setEnabled(false);
		ckbDonante.setSelected(true);
		ckbDonante.setBackground(Colors.FONDO);
		ckbDonante.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ckbDonante.setBounds(703, 215, 129, 40);
		this.add(ckbDonante);
	}
	
	private void initializeEmitirLicencia(){
		panelEmitirLicencia = new JPanel();
		panelEmitirLicencia.setVisible(false);
		panelEmitirLicencia.setBounds(0, 321, 844, 284);
		panelEmitirLicencia.setBackground(Colors.FONDO);
		this.add(panelEmitirLicencia);
		panelEmitirLicencia.setLayout(null);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(15, 0, 150, 40);
		panelEmitirLicencia.add(lblObservaciones);
		lblObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		taObservaciones = new JTextArea();
		taObservaciones.setBounds(15, 53, 433, 146);
		taObservaciones.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), 
	    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		panelEmitirLicencia.add(taObservaciones);
		taObservaciones.setLineWrap(true);
		taObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		tfCosto = new JTextField();
		tfCosto.setBounds(572, 159, 260, 40);
		panelEmitirLicencia.add(tfCosto);
		tfCosto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfCosto.setEditable(false);
		tfCosto.setColumns(10);
		
		JLabel lblClase = new JLabel("Clase:");
		lblClase.setBounds(460, 53, 119, 40);
		panelEmitirLicencia.add(lblClase);
		lblClase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		cbClase = new JComboBox<>();
		Date expiryDate = controladorLicencia.calculateExpiryDate(titularDTO);
		cbClase.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				StringBuffer sb = new StringBuffer("$");
				sb.append(String.valueOf(controladorLicencia.calculateLicenseCost((LicenseType)cbClase.getSelectedItem(), expiryDate)));
				tfCosto.setText(sb.toString());
			}
		});
		controladorLicencia.loadLicenseTypeComboBox(cbClase, titularDTO, altaTitular);
		cbClase.setBounds(572, 53, 80, 40);
		cbClase.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelEmitirLicencia.add(cbClase);
		
		JLabel lblVigencia = new JLabel("Vigencia:");
		lblVigencia.setBounds(460, 106, 119, 40);
		panelEmitirLicencia.add(lblVigencia);
		lblVigencia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		tfVigencia = new JTextField();
		tfVigencia.setBounds(572, 105, 260, 40);
		panelEmitirLicencia.add(tfVigencia);
		tfVigencia.setText(formatoFecha.format(expiryDate));
		tfVigencia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfVigencia.setEditable(false);
		tfVigencia.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(460, 159, 119, 40);
		panelEmitirLicencia.add(lblCosto);
		lblCosto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnAceptarEmitir = new JButton("ACEPTAR");
		btnAceptarEmitir.setFocusable(false);
		btnAceptarEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbClase.getSelectedItem()==null)
					JOptionPane.showMessageDialog(null, "El titular ingresado no posee clases de licencia disponibles para emitir");
				else {
					LicenseDTO licenciaDTO = new LicenseDTO();
					licenciaDTO.setLicenseType((LicenseType) cbClase.getSelectedItem());
					licenciaDTO.setObservation(taObservaciones.getText());
					licenciaDTO.setEmisionDate(new Date());
					licenciaDTO.setExpiryDate(expiryDate);
					controladorLicencia.registerLicense(titularDTO, licenciaDTO, MenuPrincipal.menuPrincipal.usuarioDTO, altaTitular);				
					if(JOptionPane.showConfirmDialog(null, "Licencia emitida exitosamente, ¿Desea imprimirla ahora?", "Éxito", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						try {
							(PanelController.getImprimir(titularDTO, licenciaDTO, _DESTINO)).setVisible(true);
						}catch(Exception ex) {System.out.println("No se pudo mostrar el panel imprimir");}
					}
					MenuPrincipal.menuPrincipal.cancelar(MenuPrincipal.PANEL_EMITIR);
				}
			}
		});
		btnAceptarEmitir.setBounds(463, 231, 175, 40);
		panelEmitirLicencia.add(btnAceptarEmitir);
		btnAceptarEmitir.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnCancelarEmitir = new JButton("CANCELAR");
		btnCancelarEmitir.setFocusable(false);
		btnCancelarEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					MenuPrincipal.menuPrincipal.cancelar(MenuPrincipal.PANEL_EMITIR);
				}
			}
		});
		btnCancelarEmitir.setBounds(657, 231, 175, 40);
		panelEmitirLicencia.add(btnCancelarEmitir);
		btnCancelarEmitir.setFont(new Font("Tahoma", Font.BOLD, 20));
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
	
	private void cargarDatosTitular(TitularDTO dto) {
		tfNombre.setText(dto.getName());
		tfApellido.setText(dto.getSurname());
		tfDireccion.setText(dto.getAdress());
		tfFechaNac.setText(formatoFecha.format(dto.getBirthdate()));
		cbTipoSangre.setSelectedItem(dto.getBloodType());
		ckbDonante.setSelected(dto.getOrganDonor());
	}
	
	private void cargarDatosContribuyente(TaxPayerDTO dto){
		tfNombre.setText(dto.getName());
		tfApellido.setText(dto.getSurname());
		tfDireccion.setText(dto.getAdress());
		tfFechaNac.setText(formatoFecha.format(dto.getBirthdate()));
	}
	
	private void bloquearComponentes(Boolean altaTitular) {
		cbTipoDoc.setEnabled(false);
		tfNroDoc.setEnabled(false);
		tfNroDoc.setEditable(false);
		btnBuscar.setEnabled(false);
		btnAceptar.setEnabled(true);
		if(altaTitular) {
			cbTipoSangre.setEnabled(true);
			ckbDonante.setEnabled(true);
		}
	}
	
	private void completarTitularDTO(TaxPayerDTO dto) {
		titularDTO = new TitularDTO();
		titularDTO.setTypeId(dto.getTypeId());
		titularDTO.setPersonalId(dto.getPersonalId().toString());
		titularDTO.setName(dto.getName());
		titularDTO.setSurname(dto.getSurname());
		titularDTO.setAdress(dto.getAdress());
		titularDTO.setBirthdate(dto.getBirthdate());
		titularDTO.setBloodType(cbTipoSangre.getSelectedItem().toString());
		titularDTO.setOrganDonor(ckbDonante.isSelected());
	}

	public void reset() {
		this.removeAll();
		this.initialize();
	}
}
