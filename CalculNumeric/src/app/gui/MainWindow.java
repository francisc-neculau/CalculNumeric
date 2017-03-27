package app.gui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.gui.services.Homework1;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Insets;

public class MainWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	public MainWindow()
	{
		// cum as putea calcula inversa unei matrici
		// cand avem solve(A, B, x);, orice B
		setMinimumSize(new Dimension(450, 300));
		/* * * * * * * * * * * */
		Homework1.execute();
		/* * * * * * * * * * * */
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 264);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 264);
		panel.add(tabbedPane);
		
		/*
		 * Tab1
		 */
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Tema 1", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
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
		
		/*
		 * Tab2
		 */
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tema 1", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(Homework1.getResults()[3]);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridheight = 2;
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 0;
		panel_2.add(textPane, gbc_textPane);
		
		updateRowHeights();
	}
	
	private void updateRowHeights()
	{
	    for (int row = 0; row < table.getRowCount(); row++)
	    {
	        int rowHeight = table.getRowHeight();

	        for (int column = 0; column < table.getColumnCount(); column++)
	        {
	            Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
	            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	        }

	        table.setRowHeight(row, rowHeight);
	    }
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
