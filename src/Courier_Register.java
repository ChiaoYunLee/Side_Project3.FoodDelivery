import java.awt.EventQueue;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import java.sql.Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Courier_Register {

	JFrame frame;
	private JTextField Name_textField;
	private JTextField Phone_textField;
	private JTextField Email_textField;
	private JTextField Password_textField;
	private JTextField Password_textField2;
	private JTextField JoinDate_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Courier_Register window = new Courier_Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Courier_Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 153, 153));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 342, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(255, 204, 204), 4, true));
		panel1.setBackground(new Color(255, 153, 153));
		panel1.setBounds(10, 10, 308, 55);
		frame.getContentPane().add(panel1);

		JLabel title_label = new JLabel("Courier Registration");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		title_label.setForeground(new Color(255, 255, 255));
		panel1.add(title_label);

		final JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 204, 204));
		panel2.setBounds(10, 75, 308, 462);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);

		JLabel Name_Label = new JLabel("Name : ");
		Name_Label.setForeground(new Color(0, 51, 51));
		Name_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Name_Label.setBounds(24, 30, 66, 15);
		panel2.add(Name_Label);

		JLabel Sex_Label = new JLabel("Sex : ");
		Sex_Label.setForeground(new Color(0, 51, 51));
		Sex_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Sex_Label.setBounds(24, 60, 66, 15);
		panel2.add(Sex_Label);

		JLabel Phone_Label = new JLabel("Phone : ");
		Phone_Label.setForeground(new Color(0, 51, 51));
		Phone_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Phone_Label.setBounds(24, 90, 66, 15);
		panel2.add(Phone_Label);

		JLabel Email_Label = new JLabel("E-mail : ");
		Email_Label.setForeground(new Color(0, 51, 51));
		Email_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Email_Label.setBounds(24, 120, 66, 15);
		panel2.add(Email_Label);

		JLabel Password_Label = new JLabel("Password : ");
		Password_Label.setForeground(new Color(0, 51, 51));
		Password_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Password_Label.setBounds(24, 150, 95, 15);
		panel2.add(Password_Label);

		JLabel JoinDate_Label = new JLabel("Join Date : ");
		JoinDate_Label.setForeground(new Color(0, 51, 51));
		JoinDate_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		JoinDate_Label.setBounds(24, 210, 95, 15);
		panel2.add(JoinDate_Label);

		Name_textField = new JTextField();
		Name_textField.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		Name_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Name_textField.setForeground(new Color(0, 51, 51));
		Name_textField.setBounds(83, 30, 200, 21);
		panel2.add(Name_textField);
		Name_textField.setColumns(10);

		final JRadioButton sex_button1 = new JRadioButton("Male");
		sex_button1.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		sex_button1.setBackground(new Color(255, 204, 204));
		sex_button1.setBounds(64, 57, 66, 23);
		panel2.add(sex_button1);

		final JRadioButton sex_button2 = new JRadioButton("Female");
		sex_button2.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		sex_button2.setBackground(new Color(255, 204, 204));
		sex_button2.setBounds(132, 57, 84, 23);
		panel2.add(sex_button2);

		final JRadioButton sex_button3 = new JRadioButton("Other");
		sex_button3.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		sex_button3.setBackground(new Color(255, 204, 204));
		sex_button3.setBounds(218, 57, 84, 23);
		panel2.add(sex_button3);

		ButtonGroup btnGroup1 = new ButtonGroup();
		btnGroup1.add(sex_button1);
		btnGroup1.add(sex_button2);
		btnGroup1.add(sex_button3);

		Phone_textField = new JTextField();
		Phone_textField.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		Phone_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Phone_textField.setForeground(new Color(0, 51, 51));
		Phone_textField.setColumns(10);
		Phone_textField.setBounds(83, 90, 200, 21);
		panel2.add(Phone_textField);

		Email_textField = new JTextField();
		Email_textField.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		Email_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Email_textField.setForeground(new Color(0, 51, 51));
		Email_textField.setColumns(10);
		Email_textField.setBounds(83, 120, 200, 21);
		panel2.add(Email_textField);

		Password_textField = new JPasswordField();
		Password_textField.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		Password_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Password_textField.setForeground(new Color(0, 51, 51));
		Password_textField.setColumns(10);
		Password_textField.setBounds(103, 150, 180, 21);
		panel2.add(Password_textField);

		Password_textField2 = new JPasswordField();
		Password_textField2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		Password_textField2.setHorizontalAlignment(SwingConstants.CENTER);
		Password_textField2.setForeground(new Color(0, 51, 51));
		Password_textField2.setColumns(10);
		Password_textField2.setBounds(147, 180, 136, 21);
		panel2.add(Password_textField2);

		JoinDate_textField = new JTextField();
		JoinDate_textField.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		JoinDate_textField.setHorizontalAlignment(SwingConstants.CENTER);
		JoinDate_textField.setForeground(new Color(0, 51, 51));
		JoinDate_textField.setColumns(10);
		JoinDate_textField.setBounds(103, 210, 180, 21);
		Date systemDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		JoinDate_textField.setText(dateFormat.format(systemDate));
		panel2.add(JoinDate_textField);

		JLabel Password_Label2 = new JLabel("Check Password :");
		Password_Label2.setForeground(new Color(0, 51, 51));
		Password_Label2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Password_Label2.setBounds(24, 180, 125, 15);
		panel2.add(Password_Label2);

		JLabel Agreement_Label = new JLabel("Agreement : ");
		Agreement_Label.setForeground(new Color(0, 51, 51));
		Agreement_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		Agreement_Label.setBounds(24, 240, 95, 21);
		panel2.add(Agreement_Label);

		final JPanel panel3 = new JPanel();
		panel3.setBorder(new LineBorder(new Color(255, 153, 153), 2, true));
		panel3.setBackground(new Color(255, 255, 255));
		panel3.setBounds(22, 268, 261, 108);//22, 268, 261, 108
		panel2.add(panel3);
		panel3.setLayout(null);

		JLabel AgreementContext = new JLabel("If you agree to be the courier in our ");
		AgreementContext.setBounds(14, 7, 233, 20);
		AgreementContext.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		panel3.add(AgreementContext);

		JLabel AgreementContext1 = new JLabel("platform, please select the checkbox.");
		AgreementContext1.setBounds(12, 32, 236, 20);
		AgreementContext1.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		panel3.add(AgreementContext1);

		final JCheckBox Agree_CheckBox = new JCheckBox("Agree");
		Agree_CheckBox.setBounds(102, 68, 78, 27);//102, 68, 61, 27
		Agree_CheckBox.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panel3.add(Agree_CheckBox);
		Agree_CheckBox.setBackground(new Color(255, 204, 204));

		JButton submit_Button = new JButton("Submit");
		submit_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		submit_Button.setForeground(new Color(255, 153, 153));
		submit_Button.setBackground(new Color(255, 153, 153));
		submit_Button.setBounds(60, 400, 85, 29);
		submit_Button.setOpaque(true);
		submit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String couName = Name_textField.getText();
				String couPhone = Phone_textField.getText();
				String couEmail = Email_textField.getText();
				String couPassword = Password_textField.getText();
				String couSex = "";
				if (sex_button1.isSelected() == true) {
					couSex = "M";
				} else if (sex_button2.isSelected() == true) {
					couSex = "F";
				} else if (sex_button3.isSelected() == true) {
					couSex = "O";
				}
				try {
					Connection conn = null;
					conn = Sql_Connection.connection_mysql();
					PreparedStatement st;
					st = conn.prepareStatement("SELECT Courier_Phone FROM COURIER WHERE Courier_Phone = ?");
					st.setString(1, couPhone);
					ResultSet result = st.executeQuery();
					String existPhone = "";
					while (result.next()) {
						existPhone = result.getString("Courier_Phone");
					}
					Courier newCou = new Courier();
					if (Agree_CheckBox.isSelected() == true
							&& Password_textField.getText().equals(Password_textField2.getText())
							&& existPhone.equals("") && !couName.equals("") && !couSex.equals("")
							&& !couPhone.equals("") && !couEmail.equals("") && !couPassword.equals("")) {
						try {
							newCou.addCourier(couName, couSex, couPhone, couEmail, couPassword);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane finish = new JOptionPane();
						finish.showMessageDialog(panel2, "Registration is complete! \nThanks for registering!");
					} else if (couName.equals("")) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "Please enter your name!");
					} else if (couSex.equals("")) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "Please select your gender!");
					} else if (couPhone.equals("")) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "Please enter your phone!");
					} else if (!existPhone.equals("")) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "This phone has registed!");
					} else if (couEmail.equals("")) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "Please enter your E-mail!");
					} else if (couPassword.equals("")) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "Please set the password!");
					} else if (!Password_textField.getText().equals(Password_textField2.getText())) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "Password are not same!");
					} else if (Agree_CheckBox.isSelected() == false) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel3, "Please select 'agree' this checkbox!");
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		panel2.add(submit_Button);

		JButton Back_Button = new JButton("Back");
		Back_Button.setForeground(new Color(255, 153, 153));
		Back_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Back_Button.setBackground(new Color(255, 153, 153));
		Back_Button.setBounds(160, 400, 85, 29);
		Back_Button.setOpaque(true);
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage home = new Homepage();
				home.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Back_Button);
	}
}

