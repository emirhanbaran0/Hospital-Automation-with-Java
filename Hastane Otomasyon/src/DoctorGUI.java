import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DoctorGUI extends JFrame {

	
	private static Doctor doctor=new Doctor();
	
	private JPanel wPane;
	private JTable WhourTable;
	private DefaultTableModel whourModel;
	private Object[]  whourData=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DoctorGUI(Doctor doctor) throws SQLException {
		
		
		whourModel=new DefaultTableModel();
		Object[]  colHour=new Object[2];
		colHour[0]="ID";
		colHour[1]="Tarih";
		whourModel.setColumnIdentifiers(colHour);
		whourData=new Object[2];
		for(int i=0;i<doctor.getWhour(doctor.getId()).size();i++)
		{
			whourData[0]=doctor.getWhour(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhour(doctor.getId()).get(i).getCalismasaatleri();
			whourModel.addRow(whourData);
			
		}
		
		
		setTitle("Hastane Y\u00F6netim Sistemi ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 393);
		wPane = new JPanel();
		wPane.setBackground(Color.WHITE);
		wPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wPane);
		wPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın "+doctor.getIsim());
		lblNewLabel.setBounds(10, 11, 206, 26);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		wPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.setBounds(483, 12, 89, 24);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI=new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		wPane.add(btnNewButton);
		
		JTabbedPane wTab = new JTabbedPane(JTabbedPane.TOP);
		wTab.setBounds(10, 48, 552, 295);
		wPane.add(wTab);
		
		JPanel w_workhour = new JPanel();
		w_workhour.setBackground(Color.WHITE);
		wTab.addTab("Çalışma  Saatleri", null, w_workhour, null);
		w_workhour.setLayout(null);
		
		JComboBox selectTime = new JComboBox();
		selectTime.setBounds(124, 11, 54, 22);
		selectTime.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"}));
		w_workhour.add(selectTime);
		
		JDateChooser selectDate = new JDateChooser();
		selectDate.setBounds(10, 11, 104, 20);
		w_workhour.add(selectDate);
		
		JButton btnaddWhour = new JButton("Ekle");
		btnaddWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String date="";
				try {
					 date=sdf.format(selectDate.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if(date.length()==0)
				{
					Helper.shwMsg("Lütfen Geçerli bir tarih giriniz! ");
				}
				else
				{
					String time=" "+ selectTime.getSelectedItem().toString();
					String selectDate=date +time;
					boolean control= doctor.addWhour(doctor.getId(), doctor.getIsim(),selectDate);
					if(control)
					{
						Helper.shwMsg("İşlem Başarılı");
						try {
							updatewhourModel(doctor);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				
				
				
			}
		});
		btnaddWhour.setBounds(188, 10, 89, 23);
		btnaddWhour.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		w_workhour.add(btnaddWhour);
		
		JScrollPane wScroolhour = new JScrollPane();
		wScroolhour.setBounds(10, 44, 527, 212);
		w_workhour.add(wScroolhour);
		
		WhourTable = new JTable(whourModel);
		wScroolhour.setViewportView(WhourTable);
		
		JButton btndeleteWhour = new JButton("Sil");
		btndeleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow= WhourTable.getSelectedRow();
				if(selRow>=0)
				{
					String selID=WhourTable.getModel().getValueAt(selRow, 0).toString();
					int selectID=Integer.parseInt(selID);
					boolean control;
					try {
						control=doctor.deletewHour(selectID);
						if(control)
						{
							Helper.shwMsg("success");
							updatewhourModel(doctor);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					Helper.shwMsg("Lütfen bir tarih seçiniz");
				}
			}
		});
		btndeleteWhour.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		btndeleteWhour.setBounds(448, 11, 89, 23);
		w_workhour.add(btndeleteWhour);
	}
	
	public void updatewhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) WhourTable.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<doctor.getWhour(doctor.getId()).size();i++)
		{
			whourData[0]=doctor.getWhour(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhour(doctor.getId()).get(i).getCalismasaatleri();
			whourModel.addRow(whourData);
		}
			
		}
		

	public static Doctor getDoctor() {
		return doctor;
	}

	public static void setDoctor(Doctor doctor) {
		DoctorGUI.doctor = doctor;
	}
}
