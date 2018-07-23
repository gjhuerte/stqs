import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthorReader implements ActionListener
{
	String display = "";
	JFrame f = new JFrame("Authors Note:");
	Container contentpane;
	JTextArea text = new JTextArea(50,400);
	JScrollPane pane;
	JPanel okPanel = new JPanel();
	JButton ok = new JButton("OK");
	
	public void launchFrame()
	{
		instruct_read();
		contentpane = f.getContentPane();
		text.setEditable(false);
		text.setText(display);
		pane = new JScrollPane(text);
		okPanel.add(ok);
			ok.addActionListener(this);
		contentpane.add(pane);
		contentpane.add(okPanel,BorderLayout.SOUTH);
		f.setSize(1000,1000);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		f.setVisible(false);
	}
	
	public void instruct_read()
	{	String line = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("author"));
			while(( line = br.readLine()) != null)
			{
				display += line;
				display += "\n";
			}
			br.close();
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error! Missing File","File Detection",JOptionPane.ERROR_MESSAGE);
		}
	}
}