package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import controlador.Coordinador;
import modelo.AlumnoVO;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class VentanaAlumno extends JFrame {

	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtApyNom;
	private Coordinador miCoordinador;
	private int accion;
	private JDateChooser selectorFecha;
	private JCheckBox chkDoc;
	private JButton btnBuscarXDni;
	private JButton btnBuscarXApellido;
	private JButton btnGuardar;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnCancelar;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * Create the frame.
	 */
	public VentanaAlumno() {
		setTitle("Alumno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 549, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarAlumno();
			}
		});
		panelSur.add(btnGuardar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion=1;
				agregarAlumno();
			}
		});
		panelSur.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarAlumno();
			}
		});
		panelSur.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarAlumno();
			}
		});
		panelSur.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarAlumno();
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
		panelCentro.setLayout(new GridLayout(0, 3, 5, 5));
		
		JLabel lblDni = new JLabel("DNI:");
		panelCentro.add(lblDni);
		
		txtDni = new JTextField();
		panelCentro.add(txtDni);
		txtDni.setColumns(10);
		
		btnBuscarXDni = new JButton("Buscar x Dni");
		btnBuscarXDni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busquedaParcialDni();
			}
		});
		panelCentro.add(btnBuscarXDni);
		
		JLabel lblApellidoYNombre = new JLabel("Apellido y nombre:");
		panelCentro.add(lblApellidoYNombre);
		
		txtApyNom = new JTextField();
		panelCentro.add(txtApyNom);
		txtApyNom.setColumns(10);
		
		btnBuscarXApellido = new JButton("Buscar x Apellido");
		btnBuscarXApellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busquedaParcialAlumnoApellido();
			}
		});
		panelCentro.add(btnBuscarXApellido);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		panelCentro.add(lblFechaDeNacimiento);
		
		selectorFecha = new JDateChooser();
		panelCentro.add(selectorFecha);
		
		JLabel label = new JLabel("");
		panelCentro.add(label);
		
		chkDoc = new JCheckBox("Documentaci\u00F3n");
		panelCentro.add(chkDoc);
		
		limpiar();
	}

	protected void busquedaParcialAlumnoApellido() {
		int ven=1;
		int btn=2;
		String ape=txtApyNom.getText();
		int doc=0;
		miCoordinador.mostrarVentanaAlumnoBuscar(btn,doc,ape,ven);
	}

	protected void busquedaParcialDni() {
		int ven=1;
		int btn=3;
		int doc;
		String ape="";
		if (txtDni.getText().isEmpty())
		{
			doc=0;
			JOptionPane.showMessageDialog(null, "<html>Ingrese los primeros números del"
					+ "documento <br> o el número completo</html>","Información",
					JOptionPane.WARNING_MESSAGE);
		}else{
			try{
				doc=Integer.valueOf(txtDni.getText().trim());
				miCoordinador.mostrarVentanaAlumnoBuscar(btn,doc, ape,ven);
			}catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "<html>Ingrese los primeros números del"
					+ "documento <br> sin espacios o letras</html>","Información",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void eliminarAlumno() {
		if (!txtDni.getText().equals(""))
		{
			int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de eliminar"
					+ " ese Alumno?","Confirmación",JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_NO_OPTION)
			{
				miCoordinador.eliminarAlumno(Integer.valueOf(txtDni.getText()));
				limpiar();
			}
		}else
			JOptionPane.showMessageDialog(null, "Ingrese un número de documento","Información"
					,JOptionPane.WARNING_MESSAGE);
	}

	protected void modificarAlumno() {
		accion=2;
		habilita(false, true, true, true, false, false, true, false, false, false, false, true);
	}

	/**
	 * Muestra en un JTable todos los alumnos
	 */
	protected void buscarAlumno() {
		int ven=1;
		int btn=1;
		int doc=0;
		String ape="";
		miCoordinador.mostrarVentanaAlumnoBuscar(btn,doc,ape,ven);
		
	}

	protected void guardarAlumno() {
		try {
			Date date=selectorFecha.getDate();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			AlumnoVO miAlumno = new AlumnoVO();
			miAlumno.setAldni(Integer.valueOf(txtDni.getText()));
			miAlumno.setAlapynom(txtApyNom.getText());
			miAlumno.setAlfnac(sdf.format(date));
			if (chkDoc.isSelected())
				miAlumno.setAldoc((byte) 1);
			else
				miAlumno.setAldoc((byte) 0);
			
			if (accion==1){
				miCoordinador.registrarAlumno(miAlumno);
				limpiar();
			}else{
				miCoordinador.modificarAlumno(miAlumno);
				limpiar();
			}
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error en el ingreso de datos","Error",JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
		
	}

	private void limpiar() {
		txtDni.setText("");
		txtApyNom.setText("");
		selectorFecha.setCalendar(null);
		chkDoc.setSelected(false);
		
		habilita(true,true,false,false,true,true,false,true,false,false,true,true);
	}

	protected void agregarAlumno() {
		habilita(true,true,true,true,false,false,true,false,false,false,false,true);
		
	}
	
	public void habilita(boolean dni,boolean nombre,boolean fecha,boolean doc,boolean bbuscarDni,boolean bbuscarNombre,boolean bGuardar,
			boolean bAgregar, boolean bModificar,boolean bEliminar,boolean bBuscar,boolean bCancelar){
		txtDni.setEditable(dni);
		txtApyNom.setEditable(nombre);
		selectorFecha.setEnabled(fecha);
		chkDoc.setEnabled(doc);
		btnBuscarXDni.setVisible(bbuscarDni);
		btnBuscarXApellido.setVisible(bbuscarNombre);
		btnGuardar.setVisible(bGuardar);
		btnAgregar.setEnabled(bAgregar);
		btnModificar.setEnabled(bModificar);
		btnEliminar.setEnabled(bEliminar);
		btnBuscar.setEnabled(bBuscar);
		btnCancelar.setEnabled(bCancelar);
	}

	public void muestraAlumno(AlumnoVO miAlumnoVO) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date miDia = new Date();
		GregorianCalendar miGCalendar = new GregorianCalendar();
		try{
			miDia = formato.parse(miAlumnoVO.getAlfnac());
		}catch (ParseException e){
			e.printStackTrace();
		}
		miGCalendar.setTime(miDia);
		txtDni.setText(String.valueOf(miAlumnoVO.getAldni()));
		txtApyNom.setText(miAlumnoVO.getAlapynom());
		selectorFecha.setCalendar(miGCalendar);
		
		if (miAlumnoVO.getAldoc() == 0)
			chkDoc.setSelected(false);
		else
			chkDoc.setSelected(true);
		habilita(false, false, false, false, false, false, false, false, true, true, true, true);
	}
	
	
}
