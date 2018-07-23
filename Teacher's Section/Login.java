import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.border.*;
	

public class Login implements ActionListener,KeyListener
{ 
	MainMenu m = new MainMenu();
	JFrame f1 = new JFrame("Login");
	Container contentpane;
	
	JLabel titleLabel = new JLabel("Welcome Teacher");
	
	JLabel userLabel = new JLabel("Username:");
	JTextField userField = new JTextField(12);
	JLabel passLabel = new JLabel("Password:");
	JPasswordField passField = new JPasswordField(12);
	JLabel welcome = new JLabel("Register then press OK to continue");
	
	JButton okButton = new JButton("OK");
	JButton instructButton = new JButton("INSTRUCTION");
	JButton cancelButton = new JButton("CANCEL");
	
	
	JPanel NorthPanel = new JPanel();
	JPanel SouthPanel = new JPanel();
	JPanel CenterPanel = new JPanel();
	
	String account[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	
	String filename = "accounts.txt";
	
	public void launchFrame()
	{
		contentpane = f1.getContentPane();
		
		NorthPanel.add(titleLabel);
		
		SouthPanel.add(okButton);
		okButton.addActionListener(this);
			okButton.setToolTipText("OK");
		SouthPanel.add(instructButton);
		instructButton.addActionListener(this);
			instructButton.setToolTipText("Instruction");
		SouthPanel.add(cancelButton);
		cancelButton.addActionListener(this);
			cancelButton.setToolTipText("Cancel");
		
		contentpane.add(NorthPanel,BorderLayout.NORTH);
		contentpane.add(CenterPanel,BorderLayout.CENTER);
		contentpane.add(SouthPanel,BorderLayout.SOUTH);
		
		userField.addKeyListener(this);
		passField.addKeyListener(this);
		f1.setIconImage(new ImageIcon("logo.png").getImage());
		f1.setSize(275,150);
		f1.setVisible(true);
		f1.setResizable(false);
		f1.setLocationRelativeTo(m.contentpane);
		f1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		createFile();
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 10)
		{
			if((account[1].equals(userField.getText()))&&(account[2].equals(passField.getText())))
			{
				m.launchFrame();
				f1.dispose();
			}
			else
				JOptionPane.showMessageDialog(null,"Accounts Mismatch");
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == okButton )
		{
			if((account[1].equals(userField.getText()))&&(account[2].equals(passField.getText())))
			{
				m.launchFrame();
				f1.dispose();
			}
			else
				JOptionPane.showMessageDialog(null,"Accounts Mismatch");
		}
		
		if( e.getSource() == instructButton )
		{
			InstructionReader ir = new InstructionReader();
			ir.launchFrame();
		}
		
		if( e.getSource() == cancelButton )
		{
			System.exit(0);
		}
	}
	
	public void readFile()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			int x = 0;
			String line = "";
			while((line = br.readLine())!= null)
			{
				account[x] = line;
				x++;
			}
			br.close();
		}
		
		catch(Exception e)
		{
			createFile();
		}
	}
	
	public void createFile()
	{	File file = new File(filename);
		if(!file.exists())
		{
			try
			{
				f1.dispose();
				AccountFrame af = new AccountFrame();
				af.proceed = 1;
				af.launchFrame();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}			
			
		}
		else
		{
			CenterPanel.add(userLabel);
			CenterPanel.add(userField);
			CenterPanel.add(passLabel);
			CenterPanel.add(passField);
			readFile();
		}
	}
	
	public static void main(String[] args)
	{
		Login  l = new Login();
		l.launchFrame();
	}
}
