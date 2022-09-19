package controlador;

import java.sql.SQLException;

import modelo.AlumnoDAO;
import modelo.AlumnoMateriaDAO;
import modelo.AlumnoMateriaVO;
import modelo.AlumnoVO;
import modelo.MateriaDAO;
import modelo.MateriaVO;
import vista.VentanaAlumno;
import vista.VentanaAlumnoBuscar;
import vista.VentanaAlumnoMateriaNueva;
import vista.VentanaMateria;
import vista.VentanaMateriaBuscar;
import vista.VentanaNota;
import vista.VentanaNota2;
import vista.VentanaNotaBuscar;
import vista.VentanaPrincipal;

public class Coordinador {

	private VentanaPrincipal miVentanaPrincipal;
	private VentanaAlumno miVentanaAlumno;
	private VentanaAlumnoBuscar miVentanaAlumnoBuscar;
	private VentanaMateria miVentanaMateria;
	private VentanaMateriaBuscar miVentanaMateriaBuscar;
	private VentanaNota miVentanaNota;
	private VentanaNota2 miVentanaNota2;
	private VentanaNotaBuscar miVentanaNotaBuscar;
	private VentanaAlumnoMateriaNueva miVentanaAlumnoMateriaNueva;
	
	
	public VentanaNota2 getMiVentanaNota2() {
		return miVentanaNota2;
	}
	public void setMiVentanaNota2(VentanaNota2 miVentanaNota2) {
		this.miVentanaNota2 = miVentanaNota2;
	}
	
	public VentanaAlumnoMateriaNueva getMiVentanaAlumnoMateriaNueva() {
		return miVentanaAlumnoMateriaNueva;
	}
	public void setMiVentanaAlumnoMateriaNueva(VentanaAlumnoMateriaNueva miVentanaAlumnoMateriaNueva) {
		this.miVentanaAlumnoMateriaNueva = miVentanaAlumnoMateriaNueva;
	}
	public VentanaNotaBuscar getMiVentanaNotaBuscar() {
		return miVentanaNotaBuscar;
	}
	public void setMiVentanaNotaBuscar(VentanaNotaBuscar miVentanaNotaBuscar) {
		this.miVentanaNotaBuscar = miVentanaNotaBuscar;
	}
	public VentanaNota getMiVentanaNota() {
		return miVentanaNota;
	}
	public void setMiVentanaNota(VentanaNota miVentanaNota) {
		this.miVentanaNota = miVentanaNota;
	}
	public VentanaPrincipal getMiVentanaPrincipal() {
		return miVentanaPrincipal;
	}
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) {
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	public VentanaAlumno getMiVentanaAlumno() {
		return miVentanaAlumno;
	}
	public void setMiVentanaAlumno(VentanaAlumno miVentanaAlumno) {
		this.miVentanaAlumno = miVentanaAlumno;
	}
	public VentanaAlumnoBuscar getMiVentanaAlumnoBuscar() {
		return miVentanaAlumnoBuscar;
	}
	public void setMiVentanaAlumnoBuscar(VentanaAlumnoBuscar miVentanaAlumnoBuscar) {
		this.miVentanaAlumnoBuscar = miVentanaAlumnoBuscar;
	}
	public VentanaMateria getMiVentanaMateria() {
		return miVentanaMateria;
	}
	public void setMiVentanaMateria(VentanaMateria miVentanaMateria) {
		this.miVentanaMateria = miVentanaMateria;
	}
	public VentanaMateriaBuscar getMiVentanaMateriaBuscar() {
		return miVentanaMateriaBuscar;
	}
	public void setMiVentanaMateriaBuscar(VentanaMateriaBuscar miVentanaMateriaBuscar) {
		this.miVentanaMateriaBuscar = miVentanaMateriaBuscar;
	}
	
	///// mostrar ventanas
	
	public void mostrarVentanaAlumno() {
		miVentanaAlumno.setVisible(true);
		
	}
	public void mostrarVentanaMateria() {
		// TODO Auto-generated method stub
		miVentanaMateria.setVisible(true);
	}
	
public void mostrarVentanaAlumnoBuscar(int btn, int doc, String ape, int ven) {
		miVentanaAlumnoBuscar.setVisible(true);
		miVentanaAlumnoBuscar.mostrarDatosConTableModel(btn,doc,ape,ven);
	}

////////////////
	public void registrarAlumno(AlumnoVO miAlumno) throws SQLException {
		AlumnoDAO miAlumnoDAO= new AlumnoDAO();
		System.out.println("Fecha "+miAlumno.getAlfnac());
		System.out.println("Documentación "+miAlumno.getAldoc());
		miAlumnoDAO.registrarAlumno(miAlumno);
		
	}
	public void modificarAlumno(AlumnoVO miAlumno) {
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.modificarAlumno(miAlumno);
	}
	public void pasarDatosAlumno(AlumnoVO miAlumnoVO) {
		miVentanaAlumno.muestraAlumno(miAlumnoVO);
		
	}
	public void eliminarAlumno(int dni) {
		//acá iría a validar a lógica
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.eliminarAlumno(dni);
	}
	public void registrarMateria(MateriaVO miMateriaVO) {
		MateriaDAO miMateriaDAO=new MateriaDAO();
		miMateriaDAO.registrarMateria(miMateriaVO);
	}
	public void mostrarVentanaMateriaBuscar(int btn, String cod, String nom) {
		miVentanaMateriaBuscar.setVisible(true);
		miVentanaMateriaBuscar.mostrarDatosConTableModel(btn,cod,nom);
		
	}
	public void pasarDatosMateria(MateriaVO miMateriaVO) {
		miVentanaMateria.muestraMateria(miMateriaVO);
		
	}
	public void eliminarMateria(String codmat) {
		MateriaDAO miMateriaDAO = new MateriaDAO();
		miMateriaDAO.eliminarMateria(codmat);
		
	}
	public void modificarMateria(MateriaVO miMateriaVO) {
		MateriaDAO miMateriaDAO = new MateriaDAO();
		miMateriaDAO.modificarMateria(miMateriaVO);
	}
	public void mostrarVentanaNota() {
		miVentanaNota.setVisible(true);
		
	}
	public void mostrarVentanaNotaBuscar(int doc) {
		miVentanaNotaBuscar.setVisible(true);
		miVentanaNotaBuscar.mostrarDatosContableModel(doc);
		
	}
	public void registrarNota(AlumnoMateriaVO miAluMateriaVO) {
		AlumnoMateriaDAO miAlumnoMateriaDAO = new AlumnoMateriaDAO();
		miAlumnoMateriaDAO.registrarNota(miAluMateriaVO);
		
	}
	public void pasarDatosVentanaAlumnoMateriaNueva(AlumnoVO miAlumnoVO) {
		miVentanaAlumnoMateriaNueva.setVisible(true);
		miVentanaAlumnoMateriaNueva.mostrarDatosAlumno(miAlumnoVO);
	}
	public void mostrarVentanaNota2() {
		miVentanaNota2.setVisible(true);
		
	}
	
}
