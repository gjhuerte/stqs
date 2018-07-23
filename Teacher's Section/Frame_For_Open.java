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
import javax.swing.JFileChooser;

public class Frame_For_Open implements ActionListener
{		

	public String END_OF_ITEMS = "- - END OF ITEMS - -";
	String FileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	
	String idFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	
	String mcFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	
	String enFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	
	String esFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	
	String filename = "";
	JFrame f;
	JPanel panel = new JPanel(new BorderLayout());
	int success = 0;
	
	JPanel leftPanel = new JPanel(new GridLayout(5,1));
	JPanel rightPanel = new JPanel(new GridLayout(4,2));
	
	JTextField fileChosen = new JTextField("None",12);
	JCheckBox id = new JCheckBox("IDENTIFICATION");
	JCheckBox mc = new JCheckBox("MULTIPLE CHOICE");
	JCheckBox en = new JCheckBox("ENUMERATION");
	JCheckBox es = new JCheckBox("ESSAY WRITING");
	
	JLabel idLbl = new JLabel("IDENTIFICATION:");
	JLabel idPresent = new JLabel("Not Existing");
	JLabel mcLbl = new JLabel("MULTIPLE CHOICE:");
	JLabel mcPresent = new JLabel("Not Existing");
	JLabel enLbl = new JLabel("ENUMERATION:");
	JLabel enPresent = new JLabel("Not Existing");
	JLabel esLbl = new JLabel("ESSAY WRITING:");
	JLabel esPresent = new JLabel("Not Existing");
	
	JPanel BttmPanel = new JPanel();
	JButton saveBttn = new JButton("Open and Create temp");
	JButton cancelBttn = new JButton("Cancel");
	
	
	public void launchFrame()
	{
		openMethod();
		setPresent();
		f = new JFrame(filename);
		fileChosen.setEditable(false);
		leftPanel.setBorder(BorderFactory.createTitledBorder(null, "File to Open",TitledBorder.TOP,TitledBorder.CENTER));
		leftPanel.add(fileChosen);
		leftPanel.add(id);
		leftPanel.add(mc);
		leftPanel.add(en);
		leftPanel.add(es);
		
		rightPanel.setBorder(BorderFactory.createTitledBorder(null, "Existing File",TitledBorder.TOP,TitledBorder.CENTER));
		rightPanel.add(idLbl);
		rightPanel.add(idPresent);
		rightPanel.add(mcLbl);
		rightPanel.add(mcPresent);
		rightPanel.add(enLbl);
		rightPanel.add(enPresent);
		rightPanel.add(esLbl);
		rightPanel.add(esPresent);
		
		BttmPanel.add(saveBttn);saveBttn.addActionListener(this);
		BttmPanel.add(cancelBttn);cancelBttn.addActionListener(this);
		
		panel.setBorder(BorderFactory.createTitledBorder(null, "Open",TitledBorder.TOP,TitledBorder.CENTER));
	
		panel.add(leftPanel,BorderLayout.WEST);
		panel.add(rightPanel,BorderLayout.CENTER);
		panel.add(BttmPanel,BorderLayout.SOUTH);
		
		f.add(panel);
		f.setSize(400,250);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == saveBttn)
		{
			if(success == 1)
			{
				int ok = JOptionPane.showConfirmDialog(null,"You still have existing temp file.Do you still want to continue?","Status",JOptionPane.YES_NO_OPTION);
				
				if(ok == JOptionPane.YES_OPTION)
				{
					readFile();
					f.setVisible(false);
				}
			}
			else
			{
				readFile();
				f.setVisible(false);
			}				
		}
		
