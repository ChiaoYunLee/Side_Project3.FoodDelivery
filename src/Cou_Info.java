import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class Cou_Info {

	JFrame frame;
	static String cou_ID = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cou_Info window = new Cou_Info(cou_ID);
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
	public Cou_Info(String cou_ID) {
		this.cou_ID = cou_ID;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String couName = "";
		String couSex = "";
		String couPhone = "";
		String couEmail = "";
		String couPW = "";
		String couJoin = "";
		String couSalary = "";
		String couBonus = "";

		Connection conn2 = null;
		conn2 = Sql_Connection.connection_mysql();
		PreparedStatement st2;
		try {
			st2 = conn2.prepareStatement("SELECT * FROM COURIER WHERE Courier_ID = ?");
			st2.setString(1, cou_ID);
			ResultSet result2 = st2.executeQuery();
			if (result2.next()) {
				couName = result2.getString("Courier_Name");
				couSex = result2.getString("Courier_Sex");
				couPhone = result2.getString("Courier_Phone");
				couEmail = result2.getString("Courier_Email");
				couPW = result2.getString("Courier_Password");
				couJoin = result2.getString("Courier_JoinDate");
				couSalary = result2.getString("Courier_Salary");
				couBonus = result2.getString("Courier_Bonus");

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 204, 204));
		panel2.setBounds(10, 75, 308, 462);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);

		JButton Back_Button = new JButton("Back");
		Back_Button.setBounds(115, 415, 85, 29);
		Back_Button.setForeground(new Color(255, 153, 153));
		Back_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Back_Button.setBackground(new Color(255, 153, 153));
		Back_Button.setOpaque(true);
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cou_Manager cou_manager = new Cou_Manager(cou_ID);
				cou_manager.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Back_Button);

		JLabel title_label = new JLabel("Personal Information");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		title_label.setForeground(new Color(255, 255, 255));
		panel1.add(title_label);

		JLabel ID_Label = new JLabel("ID :   " + cou_ID);
		ID_Label.setForeground(new Color(0, 51, 51));
		ID_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		ID_Label.setBounds(24, 30, 270, 20);
		panel2.add(ID_Label);

		JLabel Name_Label = new JLabel("Name :   " + couName);
		Name_Label.setForeground(new Color(0, 51, 51));
		Name_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Name_Label.setBounds(24, 60, 270, 20);
		panel2.add(Name_Label);

		JLabel Sex_Label = new JLabel("Sex :   " + couSex);
		Sex_Label.setForeground(new Color(0, 51, 51));
		Sex_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Sex_Label.setBounds(24, 90, 270, 20);
		panel2.add(Sex_Label);

		JLabel Phone_Label = new JLabel("Phone :   " + couPhone);
		Phone_Label.setForeground(new Color(0, 51, 51));
		Phone_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Phone_Label.setBounds(24, 120, 270, 20);
		panel2.add(Phone_Label);

		JLabel Email_Label = new JLabel("E-mail :   " + couEmail);
		Email_Label.setForeground(new Color(0, 51, 51));
		Email_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Email_Label.setBounds(24, 150, 270, 20);
		panel2.add(Email_Label);

		JLabel password_Label = new JLabel("Password :   " + couPW);
		password_Label.setForeground(new Color(0, 51, 51));
		password_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		password_Label.setBounds(24, 180, 270, 20);
		panel2.add(password_Label);

		JLabel JoinDate_Label = new JLabel("Join Date :   " + couJoin);
		JoinDate_Label.setForeground(new Color(0, 51, 51));
		JoinDate_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		JoinDate_Label.setBounds(24, 210, 270, 20);
		panel2.add(JoinDate_Label);

		JLabel Salary_Label = new JLabel("Salary :   $" + couSalary + " + $" + couBonus + " (Bonus)");
		Salary_Label.setForeground(new Color(0, 51, 51));
		Salary_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Salary_Label.setBounds(24, 240, 270, 20);
		panel2.add(Salary_Label);

	}
}

