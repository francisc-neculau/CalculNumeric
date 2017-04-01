package app.gui;

import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogMatrices extends JDialog
{
	JTextPane textPane;
	MainWindow mainWindow;
	
	public DialogMatrices(MainWindow mw) {
		
		mainWindow = mw;
		
		getContentPane().setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 11, 414, 206);
		getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DialogMatrices.this.mainWindow.setStringMatrices(DialogMatrices.this.textPane.getText());
				DialogMatrices.this.dispose();
			}
		});
		btnNewButton.setBounds(10, 228, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				DialogMatrices.this.dispose();
			}
		});
		btnNewButton_1.setBounds(109, 228, 89, 23);
		getContentPane().add(btnNewButton_1);
	}
}
