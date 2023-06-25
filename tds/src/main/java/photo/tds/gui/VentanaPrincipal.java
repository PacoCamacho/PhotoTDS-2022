package photo.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import photo.tds.controlador.Controlador;
import photo.tds.dao.DAOException;
import photo.tds.dominio.RepositorioUsuarios;
import photo.tds.dominio.Usuario;
import photo.tds.helpers.Item;
import photo.tds.dominio.Foto;
import photo.tds.dominio.Publicacion;
import pulsador.IEncendidoListener;
import pulsador.Luz;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

public class VentanaPrincipal implements IEncendidoListener {

	private static String usuario;
	private JFrame frmVentanaPrincipal;
	private static JPanel panelCentral;
	private static List<Object> resultadoBusqueda;
	private JTextField buscador;

	public static JPanel getPanelCentral() {
		return panelCentral;
	}

	public VentanaPrincipal(String u) {
		usuario = u;
		initialize();
	}

	public void mostrarVentana() {
		frmVentanaPrincipal.setLocationRelativeTo(null);
		frmVentanaPrincipal.setVisible(true);
	}

	public void initialize() {
		frmVentanaPrincipal = new JFrame();
		frmVentanaPrincipal.setTitle("PhotoApo- Ventana Principal");
		frmVentanaPrincipal.setSize(500, 600);
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = (JPanel) frmVentanaPrincipal.getContentPane();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout());

		JPanel panelNorte = new JPanel();
		frmVentanaPrincipal.getContentPane().add(panelNorte, BorderLayout.NORTH);

