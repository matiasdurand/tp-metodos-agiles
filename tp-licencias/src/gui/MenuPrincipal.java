package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.PanelController;
import res.colors.Colors;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Toolkit;

public class MenuPrincipal extends JFrame{
	
	private static final long serialVersionUID = 1L;
	//Constantes que identifican a cada JPanel
	private final int PANEL_LOGIN=-1;
	private final int PANEL_INICIAL=0;
	private final int PANEL_MENU_LICENCIA=1;
	private final int PANEL_MENU_USUARIO=2;
	public final static int PANEL_EMITIR=3;
	public final static int PANEL_RENOVAR=4;
	public final static int PANEL_IMPRIMIR=5;
	
	protected static MenuPrincipal menuPrincipal;
	private JFrame frmPrincipal;
	private JButton btnUsuario;
	private JButton btnLicencia;
	private JButton btnInicio;
	private JButton btnCerrarSesion;
	private JPanel barraLateral;
	private JPanel panelTitulo;
	private JPanel panelMenuLicencia;
	private JPanel panelMenuUsuario;
	private JPanel panel; //En este puntero se asigna el JPanel que se debe visualizar en pantalla
	private JLabel lblNombreUsuario;
	private String user=null;
	
	
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
	
	public MenuPrincipal() {
		menuPrincipal = this;
		initialize();
	}

	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setResizable(false);
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/res/images/program_icon_125px.png")));
		frmPrincipal.setTitle("Sistema de Gesti\u00F3n de Licencias");
		frmPrincipal.setBounds(100, 100, 1000, 700);
		frmPrincipal.setMinimumSize(new Dimension(1000, 700));
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Colors.FONDO);
		panel.setBounds(150, 60, 844, 605);
		frmPrincipal.getContentPane().add(panel);
		
		armarBarraLateral();
		armarPanelTitulo();
		armarPanelMenuUsuario();
		armarPanelMenuLicencia();
		
		//Inicializamos la vista en panelPrincipal
		if(user==null)
			mostrarPanel(PANEL_LOGIN);
		else
			mostrarPanel(PANEL_INICIAL);
	}
	
	//Crea la interfaz y elementos del JPanel BarraLateral
	private void armarBarraLateral() {
		barraLateral = new JPanel();
		barraLateral.setBackground(new Color(0, 0, 128));
		barraLateral.setBounds(0, 0, 150, 665);
		frmPrincipal.getContentPane().add(barraLateral);
		barraLateral.setLayout(null);
		
		btnLicencia = new JButton("LICENCIA");
		btnLicencia.setFocusPainted(false);
		btnLicencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(PANEL_MENU_LICENCIA);
			}
		});
		btnLicencia.setForeground(Colors.MENU_LATERAL);
		btnLicencia.setBackground(Colors.FONDO);
		btnLicencia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLicencia.setBounds(0, 169, 150, 40);
		barraLateral.add(btnLicencia);
		
		btnUsuario = new JButton("USUARIO");
		btnUsuario.setFocusPainted(false);
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(PANEL_MENU_USUARIO);
			}
		});
		btnUsuario.setForeground(Colors.MENU_LATERAL);
		btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUsuario.setBackground(Colors.FONDO);
		btnUsuario.setBounds(0, 209, 150, 40);
		barraLateral.add(btnUsuario);
		
		btnCerrarSesion = new JButton("CERRAR SESI\u00D3N");
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setMargin(new Insets(0, 0, 0, 0));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 17));
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					user=null;
					lblNombreUsuario.setText("nombreUsuario");
					barraLateral.repaint();
					mostrarPanel(PANEL_LOGIN);
				}
				else
					btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 17));;
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
		
		btnInicio = new JButton("INICIO");
		btnInicio.setForeground(new Color(25, 35, 126));
		btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnInicio.setFocusPainted(false);
		btnInicio.setBackground(new Color(232, 234, 246));
		btnInicio.setBounds(0, 129, 150, 40);
		btnInicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(PANEL_INICIAL);
			}
		});
		barraLateral.add(btnInicio);
	}
	
	//Crea la interfaz y elementos del JPanel TituloPrincipal
	private void armarPanelTitulo() {
		panelTitulo = new JPanel();
		panelTitulo.setBounds(150, 0, 844, 60);
		panelTitulo.setBackground(Colors.FONDO);
		frmPrincipal.getContentPane().add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sistema de Gesti\u00F3n de Licencias");
		lblTitulo.setBounds(175, 10, 500, 39);
		panelTitulo.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBackground(Colors.FUENTE_NORMAL);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
	}
	
	//Crea la interfaz y elementos del JPanel PanelLicencia
	private void armarPanelMenuLicencia() {
		panelMenuLicencia = new JPanel();
		panelMenuLicencia.setBounds(150, 60, 844, 605);
		panelMenuLicencia.setBackground(Colors.FONDO);
		panelMenuLicencia.setLayout(null);
		
		JButton btnEmitir = new JButton("");
		btnEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(PANEL_EMITIR);
			}
		});
		btnEmitir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/res/images/emitir_licencia_100px.png")));
		btnEmitir.setBounds(132, 151, 105, 105);
		panelMenuLicencia.add(btnEmitir);
		
		JButton btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel(PANEL_IMPRIMIR);
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
		panelMenuUsuario.setLayout(null);
	}
	
	//Muestra el panel solicitado ocultando los demas
	private void mostrarPanel(int opcion) {
		btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLicencia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmPrincipal.getContentPane().remove(panel);
		switch(opcion) {
			case PANEL_LOGIN:{
				PanelController.getLogin().setVisible(true);
				break;
			}
			case PANEL_INICIAL:{
				btnInicio.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel = PanelController.getPanelInicial();
				break;
			}
			case PANEL_MENU_LICENCIA:{
				btnLicencia.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel=panelMenuLicencia;
				break;
			}
			case PANEL_EMITIR:{
				panel=PanelController.getPanelEmitir();
				break;
			}
			case PANEL_IMPRIMIR:{
				//TODO implementar un menu para que seleccione la licencia que desea imprimir
				/*PanelController.getImprimir().setVisible(true);*/
				break;
			}
			case PANEL_MENU_USUARIO:{
				btnUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel=panelMenuUsuario;
				break;
			}
		}
		frmPrincipal.getContentPane().add(panel);
		frmPrincipal.pack();
		frmPrincipal.revalidate();
		frmPrincipal.repaint();
	}
	
	protected void cancelar(Integer aux) {
		switch(aux){
			case PANEL_EMITIR:{
				mostrarPanel(PANEL_MENU_LICENCIA);
				break;
			}
		}
	}
	
	protected void ingresar(String user) {
		this.user=user;
		lblNombreUsuario.setText(user);
		barraLateral.repaint();
		mostrarPanel(PANEL_INICIAL);
	}
	
	protected void cerrar() {
		System.exit(0);
	}
}
