import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CorrelatedGroupTForm {

	private ArrayList<Integer> firstData = new ArrayList<Integer>();
	private ArrayList<Integer> secondData = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>();
	private String group1Label = "";
	private String group2Label = "";
	private ArrayList<String> labelList = new ArrayList<String>();
	private String typeOfTest = "";
	private double criticalValue;
	
	private JFrame frmCorrelatedGroupTtest;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField txtGroupLabel;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void NewCorrelatedGroupTForm() {	
	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CorrelatedGroupTForm window = new CorrelatedGroupTForm();
					window.frmCorrelatedGroupTtest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CorrelatedGroupTForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCorrelatedGroupTtest = new JFrame();
		frmCorrelatedGroupTtest.setTitle("Correlated Group T-Test Equation");
		frmCorrelatedGroupTtest.setBounds(100, 100, 1027, 737);
		frmCorrelatedGroupTtest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		textField = new JTextField();
		textField.setText("Group 1 Label");
		textField.setColumns(10);
		
		JButton button = new JButton("Insert");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				group1Label = textField.getText();
				System.out.println(group1Label);
				firstData.clear();
				
				for(int col = 0; col < 2; col++) {
					for(int row = 0; row < 10; row++) {
						if(table.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table.getModel().getValueAt(row, col);
							firstData.add(tmp);		
							System.out.println("List 1:" + tmp);
						}
						
					}
				}
				/*for(int i = 0; i < firstData.size(); i++ ) {
					System.out.println(firstData.get(i));
				}*/
				
				
			}
		});
		
		txtGroupLabel = new JTextField();
		txtGroupLabel.setText("Group 2 Label");
		txtGroupLabel.setColumns(10);
		
		JButton button_1 = new JButton("Insert");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				group2Label = txtGroupLabel.getText();
				System.out.println(group2Label);
				secondData.clear();
				for(int col = 0; col < 2; col++) {
					for(int row = 0; row < 10; row++) {
						if(table_1.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table_1.getModel().getValueAt(row, col);
							secondData.add(tmp);
							System.out.println("List 2: " + tmp);	
						}	
					}
				}
				
			}
		});
		
		JLabel label = new JLabel("Critical Value");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel label_1 = new JLabel("Type of Test");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton button_2 = new JButton("Insert");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				criticalValue = Double.parseDouble(textField_1.getText());
				typeOfTest = textField_1.getText();
				
			}
		});
		
		JButton button_3 = new JButton("Give me those Results!");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalList.add(firstData);
				totalList.add(secondData);
				
				labelList.add(group1Label);
				labelList.add(group2Label);
				
				System.out.println(totalList.toString());
				CorrelatedGroupsTTest test2 = new CorrelatedGroupsTTest(totalList, labelList, criticalValue, typeOfTest);
				test2.calculateStatistics();
				System.out.println(test2.printStatistics());
				
				String userHomeFolder = System.getProperty("user.home");
				File textFile = new File(userHomeFolder, "CorrelatedGroupTForm.txt");
				try {

					BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
					out.write(test2.printStatistics());
					out.close();
					System.out.println("Did it!");
				} catch (IOException x) {
					System.out.println("Didnt work");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmCorrelatedGroupTtest.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(116)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(131)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(51)
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(83)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
												.addComponent(label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
												.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
												.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
											.addGap(24))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(161)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtGroupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(461)
							.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)))
					.addGap(38))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtGroupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addGap(60)
					.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(102))
		);
		
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
				"Data2", "Data2"
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
		frmCorrelatedGroupTtest.getContentPane().setLayout(groupLayout);
	}
}
