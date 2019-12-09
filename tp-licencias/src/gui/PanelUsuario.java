package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import controllers.TitularController;
import controllers.UserController;
import res.colors.Colors;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import domain.TypeId;
import dto.UserDTO;

public class PanelUsuario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tfUser;
	private JPasswordField tfPassword;
	private JPasswordField tfRepeatPassword;
	private boolean mostrarContraseña=false;
	
	private UserController controladorUsuario = UserController.getInstance();
	private UserDTO nuevoUsuarioDTO = new UserDTO();
	
	private JLabel lblTitulo;
	private JComboBox<TypeId> cmbTipoDoc;
	private JTextField tfNroDoc;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JButton btnBuscar;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public PanelUsuario(int opcion) {
		super();
		initialize();
		configurarInterfaz(opcion);
	}
	
	private void configurarInterfaz(int opcion) {
		switch(opcion) {
			case MenuPrincipal.PANEL_ALTA_USUARIO:{
				configurarInterfazAlta();
				break;
			}
			case MenuPrincipal.PANEL_MODIFICAR_USUARIO:{
				configurarInterfazModificar();
				break;
			}
		}
		
	}

	private void configurarInterfazModificar() {
		lblTitulo.setText("Modificar usuario");
		bloquearComponentes(true);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUser.getText()!="") {
					nuevoUsuarioDTO = controladorUsuario.userLocatorByUsername(tfUser.getText());
					if(nuevoUsuarioDTO!=null) {
						cargarDatos();
						bloquearComponentes(false);
					}
					else
						JOptionPane.showMessageDialog(null, "No se han encontrado resultados para este nombre de usuario. Vuelva a intentarlo");
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarEntradas()) {
					controladorUsuario.modifyUser(nuevoUsuarioDTO, MenuPrincipal.menuPrincipal.usuarioDTO);
					JOptionPane.showMessageDialog(null, "El usuario ha sido modificado correctamente");
				}
			}
		});
	}
	
	private void cargarDatos() {
		tfNombre.setText(nuevoUsuarioDTO.getName());
		tfApellido.setText(nuevoUsuarioDTO.getSurname());
		tfNroDoc.setText(nuevoUsuarioDTO.getPersonalId());
		cmbTipoDoc.setSelectedItem(nuevoUsuarioDTO.getTypeId());
		tfPassword.setText(nuevoUsuarioDTO.getPassword());
		tfRepeatPassword.setText(nuevoUsuarioDTO.getPassword());
	}
	
	private void bloquearComponentes(Boolean bloquear) {
		btnAceptar.setEnabled(!bloquear);
		tfNombre.setEnabled(!bloquear);
		tfApellido.setEnabled(!bloquear);
		cmbTipoDoc.setEnabled(false);
		tfNroDoc.setEnabled(false);
		tfPassword.setEnabled(!bloquear);
		tfRepeatPassword.setEnabled(!bloquear);
	}

	private void configurarInterfazAlta() {
		lblTitulo.setText("Registrar usuario");
		btnBuscar.setVisible(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarEntradas()) {
					controladorUsuario.registerUser(nuevoUsuarioDTO, MenuPrincipal.menuPrincipal.usuarioDTO);
					JOptionPane.showMessageDialog(null, "El usuario ha sido dado de alta correctamente");
				}
			}
		});
	}

	private boolean validarEntradas() {
		boolean valido=false;
		if(validateUsername())
			if(validatePassword()) {
				completarnuevoUsuarioDTO();
				if(controladorUsuario.validate(nuevoUsuarioDTO))
					valido=true;
				else
					JOptionPane.showMessageDialog(null, "La información del usuario ingresada no es válida");
			}
			else
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
		else
			JOptionPane.showMessageDialog(null, "El nombre de usuario ingresado ya se encuentra en uso");
		return valido;
	}
	
	private void completarnuevoUsuarioDTO() {
		nuevoUsuarioDTO.setName(tfNombre.getText());
		nuevoUsuarioDTO.setSurname(tfApellido.getText());
		nuevoUsuarioDTO.setTypeId((TypeId) cmbTipoDoc.getSelectedItem());
		nuevoUsuarioDTO.setPersonalId(tfNroDoc.getText());
		nuevoUsuarioDTO.setUsername(tfUser.getText());
		nuevoUsuarioDTO.setPassword(tfPassword.getSelectedText());
	}

	private void initialize() {
		this.setBounds(150, 60, 844, 605);
		this.setBackground(Colors.FONDO);
		this.setLayout(null);
		
		lblTitulo = new JLabel();
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(12, 17, 817, 25);
		this.add(lblTitulo);
		
		JLabel lblDocumento = new JLabel("Ingrese el documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDocumento.setBounds(12, 212, 210, 40);
		add(lblDocumento);
		
		JLabel lblTipoDoc = new JLabel("Tipo:");
		lblTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDoc.setBounds(234, 212, 53, 40);
		add(lblTipoDoc);
		
		cmbTipoDoc = new JComboBox<TypeId>();
		TitularController.getInstance().loadTypeIdComboBox(cmbTipoDoc);
		cmbTipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbTipoDoc.setFocusable(false);
		cmbTipoDoc.setBounds(299, 213, 149, 40);
		add(cmbTipoDoc);
		
		JLabel lblNroDoc = new JLabel("N\u00FAmero:");
		lblNroDoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNroDoc.setBounds(460, 212, 96, 40);
		add(lblNroDoc);
		
		tfNroDoc = new JTextField();
		tfNroDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNroDoc.setDisabledTextColor(Color.GRAY);
		tfNroDoc.setColumns(10);
		tfNroDoc.setBounds(568, 212, 261, 40);
		add(tfNroDoc);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon(PanelUsuario.class.getResource("/res/images/find_user_filled_30px.png")));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBuscar.setFocusable(false);
		btnBuscar.setBounds(579, 106, 175, 40);
		add(btnBuscar);
		
		JLabel lblNombre = new JLabel("Nombre/s:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(12, 264, 119, 40);
		add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNombre.setDisabledTextColor(Color.GRAY);
		tfNombre.setColumns(10);
		tfNombre.setBounds(129, 265, 319, 40);
		add(tfNombre);
		
		JLabel lblApellido = new JLabel("Apellido/s:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellido.setBounds(460, 264, 119, 40);
		add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfApellido.setDisabledTextColor(Color.GRAY);
		tfApellido.setColumns(10);
		tfApellido.setBounds(569, 265, 260, 40);
		add(tfApellido);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreDeUsuario.setBounds(12, 106, 194, 40);
		add(lblNombreDeUsuario);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAceptar.setFocusable(false);
		btnAceptar.setBounds(463, 552, 175, 40);
		add(btnAceptar);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfUser.setDisabledTextColor(Color.GRAY);
		tfUser.setColumns(10);
		tfUser.setBounds(198, 107, 250, 40);
		add(tfUser);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContraseña.setBounds(12, 159, 194, 40);
		add(lblContraseña);
		
		JButton btnMostrarOcultarContraseña = new JButton("");
		btnMostrarOcultarContraseña.setBackground(Color.WHITE);
		btnMostrarOcultarContraseña.setVerticalTextPosition(SwingConstants.TOP);
		btnMostrarOcultarContraseña.setVerticalAlignment(SwingConstants.TOP);
		btnMostrarOcultarContraseña.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMostrarOcultarContraseña.setIcon(new ImageIcon(PanelUsuario.class.getResource("/res/images/show_password_30px.png")));
		btnMostrarOcultarContraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrarOcultarContraseña.setFocusable(false);
		btnMostrarOcultarContraseña.setBounds(339, 159, 40, 40);
		btnMostrarOcultarContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarContraseña=(!mostrarContraseña);
				if(mostrarContraseña) {
					btnMostrarOcultarContraseña.setIcon(new ImageIcon(PanelUsuario.class.getResource("/res/images/show_password_30px.png")));
					tfPassword.setEchoChar((char)0);
					tfRepeatPassword.setEchoChar((char)0);
				}
				else {
					btnMostrarOcultarContraseña.setIcon(new ImageIcon(PanelUsuario.class.getResource("/res/images/hide_password_30px.png")));
					tfPassword.setEchoChar('*');
					tfRepeatPassword.setEchoChar('*');
				}
				revalidate();
				repaint();
			}
		});
		add(btnMostrarOcultarContraseña);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfPassword.setDisabledTextColor(Color.GRAY);
		tfPassword.setColumns(10);
		tfPassword.setBounds(129, 159, 250, 40);
		add(tfPassword);
		
		JLabel lblRepetirContraseña = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRepetirContraseña.setBounds(393, 159, 194, 40);
		add(lblRepetirContraseña);
		
		tfRepeatPassword = new JPasswordField();
		tfRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfRepeatPassword.setDisabledTextColor(Color.GRAY);
		tfRepeatPassword.setColumns(10);
		tfRepeatPassword.setBounds(579, 159, 250, 40);
		add(tfRepeatPassword);
		add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setFocusable(false);
		btnCancelar.setBounds(657, 552, 175, 40);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.menuPrincipal.cancelar(MenuPrincipal.PANEL_ALTA_USUARIO);
			}
		});
		add(btnCancelar);
	}

	protected boolean validateUsername() {
		return controladorUsuario.validate(nuevoUsuarioDTO);
	}

	@SuppressWarnings("deprecation")
	protected boolean validatePassword() {
		return (tfPassword.getText()!="" && tfPassword.getText()==tfRepeatPassword.getText());
	}
	
	public void reset(int opcion) {
		this.removeAll();
		this.initialize();
		this.configurarInterfaz(opcion);
	}
}
