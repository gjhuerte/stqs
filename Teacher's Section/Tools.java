import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Tools implements ActionListener
{
	write w = new write();
	JFrame f1 = new JFrame();	
	Container contentpane;
	int ctr = 1;
	int NUMBER_OF_CHOICE = 0;
	String arrayText = "";
	String NEWLINE = ",";
	String display = "";
	String filename = "temp";
	
	
	//launchFrame
	
	JPanel panel = new JPanel(new GridLayout(0,2,0,10));
	
	JLabel c1 = new JLabel("Choice 1");
	JTextField t1 = new JTextField(20);
	
	JLabel c2 = new JLabel("Choice 2");
	JTextField t2 = new JTextField(20);
	
	JLabel c3 = new JLabel("Choice 3");
	JTextField t3 = new JTextField(20);
	
	JLabel c4 = new JLabel("Choice 4");
	JTextField t4 = new JTextField(20);
	
	JLabel c5 = new JLabel("Choice 5");
	JTextField t5 = new JTextField(20);
	
	JLabel c6 = new JLabel("Choice 6");
	JTextField t6 = new JTextField(20);
	
	JLabel c7 = new JLabel("Choice 7");
	JTextField t7 = new JTextField(20);
	
	JLabel c8 = new JLabel("Choice 8");
	JTextField t8 = new JTextField(20);
	
	JLabel c9 = new JLabel("Choice 9");
	JTextField t9 = new JTextField(20);
	
	JLabel c10 = new JLabel("Choice 10");
	JTextField t10 = new JTextField(20);
	
	JButton submit = new JButton("Submit");
	JButton cancel = new JButton("Cancel");
	
	public void launchFrame()
	{
		int y = 120;
		NUMBER_OF_CHOICE = 0;
		panel.setBorder(BorderFactory.createTitledBorder(null, "Choices",TitledBorder.TOP,TitledBorder.CENTER));
		while((NUMBER_OF_CHOICE < 1) || (NUMBER_OF_CHOICE > 10))
		{
			String temp = "";
			try
			{
				temp = JOptionPane.showInputDialog(null,"Enter the number of Choices ( MAX of 10 ):","NUMBER OF CHOICES",JOptionPane.QUESTION_MESSAGE);
				NUMBER_OF_CHOICE = Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "ERROR!  Choose a number from  1 to 9", "NUMBER OF CHOICES",JOptionPane.ERROR_MESSAGE);
				break;
			}
			
			if( NUMBER_OF_CHOICE > 10 )
			{
				JOptionPane.showMessageDialog(null, "Exceeds the number of choices", "NUMBER OF CHOICES",JOptionPane.ERROR_MESSAGE);
			}
			else
			if( NUMBER_OF_CHOICE < 1 )
			{
				JOptionPane.showMessageDialog(null, "Below the number of choices", "NUMBER OF CHOICES",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if( NUMBER_OF_CHOICE >= 1 )
				{
					panel.add(c1);
					panel.add(t1);
					if( NUMBER_OF_CHOICE >= 2 )
					{	
						panel.add(c2);
						panel.add(t2);
						y+=50;
						if( NUMBER_OF_CHOICE >= 3 )
						{
							panel.add(c3);
							panel.add(t3);
							y+=50;
							if( NUMBER_OF_CHOICE >= 4 )
							{
								panel.add(c4);
								panel.add(t4);
								y+=50;						
								if( NUMBER_OF_CHOICE >= 5 )
								{
									panel.add(c5);
									panel.add(t5);
									y+=50;
									if( NUMBER_OF_CHOICE >= 6 )
									{
										panel.add(c6);
										panel.add(t6);
										y+=50;
										if( NUMBER_OF_CHOICE >= 7 )
										{
											panel.add(c7);
											panel.add(t7);
											y+=50;
											if( NUMBER_OF_CHOICE >= 8 )
											{
												panel.add(c8);
												panel.add(t8);
												y+=50;
												if( NUMBER_OF_CHOICE >= 9 )
												{
													panel.add(c9);
													panel.add(t9);
													y+=50;
													if( NUMBER_OF_CHOICE >= 10)
													{
														panel.add(c10);
														panel.add(t10);
														y+=50;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				submit.addActionListener(this);
				cancel.addActionListener(this);
				panel.add(submit);
				panel.add(cancel);
				contentpane = f1.getContentPane();
				contentpane.add(panel,BorderLayout.CENTER);
		
				f1.setSize(450,y);
				f1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				f1.setVisible(true);
				f1.setResizable(false);
				
			}
		}
		
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == submit )
		{
			if( NUMBER_OF_CHOICE >= 1 )
			{
				display = "";
				display += NUMBER_OF_CHOICE;
				display += NEWLINE;
				display += t1.getText();
				if( NUMBER_OF_CHOICE >= 2 )
				{	
					display += NEWLINE;
					display += t2.getText();
					if( NUMBER_OF_CHOICE >= 3 )
					{
						display += NEWLINE;
						display += t3.getText();
						if( NUMBER_OF_CHOICE >= 4 )
						{
							display += NEWLINE;
							display += t4.getText();
							if( NUMBER_OF_CHOICE >= 5 )
							{
								display += NEWLINE;
								display += t5.getText();
								if( NUMBER_OF_CHOICE >= 6 )
								{
									display += NEWLINE;
									display += t6.getText();
									if( NUMBER_OF_CHOICE >= 7 )
									{
										display += NEWLINE;
										display += t7.getText();
										if( NUMBER_OF_CHOICE >= 8 )
										{
											display += NEWLINE;
											display += t8.getText();
											if( NUMBER_OF_CHOICE >= 9 )
											{
												display += NEWLINE;
												display += t9.getText();
												if( NUMBER_OF_CHOICE >= 10)
												{
													display += NEWLINE;
													display += t10.getText();
												}
											}
										}
									}
								}
							}
						}
					}
				}	
			}
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t7.setText("");
		t8.setText("");
		t9.setText("");
		t10.setText("");
		NUMBER_OF_CHOICE = 0;
		w.setString(display);
		w.writeToFile(filename);
		f1.dispose();
		removeDisplay();
						
		}
		
		if( e.getSource() == cancel )
		{
			f1.dispose();
			
		}
		
	}
	
	public void mcChoice()
	{
			launchFrame();
	}
	
	public void removeDisplay()
	{
		
		panel.remove(t1);
		panel.remove(t2);
		panel.remove(t3);
		panel.remove(t4);
		panel.remove(t5);
		panel.remove(t6);
		panel.remove(t7);
		panel.remove(t8);
		panel.remove(t9);
		panel.remove(t10);
		panel.remove(c1);
		panel.remove(c2);
		panel.remove(c3);
		panel.remove(c4);
		panel.remove(c5);
		panel.remove(c6);
		panel.remove(c7);
		panel.remove(c8);
		panel.remove(c9);
		panel.remove(c10);
		
	}
	
	public static void main(String args[])
	{
		Tools t = new Tools();
		t.launchFrame();
	}
	
}