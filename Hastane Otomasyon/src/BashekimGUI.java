import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.Helper;
import Helper.Item;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private JPanel contentPane;
	private JTextField dName;
	private JTextField dTc;
	private JTextField dSifre;
	private JTextField textField_3;
	private JTable doktortable;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private DefaultTableModel klinikModel = null;
	private Object[] klinikData = null;

	Random rand = new Random();
	private JTable kliniktable;
	private JTextField textField;
	private JTable workerTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
         
		
		//doctorModel
		
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		;
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctor().size(); i++) {
			doctorData[0] = bashekim.getDoctor().get(i).getId();
			doctorData[1] = bashekim.getDoctor().get(i).getIsim();
			doctorData[2] = bashekim.getDoctor().get(i).getTc();
			doctorData[3] = bashekim.getDoctor().get(i).getSifre();
			doctorModel.addRow(doctorData);
		}

		//klinikModel
		
		klinikModel = new DefaultTableModel();
		Object[] colKlinikName = new Object[2];
		colKlinikName[0] = "ID";
		colKlinikName[1] = "KLİNİK ADI";
		klinikModel.setColumnIdentifiers(colKlinikName);
		;
		klinikData = new Object[2];
		for (int i = 0; i < clinic.getClinic().size(); i++) {
			klinikData[0] = clinic.getClinic().get(i).getId();
			klinikData[1] = clinic.getClinic().get(i).getIsim();
			;
			klinikModel.addRow(klinikData);
		}
			
		//WorkerModel
		
		DefaultTableModel workerModel=new DefaultTableModel();
		Object[] colworker=new Object[2];
		colworker[0]="ID";
		colworker[1]="Ad Soyad";
		workerModel.setColumnIdentifiers(colworker);
		Object[] workerData=new Object[2];
		
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 392);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n " + bashekim.getIsim());
		lblNewLabel.setBounds(20, 11, 206, 26);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI=new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(483, 13, 89, 24);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		contentPane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 48, 552, 295);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_1.setBounds(451, 15, 75, 27);
		panel.add(lblNewLabel_1);

		dName = new JTextField();
		dName.setBounds(439, 46, 98, 20);
		panel.add(dName);
		dName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("T.C. No");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_2.setBounds(460, 73, 46, 14);
		panel.add(lblNewLabel_2);

		dTc = new JTextField();
		dTc.setBounds(439, 88, 98, 20);
		panel.add(dTc);
		dTc.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u015Eifre");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(460, 111, 46, 14);
		panel.add(lblNewLabel_3);

		dSifre = new JTextField();
		dSifre.setBounds(439, 131, 98, 20);
		panel.add(dSifre);
		dSifre.setColumns(10);

		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dName.getText().length() == 0 || dSifre.getText().length() == 0 || dTc.getText().length() == 0) {
					Helper.shwMsg("fill");
				}
				if (dName.getText().length() != 0 && dSifre.getText().length() != 0 && dTc.getText().length() != 0) {
					int i = rand.nextInt();
					if (i < 0)
						i = -i;
					try {
						boolean control = bashekim.addDoctor(i, dTc.getText(), dSifre.getText(), dName.getText());
						if (control) {
							Helper.shwMsg("İşlem Başarılı");
							dName.setText(null);
							dSifre.setText(null);
							dTc.setText(null);
							updateDoctorModel();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(451, 161, 75, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Sil");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_3.getText().length() == 0) {
					Helper.shwMsg("Lütfen Silmek İstediğiniz Doktorun Id'sini Ekleyiniz!");
				}
				if (textField_3.getText().length() != 0) {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(textField_3.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.shwMsg("Success");
								textField_3.setText(null);
								updateDoctorModel();

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(439, 233, 89, 23);
		panel.add(btnNewButton_2);

		textField_3 = new JTextField();
		textField_3.setBounds(420, 205, 127, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_4.setBounds(451, 184, 75, 20);
		panel.add(lblNewLabel_4);

		JScrollPane doktorscrollPane = new JScrollPane();
		doktorscrollPane.setBounds(10, 21, 408, 235);
		panel.add(doktorscrollPane);

		doktortable = new JTable(doctorModel);
		doktorscrollPane.setViewportView(doktortable);

		JPanel pKlinik = new JPanel();
		pKlinik.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, pKlinik, null);
		pKlinik.setLayout(null);

		JScrollPane clinicPane = new JScrollPane();
		clinicPane.setBounds(10, 11, 229, 245);
		pKlinik.add(clinicPane);

		JPopupMenu clinicMenu=new JPopupMenu();
		JMenuItem  updateMenu=new JMenuItem("Güncelle");
		JMenuItem  deleteMenu=new JMenuItem("Sil");
		
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selID=Integer.parseInt( kliniktable.getValueAt(kliniktable.getSelectedRow(), 0).toString());
				Clinic selectClinic=new Clinic();
				try {
					//selectClinic = clinic.getFetch(selID);
					for(int i=0;i<clinic.getClinic().size();i++)
					{
						if(clinic.getClinic().get(i).getId()==selID)
						{
							selectClinic=clinic.getClinic().get(i);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateClinicGUI updateGUI=new updateClinicGUI(selectClinic);	
				updateGUI.setVisible(true);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.addWindowListener(new WindowAdapter()
						{
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						super.windowClosed(e);
					}
						});
			}
			
		});
		
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure"))
				{
					int selID=Integer.parseInt( kliniktable.getValueAt(kliniktable.getSelectedRow(), 0).toString());
					try {
						if(clinic.deleteClinic(selID))
						{
							Helper.shwMsg("success");
							updateClinicModel();
						}
						else {
							Helper.shwMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		kliniktable = new JTable(klinikModel);
		clinicPane.setViewportView(kliniktable);
		kliniktable.setComponentPopupMenu(clinicMenu);
		kliniktable.addMouseListener(new MouseAdapter()
				{
			@Override
			public void mousePressed(MouseEvent e) {
				Point point=e.getPoint();
				int selectedRow=kliniktable.rowAtPoint(point);
				kliniktable.setRowSelectionInterval(selectedRow, selectedRow);
				super.mousePressed(e);
			}
				});
			
		JLabel lblNewLabel_5 = new JLabel("Poliklinik Adı");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_5.setBounds(238, 24, 75, 24);
		pKlinik.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(243, 43, 125, 24);
		pKlinik.add(textField);
		textField.setColumns(10);

		JButton btnAddClinic = new JButton("Ekle");
		btnAddClinic.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() == 0)
					Helper.shwMsg("fill");
				else {
					try {
						if (clinic.addClinic(idOlustur(), textField.getText())) {
							Helper.shwMsg("success");
							textField.setText(null);
							updateClinicModel();
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btnAddClinic.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddClinic.setBounds(265, 78, 75, 23);
		pKlinik.add(btnAddClinic);

		JScrollPane scrollWorker = new JScrollPane();
		scrollWorker.setBounds(378, 11, 159, 245);
		pKlinik.add(scrollWorker);
		
		workerTable = new JTable();
		scrollWorker.setViewportView(workerTable);
		
		JComboBox selectDoctor = new JComboBox();
		
		selectDoctor.setBounds(243, 197, 125, 24);	
		for(int i=0;i<bashekim.getDoctor().size();i++)
		{
			selectDoctor.addItem(new Item(bashekim.getDoctor().get(i).getId(),bashekim.getDoctor().get(i).getIsim()) {
				
			});
		}
		selectDoctor.addActionListener(e-> {
			JComboBox c=(JComboBox) e.getSource();
			Item item=(Item) c.getSelectedItem();
			System.out.println(item.getValue()+ item.getKey());
		});
		pKlinik.add(selectDoctor);
		
		JButton addWorker = new JButton("Ekle");
		addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow=kliniktable.getSelectedRow();
				if(selectRow>-1)
				{
					String selClinic=kliniktable.getModel().getValueAt(selectRow, 0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					Item doctorItem=(Item) selectDoctor.getSelectedItem();
					try {
						boolean control=bashekim.addWorker(selClinicID, doctorItem.getKey());
						if(control)
						{
							Helper.shwMsg("İşlem Başarılı");
							DefaultTableModel clearModel= (DefaultTableModel) workerTable.getModel();
							clearModel.setRowCount(0);
							for(int i=0;i<bashekim.getWorker(selClinicID).size();i++)
							{
								workerData[0]=bashekim.getWorker(selClinicID).get(i).getId();
								workerData[1]=bashekim.getWorker(selClinicID).get(i).getIsim();
								workerModel.addRow(workerData);
							}
							workerTable.setModel(workerModel);		
						}
								
						else 
							Helper.shwMsg("error");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					Helper.shwMsg("Lütfen bir poliklinik seçiniz!");
				}
			}
		});
		addWorker.setFont(new Font("Tahoma", Font.BOLD, 11));
		addWorker.setBounds(265, 232, 75, 23);
		pKlinik.add(addWorker);
		
		JLabel lblNewLabel_5_1 = new JLabel("Poliklinik Adı");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(238, 112, 91, 24);
		pKlinik.add(lblNewLabel_5_1);
		
		JButton btnSe = new JButton("Seç");
		btnSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int  selRow= kliniktable.getSelectedRow();
				if(selRow>=0)
				{
					String selClinic=kliniktable.getModel().getValueAt(selRow, 0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					DefaultTableModel clearModel= (DefaultTableModel) workerTable.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0;i<bashekim.getWorker(selClinicID).size();i++)
						{
							workerData[0]=bashekim.getWorker(selClinicID).get(i).getId();
							workerData[1]=bashekim.getWorker(selClinicID).get(i).getIsim();
							workerModel.addRow(workerData);
						}
						
					}catch(SQLException e1)
					{
						e1.printStackTrace();
					}					
				   workerTable.setModel(workerModel);
				}else
				{
					Helper.shwMsg("Lütfen Bir Poliklinik Seçiniz");
				}
			}
		});
		btnSe.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSe.setBounds(249, 133, 119, 23);
		pKlinik.add(btnSe);

		doktortable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					textField_3.setText(doktortable.getValueAt(doktortable.getSelectedRow(), 0).toString());
				} catch (Exception E) {

				}

			}

		});

		doktortable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(doktortable.getValueAt(doktortable.getSelectedRow(), 0).toString());
					String selectName = doktortable.getValueAt(doktortable.getSelectedRow(), 1).toString();
					String selecTc = doktortable.getValueAt(doktortable.getSelectedRow(), 2).toString();
					String selectSifre = doktortable.getValueAt(doktortable.getSelectedRow(), 3).toString();
					try {
						boolean control = bashekim.updateDoctor(selectID, selecTc, selectSifre, selectName);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) doktortable.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctor().size(); i++) {
			doctorData[0] = bashekim.getDoctor().get(i).getId();
			doctorData[1] = bashekim.getDoctor().get(i).getIsim();
			doctorData[2] = bashekim.getDoctor().get(i).getTc();
			doctorData[3] = bashekim.getDoctor().get(i).getSifre();
			doctorModel.addRow(doctorData);

		}

	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) kliniktable.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getClinic().size(); i++) {
			klinikData[0] = clinic.getClinic().get(i).getId();
			klinikData[1] = clinic.getClinic().get(i).getIsim();
			klinikModel.addRow(klinikData);

		}

	}
	
	
	public int idOlustur()
	{
		Random rand=new Random();
		int a;
		
		a=rand.nextInt();
		if(a<0)
		a=-a;
			return a;
	}

	public Object[] getKlinikData() {
		return klinikData;
	}

	public void setKlinikData(Object[] klinikData) {
		this.klinikData = klinikData;
	}
}
