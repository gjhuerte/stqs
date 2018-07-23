import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.filechooser.*;
import javax.swing.border.*;

public class SaveGUI implements ActionListener
{
	JFrame f = new JFrame(" - -  Save - -");
	
	JPanel centerPanel = new JPanel(new GridLayout(1,2));
	JPanel northPanel = new JPanel(new GridLayout(1,2));
	
	JPanel cbPanel = new JPanel(new GridLayout(4,1));
	JCheckBox idCB = new JCheckBox("IDENTIFICATION");
	JCheckBox mcCB = new JCheckBox("MULTIPLE CHOICE");
	JCheckBox enCB = new JCheckBox("ENUMERATION");
	JCheckBox esCB = new JCheckBox("ESSAY WRITING");
	
	JPanel addInfoPanel = new JPanel(new BorderLayout());
	JTextArea textInfo = new JTextArea("--DELETE THIS AFTER READING--\nInformation like deadline and other instructions \ncan be added here. It will be displayed separately\n on the students answersheet",3,23);
	JScrollPane sp = new JScrollPane(textInfo);
	JButton clearText = new JButton("Clear");

	JPanel encryptPanel = new JPanel(new GridLayout(1,2));
	JRadioButton encryptBttn = new JRadioButton("ENCRYPTED");
	JRadioButton notBttn = new JRadioButton("NOT ENCRYPTED");
	ButtonGroup encryptGroup = new ButtonGroup();
	
	JPanel passPanel = new JPanel(new BorderLayout());
	JPasswordField passField = new JPasswordField(12);
	JPanel passBttn = new JPanel(new GridLayout());
	JButton clearPass = new JButton("Clear");
	JButton viewPass  = new JButton("View");
	
	JPanel bttnPanel = new JPanel(new GridLayout(1,2));
	JButton okBttn = new JButton("OK");
	JButton cancelBttn = new JButton("Cancel");
	
	public void saveMethod()
	{
		cbPanel.setBorder(BorderFactory.createTitledBorder(null, "What types of Examination do you want to add?",TitledBorder.TOP,TitledBorder.CENTER));
		cbPanel.add(idCB);
		cbPanel.add(mcCB);
		cbPanel.add(enCB);
		cbPanel.add(esCB);
		
		addInfoPanel.setBorder(BorderFactory.createTitledBorder(null, "Additional Information",TitledBorder.TOP,TitledBorder.CENTER));
		addInfoPanel.add(sp,BorderLayout.CENTER);
		
		addInfoPanel.add(clearText,BorderLayout.SOUTH);
		
		encryptPanel.setBorder(BorderFactory.createTitledBorder(null, "Encryption type",TitledBorder.TOP,TitledBorder.CENTER));
		encryptPanel.add(encryptBttn);
		encryptPanel.add(notBttn);notBttn.setSelected(true);
		
		encryptGroup.add(encryptBttn);
		encryptGroup.add(notBttn);
		//encryptBttn.setEnabled(false);
		//notBttn.setEnabled(false);
		encryptBttn.setToolTipText("This feature is not yet available in ALPHA version");
		notBttn.setToolTipText("This feature is not yet available in ALPHA version");
		passPanel.setBorder(BorderFactory.createTitledBorder(null, "Password",TitledBorder.TOP,TitledBorder.CENTER));
		passPanel.add(passField,BorderLayout.CENTER);
		passBttn.add(clearPass);
		passBttn.add(viewPass);
		passPanel.add(passBttn,BorderLayout.SOUTH);
		
		bttnPanel.add(okBttn);
		bttnPanel.add(cancelBttn);
		
		northPanel.add(cbPanel);
		northPanel.add(addInfoPanel);
		
		viewPass.addActionListener(this);
		clearPass.addActionListener(this);
		clearText.addActionListener(this);
		okBttn.addActionListener(this);
		cancelBttn.addActionListener(this);
		
		f.setLayout(new BorderLayout());
		f.add(northPanel,BorderLayout.NORTH);
		centerPanel.add(encryptPanel,BorderLayout.WEST);
		centerPanel.add(passPanel,BorderLayout.EAST);
		f.add(centerPanel);
		f.add(bttnPanel,BorderLayout.SOUTH);
		f.setSize(600,250);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		checkAvailable();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == clearPass)
		{
			passField.setText("");
		}
		
