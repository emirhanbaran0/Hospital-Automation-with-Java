import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel wPane;
	private JTextField adfield;
	private JTextField tcfield;
	private JPasswordField sifrefield;
	private Hasta hasta=new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 276);
		wPane = new JPanel();
		wPane.setBackground(Color.WHITE);
		wPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wPane);
		wPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_1.setBounds(12, 11, 248, 27);
		wPane.add(lblNewLabel_1);
		
		adfield = new JTextField();
		adfield.setColumns(10);
		adfield.setBounds(12, 34, 209, 20);
		wPane.add(adfield);
		
		JLabel lblNewLabel_1_1 = new JLabel("TC Kimlik No");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(12, 55, 75, 27);
		wPane.add(lblNewLabel_1_1);
		
		tcfield = new JTextField();
		tcfield.setColumns(10);
		tcfield.setBounds(12, 80, 209, 20);
		wPane.add(tcfield);
		
		sifrefield = new JPasswordField();
		sifrefield.setBounds(12, 127, 209, 20);
		wPane.add(sifrefield);
		
		JLabel lblNewLabel = new JLabel("\u015Eifre");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel.setBounds(12, 108, 56, 14);
		wPane.add(lblNewLabel);
		
		JButton registerButton = new JButton("Kay\u0131t Ol");
		registerButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(tcfield.getText().length()==0 || sifrefield.getText().length()==0 || adfield.getText().length()==0 )
				{
					Helper.shwMsg("fill");
				}
				else
				{
					boolean control= hasta.register(tcfield.getText(), sifrefield.getText(), adfield.getText());
					if(control)
					{
						Helper.shwMsg("success");
						LoginGUI login=new LoginGUI();
						login.setVisible(true);
						dispose();
					}
					else
					{
						Helper.shwMsg("error");
					}
				}
			}
		});
		registerButton.setBounds(12, 158, 209, 32);
		wPane.add(registerButton);
		
		JButton backButton = new JButton("Geri  D\u00F6n");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(12, 203, 209, 23);
		wPane.add(backButton);
	}
}
