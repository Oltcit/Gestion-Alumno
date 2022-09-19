package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoMateriaDAO;
import modelo.AlumnoMateriaVO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaNotaBuscar extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JTable tabla;
	private JScrollPane scrollPane;

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
					VentanaNotaBuscar frame = new VentanaNotaBuscar();
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
	public VentanaNotaBuscar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnVolver);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public void mostrarDatosContableModel(int doc) {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla = new JTable();
		
		
		tabla.setModel(modelo);
		modelo.addColumn("Materia");
		modelo.addColumn("Nota");
		
		AlumnoMateriaDAO miAlumnoMateriaDAO = new AlumnoMateriaDAO();
		miAlumnoMateriaDAO.buscarNotas(modelo,doc);
		
		scrollPane.setViewportView(tabla);
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();	
		setTitle(miAlumnoDAO.darNombre(doc));
	}

}
