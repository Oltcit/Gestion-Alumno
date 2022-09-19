package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoVO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAlumnoBuscar extends JFrame {

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
					VentanaAlumnoBuscar frame = new VentanaAlumnoBuscar();
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
	public VentanaAlumnoBuscar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 300);
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

	public void mostrarDatosConTableModel(int btn, int doc, String ape, int ven) {
		
		
		DefaultTableModel modelo = new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"DNI", "Apellido y nombre", "Fecha de Nac.", "Documentación"})
		{
		Class[] columnTypes = new Class[] {
			Integer.class, String.class, String.class, Boolean.class
		};
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
		boolean[] columnEditables = new boolean[] {
			false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	tabla = new JTable();
	tabla.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e){
			if (ven==1)
				miCoordinador.pasarDatosAlumno(pasarDatosAlumno(e));
			else
				miCoordinador.pasarDatosVentanaAlumnoMateriaNueva(pasarDatosAlumno(e));
		}
	});
	
	tabla.setModel(modelo);
	
	// especifica cual es la columna que va a tener el renderizado (el checkBox)
	
			agregarCheck(tabla.getColumnModel().getColumn(3));
		//////////////////////////////////////////////////////
	
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		if (btn==1)
			miAlumnoDAO.buscarAlumnos(modelo);
		if (btn==2)
			miAlumnoDAO.buscarParcialAlumnoApellido(modelo,ape);
		if (btn==3)
			miAlumnoDAO.buscarParcialAlumnoDni(modelo,doc);
		
		scrollPane.setViewportView(tabla);
	}

	private void agregarCheck(TableColumn columna) {
		JCheckBox chkDoc = new JCheckBox();
		columna.setCellEditor(new DefaultCellEditor(chkDoc));
		
	}
	
	protected AlumnoVO pasarDatosAlumno(MouseEvent e) {
		AlumnoVO miAlumnoVO = new AlumnoVO();
		int row=tabla.rowAtPoint(e.getPoint());
		miAlumnoVO.setAldni(Integer.valueOf(tabla.getValueAt(row, 0).toString()));
		miAlumnoVO.setAlapynom(tabla.getValueAt(row, 1).toString());
		miAlumnoVO.setAlfnac(tabla.getValueAt(row, 2).toString());
		
		String estado = tabla.getValueAt(row, 3).toString();
		if (estado.equals("false")){
			miAlumnoVO.setAldoc((byte) 0);
		}else{
			miAlumnoVO.setAldoc((byte) 1);
		}
		// para access
		/*int estado=Integer.valueOf(tabla.getValueAt(row, 3).toString());
		if (estado ==0){
			miAlumnoVO.setAldoc((byte) 0);
		}else{
			miAlumnoVO.setAldoc((byte) 1);
		}*/
		
		return miAlumnoVO;
	}
}