		if(e.getSource() == cancelBttn)
		{
			f.setVisible(false);
			filename = "";
		}
	}
	
	public void setPresent()
	{
		
		File id = new File("id.temp");
		File mc = new File("mc.temp");
		File en = new File("en.temp");
		File es = new File("es.temp");
	
		if(id.exists())
		{
			idPresent.setText("EXISTING");	
			saveBttn.setText("Replace temp");
			success = 1;
		}
		else
			idPresent.setText("NOT EXISTING");	
		
		if(mc.exists())
		{
			mcPresent.setText("EXISTING");
			saveBttn.setText("Replace temp");
			success = 1;
		}
		else
			mcPresent.setText("NOT EXISTING");	
		
		if(en.exists())
		{
			enPresent.setText("EXISTING");
			saveBttn.setText("Replace temp");	
			success = 1;
		}
		else
			enPresent.setText("NOT EXISTING");	
		
		if(es.exists())
		{
			esPresent.setText("EXISTING");
			saveBttn.setText("Replace temp");
			success = 1;
		}
		else
			esPresent.setText("NOT EXISTING");
		
		
	}
	
	public void openMethod()
	{
		File dir = new File(System.getProperty("user.home")+ "\\Documents\\STQS\\Questionnaire_Created");
		if(!dir.exists())
		{
			dir.mkdirs();
		}
			JFileChooser file = new JFileChooser();
			FileFilter filter = new ExtensionFileFilter("Test Paper","tpr");
			file.setFileFilter(filter);
			file.setAcceptAllFileFilterUsed(false);
			
			file.setCurrentDirectory(dir);
			int result = file.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) 
			{
				File selectedFile = file.getSelectedFile();
				filename = selectedFile.getAbsolutePath();
				fileChosen.setText(filename);
			}
			
			if(result == JFileChooser.CANCEL_OPTION)
			{
				f.setVisible(false);	
			}
	}
	
	public void readFile()
	{
		int x = 0;
		String line = "";
		try{
			
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			line = br.readLine();line = br.readLine() ;
			
			if((line = br.readLine()).equals("I. IDENTIFICATION"))
			{
				idFileContent[x] = line;x++;
				while(!(line = br.readLine()).equals(END_OF_ITEMS))
				{
					idFileContent[x] = line;
					x++;
				}
				
				if(id.isSelected())
				{
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("id.temp")));
					for(int y = 0;y<x;y++)
					{
						bw.append(idFileContent[y]);
						bw.newLine();
					}
					bw.append(END_OF_ITEMS);
					bw.close();
				}
					
				x = 0;
			}
			
			if((line = br.readLine()).equals("II. MULTIPLE CHOICE"))
			{
				mcFileContent[x] = line;x++;
				while(!(line = br.readLine()).equals(END_OF_ITEMS))
				{
					mcFileContent[x] = line;
					x++;
				}
				
				if(mc.isSelected())
				{
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("mc.temp")));
					for(int y = 0;y<x;y++)
					{
						bw.append(mcFileContent[y]);
						bw.newLine();
					}
						bw.append(END_OF_ITEMS);
						bw.close();
				}
				x = 0;
			
			}
			
			if((line = br.readLine()).equals("III. ENUMERATION"))
			{
				enFileContent[x] = line;x++;
				while(!(line = br.readLine()).equals(END_OF_ITEMS))
				{
					enFileContent[x] = line;
					x++;
				}
				
				if(en.isSelected())
				{
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("en.temp")));
					for(int y = 0;y<x;y++)
					{
						bw.append(enFileContent[y]);
						bw.newLine();
					}
						bw.append(END_OF_ITEMS);
						bw.close();
				}
				
		
				x = 0;
			}
			
			if((line = br.readLine()).equals("IV. ESSAY WRITING"))		
			{
				esFileContent[x] = line;x++;
				while(!(line = br.readLine()).equals(END_OF_ITEMS))
				{
					esFileContent[x] = line;
					x++;
				}
		
				if(es.isSelected())
				{
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("es.temp")));
					for(int y = 0;y<x;y++)
					{
						bw.append(esFileContent[y]);
						bw.newLine();
					}
						bw.append(END_OF_ITEMS);
						bw.close();
				}
					
				x = 0;
			}
			br.close();
			JOptionPane.showMessageDialog(null,"Temporary files created!","Status",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e){}
		
		
	}
	
	/*public static void main(String args[])
	{
		Frame_For_Open ffo = new Frame_For_Open();
		ffo.launchFrame();
	}*/
}