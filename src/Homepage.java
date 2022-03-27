import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Homepage {

	JFrame frame;
	private JTextField Phone_textField;
	private JTextField Password_textField;
	public String phone;
	public String pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage window = new Homepage();
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
	public Homepage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(255, 204, 204), 4, true));
		panel1.setBackground(new Color(255, 153, 153));
		panel1.setBounds(10, 10, 308, 55);
		frame.getContentPane().add(panel1);

		JLabel title_label = new JLabel("Home Page");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setForeground(new Color(255, 255, 255));
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		panel1.add(title_label);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 204));
		panel.setBounds(10, 75, 308, 462);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel Login_panel = new JPanel();
		Login_panel.setBackground(new Color(255, 255, 255));
		Login_panel.setBounds(10, 50, 288, 164);
		panel.add(Login_panel);
		Login_panel.setLayout(null);

		JPanel LoginTitle_panel = new JPanel();
		LoginTitle_panel.setBackground(new Color(102, 153, 204));
		LoginTitle_panel.setBounds(0, 0, 288, 32);
		Login_panel.add(LoginTitle_panel);

		JLabel LoginTitle_Label = new JLabel("Login");
		LoginTitle_Label.setForeground(new Color(255, 255, 255));
		LoginTitle_Label.setHorizontalAlignment(SwingConstants.CENTER);
		LoginTitle_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		LoginTitle_panel.add(LoginTitle_Label);

		JLabel Phone_Label = new JLabel("Phone : ");
		Phone_Label.setBackground(new Color(255, 255, 255));
		Phone_Label.setForeground(new Color(0, 102, 153));
		Phone_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Phone_Label.setBounds(10, 50, 66, 15);
		Login_panel.add(Phone_Label);

		Phone_textField = new JTextField();
		Phone_textField.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		Phone_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Phone_textField.setForeground(new Color(0, 102, 153));
		Phone_textField.setColumns(10);
		Phone_textField.setBounds(78, 50, 200, 21);
		Login_panel.add(Phone_textField);

		JLabel Password_Label = new JLabel("Password :");
		Password_Label.setForeground(new Color(0, 102, 153));
		Password_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Password_Label.setBackground(new Color(255, 255, 255));
		Password_Label.setBounds(10, 80, 98, 15);
		Login_panel.add(Password_Label);

		Password_textField = new JPasswordField();
		Password_textField.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		Password_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Password_textField.setForeground(new Color(0, 102, 153));
		Password_textField.setColumns(10);
		Password_textField.setBounds(99, 80, 179, 21);
		Login_panel.add(Password_textField);

		final JButton login_Button = new JButton("Login");
		login_Button.setForeground(new Color(102, 153, 204));
		login_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		login_Button.setBackground(new Color(255,255,255));
		login_Button.setBounds(107, 120, 80, 30);
		login_Button.setOpaque(true);
		login_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phone = Phone_textField.getText();
				pw = Password_textField.getText();
				Connection conn2 = null;
				conn2 = Sql_Connection.connection_mysql();
				PreparedStatement st2;
				try {
					st2 = conn2.prepareStatement("SELECT * FROM COURIER WHERE Courier_Phone = ?");
					st2.setString(1, phone);
					ResultSet result2 = st2.executeQuery();
					String cou_ID = "";
					if (result2.next() && (result2.getString("Courier_Password")).equals(pw)) {
						cou_ID = result2.getString("Courier_ID");
						Courier courier = new Courier();
						courier.logIn(phone, pw);
						Cou_Manager cou_manager = new Cou_Manager(cou_ID);
						cou_manager.frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(false);
					} else {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(login_Button, "Wrong Phone or Password!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Login_panel.add(login_Button);

		JLabel welcome_Label = new JLabel("Welcome to the \"Delivery Platform\"!");
		welcome_Label.setHorizontalAlignment(SwingConstants.CENTER);
		welcome_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		welcome_Label.setBounds(10, 15, 288, 22);
		panel.add(welcome_Label);

		JLabel Register_Label = new JLabel("If you don't have an account...");
		Register_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Register_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Register_Label.setBounds(10, 230, 288, 22);
		panel.add(Register_Label);

		JLabel Register_Label1 = new JLabel("Choose the identity you want to register.");
		Register_Label1.setHorizontalAlignment(SwingConstants.CENTER);
		Register_Label1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Register_Label1.setBounds(10, 249, 288, 22);
		panel.add(Register_Label1);

		JButton CusRegister_Button = new JButton("Customer");
		CusRegister_Button.setForeground(new Color(255, 153,153 ));
		CusRegister_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		CusRegister_Button.setBackground(new Color(255, 153, 153));
		CusRegister_Button.setBounds(10, 281, 140, 63);
		CusRegister_Button.setOpaque(true);
		panel.add(CusRegister_Button);

		JButton CouRegister_Button = new JButton("Courier");
		CouRegister_Button.setForeground(new Color(255, 153, 153));
		CouRegister_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		CouRegister_Button.setBackground(new Color(255, 153, 153));
		CouRegister_Button.setBounds(159, 281, 140, 63);
		CouRegister_Button.setOpaque(true);
		CouRegister_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Courier_Register cou_register = new Courier_Register();
				cou_register.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel.add(CouRegister_Button);
		frame.setBounds(100, 100, 342, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
