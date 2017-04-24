package app.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import app.gui.services.Homework1;
import app.gui.services.Homework2;
import app.gui.services.Homework3;
import app.gui.services.Homework4;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane textPane_1;
	private JTextPane textPane_3;
	private String stringMatrices;
	// Tab 4
	private JTextPane textPane_41, textPane_42, textPane_43, textPane_44;
	private JTextPane textPane_t41, textPane_t42, textPane_t43, textPane_t44;
	private JTextPane textPane_n41, textPane_n42, textPane_n43, textPane_n44;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField txtDimension;
	public MainWindow()
	{
		// cum as putea calcula inversa unei matrici
		// cand avem solve(A, B, x);, orice B
		setMinimumSize(new Dimension(835, 535));
		/* * * * * * * * * * * */
		Homework1.execute();
		/* * * * * * * * * * * */
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 829, 506);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(10, 11, 809, 484);
		panel.add(tabbedPane);
		
		////////////////////////////////////////////////////////////////
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Tema 1", null, panel_1, null);
		panel_1.setLayout(new GridLayout(0, 1, 0, 20));
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new LineBorder(Color.GRAY, 2));
		textPane.setText(Homework1.getResults()[3]);
		panel_1.add(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		JTable table1 = new JTable();
		scrollPane_1.setViewportView(table1);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
				{Homework1.getResults()[0]},
				{Homework1.getResults()[1]},
				{Homework1.getResults()[2]},
			},
			new String[] {
				"Results"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panel_1.add(scrollPane_1);
		////////////////////////////////////////////////////////////////
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tema 2", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnSetA = new JButton("Set Matrices");
		btnSetA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogMatrices dmat = new DialogMatrices(MainWindow.this);
				dmat.setVisible(true);
			}
		});
		btnSetA.setBounds(10, 314, 105, 23);
		panel_2.add(btnSetA);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 11, 534, 360);
		panel_2.add(scrollPane);
		
		textPane_1 = new JTextPane();
		scrollPane.setViewportView(textPane_1);
		textPane_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		textPane_1.setEditable(false);
		
		JButton btnNewButton_1 = new JButton("Run");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainWindow.this.updateHomework2Display();
			}
		});
		btnNewButton_1.setBounds(10, 348, 105, 23);
		panel_2.add(btnNewButton_1);
		////////////////////////////////////////////////////////////////
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Tema 3", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(125, 11, 534, 360);
		panel_3.add(scrollPane_3);
		
		textPane_3 = new JTextPane();
		scrollPane_3.setViewportView(textPane_3);
		textPane_3.setBorder(new EmptyBorder(10, 10, 10, 10));
		textPane_3.setEditable(false);
		
		JButton btnNewButton_2 = new JButton("Run");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainWindow.this.updateHomework3Display();
			}
		});
		btnNewButton_2.setBounds(10, 348, 105, 23);
		panel_3.add(btnNewButton_2);
		////////////////////////////////////////////////////////////////
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Tema 4", null, panel_4, null);
		panel_4.setLayout(null);
		///
		JScrollPane scrollPane_41 = new JScrollPane();
		scrollPane_41.setBounds(108, 11, 164, 372);
		panel_4.add(scrollPane_41);

		JScrollPane scrollPane_42 = new JScrollPane();
		scrollPane_42.setBounds(282, 11, 164, 372);
		panel_4.add(scrollPane_42);
		
		JScrollPane scrollPane_43 = new JScrollPane();
		scrollPane_43.setBounds(456, 11, 164, 372);
		panel_4.add(scrollPane_43);
		
		JScrollPane scrollPane_44 = new JScrollPane();
		scrollPane_44.setBounds(630, 11, 164, 372);
		panel_4.add(scrollPane_44);
		///
		textPane_41 = new JTextPane();
		scrollPane_41.setViewportView(textPane_41);
		textPane_41.setBorder(new EmptyBorder(10, 10, 10, 10));
		textPane_41.setEditable(false);

		textPane_42 = new JTextPane();
		scrollPane_42.setViewportView(textPane_42);
		textPane_42.setBorder(new EmptyBorder(10, 10, 10, 10));
		textPane_42.setEditable(false);
		
		textPane_43 = new JTextPane();
		scrollPane_43.setViewportView(textPane_43);
		textPane_43.setBorder(new EmptyBorder(10, 10, 10, 10));
		textPane_43.setEditable(false);
		
		textPane_44 = new JTextPane();
		scrollPane_44.setViewportView(textPane_44);
		textPane_44.setBorder(new EmptyBorder(10, 10, 10, 10));
		textPane_44.setEditable(false);
		
		textPane_t41 = new JTextPane();
		textPane_t41.setBounds(282, 425, 164, 20);
		panel_4.add(textPane_t41);
		
		textPane_t42 = new JTextPane();
		textPane_t42.setBounds(456, 425, 164, 20);
		panel_4.add(textPane_t42);
		
		textPane_t43 = new JTextPane();
		textPane_t43.setBounds(630, 425, 164, 20);
		panel_4.add(textPane_t43);
		
		textPane_t44 = new JTextPane();
		textPane_t44.setBounds(108, 425, 164, 20);
		panel_4.add(textPane_t44);
		
		JButton button_4 = new JButton("Run");
		button_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainWindow.this.updateHomework4Display();
			}
		});
		button_4.setBounds(10, 11, 83, 23);
		panel_4.add(button_4);
		
		textPane_n41 = new JTextPane();
		textPane_n41.setBounds(108, 394, 164, 20);
		panel_4.add(textPane_n41);
		
		textPane_n42 = new JTextPane();
		textPane_n42.setBounds(282, 394, 164, 20);
		panel_4.add(textPane_n42);
		
		textPane_n43 = new JTextPane();
		textPane_n43.setBounds(456, 394, 164, 20);
		panel_4.add(textPane_n43);
		
		textPane_n44 = new JTextPane();
		textPane_n44.setBounds(630, 394, 164, 20);
		panel_4.add(textPane_n44);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Tema 5", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(444, 11, 350, 350);
		panel_5.add(scrollPane_2);
		
		JTextPane textPane_2 = new JTextPane();
		scrollPane_2.setViewportView(textPane_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(84, 11, 350, 350);
		panel_5.add(scrollPane_4);
		
		JTextPane textPane_4 = new JTextPane();
		scrollPane_4.setViewportView(textPane_4);
		
		textField = new JTextField();
		textField.setBounds(184, 372, 250, 20);
		panel_5.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(94, 372, 86, 20);
		panel_5.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(544, 372, 250, 20);
		panel_5.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(544, 403, 250, 20);
		panel_5.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(454, 372, 86, 20);
		panel_5.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(184, 403, 250, 20);
		panel_5.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(94, 403, 86, 20);
		panel_5.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(454, 403, 86, 20);
		panel_5.add(textField_7);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(10, 104, 64, 23);
		panel_5.add(btnNewButton);
		
		txtDimension = new JTextField();
		txtDimension.setText("4");
		txtDimension.setBounds(10, 138, 64, 20);
		panel_5.add(txtDimension);
		txtDimension.setColumns(10);
		
		////////////////////////////////////////////////////////////////
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setResizable(false);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(MainWindow.this, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
	}
	
	
	private void updateHomework2Display()
	{
		Homework2.setStringMatrices(stringMatrices);
		Homework2.execute();
		textPane_1.setText(Homework2.getResults()[0]);
	}
	
	private void updateHomework3Display()
	{
		Homework3.execute();
		textPane_3.setText(Homework3.getResults()[0]);
	}
	
	private void updateHomework4Display()
	{
		Homework4.execute();
		textPane_41.setText(Homework4.getResults()[0]);
		textPane_n41.setText(Homework4.getResults()[1]);
		textPane_t41.setText(Homework4.getResults()[2]);
		
		textPane_42.setText(Homework4.getResults()[3]);
		textPane_n42.setText(Homework4.getResults()[4]);
		textPane_t42.setText(Homework4.getResults()[5]);
		
		textPane_43.setText(Homework4.getResults()[6]);
		textPane_n43.setText(Homework4.getResults()[7]);
		textPane_t43.setText(Homework4.getResults()[8]);
		
		textPane_44.setText(Homework4.getResults()[9]);
		textPane_n44.setText(Homework4.getResults()[10]);
		textPane_t44.setText(Homework4.getResults()[11]);
	}
	
	public void setStringMatrices(String stringMatrices)
	{
		this.stringMatrices = stringMatrices;
	}
	public static void main(String[] args)
	{
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
	}
}
//
//	protected JComponent makeTextPanel(String text)
//	{
//		JPanel panel = new JPanel(false);
//		JLabel filler = new JLabel(text);
//		filler.setHorizontalAlignment(JLabel.CENTER);
//		panel.setLayout(new GridLayout(1, 1));
//		panel.add(filler);
//		return panel;
//	}
//
//	private static void createAndShowGUI()
//	{
//		JFrame frame = new JFrame("ribbonMenu");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		frame.add(new ribbonMenu(), BorderLayout.CENTER);
//
//		frame.pack();
//		frame.setVisible(true);
//	}
//
//	public static void main(String[] args)
//	{
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				UIManager.put("swing.boldmetal", Boolean.FALSE);
//				createAndShowGUI();
//			}
//
//		});
//	}
//}
