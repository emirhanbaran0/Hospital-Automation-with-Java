import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField hastaTC;
	private JTextField doktorTc;
	private JPasswordField Doktorsifre;
	JDBConnect conn= new JDBConnect();
	private JPasswordField hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(142, 0, 70, 62);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("h.png")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel_1.setBounds(83, 62, 206, 41);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 9));
		contentPane.add(lblNewLabel_1);
		
		JTabbedPane w_tabPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabPane.setBounds(10, 125, 364, 225);
		contentPane.add(w_tabPane);
		
		JPanel hastalogin = new JPanel();
		w_tabPane.addTab("Hasta Girişi", null, hastalogin, null);
		hastalogin.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. Numara: ");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(10, 28, 78, 41);
		hastalogin.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Şifre: ");
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(10, 64, 78, 41);
		hastalogin.add(lblNewLabel_1_2);
		
		hastaTC = new JTextField();
		hastaTC.setText("");
		hastaTC.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		hastaTC.setColumns(10);
		hastaTC.setBounds(87, 38, 181, 21);
		hastalogin.add(hastaTC);
		
		JButton btnhastakayit = new JButton("Kayıt Ol");
		btnhastakayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI=new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btnhastakayit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnhastakayit.setBounds(87, 125, 89, 31);
		hastalogin.add(btnhastakayit);
		
		JButton btnhastagiris = new JButton("Giriş Yap");
		btnhastagiris.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(hastaTC.getText().length()!=0 && hastaPass.getText().length()!=0)
				{  boolean key =true;
					try {
						Connection con=conn.ConnectDB();
						java.sql.Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM hastane");				
						while(rs.next())
						{
							if(hastaTC.getText().equals(rs.getString("tc")) && hastaPass.getText().equals(rs.getString("sifre")))
							{
								if(rs.getString("ktype").equals("hasta"))
								{
									Hasta hasta=new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setSifre(rs.getString("sifre"));
									hasta.setTc(rs.getString("tc"));
									hasta.setIsim(rs.getString("isim"));
									PatientGUI pGUI=new PatientGUI(hasta);
									pGUI.setVisible(true);
									dispose();
									key=false;
								}
							}
						}
					} catch (SQLException e1) {
						System.out.println("Hata");
						e1.printStackTrace();
					}
					if(key) {
						Helper.shwMsg("Sistemde böyle bir kullanıcı mevcut değildir! Lütfen Kayıt Olunuz.");
					}
				}
				if(hastaTC.getText().length()==0 || hastaPass.getText().length()==0)
				{
					Helper.shwMsg("fill");
				}
			}
		});
		btnhastagiris.setBounds(186, 125, 89, 31);
		hastalogin.add(btnhastagiris);
		
		hastaPass = new JPasswordField();
		hastaPass.setBounds(87, 74, 181, 20);
		hastalogin.add(hastaPass);
		
		JPanel doktorlogin = new JPanel();
		w_tabPane.addTab("Doktor Girişi", null, doktorlogin, null);
		doktorlogin.setLayout(null);
		
		JPanel hastalogin_1 = new JPanel();
		hastalogin_1.setLayout(null);
		hastalogin_1.setBounds(0, 0, 359, 197);
		doktorlogin.add(hastalogin_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("T.C. Numara: ");
		lblNewLabel_1_1_2.setForeground(Color.RED);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_2.setBounds(10, 28, 78, 41);
		hastalogin_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Şifre: ");
		lblNewLabel_1_2_2.setForeground(Color.RED);
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2.setBounds(10, 64, 78, 41);
		hastalogin_1.add(lblNewLabel_1_2_2);
		
		doktorTc = new JTextField();
		doktorTc.setText("");
		doktorTc.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		doktorTc.setColumns(10);
		doktorTc.setBounds(87, 38, 181, 21);
		hastalogin_1.add(doktorTc);
		
		JButton btngir = new JButton("Giriş Yap");
		btngir.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(Doktorsifre.getText().length()==0 || doktorTc.getText().length()==0)
				{
					Helper.shwMsg("fill");
				}
				if(Doktorsifre.getText().length()!=0 || doktorTc.getText().length()!=0)
				{
					try {
						Connection con=conn.ConnectDB();
						java.sql.Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM hastane");
						while(rs.next())
						{
							if(doktorTc.getText().equals(rs.getString("tc")) && Doktorsifre.getText().equals(rs.getString("sifre")))
							{
								if(rs.getString("ktype").equals("bashekim"))
								{
									Bashekim bashekim=new Bashekim();
									bashekim.setId(rs.getInt("id"));
									bashekim.setSifre(rs.getString("sifre"));
									bashekim.setTc(rs.getString("tc"));
									bashekim.setIsim(rs.getString("isim"));
									BashekimGUI bGUI=new BashekimGUI(bashekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("ktype").equals("doktor"))
								{
									Doctor doctor=new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setSifre(rs.getString("sifre"));
									doctor.setTc(rs.getString("tc"));
									doctor.setIsim(rs.getString("isim"));
									DoctorGUI dGUI=new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						System.out.println("Hata");
						e1.printStackTrace();
					}
				}
			}
		});
		btngir.setBounds(87, 116, 181, 30);
		hastalogin_1.add(btngir);
		
		Doktorsifre = new JPasswordField();
		Doktorsifre.setBounds(87, 74, 181, 20);
		hastalogin_1.add(Doktorsifre);
	}
}
