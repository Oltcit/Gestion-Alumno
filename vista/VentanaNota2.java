package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoMateriaDAO;
import modelo.AlumnoMateriaVO;
import modelo.AlumnoVO;
import modelo.CalculaAi;
import modelo.MateriaDAO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaNota2 extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JComboBox<String> cbAlumno;
	private DefaultComboBoxModel<String> modeloCbAlumno;
	private JLabel lblNombre;
	private JComboBox <String> cbMateria;
	private DefaultComboBoxModel<String> modeloCbMateria;
	private JComboBox cbNota;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnBuscar;
	private JButton btnGuardar;
	private JLabel lblNomMat;
	private int numAi;
	private int accion;

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
					VentanaNota2 frame = new VentanaNota2();
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
	public VentanaNota2() {
		setTitle("Notas ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 603, 377);
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
			}
		});
		panelSur.add(btnBuscar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		panelSur.add(btnCancelar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		JLabel lblAlumno = new JLabel("Alumno");
		lblAlumno.setBounds(30, 65, 46, 14);
		panelCentro.add(lblAlumno);
		
		cbAlumno = new JComboBox<String>();
		cbAlumno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				darNombreAlumno();
				actualizaDatos();
			}
		});
		cbAlumno.setBounds(93, 62, 107, 20);
		modeloCbAlumno = new DefaultComboBoxModel<String>();
		cbAlumno.setModel(modeloCbAlumno);
		panelCentro.add(cbAlumno);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setBounds(318, 65, 46, 14);
		panelCentro.add(lblMateria);
		
		cbMateria = new JComboBox();
		cbMateria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				darNombreMateria();
				actualizaDatos();
			}
		});
		cbMateria.setBounds(374, 62, 107, 20);
		modeloCbMateria = new DefaultComboBoxModel<String>();
		cbMateria.setModel(modeloCbMateria);
		panelCentro.add(cbMateria);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(179, 153, 46, 14);
		panelCentro.add(lblNota);
		
		cbNota = new JComboBox();
		cbNota.setModel(new DefaultComboBoxModel(new String[] {"", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"}));
		cbNota.setBounds(235, 150, 46, 20);
		panelCentro.add(cbNota);
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(30, 112, 251, 14);
		panelCentro.add(lblNombre);
		
		lblNomMat = new JLabel("");
		lblNomMat.setBounds(351, 112, 117, 14);
		panelCentro.add(lblNomMat);
		
		cargarComboAlumnos(modeloCbAlumno);
		cargarComboMaterias(modeloCbMateria);
		actualizaDatos();
		limpiar();
	}

	protected void actualizaDatos() {
		int doc= Integer.valueOf(cbAlumno.getSelectedItem().toString());
		String codigo= (String) cbMateria.getSelectedItem();
		AlumnoMateriaDAO miAlumnoMateriaDAO = new AlumnoMateriaDAO();
		int nota= miAlumnoMateriaDAO.actualizaDatos(doc, codigo);
		
		if (nota>=0 && nota <= 10)
			cbNota.setSelectedItem(String.valueOf(nota));
		else
				cbNota.setSelectedIndex(0);
			
	}

	protected void guardarNota() {
		try{
			CalculaAi cai= new CalculaAi();
			numAi = cai.calculaIdAlumnoMateria();
			AlumnoMateriaVO miAlumnoMateriaVO = new AlumnoMateriaVO();
			miAlumnoMateriaVO.setAlmat(numAi);
			miAlumnoMateriaVO.setAldni(Integer.valueOf(cbAlumno.getSelectedItem().toString()));
			miAlumnoMateriaVO.setCodmat(cbMateria.getSelectedItem().toString());
			miAlumnoMateriaVO.setNota(Integer.valueOf(cbNota.getSelectedItem().toString()));
			
			if (accion==1){
				AlumnoMateriaDAO miAlumnoMateriaDAO = new AlumnoMateriaDAO();
				miAlumnoMateriaDAO.registrarNota(miAlumnoMateriaVO);
			}
			limpiar();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error en el ingreso de datos", "Error",
					JOptionPane.ERROR_MESSAGE);;
		}
	}

	protected void agregarNota() {	
		accion = 1;
		habilita(true, true, true, true, false, false, false, false, true);
	}

	protected void darNombreMateria() {
		MateriaDAO miMateriaDAO= new MateriaDAO();
		String codigo= cbMateria.getSelectedItem().toString();
		lblNomMat.setText(miMateriaDAO.darNombre(codigo));
	}

	private void cargarComboMaterias(DefaultComboBoxModel<String> modeloCbMateria2) {
		MateriaDAO miMateriaDAO = new MateriaDAO();
		miMateriaDAO.cargarComboMaterias(modeloCbMateria);
		
	}

	protected void darNombreAlumno() {
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		int doc = Integer.valueOf(cbAlumno.getSelectedItem().toString());
		lblNombre.setText(miAlumnoDAO.traerNombre(doc));
	}

	private void cargarComboAlumnos(DefaultComboBoxModel<String> modeloCbAlumno) {
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.cargarComboAlumnos2(modeloCbAlumno);		
	}

	public void habilita(boolean cbDni, boolean cbMat, boolean cbnota, boolean bGuardar, 
			boolean bAgregar, boolean bModif, boolean bEli, boolean bBuscar,
			boolean bCancelar){
		
		cbAlumno.setEnabled(cbDni);
		cbMateria.setEnabled(cbMat);
		cbNota.setEnabled(cbnota);
		btnGuardar.setVisible(bGuardar);
		btnAgregar.setEnabled(bAgregar);
		btnModificar.setEnabled(bModif);
		btnEliminar.setEnabled(bEli);
		btnBuscar.setEnabled(bBuscar);
		btnCancelar.setEnabled(bCancelar);
	}
	
	private void limpiar(){
		cbAlumno.setSelectedIndex(0);
		cbMateria.setSelectedIndex(0);
		cbNota.setSelectedIndex(0);
		
		habilita(true, true, false, false, true, false, false, true, true);
	}
	public JLabel getLblNomMat() {
		return lblNomMat;
	}
}
