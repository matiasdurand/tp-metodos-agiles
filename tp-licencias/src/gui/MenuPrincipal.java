package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import res.colors.Colors;

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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import java.awt.Insets;

public class MenuPrincipal extends JFrame{
	
	//Constantes que identifican a cada JPanel
	private final int PANEL_PRINCIPAL=0;
	private final int PANEL_LICENCIA=1;
	private final int PANEL_BUSCAR_TITULAR=2;
	private final int PANEL_USUARIO=3;
	private final int PANEL_ALTA_TITULAR=4;
	private final int PANEL_EMITIR=5;
	
	private JFrame frmPrincipal;
	private JTable tableLicencias;
	private JPanel barraLateral;
	private JPanel panelTitulo;
	private JPanel panelLicencia;
	private JPanel panelPrincipal;
	private JPanel panelUsuario;
	private JPanel panelBuscarTitular;
	private JPanel panelEmitir;
	private JLabel lblNombreUsuario;
	private JComboBox cbTipoDoc;
	private JTextField tfNro;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JFormattedTextField tfFechaNac;
	private JTextField tfDireccion;
	private String user=null;
	private JFrame frmLogin;
	private JButton btnBuscar;
	private JComboBox cbTipoSangre;
	private JCheckBox ckbDonante;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private boolean altaTitular=false; //La utilizamos para diferenciar si da de alta un titular o ya existe
	private JTextField tfVigencia;
	private JTextField tfCosto;

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
		armarPanelUsuario();
		armarPanelLicencia();
		armarPanelPrincipal();
		
