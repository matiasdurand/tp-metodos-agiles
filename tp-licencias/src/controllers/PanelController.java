package controllers;

import java.awt.Component;
import java.awt.Container;

import javax.naming.Context;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.ImprimirLicencia;
import gui.Login;
import gui.PanelEmitir;
import gui.PanelInicial;

public class PanelController {
	
	private static PanelController _INSTANCE = null;
	private static PanelEmitir panelEmitir;
	private static PanelInicial panelInicial;
	private static ImprimirLicencia imprimir;
	private static Login login;
	
	public static PanelController getInstance() {
		if(_INSTANCE == null)
			_INSTANCE = new PanelController();
		return _INSTANCE;
	}
	
	private PanelController() {
		
	}
	
	public static JFrame getImprimir() {
		if(imprimir == null)
			imprimir = new ImprimirLicencia();
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
	
	public static JDialog getLogin() {
		if(login == null)
			login = new Login();
		return login;
	}

	private void limpiar(Component component) {
        if (component instanceof JTextField) {
                JTextField text = (JTextField) component;
                text.setText("");
        } else {
                if(component instanceof JTextArea) {
                	((JTextArea) component).setText("");
                }
        		if (component instanceof Container) {
                        for (Component c : ((Container) component).getComponents()) {
                                limpiar(c);
                        }
                }
        }
	}
	
}
