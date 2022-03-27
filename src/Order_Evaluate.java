import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Order_Evaluate {

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
					Order_Evaluate window = new Order_Evaluate(cou_ID, cus_ID, order_ID);
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
	public Order_Evaluate(String cou_ID, String cus_ID, String order_ID) {
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

		JLabel title_label = new JLabel("Order Evaluation");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		title_label.setForeground(new Color(255, 255, 255));
		panel1.add(title_label);

		JButton Good_Button = new JButton("Give a Good Review");
		Good_Button.setForeground(new Color(102, 153, 204));
		Good_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		Good_Button.setBackground(new Color(255, 255, 255));
		Good_Button.setBounds(10, 20, 288, 72);
		Good_Button.setOpaque(true);
		Good_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Good_Evaluation goodEva = new Good_Evaluation(cou_ID, cus_ID, order_ID);
				goodEva.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Good_Button);

		JButton Past_Button = new JButton("Give a Bad Review");
		Past_Button.setOpaque(true);
		Past_Button.setForeground(new Color(102, 153, 204));
		Past_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		Past_Button.setBackground(new Color(255, 255, 255));
		Past_Button.setBounds(10, 110, 288, 72);
		Past_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bad_Evaluation badEva = new Bad_Evaluation(cou_ID, cus_ID, order_ID);
				badEva.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Past_Button);
	}

}
