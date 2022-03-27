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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Cus_Credit {

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
					Cus_Credit window = new Cus_Credit(cou_ID, cus_ID, order_ID);
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
	public Cus_Credit(String cou_ID, String cus_ID, String order_ID) {
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

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 204, 204));
		panel2.setBounds(10, 75, 308, 462);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);

		JLabel title_label = new JLabel("Customer Credit History");
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
				Cou_Order cou_order = new Cou_Order(cou_ID);
				cou_order.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Back_Button);

		JTextPane past_TextArea = new JTextPane();
		past_TextArea.setContentType("");
		past_TextArea.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		past_TextArea.setForeground(new Color(102, 153, 204));
		past_TextArea.setEditable(false);

		Connection conn4 = null;
		conn4 = Sql_Connection.connection_mysql();
		PreparedStatement st4;
		try {
			String cusCredit = "This customer's past reviews:\n";
			int curPoint = 0;
			PreparedStatement st5 = conn4.prepareStatement(
					"SELECT Order_ID, GROUP_CONCAT(CUSTOMER_REVIEW.Comment_Description) AS descrip, SUM(Point) AS point FROM REVIEW, CUSTOMER_REVIEW, COMMENT WHERE REVIEW.Customer_ID=? AND Review_type='CU' AND Review_ID= CustomerReview_ID AND CUSTOMER_REVIEW.Comment_ID=COMMENT.Comment_ID GROUP BY Order_ID");
			st5.setString(1, cus_ID);
			ResultSet result5 = st5.executeQuery();
			while (result5.next()) {
				if (Integer.parseInt(result5.getString("point")) < 0) {
					cusCredit += "# " + result5.getString("Order_ID") + "    " + result5.getString("descrip") + "    "
							+ result5.getString("point") + "\n";
				} else {
					cusCredit += "# " + result5.getString("Order_ID") + "    " + result5.getString("descrip") + "    +"
							+ result5.getString("point") + "\n";
				}
				curPoint += Integer.parseInt(result5.getString("point"));
			}
			past_TextArea.setText(cusCredit);

			JScrollPane scroll = new JScrollPane(past_TextArea);
			scroll.setBounds(10, 23, 288, 268);
			panel2.add(scroll);

			JPanel point_panel = new JPanel();
			point_panel.setLayout(null);
			point_panel.setBorder(new LineBorder(new Color(255, 153, 153), 4));
			point_panel.setBackground(Color.WHITE);
			point_panel.setBounds(23, 311, 260, 86);
			panel2.add(point_panel);

			JLabel point_Label = new JLabel("0  points");
			point_Label.setHorizontalAlignment(SwingConstants.CENTER);
			point_Label.setForeground(new Color(102, 153, 204));
			point_Label.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
			point_Label.setBackground(Color.WHITE);
			point_Label.setBounds(10, 22, 240, 40);
			point_Label.setText("Total :  " + curPoint + "  points");
			point_panel.add(point_Label);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

