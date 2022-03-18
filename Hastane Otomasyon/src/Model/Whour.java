package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.JDBConnect;

public class Whour {

	private int id,doctorId;
	private String doctorIsim,calismasaatleri,durum;
	JDBConnect conn= new JDBConnect();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	
	
	public Whour(int id, int doctorId, String doctorIsim, String calismasaatleri, String durum) {
		this.id = id;
		this.doctorId = doctorId;
		this.doctorIsim = doctorIsim;
		this.calismasaatleri = calismasaatleri;
		this.durum = durum;
	}
	
	public Whour()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}
	public String getDoctorIsim() {
		return doctorIsim;
	}
	public void setDoctorIsim(String doctorIsim) {
		this.doctorIsim = doctorIsim;
	}
	public String getCalismasaatleri() {
		return calismasaatleri;
	}
	public void setCalismasaatleri(String calismasaatleri) {
		this.calismasaatleri = calismasaatleri;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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
}
