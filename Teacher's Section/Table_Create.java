import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileFilter;

public class Table_Create implements ActionListener
{
	JFrame f = new JFrame("Table1");
	String ColumnNames = "";
	JPanel bttnPanel = new JPanel(new GridLayout(2,2));
	JButton rowAdd = new JButton("Add Row");
	JButton columnAdd = new JButton("Add Column");
	JButton saveBttn = new JButton("Save");
	JButton cancelBttn = new JButton("Exit");
	String NEWLINE = "\n";
	String csvSplitBy = ",";
	DefaultTableModel model = new DefaultTableModel();
    JTable table;
	Object column[];
	int height = 110;
	int width = 250;
	
	public void launchFrame()
	{
			
			table = new JTable(model){  
			
				public boolean isCellEditable(int row, int column)
				{  
					return true;  
				}  
												};
												
												
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		f.add(scrollPane, BorderLayout.CENTER); 
		
		rowAdd.setEnabled(false);
		bttnPanel.add(rowAdd);
		bttnPanel.add(columnAdd);
		bttnPanel.add(saveBttn);
		bttnPanel.add(cancelBttn);
		
		rowAdd.addActionListener(this);
		columnAdd.addActionListener(this);
		saveBttn.addActionListener(this);
		cancelBttn.addActionListener(this);
		
		f.add(bttnPanel,BorderLayout.SOUTH);
		f.setSize(width,height);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == rowAdd )
		{
			model.addRow(new Object[]{JOptionPane.showInputDialog("Enter Item:")});
			height+=20;
			f.setSize(width,height);
		}
		
		if( e.getSource() == columnAdd )
		{
			rowAdd.setEnabled(true);
			String input = JOptionPane.showInputDialog("Column name:");
			model.addColumn(input);
			ColumnNames += input;
			ColumnNames += ",";
			width+=50;
			f.setSize(width,height);
		}
		
		if(e.getSource() == saveBttn)
		{
			saveFile();
		}
		
		if( e.getSource() == cancelBttn)
		{
			f.dispose();
		}
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
		file.setAcceptAllFileFilterUsed(false);
		file.setCurrentDirectory(dir);
		int result = file.showSaveDialog(f);
		String filename = file.getSelectedFile() + ".csv";
		File selectedFile = new File(filename);
		if(result == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
				int row = model.getRowCount();
				int column = model.getColumnCount();
				bw.append(ColumnNames);
				bw.newLine();
				ColumnNames = "";
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
			}
		
			catch (Exception e)
			{
			
			}	
		}
		
		if(result == JFileChooser.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null,"File not saved!","Status",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void main(String args[])
	{
		Table_Create tc = new Table_Create();
		tc.launchFrame();
	}
}