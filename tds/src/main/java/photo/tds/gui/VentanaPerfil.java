package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaPerfil {
	 	private JFrame frame;

	    public VentanaPerfil() {
	        initialize();
	    }

	    public void mostrarVentana() {
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }
	    
	    public void initialize() {
	        frame = new JFrame();
	        frame.setTitle("Perfil");
	        frame.setSize(800, 600);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        JPanel panel = new JPanel();
	        frame.getContentPane().add(panel, BorderLayout.NORTH);
	        GridBagLayout gbl_panel = new GridBagLayout();
	        gbl_panel.columnWidths = new int[]{0, 0};
	        gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
	        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	        gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
	        panel.setLayout(gbl_panel);
	        
	        JLabel lblMiauuuu = new JLabel("MIAUUUU");
	        GridBagConstraints gbc_lblMiauuuu = new GridBagConstraints();
	        gbc_lblMiauuuu.insets = new Insets(0, 0, 5, 0);
	        gbc_lblMiauuuu.gridx = 0;
	        gbc_lblMiauuuu.gridy = 0;
	        panel.add(lblMiauuuu, gbc_lblMiauuuu);
	        
	        JPanel panel_1 = new JPanel();
	        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	        gbc_panel_1.anchor = GridBagConstraints.WEST;
	        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
	        gbc_panel_1.fill = GridBagConstraints.VERTICAL;
	        gbc_panel_1.gridx = 0;
	        gbc_panel_1.gridy = 1;
	        panel.add(panel_1, gbc_panel_1);
	        
	        JPanel panel_2 = new JPanel();
	        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	        gbc_panel_2.fill = GridBagConstraints.BOTH;
	        gbc_panel_2.gridx = 0;
	        gbc_panel_2.gridy = 2;
	        panel.add(panel_2, gbc_panel_2);
	        
	        JPanel panel_3 = new JPanel();
	        frame.getContentPane().add(panel_3, BorderLayout.CENTER);
	        GridBagLayout gbl_panel_3 = new GridBagLayout();
	        gbl_panel_3.columnWidths = new int[]{395, 10, 0, 0, 0};
	        gbl_panel_3.rowHeights = new int[]{10, 0, 0};
	        gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	        gbl_panel_3.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	        panel_3.setLayout(gbl_panel_3);
	    }
}
