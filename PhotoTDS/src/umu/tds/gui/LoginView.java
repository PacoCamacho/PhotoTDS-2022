package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import umu.tds.controlador.Controlador;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LoginView {

	private JFrame frmLogin;
	private JTextField textUsuario;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	public void mostrarVentana() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}
	
	/********************************************************************** 
	 * Procurar organizar la creación de una ventana en varios métodos
	 * con el fin de facilitar su comprensión. Esta clase muestra un ejemplo
	 **********************************************************************/
	
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setForeground(new Color(0, 0, 160));
		frmLogin.setBackground(new Color(0, 0, 160));
		frmLogin.setTitle("Login PhotoTDS\r\n");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout());

		crearPanelTitulo();
		crearPanelLogin();
		frmLogin.pack();
	}

	private void crearPanelTitulo() {
		JPanel panel_Norte = new JPanel();
		frmLogin.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

		JLabel lblTitulo = new JLabel("");
		lblTitulo.setIcon(new ImageIcon("C:\\Users\\franc\\Documents\\imagenes TDS\\PhotoTDS_logo.png"));
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setForeground(Color.DARK_GRAY);
		panel_Norte.add(lblTitulo);
	}

	private void crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmLogin.getContentPane().add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new BorderLayout(0, 0));

		panelLogin.add(crearPanelUsuarioPassw(), BorderLayout.NORTH);
		panelLogin.add(crearPanelBotones(), BorderLayout.SOUTH);
	}

	private JPanel crearPanelUsuarioPassw() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelCampos = new GridBagLayout();
		gbl_panelCampos.columnWidths = new int[]{77, 414, 83, 0};
		gbl_panelCampos.rowHeights = new int[]{61, 0};
		gbl_panelCampos.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCampos.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelCampos.setLayout(gbl_panelCampos);
								
										// Panel Campo Login
										JPanel panelCampoUsuarioContraseña = new JPanel();
										GridBagConstraints gbc_panelCampoUsuarioContraseña = new GridBagConstraints();
										gbc_panelCampoUsuarioContraseña.gridwidth = 3;
										gbc_panelCampoUsuarioContraseña.insets = new Insets(0, 0, 0, 5);
										gbc_panelCampoUsuarioContraseña.gridx = 0;
										gbc_panelCampoUsuarioContraseña.gridy = 0;
										panelCampos.add(panelCampoUsuarioContraseña, gbc_panelCampoUsuarioContraseña);
										GridBagLayout gbl_panelCampoUsuarioContraseña = new GridBagLayout();
										gbl_panelCampoUsuarioContraseña.columnWidths = new int[]{0, 136, 236, 0, 0};
										gbl_panelCampoUsuarioContraseña.rowHeights = new int[]{0, 24, 0};
										gbl_panelCampoUsuarioContraseña.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
										gbl_panelCampoUsuarioContraseña.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
										panelCampoUsuarioContraseña.setLayout(gbl_panelCampoUsuarioContraseña);
										
												JLabel lblUsuario = new JLabel("Usuario: ");
												lblUsuario.setIcon(new ImageIcon("C:\\Users\\franc\\Documents\\imagenes TDS\\Persona.png"));
												GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
												gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
												gbc_lblUsuario.anchor = GridBagConstraints.NORTHEAST;
												gbc_lblUsuario.gridx = 1;
												gbc_lblUsuario.gridy = 0;
												panelCampoUsuarioContraseña.add(lblUsuario, gbc_lblUsuario);
												lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
												lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
												
														textUsuario = new JTextField();
														GridBagConstraints gbc_textUsuario = new GridBagConstraints();
														gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
														gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
														gbc_textUsuario.anchor = GridBagConstraints.NORTH;
														gbc_textUsuario.gridx = 2;
														gbc_textUsuario.gridy = 0;
														panelCampoUsuarioContraseña.add(textUsuario, gbc_textUsuario);
														textUsuario.setColumns(15);
														
														JLabel lblNewLabel = new JLabel("Contraseña:");
														lblNewLabel.setIcon(new ImageIcon("C:\\Users\\franc\\Documents\\imagenes TDS\\candado.png"));
														lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
														GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
														gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
														gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
														gbc_lblNewLabel.gridx = 1;
														gbc_lblNewLabel.gridy = 1;
														panelCampoUsuarioContraseña.add(lblNewLabel, gbc_lblNewLabel);
														
														passwordField = new JPasswordField();
														GridBagConstraints gbc_passwordField = new GridBagConstraints();
														gbc_passwordField.insets = new Insets(0, 0, 0, 5);
														gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
														gbc_passwordField.gridx = 2;
														gbc_passwordField.gridy = 1;
														panelCampoUsuarioContraseña.add(passwordField, gbc_passwordField);
		
		return panelCampos;
	}

	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new EmptyBorder(5, 0, 5, 0));
		panelBotones.setLayout(new BorderLayout(0, 0));

		JPanel panelBotonesLoginRegistro = new JPanel();
		panelBotones.add(panelBotonesLoginRegistro, BorderLayout.CENTER);
		GridBagLayout gbl_panelBotonesLoginRegistro = new GridBagLayout();
		gbl_panelBotonesLoginRegistro.columnWidths = new int[]{118, 57, 85, 0, 0};
		gbl_panelBotonesLoginRegistro.rowHeights = new int[]{21, 0};
		gbl_panelBotonesLoginRegistro.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelBotonesLoginRegistro.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelBotonesLoginRegistro.setLayout(gbl_panelBotonesLoginRegistro);
		
				JButton btnLogin = new JButton("Login");
				btnLogin.setForeground(new Color(0, 0, 160));
				btnLogin.setBackground(new Color(0, 0, 160));
				btnLogin.setFont(new Font("Segoe Script", Font.PLAIN, 12));
				GridBagConstraints gbc_btnLogin = new GridBagConstraints();
				gbc_btnLogin.anchor = GridBagConstraints.NORTH;
				gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
				gbc_btnLogin.gridx = 1;
				gbc_btnLogin.gridy = 0;
				panelBotonesLoginRegistro.add(btnLogin, gbc_btnLogin);
				
						addManejadorBotonLogin(btnLogin);
		
				JButton btnRegistro = new JButton("Registro");
				btnRegistro.setBackground(new Color(0, 0, 160));
				btnRegistro.setForeground(new Color(0, 0, 160));
				btnRegistro.setFont(new Font("Segoe Script", Font.PLAIN, 12));
				GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
				gbc_btnRegistro.insets = new Insets(0, 0, 0, 5);
				gbc_btnRegistro.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnRegistro.gridx = 2;
				gbc_btnRegistro.gridy = 0;
				panelBotonesLoginRegistro.add(btnRegistro, gbc_btnRegistro);
				addManejadorBotonRegistro(btnRegistro);
				
						JButton btnSalir = new JButton("Salir");
						btnSalir.setBackground(new Color(0, 0, 160));
						btnSalir.setForeground(new Color(0, 0, 160));
						btnSalir.setFont(new Font("Segoe Script", Font.PLAIN, 12));
						GridBagConstraints gbc_btnSalir = new GridBagConstraints();
						gbc_btnSalir.anchor = GridBagConstraints.EAST;
						gbc_btnSalir.gridx = 3;
						gbc_btnSalir.gridy = 0;
						panelBotonesLoginRegistro.add(btnSalir, gbc_btnSalir);
		addManejadorBotonSalir(btnSalir);
		
		return panelBotones;
	}

	private void addManejadorBotonSalir(JButton btnSalir) {
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				System.exit(0);
			}
		});
	}

	private void addManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroView registro = new RegistroView (frmLogin);
				registro.setLocationRelativeTo(frmLogin);                              	
				registro.setVisible(true);
				frmLogin.dispose();
			}
		});
	}

	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.INSTANCE.loginUsuario(
						textUsuario.getText(),
						new String(textPassword.getPassword()));

				if (login) {
					VentanaPrincipal principal = new VentanaPrincipal();
					principal.mostrarVentana();
					frmLogin.dispose();
				} else
					JOptionPane.showMessageDialog(frmLogin, "Nombre de usuario o contraseña no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

}
