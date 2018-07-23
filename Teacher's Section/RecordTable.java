import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileFilter;

public class RecordTable implements ActionListener
{
	JFrame f;
	Container contentpane;
	JPanel bttnPanel = new JPanel(new GridLayout(2,2));
	JButton saveBttn = new JButton("Save");
	JButton exitBttn = new JButton("Exit");
	JButton rowAdd = new JButton("Add Row");
	JButton columnAdd = new JButton("Add Column");
	String columnNames = "";
	DefaultTableModel model = new DefaultTableModel();
	JTable table;
	String NEWLINE = "\n";
	String csvSplitBy = ",";
	
	public void launchFrame()
	{
		String file = openFile();
		addReadColumn(file);
		addReadRow(file);
		bttnPanel.add(rowAdd);
		bttnPanel.add(columnAdd);
		f = new JFrame(file);
		bttnPanel.add(saveBttn);
		bttnPanel.add(exitBttn);
		
		saveBttn.addActionListener(this);
		exitBttn.addActionListener(this);
		rowAdd.addActionListener(this);
		columnAdd.addActionListener(this);
		
		JTable table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		contentpane = f.getContentPane();
		
		contentpane.add(scrollPane, BorderLayout.CENTER);
		contentpane.add(bttnPanel, BorderLayout.SOUTH);
			
		f.setSize(1000,1000);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == saveBttn)
		{
			saveFile();
		}
		
		if(e.getSource() == exitBttn)
		{
			f.setVisible(false);
		}
		
		if( e.getSource() == rowAdd )
		{
			model.addRow(new Object[]{JOptionPane.showInputDialog("Enter Item:")});
		}
		
		if( e.getSource() == columnAdd )
		{
			rowAdd.setEnabled(true);
			String input = JOptionPane.showInputDialog("Column name:");
			model.addColumn(input);
			columnNames += input;
			columnNames += ",";
		}
	}
	
	public void addReadColumn(String file)
	{
		String line = "";
		File filename = new File(file);
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			line = reader.readLine();
			columnNames = line;
			String[] temp1 = line.split(csvSplitBy);
			int x = 0;
			while(!temp1[x].equals(""))
			{
				model.addColumn(temp1[x]);
				x++;
			}
			
			reader.close();
		}
			
		catch(Exception e)
		{
				
		}
		
	}
	
	public void addReadRow(String file)
	{
		String line = "";
		String csvSplitBy = ",";
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
			line = reader.readLine();
            while((line = reader.readLine()) != null)                                
            {
				model.addRow(line.split(csvSplitBy));
            }
			
			reader.close();
		}
			
		catch(Exception e)
		{
				
		}
		
	}
	
	public String openFile()
	{
		File dir = new File(System.getProperty("user.home")+"\\Documents\\STQS\\Records");
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		String filename = "";
		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(dir);	
		FileFilter filter = new ExtensionFileFilter("Class Records","csv");
		file.setFileFilter(filter);
		file.setAcceptAllFileFilterUsed(false);
		int result = file.showOpenDialog(contentpane); 
			 
		if (result == JFileChooser.APPROVE_OPTION) 
		{
			File selectedFile = file.getSelectedFile();
			filename =  selectedFile.getAbsolutePath();
		}
		
		if(result == JFileChooser.CANCEL_OPTION)
		{
			f.setVisible(false);
		}
		
		return filename;
	}
	
	public void saveFile()
	{
		write w = new write();
		File dir = new File(System.getProperty("user.home")+"\\Documents\\STQS\\Records");
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		JFileChooser file = new JFileChooser();
		FileFilter filter = new ExtensionFileFilter("Class Records","csv");
		file.setFileFilter(filter);
		file.setAcceptAllFileFilterUsed(true);
		file.setCurrentDirectory(dir);
		int result = file.showSaveDialog(f);
		if(result == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				String filename = file.getSelectedFile() + ".csv";
				File selectedFile = new File(filename);
				BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
				int row = model.getRowCount();
				int column = model.getColumnCount();
				bw.append(columnNames);
						System.out.println(columnNames);
				bw.newLine();
				columnNames = "";
				
				for (int j = 0; j  < row; j++) 
				{
					for (int i = 0; i  < column; i++) 
					{
						try
						{
								bw.append((String)model.getValueAt(j, i));
								bw.append(csvSplitBy);	
						}
						catch(Exception e){}
					}
				
					bw.newLine();
				}
		
				bw.close();	
				JOptionPane.showMessageDialog(null,"Success in saving . . .","Status",JOptionPane.INFORMATION_MESSAGE);
			}
		
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,"Error in saving . . .","Status",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}	
		}
		
		if(result == JFileChooser.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null,"File not saved!","Status",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void main(String args[])
	{
		RecordTable r = new RecordTable();
		r.launchFrame();
	}
	
	
	
}

