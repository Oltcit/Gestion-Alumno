package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class AlumnoDAO {

	/**
	 * Registra un alumno nuevo
	 * @param miAlumno
	 */
	public void registrarAlumno(AlumnoVO miAlumno)   {
		Conexion conex=new Conexion();
	try{	
		Statement estatuto=conex.getConnection().createStatement();
		estatuto.executeUpdate("INSERT INTO alumno VALUES ('"+miAlumno.getAldni()+"','"+miAlumno.getAlapynom()+"','"+miAlumno.getAlfnac()+
				"','"+miAlumno.getAldoc()+"')");
		JOptionPane.showMessageDialog(null, "Se ha registrado exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
		estatuto.close();
		conex.desconectar();
	} catch (SQLException e){
		JOptionPane.showMessageDialog(null, "No se registró","Error",JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
	}

	public void buscarAlumnos(DefaultTableModel modelo) {
		Conexion conex=new Conexion();
		try{
			Statement estatuto=conex.getConnection().createStatement();
			ResultSet res = estatuto.executeQuery("SELECT * from alumno order by alapynom");
			/*
			 * se tuvo que cambiar el for del resulset porque con Access cambiaba la columna
			 * 1 por la 2 y aparecía el apellido en la columna del DNI y el DNI en la
			 * columna del apellido
			 */
			//Para Access
			/*while (res.next()){
				Object fila[]= new Object[4];
				
				fila[0]=res.getInt("aldni");
				fila[1]=res.getString("alapynom");
				fila[2]=res.getString("alfnac");
				fila[3]=res.getInt("aldoc");
				modelo.addRow(fila);
				
			}*/
			
			//Para MySQL
			while (res.next()){
				Object fila[]= new Object[4];
				for (int i=0; i<4;i++)
					fila[i]=res.getObject(i+1);
				
				modelo.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void cargarComboAlumnos(DefaultComboBoxModel<String> modeloCbAlumno) {
		try{
			Conexion conex = new Conexion();
			ResultSet resAl = null;
			Statement estatutoAl = conex.getConnection().createStatement();
			resAl = estatutoAl.executeQuery("SELECT aldni from alumno order by aldni");
			
			while (resAl.next()){
				
				modeloCbAlumno.addElement((String) resAl.getObject(1).toString());
	
			}
			resAl.close();
			estatutoAl.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar alumnos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
public void cargarComboAlumnos2(DefaultComboBoxModel<String> modeloCbAlumno) {
		try{
			Conexion conex = new Conexion();
			ResultSet res = null;
			Statement estatuto = conex.getConnection().createStatement();
			res = estatuto.executeQuery("Select aldni from alumno order by aldni");
			while (res.next()){
				modeloCbAlumno.addElement(res.getObject(1).toString());
			}
			res.close();
			estatuto.close();
			conex.desconectar();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Error al consultar alumnos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public String darNombre(int doc) {
		Conexion conex = new Conexion();
		try{
			String nom="";
			String consulta2 = "SELECT alumno.alapynom FROM alumno where alumno.aldni=?";
			PreparedStatement estatuto2 = conex.getConnection().prepareStatement(consulta2);
			estatuto2.setInt(1,doc);
			ResultSet res2 = estatuto2.executeQuery();
			while (res2.next()){
				return nom=res2.getString("alapynom");	
			}
			
			res2.close();
			estatuto2.close();
			conex.desconectar();

		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar nombre","Error",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
public String traerNombre(int doc) {
		Conexion conex = new Conexion();
		try{
			String nom="";
			String consulta ="Select alumno.alapynom from alumno where alumno.aldni=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setInt(1, doc);
			ResultSet res = estatuto.executeQuery();
			while (res.next()){
				return nom=res.getString("alapynom");
			}
		
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al consultar nombre","Error",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	public void eliminarAlumno(int dni) {
		Conexion conex = new Conexion();
		try{
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("DELETE FROM alumno WHERE aldni='"+dni+"'");
			//la consulta en SQL seria: DELETE FROM alumno WHERE aldni='22333444'
			JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente","Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
		}catch (SQLException e){
			JOptionPane.showMessageDialog(null, "No se eliminó","Información",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void modificarAlumno(AlumnoVO miAlumno) {
		Conexion conex = new Conexion();
		try{
			String consulta="UPDATE alumno SET aldni=?, alapynom=?,alfnac=?,aldoc=? "
					+ "WHERE aldni=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			
			estatuto.setInt(1, miAlumno.getAldni());
			estatuto.setString(2, miAlumno.getAlapynom());
			estatuto.setString(3, miAlumno.getAlfnac());
			estatuto.setByte(4, miAlumno.getAldoc());
			estatuto.setInt(5, miAlumno.getAldni());
			estatuto.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Se ha modificado correctamente",
					"Confirmación",JOptionPane.INFORMATION_MESSAGE);
		}catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Error al modificar","Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void buscarParcialAlumnoDni(DefaultTableModel modelo, int doc) {
		Conexion conex=new Conexion();
		try{
			String consulta= "SELECT * FROM alumno WHERE aldni like ? order by alapynom";
			PreparedStatement estatuto=conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1, doc+"%");
			ResultSet res = estatuto.executeQuery();
			
			while (res.next()){
				Object fila[]= new Object[4];
				for (int i=0; i<4;i++)
					fila[i]=res.getObject(i+1);
				modelo.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void buscarParcialAlumnoApellido(DefaultTableModel modelo, String ape) {
		Conexion conex=new Conexion();
		try{
			String consulta= "SELECT * FROM alumno WHERE alapynom like ? order by alapynom";
			PreparedStatement estatuto=conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1, ape+"%");
			ResultSet res = estatuto.executeQuery();
			
			while (res.next()){
				Object fila[]= new Object[4];
				for (int i=0; i<4;i++)
					fila[i]=res.getObject(i+1);
				modelo.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	

	

}
