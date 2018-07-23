import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class checkGUI implements KeyListener
{
	AnswerCreator ac = new AnswerCreator();
	JFrame f = new JFrame();
	JTextArea area = new JTextArea(45,45);
	JPanel questionPanel = new JPanel(new BorderLayout());
	JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	JTextArea scoreArea = new JTextArea(20,20);
	JButton submitBttn = new JButton("Submit");
	JButton cancelBttn = new JButton("Cancel");
	int  x = 0;
	int ctr = 0;
	String FileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	int END;

	int idCORRECT_POINTS = 0;
	int mcCORRECT_POINTS = 0;
	int enCORRECT_POINTS = 0;
	int esCORRECT_POINTS = 0;
	
	public void checkGUI()
	{
		f.setUndecorated(true);
		f.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		String temp[] = FileContent[ctr].split(",");
		area.setText(temp[1]+":\n"+temp[0]);
		buttonPanel.add(submitBttn);submitBttn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(ctr < END)
				{
					String _temp[] = FileContent[ctr].split(",");
					String temp[] = new String[]{"",""};
					for(int x  = 0;x<_temp.length;x++)
						temp[x] += _temp[x];
					area.setText(temp[1]+":\n"+temp[0]);
					String score = scoreArea.getText();
					if(!score.equals(""))
					{
						if(temp[1].equals("I. IDENTIFICATION"))
						{
							ctr++;
							idCORRECT_POINTS+=Integer.parseInt(score);
						}
						if(temp[1].equals("II. MULTIPLE CHOICE"))
						{
							
							ctr++;
							mcCORRECT_POINTS+=Integer.parseInt(score);
						}
						if(temp[1].equals("III. ENUMERATION"))
						{
							ctr++;
							enCORRECT_POINTS+=Integer.parseInt(score);
						}
						if(temp[1].equals("IV. ESSAY WRITING"))
						{
							ctr++;
							esCORRECT_POINTS+=Integer.parseInt(score);
						}
					}
				}
				else
					f.dispose();
			}
		});
		buttonPanel.add(cancelBttn);cancelBttn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int RESULT = JOptionPane.showConfirmDialog(null,"Points will not be given for this item.\nDo you still want to continue?","Status",JOptionPane.YES_NO_OPTION);
				if(RESULT == JOptionPane.YES_OPTION)
				f.dispose();
			}
		});
		area.setBorder(BorderFactory.createTitledBorder(null,"Question",TitledBorder.TOP,TitledBorder.LEFT));
		scoreArea.setBorder(BorderFactory.createTitledBorder(null,"Points if considered",TitledBorder.TOP,TitledBorder.LEFT));
		area.setEditable(false);
		questionPanel.add(scoreArea,BorderLayout.CENTER);
		questionPanel.add(buttonPanel,BorderLayout.SOUTH);
		f.setLayout(new GridLayout(1,2));
		f.add(area);scoreArea.addKeyListener(this);
		
		f.add(questionPanel);
		f.setSize(400,150);
		f.setLocationRelativeTo(ac.studAnswerPanel);
		f.setVisible(true);
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == scoreArea)
		{
			if((e.getKeyCode()>=48)&&(e.getKeyCode()<=57));
			else
			if((e.getKeyCode()==10)||(e.getKeyCode()==8));
			else
			if((e.getKeyCode()>36)&&(e.getKeyCode()<41));
			else
			if((e.getKeyCode()<96)||(e.getKeyCode()>105))
			{
				JOptionPane.showMessageDialog(null,"Numbers only!","Error Status",JOptionPane.ERROR_MESSAGE);
				scoreArea.setText("");
			}
		}
	}
	
	public void setWords(String QUESTION,String TYPE)
	{
		FileContent[ctr] = QUESTION + "," + TYPE;
		ctr++;
		END = ctr;
	}
	
	public static void main(String args[])
	{
		checkGUI c = new checkGUI();
	}
}