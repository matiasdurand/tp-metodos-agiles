package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import res.colors.Colors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImprimirLicencia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImprimirLicencia frame = new ImprimirLicencia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ImprimirLicencia() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(844,605));
		setIconImage(Toolkit.getDefaultToolkit().getImage(ImprimirLicencia.class.getResource("/res/images/print_icon_125px.png")));
		setTitle("Imprimir licencia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 844, 605);
		contentPane = new JPanel();
		contentPane.setBackground(Colors.FONDO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Imprimir licencia");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitulo.setBackground(new Color(16, 16, 16));
		lblTitulo.setBounds(10, 13, 816, 39);
		contentPane.add(lblTitulo);
		
		JPanel panelPDF = new JPanel();
		panelPDF.setFocusable(false);
		panelPDF.setBounds(10, 65, 816, 439);
		panelPDF.setBackground(Color.WHITE);
		contentPane.add(panelPDF);
		panelPDF.setLayout(null);
		
		JTextArea taInfoLicencia = new JTextArea();
		taInfoLicencia.setFont(new Font("Verdana", Font.BOLD, 20));
		taInfoLicencia.setBounds(332, 135, 297, 174);
		taInfoLicencia.setBorder(null);
		taInfoLicencia.setBackground(new Color(0,0,0,0));
		panelPDF.add(taInfoLicencia);
		
		JLabel lblTemplateLicencia = new JLabel("");
		lblTemplateLicencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemplateLicencia.setBackground(Colors.FONDO);
		lblTemplateLicencia.setForeground(Colors.FONDO);
		lblTemplateLicencia.setIcon(new ImageIcon(ImprimirLicencia.class.getResource("/res/images/template_licencia.jpg")));
		lblTemplateLicencia.setBounds(172, 70, 471, 298);
		panelPDF.add(lblTemplateLicencia);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?","Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setFocusable(false);
		btnCancelar.setBounds(651, 517, 175, 40);
		contentPane.add(btnCancelar);
		
		JButton btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.setEnabled(false);
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnImprimir.setFocusable(false);
		btnImprimir.setBounds(464, 517, 175, 40);
		contentPane.add(btnImprimir);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setEnabled(false);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGuardar.setFocusable(false);
		btnGuardar.setBounds(277, 517, 175, 40);
		contentPane.add(btnGuardar);
	}
}
