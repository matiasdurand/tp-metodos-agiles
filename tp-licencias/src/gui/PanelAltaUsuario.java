package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import res.colors.Colors;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class PanelAltaUsuario extends JPanel {

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

	public PanelAltaUsuario() {
		super();
		initialize();
	}
	
	private void initialize() {
		this.setBounds(150, 60, 844, 605);
		this.setBackground(Colors.FONDO);
		this.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Alta de usuario");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(12, 15, 817, 25);
		this.add(lblTitulo);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreDeUsuario.setBounds(15, 105, 194, 40);
		add(lblNombreDeUsuario);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAceptar.setFocusable(false);
		btnAceptar.setBounds(460, 318, 175, 40);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateUserName())
					if(validatePassword()){
						nuevoUsuarioDTO.setUserName(tfUser.getText());
						nuevoUsuarioDTO.setPassword(tfPassword.getText());
						nuevoUsuarioDTO.setSuperUser(ckbSuperUser.isSelected());
						controladorUsuario.registerUser(nuevoUsuarioDTO, MenuPrincipal.menuPrincipal.usuarioDTO);
						JOptionPane.showMessageDialog(null, "El usuario ha sido dado de alta correctamente");
					}
					else
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
				else
					JOptionPane.showMessageDialog(null, "El nombre de usuario ingresado ya se encuentra en uso");
			}
		});
		add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setFocusable(false);
		btnCancelar.setBounds(654, 318, 175, 40);
		add(btnCancelar);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfUser.setDisabledTextColor(Color.GRAY);
		tfUser.setColumns(10);
		tfUser.setBounds(201, 105, 250, 40);
		add(tfUser);
		
		JButton btnMostrarOcultarContraseña = new JButton("");
		btnMostrarOcultarContraseña.setBackground(Color.WHITE);
		btnMostrarOcultarContraseña.setVerticalTextPosition(SwingConstants.TOP);
		btnMostrarOcultarContraseña.setVerticalAlignment(SwingConstants.TOP);
		btnMostrarOcultarContraseña.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMostrarOcultarContraseña.setIcon(new ImageIcon(PanelAltaUsuario.class.getResource("/res/images/show_password_30px.png")));
		btnMostrarOcultarContraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrarOcultarContraseña.setFocusable(false);
		btnMostrarOcultarContraseña.setBounds(411, 158, 40, 40);
		btnMostrarOcultarContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarContraseña=(!mostrarContraseña);
				if(mostrarContraseña) {
					btnMostrarOcultarContraseña.setIcon(new ImageIcon(PanelAltaUsuario.class.getResource("/res/images/show_password_30px.png")));
					tfPassword.setEchoChar((char)0);
					tfRepeatPassword.setEchoChar((char)0);
				}
				else {
					btnMostrarOcultarContraseña.setIcon(new ImageIcon(PanelAltaUsuario.class.getResource("/res/images/hide_password_30px.png")));
					tfPassword.setEchoChar('*');
					tfRepeatPassword.setEchoChar('*');
				}
			}
		});
		add(btnMostrarOcultarContraseña);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContraseña.setBounds(15, 158, 194, 40);
		add(lblContraseña);
		
		JLabel lblRepetirContraseña = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRepetirContraseña.setBounds(15, 211, 194, 40);
		add(lblRepetirContraseña);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfPassword.setDisabledTextColor(Color.GRAY);
		tfPassword.setColumns(10);
		tfPassword.setBounds(201, 158, 250, 40);
		add(tfPassword);
		
		tfRepeatPassword = new JPasswordField();
		tfRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfRepeatPassword.setDisabledTextColor(Color.GRAY);
		tfRepeatPassword.setColumns(10);
		tfRepeatPassword.setBounds(201, 211, 250, 40);
		add(tfRepeatPassword);
		
		JCheckBox ckbSuperUser = new JCheckBox(" Conceder permiso de administrador");
		ckbSuperUser.setHorizontalAlignment(SwingConstants.RIGHT);
		ckbSuperUser.setSelected(false);
		ckbSuperUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ckbSuperUser.setFocusable(false);
		ckbSuperUser.setBackground(new Color(232, 234, 246));
		ckbSuperUser.setBounds(463, 105, 366, 40);
		add(ckbSuperUser);
	}

	protected boolean validateUserName() {
		return controladorUsuario.validate(tfUser.getText());
	}

	@SuppressWarnings("deprecation")
	protected boolean validatePassword() {
		return (tfPassword.getText()!="" && tfPassword.getText()==tfRepeatPassword.getText());
	}
}
