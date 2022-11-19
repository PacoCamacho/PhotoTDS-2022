 package umu.tds.gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.CompoundBorder;

import umu.tds.controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;

/**
 * Clase Ventana Login
 * 
 * Vetana donde se 
 */
public class VentanaLogin {

	// --------------------------------- ATRIBUTOS --------------------------------------

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;

	// ---------------------------------- METODOS --------------------------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//CONSTRUCTOR
	public VentanaLogin() {
		initialize();
	}
	//Funcion para mostrar la ventana
	public void mostrarVentana() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}
	//Funcion para asignar el LOOK AND FIELD
	private void setLandF() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	//Metodo que crea el panel LOGIN
	private void creaPanelCentro() {
		JPanel panelLogin = new JPanel();
		panelLogin.setToolTipText("");
		panelLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gbl_panelLogin = new GridBagLayout();
		gbl_panelLogin.columnWidths = new int[]{50, 10, 0, 0, 0, 50, 0};
		gbl_panelLogin.rowHeights = new int[]{40, 0, 0, 0, 20, 10, 40, 0};
		gbl_panelLogin.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelLogin.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelLogin.setLayout(gbl_panelLogin);
		
		//LABEL USUARIO
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setIcon(new ImageIcon(VentanaLogin.class.getResource("/resources/programmer.png")));
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.gridheight = 4;
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 2;
		gbc_lblUsuario.gridy = 1;
		panelLogin.add(lblUsuario, gbc_lblUsuario);
		
		//CAMPO DE TEXTO DE USUARIO
		textField = new JTextField();
		textField.setToolTipText("");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		panelLogin.add(textField, gbc_textField);
		textField.setColumns(25);
		
		//PANEL CENTRO
		JPanel panelCentro = new JPanel();
		panelCentro.setBorder(new CompoundBorder());
		frmLogin.getContentPane().add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{0, 0, 0};
		gbl_panelCentro.rowHeights = new int[]{0, 0, 20};
		gbl_panelCentro.columnWeights = new double[]{0.0, Double.MIN_VALUE, 0.0};
		gbl_panelCentro.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0};
		panelCentro.setLayout(gbl_panelCentro);
		GridBagConstraints gbc_panelLogin = new GridBagConstraints();
		gbc_panelLogin.insets = new Insets(0, 0, 5, 5);
		gbc_panelLogin.gridx = 1;
		gbc_panelLogin.gridy = 1;
		panelCentro.add(panelLogin, gbc_panelLogin);	//LINEA PARA AÑADIR EL PANEL LOGIN AL CENTRO SIN CONSTRAINTS PARA QUE ME LO PONGA CENTRADO
		

		//LABEL CONTRASEÑA
		JLabel lblConstrasea = new JLabel(" Constraseña:");
		lblConstrasea.setForeground(Color.WHITE);
		lblConstrasea.setIcon(new ImageIcon(VentanaLogin.class.getResource("/resources/padlock.png")));
		GridBagConstraints gbc_lblConstrasea = new GridBagConstraints();
		gbc_lblConstrasea.anchor = GridBagConstraints.EAST;
		gbc_lblConstrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstrasea.gridx = 2;
		gbc_lblConstrasea.gridy = 5;
		panelLogin.add(lblConstrasea, gbc_lblConstrasea);
		
		//CAMPO DE TEXTO DE LA CONTRASEÑA
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 5;
		panelLogin.add(passwordField, gbc_passwordField);
	}
	
	//Metodo para crear el panel norte
	private void creaPanelNorte() {
		JPanel panelNorte = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelNorte.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setVgap(15);
		frmLogin.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		//LOGO DE APPVIDEO 
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(VentanaLogin.class.getResource("/resources/logoAppVideo_opt.png")));
		panelNorte.add(labelLogo);
	}
	
	//Manejador del boton registro que nos lleva a la ventana de registro
	private void addManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroView registroView = new RegistroView(frmLogin);
				registroView.setLocationRelativeTo(frmLogin);                              	
				registroView.setVisible(true);
				frmLogin.dispose();
			}
		});
	}
	
	//Manejador del boton login para comprobar si los datos introducidos por el usuario son correctos
	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.INSTANCE.loginUsuario(textField.getText(),
						new String(passwordField.getPassword())); //llamamos a la funcion del controlador para loguear

				if (login) { // si nos logueamos correctamente, lanzamos la ventana principal y cerramos la de login
					VentanaPrincipal window = new VentanaPrincipal();
					window.mostrarVentana();
					frmLogin.dispose();
				} else // sino, le comunicamos al usuario de que los datos que ha introducido son incorrectos o no existen en el sistema
					JOptionPane.showMessageDialog(frmLogin, "Nombre de usuario o contraseña no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	//Metodo que crea el panel SUR
	private void creaPanelSur() {
		JPanel panelSur = new JPanel();
		panelSur.setToolTipText("");
		frmLogin.getContentPane().add(panelSur, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSur = new GridBagLayout();
		gbl_panelSur.columnWidths = new int[]{373, 63, 10, 79, 373, 0};
		gbl_panelSur.rowHeights = new int[]{10, 25, 10, 0, 20, 0};
		gbl_panelSur.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelSur.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSur.setLayout(gbl_panelSur);
		
		//BOTON LOGIN
		JButton btnLogin = new JButton("Login");
		addManejadorBotonLogin(btnLogin);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridwidth = 3;
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 1;
		panelSur.add(btnLogin, gbc_btnLogin);
		
		//BOTON REGISTRAR
		JButton btnRegister = new JButton("Register");
		addManejadorBotonRegistro(btnRegister);
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegister.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegister.anchor = GridBagConstraints.NORTH;
		gbc_btnRegister.gridx = 3;
		gbc_btnRegister.gridy = 3;
		panelSur.add(btnRegister, gbc_btnRegister);
		
		//LABEL "¿AUN NO TIENE CUENTA?"
		JLabel lblanNoTienes = new JLabel("¿Aún no tienes una cuenta?");
		GridBagConstraints gbc_lblanNoTienes = new GridBagConstraints();
		gbc_lblanNoTienes.insets = new Insets(0, 0, 5, 5);
		gbc_lblanNoTienes.gridx = 1;
		gbc_lblanNoTienes.gridy = 3;
		panelSur.add(lblanNoTienes, gbc_lblanNoTienes);

	}
	
	//Funcion que llama el constructor de la ventana general, donde se crea el JFRAME y se establece el LOOK AND FIELD
	private void initialize() {
		frmLogin = new JFrame();
		
		setLandF();	//establecemos el look and feel que queremos
		
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 900, 750);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout(0, 0));
		
		creaPanelSur();
		creaPanelCentro();
		creaPanelNorte();
		
	}

}
