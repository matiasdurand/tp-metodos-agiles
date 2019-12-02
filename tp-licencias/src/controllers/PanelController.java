package controllers;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.itextpdf.io.IOException;

import dto.LicenseDTO;
import dto.TitularDTO;
import gui.ImprimirLicencia;
import gui.Login;
import gui.PanelUsuario;
import gui.PanelEmitir;
import gui.PanelInicial;
import gui.PanelModificarTitular;

public class PanelController {
	
	private static PanelController _INSTANCE = null;
	private static PanelEmitir panelEmitir;
	private static PanelInicial panelInicial;
	private static ImprimirLicencia imprimir;
	private static PanelUsuario panelUsuario;
	private static PanelModificarTitular panelTitular;
	private static Login login;
	
	public static PanelController getInstance() {
		if(_INSTANCE == null)
			_INSTANCE = new PanelController();
		return _INSTANCE;
	}
	
	private PanelController() {
		
	}
	
	public static JFrame getImprimir(TitularDTO titularDTO, LicenseDTO licenseDTO, String destino) throws IOException, java.io.IOException {
		if(imprimir == null)
			imprimir = new ImprimirLicencia(titularDTO,licenseDTO,destino);
		return imprimir;
	}

	public static JPanel getPanelEmitir() {
		if(panelEmitir == null)
			panelEmitir = new PanelEmitir();
		else
			panelEmitir.reset();
		return panelEmitir;
	}
	
	public static JPanel getPanelInicial() {
		if(panelInicial == null)
			panelInicial = new PanelInicial();
		else
			panelInicial.reset();
		return panelInicial;
	}
	
	public static JPanel getPanelTitular(int tipo) {
		if(panelTitular == null)
			panelTitular = new PanelModificarTitular(tipo);
		else
			panelTitular.reset();
		return panelTitular;
	}
	
	public static JPanel getPanelUsuario(int tipo) {
		if(panelUsuario == null)
			panelUsuario = new PanelUsuario(tipo);
		else
			panelUsuario.reset();
		return panelUsuario;
	}
	
	public static JDialog getLogin() {
		if(login == null)
			login = new Login();
		else
			login.reset();
		return login;
	}	
}
