package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import res.colors.Colors;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

public class Login {
	private JFrame frmLogin;
	private JTextField tfUser;
	private JTextField tfPassword;
	private MenuPrincipal menuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(240, 240, 240));
		frmLogin.setTitle("Sistema de Gesti\u00F3n de Licencias");
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setFont(new Font("Google Sans", Font.PLAIN, 13));
		frmLogin.setBounds(100, 100, 550, 400);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesion.setFont(new Font("Google Sans", Font.BOLD, 25));
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(65, 121, 420, 33);
		frmLogin.getContentPane().add(lblIniciarSesion);
		
		JLabel lblIcono = new JLabel("");
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setIcon(new ImageIcon(Login.class.getResource("/res/images/user_male_circle_filled_100px.png")));
		lblIcono.setBounds(220, 15, 110, 110);
		frmLogin.getContentPane().add(lblIcono);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblUser.setIcon(new ImageIcon(Login.class.getResource("/res/images/user_filled_30px.png")));
		lblUser.setBounds(41, 189, 145, 40);
		frmLogin.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setFont(new Font("Google Sans", Font.PLAIN, 20));
		lblPassword.setIcon(new ImageIcon(Login.class.getResource("/res/images/password_filled_30px.png")));
		lblPassword.setBounds(41, 242, 145, 40);
		frmLogin.getContentPane().add(lblPassword);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Google Sans", Font.PLAIN, 15));
		tfUser.setBounds(220, 193, 265, 30);
		frmLogin.getContentPane().add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Google Sans", Font.PLAIN, 15));
		tfPassword.setColumns(10);
		tfPassword.setBounds(220, 248, 265, 30);
		frmLogin.getContentPane().add(tfPassword);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validar inicio de sesion
				//
				menuPrincipal = new MenuPrincipal(lblUser.getText(), frmLogin);
				limpiar(frmLogin);
				frmLogin.setVisible(false);
			}
		});
		btnAceptar.setFont(new Font("Google Sans", Font.BOLD, 20));
		btnAceptar.setBounds(41, 295, 444, 40);
		frmLogin.getContentPane().add(btnAceptar);
		
		JPanel panelInicioSesion = new JPanel();
		panelInicioSesion.setBounds(0, 0, 544, 365);
		panelInicioSesion.setBackground(Colors.FONDO);
		frmLogin.getContentPane().add(panelInicioSesion);
	}
	
	//Le enviamos un JPanel y limpiar los TextFields
	private void limpiar(Component component) {
        if (component instanceof JTextField) {
                JTextField text = (JTextField) component;
                text.setText("");
        } else {
                if (component instanceof Container) {
                        for (Component c : ((Container) component).getComponents()) {
                                limpiar(c);
                        }
                }
        }
	}
}
