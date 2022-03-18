package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import Helper.JDBConnect;

public class Clinic {

	private int id;
	private String isim;
	
	private Statement st = null;
	private ResultSet rs = null;
	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	PreparedStatement preparedStatement=null;
	JDBConnect conn=new JDBConnect();
	

	public Clinic(int id, String isim) {
		super();
		this.id = id;
		this.isim = isim;
	}

	public Clinic() {

	}

	public ArrayList<Clinic> getClinic() throws SQLException {
		
		ArrayList<Clinic> list = new java.util.ArrayList<Clinic>();
		Connection con=conn.ConnectDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM klinik");		
			while (rs.next()==true) {
				Clinic  obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setIsim(rs.getString("isim"));
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
	
	public Clinic getFetch(int id) throws SQLException
	{
		Clinic clinic = new Clinic();
		Connection con=conn.ConnectDB();
		try {
			
			setSt(con.createStatement());
			rs=st.executeQuery("SELECT *FROM klinik where id="+id);
			while(rs.next())
			{
				
				clinic.setId(rs.getInt(id));
				clinic.setIsim(rs.getString("isim"));
				break;
			}
		}catch(SQLException e)
		{
			
		}
		return clinic;
	}
	
	
	public boolean addClinic(int id,String isim ) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		try
		{
			String query="INSERT INTO klinik(id,isim) VALUES (?,?)";
			Connection con=conn.ConnectDB();
			setSt(con.createStatement());			
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, id);;
			preparedStatement.setString(2, isim);
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
	
	
	public boolean deleteClinic(int id ) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		try
		{
			String query="DELETE FROM klinik WHERE id=? ";
			Connection con=conn.ConnectDB();
			setSt(con.createStatement());			
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
	
	
	public boolean updateClinic(int id,String isim) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		try
		{
			String query="UPDATE klinik SET isim=? WHERE id=? ";
			Connection con=conn.ConnectDB();
			setSt(con.createStatement());			
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setInt(2, id);
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
	
	public ArrayList<User> getWorker(int klinikId) throws SQLException
	{
		ArrayList<User> list=new java.util.ArrayList<User>();
		Connection con=conn.ConnectDB();
		Statement st=null;
		ResultSet rs=null;
		try
		{
			st=con.createStatement();
			rs=st.executeQuery("SELECT hastane.id,hastane.isim,hastane.tc,hastane.ktype from calisanlar  LEFT JOIN  hastane on calisanlar.calisanId=hastane.id WHERE klinikId="+ klinikId);
			while(rs.next()==true)
			{
				User obj = new User();
				obj.setId(rs.getInt("id"));
				obj.setIsim(rs.getString("isim"));
				obj.setTc(rs.getString("tc"));
				//obj.setSifre(rs.getString("hastane.sifre"));
				obj.setKtype(rs.getString("ktype"));
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
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}
}
