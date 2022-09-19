package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setResizable(false);
		setTitle("Gesti\u00F3n Alumnos Access");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAlumno = new JButton("Alumno");
		btnAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miCoordinador.mostrarVentanaAlumno();
			}
		});
		btnAlumno.setBounds(299, 55, 89, 23);
		contentPane.add(btnAlumno);
		
		JButton btnMateria = new JButton("Materia");
		btnMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.mostrarVentanaMateria();
			}
		});
		btnMateria.setBounds(459, 55, 89, 23);
		contentPane.add(btnMateria);
		
		JButton btnNota = new JButton("Nota");
		btnNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.mostrarVentanaNota2();
			}
		});
		btnNota.setBounds(299, 122, 89, 23);
		contentPane.add(btnNota);
		
		JButton btnAlumnomateria = new JButton("Alumno-materia");
		btnAlumnomateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ven=2;
				int btn=1;
				int doc=0;
				String ape="";
				miCoordinador.mostrarVentanaAlumnoBuscar(btn, doc, ape,ven);
			}
		});
		btnAlumnomateria.setBounds(459, 122, 136, 23);
		contentPane.add(btnAlumnomateria);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/Is44 logo.JPG")));
		lblLogo.setBounds(10, 11, 204, 219);
		contentPane.add(lblLogo);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/Instituto frente.JPG")));
		lblFondo.setBounds(0, 0, 629, 362);
		contentPane.add(lblFondo);
		
	}
}
