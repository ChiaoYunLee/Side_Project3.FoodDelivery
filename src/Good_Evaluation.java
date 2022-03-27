import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Good_Evaluation {

	JFrame frame;
	static String cou_ID = "";
	static String cus_ID = "";
	static String order_ID = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Good_Evaluation window = new Good_Evaluation(cou_ID, cus_ID, order_ID);
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
	public Good_Evaluation(String cou_ID, String cus_ID, String order_ID) {
		this.cou_ID = cou_ID;
		this.cus_ID = cus_ID;
		this.order_ID = order_ID;
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

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 255));
		panel3.setBounds(10, 23, 288, 373);
		panel2.add(panel3);
		panel3.setLayout(null);

		JLabel context_Label = new JLabel("Comment : ");
		context_Label.setForeground(new Color(0, 51, 51));
		context_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		context_Label.setBounds(43, 30, 100, 20);
		panel3.add(context_Label);

		final JCheckBox CUG1_CheckBox = new JCheckBox("On time");
		CUG1_CheckBox.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		CUG1_CheckBox.setBackground(new Color(255, 204, 204));
		CUG1_CheckBox.setBounds(43, 60, 200, 27);
		panel3.add(CUG1_CheckBox);

		final JCheckBox CUG2_CheckBox = new JCheckBox("Friendly");
		CUG2_CheckBox.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		CUG2_CheckBox.setBackground(new Color(255, 204, 204));
		CUG2_CheckBox.setBounds(43, 90, 200, 27);
		panel3.add(CUG2_CheckBox);

		JButton Submit_Button = new JButton("Submit");
		Submit_Button.setOpaque(true);
		Submit_Button.setForeground(new Color(102, 153, 204));
		Submit_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Submit_Button.setBackground(new Color(102, 153, 204));
		Submit_Button.setBounds(102, 273, 85, 29);
		Submit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reviewID = "";
				int point = 0;
				try {
					Connection conn10 = null;
					conn10 = Sql_Connection.connection_mysql();
					Statement st10 = conn10.createStatement();
					ResultSet result10 = st10
							.executeQuery("SELECT Review_ID FROM REVIEW ORDER BY Review_ID DESC limit 1");
					if (result10.next()) {
						String lastID = result10.getString("Review_ID");
						String lastNum = lastID.substring(1, lastID.length());
						int currentNum = Integer.parseInt(lastNum) + 1;
						reviewID = "R" + currentNum;
					}
					PreparedStatement st11 = conn10
							.prepareStatement("INSERT INTO dbms_group5.REVIEW VALUES (?,?,?,?,?)");
					st11.setString(1, reviewID);
					st11.setString(2, order_ID);
					st11.setString(3, cus_ID);
					st11.setString(4, cou_ID);
					st11.setString(5, "CU");
					st11.executeUpdate();
					if (CUG1_CheckBox.isSelected() == false && CUG2_CheckBox.isSelected() == false) {
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(CUG1_CheckBox, "You don't check any reviews!");
					} else {
						ArrayList reviewList = new ArrayList();
						if (CUG1_CheckBox.isSelected() == true) {
							reviewList.add("CUG1");
						}
						if (CUG2_CheckBox.isSelected() == true) {
							reviewList.add("CUG2");
						}
						for (int i = 0; i < reviewList.size(); i++) {
							String review = reviewList.get(i).toString();
							String description = "";
							PreparedStatement st12 = conn10.prepareStatement(
									"SELECT Comment_Description,Point FROM COMMENT WHERE Comment_ID=? ");
							st12.setString(1, review);
							ResultSet result12 = st12.executeQuery();
							while (result12.next()) {
								description = result12.getString("Comment_Description");
								point += result12.getInt("Point");
							}
							PreparedStatement st13 = conn10
									.prepareStatement("INSERT INTO CUSTOMER_REVIEW VALUES (?,?,?)");
							st13.setString(1, reviewID);
							st13.setString(2, review);
							st13.setString(3, description);
							st13.executeUpdate();
						}
						JOptionPane finish = new JOptionPane();
						finish.showMessageDialog(panel2,"Evaluation is complete! \nPress the 'Back' Button, back to the management interface. ");
					}
					PreparedStatement st14 = conn10.prepareStatement(
							"UPDATE CUSTOMER SET Customer_Point = Customer_Point+ ? WHERE Customer_ID=?");
					st14.setInt(1, point);
					st14.setString(2, cus_ID);
					st14.executeUpdate();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		panel3.add(Submit_Button);

		JLabel title_label = new JLabel("Order Evaluation");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		title_label.setForeground(new Color(255, 255, 255));
		panel1.add(title_label);

		JButton Back_Button = new JButton("Back");
		Back_Button.setBounds(115, 415, 85, 29);
		Back_Button.setForeground(Color.WHITE);
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
	}
}

