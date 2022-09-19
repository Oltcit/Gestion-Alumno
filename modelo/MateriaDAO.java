package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MateriaDAO {

	public void registrarMateria(MateriaVO miMateriaVO) {
		Conexion conex=new Conexion();
		try{	
			Statement estatuto=conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO materia VALUES ('"+miMateriaVO.getCodmat()+"','"+miMateriaVO.getMatnom()+"','"+miMateriaVO.getModulos()+
					"')");
			JOptionPane.showMessageDialog(null, "Se ha registrado exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "No se registró","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		}

	public String darNombre(String codigo) {
		Conexion conex = new Conexion();
		try{
			String nom="";
			String consulta2 = "SELECT materia.matnom FROM materia where materia.codmat=?";
			PreparedStatement estatuto2 = conex.getConnection().prepareStatement(consulta2);
			estatuto2.setString(1,codigo);
			ResultSet res2 = estatuto2.executeQuery();
			while (res2.next()){
				return nom=res2.getString("matnom");	
			}
			
			res2.close();
			estatuto2.close();
			conex.desconectar();
			
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar nombre","Error",JOptionPane.ERROR_MESSAGE);
		}
			
		return null;
	}
	
	public void cargarComboMaterias(DefaultComboBoxModel<String> modeloCbMateria){
		try{
			Conexion conex = new Conexion();
			ResultSet resMat = null;
			Statement estatutoMat = conex.getConnection().createStatement();
			resMat = estatutoMat.executeQuery("SELECT codmat from materia");
			
			while (resMat.next()){
				
				modeloCbMateria.addElement((String) resMat.getObject(1));
		
			}
			resMat.close();
			estatutoMat.close();
			conex.desconectar();
		}		catch (SQLException e){
					JOptionPane.showMessageDialog(null, "Error al consultar materias","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
		public void buscarMaterias(DefaultTableModel modeloM) {
			Conexion conex=new Conexion();
			try{
				Statement estatuto=conex.getConnection().createStatement();
				ResultSet res = estatuto.executeQuery("SELECT * from materia order by matnom");
				/*
				 * se tuvo que cambiar el for del resulset porque con Access cambiaba la columna
				 * 1 por la 2 y aparecía el apellido en la columna del DNI y el DNI en la
				 * columna del apellido
				 */
				//Para Access
				/*while (res.next()){
					Object fila[]= new Object[3];
					
					fila[0]=res.getString("codmat");
					fila[1]=res.getString("matnom");
					fila[2]=res.getString("modulos");
					modeloM.addRow(fila);
					
					System.out.println(res.getString("codmat"));
					
				}*/
				
				//Para MySQL
				while (res.next()){
					Object fila[]= new Object[3];
					for (int i=0; i<3;i++)
						fila[i]=res.getObject(i+1);
					
					modeloM.addRow(fila);
				}
				res.close();
				estatuto.close();
				conex.desconectar();
				
			} catch (SQLException e){
				JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
			}
		
	}

		public void buscarParcialMateriaCodigo(DefaultTableModel modelo, String cod) {
			Conexion conex=new Conexion();
			try{
				String consulta= "SELECT * FROM materia WHERE codmat like ? order by codmat";
				PreparedStatement estatuto=conex.getConnection().prepareStatement(consulta);
				estatuto.setString(1, cod+"%");
				ResultSet res = estatuto.executeQuery();
				
				while (res.next()){
					Object fila[]= new Object[3];
					for (int i=0; i<3;i++)
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

		public void buscarParcialMateriaNombre(DefaultTableModel modelo, String nom) {
			Conexion conex=new Conexion();
			try{
				String consulta= "SELECT * FROM materia WHERE matnom like ? order by matnom";
				PreparedStatement estatuto=conex.getConnection().prepareStatement(consulta);
				estatuto.setString(1, nom+"%");
				ResultSet res = estatuto.executeQuery();
				
				while (res.next()){
					Object fila[]= new Object[3];
					for (int i=0; i<3;i++)
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

		public void eliminarMateria(String codmat) {
			Conexion conex = new Conexion();
			try{
				Statement estatuto = conex.getConnection().createStatement();
				estatuto.executeUpdate("DELETE FROM materia WHERE codmat='"+codmat+"'");
				
				JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente","Información",
						JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();
				conex.desconectar();
			}catch (SQLException e){
				JOptionPane.showMessageDialog(null, "No se eliminó","Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		public void modificarMateria(MateriaVO miMateriaVO) {
			Conexion conex = new Conexion();
			try{
				String consulta="UPDATE materia SET codmat=?, matnom=?,modulos=? "
						+ "WHERE codmat=?";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				
				estatuto.setString(1, miMateriaVO.getCodmat());
				estatuto.setString(2, miMateriaVO.getMatnom());
				estatuto.setInt(3, miMateriaVO.getModulos());
				estatuto.setString(4, miMateriaVO.getCodmat());
				estatuto.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Se ha modificado correctamente",
						"Confirmación",JOptionPane.INFORMATION_MESSAGE);
			}catch (SQLException e){
				JOptionPane.showMessageDialog(null, "Error al modificar","Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}

		public void cargarListaTotal(DefaultListModel<String> modeloTotal, int doc) {
			Conexion conex = new Conexion();
			try{
				String consulta="SELECT codmat, matnom FROM materia where codmat not in "
						+ "(SELECT codmat from alumno_materia where aldni=?)";
				PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
				
				estatuto.setInt(1, doc);
				ResultSet res = estatuto.executeQuery();
				while (res.next()){
					String cadena="";
					for (int i=0;i<2;i++)
						cadena+=res.getObject(i+1)+"  ";
					modeloTotal.addElement(cadena);
				}
				
				res.close();
				estatuto.close();
				conex.desconectar();
			}catch (SQLException e){
				JOptionPane.showMessageDialog(null, "Error al consultar","Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}

}
