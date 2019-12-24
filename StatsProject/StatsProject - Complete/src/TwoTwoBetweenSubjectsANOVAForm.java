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

public class TwoTwoBetweenSubjectsANOVAForm {
	
	private ArrayList<Integer> firstData = new ArrayList<Integer>();
	private ArrayList<Integer> secondData = new ArrayList<Integer>();
	private ArrayList<Integer> thirdData = new ArrayList<Integer>();
	private ArrayList<Integer> fourthData = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>(); 
	
	private String group1Label = "";
	private String group2Label = "";
	private String group3Label = "";
	private String group4Label = "";
	private ArrayList<String> labelList = new ArrayList<String>();
	
	private double criticalValue;
	private JFrame frmTwoXTwo;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField txtGroupLabel;
	private JTextField txtGroupLabel_1;
	private JTextField txtGroupLabel_2;
	private JTextField txtGroupLabel_3;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void NewTwoTwoBetweenSubjectsANOVAForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TwoTwoBetweenSubjectsANOVAForm window = new TwoTwoBetweenSubjectsANOVAForm();
					window.frmTwoXTwo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TwoTwoBetweenSubjectsANOVAForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTwoXTwo = new JFrame();
		frmTwoXTwo.setTitle("Two x Two Between Subjects ANOVA");
		frmTwoXTwo.setBounds(100, 100, 1032, 626);
		frmTwoXTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		txtGroupLabel = new JTextField();
		txtGroupLabel.setText("Group 1 Label");
		txtGroupLabel.setColumns(10);
		
		txtGroupLabel_1 = new JTextField();
		txtGroupLabel_1.setText("Group 2 Label");
		txtGroupLabel_1.setColumns(10);
		
		txtGroupLabel_2 = new JTextField();
		txtGroupLabel_2.setText("Group 3 Label");
		txtGroupLabel_2.setColumns(10);
		
		txtGroupLabel_3 = new JTextField();
		txtGroupLabel_3.setText("Group 4 Label");
		txtGroupLabel_3.setColumns(10);
		
		JButton btnInsertAllData = new JButton("Insert all Data!");
		btnInsertAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Restart the different lists with data
				firstData.clear();
				secondData.clear();
				thirdData.clear();
				fourthData.clear();
				
				group1Label = txtGroupLabel.getText();
				group2Label = txtGroupLabel_1.getText();
				group3Label = txtGroupLabel_2.getText();
				group4Label = txtGroupLabel_3.getText();
				
				//criticalValue = Double.parseDouble(criticalTextField.getText());
				
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
						if(table_2.getModel().getValueAt(row, col) != null) {
							int tmp = (int)table_2.getModel().getValueAt(row, col);
							fourthData.add(tmp);		
						}
					}
				}
			}
		});
		
		JButton btnShowMeThose = new JButton("Show me those results!");
		btnShowMeThose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelList.add(group1Label);
				labelList.add(group2Label);
				labelList.add(group3Label);
				labelList.add(group4Label);
				
				totalList.add(firstData);
				totalList.add(secondData);
				totalList.add(thirdData);
				totalList.add(fourthData);
				
				System.out.println(totalList.toString());
				
				TwoByTwoBetweenSubjectsANOVA test5 = new TwoByTwoBetweenSubjectsANOVA(totalList, labelList);
				
				test5.calculateStatistics();
				System.out.println(test5.printStatistics());
				
				String userHomeFolder = System.getProperty("user.home");
				File textFile = new File(userHomeFolder, "ANOVA-TwoTwoBetweenSubjects.txt");
				try {

					BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
					out.write(test5.printStatistics());
					out.close();
					System.out.println("Did it!");
				} catch (IOException x) {
					System.out.println("Didnt work");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmTwoXTwo.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(436, Short.MAX_VALUE)
					.addComponent(btnInsertAllData, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(425))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addComponent(txtGroupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
					.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(139)
					.addComponent(txtGroupLabel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(129)
					.addComponent(txtGroupLabel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(74))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(379, Short.MAX_VALUE)
					.addComponent(btnShowMeThose, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addGap(369))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
						.addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_2, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_3, 0, 0, Short.MAX_VALUE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtGroupLabel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGroupLabel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGroupLabel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGroupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnInsertAllData, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(67)
					.addComponent(btnShowMeThose, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		
		table_3 = new JTable();
		table_3.setRowHeight(30);
		table_3.setModel(new DefaultTableModel(
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
				"Data4", "Data4"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_3.setViewportView(table_3);
		
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
		frmTwoXTwo.getContentPane().setLayout(groupLayout);
	}
}
