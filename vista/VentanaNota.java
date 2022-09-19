package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoMateriaDAO;
import modelo.AlumnoMateriaVO;
import modelo.CalculaAi;
import modelo.MateriaDAO;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaNota extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbMateria;
	private DefaultComboBoxModel<String> modeloCbMateria;
	private JComboBox<String> cbAlumno;
	private DefaultComboBoxModel<String> modeloCbAlumno;
	private JComboBox cbNota;
	private Coordinador miCoordinador;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnModificar;
	private JLabel lblMatnom;
	private JLabel lblAlNombre;
	private int accion=0;
	private int numAi;

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
					VentanaNota frame = new VentanaNota();
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
	public VentanaNota() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarNota();
			}
		});
		panelSur.add(btnGuardar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarNota();
			}
		});
		panelSur.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		panelSur.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		panelSur.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarNotas();
			}
		});
		panelSur.add(btnBuscar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		panelSur.add(btnCancelar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		JLabel lblAlumno = new JLabel("Alumno:");
		lblAlumno.setBounds(10, 44, 46, 14);
		panelCentro.add(lblAlumno);
		
		cbAlumno = new JComboBox<String>();
		cbAlumno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				darNombreAlumno();
				actualizaDatos();
			}
		});
		modeloCbAlumno = new DefaultComboBoxModel<String>();
		cbAlumno.setModel(modeloCbAlumno);
		cbAlumno.setBounds(81, 41, 148, 20);
		panelCentro.add(cbAlumno);
		
		JLabel lblMateria = new JLabel("Materia:");
		lblMateria.setBounds(266, 44, 46, 14);
		panelCentro.add(lblMateria);
		
		cbMateria = new JComboBox<String>();
		cbMateria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				darNombreMateria();
				actualizaDatos();
			}
		});
		modeloCbMateria = new DefaultComboBoxModel<String>();
		cbMateria.setModel(modeloCbMateria);
		cbMateria.setBounds(341, 41, 162, 20);
		panelCentro.add(cbMateria);
		
		JLabel lblNota = new JLabel("Nota:");
		lblNota.setBounds(131, 116, 46, 14);
		panelCentro.add(lblNota);
		
		cbNota = new JComboBox();
		cbNota.setForeground(Color.BLACK);
		cbNota.setBackground(Color.WHITE);
		cbNota.setEditable(true);
		cbNota.setModel(new DefaultComboBoxModel(new String[] {"", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"}));
		cbNota.setBounds(203, 113, 60, 20);
		//UIManager.put(cbNota, new Color(212,212,210));
		//UIManager.put( "cbNota", Color.BLACK );
		UIManager.put( "ComboBox.disabledBackground", new Color(212,212,210) );
		UIManager.put( "ComboBox.disabledForeground", Color.BLACK );
		panelCentro.add(cbNota);
		
		lblMatnom = new JLabel("");
		lblMatnom.setBounds(341, 83, 230, 14);
		panelCentro.add(lblMatnom);
		
		lblAlNombre = new JLabel("");
		lblAlNombre.setBounds(10, 83, 219, 14);
		panelCentro.add(lblAlNombre);
		
		//cargarComboAlumnos(modeloCbAlumno);
		//cargarComboMaterias(modeloCbMateria);
		//darNombreAlumno();
		//darNombreMateria();
		//limpiar();
	}

	protected void actualizaDatos() {
		int doc = Integer.valueOf(cbAlumno.getSelectedItem().toString());
		String codigo = (String) cbMateria.getSelectedItem();
		AlumnoMateriaDAO miAlumnoMateriaDAO = new AlumnoMateriaDAO();
		int nota=miAlumnoMateriaDAO.actualizaDatos(doc,codigo);
	
		if (nota==99)
			cbNota.setSelectedIndex(0);
		else
			cbNota.setSelectedItem(String.valueOf(nota));
	}

	protected void buscarNotas() {
		int doc = Integer.valueOf(cbAlumno.getSelectedItem().toString());
		miCoordinador.mostrarVentanaNotaBuscar(doc);
		
	}

	protected void guardarNota() {
		try{
			AlumnoMateriaVO miAluMateriaVO = new AlumnoMateriaVO();
			miAluMateriaVO.setAlmat(numAi);
			miAluMateriaVO.setAldni(Integer.valueOf(cbAlumno.getSelectedItem().toString()));
			miAluMateriaVO.setCodmat(cbMateria.getSelectedItem().toString());
			miAluMateriaVO.setNota(Integer.valueOf(cbNota.getSelectedItem().toString()));
			
			if (accion==1)
				miCoordinador.registrarNota(miAluMateriaVO);
				limpiar();
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error en el ingreso de datos","Error",
					JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
	}

	protected void agregarNota() {
		CalculaAi cai = new CalculaAi();
		numAi=cai.calculaIdAlumnoMateria();
		accion=1;
		habilita(true, true, true, true, false, false, false, false, true);
	}

	private void darNombreAlumno() {
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		int doc = Integer.valueOf(cbAlumno.getSelectedItem().toString());
		lblAlNombre.setText(miAlumnoDAO.darNombre(doc));
	}

	private void darNombreMateria() {
		MateriaDAO miMateriaDAO = new MateriaDAO();
		String codigo = cbMateria.getSelectedItem().toString();
		lblMatnom.setText(miMateriaDAO.darNombre(codigo));
	}

	private void cargarComboAlumnos(DefaultComboBoxModel<String> modeloCbAlumno2) {
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.cargarComboAlumnos(modeloCbAlumno);
	}

	private void cargarComboMaterias(DefaultComboBoxModel<String> modeloCbMateria) {
		MateriaDAO miMateriaDAO = new MateriaDAO();
		miMateriaDAO.cargarComboMaterias(modeloCbMateria);
	}
	
	public void habilita(boolean cbDni,boolean cbNombre,boolean cbNotas,boolean bGuardar,
			boolean bAgregar, boolean bModificar,boolean bEliminar,boolean bBuscar,boolean bCancelar){
		cbAlumno.setEnabled(cbDni);
		cbMateria.setEnabled(cbNombre);
		cbNota.setEnabled(cbNotas);

		btnGuardar.setVisible(bGuardar);
		btnAgregar.setEnabled(bAgregar);
		btnModificar.setEnabled(bModificar);
		btnEliminar.setEnabled(bEliminar);
		btnBuscar.setEnabled(bBuscar);
		btnCancelar.setEnabled(bCancelar);
	}
	private void limpiar() {
		cbAlumno.setSelectedIndex(0);
		cbMateria.setSelectedIndex(0);
		cbNota.setSelectedIndex(0);
		
		habilita(true, true, false, false, true, false, false, true, true);
	}
}