		//Inicializamos la vista en panelPrincipal
		mostrarPanel(PANEL_PRINCIPAL);
	}
	
	//Crea la interfaz y elementos del JPanel BarraLateral
	private void armarBarraLateral() {
		barraLateral = new JPanel();
		barraLateral.setBackground(new Color(0, 0, 128));
		barraLateral.setBounds(0, 0, 150, 665);
		frmPrincipal.getContentPane().add(barraLateral);
		barraLateral.setLayout(null);
		
		JButton btnLicencia = new JButton("LICENCIA");
		btnLicencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(panelPrincipal.isVisible()) {
					mostrarPanel(PANEL_LICENCIA);
				}
				else {
					mostrarPanel(PANEL_PRINCIPAL);
				}
			}
		});
		btnLicencia.setForeground(Colors.MENU_LATERAL);
		btnLicencia.setBackground(Colors.FONDO);
		btnLicencia.setFont(new Font("Google Sans", Font.PLAIN, 18));
		btnLicencia.setBounds(0, 129, 150, 40);
		barraLateral.add(btnLicencia);
		
		JButton btnUsuario = new JButton("USUARIO");
		btnUsuario.setForeground(Colors.MENU_LATERAL);
		btnUsuario.setFont(new Font("Google Sans", Font.PLAIN, 18));
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
		btnCerrarSesion.setFont(new Font("Google Sans", Font.PLAIN, 18));
		btnCerrarSesion.setBounds(0, 625, 150, 40);
		barraLateral.add(btnCerrarSesion);
		
		JLabel lblUser = new JLabel("");
		lblUser.setForeground(Color.WHITE);
		lblUser.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/user_male_circle_60px.png")));
		lblUser.setBounds(44, 10, 60, 60);
		barraLateral.add(lblUser);
		
		lblNombreUsuario = new JLabel("nombreUsuario", SwingConstants.CENTER);
		lblNombreUsuario.setForeground(Colors.FUENTE_MENU);
		lblNombreUsuario.setFont(new Font("Google Sans", Font.PLAIN, 17));
		lblNombreUsuario.setBounds(10, 78, 130, 16);
		barraLateral.add(lblNombreUsuario);
	}
	
	//Crea la interfaz y elementos del JPanel TituloPrincipal
	private void armarPanelTitulo() {
		panelTitulo = new JPanel();
		panelTitulo.setBounds(150, 0, 844, 60);
		panelTitulo.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sistema de Gesti\u00F3n de Licencias");
		lblTitulo.setBounds(188, 10, 468, 39);
		lblTitulo.setBackground(Colors.FUENTE_NORMAL);
		panelTitulo.add(lblTitulo);
		lblTitulo.setFont(new Font("Google Sans", Font.BOLD, 30));
	}
	
	//Crea la interfaz y elementos del JPanel PanelEmitir
	private void armarPanelBuscarTitular() {
		panelBuscarTitular = new JPanel();
		panelBuscarTitular.setBounds(150, 60, 844, 605);
		frmPrincipal.getContentPane().add(panelBuscarTitular);
		panelBuscarTitular.setLayout(null);
		
		JLabel lblTituloBuscarTitular = new JLabel("Buscar titular");
		lblTituloBuscarTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloBuscarTitular.setFont(new Font("Google Sans", Font.PLAIN, 25));
		lblTituloBuscarTitular.setBounds(12, 15, 817, 25);
		panelBuscarTitular.add(lblTituloBuscarTitular);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDoc(tfNro.getText())) {
					btnAceptar.setEnabled(true);
					//TODO obtenerDatos del titular
					/*TitularDTO dto = controladorTitular.titularLocator(cbTipo.getSelectedItem(), Long.valueOf(tfNro.getText()));
					if(dto.isNull()) {
						//TODO pasar a interfaz dar de alta titular
						if(JOptionPane.showConfirmDialog(null, "No se encontraron resultados, ¿Desea dar de alta al titular?", "Titular no encontrado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)
							== JOptionPane.YES_OPTION)
						{
							altaTitular=true;
							mostrarPanel(PANEL_ALTA_TITULAR);
						}
					}
					else{
						altaTitular=false;
						tfNombre.setText(dto.getName());
						tfApellido.setText(dto.getSurname());
						tfDireccion.setText(dto.getAddress());
						tfFechaNac.setText(dto.getBirthday());
						cbTipoSangre.setSelectedItem(dto.getBloodType());
						ckbDonante.setSelected(dto.getOrganDonnor());
					}*/
				}
				else {
					JOptionPane.showMessageDialog(null, "Formato de documento incorrecto", "Error", JOptionPane.OK_OPTION);
				}
			}
		});
		btnBuscar.setFont(new Font("Google Sans", Font.BOLD, 15));
		btnBuscar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/find_user_filled_30px.png")));
		btnBuscar.setBounds(572, 111, 130, 40);
		panelBuscarTitular.add(btnBuscar);
		
		JLabel lblTipoDoc = new JLabel("Tipo:");
		lblTipoDoc.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblTipoDoc.setBounds(15, 109, 53, 40);
		panelBuscarTitular.add(lblTipoDoc);
		
		cbTipoDoc = new JComboBox();
		cbTipoDoc.setBounds(132, 109, 80, 40);
		
		//TODO Cargar enum del tipo de doc desde BD
		
		panelBuscarTitular.add(cbTipoDoc);
		
		JLabel lblNumero = new JLabel("Nro:");
		lblNumero.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblNumero.setBounds(224, 109, 74, 40);
		panelBuscarTitular.add(lblNumero);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setFont(new Font("Google Sans", Font.PLAIN, 22));
		lblDocumento.setBounds(15, 76, 436, 20);
		panelBuscarTitular.add(lblDocumento);
		
		tfNro = new JTextField();
		tfNro.setFont(new Font("Google Sans", Font.PLAIN, 18));
		tfNro.setBounds(274, 109, 177, 40);
		
		//Configuramos que solo permita ingresar digitos
		tfNro.addKeyListener(new KeyAdapter() {
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
		panelBuscarTitular.add(tfNro);
		tfNro.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre/s:");
		lblNombre.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblNombre.setBounds(15, 162, 119, 40);
		panelBuscarTitular.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido/s:");
		lblApellido.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblApellido.setBounds(463, 162, 119, 40);
		panelBuscarTitular.add(lblApellido);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setFont(new Font("Google Sans", Font.PLAIN, 18));
		tfNombre.setColumns(10);
		tfNombre.setBounds(132, 162, 319, 40);
		panelBuscarTitular.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setFont(new Font("Google Sans", Font.PLAIN, 18));
		tfApellido.setEditable(false);
		tfApellido.setColumns(10);
		tfApellido.setBounds(572, 164, 260, 40);
		panelBuscarTitular.add(tfApellido);
		
		JLabel lblFechaNac = new JLabel("Fecha nac.:");
		lblFechaNac.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblFechaNac.setBounds(15, 215, 119, 40);
		panelBuscarTitular.add(lblFechaNac);
		
		try {
			MaskFormatter fechaMask = new MaskFormatter("##/##/####");
			fechaMask.setPlaceholderCharacter('_');
			tfFechaNac = new JFormattedTextField(fechaMask);
			tfFechaNac.setFont(new Font("Google Sans", Font.PLAIN, 18));
			tfFechaNac.setEditable(false);
			tfFechaNac.setColumns(10);
			tfFechaNac.setBounds(132, 215, 80, 40);
			panelBuscarTitular.add(tfFechaNac);
		}catch(Exception e) {};
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblDireccion.setBounds(15, 268, 119, 40);
		panelBuscarTitular.add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Google Sans", Font.PLAIN, 18));
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(132, 268, 700, 40);
		panelBuscarTitular.add(tfDireccion);
		
		JLabel lblGrupoSanguineo = new JLabel("Tipo de sangre:");
		lblGrupoSanguineo.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblGrupoSanguineo.setBounds(463, 215, 170, 40);
		panelBuscarTitular.add(lblGrupoSanguineo);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(altaTitular && validarDatosTitular()) {
					mostrarPanel(PANEL_EMITIR);
				}
				else {
					
				}
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setFont(new Font("Google Sans", Font.BOLD, 20));
		btnAceptar.setBounds(463, 321, 175, 40);

		panelBuscarTitular.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaTitular=false;
				limpiar(panelBuscarTitular);
				mostrarPanel(PANEL_LICENCIA);
			}
		});
		btnCancelar.setFont(new Font("Google Sans", Font.BOLD, 20));
		btnCancelar.setBounds(657, 321, 175, 40);
		panelBuscarTitular.add(btnCancelar);
		
		cbTipoSangre = new JComboBox();
		cbTipoSangre.setEnabled(false);
		cbTipoSangre.setBounds(607, 215, 80, 40);
		panelBuscarTitular.add(cbTipoSangre);
		
		ckbDonante = new JCheckBox(" Donante");
		ckbDonante.setEnabled(false);
		ckbDonante.setFont(new Font("Google Sans", Font.PLAIN, 20));
		ckbDonante.setBounds(703, 215, 129, 40);
		panelBuscarTitular.add(ckbDonante);
	}
	
	private void armarPanelEmitirLicencia() {
		panelEmitir = new JPanel();
		panelEmitir.setVisible(false);
		panelEmitir.setBounds(0, 321, 844, 284);
		panelBuscarTitular.add(panelEmitir);
		panelEmitir.setLayout(null);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(15, 0, 150, 40);
		panelEmitir.add(lblObservaciones);
		lblObservaciones.setFont(new Font("Google Sans", Font.PLAIN, 20));
		
		JTextArea taObservaciones = new JTextArea();
		taObservaciones.setBounds(15, 41, 433, 146);
		panelEmitir.add(taObservaciones);
		taObservaciones.setLineWrap(true);
		taObservaciones.setFont(new Font("Google Sans", Font.PLAIN, 18));
		
		JLabel lblClase = new JLabel("Clase:");
		lblClase.setBounds(460, 41, 119, 40);
		panelEmitir.add(lblClase);
		lblClase.setFont(new Font("Google Sans", Font.PLAIN, 20));
		
		JComboBox cbClase = new JComboBox();
		cbClase.setBounds(572, 41, 80, 40);
		panelEmitir.add(cbClase);
		
		JLabel lblVigencia = new JLabel("Vigencia:");
		lblVigencia.setBounds(460, 94, 119, 40);
		panelEmitir.add(lblVigencia);
		lblVigencia.setFont(new Font("Google Sans", Font.PLAIN, 20));
		
		tfVigencia = new JTextField();
		tfVigencia.setBounds(572, 93, 260, 40);
		panelEmitir.add(tfVigencia);
		tfVigencia.setFont(new Font("Google Sans", Font.PLAIN, 18));
		tfVigencia.setEditable(false);
		tfVigencia.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(460, 147, 119, 40);
		panelEmitir.add(lblCosto);
		lblCosto.setFont(new Font("Google Sans", Font.PLAIN, 20));
		
		tfCosto = new JTextField();
		tfCosto.setBounds(572, 147, 260, 40);
		panelEmitir.add(tfCosto);
		tfCosto.setFont(new Font("Google Sans", Font.PLAIN, 18));
		tfCosto.setEditable(false);
		tfCosto.setColumns(10);
		
		JButton btnAceptarEmitir = new JButton("ACEPTAR");
		btnAceptarEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				configurarPanelEmitir(false);
			}
		});
		btnAceptarEmitir.setBounds(463, 231, 175, 40);
		btnAceptarEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Validar datos ingresados
				/*
				 
				 */
			}
		});
		panelEmitir.add(btnAceptarEmitir);
		btnAceptarEmitir.setFont(new Font("Google Sans", Font.BOLD, 20));
		btnAceptarEmitir.setEnabled(false);
		
		JButton btnCancelarEmitir = new JButton("CANCELAR");
		btnCancelarEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					limpiar(panelBuscarTitular);
					limpiar(panelEmitir);
					mostrarPanel(PANEL_BUSCAR_TITULAR);
					altaTitular=false;
				}
			}
		});
		btnCancelarEmitir.setBounds(657, 231, 175, 40);
		panelEmitir.add(btnCancelarEmitir);
		btnCancelarEmitir.setFont(new Font("Google Sans", Font.BOLD, 20));
	}
	
	//Crea la interfaz y elementos del JPanel PanelPrincipal
	private void armarPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(150, 60, 844, 605);
		panelPrincipal.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblTituloPrincipal = new JLabel("Licencias vigentes");
		lblTituloPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPrincipal.setFont(new Font("Google Sans", Font.PLAIN, 18));
		lblTituloPrincipal.setBounds(15, 15, 817, 25);
		panelPrincipal.add(lblTituloPrincipal);
		
		tableLicencias = new JTable();
		tableLicencias.setBounds(15, 55, 817, 537);
		panelPrincipal.add(tableLicencias);
	}
	
	//Crea la interfaz y elementos del JPanel PanelLicencia
	private void armarPanelLicencia() {
		panelLicencia = new JPanel();
		panelLicencia.setBounds(150, 60, 844, 605);
		panelLicencia.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelLicencia);
		panelLicencia.setVisible(false);
		panelLicencia.setLayout(null);
		
		JButton btnEmitir = new JButton("");
		btnEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(PANEL_BUSCAR_TITULAR);
			}
		});
		btnEmitir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/emitir_100px.png")));
		btnEmitir.setBounds(211, 151, 105, 105);
		panelLicencia.add(btnEmitir);
		
		JButton btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImprimir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/print_100px.png")));
		btnImprimir.setBounds(528, 151, 105, 105);
		panelLicencia.add(btnImprimir);
		
		JLabel lblEmitir = new JLabel("EMITIR");
		lblEmitir.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmitir.setFont(new Font("Google Sans", Font.PLAIN, 18));
		lblEmitir.setBounds(211, 260, 105, 16);
		panelLicencia.add(lblEmitir);
		
		JLabel lblImprimir = new JLabel("IMPRIMIR");
		lblImprimir.setHorizontalAlignment(SwingConstants.CENTER);
		lblImprimir.setFont(new Font("Google Sans", Font.PLAIN, 18));
		lblImprimir.setBounds(528, 260, 105, 16);
		panelLicencia.add(lblImprimir);
	}
	
	//Crea la interfaz y elementos del JPanel PanelUsuario
	private void armarPanelUsuario() {
		panelUsuario = new JPanel();
		panelUsuario.setBounds(150, 60, 844, 605);
		panelUsuario.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
	}
	
	//Muestra el panel solicitado ocultando los demas
	private void mostrarPanel(int panel) {
		panelPrincipal.setVisible(false);
		panelBuscarTitular.setVisible(false);
		panelLicencia.setVisible(false);
		panelUsuario.setVisible(false);
		panelEmitir.setVisible(false);
		
		switch(panel) {
			case PANEL_PRINCIPAL:{
				panelPrincipal.setVisible(true);
				break;
			}
			case PANEL_LICENCIA:{
				panelLicencia.setVisible(true);
				break;
			}
			case PANEL_BUSCAR_TITULAR:{
				activarDesactivarComponentesPanelBuscarTitular(Boolean.FALSE);
				btnAceptar.setVisible(true);
				btnAceptar.setEnabled(false);
				btnCancelar.setVisible(true);
				panelBuscarTitular.setVisible(true);
				break;
			}
			case PANEL_USUARIO:{
				panelUsuario.setVisible(true);
				break;
			}
			case PANEL_ALTA_TITULAR:{
				activarDesactivarComponentesPanelBuscarTitular(Boolean.TRUE);
				panelBuscarTitular.setVisible(true);
				break;
			}
			case PANEL_EMITIR:{
				panelBuscarTitular.setVisible(true);
				configurarPanelEmitir(true);
				break;
			}
		}
	}
	
	//Activa o desactiva los elementos de la interfaz panel emitir para dar de alta a un titular o emitir una licencia
	private void activarDesactivarComponentesPanelBuscarTitular(boolean activo) {
		btnBuscar.setVisible(!activo);
		cbTipoDoc.setEnabled(!activo);
		tfNro.setEditable(!activo);
		tfNombre.setEditable(activo);
		tfApellido.setEditable(activo);
		tfDireccion.setEditable(activo);
		tfFechaNac.setEditable(activo);
		cbTipoSangre.setEnabled(activo);
		ckbDonante.setEnabled(activo);
	}
	
	//Configura la interfaz bloqueando los elementos de buscar titular y habilitando los de emitir
	private void configurarPanelEmitir(boolean activo) {
		btnBuscar.setVisible(!activo);
		cbTipoDoc.setEnabled(!activo);
		tfNro.setEditable(!activo);
		tfNombre.setEditable(!activo);
		tfApellido.setEditable(!activo);
		tfDireccion.setEditable(!activo);
		tfFechaNac.setEditable(!activo);
		cbTipoSangre.setEnabled(!activo);
		ckbDonante.setEnabled(!activo);
		btnAceptar.setVisible(!activo);
		btnCancelar.setVisible(!activo);
		panelEmitir.setVisible(activo);
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
	
	//Validamos los datos ingresados en alta titular
	private boolean validarDatosTitular() {
		boolean resultado=false;
		
		//TODO terminar validaciones
		if(tfNombre.getText().matches("[A-Za-z]{1,3}")
				&& tfApellido.getText().matches("[A-Za-z]{1,4")
				&& tfFechaNac.getText().matches("^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$"))
			resultado=true;
		return resultado;
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
