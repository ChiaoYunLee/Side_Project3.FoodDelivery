import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class Cou_Order {

	JFrame frame;
	static String cou_ID = "";
	String cus_ID = "";
	String order_ID = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cou_Order window = new Cou_Order(cou_ID);
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
	public Cou_Order(String cou_ID) {
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

		JLabel title_label = new JLabel("Current Order");
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

		JTextPane curOrder_TextArea = new JTextPane();
		curOrder_TextArea.setForeground(new Color(102, 153, 204));
		curOrder_TextArea.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		curOrder_TextArea.setEditable(false);
		curOrder_TextArea.setContentType("");

		final ArrayList cusList = new ArrayList();
		final ArrayList orderList = new ArrayList();
		Connection conn4 = null;
		conn4 = Sql_Connection.connection_mysql();
		PreparedStatement st4;
		try {
			st4 = conn4.prepareStatement(
					"SELECT ORDER.Order_ID, Customer_ID, GROUP_CONCAT(ITEM.Item_Name) AS food, Food_Price + Delivery_Fee AS price, Order_PaymentMethod FROM dbms_group5.ORDER, ORDER_ITEM, ITEM WHERE Courier_ID IS NULL AND ORDER.Order_ID=ORDER_ITEM.Order_ID AND ORDER_ITEM.Item_ID= ITEM.Item_ID GROUP BY Order_ID");
			ResultSet result4 = st4.executeQuery();
			String curOrder = "";
			int index = 0;
			while (result4.next() && index < 2) {
				curOrder += "# " + result4.getString("Order_ID") + "\nCustomer: " + result4.getString("Customer_ID")
						+ "\nItem: " + result4.getString("food") + "\nTotal Amount: " + result4.getString("price")
						+ "\nPayment Method: " + result4.getString("Order_PaymentMethod") + "\n\n\n";
				cusList.add(result4.getString("Customer_ID"));
				orderList.add(result4.getString("Order_ID"));
				index += 1;
			}
			curOrder_TextArea.setText(curOrder);
			JScrollPane scroll = new JScrollPane(curOrder_TextArea);
			scroll.setBounds(10, 23, 288, 373);
			panel2.add(scroll);

		} catch (

		SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton accept_Button = new JButton("Accept");
		accept_Button.setForeground(new Color(255, 255, 255));
		accept_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		accept_Button.setBackground(new Color(102, 153, 204));
		accept_Button.setBounds(100, 115, 80, 20);
		accept_Button.setOpaque(true);
		accept_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cus_ID = cusList.get(0).toString();
				order_ID = orderList.get(0).toString();
				Connection conn6 = null;
				conn6 = Sql_Connection.connection_mysql();
				PreparedStatement st6;
				try {
					st6 = conn6.prepareStatement("UPDATE dbms_group5.ORDER SET Courier_ID= ? WHERE Order_ID= ?");
					st6.setString(1, cou_ID);
					st6.setString(2, order_ID);
					st6.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				check_Order check1 = new check_Order(cou_ID, cus_ID, order_ID);
				check1.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		curOrder_TextArea.add(accept_Button);

		JButton accept_Button1 = new JButton("Accept");
		accept_Button1.setForeground(new Color(255, 255, 255));
		accept_Button1.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		accept_Button1.setBackground(new Color(102, 153, 204));
		accept_Button1.setBounds(100, 262, 80, 20);
		accept_Button1.setOpaque(true);
		accept_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cus_ID = cusList.get(1).toString();
				order_ID = orderList.get(1).toString();
				Connection conn6 = null;
				conn6 = Sql_Connection.connection_mysql();
				PreparedStatement st6;
				try {
					st6 = conn6.prepareStatement("UPDATE dbms_group5.ORDER SET Courier_ID= ? WHERE Order_ID= ?");
					st6.setString(1, cou_ID);
					st6.setString(2, order_ID);
					st6.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				check_Order check1 = new check_Order(cou_ID, cus_ID, order_ID);
				check1.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		curOrder_TextArea.add(accept_Button1);

		JButton past_Button = new JButton("Credit");
		past_Button.setHorizontalAlignment(SwingConstants.CENTER);
		past_Button.setForeground(new Color(0, 51, 102));
		past_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		past_Button.setBackground(new Color(255, 255, 204));
		past_Button.setBounds(105, 28, 65, 15);
		past_Button.setOpaque(true);
		past_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cus_ID = cusList.get(0).toString();
				order_ID = orderList.get(0).toString();
				Cus_Credit credit = new Cus_Credit(cou_ID, cus_ID, order_ID);
				credit.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		curOrder_TextArea.add(past_Button);

		JButton past_Button1 = new JButton("Credit");
		past_Button1.setHorizontalAlignment(SwingConstants.CENTER);
		past_Button1.setForeground(new Color(0, 51, 102));
		past_Button1.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		past_Button1.setBackground(new Color(255, 255, 204));
		past_Button1.setBounds(105, 175, 65, 15);
		past_Button1.setOpaque(true);
		past_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cus_ID = cusList.get(1).toString();
				order_ID = orderList.get(1).toString();
				Cus_Credit credit1 = new Cus_Credit(cou_ID, cus_ID, order_ID);
				credit1.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		curOrder_TextArea.add(past_Button1);
	}
}
