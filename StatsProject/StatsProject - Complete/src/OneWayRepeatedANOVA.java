import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class OneWayRepeatedANOVA {

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
	
	private JFrame frmOneWayRepeated;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField txtGroupLabel;
	private JTextField txtGroupLabel_1;
	private JTextField txtGroupLabel_2;
	private JTextField txtCriticalValue;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void NewOneWayRepeatedANOVA() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneWayRepeatedANOVA window = new OneWayRepeatedANOVA();
					window.frmOneWayRepeated.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OneWayRepeatedANOVA() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOneWayRepeated = new JFrame();
		frmOneWayRepeated.setTitle("One Way Repeated ANOVA Equation");
		frmOneWayRepeated.setBounds(100, 100, 1082, 722);
		frmOneWayRepeated.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JButton btnInsertAllData = new JButton("Insert all Data");
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
				
				criticalValue = Double.parseDouble(txtCriticalValue.getText());
				
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
		
		txtGroupLabel = new JTextField();
		txtGroupLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGroupLabel.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupLabel.setText("Group 1 Label");
		txtGroupLabel.setColumns(10);
		
		txtGroupLabel_1 = new JTextField();
		txtGroupLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGroupLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupLabel_1.setText("Group 2 Label");
		txtGroupLabel_1.setColumns(10);
		
		txtGroupLabel_2 = new JTextField();
		txtGroupLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGroupLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupLabel_2.setText("Group 3 Label");
		txtGroupLabel_2.setColumns(10);
		
		txtCriticalValue = new JTextField();
		txtCriticalValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCriticalValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtCriticalValue.setText("Critical Value");
		txtCriticalValue.setColumns(10);
		
		JButton btnShowMeThose = new JButton("Show me those results!");
		btnShowMeThose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnShowMeThose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				totalList.add(firstData);
				totalList.add(secondData);
				totalList.add(thirdData);
				
				labelList.add(group1Label);
				labelList.add(group2Label);
				labelList.add(group3Label);
				
				System.out.println(totalList.toString());
				
				OneWayRepeatedMeasuresANOVA test3 = new OneWayRepeatedMeasuresANOVA(totalList, labelList, criticalValue);
				
				test3.calculateStatistics();
				System.out.println(test3.printStatistics());
				
				String userHomeFolder = System.getProperty("user.home");
				File textFile = new File(userHomeFolder, "ANOVA-OneWayRepeated.txt");
				try {

					BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
					out.write(test3.printStatistics());
					out.close();
					System.out.println("Did it!");
				} catch (IOException x) {
					System.out.println("Didnt work");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmOneWayRepeated.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addGap(105)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnShowMeThose, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
							.addGap(106)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(77, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtGroupLabel, 160, 160, 160)
						.addComponent(txtCriticalValue, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
					.addGap(214)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInsertAllData, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
					.addComponent(txtGroupLabel_2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(134))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtGroupLabel_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtGroupLabel, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(283)
							.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(btnInsertAllData, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
							.addGap(40)
							.addComponent(btnShowMeThose, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(79))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(txtCriticalValue, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
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
				"Data3", "Data3"
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
				"Data1", "Data1"
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
		frmOneWayRepeated.getContentPane().setLayout(groupLayout);
	}
}
