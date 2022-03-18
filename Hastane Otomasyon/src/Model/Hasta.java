package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import Helper.Helper;

public class Hasta extends User{

	Statement st=null;
	ResultSet rs=null;
	Connection con=null;
	PreparedStatement preparedStatement=null;
	
	public Hasta() {
		super();
		
	}

	public Hasta(int id, String tc, String sifre, String isim, String ktype) {
		super(id, tc, sifre, isim, ktype);
		
	}
	
	public boolean addAppointment(int id,int doctorId,String doctorIsim, int hastaId ,String hastaIsim,String randevutarih) {
		int key = 0;
		String str="Dolu";
		String query = "INSERT INTO randevu (id,doctorId,doctorIsim,hastaId,hastaIsim,randevutarih) VALUES (?,?,?,?,?,?)";

		try {
			Connection con = conn.ConnectDB();
			st = con.createStatement();			
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, idAta());
				preparedStatement.setInt(2, doctorId);
				preparedStatement.setString(3, doctorIsim);
				preparedStatement.setInt(4, hastaId );
				preparedStatement.setString(5, hastaIsim);
				preparedStatement.setString(6, randevutarih);
				preparedStatement.executeUpdate();

			key=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(key==1)
			return true;
					
		else 
			
		return false;
	}

	public boolean register(String tc, String sifre, String isim) {
		int key = 0;
		int count=0;
		String str="Dolu";
		String query = "INSERT INTO hastane (id,tc,sifre,isim,ktype) VALUES (?,?,?,?,?)";

		try {
			Connection con = conn.ConnectDB();
			st = con.createStatement();			
			rs = st.executeQuery("SELECT * FROM hastane where tc='"+tc+"'");
			while(rs.next())
			{
				count++;
				Helper.shwMsg("Kullanıcı Zaten Mevcuttur.");
				break;
			}
			if(count==0)
			{
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, idAta());
				preparedStatement.setString(2, tc);
				preparedStatement.setString(3, sifre);
				preparedStatement.setString(4, isim );
				preparedStatement.setString(5, "hasta" );
				preparedStatement.executeUpdate();
			}
			key=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(key==1)
			return true;
					
		else 
			
		return false;
	}
	
	public int idAta()
	{
		Random rand=new Random();
		int i=rand.nextInt();
		if(i<0)
			i=-i;
		i=i%1000;		
		return i;
		
		
	}
	
	public boolean updateWhourStatus(int doctorId,String randevutarih) {
		int key = 0;
		String str="Dolu";
		String query = "UPDATE  saatler SET  durum=? doctorId=? and where randevutarih=?";

		try {
			Connection con = conn.ConnectDB();
			st = con.createStatement();			
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, "Dolu");
				preparedStatement.setInt(2, doctorId);	
				preparedStatement.setString(3, randevutarih);
				preparedStatement.executeUpdate();

			key=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(key==1)
			return true;
					
		else 
			
		return false;
	}

}
