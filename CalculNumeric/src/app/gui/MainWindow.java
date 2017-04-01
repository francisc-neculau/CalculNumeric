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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import app.gui.services.Homework1;
import app.gui.services.Homework2;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane textPane_1;
	private String stringMatrices;

	public MainWindow()
	{
		// cum as putea calcula inversa unei matrici
		// cand avem solve(A, B, x);, orice B
		setMinimumSize(new Dimension(700, 460));
		/* * * * * * * * * * * */
		Homework1.execute();
		/* * * * * * * * * * * */
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 694, 432);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(10, 11, 674, 410);
		panel.add(tabbedPane);
		
		/*
		 * Tab2
		 */
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tema 1", null, panel_2, null);
		panel_2.setLayout(new GridLayout(0, 1, 0, 20));
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new LineBorder(Color.GRAY, 2));
		textPane.setText(Homework1.getResults()[3]);
		panel_2.add(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		JTable table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		table2.setModel(new DefaultTableModel(
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
		panel_2.add(scrollPane_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Tema 2", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnSetA = new JButton("Set Matrices");
		btnSetA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogMatrices dmat = new DialogMatrices(MainWindow.this);
				dmat.setVisible(true);
			}
		});
		btnSetA.setBounds(10, 314, 105, 23);
		panel_1.add(btnSetA);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 11, 534, 360);
		panel_1.add(scrollPane);
		
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
		panel_1.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Tema 3", null, panel_3, null);
		
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
