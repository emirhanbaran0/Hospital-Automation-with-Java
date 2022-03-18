package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import Helper.Helper;
import Helper.JDBConnect;

public class Doctor extends User {

	JDBConnect conn = new JDBConnect();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Doctor() {
		super();

	}

	public Doctor(int id, String tc, String sifre, String isim, String ktype) {
		super(id, tc, sifre, isim, ktype);

	}

	public boolean addWhour(int doctorId, String doctorIsim, String calismasaatleri) {
		int key = 0;
		int count=0;
		String str="Dolu";
		String query = "INSERT INTO saatler (id,doctorId,doctorIsim,calismasaatleri,durum) VALUES (?,?,?,?,?)";

		try {
			Connection con = conn.ConnectDB();
			st = con.createStatement();			
			rs = st.executeQuery("SELECT * FROM saatler where (durum='Dolu' or durum=NULL) AND  doctorId=" + doctorId
					+ "AND calismasaatleri='" + calismasaatleri + "'");
			while(rs.next())
			{
				count++;
				Helper.shwMsg("Seçili Tarih ve Saat İçin Önceden Randevunuz Vardır.");
				break;
			}
			if(count==0)
			{
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, idAta());
				preparedStatement.setInt(2, doctorId);
				preparedStatement.setString(3, doctorIsim);
				preparedStatement.setString(4, calismasaatleri);
				preparedStatement.setString(5, str );
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
	
	
	public ArrayList<Whour> getWhour(int doctorId) throws SQLException
	{
		ArrayList<Whour> list=new java.util.ArrayList<Whour>();
		Connection con=conn.ConnectDB();
		Statement st=null;
		ResultSet rs=null;
		try
		{
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM saatler where durum='Dolu' AND doctorId="+doctorId);
			while(rs.next()==true)
			{
				Whour obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctorId"));
				obj.setDoctorIsim(rs.getString("doctorIsim"));
				obj.setDurum(rs.getString("durum"));
				obj.setCalismasaatleri(rs.getString("calismasaatleri"));
				list.add(obj);
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			st.close();
			rs.close();
			con.close();
		}
		return list;
		
	}
	
	public boolean deletewHour(int id ) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		try
		{
			String query="DELETE FROM saatler Where id=? ";
			Connection con=conn.ConnectDB();
			st=con.createStatement();			
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key=true;

		}
		catch(SQLException E)
		{
			E.printStackTrace();
		}
		if(key)
		return true;
		else 
			return false;
	}

	public int idAta() {
		int i;
		Random rand = new Random();
		i = rand.nextInt();
		if (i < 0)
			i = -i;
		i = i % 1000;
		return i;
	}
}
