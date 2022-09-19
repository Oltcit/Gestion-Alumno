package modelo;


import java.sql.*;


/**
 * Clase que permite conectar con la base de datos MySQL
 */
public class Conexion {
	   static String bd = "usalumno";
	   static String login = "root";
	   static String password = "Admin";
	   static String driver = "com.mysql.cj.jdbc.Driver"; 
	   static String url= "jdbc:mysql://localhost:3306/"+bd+"?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Argentina/Buenos_Aires";
	   
	   Connection conn = null;

	   /** Constructor de DbConnection */
	   public Conexion() {
	      try{
	         //obtenemos el driver de para MySQL
	         Class.forName(driver);
	         //obtenemos la conexión
	         conn = DriverManager.getConnection(url,login,password);

	         if (conn!=null){
	            System.out.println("Conección a base de datos "+bd+" OK");
	         }
	      }
	      catch(SQLException e){
	         System.out.println(e);
	      }catch(ClassNotFoundException e){
	         System.out.println(e);
	      }catch(Exception e){
	         System.out.println(e);
	      }
	   }
	   /**Permite retornar la conexión*/
	   public Connection getConnection(){
	      return conn;
	   }

	   public void desconectar(){
	      conn = null;
	   }

}