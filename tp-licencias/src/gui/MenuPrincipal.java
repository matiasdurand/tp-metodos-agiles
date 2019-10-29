package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import res.colors.Colors;
//TODO descomentar imports
/*import domain.TypeId;
*/
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MenuPrincipal extends JFrame{
	
	//Constantes que identifican a cada JPanel
	private final int PANEL_MENU_PRINCIPAL=0;
	private final int PANEL_MENU_LICENCIA=1;
	private final int PANEL_TITULAR_LICENCIA=2;
	private final int PANEL_MENU_USUARIO=3;
	private final int PANEL_ALTA_TITULAR=4;
	private final int PANEL_EMITIR=5;
	
	private JFrame frmPrincipal;
	private JTable tableLicencias;
	private JPanel barraLateral;
	private JPanel panelTitulo;
	private JPanel panelMenuLicencia;
	private JPanel panelMenuPrincipal;
	private JPanel panelMenuUsuario;
	private JPanel panelTitularLicencia;
	private JPanel panelEmitirLicencia;
	private JLabel lblNombreUsuario;
	//TODO completar tipo de dato de comboboxs
	private JComboBox cbTipoSangre;
	private JComboBox cbTipoDoc;
	private JComboBox cbClase;
	private JTextField tfNroDoc;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfFechaNac;
	private JTextField tfDireccion;
	private String user=null;
	private JFrame frmLogin;
	private JButton btnBuscar;
	private JCheckBox ckbDonante;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private boolean altaTitular=false; //La utilizamos para diferenciar si da de alta un titular o ya existe
	private JTextField tfVigencia;
	private JTextField tfCosto;
	private JTextArea taObservaciones;
	
	private TitularDTO titularDTO;
	private TaxPayerDTO contribuyenteDTO;
	
	//TODO descomentar controladores
	/*private TitularController controladorTitular;
	private TaxPayerController controladorContribuyente;
	private LicenceController controladorLicencia;
	*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal(String user, JFrame frmLogin) {
		this.user=user;
		this.frmLogin=frmLogin;
		main(null);
	}
	
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Sistema de Gesti\u00F3n de Licencias");
		frmPrincipal.setResizable(false);
		frmPrincipal.setBounds(100, 100, 1000, 700);
		frmPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.getContentPane().setLayout(null);
		
		//Creamos las distintas interfaces
		armarBarraLateral();
		armarPanelTitulo();
		armarPanelBuscarTitular();
		armarPanelEmitirLicencia();
		armarPanelMenuUsuario();
		armarPanelMenuLicencia();
		armarPanelMenuPrincipal();
		
		//Inicializamos la vista en panelPrincipal
		mostrarPanel(PANEL_MENU_PRINCIPAL);
	}
	
	//Crea la interfaz y elementos del JPanel BarraLateral
	private void armarBarraLateral() {
		barraLateral = new JPanel();
		barraLateral.setBackground(new Color(0, 0, 128));
		barraLateral.setBounds(0, 0, 150, 665);
		frmPrincipal.getContentPane().add(barraLateral);
		barraLateral.setLayout(null);
		
		JButton btnLicencia = new JButton("LICENCIA");
		btnLicencia.setFocusPainted(false);
		btnLicencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(panelMenuPrincipal.isVisible()) {
					btnLicencia.setFont(new Font("Tahoma", Font.BOLD, 18));
					mostrarPanel(PANEL_MENU_LICENCIA);
				}
				else {
					mostrarPanel(PANEL_MENU_PRINCIPAL);
					btnLicencia.setFont(new Font("Tahoma", Font.PLAIN, 17));
				}
			}
		});
		btnLicencia.setForeground(Colors.MENU_LATERAL);
		btnLicencia.setBackground(Colors.FONDO);
		btnLicencia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLicencia.setBounds(0, 129, 150, 40);
		barraLateral.add(btnLicencia);
		
		JButton btnUsuario = new JButton("USUARIO");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(panelMenuUsuario.isVisible()) {
					btnUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
					mostrarPanel(PANEL_MENU_USUARIO);
				}
				else {
					btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
					mostrarPanel(PANEL_MENU_PRINCIPAL);
				}
			}
		});
		btnUsuario.setForeground(Colors.MENU_LATERAL);
		btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUsuario.setBackground(Colors.FONDO);
		btnUsuario.setBounds(0, 169, 150, 40);
		barraLateral.add(btnUsuario);
		
		JButton btnCerrarSesion = new JButton("CERRAR SESI\u00D3N");
		btnCerrarSesion.setMargin(new Insets(2, 6, 2, 6));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					//TODO Mostrar frmLogin
					//frmLogin.setVisible(true);
					frmPrincipal.dispose();
				};
			}
		});
		btnCerrarSesion.setBackground(Colors.FONDO);
		btnCerrarSesion.setForeground(Colors.MENU_LATERAL);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCerrarSesion.setBounds(0, 625, 150, 40);
		barraLateral.add(btnCerrarSesion);
		
		JLabel lblUser = new JLabel("");
		lblUser.setForeground(Color.WHITE);
		lblUser.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/user_male_circle_60px.png")));
		lblUser.setBounds(44, 10, 60, 60);
		barraLateral.add(lblUser);
		
		lblNombreUsuario = new JLabel("nombreUsuario", SwingConstants.CENTER);
		lblNombreUsuario.setForeground(Colors.FUENTE_MENU);
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNombreUsuario.setBounds(10, 78, 130, 16);
		barraLateral.add(lblNombreUsuario);
	}
	
	//Carga los datos del titular en la interfaz
	//TODO descomentar metodos
	/*private void cargarDatosTitular(TitularDTO dto) {
		tfNombre.setText(dto.getName());
		tfApellido.setText(dto.getSurname());
		tfDireccion.setText(dto.getAddress());
		tfFechaNac.setText(dto.getBirthday());
		cbTipoSangre.setSelectedItem(dto.getBloodType());
		ckbDonante.setSelected(dto.getOrganDonnor());
	}
	
	private void cargarDatosContribuyente(TaxPayerDTO dto){
		tfNombre.setText(dto.getName());
		tfApellido.setText(dto.getSurname());
		tfDireccion.setText(dto.getAddress());
		tfFechaNac.setText(dto.getBirthday());
	}
	*/
	
	//Crea la interfaz y elementos del JPanel TituloPrincipal
	private void armarPanelTitulo() {
		panelTitulo = new JPanel();
		panelTitulo.setBounds(150, 0, 844, 60);
		panelTitulo.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sistema de Gesti\u00F3n de Licencias");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(172, 10, 500, 39);
		lblTitulo.setBackground(Colors.FUENTE_NORMAL);
		panelTitulo.add(lblTitulo);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
	}
	
	//Crea la interfaz y elementos del JPanel PanelEmitir
	private void armarPanelBuscarTitular() {
		panelTitularLicencia = new JPanel();
		panelTitularLicencia.setBounds(150, 60, 844, 605);
		frmPrincipal.getContentPane().add(panelTitularLicencia);
		panelTitularLicencia.setLayout(null);
		
		JLabel lblTituloBuscarTitular = new JLabel("Buscar titular");
		lblTituloBuscarTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloBuscarTitular.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTituloBuscarTitular.setBounds(12, 15, 817, 25);
		panelTitularLicencia.add(lblTituloBuscarTitular);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDoc(tfNroDoc.getText())) {
					btnAceptar.setEnabled(true);
					//TODO obtenerDatos del titular
					/*titularDTO = controladorTitular.titularLocator(cbTipo.getSelectedItem(), Long.valueOf(tfNroDoc.getText()));
					if(titularDTO.isNull()) {
						
						//Como el titular no existe, consultamos si desea darlo de alta
						if(JOptionPane.showConfirmDialog(null, "No se encontraron resultados, ¿Desea dar de alta al titular?", "Titular no encontrado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)
							== JOptionPane.YES_OPTION)
						{
							contribuyenteDTO = controladorContribuyente.taxPayerLocator(cbTipo.getSelectedItem(), long.valueOf(tfNroDoc.getText()));
							if(contribuyenteDTO.isNull()){
								JOptionPane.showMessageDialog(null, "El documento ingresado no se corresponde a ningún contribuyente registrado", "Contribuyente no encontrado", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								altaTitular=true;
								cargarDatosContribuyente(contribuyenteDTO);
								mostrarPanel(PANEL_ALTA_TITULAR);
							}
						}
					}
					else{
						altaTitular=false;
						cargarDatosTitular(titularDTO);
						mostrarPanel(PANEL_EMITIR);
					}*/
				}
				else {
					JOptionPane.showMessageDialog(null, "Formato de documento incorrecto", "Error", JOptionPane.OK_OPTION);
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBuscar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/find_user_filled_30px.png")));
		btnBuscar.setBounds(572, 111, 130, 40);
		panelTitularLicencia.add(btnBuscar);
		
		JLabel lblTipoDoc = new JLabel("Tipo:");
		lblTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDoc.setBounds(15, 109, 53, 40);
		panelTitularLicencia.add(lblTipoDoc);
		
		cbTipoDoc = new JComboBox<>();
		//TODO descomentar cargador combobox y setear tipo de dato en constructor
		/*controladorTitular.loadPersonalIdComboBox(cbTipoDoc);
		seleccionar por defecto DNI*/
		cbTipoDoc.setBounds(132, 109, 80, 40);
		panelTitularLicencia.add(cbTipoDoc);
		
		JLabel lblNumero = new JLabel("Nro:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumero.setBounds(224, 109, 74, 40);
		panelTitularLicencia.add(lblNumero);
		
		JLabel lblDocumento = new JLabel("Ingrese el documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDocumento.setBounds(12, 56, 436, 40);
		panelTitularLicencia.add(lblDocumento);
		
		tfNroDoc = new JTextField();
		tfNroDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		panelTitularLicencia.add(tfNroDoc);
		tfNroDoc.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre/s:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(15, 162, 119, 40);
		panelTitularLicencia.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido/s:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellido.setBounds(463, 162, 119, 40);
		panelTitularLicencia.add(lblApellido);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNombre.setColumns(10);
		tfNombre.setBounds(132, 162, 319, 40);
		panelTitularLicencia.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfApellido.setEditable(false);
		tfApellido.setColumns(10);
		tfApellido.setBounds(572, 164, 260, 40);
		panelTitularLicencia.add(tfApellido);
		
		JLabel lblFechaNac = new JLabel("Fecha nac.:");
		lblFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFechaNac.setBounds(15, 215, 119, 40);
		panelTitularLicencia.add(lblFechaNac);
		tfFechaNac = new JTextField();
		tfFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfFechaNac.setEditable(false);
		tfFechaNac.setColumns(10);
		tfFechaNac.setBounds(132, 215, 80, 40);
		panelTitularLicencia.add(tfFechaNac);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDireccion.setBounds(15, 268, 119, 40);
		panelTitularLicencia.add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(132, 268, 700, 40);
		panelTitularLicencia.add(tfDireccion);
		
		JLabel lblGrupoSanguineo = new JLabel("Tipo de sangre:");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGrupoSanguineo.setBounds(463, 215, 170, 40);
		panelTitularLicencia.add(lblGrupoSanguineo);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(altaTitular) {
					titularDTO = completarTitularDTO(contribuyenteDTO);
				}
				mostrarPanel(PANEL_EMITIR);
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAceptar.setBounds(463, 321, 175, 40);
		
				panelTitularLicencia.add(btnAceptar);
				
				btnCancelar = new JButton("CANCELAR");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						altaTitular=false;
						limpiar(panelTitularLicencia);
						mostrarPanel(PANEL_MENU_LICENCIA);
					}
				});
				btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnCancelar.setBounds(657, 321, 175, 40);
				panelTitularLicencia.add(btnCancelar);
				
				cbTipoSangre = new JComboBox<>();
				//TODO descomentar carga combobox y setear tipo de dato en constructor
				/*controladorTitular.loadBloodTyperComboBox(cbTipoSangre);
				*/
				cbTipoSangre.setEnabled(false);
				cbTipoSangre.setBounds(607, 215, 80, 40);
				panelTitularLicencia.add(cbTipoSangre);
				
				ckbDonante = new JCheckBox(" Donante");
				ckbDonante.setEnabled(true);
				ckbDonante.setFont(new Font("Tahoma", Font.PLAIN, 20));
				ckbDonante.setBounds(703, 215, 129, 40);
				panelTitularLicencia.add(ckbDonante);
	}
	
	private TitularDTO completarTitularDTO(TaxPayerDTO dto) {
		TitularDTO aux = new TitularDTO();
		aux.setId(dto.getId())
			.setTypeId(dto.getTypeId())
			.setPersonalId(dto.getPersonalId())
			.setName(dto.getName())
			.setSurname(dto.getSurname())
			.setAdress(dto.getAdress())
			.setBirthday(dto.getBirthday())
			.setBloodType(cbTipoSangre.getSelectedItem())
			.setOrganDonnor(ckbDonante.isSelected());
		return aux;
	}
	
	private void armarPanelEmitirLicencia() {
		panelEmitirLicencia = new JPanel();
		panelEmitirLicencia.setVisible(false);
		panelEmitirLicencia.setBounds(0, 321, 844, 284);
		panelTitularLicencia.add(panelEmitirLicencia);
		panelEmitirLicencia.setLayout(null);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(15, 0, 150, 40);
		panelEmitirLicencia.add(lblObservaciones);
		lblObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		taObservaciones = new JTextArea();
		taObservaciones.setBounds(15, 53, 433, 146);
		panelEmitirLicencia.add(taObservaciones);
		taObservaciones.setLineWrap(true);
		taObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblClase = new JLabel("Clase:");
		lblClase.setBounds(460, 53, 119, 40);
		panelEmitirLicencia.add(lblClase);
		lblClase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		cbClase = new JComboBox<>();
		Date vigencia;
		cbClase.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				tfCosto.setText(" $"+String.valueOf(controladorLicencia.calculateLicenceCost(cbClase.getSelectedItem()))+" .");
				vigencia = controladorLicencia.calculateValidity(cbClase.getSelectedItem());
				tfVigencia.setText(vigencia.toString()+" año/s."));
			}
		});
		//TODO descomentar combobox clase
		/*
		controladorLicencia.loadLicenceTypeComboBox(cbClase, titularDTO, altaTitular);
		*/
		cbClase.setBounds(572, 53, 80, 40);
		panelEmitirLicencia.add(cbClase);
		
		JLabel lblVigencia = new JLabel("Vigencia:");
		lblVigencia.setBounds(460, 106, 119, 40);
		panelEmitirLicencia.add(lblVigencia);
		lblVigencia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		tfVigencia = new JTextField();
		tfVigencia.setBounds(572, 105, 260, 40);
		panelEmitirLicencia.add(tfVigencia);
		tfVigencia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfVigencia.setEditable(false);
		tfVigencia.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(460, 159, 119, 40);
		panelEmitirLicencia.add(lblCosto);
		lblCosto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		tfCosto = new JTextField();
		tfCosto.setBounds(572, 159, 260, 40);
		panelEmitirLicencia.add(tfCosto);
		tfCosto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfCosto.setEditable(false);
		tfCosto.setColumns(10);
		
		JButton btnAceptarEmitir = new JButton("ACEPTAR");
		btnAceptarEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(altaTitular) {
					controladorTitular.registerTitular(titularDTO, cbClase.getSelectedItem());
				}
				LicenciaDTO licenciaDTO = new LicenciaDTO();
				licenciaDTO.setLicenceType(cbClase.getSelectedItem())
					.setObservation(taObservaciones)
					.setExpiricyDate(vigencia);
				controladorLicencia.registerLicence(titularDTO, licenciaDTO, altaTitular);
				//TODO hacer un popup mostrado la informacion de la licencia
				configurarPanelEmitir(false);
			}
		});
		btnAceptarEmitir.setBounds(463, 231, 175, 40);
		panelEmitirLicencia.add(btnAceptarEmitir);
		btnAceptarEmitir.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnCancelarEmitir = new JButton("CANCELAR");
		btnCancelarEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					limpiar(panelTitularLicencia);
					limpiar(panelEmitirLicencia);
					mostrarPanel(PANEL_TITULAR_LICENCIA);
					altaTitular=false;
				}
			}
		});
		btnCancelarEmitir.setBounds(657, 231, 175, 40);
		panelEmitirLicencia.add(btnCancelarEmitir);
		btnCancelarEmitir.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
	
	//Crea la interfaz y elementos del JPanel PanelPrincipal
	private void armarPanelMenuPrincipal() {
		panelMenuPrincipal = new JPanel();
		panelMenuPrincipal.setBounds(150, 60, 844, 605);
		panelMenuPrincipal.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelMenuPrincipal);
		panelMenuPrincipal.setLayout(null);
		
		JLabel lblTituloPrincipal = new JLabel("Licencias");
		lblTituloPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTituloPrincipal.setBounds(15, 15, 817, 25);
		panelMenuPrincipal.add(lblTituloPrincipal);
		
		JCheckBox ckbxVencidas = new JCheckBox("Mostrar solo vencidas");
		ckbxVencidas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ckbxVencidas.setBackground(Colors.FONDO);
		ckbxVencidas.setBounds(633, 15, 199, 25);
		panelMenuPrincipal.add(ckbxVencidas);
		
		tableLicencias = new JTable();
		tableLicencias.setBounds(15, 55, 817, 537);
		panelMenuPrincipal.add(tableLicencias);
	}
	
	//Crea la interfaz y elementos del JPanel PanelLicencia
	private void armarPanelMenuLicencia() {
		panelMenuLicencia = new JPanel();
		panelMenuLicencia.setBounds(150, 60, 844, 605);
		panelMenuLicencia.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelMenuLicencia);
		panelMenuLicencia.setVisible(false);
		panelMenuLicencia.setLayout(null);
		
		JButton btnEmitir = new JButton("");
		btnEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(PANEL_TITULAR_LICENCIA);
			}
		});
		btnEmitir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/emitir_licencia_100px.png")));
		btnEmitir.setBounds(132, 151, 105, 105);
		panelMenuLicencia.add(btnEmitir);
		
		JButton btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImprimir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/imprimir_100px.png")));
		btnImprimir.setBounds(606, 151, 105, 105);
		panelMenuLicencia.add(btnImprimir);
		
		JLabel lblEmitir = new JLabel("EMITIR");
		lblEmitir.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmitir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmitir.setBounds(132, 260, 105, 16);
		panelMenuLicencia.add(lblEmitir);
		
		JLabel lblImprimir = new JLabel("IMPRIMIR");
		lblImprimir.setHorizontalAlignment(SwingConstants.CENTER);
		lblImprimir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImprimir.setBounds(606, 260, 105, 16);
		panelMenuLicencia.add(lblImprimir);
		
		JButton btnRenovar = new JButton("");
		btnRenovar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/renovar_licencia_100px.png")));
		btnRenovar.setBounds(369, 151, 105, 105);
		panelMenuLicencia.add(btnRenovar);
		
		JLabel lblRenovar = new JLabel("RENOVAR");
		lblRenovar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRenovar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRenovar.setBounds(369, 260, 105, 16);
		panelMenuLicencia.add(lblRenovar);
	}
	
	//Crea la interfaz y elementos del JPanel PanelUsuario
	private void armarPanelMenuUsuario() {
		panelMenuUsuario = new JPanel();
		panelMenuUsuario.setBounds(150, 60, 844, 605);
		panelMenuUsuario.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelMenuUsuario);
		panelMenuUsuario.setLayout(null);
	}
	
	//Muestra el panel solicitado ocultando los demas
	private void mostrarPanel(int panel) {
		panelMenuPrincipal.setVisible(false);
		panelTitularLicencia.setVisible(false);
		panelMenuLicencia.setVisible(false);
		panelMenuUsuario.setVisible(false);
		panelEmitirLicencia.setVisible(false);
		
		switch(panel) {
			case PANEL_MENU_PRINCIPAL:{
				panelMenuPrincipal.setVisible(true);
				break;
			}
			case PANEL_MENU_LICENCIA:{
				panelMenuLicencia.setVisible(true);
				break;
			}
			case PANEL_TITULAR_LICENCIA:{
				activarComponentesPanelTitular(Boolean.FALSE);
				btnAceptar.setVisible(true);
				btnAceptar.setEnabled(false);
				btnCancelar.setVisible(true);
				panelTitularLicencia.setVisible(true);
				break;
			}
			case PANEL_MENU_USUARIO:{
				panelMenuUsuario.setVisible(true);
				break;
			}
			case PANEL_ALTA_TITULAR:{
				activarComponentesPanelTitular(Boolean.TRUE);
				panelTitularLicencia.setVisible(true);
				break;
			}
			case PANEL_EMITIR:{
				panelTitularLicencia.setVisible(true);
				configurarPanelEmitir(true);
				break;
			}
		}
	}
	
	//Activa o desactiva los elementos de la interfaz panel emitir para dar de alta a un titular o emitir una licencia
	private void activarComponentesPanelTitular(boolean activar) {
		btnBuscar.setVisible(!activar);
		cbTipoDoc.setEnabled(!activar);
		tfNroDoc.setEditable(!activar);
		cbTipoSangre.setEnabled(activar);
		ckbDonante.setEnabled(activar);
	}
	
	//Configura la interfaz bloqueando los elementos de buscar titular y habilitando los de emitir
	private void configurarPanelEmitir(boolean activar) {
		btnBuscar.setVisible(!activar);
		cbTipoDoc.setEnabled(!activar);
		tfNroDoc.setEditable(!activar);
		cbTipoSangre.setEnabled(!activar);
		ckbDonante.setEnabled(!activar);
		btnAceptar.setVisible(!activar);
		btnCancelar.setVisible(!activar);
		panelEmitirLicencia.setVisible(activar);
	}
	
	//Le enviamos un JPanel y limpiar los TextFields
	private void limpiar(Component component) {
        if (component instanceof JTextField) {
                JTextField text = (JTextField) component;
                text.setText("");
        } else {
                if(component instanceof JTextArea) {
                	((JTextArea) component).setText("");
                }
        		if (component instanceof Container) {
                        for (Component c : ((Container) component).getComponents()) {
                                limpiar(c);
                        }
                }
        }
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
}
