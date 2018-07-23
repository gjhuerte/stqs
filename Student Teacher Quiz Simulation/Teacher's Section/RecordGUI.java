import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileFilter;

public class RecordGUI extends JFrame implements ActionListener
{
	JPanel upperPanel = new JPanel(new GridLayout(1,2));
	JTextField nameField = new JTextField();
	JTextField columnField = new JTextField();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem openBttn = new JMenuItem("Open");
	JMenuItem addBttn = new JMenuItem("Save");
	JMenuItem exitBttn = new JMenuItem("Exit");
	String columnNames = "";
	DefaultTableModel model = new DefaultTableModel();
	JTable table;
	String NEWLINE = "\n";
	String csvSplitBy = ",";
	
	public void launchFrame(String NAME,int grade)
	{
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(openBttn);openBttn.addActionListener(this);
		menu.add(addBttn);addBttn.addActionListener(this);
		menu.add(exitBttn);exitBttn.addActionListener(this);
		upperPanel.add(nameField);nameField.setBorder(BorderFactory.createTitledBorder(null, "Name",TitledBorder.TOP,TitledBorder.CENTER));nameField.setText(NAME);nameField.setEditable(false);
		upperPanel.add(columnField);columnField.setBorder(BorderFactory.createTitledBorder(null, "Column",TitledBorder.TOP,TitledBorder.CENTER));columnField.setText(""+grade);columnField.setEditable(false);
		
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane,BorderLayout.CENTER);
		add(upperPanel,BorderLayout.NORTH);
		setVisible(true);
		setSize(1320,800);
		addWindowListener(new WindowListener(){
			public void windowOpened(WindowEvent e){}
			public void windowClosing(WindowEvent e){}
			public void windowDeactivated(WindowEvent e){}
			public void windowIconified(WindowEvent e){}
			public void windowDeiconified(WindowEvent e){}
			public void windowActivated(WindowEvent e){}
			public void windowClosed(WindowEvent e)
			{
				removeAll();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == openBttn )
		{
			openMethod();
		}
		
		if( e.getSource() == addBttn )
		{
			saveFile();
			dispose();
		}
		
		if( e.getSource() == exitBttn )
		{
			dispose();
		}
	
	}
	
	public void openMethod()
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
		int result = file.showOpenDialog(null); 
			 
		if (result == JFileChooser.APPROVE_OPTION) 
		{
			File selectedFile = file.getSelectedFile();
			filename =  selectedFile.getAbsolutePath();
			String line = "";
			try 
			{
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				line = reader.readLine();
				String temp[] = line.split(csvSplitBy);
				for(int x = 0;x<temp.length;x++)
					model.addColumn(temp[x]);
				while((line = reader.readLine()) != null)                                
				{
					model.addRow(line.split(csvSplitBy));
				}
			
				reader.close();
			}
			
			catch(Exception e){}
		}
		
		if(result == JFileChooser.CANCEL_OPTION)
		{
			dispose();
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
		file.setAcceptAllFileFilterUsed(true);
		file.setCurrentDirectory(dir);
		int result = file.showSaveDialog(null);
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
				dispose();
			}
		
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,"Error in saving . . .","Status",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}	
		}
	}
}
