package controlador;

import vista.VentanaAlumno;
import vista.VentanaAlumnoBuscar;
import vista.VentanaAlumnoMateriaNueva;
import vista.VentanaMateria;
import vista.VentanaMateriaBuscar;
import vista.VentanaNota;
import vista.VentanaNota2;
import vista.VentanaNotaBuscar;
import vista.VentanaPrincipal;

public class Principal {

	Coordinador miCoordinador;
	VentanaPrincipal miVentanaPrincipal;
	VentanaAlumno miVentanaAlumno;
	VentanaAlumnoBuscar miVentanaAlumnoBuscar;
	VentanaMateria miVentanaMateria;
	VentanaMateriaBuscar miVentanaMateriaBuscar;
	VentanaNota miVentanaNota;
	VentanaNota2 miVentanaNota2;
	VentanaNotaBuscar miVentanaNotaBuscar;
	VentanaAlumnoMateriaNueva miVentanaAlumnoMateriaNueva;
	
	public static void main(String[] args) {
		
		Principal miPrincipal = new Principal();
		miPrincipal.iniciar();
	}

	private void iniciar() {
		//se instancian las clases
		miCoordinador = new Coordinador();
		miVentanaPrincipal = new VentanaPrincipal();
		miVentanaAlumno = new VentanaAlumno();
		miVentanaAlumnoBuscar = new VentanaAlumnoBuscar();
		miVentanaMateria = new VentanaMateria();
		miVentanaMateriaBuscar = new VentanaMateriaBuscar();
		miVentanaNota = new VentanaNota();
		miVentanaNota2 = new VentanaNota2();
		miVentanaNotaBuscar = new VentanaNotaBuscar();
		miVentanaAlumnoMateriaNueva = new VentanaAlumnoMateriaNueva();
		
		//se establecen las relaciones entre clases
		miVentanaPrincipal.setMiCoordinador(miCoordinador);
		miVentanaAlumno.setMiCoordinador(miCoordinador);
		miVentanaAlumnoBuscar.setMiCoordinador(miCoordinador);
		miVentanaMateria.setMiCoordinador(miCoordinador);
		miVentanaMateriaBuscar.setMiCoordinador(miCoordinador);
		miVentanaNota.setMiCoordinador(miCoordinador);
		miVentanaNota2.setMiCoordinador(miCoordinador);
		miVentanaNotaBuscar.setMiCoordinador(miCoordinador);
		miVentanaAlumnoMateriaNueva.setMiCoordinador(miCoordinador);
		
		//se establecen relaciones con la clase coordinador
		miCoordinador.setMiVentanaPrincipal(miVentanaPrincipal);
		miCoordinador.setMiVentanaAlumno(miVentanaAlumno);
		miCoordinador.setMiVentanaAlumnoBuscar(miVentanaAlumnoBuscar);
		miCoordinador.setMiVentanaMateria(miVentanaMateria);
		miCoordinador.setMiVentanaMateriaBuscar(miVentanaMateriaBuscar);
		miCoordinador.setMiVentanaNota(miVentanaNota);
		miCoordinador.setMiVentanaNota2(miVentanaNota2);
		miCoordinador.setMiVentanaNotaBuscar(miVentanaNotaBuscar);
		miCoordinador.setMiVentanaAlumnoMateriaNueva(miVentanaAlumnoMateriaNueva);
		
		miVentanaPrincipal.setVisible(true);
	}

	
}
