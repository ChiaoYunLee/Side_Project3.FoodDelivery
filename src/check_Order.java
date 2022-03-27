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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class check_Order {

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
					check_Order window = new check_Order(cou_ID, cus_ID, order_ID);
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
	public check_Order(String cou_ID, String cus_ID, String order_ID) {
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

		JTextPane orderDetail_TextArea = new JTextPane();
		orderDetail_TextArea.setForeground(new Color(102, 153, 204));
		orderDetail_TextArea.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		orderDetail_TextArea.setEditable(false);
		orderDetail_TextArea.setContentType("");
		orderDetail_TextArea.setBounds(10, 24, 286, 366);
		Connection conn4 = null;
		conn4 = Sql_Connection.connection_mysql();
		PreparedStatement st4;
		try {
			st4 = conn4.prepareStatement(
					"SELECT ORDER.Order_ID, Customer_ID, GROUP_CONCAT(ITEM.Item_Name) AS food, Food_Price, Delivery_Fee, Food_Price+Delivery_Fee AS price, Order_PaymentMethod FROM dbms_group5.ORDER, ORDER_ITEM, ITEM WHERE ORDER.Order_ID =? AND ORDER.Order_ID=ORDER_ITEM.Order_ID AND ORDER_ITEM.Item_ID= ITEM.Item_ID GROUP BY Order_ID");
			st4.setString(1, order_ID);
			ResultSet result4 = st4.executeQuery();
			String curOrder = "";
			while (result4.next()) {
				curOrder += "# " + result4.getString("Order_ID") + "\n\nCustomer: " + result4.getString("Customer_ID")
						+ "\n\nItem: \n" + result4.getString("food") + "\n\nFood Price: "
						+ result4.getString("Food_Price") + "\nDelivery Fee: " + result4.getString("Delivery_Fee")
						+ "\nTotal Amount: " + result4.getString("price") + "\n\nPayment Method: "
						+ result4.getString("Order_PaymentMethod") + "\n";
			}
			orderDetail_TextArea.setText(curOrder);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel2.add(orderDetail_TextArea);

		JLabel title_label = new JLabel("Current Order");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		title_label.setForeground(new Color(255, 255, 255));
		panel1.add(title_label);

		JButton Finish_Button = new JButton("Order Finish");
		Finish_Button.setBounds(96, 415, 140, 29);//96, 415, 120, 29
		Finish_Button.setForeground(new Color(255, 153, 153));
		Finish_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		Finish_Button.setBackground(new Color(255, 153, 153));
		Finish_Button.setOpaque(true);
		Finish_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order_Evaluate orderEva = new Order_Evaluate(cou_ID, cus_ID, order_ID);
				orderEva.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Finish_Button);
	}
}
