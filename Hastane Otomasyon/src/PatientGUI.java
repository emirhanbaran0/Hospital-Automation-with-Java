import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Clinic;
import Model.Hasta;
import Model.Randevu;
import Model.Whour;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JComboBox;

public class PatientGUI extends JFrame {

	private JPanel contentPane;
	private static Hasta hasta=new Hasta();
	private JTable doctorTable;
	private Clinic clinic=new Clinic();
	private DefaultTableModel doctorModel;
	private Object[] doctorData=null;
	private JTable whourTable;
	private Whour whour=new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData=null;
	private int selectDoctorId;
	private String selectDoctorName;
	private JTable randevuTable;
	private DefaultTableModel randevuModel;
	private Object[] randevuData=null;
	private Randevu randevu=new Randevu();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(hasta);
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
	public PatientGUI(Hasta hasta) throws SQLException {
		//doctor Model
		 doctorModel=new DefaultTableModel();
		Object[] colDoctor=new Object[2];
		colDoctor[0]="ID";
		colDoctor[1]="Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		Object[] doctorData=new Object[2];
		
		//whour Model
		whourModel=new DefaultTableModel();
		Object[] colwhour=new Object[2];
		colwhour[0]="ID";
		colwhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colwhour);
		Object[] whourData=new Object[2];
		