		panelCentral = new JPanel();
		frmVentanaPrincipal.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new CardLayout(0, 0));
		// panelCentral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frmVentanaPrincipal.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				panelCentral.setSize(frmVentanaPrincipal.getSize());
				panelCentral.revalidate();
			}
		});

		JLabel PhotoAppl = new JLabel("PhotoApp");
		PhotoAppl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelNorte.add(PhotoAppl);

		JPanel SubPanelNorte = new JPanel();
		panelNorte.add(SubPanelNorte);

		Luz luz = new Luz();
		luz.addEncendidoListener(this);
		SubPanelNorte.add(luz);

		JButton SubirFoto = new JButton();
		SubirFoto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/photo/tds/imagenes/subir-imagen.png")));
		SubPanelNorte.add(SubirFoto);

		buscador = new JTextField();
		SubPanelNorte.add(buscador);
		buscador.setColumns(10);

		JLabel botonBuscador = new JLabel("dfgdfg");
		botonBuscador.setIcon(
			new ImageIcon("C:\\Users\\franc\\TDS-FOTO-2023\\tds\\src\\main\\java\\photo\\tds\\imagenes\\lupa.png"));
		SubPanelNorte.add(botonBuscador);

		addManejadorBotonBuscar(botonBuscador);

		SubirFoto.addActionListener(e -> {
			VentanaNuevaPublicacion nuevaFoto = new VentanaNuevaPublicacion(usuario, true);
			nuevaFoto.mostrarVentana(frmVentanaPrincipal);
		});

		JLabel perfil = new JLabel("Perfil");
		perfil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelNorte.add(perfil);

		perfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					iniciarPanelPerfil(true, false);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				CardLayout cl = (CardLayout) panelCentral.getLayout();
				cl.show(panelCentral, "panelPerfil");
			}
		});

		PhotoAppl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCentral.removeAll();
				panelCentral.revalidate();
				iniciarPanelPublicaciones();
				CardLayout cl = (CardLayout) panelCentral.getLayout();
				cl.show(panelCentral, "panelPrincipal");
			}
		});

		iniciarPanelPublicaciones();

	}

	public static void iniciarPanelPerfil(boolean foto, boolean otroUsuario) throws DAOException {
		System.out.println("Cambio al panel de perfil");
		JPanel panelPerfil = new VentanaPerfil(usuario, usuario, foto, otroUsuario).getPanelPerfil();
		panelCentral.removeAll();
		panelCentral.add(panelPerfil, "panelPerfil");
		panelCentral.revalidate();
		panelCentral.repaint();

	}

	private void iniciarPanelPublicaciones() {
		System.out.println("Cambio al panel de publicaciones");
		JPanel panelPublicaciones = new PanelPublicaciones(usuario, frmVentanaPrincipal).getPanelPublicaciones();
		// panelPublicaciones.setBorder(BorderFactory.createLineBorder(Color.RED));
		frmVentanaPrincipal.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// Obtener el tamaño de la ventana
				Dimension size = frmVentanaPrincipal.getSize();
				// Ajustar el tamaño del panel al tamaño de la ventana
				panelPublicaciones.setSize(size);
			}
		});
		panelCentral.add(panelPublicaciones, "panelPublicaciones");

	}

	private void addManejadorBotonBuscar(JLabel boton) {
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Busqueda clickada con el campo: " + buscador.getText());

				if (!buscador.getText().isEmpty()) {
					try {
						resultadoBusqueda = Controlador.getInstancia().buscar(buscador.getText());
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					JScrollPane scrollPane = new JScrollPane();
					panelCentral.removeAll();
					panelCentral.add(scrollPane, "resultados busqueda");
					panelCentral.revalidate();
					panelCentral.repaint();

					JList<Item> list = new JList<>();
					scrollPane.setViewportView(list);
					DefaultListModel<Item> model = new DefaultListModel<>();
					list.setModel(model);
					list.setLayoutOrientation(JList.VERTICAL);

					list.setVisibleRowCount(-1);

					for (Object object : resultadoBusqueda) {
						PanelResultadoBusqueda pr = new PanelResultadoBusqueda(object);

						if (pr.esPublicacion()) {

							Foto foto = (Foto) pr.getObjeto();
							try {
								foto.setImagen(ImageIO.read(new File(foto.getPath())));
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							PanelFoto panelfoto = null;
							try {
								panelfoto = new PanelFoto(foto, foto.getUsuario(), false, true);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Item item = new Item(panelfoto, foto.getImagen());
							model.addElement(item);
							list.setCellRenderer(FotoCellListRenderer());

						} else {
							Usuario u = (Usuario) pr.getObjeto();
							Image imagenPerfil = null;
							try {
								imagenPerfil = ImageIO.read(new File(u.getFotoPerfil()));
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							VentanaPerfil panelPerfil = null;
							try {
								panelPerfil = new VentanaPerfil(u.getLogin(), u.getLogin(), true, false);
							} catch (DAOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Item item = new Item(panelPerfil, imagenPerfil);
							model.addElement(item);
							list.setCellRenderer(PerfilCellListRenderer());

						}

					}

				}

				// Crear un ScrollPane en el que se mostraran los iconos de las fotos de perfil
				// de los usuarios o las fotos encontradas por el hashtag. A esos iconos
				// añadirles
				// un actionlistener que si les haces click te diriga a un panel perfil en caso
				// de
				// usuarios o a un panelFoto en caso de hashtags.

			}
		});

	}

	private static ListCellRenderer<? super Item> FotoCellListRenderer() {
		return new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				Item item = (Item) value;
				ImageIcon icon = new ImageIcon(item.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				label.setIcon(icon);

				if (value instanceof Item) {
					if (isSelected) {
						System.out.println("Foto seleccionada");
						JPanel panelFoto = null;
						try {
							panelFoto = new PanelFoto((Foto) resultadoBusqueda.get(index),
									((Publicacion) resultadoBusqueda.get(index)).getUsuario(), false, true)
									.getPanelFoto();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JPanel panelCentralCardLayout = VentanaPrincipal.getPanelCentral();
						panelCentralCardLayout.add(panelFoto, "panel foto");
						CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
						cl.show(panelCentralCardLayout, "panel foto");
					}
				}

				return label;
			}
		};
	}

	private static ListCellRenderer<? super Item> PerfilCellListRenderer() {
		return new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				Item item = (Item) value;
				ImageIcon icon = new ImageIcon(item.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				label.setIcon(icon);

				if (value instanceof Item) {
					if (isSelected) {
						System.out.println("Foto seleccionada");
						JPanel panelPerfil = null;
						try {
							panelPerfil = new VentanaPerfil(((Usuario) resultadoBusqueda.get(index)).getLogin(),
									usuario, true, true).getPanelPerfil();
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JPanel panelCentralCardLayout = VentanaPrincipal.getPanelCentral();
						panelCentralCardLayout.add(panelPerfil, "panel foto");
						CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
						cl.show(panelCentralCardLayout, "panel foto");
					}
				}

				return label;
			}
		};
	}

	@Override
	public void enteradoCambioEncendido(EventObject arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		if (chooser.getSelectedFile() != null) {

			String fichero = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(fichero);
			Controlador.getInstancia().subirFotos(usuario, fichero);

		}

	}
}