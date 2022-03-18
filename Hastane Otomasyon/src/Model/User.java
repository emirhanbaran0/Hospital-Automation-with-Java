package Model;

import java.util.Random;

import Helper.JDBConnect;

public class User {
	private String tc;
	private String sifre;
	private String isim;
	private int id;
	private String ktype;

	public String getKtype() {
		return ktype;
	}

	public void setKtype(String ktype) {
		this.ktype = ktype;
	}

	JDBConnect conn=new JDBConnect();
	public String getTc() {
		return tc;
	}

	public User(int id,String tc, String sifre, String isim,String ktype) {
		super();
		this.tc = tc;
		this.sifre = sifre;
		this.isim = isim;
		this.id=id;
		this.ktype=ktype;
	}
	public User()
	{
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
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
}
