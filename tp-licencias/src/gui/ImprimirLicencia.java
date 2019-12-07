package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.io.IOException;

import dto.LicenseDTO;
import dto.TitularDTO;
import res.colors.Colors;
import utils.ConvertPDFPagesToImages;
import utils.GeneratePDF;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImprimirLicencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;

	public ImprimirLicencia(TitularDTO titularDTO, LicenseDTO licenseDTO, String destino) throws IOException, java.io.IOException {
		super();
		setResizable(false);
		setAutoRequestFocus(false);
		setMinimumSize(new Dimension(1050,605));
	    
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
		lblTitulo.setBounds(10, 13, 1022, 39);
		contentPane.add(lblTitulo);
		
		JPanel panelPDF = new JPanel();
		panelPDF.setFocusable(false);
		panelPDF.setBounds(10, 65, 1022, 439);
		panelPDF.setBackground(Color.WHITE);
		contentPane.add(panelPDF);
		panelPDF.setLayout(null);
		
		JLabel lblTemplateLicencia = new JLabel("");
		lblTemplateLicencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemplateLicencia.setBackground(Colors.FONDO);
		lblTemplateLicencia.setForeground(Colors.FONDO);
		GeneratePDF.generatePDF(titularDTO, licenseDTO, destino);
		cargarPDF(lblTemplateLicencia,destino);
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
		btnCancelar.setBounds(857, 517, 175, 40);
		contentPane.add(btnCancelar);
		
		JButton btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.setEnabled(false);
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnImprimir.setFocusable(false);
		btnImprimir.setBounds(670, 517, 175, 40);
		contentPane.add(btnImprimir);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setVisible(false);
		btnGuardar.setEnabled(false);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGuardar.setFocusable(false);
		btnGuardar.setBounds(483, 517, 175, 40);
		contentPane.add(btnGuardar);
	}
	
	private void cargarPDF(JLabel lbl, String dirPDF) {
		lbl.setBounds(12, 70, 1000, 306);
		String dirImg = System.getProperty("user.home") + "\\Desktop\\";
		ImageIcon imgLicencia = new ImageIcon(ConvertPDFPagesToImages.convertPDFPagesToImages(dirPDF, dirImg));
		lbl.setIcon(imgLicencia);
	}
}
