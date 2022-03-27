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

public class Cou_Manager {

	JFrame frame;
	static String cou_ID = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cou_Manager window = new Cou_Manager(cou_ID);
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
	public Cou_Manager(String cou_ID) {
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

		JButton Info_Button = new JButton("Personal Information");
		Info_Button.setForeground(new Color(102, 153, 204));
		Info_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		Info_Button.setBackground(new Color(255, 255, 255));
		Info_Button.setBounds(10, 20, 288, 72);
		Info_Button.setOpaque(true);
		Info_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cou_Info info = new Cou_Info(cou_ID);
				info.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Info_Button);

		JButton Past_Button = new JButton("Past Order Evaluation");
		Past_Button.setOpaque(true);
		Past_Button.setForeground(new Color(102, 153, 204));
		Past_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		Past_Button.setBackground(new Color(255, 255, 255));
		Past_Button.setBounds(10, 110, 288, 72);
		Past_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cou_PastReview past = new Cou_PastReview(cou_ID);
				past.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Past_Button);

		JButton Point_Button = new JButton("Points Management");
		Point_Button.setOpaque(true);
		Point_Button.setForeground(new Color(102, 153, 204));
		Point_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		Point_Button.setBackground(new Color(255, 255, 255));
		Point_Button.setBounds(10, 200, 288, 72);
		Point_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cou_Point point = new Cou_Point(cou_ID);
				point.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Point_Button);

		JButton Order_Button = new JButton("Receive Order");
		Order_Button.setOpaque(true);
		Order_Button.setForeground(new Color(102, 153, 204));
		Order_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		Order_Button.setBackground(new Color(255, 255, 255));
		Order_Button.setBounds(10, 290, 288, 72);
		Order_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cou_Order order = new Cou_Order(cou_ID);
				order.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(Order_Button);

		JButton signOut_Button = new JButton("Sign Out");
		signOut_Button.setBounds(109, 395,96, 29);//109, 395, 96, 29
		signOut_Button.setForeground(new Color(102, 153, 204));
		signOut_Button.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		signOut_Button.setBackground(new Color(102, 153, 204));
		signOut_Button.setOpaque(true);
		signOut_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage home = new Homepage();
				home.frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(false);
			}
		});
		panel2.add(signOut_Button);

		JLabel title_label = new JLabel("Courier Management");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		title_label.setForeground(new Color(255, 255, 255));
		panel1.add(title_label);

	}
}
