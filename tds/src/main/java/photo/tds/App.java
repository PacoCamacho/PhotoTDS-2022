package photo.tds;

import java.awt.EventQueue;

import photo.tds.gui.VentanaLogin;

public class App {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin ventana = new VentanaLogin();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
