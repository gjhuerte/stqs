 import java.io.*;
import javax.swing.*;

public class resetData
{
	public resetData()
	{
		String CURRENT_PASSWORD = OPEN_ACCOUNT();
		String password = JOptionPane.showInputDialog("Enter password:");
		if(password.equals(CURRENT_PASSWORD))
		{
			File dir1;
			try
			{
				String DELETED_FILE = "";
				String FILE1 = "";
				String FILE2 = "";
				dir1 = new File(System.getProperty("user.home")+ "\\Documents\\STQS");
				if(dir1.exists())
					FILE1 += dir1;
				rmdir(dir1);

				File dir2 = new File("accounts.txt");dir2.delete();dir1.delete();
				if(dir2.exists())
				FILE2 += "Accounts";
				DELETED_FILE += "\n" + FILE1;
				DELETED_FILE += "\n" + FILE2;
				if(DELETED_FILE.equals("\n\n"))
				{
					JOptionPane.showMessageDialog(null,"Files not found or already deleted!","Status",JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null,"Files deleted:"+DELETED_FILE,"Status",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"ERROR DETECTED!","Status",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null,"Wrong Password!","Status",JOptionPane.ERROR_MESSAGE);
	}
	
	public String OPEN_ACCOUNT() 
	{
		String CURRENT_PASSWORD = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File("accounts.txt")));
			br.readLine();
			br.readLine();
			CURRENT_PASSWORD = br.readLine();
			br.close();
		}
		catch(Exception e){}
		return CURRENT_PASSWORD;
	}
	
	public  void rmdir(final File  dir1)
	{
		if (dir1.isDirectory()) 
				{
					File[] list = dir1.listFiles();
					if (list != null) 
					{
						for (int i = 0; i < list.length; i++) 
						{
							File tmpF = list[i];
							if (tmpF.isDirectory()) rmdir
							(tmpF);
							tmpF.delete();
						}
					}
				}
	}
	public static void main(String args[])
	{
		resetData r = new resetData();
	}
}