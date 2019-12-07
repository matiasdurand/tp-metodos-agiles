package controllers;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.itextpdf.io.IOException;

import dto.LicenseDTO;
import dto.TitularDTO;
import gui.ImprimirLicencia;
import gui.Login;
import gui.PanelEmitir;
import gui.PanelInicial;

public class PanelController {
	
	private static PanelController _INSTANCE = null;
	private static PanelEmitir panelEmitir;
	private static PanelInicial panelInicial;
	private static Login login;
	
	public static PanelController getInstance() {
		if(_INSTANCE == null)
			_INSTANCE = new PanelController();
		return _INSTANCE;
	}
	
	private PanelController() {
		
	}
	
	public static JFrame getImprimir(TitularDTO titularDTO, LicenseDTO licenseDTO, String destino) throws IOException, java.io.IOException {
		 return (new ImprimirLicencia(titularDTO,licenseDTO,destino)); 
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
	
	public static JDialog getLogin() {
		if(login == null)
			login = new Login();
		else
			login.reset();
		return login;
	}	
}
