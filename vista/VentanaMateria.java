package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.AlumnoVO;
import modelo.MateriaVO;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class VentanaMateria extends JFrame {

	private JPanel contentPane;
	private JTextField txtCod;
	private JTextField txtNom;
	private JTextField txtModulos;
	private Coordinador miCoordinador;
	private int accion;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private JButton btnBuscarXCdigo;
	private JButton btnModificar;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnBuscarXNombre;

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
					VentanaMateria frame = new VentanaMateria();
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
	public VentanaMateria() {
		setTitle("ABM Materias");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 571, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarMateria();
			}
		});
		panelSur.add(btnGuardar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accion=1;
				agregarMateria();
			}
		});
		panelSur.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarMateria();
			}
		});
		panelSur.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarMateria();
			}
		});
		panelSur.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarMateria();
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
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(28, 47, 46, 14);
		panelCentro.add(lblCdigo);
		
		txtCod = new JTextField();
		txtCod.setBounds(90, 44, 86, 20);
		panelCentro.add(txtCod);
		txtCod.setColumns(10);
		
		btnBuscarXCdigo = new JButton("Buscar x c\u00F3digo");
		btnBuscarXCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaParcialCodigo();
			}
		});
		btnBuscarXCdigo.setBounds(373, 43, 135, 23);
		panelCentro.add(btnBuscarXCdigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(28, 99, 46, 14);
		panelCentro.add(lblNombre);
		
		txtNom = new JTextField();
		txtNom.setBounds(90, 96, 217, 20);
		panelCentro.add(txtNom);
		txtNom.setColumns(10);
		
		btnBuscarXNombre = new JButton("Buscar x nombre");
		btnBuscarXNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busquedaParcialMateriaNombre();
			}
		});
		btnBuscarXNombre.setBounds(373, 95, 135, 23);
		panelCentro.add(btnBuscarXNombre);
		
		JLabel lblMdulos = new JLabel("M\u00F3dulos:");
		lblMdulos.setBounds(28, 156, 46, 14);
		panelCentro.add(lblMdulos);
		
		txtModulos = new JTextField();
		txtModulos.setBounds(90, 153, 86, 20);
		panelCentro.add(txtModulos);
		txtModulos.setColumns(10);
		
		limpiar();
	}

	protected void eliminarMateria() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de eliminar"
				+ "esa Materia?","Confirmación",JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_NO_OPTION)
		{
			miCoordinador.eliminarMateria(txtCod.getText());
			limpiar();
		}
		
	}

	protected void modificarMateria() {
		accion=2;
		habilita(false, true, true, false, false, true, false, false, false, false, true);	
	}

	protected void busquedaParcialMateriaNombre() {
		int btn=2;
		String cod="";
		String nom="";
		if (txtNom.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "<html>Ingrese las primeras letras del"
					+ " nombre <br> o el nombre completo</html>","Información",
					JOptionPane.WARNING_MESSAGE);
		}else{
				nom=txtNom.getText();
				miCoordinador.mostrarVentanaMateriaBuscar(btn,cod,nom);
			}
		
	}

	protected void busquedaParcialCodigo() {
		int btn=3;
		String cod="";
		String nom="";
		if (txtCod.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "<html>Ingrese las primeras letras del"
					+ " código <br> o el código completo</html>","Información",
					JOptionPane.WARNING_MESSAGE);
		}else{
				cod=txtCod.getText();
				miCoordinador.mostrarVentanaMateriaBuscar(btn,cod,nom);
			}
		
	}

	protected void buscarMateria() {
		// TODO Auto-generated method stub
		int btn=1;
		String cod="";
		String nom="";
		miCoordinador.mostrarVentanaMateriaBuscar(btn,cod,nom);
	}

	protected void guardarMateria() {
		try {
			MateriaVO miMateriaVO = new MateriaVO();
			miMateriaVO.setCodmat(txtCod.getText());
			miMateriaVO.setMatnom(txtNom.getText());
			miMateriaVO.setModulos(Integer.valueOf(txtModulos.getText()));
			
			if (accion==1){
				miCoordinador.registrarMateria(miMateriaVO);
				limpiar();
			}else{
				miCoordinador.modificarMateria(miMateriaVO);
				limpiar();
			}
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error en el ingreso de datos","Error",JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
		
	}

	protected void agregarMateria() {
		habilita(true, true, true, false, false, true, false, false, false, false, true);
		
	}
	
	public void habilita(boolean cod,boolean nombre,boolean modulo,boolean bbuscarCod,boolean bbuscarNombre,boolean bGuardar,
			boolean bAgregar, boolean bModificar,boolean bEliminar,boolean bBuscar,boolean bCancelar){
		txtCod.setEditable(cod);
		txtNom.setEditable(nombre);
		txtModulos.setEditable(modulo);
		btnBuscarXCdigo.setVisible(bbuscarCod);
		btnBuscarXNombre.setVisible(bbuscarNombre);
		btnGuardar.setVisible(bGuardar);
		btnAgregar.setEnabled(bAgregar);
		btnModificar.setEnabled(bModificar);
		btnEliminar.setEnabled(bEliminar);
		btnBuscar.setEnabled(bBuscar);
		btnCancelar.setEnabled(bCancelar);
	}

	public void muestraMateria(MateriaVO miMateriaVO) {
		txtCod.setText(miMateriaVO.getCodmat());
		txtNom.setText(miMateriaVO.getMatnom());
		txtModulos.setText(String.valueOf(miMateriaVO.getModulos()));
		
		habilita(false, false, false, false, false, false, false, true, true, true, true);	
	}
	
	private void limpiar(){
		txtCod.setText("");
		txtNom.setText("");
		txtModulos.setText("");
		
		habilita(true, true, false, true, true, false, true, false, false, true, true);
		
	}
}
