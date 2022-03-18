package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import Helper.Helper;

public class Bashekim extends User{
	
	

	private Statement st;
	private ResultSet rs;

	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public Bashekim(int id,String tc, String sifre, String isim,String ktype) {
		super(id,tc, sifre, isim,ktype);
		// TODO Auto-generated constructor stub
	}
	public Bashekim()
	{
		
	}

	public ArrayList<User> getDoctor() throws SQLException
	{
		ArrayList<User> list=new java.util.ArrayList<User>();
		Connection con=conn.ConnectDB();
		Statement st=null;
		ResultSet rs=null;
		try
		{
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM hastane WHERE ktype='doktor'");
			while(rs.next()==true)
			{
				User obj = new User();
				obj.setId(rs.getInt("id"));
				obj.setIsim(rs.getString("isim"));
				obj.setTc(rs.getString("tc"));
				obj.setSifre(rs.getString("sifre"));
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
	
	public boolean addDoctor(int id,String tcno,String sifre,String isim ) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		try
		{
			String query="INSERT INTO hastane(id,tc,sifre,isim,ktype) VALUES (?,?,?,?,?)";
			Connection con=conn.ConnectDB();
			setSt(con.createStatement());			
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, sifre);
			preparedStatement.setString(4, isim);
			preparedStatement.setString(5, "doktor");
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

	public boolean deleteDoctor(int id ) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		try
		{
			String query="DELETE FROM hastane WHERE id=? ";
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
	
	public boolean updateDoctor(int id,String tc,String sifre,String isim) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		try
		{
			String query="UPDATE hastane SET isim=?,tc=?,sifre=? WHERE id=? ";
			Connection con=conn.ConnectDB();
			setSt(con.createStatement());			
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setString(2, tc);
			preparedStatement.setString(3, sifre);
			preparedStatement.setInt(4, id);
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
	
	public boolean addWorker(int klinikId,int calisanId) throws SQLException
	{
		boolean key=false;
		PreparedStatement preparedStatement=null;
		int count=0;
			try
			{
				int id=calisanlarId();
				String query="INSERT INTO calisanlar(id,klinikId,calisanId) VALUES (?,?,?)";
				Connection con=conn.ConnectDB();
				setSt(con.createStatement());	
				setRs(st.executeQuery("SELECT*FROM calisanlar WHERE  klinikId="+klinikId+ "AND  calisanId="+calisanId));
				while(rs.next())
					count++;
				if(count==0)
				{
					preparedStatement=con.prepareStatement(query);
					preparedStatement.setInt(1, calisanlarId());
					preparedStatement.setInt(2, klinikId);
					preparedStatement.setInt(3, calisanId);
					preparedStatement.executeUpdate();
				}	
				else 
					Helper.shwMsg("Bu kullanıcı zaten seçili poliklinikte mevcuttur.");
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
	
	public Statement getSt() {
		return st;
	}
	public void setSt(Statement st) {
		this.st = st;
	}
	
	public int calisanlarId()
	{
		Random rand=new Random();
		int id=rand.nextInt();
		if(id<0)
			id=-id;
		id=id%1000;
		
		return id;
	}
	
}
