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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import app.gui.services.Homework1;
import app.gui.services.Homework2;
import app.gui.services.Homework3;
import app.gui.services.Homework4;
import app.gui.services.Homework5;
import app.gui.services.Homework6;
import javax.swing.ScrollPaneConstants;

public class MainWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane textPane_1;
	private JTextPane textPane_3;
	// ************ Tab 4
	private JTextPane textPane_41, textPane_42, textPane_43, textPane_44;
	private JTextPane textPane_t41, textPane_t42, textPane_t43, textPane_t44;
	private JTextPane textPane_n41, textPane_n42, textPane_n43, textPane_n44;
	// ************ Tab 5
	private JTextPane textPane_h5_1, textPane_h5_2;
	private JTextField textField_11, textField_12, textField_13, textField_14;
	private JTextField textField_21, textField_22, textField_23, textField_24;
	private JTextField txtDimension;
	private JTextField txtHotellingBodewig;
	private JTextField txtLiAndLi;
	//
	private JTextPane textArea, textArea_1, textArea_2, textArea_3, textArea_4;
	private JTextField txtEigenValuesOf;
	private JTextField txtMatrixA;
	private JTextField txtAsMatrix;
	private JTextField textField_p1_1;
	private JTextField textField_p1_2;
	private JTextField textField_p1_3;
	private JTextField textField_p1_7;
	private JTextField txtCol;
	private JTextField txtRows;
	private JTextField txtS;
	private JTextField textField_p1_4;
	private JTextField textField_p1_5;
	private JTextField textField_p1_6;
	private JTextField txtSparseMatrices;
	private JTextField txtDimension_1;
	private JTextField txtSparsity;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField txtRandomSparseMatrix;
	private JTextField txtFileSparseMatrix;
	private JTextField textField_p2_1;
	private JTextField textField_p2_2;
	private JTextField textField_p3_1;
	private JTextField textField_p3_2;
	
	
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
		////////////////////////////////////////////////////////////////
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Tema 5", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_h5_1 = new JScrollPane();
		scrollPane_h5_1.setBounds(444, 42, 350, 319);
		panel_5.add(scrollPane_h5_1);
		
		txtHotellingBodewig = new JTextField();
		txtHotellingBodewig.setText(" Hotelling Bodewig");
		txtHotellingBodewig.setEditable(false);
		txtHotellingBodewig.setBounds(94, 11, 115, 20);
		panel_5.add(txtHotellingBodewig);
		txtHotellingBodewig.setColumns(10);
		
		txtLiAndLi = new JTextField();
		txtLiAndLi.setText(" Li And Li");
		txtLiAndLi.setEditable(false);
		txtLiAndLi.setColumns(10);
		txtLiAndLi.setBounds(454, 11, 115, 20);
		panel_5.add(txtLiAndLi);

		textPane_h5_1 = new JTextPane();
		textPane_h5_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_h5_1.setViewportView(textPane_h5_1);
		
		JScrollPane scrollPane_h5_2 = new JScrollPane();
		scrollPane_h5_2.setBounds(84, 42, 350, 319);
		panel_5.add(scrollPane_h5_2);
		
		textPane_h5_2 = new JTextPane();
		textPane_h5_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_h5_2.setViewportView(textPane_h5_2);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(94, 372, 97, 20);
		panel_5.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(94, 403, 97, 20);
		panel_5.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setBounds(201, 372, 233, 20);
		panel_5.add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(201, 403, 97, 20);
		panel_5.add(textField_14);
		
		textField_21 = new JTextField();
		textField_21.setColumns(10);
		textField_21.setBounds(454, 372, 97, 20);
		panel_5.add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setColumns(10);
		textField_22.setBounds(454, 403, 97, 20);
		panel_5.add(textField_22);
		
		textField_23 = new JTextField();
		textField_23.setColumns(10);
		textField_23.setBounds(561, 372, 233, 20);
		panel_5.add(textField_23);
		
		textField_24 = new JTextField();
		textField_24.setColumns(10);
		textField_24.setBounds(561, 403, 97, 20);
		panel_5.add(textField_24);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(10, 104, 64, 23);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainWindow.this.setHomework5Dimension();
				MainWindow.this.updateHomework5Display();
			}
		});
		panel_5.add(btnNewButton);
		
		txtDimension = new JTextField();
		txtDimension.setText("4");
		txtDimension.setBounds(10, 138, 64, 20);
		panel_5.add(txtDimension);
		txtDimension.setColumns(10);
		
		////////////////////////////////////////////////////////////////
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Tema 6", null, panel_6, null);
		panel_6.setLayout(null);
		
		txtMatrixA = new JTextField();
		txtMatrixA.setEditable(false);
		txtMatrixA.setText(" Matrix A");
		txtMatrixA.setBounds(119, 11, 65, 20);
		panel_6.add(txtMatrixA);
		txtMatrixA.setColumns(10);
		
		txtEigenValuesOf = new JTextField();
		txtEigenValuesOf.setText(" Eigen Values : ");
		txtEigenValuesOf.setEditable(false);
		txtEigenValuesOf.setBounds(10, 69, 86, 20);
		panel_6.add(txtEigenValuesOf);
		txtEigenValuesOf.setColumns(10);
		
		JScrollPane scrollPane_h6_p11 = new JScrollPane();
		scrollPane_h6_p11.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_h6_p11.setBounds(10, 100, 125, 252);
		panel_6.add(scrollPane_h6_p11);
		
		textArea = new JTextPane();
		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_h6_p11.setViewportView(textArea);
		
		textField_p1_1 = new JTextField();
		textField_p1_1.setBounds(10, 363, 125, 20);
		panel_6.add(textField_p1_1);
		textField_p1_1.setColumns(10);
		
		textField_p1_2 = new JTextField();
		textField_p1_2.setBounds(10, 394, 125, 20);
		panel_6.add(textField_p1_2);
		textField_p1_2.setColumns(10);
		
		textField_p1_3 = new JTextField();
		textField_p1_3.setBounds(10, 425, 125, 20);
		panel_6.add(textField_p1_3);
		textField_p1_3.setColumns(10);
		
		txtCol = new JTextField();
		txtCol.setEditable(false);
		txtCol.setText("C");
		txtCol.setBounds(145, 103, 29, 20);
		panel_6.add(txtCol);
		txtCol.setColumns(10);
		
		txtRows = new JTextField();
		txtRows.setEditable(false);
		txtRows.setText("R");
		txtRows.setBounds(145, 134, 29, 20);
		panel_6.add(txtRows);
		txtRows.setColumns(10);
		
		txtS = new JTextField();
		txtS.setEditable(false);
		txtS.setText("s");
		txtS.setBounds(145, 165, 29, 20);
		panel_6.add(txtS);
		txtS.setColumns(10);
		
		textField_p1_4 = new JTextField();
		textField_p1_4.setText("7");
		textField_p1_4.setBounds(184, 103, 86, 20);
		panel_6.add(textField_p1_4);
		textField_p1_4.setColumns(10);
		
		textField_p1_5 = new JTextField();
		textField_p1_5.setText("5");
		textField_p1_5.setBounds(184, 134, 86, 20);
		panel_6.add(textField_p1_5);
		textField_p1_5.setColumns(10);
		
		textField_p1_6 = new JTextField();
		textField_p1_6.setText("5");
		textField_p1_6.setBounds(184, 165, 86, 20);
		panel_6.add(textField_p1_6);
		textField_p1_6.setColumns(10);
		
		txtAsMatrix = new JTextField();
		txtAsMatrix.setText("As matrix : ");
		txtAsMatrix.setEditable(false);
		txtAsMatrix.setColumns(10);
		txtAsMatrix.setBounds(145, 227, 65, 20);
		panel_6.add(txtAsMatrix);
		
		JScrollPane scrollPane_h6_p12 = new JScrollPane();
		scrollPane_h6_p12.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_h6_p12.setBounds(145, 258, 170, 160);
		panel_6.add(scrollPane_h6_p12);
		
		textArea_1 = new JTextPane();
		textArea_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_h6_p12.setViewportView(textArea_1);
		
		textField_p1_7 = new JTextField();
		textField_p1_7.setColumns(10);
		textField_p1_7.setBounds(145, 425, 125, 20);
		panel_6.add(textField_p1_7);
		
		txtSparseMatrices = new JTextField();
		txtSparseMatrices.setEditable(false);
		txtSparseMatrices.setText(" Sparse Matrices");
		txtSparseMatrices.setBounds(541, 11, 117, 20);
		panel_6.add(txtSparseMatrices);
		txtSparseMatrices.setColumns(10);
		
		txtFileSparseMatrix = new JTextField();
		txtFileSparseMatrix.setText(" File Sparse Matrix");
		txtFileSparseMatrix.setEditable(false);
		txtFileSparseMatrix.setColumns(10);
		txtFileSparseMatrix.setBounds(370, 69, 125, 20);
		panel_6.add(txtFileSparseMatrix);
		
		textField_p2_1 = new JTextField();
		textField_p2_1.setColumns(10);
		textField_p2_1.setBounds(359, 103, 151, 20);
		panel_6.add(textField_p2_1);
		
		JScrollPane scrollPane_h6_p2 = new JScrollPane();
		scrollPane_h6_p2.setBounds(359, 134, 151, 284);
		panel_6.add(scrollPane_h6_p2);
		
		textArea_3 = new JTextPane();
		textArea_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_h6_p2.setViewportView(textArea_3);
		
		textField_p2_2 = new JTextField();
		textField_p2_2.setColumns(10);
		textField_p2_2.setBounds(359, 425, 86, 20);
		panel_6.add(textField_p2_2);
		
		txtRandomSparseMatrix = new JTextField();
		txtRandomSparseMatrix.setEditable(false);
		txtRandomSparseMatrix.setText(" Random Sparse Matrix");
		txtRandomSparseMatrix.setBounds(601, 69, 153, 20);
		panel_6.add(txtRandomSparseMatrix);
		txtRandomSparseMatrix.setColumns(10);
		
		txtDimension_1 = new JTextField();
		txtDimension_1.setEditable(false);
		txtDimension_1.setText(" Dimension");
		txtDimension_1.setBounds(579, 100, 79, 20);
		panel_6.add(txtDimension_1);
		txtDimension_1.setColumns(10);
		
		txtSparsity = new JTextField();
		txtSparsity.setEditable(false);
		txtSparsity.setText(" Sparsity");
		txtSparsity.setColumns(10);
		txtSparsity.setBounds(579, 134, 79, 20);
		panel_6.add(txtSparsity);
		
		textField_4 = new JTextField();
		textField_4.setText("500");
		textField_4.setBounds(668, 100, 86, 20);
		panel_6.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setText("0.015");
		textField_5.setBounds(668, 134, 86, 20);
		panel_6.add(textField_5);
		textField_5.setColumns(10);
		
		JScrollPane scrollPane_h6_p31 = new JScrollPane();
		scrollPane_h6_p31.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_h6_p31.setBounds(541, 162, 253, 81);
		panel_6.add(scrollPane_h6_p31);
		
		textArea_2 = new JTextPane();
		textArea_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_h6_p31.setViewportView(textArea_2);
		
		textField_p3_1 = new JTextField();
		textField_p3_1.setColumns(10);
		textField_p3_1.setBounds(601, 258, 151, 20);
		panel_6.add(textField_p3_1);
		
		JScrollPane scrollPane_h6_p32 = new JScrollPane();
		scrollPane_h6_p32.setBounds(603, 289, 151, 129);
		panel_6.add(scrollPane_h6_p32);
		
		textArea_4 = new JTextPane();
		textArea_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_h6_p32.setViewportView(textArea_4);
		
		textField_p3_2 = new JTextField();
		textField_p3_2.setColumns(10);
		textField_p3_2.setBounds(601, 425, 86, 20);
		panel_6.add(textField_p3_2);
		
		JButton btnNewButton_3 = new JButton("Run");
		btnNewButton_3.setBounds(318, 10, 89, 23);
		btnNewButton_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainWindow.this.setUpHomework6();
				MainWindow.this.updateHomework6Display();
			}
		});
		panel_6.add(btnNewButton_3);
		

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
	
	private void updateHomework5Display()
	{
		Homework5.execute();
		
		textPane_h5_1.setText(Homework5.getResults()[0]);
		textField_11.setText(Homework5.getResults()[1]);
		textField_12.setText(Homework5.getResults()[2]);
		textField_13.setText(Homework5.getResults()[3]);
		textField_14.setText(Homework5.getResults()[4]);
		
		textPane_h5_2.setText(Homework5.getResults()[5]);
		textField_21.setText(Homework5.getResults()[6]);
		textField_22.setText(Homework5.getResults()[7]);
		textField_23.setText(Homework5.getResults()[8]);
		textField_24.setText(Homework5.getResults()[9]);
		
	}
	
	public void setHomework5Dimension()
	{
		int dimension = Integer.valueOf(txtDimension.getText());
		if(dimension > 41)
			Homework5.setDimension(40);
		else
			Homework5.setDimension(dimension);
	}
	
	private void updateHomework6Display()
	{
		Homework6.execute();
		
		textArea.setText(Homework6.getResults()[0]);
		textField_p1_1.setText(Homework6.getResults()[1]);
		textField_p1_2.setText(Homework6.getResults()[2]);
		textField_p1_3.setText(Homework6.getResults()[3]);

		textArea_1.setText(Homework6.getResults()[4]);
		textField_p1_7.setText(Homework6.getResults()[5]);

		textField_p2_1.setText(Homework6.getResults()[6]);
		textArea_3.setText(Homework6.getResults()[7]);
		textField_p2_2.setText(Homework6.getResults()[8]);
		
		textArea_2.setText(Homework6.getResults()[9]);
		textField_p3_1.setText(Homework6.getResults()[10]);
		textArea_4.setText(Homework6.getResults()[11]);
		textField_p3_2.setText(Homework6.getResults()[12]);
		
	}
	
	public void setUpHomework6()
	{
		int    randomSparseMatrixDimension = Integer.valueOf(textField_4.getText());
		double randomSparseMatrixSparsity  = Double.valueOf(textField_5.getText());
		
		int numberOfColumns = Integer.valueOf(textField_p1_4.getText()); // p
		int numberOfRows    = Integer.valueOf(textField_p1_5.getText());    // n
		int s = Integer.valueOf(textField_p1_6.getText());

		Homework6.setValues(numberOfRows, numberOfColumns, s, randomSparseMatrixSparsity, randomSparseMatrixDimension);
	}
	
	public void setHomework2StringMatrices(String stringMatrices)
	{
		Homework2.setStringMatrices(stringMatrices);
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
