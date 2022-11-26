package photo.tds.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;

public class VentanaRegistro extends JDialog{

	private JFrame frameRegistro;
	private JTextField textFieldEmail;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldFechaNac;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro window = new VentanaRegistro();
					window.frameRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	public VentanaRegistro() {
		initialize();
	}
	
	public void mostrarVentana() {
		frameRegistro.setLocationRelativeTo(null);
		frameRegistro.setVisible(true);
	}
	
	
	//Manejadores botones
	
	//Boton cancelar
	private void addManejadorCancelar(JButton btnCancelar){
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin ventanaLogin = new VentanaLogin();
				ventanaLogin.mostrarVentana();
				frameRegistro.dispose();
			}
		});
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameRegistro = new JFrame();
		frameRegistro.setBounds(100, 100, 1089, 708);
		frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorte = new JPanel();
		frameRegistro.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\franc\\Documents\\imagenes TDS\\PhotoTDS_logo.png"));
		panelNorte.add(lblNewLabel);
		
		JPanel panelCentro = new JPanel();
		frameRegistro.getContentPane().add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{0, 963, 0, 0};
		gbl_panelCentro.rowHeights = new int[]{0, 243, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);
		
		JPanel panelDatosPersonales = new JPanel();
		panelDatosPersonales.setBorder(new TitledBorder(null, "Datos personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDatosPersonales = new GridBagConstraints();
		gbc_panelDatosPersonales.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatosPersonales.fill = GridBagConstraints.BOTH;
		gbc_panelDatosPersonales.gridx = 1;
		gbc_panelDatosPersonales.gridy = 1;
		panelCentro.add(panelDatosPersonales, gbc_panelDatosPersonales);
		GridBagLayout gbl_panelDatosPersonales = new GridBagLayout();
		gbl_panelDatosPersonales.columnWidths = new int[]{132, 30, 500, 0, 0};
		gbl_panelDatosPersonales.rowHeights = new int[]{44, 46, 44, 39, 0};
		gbl_panelDatosPersonales.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelDatosPersonales.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatosPersonales.setLayout(gbl_panelDatosPersonales);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 0;
		panelDatosPersonales.add(lblEmail, gbc_lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 2;
		gbc_textFieldEmail.gridy = 0;
		panelDatosPersonales.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panelDatosPersonales.add(lblNombre, gbc_lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 1;
		panelDatosPersonales.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabelApellidos = new JLabel("Apellidos:");
		GridBagConstraints gbc_lblNewLabelApellidos = new GridBagConstraints();
		gbc_lblNewLabelApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabelApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelApellidos.gridx = 1;
		gbc_lblNewLabelApellidos.gridy = 2;
		panelDatosPersonales.add(lblNewLabelApellidos, gbc_lblNewLabelApellidos);
		lblNewLabelApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textFieldApellidos = new JTextField();
		GridBagConstraints gbc_textFieldApellidos = new GridBagConstraints();
		gbc_textFieldApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidos.gridx = 2;
		gbc_textFieldApellidos.gridy = 2;
		panelDatosPersonales.add(textFieldApellidos, gbc_textFieldApellidos);
		textFieldApellidos.setColumns(10);
		
		JLabel lblNewLabelFechaNac = new JLabel("Fecha Nacimiento:");
		lblNewLabelFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabelFechaNac = new GridBagConstraints();
		gbc_lblNewLabelFechaNac.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabelFechaNac.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabelFechaNac.gridx = 1;
		gbc_lblNewLabelFechaNac.gridy = 3;
		panelDatosPersonales.add(lblNewLabelFechaNac, gbc_lblNewLabelFechaNac);
		
		textFieldFechaNac = new JTextField();
		GridBagConstraints gbc_textFieldFechaNac = new GridBagConstraints();
		gbc_textFieldFechaNac.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldFechaNac.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaNac.gridx = 2;
		gbc_textFieldFechaNac.gridy = 3;
		panelDatosPersonales.add(textFieldFechaNac, gbc_textFieldFechaNac);
		textFieldFechaNac.setColumns(10);
		
		JPanel panelDatosAplicacion = new JPanel();
		panelDatosAplicacion.setBorder(new TitledBorder(null, "Datos Aplicaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDatosAplicacion = new GridBagConstraints();
		gbc_panelDatosAplicacion.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatosAplicacion.fill = GridBagConstraints.BOTH;
		gbc_panelDatosAplicacion.gridx = 1;
		gbc_panelDatosAplicacion.gridy = 2;
		panelCentro.add(panelDatosAplicacion, gbc_panelDatosAplicacion);
		GridBagLayout gbl_panelDatosAplicacion = new GridBagLayout();
		gbl_panelDatosAplicacion.columnWidths = new int[]{136, 0, 524, 0, 0};
		gbl_panelDatosAplicacion.rowHeights = new int[]{62, 54, 45, 0};
		gbl_panelDatosAplicacion.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelDatosAplicacion.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatosAplicacion.setLayout(gbl_panelDatosAplicacion);
		
		JLabel lblNewLabelUsuario = new JLabel("Nombre Usuario:");
		lblNewLabelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabelUsuario = new GridBagConstraints();
		gbc_lblNewLabelUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabelUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelUsuario.gridx = 1;
		gbc_lblNewLabelUsuario.gridy = 0;
		panelDatosAplicacion.add(lblNewLabelUsuario, gbc_lblNewLabelUsuario);
		
		textFieldUsuario = new JTextField();
		GridBagConstraints gbc_textFieldUsuario = new GridBagConstraints();
		gbc_textFieldUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsuario.gridx = 2;
		gbc_textFieldUsuario.gridy = 0;
		panelDatosAplicacion.add(textFieldUsuario, gbc_textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblNewLabelContraseña = new JLabel("Contraseña:\r\n");
		lblNewLabelContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabelContraseña = new GridBagConstraints();
		gbc_lblNewLabelContraseña.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabelContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelContraseña.gridx = 1;
		gbc_lblNewLabelContraseña.gridy = 1;
		panelDatosAplicacion.add(lblNewLabelContraseña, gbc_lblNewLabelContraseña);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 1;
		panelDatosAplicacion.add(passwordField, gbc_passwordField);
		
		JLabel lblNewLabelContraseñaRep = new JLabel("Repetir Contraseña:");
		lblNewLabelContraseñaRep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabelContraseñaRep = new GridBagConstraints();
		gbc_lblNewLabelContraseñaRep.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabelContraseñaRep.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabelContraseñaRep.gridx = 1;
		gbc_lblNewLabelContraseñaRep.gridy = 2;
		panelDatosAplicacion.add(lblNewLabelContraseñaRep, gbc_lblNewLabelContraseñaRep);
		
		passwordFieldRep = new JPasswordField();
		GridBagConstraints gbc_passwordFieldRep = new GridBagConstraints();
		gbc_passwordFieldRep.insets = new Insets(0, 0, 0, 5);
		gbc_passwordFieldRep.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRep.gridx = 2;
		gbc_passwordFieldRep.gridy = 2;
		panelDatosAplicacion.add(passwordFieldRep, gbc_passwordFieldRep);
		
		JPanel panelSur = new JPanel();
		frameRegistro.getContentPane().add(panelSur, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSur = new GridBagLayout();
		gbl_panelSur.columnWidths = new int[]{418, 0, 0, 129, 0, 0};
		gbl_panelSur.rowHeights = new int[]{0, 0, 0};
		gbl_panelSur.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelSur.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelSur.setLayout(gbl_panelSur);
		
		JButton btnRegistro = new JButton("Registrarse");
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistro.gridx = 1;
		gbc_btnRegistro.gridy = 1;
		panelSur.add(btnRegistro, gbc_btnRegistro);
		
		JButton btnCancelar = new JButton("Cancelar");
		addManejadorCancelar(btnCancelar);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 1;
		panelSur.add(btnCancelar, gbc_btnCancelar);
	}

}
