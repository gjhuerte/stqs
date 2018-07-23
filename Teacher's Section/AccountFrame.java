import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.border.TitledBorder;

public class AccountFrame implements ActionListener,KeyListener
{
	JFrame f = new JFrame("Teachers Information Sheet");
	Container contentpane;
	
	JPanel upperPanel = new JPanel(new GridLayout(1,2));
	
	JPanel labelPanel = new JPanel(new GridLayout(5,1));
	JLabel nameLabel;
	JLabel userLabel;
	JLabel passLabel;
	JLabel verifyPassLabel;
	JLabel emailLabel;
	
	JPanel txtPanel = new JPanel(new GridLayout(5,1));
	JTextField nameField = new JTextField();
	JTextField userField = new JTextField();
	JTextField passField = new JTextField();
	JTextField verifyPassField = new JTextField();
	JTextField emailField = new JTextField();
	
	JPanel bttnPanel = new JPanel(new GridLayout(1,3));
	JButton saveBttn = new JButton("Save");
	JButton clearBttn = new JButton("Clear");
	JButton cancelBttn = new JButton("Cancel");
	
	int  success = 0;
	int noBlank = 0;
	String display = "";
	String filename = "accounts.txt";
	int proceed = 0;
	String name = "Name:";
	String user = "Username:";
	String password = "Password:";
	String verifypass = "Verify Password";
	String email = "E-mail Address:";
	String error = "*";
	String notMatch = "";
	
	public void launchFrame()
	{
		contentpane = f.getContentPane();
		saveBttn.setEnabled(false);
		
		nameLabel = new JLabel(name);
		userLabel = new JLabel(user);
		passLabel = new JLabel(password);
		verifyPassLabel = new JLabel(verifypass);
		emailLabel = new JLabel(email);
		
		labelPanel.add(nameLabel);
		labelPanel.add(userLabel);
		labelPanel.add(passLabel);
		labelPanel.add(verifyPassLabel);
		labelPanel.add(emailLabel);
		
		txtPanel.add(nameField);
		nameField.addKeyListener(this);
		txtPanel.add(userField);
		userField.addKeyListener(this);
		txtPanel.add(passField);
		passField.addKeyListener(this);
		txtPanel.add(verifyPassField);
		verifyPassField.addKeyListener(this);
		txtPanel.add(emailField);
		emailField.addKeyListener(this);
		
		bttnPanel.add(saveBttn);
			saveBttn.addActionListener(this);
		bttnPanel.add(clearBttn);
			clearBttn.addActionListener(this);
		bttnPanel.add(cancelBttn);
			cancelBttn.addActionListener(this);
		
		upperPanel.setBorder(BorderFactory.createTitledBorder(null,"Registration",TitledBorder.TOP,TitledBorder.CENTER));
		upperPanel.add(labelPanel);
		upperPanel.add(txtPanel);
		
		contentpane.add(upperPanel,BorderLayout.CENTER);
		contentpane.add(bttnPanel,BorderLayout.SOUTH);
		contentpane.addKeyListener(this);
		
		f.setIconImage(new ImageIcon("logo.png").getImage());
		f.setSize(400,230);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		readFile();
		checkPassFirst();
		
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(true)saveBttn.setEnabled(true);
		
		if(e.getKeyCode() == 10)
		{
			success = verifyPass();
			if(success == 1)
			{
				noBlank = blankChecker();
				if(noBlank == 1)
				{
					saveFile();
					f.dispose();
					txtPanel.removeAll();
					labelPanel.removeAll();
					bttnPanel.removeAll();
					upperPanel.removeAll();
					contentpane.removeAll();
	
					if(proceed ==1)
					{
						MainMenu m = new MainMenu();
						m.launchFrame();
					}
				}
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if( e.getSource() == saveBttn )
		{
			success = verifyPass();
			if(success == 1)
			{
				noBlank = blankChecker();
				if(noBlank == 1)
				{
					saveFile();
					f.dispose();
					txtPanel.removeAll();
					labelPanel.removeAll();
					bttnPanel.removeAll();
					upperPanel.removeAll();
					contentpane.removeAll();
	
					if(proceed ==1)
					{
						MainMenu m = new MainMenu();
						m.launchFrame();
					}
				}
			}
		}
		
		if( e.getSource() == clearBttn )
		{
			nameField.setText("");
			userField.setText("");
			passField.setText("");
			verifyPassField.setText("");
			emailField.setText("");
		}
		
		if( e.getSource() == cancelBttn )
		{
			labelPanel.removeAll();
			txtPanel.removeAll();
			bttnPanel.removeAll();
			upperPanel.removeAll();
			contentpane.removeAll();
			f.dispose();
		}
	}
	
	public int verifyPass()
	{
		String passVar = passField.getText();
		String verPass = verifyPassField.getText();
		
		if(passVar.equals(verPass))
		{
			passLabel.setForeground(Color.black);
			verifyPassLabel.setForeground(Color.black);
			return 1;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Password Mismatch","",JOptionPane.ERROR_MESSAGE);
			passLabel.setForeground(Color.red);
			verifyPassLabel.setForeground(Color.red);
			return 0;
		}
	}
	
	public void readFile()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			nameField.setText(br.readLine());
			userField.setText(br.readLine());
			passField.setText(br.readLine());
			emailField.setText(br.readLine());
			br.close();
		}
		
		catch(Exception e){}
	}
	
