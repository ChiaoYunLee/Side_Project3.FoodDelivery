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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class Cou_PastReview {

	JFrame frame;
	static String cou_ID = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cou_PastReview window = new Cou_PastReview(cou_ID);
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
	public Cou_PastReview(String cou_ID) {
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

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 204, 204));
		panel2.setBounds(10, 75, 308, 462);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);

		JLabel title_label = new JLabel("Past Order Evaluation");
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

		JTextPane past_TextArea = new JTextPane();
		past_TextArea.setContentType("");
		past_TextArea.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		past_TextArea.setForeground(new Color(102, 153, 204));
		past_TextArea.setEditable(false);

		Connection conn3 = null;
		conn3 = Sql_Connection.connection_mysql();
		PreparedStatement st3;
		try {
			st3 = conn3.prepareStatement(
					"SELECT Order_ID, GROUP_CONCAT(COURIER_REVIEW.Comment_Description) AS descrip, SUM(Point) AS point FROM REVIEW, COURIER_REVIEW, COMMENT WHERE Courier_ID=? AND Review_type='CO' AND Review_ID= CourierReview_ID AND COURIER_REVIEW.Comment_ID=COMMENT.Comment_ID GROUP BY Order_ID");
			st3.setString(1, cou_ID);
			ResultSet result3 = st3.executeQuery();
			String pastOrder = "";
			while (result3.next()) {
				pastOrder += "# " + result3.getString("Order_ID") + "\n" + result3.getString("descrip") + "    "
						+ result3.getString("point") + "\n\n";
			}
			past_TextArea.setText(pastOrder);

			JScrollPane scroll = new JScrollPane(past_TextArea);
			scroll.setBounds(10, 23, 288, 373);
			panel2.add(scroll);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

