package sandbox;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	public static Connection getConnection(){
		Connection con=null;
		try{
			con=DriverManager.getConnection("jdbc:sqlite:datapas.db");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
}