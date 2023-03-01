package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import photo.tds.controlador.Controlador;
import photo.tds.dominio.RepositorioUsuarios;

public class VentanaPrincipal {

    private JFrame frmVentanaPrincipal;
    private JPanel postsPanel;

    public VentanaPrincipal() {
        initialize();
    }

    public void mostrarVentana() {
        frmVentanaPrincipal.setLocationRelativeTo(null);
        frmVentanaPrincipal.setVisible(true);
    }
    
    public void initialize() {
        frmVentanaPrincipal = new JFrame();
        frmVentanaPrincipal.setTitle("PhotoTDS");
        frmVentanaPrincipal.setSize(800, 1000);
        frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = (JPanel) frmVentanaPrincipal.getContentPane();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());

        // Panel de la parte superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());

        // Etiqueta con el nombre de la aplicación
        JLabel lblBienvenidoAPhototds = new JLabel("Bienvenido a PhotoTDS, " + Controlador.INSTANCE.getUsuarioActual().getNombre());
        lblBienvenidoAPhototds.setHorizontalAlignment(SwingConstants.LEFT);
        panelSuperior.add(lblBienvenidoAPhototds, BorderLayout.WEST);

		/*
		 * // Botón para añadir fotos JButton btnAdd = new JButton("Añadir fotos");
		 * panelSuperior.add(btnAdd, BorderLayout.WEST);
		 */

     // Etiqueta de búsqueda
        JTextField textField = new JTextField(20);
        Dimension maxTextSize = new Dimension(10,10); // máximo 200px de ancho
        textField.setMaximumSize(maxTextSize);
        panelSuperior.add(textField, BorderLayout.CENTER);

     // Botón para añadir fotos
        JButton btnAdd = new JButton("Añadir fotos");
        Dimension maxButtonSize = new Dimension(10,10); // máximo 150px de ancho
        btnAdd.setMaximumSize(new Dimension(1, 1));
        panelSuperior.add(btnAdd, BorderLayout.NORTH);

        // Menú desplegable
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem menuItem1 = new JMenuItem("Perfil");
        JMenuItem menuItem2 = new JMenuItem("Configuración");
        JMenuItem menuItem3 = new JMenuItem("Cerrar sesión");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.addSeparator();
        menu.add(menuItem3);
        menuBar.add(menu);
        panelSuperior.add(menuBar, BorderLayout.SOUTH);

        contentPane.add(panelSuperior, BorderLayout.NORTH);

        // Panel para las publicaciones
        JPanel panelPublicaciones = new JPanel(new GridLayout(0, 1));

        // Agregar publicaciones al panelPublicaciones

        JScrollPane scrollPane = new JScrollPane(panelPublicaciones);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }
}
