package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

import javax.swing.JOptionPane;

public class CalculaAi {
	public int calculaIdAlumnoMateria(){
		int id=1;
		
		
		Conexion con= new Conexion();
		ResultSet resId=null;
		
		try {
			String consulta = "SELECT MAX(almat) FROM alumno_materia";
			PreparedStatement estatuto = con.getConnection().prepareStatement(consulta);
			resId = estatuto.executeQuery();
					
			while(resId.next()){
				id=resId.getInt(1)+1;
			}
					
			} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error, no se conecto");
			}
			
		return id;
	}	
		
	}

