//package app.gui;
//
//import javax.swing.JPanel;
//
//public class MainWindow extends JPanel
//{
//	public ribbonMenu() {
//        super(new GridLayout(1,1));
//
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        JTabbedPane tabbedPane2 = new JTabbedPane();
//
//        JComponent panel1 = makeTextPanel("Panel #1");
//        tabbedPane.addTab("Tab 1", panel1);
//        panel1.setPreferredSize(new Dimension(600, 400));
//
//        JComponent panel2 = makeTextPanel("Panel #2");
//        tabbedPane.addTab("Tab 2", panel2);
//
//        JComponent panel3 = makeTextPanel("Panel #3");
//        tabbedPane.addTab("Tab 3", panel3);
//
//        add(tabbedPane);
//
//        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);           
//}
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
