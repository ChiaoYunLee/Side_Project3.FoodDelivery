import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Cou_Point {

	JFrame frame;
	static String cou_ID = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cou_Point window = new Cou_Point(cou_ID);
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
	public Cou_Point(String cou_ID) {
		this.cou_ID = cou_ID;
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

		final JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 204, 204));
		panel2.setBounds(10, 75, 308, 462);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);

		JLabel title_label = new JLabel("Points Management");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		title_label.setForeground(new Color(255, 255, 255));
		panel1.add(title_label);

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

		JLabel pointTitle_Label = new JLabel("Accumulated points in this month : ");
		pointTitle_Label.setForeground(new Color(0, 51, 51));
		pointTitle_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		pointTitle_Label.setBounds(24, 30, 250, 20);
		panel2.add(pointTitle_Label);

		JLabel salaryBonus_Label = new JLabel("Salary can be increased by (Bonus) : ");
		salaryBonus_Label.setForeground(new Color(0, 51, 51));
		salaryBonus_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		salaryBonus_Label.setBounds(24, 170, 260, 20);
		panel2.add(salaryBonus_Label);

		JPanel point_panel = new JPanel();
		point_panel.setBorder(new LineBorder(new Color(255, 153, 153), 4));
		point_panel.setBackground(new Color(255, 255, 255));
		point_panel.setBounds(24, 60, 260, 86);
		panel2.add(point_panel);
		point_panel.setLayout(null);

		final JLabel point_Label = new JLabel("");
		point_Label.setHorizontalAlignment(SwingConstants.CENTER);
		point_Label.setBackground(new Color(255, 255, 255));
		point_Label.setForeground(new Color(102, 153, 204));
		point_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		point_Label.setBounds(10, 22, 240, 40);
		Connection conn = null;
		conn = Sql_Connection.connection_mysql();
		PreparedStatement st;
		int curPoint = 0;
		try {
			st = conn.prepareStatement("SELECT Courier_Point FROM COURIER WHERE Courier_ID = ?");
			st.setString(1, cou_ID);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				curPoint = Integer.parseInt(result.getString("Courier_Point"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		point_Label.setText(curPoint + "  points");
		point_panel.add(point_Label);

		JPanel bonus_panel = new JPanel();
		bonus_panel.setBorder(new LineBorder(new Color(255, 153, 153), 4));
		bonus_panel.setBackground(Color.WHITE);
		bonus_panel.setBounds(24, 200, 260, 86);
		panel2.add(bonus_panel);
		bonus_panel.setLayout(null);

		final JLabel bonus_Label = new JLabel("");
		bonus_Label.setHorizontalAlignment(SwingConstants.CENTER);
		bonus_Label.setForeground(new Color(102, 153, 204));
		bonus_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		bonus_Label.setBackground(Color.WHITE);
		bonus_Label.setBounds(10, 23, 240, 40);
		Connection conn2 = null;
		conn2 = Sql_Connection.connection_mysql();
		PreparedStatement st2;
		int bonus = 0;
		try {
			st2 = conn2.prepareStatement("SELECT Courier_Bonus,Courier_Salary FROM COURIER WHERE Courier_ID = ?");
			st2.setString(1, cou_ID);
			ResultSet result2 = st2.executeQuery();
			if (result2.next()) {
				bonus = Integer.parseInt(result2.getString("Courier_Bonus"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (bonus == 1250) {
			bonus_Label.setText(" $ " + bonus + " (5%)");
		} else if (bonus == 2000) {
			bonus_Label.setText(" $ " + bonus + " (8%)");
		} else if (bonus == 3750) {
			bonus_Label.setText(" $ " + bonus + " (15%)");
		} else if (bonus == 6250) {
			bonus_Label.setText(" $ " + bonus + " (25%)");
		} else {
			bonus_Label.setText(" $ 0 (0%)");
		}
		bonus_panel.add(bonus_Label);

		JLabel bonusCon_Label = new JLabel("Press the 'Convert' button, then will");
		bonusCon_Label.setForeground(new Color(0, 51, 51));
		bonusCon_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		bonusCon_Label.setBounds(24, 290, 260, 20);
		panel2.add(bonusCon_Label);

		JLabel bonusCon1_Label = new JLabel("convert points into salary bonus.");
		bonusCon1_Label.setForeground(new Color(0, 51, 51));
		bonusCon1_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		bonusCon1_Label.setBounds(24, 310, 260, 20);
		panel2.add(bonusCon1_Label);

		JButton bonus_Button = new JButton("Convert");
		bonus_Button.setOpaque(true);
		bonus_Button.setForeground(new Color(102, 153, 204));
		bonus_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		bonus_Button.setBackground(new Color(102, 153, 204));
		bonus_Button.setBounds(107, 338, 100, 29);
		bonus_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int curPoint = 0;
					Connection conn3 = null;
					conn3 = Sql_Connection.connection_mysql();
					PreparedStatement st3;
					st3 = conn3.prepareStatement("SELECT Courier_Point FROM COURIER WHERE Courier_ID = ?");
					st3.setString(1, cou_ID);
					ResultSet result3 = st3.executeQuery();
					while (result3.next()) {
						curPoint = Integer.parseInt(result3.getString("Courier_Point"));
					}
					PreparedStatement st4;
					st4 = conn3.prepareStatement("UPDATE dbms_group5.COURIER SET Courier_Bonus= ? WHERE Courier_ID= ?");
					st4.setString(2, cou_ID);
					if (curPoint >= 2500 && curPoint < 3000) {
						st4.setString(1, "1250");
						st4.executeUpdate();
						PreparedStatement st5;
						st5 = conn3.prepareStatement(
								"UPDATE dbms_group5.COURIER SET Courier_Point=Courier_Point-2500 WHERE Courier_ID= ?");
						st5.setString(1, cou_ID);
						st5.executeUpdate();
						PreparedStatement st6;
						st6 = conn3.prepareStatement(
								"SELECT Courier_Bonus,Courier_Point FROM COURIER WHERE Courier_ID = ?");
						st6.setString(1, cou_ID);
						ResultSet result6 = st6.executeQuery();
						int bonus = 0;
						if (result6.next()) {
							bonus = Integer.parseInt(result6.getString("Courier_Bonus"));
							curPoint = Integer.parseInt(result6.getString("Courier_Point"));
						}
						point_Label.setText(curPoint + "  points");
						bonus_Label.setText(" $ " + bonus + " (5%)");
					} else if (curPoint >= 3000 && curPoint < 3500) {
						st4.setString(1, "2000");
						st4.executeUpdate();
						PreparedStatement st5;
						st5 = conn3.prepareStatement(
								"UPDATE dbms_group5.COURIER SET Courier_Point=Courier_Point-3000 WHERE Courier_ID= ?");
						st5.setString(1, cou_ID);
						st5.executeUpdate();
						PreparedStatement st6;
						st6 = conn3.prepareStatement(
								"SELECT Courier_Bonus,Courier_Point FROM COURIER WHERE Courier_ID = ?");
						st6.setString(1, cou_ID);
						ResultSet result6 = st6.executeQuery();
						int bonus = 0;
						if (result6.next()) {
							bonus = Integer.parseInt(result6.getString("Courier_Bonus"));
							curPoint = Integer.parseInt(result6.getString("Courier_Point"));
						}
						point_Label.setText(curPoint + "  points");
						bonus_Label.setText(" $ " + bonus + " (8%)");
					} else if (curPoint >= 3500 && curPoint < 4000) {
						st4.setString(1, "3750");
						st4.executeUpdate();
						PreparedStatement st5;
						st5 = conn3.prepareStatement(
								"UPDATE dbms_group5.COURIER SET Courier_Point=Courier_Point-3500 WHERE Courier_ID= ?");
						st5.setString(1, cou_ID);
						st5.executeUpdate();
						PreparedStatement st6;
						st6 = conn3.prepareStatement(
								"SELECT Courier_Bonus,Courier_Point FROM COURIER WHERE Courier_ID = ?");
						st6.setString(1, cou_ID);
						ResultSet result6 = st6.executeQuery();
						int bonus = 0;
						if (result6.next()) {
							bonus = Integer.parseInt(result6.getString("Courier_Bonus"));
							curPoint = Integer.parseInt(result6.getString("Courier_Point"));
						}
						point_Label.setText(curPoint + "  points");
						bonus_Label.setText(" $ " + bonus + " (15%)");
					} else if (curPoint >= 4000) {
						st4.setString(1, "6250");
						st4.executeUpdate();
						PreparedStatement st5;
						st5 = conn3.prepareStatement(
								"UPDATE dbms_group5.COURIER SET Courier_Point=Courier_Point-4000 WHERE Courier_ID= ?");
						st5.setString(1, cou_ID);
						st5.executeUpdate();
						PreparedStatement st6;
						st6 = conn3.prepareStatement(
								"SELECT Courier_Bonus,Courier_Point FROM COURIER WHERE Courier_ID = ?");
						st6.setString(1, cou_ID);
						ResultSet result6 = st6.executeQuery();
						int bonus = 0;
						if (result6.next()) {
							bonus = Integer.parseInt(result6.getString("Courier_Bonus"));
							curPoint = Integer.parseInt(result6.getString("Courier_Point"));
						}
						point_Label.setText(curPoint + " points");
						bonus_Label.setText(" $ " + bonus + " (25%)");
					} else {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(panel2, "You don't have enough points to convert into bonus!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel2.add(bonus_Button);
	}
}

