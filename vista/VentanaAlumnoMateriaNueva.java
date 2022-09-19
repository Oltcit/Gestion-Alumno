package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.AlumnoMateriaDAO;
import modelo.AlumnoMateriaVO;
import modelo.AlumnoVO;
import modelo.CalculaAi;
import modelo.MateriaDAO;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import com.toedter.calendar.JYearChooser;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaAlumnoMateriaNueva extends JFrame {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JScrollPane scrollPaneCursa;
	private JList<String> listaCursa;
	private DefaultListModel<String> modeloCursa;
	private JScrollPane scrollPaneTotal;
	private JList<String> listaTotal;
	private DefaultListModel<String> modeloTotal;
	private JLabel lblApe;
	private JLabel lblDni;
	private JYearChooser selectorAnio;
	private int numAi;
	

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
					VentanaAlumnoMateriaNueva frame = new VentanaAlumnoMateriaNueva();
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
	public VentanaAlumnoMateriaNueva() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos alumno", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 4, 5, 5));
		
		JLabel labelApe = new JLabel("Apellido y Nombre:");
		panelNorte.add(labelApe);
		
		lblApe = new JLabel("");
		panelNorte.add(lblApe);
		
		JLabel label = new JLabel("");
		panelNorte.add(label);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblAo);
		
		JLabel labelDni = new JLabel("Dni:");
		panelNorte.add(labelDni);
		
		lblDni = new JLabel("");
		panelNorte.add(lblDni);
		
		JLabel label_1 = new JLabel("");
		panelNorte.add(label_1);
		
		selectorAnio = new JYearChooser();
		panelNorte.add(selectorAnio);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarMateria();
			}
		});
		panelSur.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelSur.add(btnCancelar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		scrollPaneTotal = new JScrollPane();
		scrollPaneTotal.setBounds(10, 11, 257, 239);
		panelCentro.add(scrollPaneTotal);
		
		modeloTotal = new DefaultListModel<String>();
		listaTotal = new JList<String>();
		listaTotal.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPaneTotal.setViewportView(listaTotal);
		
		scrollPaneCursa = new JScrollPane();
		scrollPaneCursa.setBounds(346, 11, 264, 239);
		panelCentro.add(scrollPaneCursa);
		
		modeloCursa = new DefaultListModel<String>();
		listaCursa = new JList<String>();
		listaCursa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneCursa.setViewportView(listaCursa);
		
		JButton btnPasar = new JButton(">");
		btnPasar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pasarMateria();
			}
		});
		btnPasar.setBounds(283, 83, 53, 23);
		panelCentro.add(btnPasar);
		
		JButton btnTraer = new JButton("<");
		btnTraer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traerMateria();
			}
		});
		btnTraer.setBounds(283, 141, 53, 23);
		panelCentro.add(btnTraer);
	}

	protected void guardarMateria() {
		String fila, codMat;
		int fecha=selectorAnio.getValue();
		int doc;
		int cantMat=modeloCursa.size();
		if (modeloCursa.size()>0){
			for (int i=0;i<modeloCursa.size();i++){
				codMat="";
				fila=modeloCursa.get(i);
				int desde=0;
				int hasta=1;
				String letra=fila.substring(desde,hasta);
				while (!letra.equals(" ")){
					codMat+=letra;
					desde++;
					hasta++;
					letra=fila.substring(desde, hasta);
				}
				CalculaAi cai= new CalculaAi();
				numAi = cai.calculaIdAlumnoMateria();
				doc=Integer.valueOf(lblDni.getText().trim());
				AlumnoMateriaVO miAlumnoMateriaVO = new AlumnoMateriaVO();
				miAlumnoMateriaVO.setAldni(doc);
				miAlumnoMateriaVO.setAlmat(numAi);
				miAlumnoMateriaVO.setCodmat(codMat);
				AlumnoMateriaDAO miAlumnoMateriaDAO = new AlumnoMateriaDAO();
				System.out.println(cantMat);
				cantMat--;
				miAlumnoMateriaDAO.registrarAlumnoMateria(miAlumnoMateriaVO,cantMat);
				
			}
		}
		//dispose();
		modeloCursa.clear();
		dispose();
	}

	protected void traerMateria() {
		modeloTotal.addElement(listaCursa.getSelectedValue());
		listaTotal.setModel(modeloTotal);
		modeloCursa.removeElement(listaCursa.getSelectedValue());
	}

	protected void pasarMateria() {
		List<String> seleccion = listaTotal.getSelectedValuesList();
		Iterator i=seleccion.iterator();
		int a=0;
		while (i.hasNext()){
			modeloCursa.addElement((String) i.next());
			modeloTotal.removeElement(seleccion.get(a));
			a++;
		}
		listaCursa.setModel(modeloCursa);
	}

	public void mostrarDatosAlumno(AlumnoVO miAlumnoVO) {
		modeloTotal.clear();
		lblApe.setText(miAlumnoVO.getAlapynom());
		lblDni.setText(String.valueOf(miAlumnoVO.getAldni()));
		int doc=miAlumnoVO.getAldni();
		MateriaDAO miMateriaDAO = new MateriaDAO();
		miMateriaDAO.cargarListaTotal(modeloTotal,doc);
		listaTotal.setModel(modeloTotal);
		
	}

	
	
	public JYearChooser getSelectorAnio() {
		return selectorAnio;
	}
}
