package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import res.colors.Colors;
import dto.TaxPayerDTO;
import dto.UserDTO;
import controllers.UserController;
import dao.TaxPayerDAOSQL;
import dao.UserDAOSQL;
import domain.TypeId;
import domain.User;

import dto.UserDTO;
import controllers.UserController;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;



public class Login extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JTextField tfUser;
	private JTextField tfPassword;
	private JButton btnIngresar;
	private JLabel lblError;
	private UserDTO usuarioDTO = null;
	private UserController controladorUsuario = UserController.getInstance();

	
	public Login() {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				//PARA PROBAR REGISTRO UN USUARIO Y UN CONTRIBUYENTE
				/*TaxPayerDTO taxPayer = new TaxPayerDTO();
				taxPayer.setTypeId(TypeId.DNI);
				taxPayer.setPersonalId((long) 98765432);
				taxPayer.setName("Juan");
				taxPayer.setSurname("Perez");
				taxPayer.setAdress("Boulevard 1234");
				
				LocalDate ld = LocalDate.of(1997, 12, 11);
			
				taxPayer.setBirthdate(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant()));
				
				new TaxPayerDAOSQL(TaxPayerDTO.class).save(taxPayer);
				
				User user = new User();
				user.setUsername("admin");
				user.setPassword("admin");
				user.setSuperUser(true);
				user.setTypeId(TypeId.DNI);
				user.setPersonalId((long) 40406072);
				user.setName("Matias");
				user.setSurname("Durand");
				
				new UserDAOSQL(User.class).save(user);*/
				
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
		lblIniciarSesion.setBounds(51, 121, 444, 33);
		this.getContentPane().add(lblIniciarSesion);
		
		JLabel lblIcono = new JLabel("");
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setIcon(new ImageIcon(Login.class.getResource("/res/images/user_male_circle_filled_100px.png")));
		lblIcono.setBounds(218, 15, 110, 110);
		this.getContentPane().add(lblIcono);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setIcon(new ImageIcon(Login.class.getResource("/res/images/user_filled_30px.png")));
		lblUser.setBounds(35, 179, 145, 40);
		this.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setIcon(new ImageIcon(Login.class.getResource("/res/images/password_filled_30px.png")));
		lblPassword.setBounds(35, 232, 145, 40);
		this.getContentPane().add(lblPassword);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfUser.setBounds(210, 187, 301, 30);
		this.getContentPane().add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPassword.setColumns(10);
		tfPassword.setBounds(210, 240, 301, 30);
		this.getContentPane().add(tfPassword);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setFocusable(false);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfUser.getText().isEmpty() && !tfPassword.getText().isEmpty()) {
					usuarioDTO = controladorUsuario.userLocator(tfUser.getText(), tfPassword.getText());
					if(usuarioDTO!=null) {
						MenuPrincipal.menuPrincipal.ingresar(usuarioDTO);
						dispose();
					}
					else {
						lblError.setVisible(true);
						tfPassword.setText("");
						Timer timer = new Timer(5000, new ActionListener() {

				            @Override
				            public void actionPerformed(ActionEvent e) {
				                lblError.setVisible(false);
				            }
				        });
				        timer.start();
					}
					
				}
			}
		});
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnIngresar.setBounds(35, 295, 476, 40);
		this.getContentPane().add(btnIngresar);
		
		lblError = new JLabel("*El usuario y contraseña no son válidos. Vuelva a intentarlo.");
		lblError.setVisible(false);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblError.setBounds(35, 275, 370, 15);
		getContentPane().add(lblError);
		
		controladorUsuario.userLocator(tfUser.getText(), tfPassword.getText());

	}
	
	public void reset() {
		tfUser.setText("");
		tfPassword.setText("");
	}
}