		//Randevu Model
			 randevuModel=new DefaultTableModel();
			Object[] randevuCol=new Object[3];
			randevuCol[0]="ID";
			randevuCol[1]="Ad Soyad";
			randevuCol[2]="Tarih";
			randevuModel.setColumnIdentifiers(randevuCol);
			Object[] randevuData=new Object[3];
			for(int i=0;i<randevu.getRandevu(hasta.getId()).size();i++)
			{
				randevuData[0]=randevu.getRandevu(hasta.getId()).get(i).getId();
				randevuData[1]=randevu.getRandevu(hasta.getId()).get(i).getDoctorIsim();
				randevuData[2]=randevu.getRandevu(hasta.getId()).get(i).getRandevutarih();
				randevuModel.addRow(randevuData);
			}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın "+hasta.getIsim());
		lblNewLabel.setBounds(10, 11, 206, 26);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI=new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(473, 13, 89, 24);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 43, 552, 295);
		contentPane.add(tabbedPane);
		
		JPanel scrollPoli = new JPanel();
		scrollPoli.setForeground(Color.RED);
		scrollPoli.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevu Sistemi", null, scrollPoli, null);
		
		scrollPoli.setLayout(null);
		
		JScrollPane scrollDoctor = new JScrollPane();
		scrollDoctor.setBounds(10, 36, 205, 220);
		scrollPoli.add(scrollDoctor);
		
		doctorTable = new JTable(doctorModel);
		scrollDoctor.setViewportView(doctorTable);
		
		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 11, 96, 25);
		scrollPoli.add(lblNewLabel_1);
		
		JComboBox selectPoli = new JComboBox();
		selectPoli.setBounds(225, 33, 96, 22);
		selectPoli.addItem("Poliklinik Seç");
		for(int i=0;i<clinic.getClinic().size();i++)
		{
			selectPoli.addItem(new Item(clinic.getClinic().get(i).getId(),clinic.getClinic().get(i).getIsim()));
		}
		selectPoli.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectPoli.getSelectedIndex()!=0)
				{
					JComboBox c=new JComboBox();
					c=(JComboBox) e.getSource();
					Item item=(Item) c.getSelectedItem();
					DefaultTableModel clearModel=(DefaultTableModel) doctorTable.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i<clinic.getWorker(item.getKey()).size();i++ )
						{
							doctorData[0]=clinic.getWorker(item.getKey()).get(i).getId();
							doctorData[1]=clinic.getWorker(item.getKey()).get(i).getIsim();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else
				{
					DefaultTableModel clearModel=(DefaultTableModel) doctorTable.getModel();
					clearModel.setRowCount(0);
				}
				
			}
			
		});
		scrollPoli.add(selectPoli);
		
		JLabel lblNewLabel_2 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(225, 16, 96, 14);
		scrollPoli.add(lblNewLabel_2);
		
		JScrollPane whourScroll = new JScrollPane();
		whourScroll.setBounds(332, 36, 205, 220);
		scrollPoli.add(whourScroll);
		
		whourTable = new JTable(whourModel);
		whourScroll.setViewportView(whourTable);
		whourTable.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Randevu Saatleri");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(332, 11, 122, 25);
		scrollPoli.add(lblNewLabel_1_1);
		
		JLabel randevu = new JLabel("Randevu");
		randevu.setForeground(Color.RED);
		randevu.setFont(new Font("Tahoma", Font.BOLD, 12));
		randevu.setBounds(225, 145, 96, 25);
		scrollPoli.add(randevu);
		
		JButton btnRandevu = new JButton("Seç");
		btnRandevu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=whourTable.getSelectedRow();
				if(selRow>=0)
				{
					String date=whourTable.getModel().getValueAt(selRow, 1).toString();
					boolean control=hasta.addAppointment(idAta(), selectDoctorId, selectDoctorName, hasta.getId(), hasta.getIsim(), date);
					if(control)
					{
						Helper.shwMsg("success");
						hasta.updateWhourStatus(selectDoctorId,date);
						try {
							updatewhourModel(selectDoctorId);
							updateRandevuModel(hasta.getId());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else
					{
						Helper.shwMsg("error");
					}
				}else
				{
					Helper.shwMsg("Lütfen geçerli bir tarih giriniz.");
				}
			}
		});
		btnRandevu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRandevu.setBounds(225, 171, 100, 23);
		scrollPoli.add(btnRandevu);
		
		JLabel lblNewLabel_1_2 = new JLabel("Doktor Seç");
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(225, 86, 96, 25);
		scrollPoli.add(lblNewLabel_1_2);
		
		JButton btndoktor = new JButton("Seç");
		btndoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=doctorTable.getSelectedRow();
						if(row>=0)
						{
							String value=doctorTable.getModel().getValueAt(row, 0).toString();
							int id=Integer.parseInt(value);
							DefaultTableModel clearModel= (DefaultTableModel) whourTable.getModel();
							clearModel.setRowCount(0);
							try {
								for(int i=0;i<whour.getWhour(id).size();i++) {
									whourData[0]=whour.getWhour(id).get(i).getId();
									whourData[1]=whour.getWhour(id).get(i).getCalismasaatleri();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							whourTable.setModel(whourModel);
							selectDoctorId=id;
							selectDoctorName=doctorTable.getModel().getValueAt(row, 1).toString();

						}else {
							Helper.shwMsg("Lütfen bir doktor seçiniz!");
						}
			}
		});
		btndoktor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btndoktor.setBounds(221, 111, 100, 23);
		scrollPoli.add(btndoktor);
		
		JPanel wrandevu = new JPanel();
		tabbedPane.addTab("Randevularım", null, wrandevu, null);
		wrandevu.setLayout(null);
		
		JPanel randevuScroll = new JPanel();
		randevuScroll.setBounds(10, 11, 527, 245);
		wrandevu.add(randevuScroll);
		randevuScroll.setLayout(null);
		
		randevuTable = new JTable(randevuModel);
		randevuTable.setBounds(0, 0, 527, 245);
		randevuScroll.add(randevuTable);
		
		
	}
	
	public void updatewhourModel(int doctorId) throws SQLException
	{
		DefaultTableModel clearModel=(DefaultTableModel) whourTable.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<whour.getWhour(doctorId).size();i++) {
			whourData[0]=whour.getWhour(doctorId).get(i).getId();
			whourData[1]=whour.getWhour(doctorId).get(i).getCalismasaatleri();
		}
	}
	
	public void updateRandevuModel(int hastaId) throws SQLException
	{
		DefaultTableModel clearModel=(DefaultTableModel) randevuTable.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<randevu.getRandevu(hastaId).size();i++)
		{
			randevuData[0]=randevu.getRandevu(hastaId).get(i).getId();
			randevuData[1]=randevu.getRandevu(hastaId).get(i).getDoctorIsim();
			randevuData[2]=randevu.getRandevu(hastaId).get(i).getRandevutarih();
			randevuModel.addRow(randevuData);
		}
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