	public void saveFile()
	{
		try
		{	File file = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.append(nameField.getText());
			bw.newLine();
			bw.append(userField.getText());
			bw.newLine();
			bw.append(passField.getText());
			bw.newLine();
			bw.append(emailField.getText());
			bw.close();
			display = "YOUR ACCOUNT INFORMATION ARE AS FOLLOWS";
			display += "\n";
			display += "NAME:" + nameField.getText();
			display += "\n";
			display += "USERNAME: " + userField.getText();
			display += "\n";
			display +="PASSWORD: " + passField.getText();
			display += "\n";
			display += "EMAIL:" + emailField.getText();
			
			JOptionPane.showMessageDialog(null,""+display,"Saving File . . .",JOptionPane.PLAIN_MESSAGE);
			display = "";
		}
		
		catch(Exception e){}
	}
	
	public void checkPassFirst()
	{
		String pass = "";
		if(proceed == 0)
		{
			pass = (String)JOptionPane.showInputDialog("Enter Password:");
			if(pass.equals(passField.getText()))
			{
				f.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Wrong Password!","Password Error",JOptionPane.ERROR_MESSAGE);
				txtPanel.removeAll();
				labelPanel.removeAll();
				bttnPanel.removeAll();
				upperPanel.removeAll();
				contentpane.removeAll();
			}
		}
		else
		{
			f.setVisible(true);
			cancelBttn.setEnabled(false);
		}
		
		
	}
	
	public int blankChecker()
	{
		int ok = 0;
			if(nameField.getText().equals(""))
			{
				nameLabel.setForeground(Color.red);
				nameLabel.setText(error+name);
			}
			else
			{
				nameLabel.setForeground(Color.black);
				nameLabel.setText(name);
			}
			
			if(userField.getText().equals(""))
			{
				userLabel.setForeground(Color.red);
				userLabel.setText(error+user);
			}
			else
			{
				userLabel.setForeground(Color.black);
				userLabel.setText(user);
			}
			
			if(passField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(passField,"Note: Your account is unsecured. Press OK to Continue");
			}
			
			if(emailField.getText().equals(""))
			{
				emailLabel.setForeground(Color.red);
				emailLabel.setText(error+email);
			}
			else
			{
				emailLabel.setForeground(Color.black);
				emailLabel.setText(email);
				
			}
			
			if((!nameField.getText().equals(""))&&(!userField.getText().equals(""))&&(!emailField.getText().equals("")))
			{
				return 1;
			}
			else
				return 0;
		
	}
	/*public static void main(String args[])
	{
		AccountFrame af = new AccountFrame();
		af.launchFrame();
	}*/
}