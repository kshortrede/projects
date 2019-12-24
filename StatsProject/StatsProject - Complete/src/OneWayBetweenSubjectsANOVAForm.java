import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class OneWayBetweenSubjectsANOVAForm {

	//User's input
	private ArrayList<Integer> firstData = new ArrayList<Integer>();
	private ArrayList<Integer> secondData = new ArrayList<Integer>();
	private ArrayList<Integer> thirdData = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>(); 
	
	private String group1Label = "";
	private String group2Label = "";
	private String group3Label = "";
	private ArrayList<String> labelList = new ArrayList<String>();
	
	private double criticalValue;
	
	private JFrame frmOneWayBetween;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField txtGroupLabel;
	private JTextField txtGroupLabel_1;
	private JTextField txtGroupLabel_2;
	private JTextField criticalTextField;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void NewOneWayBetweenSubjectsANOVAForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneWayBetweenSubjectsANOVAForm window = new OneWayBetweenSubjectsANOVAForm();
					window.frmOneWayBetween.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OneWayBetweenSubjectsANOVAForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOneWayBetween = new JFrame();
		frmOneWayBetween.setTitle("One Way Between Subjects ANOVA Equation");
		frmOneWayBetween.setBounds(100, 100, 1140, 740);
		frmOneWayBetween.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		txtGroupLabel = new JTextField();
		txtGroupLabel.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupLabel.setText("Group 1 Label");
		txtGroupLabel.setColumns(10);
		
		txtGroupLabel_1 = new JTextField();
		txtGroupLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupLabel_1.setText("Group 2 Label");
		txtGroupLabel_1.setColumns(10);
		
		txtGroupLabel_2 = new JTextField();
		txtGroupLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupLabel_2.setText("Group 3 Label");
		txtGroupLabel_2.setColumns(10);
		
		JButton btnInsertAllData = new JButton("Insert All Data");
		btnInsertAllData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInsertAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Restart the different lists with data
				firstData.clear();
				secondData.clear();
				thirdData.clear();
				
				group1Label = txtGroupLabel.getText();
				group2Label = txtGroupLabel_1.getText();
				group3Label = txtGroupLabel_2.getText();
				
				labelList.add(group1Label);
				labelList.add(group2Label);
				labelList.add(group3Label);
				
				criticalValue = Double.parseDouble(criticalTextField.getText());
				
				for(int row = 0; row < 10; row++) {
					for(int col = 0; col < 2; col++) {
						if(table.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table.getModel().getValueAt(row, col);
							firstData.add(tmp);		
						}
						if(table_1.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table_1.getModel().getValueAt(row, col);
							secondData.add(tmp);		
						}
						if(table_2.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table_2.getModel().getValueAt(row, col);
							thirdData.add(tmp);		
						}
					}
				}
				
			}
		});
		
		JButton btnNewButton = new JButton("Show me those results!");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				totalList.add(firstData);
				totalList.add(secondData);
				totalList.add(thirdData);
				System.out.println(totalList.toString());
				
				OneWayBetweenSubjectsANOVA test4 = new OneWayBetweenSubjectsANOVA(totalList, labelList, criticalValue);
				
				test4.calculateStatistics();
				System.out.println(test4.printStatistics());
				
				String userHomeFolder = System.getProperty("user.home");
				File textFile = new File(userHomeFolder, "ANOVA-OneWayBetweenSubjects.txt");
				try {

					BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
					out.write(test4.printStatistics());
					out.close();
					System.out.println("Did it!");
				} catch (IOException x) {
					System.out.println("Didnt work");
				}
			}
		});
		
		criticalTextField = new JTextField();
		criticalTextField.setColumns(10);
		
		JLabel lblInsertCriticalValue = new JLabel("Insert Critical Value");
		lblInsertCriticalValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertCriticalValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(frmOneWayBetween.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(criticalTextField)
						.addComponent(txtGroupLabel))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(262)
							.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
							.addComponent(txtGroupLabel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(178))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(221)
							.addComponent(btnInsertAllData, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(468, Short.MAX_VALUE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInsertCriticalValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
					.addGap(118)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
							.addGap(95)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtGroupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGroupLabel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(94)
							.addComponent(btnInsertAllData, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addGap(82)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(lblInsertCriticalValue, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(criticalTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(52))
		);
		
		table_2 = new JTable();
		table_2.setRowHeight(30);
		table_2.setModel(new DefaultTableModel(
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
				"Data 3", "Data 3"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_2.setViewportView(table_2);
		
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
				"Data 2", "Data 2"
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
		frmOneWayBetween.getContentPane().setLayout(groupLayout);
	}
}
