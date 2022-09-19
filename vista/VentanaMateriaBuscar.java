package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoVO;
import modelo.MateriaDAO;
import modelo.MateriaVO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMateriaBuscar extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JTable tabla;
	private JScrollPane scrollPane;

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
					VentanaMateriaBuscar frame = new VentanaMateriaBuscar();
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
	public VentanaMateriaBuscar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panelSur.add(btnVolver);
		
		 scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public void mostrarDatosConTableModel(int btn, String cod, String nom) {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				miCoordinador.pasarDatosMateria(pasarDatosMateria(e));
			}
		});
		
		tabla.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Módulos");
		
		
		MateriaDAO miMateriaDAO = new MateriaDAO();
		
		if (btn==1)
			miMateriaDAO.buscarMaterias(modelo);
		if (btn==2)
			miMateriaDAO.buscarParcialMateriaNombre(modelo,nom);
		if (btn==3)
			miMateriaDAO.buscarParcialMateriaCodigo(modelo,cod);
		
			scrollPane.setViewportView(tabla);
	}

	protected MateriaVO pasarDatosMateria(MouseEvent e) {
		MateriaVO miMateriaVO = new MateriaVO();
		
		int row=tabla.rowAtPoint(e.getPoint());
		miMateriaVO.setCodmat(tabla.getValueAt(row, 0).toString());
		miMateriaVO.setMatnom(tabla.getValueAt(row, 1).toString());
		miMateriaVO.setModulos(Integer.valueOf(tabla.getValueAt(row, 2).toString()));
		
		return miMateriaVO;
	}
}
