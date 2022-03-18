package Helper;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnect {
   Connection c=null;
   String url="jdbc:sqlserver://LAPTOP-DAL1RPE4:1433;DatabaseName=Hastane;encrypt=true;trustServerCertificate=true";
   String userName="emir";
	String password="emir";
   
   public Connection ConnectDB() throws SQLException
   {
	   this.c=DriverManager.getConnection(url, userName, password);
	   return c;
   }
}
