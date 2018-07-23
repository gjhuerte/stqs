import java.io.*;
import java.util.*;
import javax.swing.*;
import java.nio.charset.Charset;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.xml.bind.DatatypeConverter;

public class write
{
	public String _string;
	public String END_OF_ITEMS = "- - END OF ITEMS - -";
	protected String absText;
	public String display = "";
	protected int fileWritten=0;
	protected int fileEnd=0;
	protected String arrayStringText[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	FileReader fr;
	BufferedReader br;	
	public String string;
	File fileName;
	int _fileWritten;
	String _fileName = "";
	FileWriter assWriter;
	public String TAB_SEPARATOR = "	";
	public String BLANK_SPACE = " ";
	public String tempString;
	public int ctr=1;
	public String[] text = new String[100];
	
	
	protected void arrayString(String FileContent)
	{
			arrayStringText[fileWritten] = FileContent;
			fileWritten++;
			fileEnd = fileWritten;
	}
	
	public void setString(String FileContent)
	{
		arrayStringText[0] = FileContent;
		fileEnd = 1;
	}
	
	public void createFile()
	{	
		if(!fileName.exists())
		{
			
			try
			{
				assWriter = new FileWriter(fileName);
				JOptionPane.showMessageDialog(null,"New file created!","File Detection",JOptionPane.PLAIN_MESSAGE);
			}
			
			catch(IOException e)
			{
				
				JOptionPane.showMessageDialog(null,"Error in creating a file!","",JOptionPane.PLAIN_MESSAGE);
			}			
			
		}
	}
	
	public String readChoices()
	{
		String tempFile = "temp";
		String line = "";
		String rtn = "";
		String temp = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(tempFile)));
			
				line = br.readLine();
				rtn += line;
			br.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"'There are no readable file");
		}
		return rtn;
			
	}
	
	public int extractNumber(String line)  //for multiple choice use only
	{
		String tempNmbr = line;	
		String divide[] = tempNmbr.split(",");
		int no = 0; 
		no = Integer.parseInt(divide[0]);
		return no;
					
	}		
		
	protected int readFile(String _filename)
	{
		fileWritten = 0;
		try 
		{
			
			fr = new FileReader(new File(_filename));
			br = new BufferedReader(fr);
			
			while((string = br.readLine())!= null) 
			{
				if(string.equals(END_OF_ITEMS))
				{
					break;
				}
				else
				{
					arrayStringText[fileWritten] = string;  
					fileWritten++;
				}
			}
			
			br.close();
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error in reading file!! Creating a new File . . .","",JOptionPane.PLAIN_MESSAGE);
			createFile();
		}
		
		_fileWritten = fileWritten;
		fileWritten = 0;
		return _fileWritten; 
	}
	
	public String viewFile()
	{
		display = arrayStringText[fileWritten];
		fileWritten++;
		if(fileWritten == _fileWritten)
			fileWritten = 0;
		return display;
	}
	
		
	
	
	public void readSave(String _filename)
	{
		try 
		{
			
			fr = new FileReader(new File(_filename));
			br = new BufferedReader(fr);
			
			while((string = br.readLine())!= null)  
			{
				arrayStringText[fileWritten] = string; 
				fileWritten++;
			}
			
			br.close();
		}
		
		catch(Exception e)
		{
			String z = "File [ " + _filename + " ] not Found"; 
			JOptionPane.showMessageDialog(null,""+z,"",JOptionPane.PLAIN_MESSAGE);
		}
		
		fileEnd = fileWritten;
		
	}
	

	
	public void writeToFile(String _fileName)
	{
		try	
		{		
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(_fileName)));
				
				for(fileWritten=0 	;fileWritten<fileEnd;fileWritten++)
				{
					bufferedWriter.append(arrayStringText[fileWritten]);	
					bufferedWriter.newLine();
				}
					bufferedWriter.append(END_OF_ITEMS);	
					bufferedWriter.newLine();	
					
				bufferedWriter.flush();		
				bufferedWriter.close();
		}
		
		catch(Exception e)
		 {
			 JOptionPane.showMessageDialog(null,"Error in reading file!! Creating a new File . . .","",JOptionPane.PLAIN_MESSAGE);
			  createFile();
		 }
		 
		 fileWritten = 0;
		 
	}
	
	public void writeToEncryptedFile(String _fileName)
	{
		try
		{
			EncryptData ed = new EncryptData();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(_fileName)));
			for(fileWritten=0 	;fileWritten<fileEnd;fileWritten++)
				{
					String target = arrayStringText[fileWritten];
					if(!target.equals(null))
					{
						String encrypted = ed.encrypt(target);
						bufferedWriter.append(encrypted);
						
					}
					else
						bufferedWriter.append(arrayStringText[fileWritten]);
						bufferedWriter.newLine();
				}
					bufferedWriter.append(END_OF_ITEMS);	
					bufferedWriter.newLine();	
					
				bufferedWriter.flush();		
				bufferedWriter.close();	    
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		 fileWritten = 0;
    }	 
}

class EncryptData
{
	private static final String FORMAT = "ISO-8859-1";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    SecretKey key;
	
	public static String getSpecialCharacter(int code) 
	{

        Charset charSet = Charset.forName(FORMAT);
        String specialCharacter = new String(new byte[] { (byte) code }, charSet);
        specialCharacter = String.format("%s", specialCharacter);

        return specialCharacter;
    }
	public EncryptData() throws Exception 
	{

        String myEncryptionKey = "4A144BEBF7E5E7B7DCF26491AE79C54C768C514CF1547D23";

        ks = new DESedeKeySpec(myEncryptionKey.getBytes(FORMAT));
        skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
        cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
        key = skf.generateSecret(ks);
    }
	
    public String encrypt(String unencryptedString) throws Exception 
	{

        String encryptedString = null;
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plainText = unencryptedString.getBytes(FORMAT);
        byte[] encryptedText = cipher.doFinal(plainText);
        encryptedString = DatatypeConverter.printBase64Binary(encryptedText);

        return encryptedString;
    }

    public String decrypt(String encryptedString)  throws Exception 
	{

        String decryptedText = null;
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedText = DatatypeConverter.parseBase64Binary(encryptedString);
        byte[] plainText = cipher.doFinal(encryptedText);
        decryptedText = new String(plainText);

        return decryptedText;
    }
}