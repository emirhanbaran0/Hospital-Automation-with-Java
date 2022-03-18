import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import Helper.Helper;
public class updateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField clinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateClinicGUI frame = new updateClinicGUI(clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param clinic2 
	 */
	public updateClinicGUI(Clinic clinic) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Poliklinik Adı");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 11, 75, 24);
		contentPane.add(lblNewLabel_5);
		
		clinicName = new JTextField();
		clinicName.setHorizontalAlignment(SwingConstants.CENTER);
		clinicName.setColumns(10);
		clinicName.setBounds(13, 31, 166, 24);
		clinicName.setText(clinic.getIsim());
		contentPane.add(clinicName);
		
		JButton btnDzenle = new JButton("D\u00FCzenle");
		btnDzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure"))
				{
					try {
						clinic.updateClinic(clinic.getId(),clinicName.getText());
						Helper.shwMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDzenle.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDzenle.setBounds(53, 66, 87, 23);
		contentPane.add(btnDzenle);
	}
}
