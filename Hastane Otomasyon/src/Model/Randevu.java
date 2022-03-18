package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.JDBConnect;

public class Randevu {

	private int id,doctorId,hastaId;
	private String doctorIsim,hastaIsim,randevutarih;
	
	Statement st=null;
	ResultSet rs=null;
	JDBConnect conn=new JDBConnect();
	PreparedStatement preparedStatement=null;
	public Randevu(int id, int doctorId, String doctorIsim,int hastaId, String hastaIsim, String randevutarih) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.hastaId = hastaId;
		this.doctorIsim = doctorIsim;
		this.hastaIsim = hastaIsim;
		this.randevutarih = randevutarih;
		
	}
	public Randevu(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getHastaId() {
		return hastaId;
	}
	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}
	public String getDoctorIsim() {
		return doctorIsim;
	}
	public void setDoctorIsim(String doctorIsim) {
		this.doctorIsim = doctorIsim;
	}
	public String getHastaIsim() {
		return hastaIsim;
	}
	public void setHastaIsim(String hastaIsim) {
		this.hastaIsim = hastaIsim;
	}
	public String getRandevutarih() {
		return randevutarih;
	}
	public void setRandevutarih(String randevutarih) {
		this.randevutarih = randevutarih;
	}
	
public ArrayList<Randevu> getRandevu(int hastaId) throws SQLException {
		
		ArrayList<Randevu> list = new java.util.ArrayList<Randevu>();
		Connection con=conn.ConnectDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM randevu where hastaId="+hastaId);		
			while (rs.next()==true) {
				Randevu  obj = new Randevu();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt(doctorId));
				obj.setDoctorIsim(rs.getString("doctoIsim"));
				obj.setHastaId(rs.getInt("hastaId"));
				obj.setHastaIsim(rs.getString("hastaIsim"));
				obj.setRandevutarih(rs.getString("randevutarih"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;

	}

public ArrayList<Randevu> getDoctorRandevu(int doctorId) throws SQLException {
	
	ArrayList<Randevu> list = new java.util.ArrayList<Randevu>();
	Connection con=conn.ConnectDB();
	try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM saatler where doctorId="+doctorId);		
		while (rs.next()==true) {
			Randevu  obj = new Randevu();
			obj.setId(rs.getInt("id"));
			obj.setDoctorId(rs.getInt(doctorId));
			obj.setDoctorIsim(rs.getString("doctoIsim"));
			obj.setHastaId(rs.getInt("hastaId"));
			obj.setHastaIsim(rs.getString("hastaIsim"));
			obj.setRandevutarih(rs.getString("randevutarih"));
			list.add(obj);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		st.close();
		rs.close();
		con.close();
	}
	return list;

}
}
