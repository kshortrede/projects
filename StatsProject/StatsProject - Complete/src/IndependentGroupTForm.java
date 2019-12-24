import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class IndependentGroupTForm {

	private JFrame frmIndependentGroupTtest;
	private JTable table;
	private JTable table_1;
	
	//Will take all the values from the user in, when the button is pressed
	private ArrayList<Integer> firstData = new ArrayList<Integer>();
	private ArrayList<Integer> secondData = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>();
	private String group1Label = "";
	private String group2Label = "";
	private ArrayList<String> labelList = new ArrayList<String>();
	private double criticalValue;
	private String typeOfTest = "";
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtGroupLabel;
	private JTextField txtGroupLabel_1;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void NewIndependentGrouptTFOrm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndependentGroupTForm window = new IndependentGroupTForm();
					window.frmIndependentGroupTtest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IndependentGroupTForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIndependentGroupTtest = new JFrame();
		frmIndependentGroupTtest.setTitle("Independent Group T-Test Equation");
		frmIndependentGroupTtest.setSize(975, 723);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				group1Label = txtGroupLabel.getText();
				System.out.println(group1Label);
				firstData.clear();
				for(int row = 0; row < 10; row++) {
					for(int col = 0; col < 2; col++) {
						if(table.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table.getModel().getValueAt(row, col);
							firstData.add(tmp);		
						}
						
					}
				}
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton button = new JButton("Insert");
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				group2Label = txtGroupLabel_1.getText();
				System.out.println(group2Label);
				secondData.clear();
				for(int row = 0; row < 10; row++) {
					for(int col = 0; col < 2; col++) {
						if(table_1.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table_1.getModel().getValueAt(row, col);
							secondData.add(tmp);
						}
					}
				}
			}
		});
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				criticalValue = Double.parseDouble(textField.getText());
				typeOfTest = textField_1.getText();
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Critical Value");
		
		JLabel lblTypeOfTest = new JLabel("Type of Test");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnGiveMeThose = new JButton("Give me those Results!");
		btnGiveMeThose.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnGiveMeThose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Call elliot's functions by sending all the input that we have
				totalList.add(firstData);
				totalList.add(secondData);
				
				labelList.add(group1Label);
				labelList.add(group2Label);
				
				System.out.println(totalList.toString());
				IndependentGroupsTTest test1 = new IndependentGroupsTTest(totalList, labelList, criticalValue, typeOfTest);
				
				test1.calculateStatistics();
				System.out.println(test1.printStatistics());
				
				String userHomeFolder = System.getProperty("user.home");
				File textFile = new File(userHomeFolder, "IndependentGroupTForm.txt");
				try {

					BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
					out.write(test1.printStatistics());
					out.close();
					System.out.println("Did it!");
				} catch (IOException x) {
					System.out.println("Didnt work");
				}
				
			}
		});
		
		txtGroupLabel = new JTextField();
		txtGroupLabel.setText("Group 1 Label");
		txtGroupLabel.setColumns(10);
		
		txtGroupLabel_1 = new JTextField();
		txtGroupLabel_1.setText("Group 2 Label");
		txtGroupLabel_1.setColumns(10);
		
		table_1 = new JTable();
		table_1.setRowHeight(30);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Data", "Data"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Data", "Data"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(frmIndependentGroupTtest.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(80)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(120)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(18)
											.addComponent(lblNewLabel))
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(24)
											.addComponent(lblTypeOfTest))
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(108)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtGroupLabel)
								.addComponent(btnInsert, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(198)
									.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(216)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(365, Short.MAX_VALUE)
					.addComponent(btnGiveMeThose, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
					.addGap(303))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel)
							.addGap(13)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(lblTypeOfTest)
							.addGap(18)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(74)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
					.addGap(115)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtGroupLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(70)
					.addComponent(btnGiveMeThose, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(51))
		);
		frmIndependentGroupTtest.getContentPane().setLayout(groupLayout);
	}
}
