import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainForm {

	private JFrame frmMainMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainMenu = new JFrame();
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.setBounds(100, 100, 904, 620);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Correlated Group");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CorrelatedGroupTForm tmp = new CorrelatedGroupTForm();
				tmp.NewCorrelatedGroupTForm();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(274, 158, 336, 108);
		frmMainMenu.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Independent Group");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				IndependentGroupTForm tmp = new IndependentGroupTForm();
				tmp.NewIndependentGrouptTFOrm();
			}
		});
		btnNewButton_1.setBounds(83, 279, 336, 108);
		frmMainMenu.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("One-Way Repeated ANOVA ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OneWayRepeatedANOVA tmp = new OneWayRepeatedANOVA();
				tmp.NewOneWayRepeatedANOVA();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(475, 279, 336, 108);
		frmMainMenu.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("One-Way Between Subjects ANOVA");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OneWayBetweenSubjectsANOVAForm tmp = new OneWayBetweenSubjectsANOVAForm();
				tmp.NewOneWayBetweenSubjectsANOVAForm();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(83, 400, 336, 108);
		frmMainMenu.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("2x2 Between Subjects ANOVA");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TwoTwoBetweenSubjectsANOVAForm tmp = new TwoTwoBetweenSubjectsANOVAForm();
				tmp.NewTwoTwoBetweenSubjectsANOVAForm();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_4.setBounds(475, 400, 336, 108);
		frmMainMenu.getContentPane().add(btnNewButton_4);
		
		JLabel lblSelectYourDesired = new JLabel("Select your desired equation");
		lblSelectYourDesired.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectYourDesired.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSelectYourDesired.setBounds(83, 50, 728, 53);
		frmMainMenu.getContentPane().add(lblSelectYourDesired);
	}
}
