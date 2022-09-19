package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AlumnoMateriaDAO {

	public void registrarNota(AlumnoMateriaVO miAluMateriaVO) {
		Conexion conex=new Conexion();
		try{	
			Statement estatuto=conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO alumno_materia VALUES ('"+miAluMateriaVO.getAlmat()+"','"+miAluMateriaVO.getAldni()+"','"+miAluMateriaVO.getCodmat()+
					"','"+miAluMateriaVO.getNota()+"')");
			
			JOptionPane.showMessageDialog(null, "Se ha registrado exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "No se registró","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void buscarNotas(DefaultTableModel modelo, int doc) {
		Conexion conex=new Conexion();
		try{
			String consulta= "SELECT codmat,nota FROM alumno_materia WHERE aldni like ? order by codmat";
			PreparedStatement estatuto=conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1, doc+"%");
			ResultSet res = estatuto.executeQuery();
			
			while (res.next()){
				Object fila[]= new Object[2];
				for (int i=0; i<2;i++)
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
	
	

	public int actualizaDatos(int doc, String codigo) {
		Conexion conex=new Conexion();
		int nota;
		try{
			String consulta= "SELECT nota FROM alumno_materia WHERE aldni = ? and codmat = ?";
			PreparedStatement estatuto=conex.getConnection().prepareStatement(consulta);
			estatuto.setInt(1, doc);
			estatuto.setString(2, codigo);
			ResultSet res = estatuto.executeQuery();
			
			while (res.next()){
				return nota=Integer.valueOf(res.getObject(1).toString());
			}
			res.close();
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}
		return nota=99;
	
	}

	public void registrarAlumnoMateria(AlumnoMateriaVO miAlumnoMateriaVO, int cantMat) {
Conexion conex= new Conexion();
		
		try {
			Statement estatuto = conex.getConnection().createStatement();
			
			estatuto.executeUpdate("INSERT INTO alumno_materia (`almat`,`aldni`,`codmat`) VALUES ('"+miAlumnoMateriaVO.getAlmat()
				+"','"+miAlumnoMateriaVO.getAldni()+"', '"+miAlumnoMateriaVO.getCodmat()+"')");

			//estatuto.executeUpdate("INSERT INTO alumno_materia VALUES ('"+miAlumnoMateriaVO.getAlmat()
			//+"','"+miAlumnoMateriaVO.getAldni()+"', '"+miAlumnoMateriaVO.getCodmat()+"',null)");
			if (cantMat==0){
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
		    conex.desconectar();
		    System.out.println(cantMat);
			}
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}	
		
	}

}
