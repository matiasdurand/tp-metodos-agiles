package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import res.colors.Colors;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUser;
	private JTextField tfPassword;
	private JButton btnIngresar;

	public Login() {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				MenuPrincipal.menuPrincipal.cerrar();
			}
		});
		initialize();
	}

	private void initialize() {
		this.setTitle("Iniciar sesi\u00F3n");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		this.setAutoRequestFocus(false);
		this.setAlwaysOnTop(true);
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Colors.FONDO);
		this.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.getContentPane().setLayout(null);
		this.setBounds(0, 0, 550, 400);
		this.setMinimumSize(new Dimension(550,400));
		this.setLocationRelativeTo(null);
	
		JLabel lblIniciarSesion = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(43, 121, 444, 33);
		this.getContentPane().add(lblIniciarSesion);
		
		JLabel lblIcono = new JLabel("");
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setIcon(new ImageIcon(Login.class.getResource("/res/images/user_male_circle_filled_100px.png")));
		lblIcono.setBounds(210, 15, 110, 110);
		this.getContentPane().add(lblIcono);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setIcon(new ImageIcon(Login.class.getResource("/res/images/user_filled_30px.png")));
		lblUser.setBounds(41, 179, 145, 40);
		this.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setIcon(new ImageIcon(Login.class.getResource("/res/images/password_filled_30px.png")));
		lblPassword.setBounds(41, 232, 145, 40);
		this.getContentPane().add(lblPassword);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfUser.setBounds(220, 187, 265, 30);
		this.getContentPane().add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPassword.setColumns(10);
		tfPassword.setBounds(220, 240, 265, 30);
		this.getContentPane().add(tfPassword);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setFocusable(false);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfUser.getText().isEmpty() && !tfPassword.getText().isEmpty()) {
					//TODO validar usuario/contraseña
					MenuPrincipal.menuPrincipal.ingresar(tfUser.getText());
					dispose();
				}
			}
		});
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnIngresar.setBounds(41, 295, 444, 40);
		this.getContentPane().add(btnIngresar);
	}
	
	public void reset() {
		tfUser.setText("");
		tfPassword.setText("");
	}
}
