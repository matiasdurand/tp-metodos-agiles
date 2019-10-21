package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import res.colors.Colors;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class MenuPrincipal{

	private JFrame frame;
	private JTable tableLicencias;
	private JPanel barraLateral;
	private JPanel panelTitulo;
	private JPanel panelLicencia;
	private JPanel panelPrincipal;
	private JPanel panelUsuario;
	private JPanel panelEmitir;
	private JTextField tfNro;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfFechaNac;
	private JTextField tfDireccion;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Creamos las distintas interfaces
		armarBarraLateral();
		armarPanelTitulo();
		armarPanelEmitir();
		armarPanelUsuario();
		armarPanelPrincipal();
		
		//Inicializamos la vista en panelPrincipal
		panelPrincipal();
	}
	
	//Crea la interfaz y elementos del JPanel BarraLateral
	private void armarBarraLateral() {
		barraLateral = new JPanel();
		barraLateral.setBackground(new Color(0, 0, 128));
		barraLateral.setBounds(0, 0, 150, 665);
		frame.getContentPane().add(barraLateral);
		barraLateral.setLayout(null);
		
		JButton btnLicencia = new JButton("LICENCIA");
		btnLicencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(panelPrincipal.isVisible()) {
					panelPrincipal.setVisible(false);
					panelLicencia.setVisible(true);
				}
				else {
					panelPrincipal.setVisible(true);
					panelLicencia.setVisible(false);
				}
			}
		});
		btnLicencia.setForeground(Colors.MENU_LATERAL);
		btnLicencia.setBackground(Colors.FONDO);
		btnLicencia.setFont(new Font("Google Sans", Font.PLAIN, 15));
		btnLicencia.setBounds(0, 129, 150, 40);
		barraLateral.add(btnLicencia);
		
		JButton btnUsuario = new JButton("USUARIO");
		btnUsuario.setForeground(Colors.MENU_LATERAL);
		btnUsuario.setFont(new Font("Google Sans", Font.PLAIN, 15));
		btnUsuario.setBackground(Colors.FONDO);
		btnUsuario.setBounds(0, 169, 150, 40);
		barraLateral.add(btnUsuario);
		
		JButton btnCerrarSesion = new JButton("CERRAR SESI\u00D3N");
		btnCerrarSesion.setBackground(Colors.FONDO);
		btnCerrarSesion.setForeground(Colors.MENU_LATERAL);
		btnCerrarSesion.setFont(new Font("Google Sans", Font.PLAIN, 15));
		btnCerrarSesion.setBounds(0, 625, 150, 40);
		barraLateral.add(btnCerrarSesion);
		
		JLabel lblUser = new JLabel("");
		lblUser.setForeground(Color.WHITE);
		lblUser.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/user_male_circle_60px.png")));
		lblUser.setBounds(44, 10, 60, 60);
		barraLateral.add(lblUser);
		
		JLabel lblNombreUsuario = new JLabel("nombreUsuario", SwingConstants.CENTER);
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
		frame.getContentPane().add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sistema de Gesti\u00F3n de Licencias");
		lblTitulo.setBounds(188, 10, 468, 39);
		lblTitulo.setBackground(Colors.FUENTE_NORMAL);
		panelTitulo.add(lblTitulo);
		lblTitulo.setFont(new Font("Google Sans", Font.BOLD, 30));
	}
	
	//Crea la interfaz y elementos del JPanel PanelEmitir
	private void armarPanelEmitir() {
		panelEmitir = new JPanel();
		panelEmitir.setBounds(150, 60, 844, 605);
		frame.getContentPane().add(panelEmitir);
		panelEmitir.setLayout(null);
		
		JLabel lblTituloEmitir = new JLabel("Emitir licencia");
		lblTituloEmitir.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloEmitir.setFont(new Font("Google Sans", Font.PLAIN, 25));
		lblTituloEmitir.setBounds(15, 15, 817, 25);
		panelEmitir.add(lblTituloEmitir);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setFont(new Font("Google Sans", Font.BOLD, 15));
		btnBuscar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/find_user_filled_30px.png")));
		btnBuscar.setBounds(572, 111, 130, 40);
		panelEmitir.add(btnBuscar);
		
		JLabel lblTipoDoc = new JLabel("Tipo:");
		lblTipoDoc.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblTipoDoc.setBounds(15, 109, 53, 40);
		panelEmitir.add(lblTipoDoc);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(132, 109, 80, 40);
		panelEmitir.add(cbTipo);
		
		JLabel lblNumero = new JLabel("Nro:");
		lblNumero.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblNumero.setBounds(224, 109, 74, 40);
		panelEmitir.add(lblNumero);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumento.setFont(new Font("Google Sans", Font.PLAIN, 22));
		lblDocumento.setBounds(15, 76, 436, 20);
		panelEmitir.add(lblDocumento);
		
		tfNro = new JTextField();
		tfNro.setFont(new Font("Google Sans", Font.PLAIN, 15));
		tfNro.setBounds(274, 109, 177, 40);
		panelEmitir.add(tfNro);
		tfNro.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre/s:");
		lblNombre.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblNombre.setBounds(15, 162, 119, 40);
		panelEmitir.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido/s:");
		lblApellido.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblApellido.setBounds(463, 162, 119, 40);
		panelEmitir.add(lblApellido);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setFont(new Font("Google Sans", Font.PLAIN, 15));
		tfNombre.setColumns(10);
		tfNombre.setBounds(132, 162, 319, 40);
		panelEmitir.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setFont(new Font("Google Sans", Font.PLAIN, 15));
		tfApellido.setEditable(false);
		tfApellido.setColumns(10);
		tfApellido.setBounds(572, 164, 260, 40);
		panelEmitir.add(tfApellido);
		
		JLabel lblFechaNac = new JLabel("Fecha nac.:");
		lblFechaNac.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblFechaNac.setBounds(15, 215, 119, 40);
		panelEmitir.add(lblFechaNac);
		
		tfFechaNac = new JTextField();
		tfFechaNac.setFont(new Font("Google Sans", Font.PLAIN, 15));
		tfFechaNac.setEditable(false);
		tfFechaNac.setColumns(10);
		tfFechaNac.setBounds(132, 215, 319, 40);
		panelEmitir.add(tfFechaNac);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblDireccion.setBounds(15, 268, 119, 40);
		panelEmitir.add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Google Sans", Font.PLAIN, 15));
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(132, 268, 700, 40);
		panelEmitir.add(tfDireccion);
		
		JLabel lblGrupoSanguineo = new JLabel("Grupo sangu\u00EDneo:");
		lblGrupoSanguineo.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblGrupoSanguineo.setBounds(463, 215, 170, 40);
		panelEmitir.add(lblGrupoSanguineo);
		
		JLabel lblRH = new JLabel("RH:");
		lblRH.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblRH.setBounds(730, 215, 50, 40);
		panelEmitir.add(lblRH);
		
		textField = new JTextField();
		textField.setFont(new Font("Google Sans", Font.PLAIN, 15));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(633, 217, 85, 40);
		panelEmitir.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Google Sans", Font.PLAIN, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(771, 217, 61, 40);
		panelEmitir.add(textField_1);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setFont(new Font("Google Sans", Font.BOLD, 20));
		btnAceptar.setBounds(463, 321, 175, 40);

		panelEmitir.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Google Sans", Font.BOLD, 20));
		btnCancelar.setBounds(657, 321, 175, 40);
		panelEmitir.add(btnCancelar);
	}
	
	//Crea la interfaz y elementos del JPanel PanelPrincipal
	private void armarPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(150, 60, 844, 605);
		panelPrincipal.setBackground(Colors.FONDO);
		frame.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblTituloPrincipal = new JLabel("Licencias vigentes");
		lblTituloPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPrincipal.setFont(new Font("Google Sans", Font.PLAIN, 18));
		lblTituloPrincipal.setBounds(15, 15, 817, 25);
		panelPrincipal.add(lblTituloPrincipal);
		
		tableLicencias = new JTable();
		tableLicencias.setBounds(15, 55, 817, 537);
		panelPrincipal.add(tableLicencias);
		
		panelLicencia = new JPanel();
		panelLicencia.setBounds(150, 60, 844, 605);
		panelLicencia.setBackground(Colors.FONDO);
		frame.getContentPane().add(panelLicencia);
		panelLicencia.setVisible(false);
		panelLicencia.setLayout(null);
		
		JButton btnEmitir = new JButton("");
		btnEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelEmitir();
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
		frame.getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
	}

	//Pone en primer plano el panel principal y oculta los demas
	private void panelPrincipal() {
		panelPrincipal.setVisible(true);
		panelEmitir.setVisible(false);
		panelLicencia.setVisible(false);
		panelUsuario.setVisible(false);
	}
	
	//Pone en primer plano el panel emitir y oculta los demas
	private void panelEmitir() {
		panelPrincipal.setVisible(false);
		panelEmitir.setVisible(true);
		panelLicencia.setVisible(false);
		panelUsuario.setVisible(false);
	}
}