		if(e.getSource() == clearText)
		{
			textInfo.setText("");
		}
		
		if(e.getSource() == viewPass)
		{
			JOptionPane.showMessageDialog(null,""+passField.getText());
		}
		
		if(e.getSource() == cancelBttn)
		{
			idCB.setSelected(false);
			mcCB.setSelected(false);
			enCB.setSelected(false);
			esCB.setSelected(false);
			encryptGroup.clearSelection();
			f.setVisible(false);
		}
		
		if(e.getSource() == okBttn)
		{
			int CONFIRM_BLANK = confirmBlank();
			if(CONFIRM_BLANK == 1)saveProcess();
		}
	}
	
	public void checkAvailable()
	{
		File id = new File("id.temp");
		File mc = new File("mc.temp");
		File en = new File("en.temp");
		File es = new File("es.temp");
		if(!id.exists())
		{
			idCB.setEnabled(false);
			idCB.setText("Not Available");
		}
		if(!mc.exists())
		{
			mcCB.setEnabled(false);
			mcCB.setText("Not Available");
		}
		if(!en.exists())
		{
			enCB.setEnabled(false);
			enCB.setText("Not Available");
		}
		if(!es.exists())
		{
			esCB.setEnabled(false);
			esCB.setText("Not Available");
		}
		
		if((!es.exists())&&(!en.exists())&&(!mc.exists())&&(!id.exists()))
		{
			f.dispose();
			JOptionPane.showMessageDialog(null,"There are no temporary files for the different types of Examinations available.\n Go to Menu -> New to create a testpaper. \n Afterwards, you can proceed to this menu again ","Saving Status . . .",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void saveProcess() 
	{
		write w = new write();
		JFileChooser file = new JFileChooser();
		if(notBttn.isSelected())
		{
			File dir = new File(System.getProperty("user.home")+ "\\Documents\\STQS\\Questionnaire_Created");
			if(!dir.exists())
			{
				dir.mkdirs();
			}
		
			try
			{
				FileFilter filter = new ExtensionFileFilter("Test Paper","tpr");
				file.setFileFilter(filter);
				file.setAcceptAllFileFilterUsed(false);
				file.setCurrentDirectory(dir);
				int result = file.showSaveDialog(f);
				w.arrayString(passField.getText());
				w.arrayString("--END OF PASSWORD--");
				if(idCB.isSelected())w.readSave("id.temp");
				if(mcCB.isSelected())w.readSave("mc.temp");
				if(enCB.isSelected())w.readSave("en.temp");
				if(esCB.isSelected())w.readSave("es.temp");
				w.arrayString("COMMENT:");
				w.arrayString("/*");
				w.arrayString(textInfo.getText());
				w.arrayString("*/");
			
				if (result == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = file.getSelectedFile();
				
					String filename = selectedFile.getAbsolutePath() + ".tpr";
					w.writeToFile(filename);

					File tempFile = new File("temp");
					tempFile.delete();
			
					int DELETE = JOptionPane.showConfirmDialog(null,"Do you want to delete your temporary files?","Status",JOptionPane.YES_NO_OPTION);
					if( DELETE == JOptionPane.YES_OPTION )
					{
						JOptionPane.showMessageDialog(null,"Temporary Files deleted","Status",JOptionPane.INFORMATION_MESSAGE);
						File id = new File("id.temp");
						File mc = new File("mc.temp");
						File en = new File("en.temp");
						File es = new File("es.temp");
						id.delete();
						mc.delete();
						en.delete();
						es.delete();
					}
					else
					if( DELETE == JOptionPane.NO_OPTION )
					{
						JOptionPane.showMessageDialog(null,"Temporary Files not deleted!","Status",JOptionPane.INFORMATION_MESSAGE);
					}
				
					f.dispose();
				}
			}
			catch(Exception e){}
		}
		if(encryptBttn.isSelected())
		{
			File dir = new File(System.getProperty("user.home")+ "\\Documents\\STQS\\Questionnaire_Finished");
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			try
			{
			
				FileFilter filter = new ExtensionFileFilter("Test Paper","etpr");
				file.setFileFilter(filter);
				file.setAcceptAllFileFilterUsed(true);
				file.setCurrentDirectory(dir);
				int result = file.showSaveDialog(f);
				w.arrayString(passField.getText());
				w.arrayString("--END OF PASSWORD--");
				if(idCB.isSelected())w.readSave("id.temp");
				if(mcCB.isSelected())w.readSave("mc.temp");
				if(enCB.isSelected())w.readSave("en.temp");
				if(esCB.isSelected())w.readSave("es.temp");
				w.arrayString("COMMENT:");
				w.arrayString("/*");
				w.arrayString(textInfo.getText());
				w.arrayString("*/");
	
				if (result == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = file.getSelectedFile();
	
					String filename = selectedFile.getAbsolutePath() + ".etpr";
					w.writeToEncryptedFile(filename);
	
					File tempFile = new File("temp");
					tempFile.delete();
			
					int DELETE = JOptionPane.showConfirmDialog(null,"Do you want to delete your temporary files?","Status",JOptionPane.YES_NO_OPTION);
					if( DELETE == JOptionPane.YES_OPTION )
					{
						JOptionPane.showMessageDialog(null,"Temporary Files deleted","Status",JOptionPane.INFORMATION_MESSAGE);
						File id = new File("id.temp");
						File mc = new File("mc.temp");
						File en = new File("en.temp");
						File es = new File("es.temp");
						id.delete();
						mc.delete();
						en.delete();
						es.delete();
					}
					else
					if( DELETE == JOptionPane.NO_OPTION )
					{
						JOptionPane.showMessageDialog(null,"Temporary Files not deleted!","Status",JOptionPane.INFORMATION_MESSAGE);
					}
				
					f.dispose();
				}
			}
			catch(Exception e){}
		}
	}
	
	public int confirmBlank()
	{
		int RETURN = 0;
		String password = passField.getText();
		if((idCB.isSelected() == false)&&(mcCB.isSelected() == false)&&(enCB.isSelected() == false)&&(esCB.isSelected() == false))
		{
			JOptionPane.showMessageDialog(null,"There are no type of Examination checked ","Error Message",JOptionPane.ERROR_MESSAGE);
			RETURN = 0;
		}
		else
		if((encryptBttn.isSelected() == false)&&(notBttn.isSelected() == false))
		{
			JOptionPane.showMessageDialog(null,"No chosen Encryption type!","Error Message",JOptionPane.ERROR_MESSAGE);
			RETURN = 0;
		}
		else
		if(password.equals(""))
		{
			int confirm = JOptionPane.showConfirmDialog(null,"It seems that you didn't add a password to your testpaper.\nDo you still want to continue ","Error Message",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION)
			RETURN = 1;
			else
			if(confirm == JOptionPane.NO_OPTION)
			RETURN = 0;
		}
		else
		{
			if(encryptBttn.isSelected() == true)
				JOptionPane.showMessageDialog(null,"Warning! This type saves your file as a finished testpaper. You cannot edit it anymore","Error Message",JOptionPane.INFORMATION_MESSAGE);
			RETURN = 1;
		}
		
		return RETURN;
	}
	
	
	/*public static void main(String args[])
	{
		SaveGUI save = new SaveGUI();
		save.saveMethod();
	}*/
}