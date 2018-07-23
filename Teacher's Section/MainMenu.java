import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.border.*;
import java.util.Calendar;
import java.text.*;
import java.util.Date;

public class MainMenu implements ActionListener
{
	AccountFrame af = new AccountFrame();
	Tools t = new Tools();  //instantiate the tools class 
	write w = new write(); //instantiate the write class
	int viewBttnIsUsed = 0;  //sets to zero if viewBttn is not used
	int x = 0; //my global variable used for every computation
	Timer timer = new Timer(1000,this);
	public String END_OF_ITEMS = "- - END OF ITEMS - -";
	String filename;
	String BLANK_SPACE = "";
	String TAB_SEPARATOR = "	";
	String display = "";
	String NEWLINE = "\n";
	String esLimit = "";
	int totalNoOfItems = 0;
	int option = 0;
	int fileEnd = 2;
	int _fileEnd = 0; //temporary fileEnd Variable
	int idFileEnd = 2;
	int mcFileEnd = 2;
	int enFileEnd = 2;
	int esFileEnd = 2;
	int no = 1;
	int ctr;
	int rightToSave = 0;
	String testno = "";
	String instruction = "" ;
	String _instruction = "";
	String instructionTemp = "";
	String idFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String mcFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String enFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String esFileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String FileContent[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	
	JFrame f = new JFrame("Teachers Section");
	Container contentpane;
	JScrollPane pane;
	JPanel eastPane = new JPanel();
	Container falseContainer;
	
	JPanel panel = new JPanel(new GridLayout(1,2));
	
	JMenuBar menu = new JMenuBar();
	
	//Menu for menu
	
	JMenu mainMenu = new JMenu("Menu");
	JMenu registerMenu = new JMenu("Registration");
	JMenu createMenu = new JMenu("Create");
	JMenu helpMenu = new JMenu("Help");
	
	//menu item for menu
	
	JMenuItem newMenuItem = new JMenuItem("Create Questionnaire");
	JMenuItem openMenuItem = new JMenuItem("Open a Questionnaire");
	JMenuItem saveMenuItem = new JMenuItem("Save Questionnaire");
	JMenuItem closeMenuItem = new JMenuItem("Close Creator");
	JMenuItem exitMenuItem = new JMenuItem("Exit Program");
	
	//menu item for registerMenu
	
	JMenuItem accountInfoMenuItem = new JMenuItem("Change Account Information");
	
	
	//menu item for createMenu
	
	JMenu recordMenu= new JMenu("Class Record");
	
	//menu item for class recordMenu
	
	JMenuItem createRecord = new JMenuItem("Create Class Record");
	JMenuItem openClassRecord = new JMenuItem("Open a Class Record");
	
	JMenuItem answerMenuItem = new JMenuItem("Create an Answer Sheet/ Check Testpaper");
	
	//menu item for help
	
	JMenuItem instructionsMenuItem = new JMenuItem("Instruction");
	JMenuItem authorNoteMenuItem = new JMenuItem("Authors Note");
	JMenuItem purchaseMenuItem = new JMenuItem("Legal Rights");
	JMenuItem versionMenuItem = new JMenuItem("Version");
	JMenuItem resetMenuItem = new JMenuItem("Reset Data");
	
	//identificationButton , enumerationButton , essayWritingButton , multipleChoiceButton

	JPanel buttonContainer = new JPanel(new GridLayout(0,4));
	JButton identificationButton = new JButton("Identification");
	JButton multipleChoiceButton = new JButton("Multiple Choice");
	JButton enumerationButton = new JButton("Enumeration");
	JButton essayWritingButton = new JButton("Essay Writing");
	
	//viewContainer
	
	JPanel viewContainer = new JPanel();
	String son = "[ Not Saved ]";
	JLabel s = new JLabel(son);
	JTextArea area = new JTextArea(39,40);
	
	//instructionCOntainer
	
	JPanel UpRightPanel = new JPanel();
	
	JPanel UpPanel = new JPanel(new GridLayout(2,0));
	
	JPanel iMidPanel = new JPanel();
	JTextField iInstruct = new JTextField(20);
	
	JPanel iBotPanel = new JPanel(new GridLayout(0,3));
	JButton iAddBttn = new JButton("Add");
	JButton iClearBttn = new JButton("Remove");
	JButton iEditBttn = new JButton("Edit");
	
	//identificationButton content
	
	JPanel identificationContainer = new JPanel();
	JPanel iRightPane = new JPanel();
	
	JPanel iButton = new JPanel(new GridLayout(12,1));
	JButton one = new JButton("Add");
	JButton two = new JButton("Add");
	JButton three = new JButton("Add");
	JButton four = new JButton("Add");
	JButton five = new JButton("Add");
	JButton six = new JButton("Add");
	JButton seven = new JButton("Add");
	JButton eight = new JButton("Add");
	JButton nine = new JButton("Add");
	JButton ten = new JButton("Add");
	
	String list[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	int incr = 1;
	JComboBox iList;
	JButton edit = new JButton("Edit");
	JButton delete = new JButton("Delete");
	JButton mcDelete = new JButton("Delete");
	JButton save = new JButton("Save");
	JButton clear = new JButton("Clear");
	JButton viewBttn = new JButton("View");
	JButton mcViewBttn = new JButton("View");
	
	JPanel iText = new JPanel(new GridLayout(12,1));
	JTextField f1 = new JTextField(22);
	JTextField f2 = new JTextField(22);
	JTextField f3 = new JTextField(22);
	JTextField f4 = new JTextField(22);
	JTextField f5 = new JTextField(22);
	JTextField f6 = new JTextField(22);
	JTextField f7 = new JTextField(22);
	JTextField f8 = new JTextField(22);
	JTextField f9 = new JTextField(22);
	JTextField f10 = new JTextField(22);
	
	JPanel itemsPanel = new JPanel(new GridLayout(12,1,0,0));
	String iString[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	JComboBox iItems1;
	JComboBox iItems2;
	JComboBox iItems3;
	JComboBox iItems4;
	JComboBox iItems5;
	JComboBox iItems6;
	JComboBox iItems7;
	JComboBox iItems8;
	JComboBox iItems9;
	JComboBox iItems10;
	
	//MultipleChoicePanel
	
	JPanel mcPanel = new JPanel(new GridLayout(12,1,0,0));
	
	JButton mc1 = new JButton("Add Choices");
	JButton mc2 = new JButton("Add Choice");
	JButton mc3 = new JButton("Add Choice");
	JButton mc4 = new JButton("Add Choice");
	JButton mc5 = new JButton("Add Choice");
	JButton mc6 = new JButton("Add Choice");
	JButton mc7 = new JButton("Add Choice");
	JButton mc8 = new JButton("Add Choice");
	JButton mc9 = new JButton("Add Choice");
	JButton mc10 = new JButton("Add Choice");
	
	//essayWritingPanel
	
	JPanel esPanel = new JPanel(new GridLayout(12,1,0,0));
	
	JButton es1 = new JButton("Limit words");
	JButton es2 = new JButton("Limit words");
	JButton es3 = new JButton("Limit words");
	JButton es4 = new JButton("Limit words");
	JButton es5 = new JButton("Limit words");
	JButton es6 = new JButton("Limit words");
	JButton es7 = new JButton("Limit words");
	JButton es8 = new JButton("Limit words");
	JButton es9 = new JButton("Limit words");
	JButton es10 = new JButton("Limit words");
	JButton esDelete = new JButton("Delete");
	JButton esView = new JButton("View");
	
	JPanel statusPanel = new JPanel(new GridLayout(9,2,40,5));
	JLabel activeBar = new JLabel("Active Questionnaire Type:");
	JLabel active = new JLabel("None");
	JLabel itemLbl = new JLabel("Total No. of Items :");
	JLabel itemNo = new JLabel("None");
	JLabel profName = new JLabel("Professors Name:");
	JLabel name = new JLabel("None");
	JLabel timeIn = new JLabel("Time Update:");
	JLabel time = new JLabel("None");
	JLabel dateIn = new JLabel("Date:");
	JLabel dateToday = new JLabel("");
	JLabel idLbl = new JLabel("Identification items: ");
	JLabel idOk = new JLabel("None");
	JLabel mcLbl = new JLabel("Multiple Choice items: ");
	JLabel mcOk = new JLabel("None");
	JLabel enLbl = new JLabel("Enumeration items: ");
	JLabel enOk = new JLabel("None");
	JLabel esLbl = new JLabel("Essay Writing items: ");
	JLabel esOk = new JLabel("None");
	
	public void launchFrame()
	{
		f.setJMenuBar(menu);
			menu.add(mainMenu);
				mainMenu.add(newMenuItem);
					newMenuItem.addActionListener(this);
				mainMenu.add(openMenuItem);
					openMenuItem.addActionListener(this);
				mainMenu.add(saveMenuItem	);
					saveMenuItem.addActionListener(this);
				mainMenu.add(closeMenuItem);
					closeMenuItem.addActionListener(this);
				mainMenu.add(exitMenuItem);
					exitMenuItem.addActionListener(this);
			menu.add(registerMenu);
				registerMenu.add(accountInfoMenuItem);
					accountInfoMenuItem.addActionListener(this);
				registerMenu.add(resetMenuItem);
					resetMenuItem.addActionListener(this);
			menu.add(createMenu);
				createMenu.add(recordMenu);
					recordMenu.add(createRecord);
					recordMenu.add(openClassRecord);
						createRecord.addActionListener(this);
						openClassRecord.addActionListener(this);
				createMenu.add(answerMenuItem);
					answerMenuItem.addActionListener(this);
			menu.add(helpMenu);
			helpMenu.add(instructionsMenuItem);
					instructionsMenuItem.addActionListener(this);
				helpMenu.add(authorNoteMenuItem);
					authorNoteMenuItem.addActionListener(this);
				helpMenu.add(purchaseMenuItem);
					purchaseMenuItem.addActionListener(this);
				helpMenu.add(versionMenuItem);
					versionMenuItem.addActionListener(this);
					
		//adds buttons
		buttonContainer.add(identificationButton);
		buttonContainer.add(multipleChoiceButton);
		buttonContainer.add(enumerationButton);
		buttonContainer.add(essayWritingButton);
		
					identificationButton.addActionListener(this);
					multipleChoiceButton.addActionListener(this);
					enumerationButton.addActionListener(this);
					essayWritingButton.addActionListener(this);
		
		buttonContainer.setVisible(false);
		
		viewContainer.setBorder(BorderFactory.createTitledBorder(null, "View",TitledBorder.TOP,TitledBorder.CENTER));
		pane = new JScrollPane (area);
		viewContainer.add(s);
		viewContainer.add(pane);
		area.setEditable(false);
		
		viewContainer.setVisible(false);
		
		iMidPanel.add(iInstruct);
		
		iBotPanel.add(iAddBttn);
			iAddBttn.addActionListener(this);
		iBotPanel.add(iClearBttn);
			iClearBttn.addActionListener(this);
		iBotPanel.add(iEditBttn);
			iEditBttn.addActionListener(this);
			
		UpPanel.add(iMidPanel);
		UpPanel.add(iBotPanel);
		UpPanel.setBorder(BorderFactory.createTitledBorder(null, "Instruction",TitledBorder.TOP,TitledBorder.CENTER));
		
		UpRightPanel.add(UpPanel,BorderLayout.CENTER);
		UpRightPanel.setVisible(false);
		
		//identificationbutton
		
		iButton.add(one);
			one.addActionListener(this);
		iButton.add(two);
			two.addActionListener(this);
		iButton.add(three);
			three.addActionListener(this);
		iButton.add(four);
			four.addActionListener(this);
		iButton.add(five);
			five.addActionListener(this);
		iButton.add(six);
			six.addActionListener(this);
		iButton.add(seven);
			seven.addActionListener(this);
		iButton.add(eight);
			eight.addActionListener(this);
		iButton.add(nine);
			nine.addActionListener(this);
		iButton.add(ten);
			ten.addActionListener(this);
			
		while(incr<=20)
		{
			x = incr;
			list[incr-1] = Integer.toString(x);
			incr++;
		}
		
		iList = new JComboBox(list);	
			iList.setToolTipText("Each number corresponds to each line.");
		iButton.add(iList);
		iButton.add(save);
			save.addActionListener(this);
			
		
		iText.add(f1);
		iText.add(f2);
		iText.add(f3);
		iText.add(f4);
		iText.add(f5);
		iText.add(f6);
		iText.add(f7);
		iText.add(f8);
		iText.add(f9);
		iText.add(f10);
		iText.add(edit);
			edit.addActionListener(this);
		iText.add(clear);
			clear.addActionListener(this);

		int start = 1;
		
		while(start<=100)
		{
			iString[start-1] = Integer.toString(start);
			start++;
		}
		iItems1 = new JComboBox(iString);	
			itemsPanel.add(iItems1);
		iItems2 = new JComboBox(iString);
			itemsPanel.add(iItems2);
		iItems3 = new JComboBox(iString);
			itemsPanel.add(iItems3);
		iItems4 = new JComboBox(iString);
			itemsPanel.add(iItems4);
		iItems5 = new JComboBox(iString);
			itemsPanel.add(iItems5);
		iItems6 = new JComboBox(iString);
			itemsPanel.add(iItems6);
		iItems7 = new JComboBox(iString);
			itemsPanel.add(iItems7);
		iItems8 = new JComboBox(iString);
			itemsPanel.add(iItems8);
		iItems9 = new JComboBox(iString);
			itemsPanel.add(iItems9);
		iItems10 = new JComboBox(iString);
			itemsPanel.add(iItems10);
			
		itemsPanel.add(delete);
			delete.addActionListener(this);
		itemsPanel.add(viewBttn);
			viewBttn.addActionListener(this);
				viewBttn.setToolTipText("view the saved temporary data");
			
		mcPanel.add(mc1);
			mc1.addActionListener(this);
		mcPanel.add(mc2);
			mc2.addActionListener(this);
		mcPanel.add(mc3);
			mc3.addActionListener(this);
		mcPanel.add(mc4);
			mc4.addActionListener(this);
		mcPanel.add(mc5);
			mc5.addActionListener(this);
		mcPanel.add(mc6);
			mc6.addActionListener(this);
		mcPanel.add(mc7);
			mc7.addActionListener(this);
		mcPanel.add(mc8);
			mc8.addActionListener(this);
		mcPanel.add(mc9);
			mc9.addActionListener(this);
		mcPanel.add(mc10);
			mc10.addActionListener(this);
		mcPanel.add(mcDelete);
			mcDelete.addActionListener(this);
		mcPanel.add(mcViewBttn);
			mcViewBttn.addActionListener(this);
			
		esPanel.add(es1);
			es1.addActionListener(this);
		esPanel.add(es2);
			es2.addActionListener(this);
		esPanel.add(es3);
			es3.addActionListener(this);
		esPanel.add(es4);
			es4.addActionListener(this);
		esPanel.add(es5);
			es5.addActionListener(this);
		esPanel.add(es6);
			es6.addActionListener(this);
		esPanel.add(es7);
			es7.addActionListener(this);
		esPanel.add(es8);
			es8.addActionListener(this);
		esPanel.add(es9);
			es9.addActionListener(this);
		esPanel.add(es10);
			es10.addActionListener(this);
		esPanel.add(esDelete);
			esDelete.addActionListener(this);
		esPanel.add(esView);
			esView.addActionListener(this);
			
		iRightPane.add(iButton);
		iRightPane.add(iText);
		
		iRightPane.setBorder(BorderFactory.createTitledBorder(null, "Question",TitledBorder.TOP,TitledBorder.CENTER));
		
		setAllText();
		statusPanel.setBorder(BorderFactory.createTitledBorder(null, "Status",TitledBorder.TOP,TitledBorder.CENTER));
		statusPanel.add(activeBar);
		statusPanel.add(active);
		statusPanel.add(itemLbl);
		statusPanel.add(itemNo);
		statusPanel.add(profName);
		statusPanel.add(name);
		statusPanel.add(timeIn);
		statusPanel.add(time);
		statusPanel.add(dateIn);
		statusPanel.add(dateToday);
		statusPanel.add(idLbl);
		statusPanel.add(idOk);
		statusPanel.add(mcLbl);
		statusPanel.add(mcOk);
		statusPanel.add(enLbl);
		statusPanel.add(enOk);
		statusPanel.add(esLbl);
		statusPanel.add(esOk);
		statusPanel.setVisible(false);
		
		identificationContainer.add(iRightPane);
		identificationContainer.setVisible(false);
		
		eastPane.setBorder(BorderFactory.createTitledBorder(null, "Test Creator",TitledBorder.TOP,TitledBorder.CENTER));
		eastPane.add(UpRightPanel);
		eastPane.add(identificationContainer);
		eastPane.add(statusPanel,BorderLayout.SOUTH);
		eastPane.setVisible(false);
		
		panel.add(viewContainer);
		panel.add(eastPane);
		
		contentpane = f.getContentPane();
		contentpane.add(buttonContainer,BorderLayout.NORTH);
		contentpane.add(panel,BorderLayout.CENTER);
		f.addWindowListener(new WindowListener()
		{
			public void windowOpened(WindowEvent e){}
			public void windowClosing(WindowEvent e){}
			public void windowDeactivated(WindowEvent e){}
			public void windowIconified(WindowEvent e){}
			public void windowDeiconified(WindowEvent e){}
			public void windowActivated(WindowEvent e)
			{
				timer.start();
			}
			public void windowClosed(WindowEvent e){}
		});
		
		f.setBackground(Color.BLACK);
		f.setIconImage(new ImageIcon("logo.png").getImage());
		f.setSize(1000,1000);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{ 
	
		if (e.getSource() == timer)
		{
            setAllText();
		}
		ctr = fileEnd;
		//start of menu item for menu
		
		if( e.getSource() == newMenuItem )
		{
			buttonContainer.setVisible(true);
		}
		
		if( e.getSource() == openMenuItem )
		{
			openFile();
		}
		
		if( e.getSource() == saveMenuItem )
		{
			saveFile(); 
		}
		
		if( e.getSource() == closeMenuItem )
		{
			eastPane.setVisible(false);
			buttonContainer.setVisible(false);
			identificationContainer.setVisible(false);
			UpRightPanel.setVisible(false);
			viewContainer.setVisible(false);
			statusPanel.setVisible(false);
			
			identificationButton.setEnabled(true);
			essayWritingButton.setEnabled(true);
			enumerationButton.setEnabled(true);
			multipleChoiceButton.setEnabled(true);
		}
		
		if( e.getSource() == exitMenuItem )
		{
			System.exit(0);
		}
		
		// end of menu item for menu
		
		//start of menu item for registerMenu
		
		if( e.getSource() == accountInfoMenuItem )
		{
			af.launchFrame();
		}
		
		//end of menu item for registerMenu
		
		//start of menu item for createMenu
		
		if( e.getSource() == openClassRecord )
		{
			RecordTable rt = new RecordTable();
			rt.launchFrame();
		}
		
		if( e.getSource() == createRecord )
		{
			Table_Create rt = new Table_Create();
			rt.launchFrame();
		}
		
		if( e.getSource() == answerMenuItem )
		{
			AnswerCreator ac = new AnswerCreator();
			ac.launchFrame();
		}
		
		//end of menu item for createMenu
		
		//start of menu item for helpMenu
		
		if( e.getSource() == instructionsMenuItem )
		{
			instruction i = new instruction();
			i.launchFrame();
		}
		
		if( e.getSource() == authorNoteMenuItem )
		{
			AuthorReader ar = new AuthorReader();
			ar.launchFrame();
		}
		
		if( e.getSource() == purchaseMenuItem )
		{
			LegalRights lr = new LegalRights();
			lr.launchFrame();
		}
		
		if( e.getSource() == versionMenuItem )
		{
			version v = new version();
			v.launchFrame();
		}
		
		if( e.getSource() == resetMenuItem )
		{
			int CONFIRM = JOptionPane.showConfirmDialog(null,"Warning! All your STQS data stored in Documents will be deleted. Your account will also be removed. Do you still want to continue?","Warning Status",JOptionPane.YES_NO_OPTION );
			
			if( CONFIRM == JOptionPane.YES_OPTION) 
			{
				resetData r = new resetData();
			}
		}
		//end of menu item for helpMenu
		
	
		if( e.getSource() == identificationButton )
		{
			testno = "I. IDENTIFICATION";
			setAllText();
			identificationContainer.setVisible(true);
			viewContainer.setVisible(true);
			UpRightPanel.setVisible(true);
			itemsPanel.setVisible(true);
			mcPanel.setVisible(false);
			esPanel.setVisible(false);
			identificationButton.setEnabled(false);
			essayWritingButton.setEnabled(true);
			enumerationButton.setEnabled(true);
			multipleChoiceButton.setEnabled(true);
			eastPane.setVisible(true);
			clearText();
			option = 1;
			iInstruct.setText("");
			_instruction = "";
			FileContent[1] = "";
			rightToSave = 0;
			filename = "id.temp";
			statusPanel.setVisible(true);
		}
		
		if( e.getSource() == multipleChoiceButton )
		{
			testno = "II. MULTIPLE CHOICE";
			setAllText();
			viewContainer.setVisible(true);
			UpRightPanel.setVisible(true);
			identificationContainer.setVisible(true);
			itemsPanel.setVisible(false);
			mcPanel.setVisible(true);
			esPanel.setVisible(false);
			multipleChoiceButton.setEnabled(false);
			identificationButton.setEnabled(true);
			essayWritingButton.setEnabled(true);
			enumerationButton.setEnabled(true);
			statusPanel.setVisible(true);
			eastPane.setVisible(true);
			clearText();
			option = 2;
			iInstruct.setText("");
			_instruction = "";
			FileContent[1] = "";
			rightToSave = 0;
			filename = "mc.temp";
		}
		
		if( e.getSource() == enumerationButton )
		{
			testno = "III. ENUMERATION";
			setAllText();
			viewContainer.setVisible(true);
			UpRightPanel.setVisible(true);
			identificationContainer.setVisible(true);
			itemsPanel.setVisible(true);
			mcPanel.setVisible(false);
			esPanel.setVisible(false);
			enumerationButton.setEnabled(false);
			identificationButton.setEnabled(true);
			multipleChoiceButton.setEnabled(true);
			essayWritingButton.setEnabled(true);
			statusPanel.setVisible(true);
			eastPane.setVisible(true);
			clearText();
			option = 3;
			iInstruct.setText("");
			_instruction = "";
			FileContent[1] = "";
			rightToSave = 0;
			filename = "en.temp";
		}
		
		if( e.getSource() == essayWritingButton )
		{
			testno = "IV. ESSAY WRITING";
			setAllText();
			viewContainer.setVisible(true);
			UpRightPanel.setVisible(true);
			identificationContainer.setVisible(true);
			itemsPanel.setVisible(false);
			mcPanel.setVisible(false);
			esPanel.setVisible(true);
			essayWritingButton.setEnabled(false);
			identificationButton.setEnabled(true);
			multipleChoiceButton.setEnabled(true);
			enumerationButton.setEnabled(true);
			statusPanel.setVisible(true);
			eastPane.setVisible(true);
			clearText();
			option = 4;
			iInstruct.setText("");
			_instruction = "";
			FileContent[1] = "";
			rightToSave = 0;
			filename = "es.temp";
		}
		
		
		// - - - - - - - - - - - -  -
		
		//identificationButton
		int tempInt = 0;
		String tempString = "";
		
		if( e.getSource() == one )
		{
			FileContent[ctr] = f1.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems1.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == two )
		{
			FileContent[ctr] = f2.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems2.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == three )
		{
			FileContent[ctr] = f3.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems3.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		
		}
		if( e.getSource() == four )
		{
			FileContent[ctr] = f4.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems4.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == five )
		{
			FileContent[ctr] = f5.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
				
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems5.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == six )
		{
			FileContent[ctr] = f6.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
				
			}
			else
			if((option ==1)||(option ==3))	
			{
				tempString = iItems6.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}	
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == seven )
		{
			FileContent[ctr] = f7.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
				
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems7.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == eight )
		{
			FileContent[ctr] = f8.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
				
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems8.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == nine )
		{
			FileContent[ctr] = f9.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
				
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems9.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if( e.getSource() == ten )
		{
			FileContent[ctr] = f10.getText();
			if(option == 2)
			{
				ctr++;
				FileContent[ctr] += w.readChoices();
				ctr++;
				
			}
			else
			if((option ==1)||(option ==3))
			{
				tempString = iItems10.getSelectedItem().toString();
				tempInt = Integer.parseInt(tempString);
				ctr+=tempInt;
			}
			else
			{
				FileContent[x] += esLimit;
				ctr++;
			}
		}
		
		if(ctr>fileEnd)
		{
			fileEnd += (ctr-fileEnd);
		}
		
		// - - - - - - - - - - - - -
		
		//multiple Choice buttons
		
		if (e.getSource() == mc1)
		{
			 t.mcChoice();
			 one.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				one.setEnabled(false);
			}
			else
			 one.setEnabled(true);
		}
		
		if (e.getSource() == mc2)
		{
			t.mcChoice();
			two.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				two.setEnabled(false);
			}
			else
			 two.setEnabled(true);
		}
		
		if (e.getSource() == mc3)
		{
			t.mcChoice();
			three.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				three.setEnabled(false);
			}
			else
			 three.setEnabled(true);
		}
		
		if (e.getSource() == mc4)
		{
			t.mcChoice();
			four.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				four.setEnabled(false);
			}
			else
			four.setEnabled(true);
		}
		
		if (e.getSource() == mc5)
		{
			t.mcChoice();
			five.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				five.setEnabled(false);
			}
			else
			 five.setEnabled(true);
		}
		
		if (e.getSource() == mc6)
		{
			t.mcChoice();
			six.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				six.setEnabled(false);
			}
			else
			 six.setEnabled(true);
		}
		
		if (e.getSource() == mc7)
		{
			t.mcChoice();
			seven.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				seven.setEnabled(false);
			}
			else
			 seven.setEnabled(true);
		}
		
		if (e.getSource() == mc8)
		{
			t.mcChoice();
			eight.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				eight.setEnabled(false);
			}
			else
			 eight.setEnabled(true);
		}
		
		if (e.getSource() == mc9)
		{
			t.mcChoice();
			nine.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				nine.setEnabled(false);
			}
			else
				nine.setEnabled(true);
		}
		
		if (e.getSource() == mc10)
		{
			t.mcChoice();
			ten.setEnabled(true);
		}
		else
		{
			if(option == 2)
			{
				ten.setEnabled(false);
			}
			else
				ten.setEnabled(true);
		}
		
		if( e.getSource() == es1 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			one.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				one.setEnabled(false);
			}
		}
		
		if( e.getSource() == es2 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			two.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				two.setEnabled(false);
			}
		}
		
		if( e.getSource() == es3 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			three.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				three.setEnabled(false);
			}
		}
		
		if( e.getSource() == es4 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			four.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				four.setEnabled(false);
			}
		}
		
		if( e.getSource() == es5 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			five.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				five.setEnabled(false);
			}
		}
		
		if( e.getSource() == es6 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			six.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				six.setEnabled(false);
			}
		}
		
		if( e.getSource() == es7 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			seven.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				seven.setEnabled(false);
			}
		}
		
		if( e.getSource() == es8 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			eight.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				eight.setEnabled(false);
			}
		}
		
		if( e.getSource() == es9 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			nine.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				nine.setEnabled(false);
			}
		}
		
		if( e.getSource() == es10 )
		{
			esLimit = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
			ten.setEnabled(true);
		}
		else
		{
			if(option == 4)
			{
				ten.setEnabled(false);
			}
		}
		
		// - - - - - - - - - - - -  -
		
		//instruction buttons
		
		if( e.getSource() == iAddBttn )
		{
			
			instruction = iInstruct.getText();
			_instruction = "INSTRUCTION: " + instruction;
			rightToSave = 1;
		}
		
		if( e.getSource() == iEditBttn )
		{
			iInstruct.setText(instruction);
		}
		
		if( e.getSource() == iClearBttn )
		{
			iInstruct.setText("");
			_instruction = "";
			FileContent[1] = "";
			rightToSave = 0;
		}
		
		// - - - - end of instruction buttons - - -  -
			
		if( e.getSource() == save )
		{
			transferFileContent();
			s.setText("[ Saved ]");
			saveMethod();
			save.setEnabled(false);
		}
		else
		{
			if(rightToSave == 0 )
			{
				save.setEnabled(false);
				save.setToolTipText("To save data , add first the instruction");
			}
			else
				save.setEnabled(true);
				s.setText("[ Not yet Saved ]");	
		}
		
		if( e.getSource() == delete )
		{
			deleteOption();
		}
		
		if( e.getSource() == mcDelete )
		{
			deleteOption();
		}
		
		if( e.getSource() == esDelete )
		{
			deleteOption();
		}
		
		
		
		if(e.getSource() == edit )
		{
			editOption();
		}
		 
		if( e.getSource() == viewBttn)
		{
			viewBttn.setEnabled(false);
			area.setText("");
			viewFile();
		}
		else
		{
			viewBttn.setEnabled(true);
		}
		
		if( e.getSource() == mcViewBttn)
		{
			mcViewBttn.setEnabled(false);
			area.setText("");
			viewFile();
		}
		else
		{
			mcViewBttn.setEnabled(true);
		}
		
		if( e.getSource() == esView)
		{
			esView.setEnabled(false);
			area.setText("");
			viewFile();
		}
		else
		{
			esView.setEnabled(true);
		}
		
		if( e.getSource() == clear )
		{
			clearText();	
		}
		
		transferFileContent();
		setDisplay();
	}
	
	//end of actionListener
	
	public void clearText()  // clears only the textfield
	{
		f1.setText("");
		f2.setText("");
		f3.setText("");
		f4.setText("");
		f5.setText("");
		f6.setText("");
		f7.setText("");
		f8.setText("");
		f9.setText("");
		f10.setText("");
		
		for( int x = 0 ; x < fileEnd ; x++ )
			FileContent[x] = "";
		fileEnd = 2;
		ctr = 2;
	}
	
	//transfers file content from variable FileContent and add it to the appropriate option
	
	public void transferFileContent()
	{
		if( option == 1)
		{
						
			idFileContent[0] = testno;
			idFileContent[1] = _instruction;
			
			for( x = 2; x < fileEnd ; x++ )
				idFileContent[x] = FileContent[x];
				idFileEnd = fileEnd;
		}
		
		if( option == 2)
		{
						
			mcFileContent[0] = testno;
			mcFileContent[1] = _instruction;
			
			for( x = 2 ; x < fileEnd ; x++ )
			{
				mcFileContent[x] = FileContent[x];
			}
				mcFileEnd = fileEnd;
		}
		
		if( option == 3)
		{
						
			enFileContent[0] = testno;
			enFileContent[1] = _instruction;
			
			for( x = 2 ; x < fileEnd ; x++ )
				enFileContent[x] = FileContent[x];
				enFileEnd = fileEnd;
		}
		
		if( option == 4)
		{
						
			esFileContent[0] = testno;
			esFileContent[1] = _instruction;
			
			for( x = 2 ; x < fileEnd ; x++ )
				esFileContent[x] = FileContent[x];
				esFileEnd = fileEnd;
		}
		
	}
	
	//end of transferFileContent
	
	public void setDisplay()  //sets the display
	{
		transferFileContent(); 
		
		no = 1;
		
		if( option == 1 )
		{
			iRightPane.add(itemsPanel);

			display = "";
			
			display += idFileContent[0] + NEWLINE;
			display += idFileContent[1] + NEWLINE;
			
			for( ctr = 2 + viewBttnIsUsed  ; ctr < idFileEnd; ctr++ )
			{
				if(idFileContent[ctr].equals(""))
				{
					idFileContent[ctr] = "-";
					display += idFileContent[ctr];
					display += NEWLINE;
				}
				else
				if(idFileContent[ctr].equals(END_OF_ITEMS))
				{
					display += idFileContent[ctr];
					display += NEWLINE;
				}
				else
				{
					display += no +"."+ idFileContent[ctr];
					display += NEWLINE;
				}
				no++;
			}
			
		}
		
		if( option == 2 )
		{
			iRightPane.add(mcPanel);
		
			no = 1;
			int nmbr = 0;
			
			display = "";
			
			display += mcFileContent[0] + NEWLINE;
			display += mcFileContent[1] + NEWLINE;
			
			for( ctr = 2 ; ctr < mcFileEnd; ctr++ )
			{	
				if(mcFileContent[ctr].equals(END_OF_ITEMS))
				{
					display += mcFileContent[ctr];
					display += NEWLINE;
					break;
				}
				else
				{
					display += no +"."+ mcFileContent[ctr];
					display += NEWLINE;
					ctr++;
					nmbr = w.extractNumber(mcFileContent[ctr]);
					String temp[] = mcFileContent[ctr].split(",");
					char choice_letter = 'a';
					for( int y = 1 ; y <= nmbr ; y++ )
					{
						display +=  choice_letter + "." + temp[y];
						display += NEWLINE;
						choice_letter++;
					}
					no++;
				}
			}
		}
		
		
		if( option == 3 )
		{
			iRightPane.add(itemsPanel);
			
			display = "";
			
			display += enFileContent[0] + NEWLINE;
			display += enFileContent[1] + NEWLINE;
			
			for( ctr = 2 ; ctr < enFileEnd - viewBttnIsUsed ; ctr++ )
			{
				if(enFileContent[ctr].equals(END_OF_ITEMS))
				{
					display += enFileContent[ctr];
					display += NEWLINE;
					break;
				}
				else
				{
					if(enFileContent[ctr].equals(""))
					{
						enFileContent[ctr] = "-";
						display += enFileContent[ctr];
						display += NEWLINE;
					}
					else
					{
						display += no +"."+ enFileContent[ctr];
						display += NEWLINE;
					}
					no++;
				}
				
			}
		}
		
		if( option == 4 )
		{
			iRightPane.add(esPanel);
			
			display = "";
			
			display += esFileContent[0] + NEWLINE;
			display += esFileContent[1] + NEWLINE;
			
			for( ctr = 2 ; ctr < esFileEnd - viewBttnIsUsed ; ctr++ )
			{
				if(esFileContent[ctr].equals(END_OF_ITEMS))
				{
					display += esFileContent[ctr];
					display += NEWLINE;
					break;
				}
				else
				{
					if(esFileContent[ctr].equals(""))
					{
						esFileContent[ctr] = "-";
						display += esFileContent[ctr];
						display += NEWLINE;
					}
					else
					{
						display += no +"."+ esFileContent[ctr];
						display += NEWLINE;
					}
					no++;
				}

				
			}
		}
		
		viewBttnIsUsed = 0;  //viewBttn is not used
		area.setText(display);
	}
	
	
	public void viewFile()
	{
		File f = new File(filename);
		if(f.exists())
		{
			if(option == 1)
			{
				fileEnd = w.readFile(filename);
				for( x = 0 ; x < fileEnd ; x++ )   //gives all data from program to an array in the  write class
				{
					FileContent[x] = w.viewFile();
				}
		
				_instruction = FileContent[1];
		
				/*for( x = 1 ; x<fileEnd ; x++ )
				{
					FileContent[x] = FileContent[x+2];
				}
		
				fileEnd -= 2 ;*/
			}
			
			if(option == 2)
			{
				int nmbr = 0;
				int y = 0;
				int end = 0;
				int arry = 2;
				
				fileEnd = w.readFile(filename);
				for( x = 0 ; x < fileEnd ; x++ )   //gives all data from program to an array in the  write class
				{
					FileContent[x] = w.viewFile();
				}
		
				_instruction = FileContent[1];
	
			}
			
			if(option ==3)
			{
				fileEnd = w.readFile(filename);
				for( x = 0 ; x < fileEnd ; x++ )   //gives all data from program to an array in the  write class
				{
					FileContent[x] = w.viewFile();
				}
		
				_instruction = FileContent[1];
			
				/*for( x = 1 ; x<fileEnd ; x++ )
				{
					
					FileContent[x] = FileContent[x+2];
				}
		
				fileEnd -= 2;*/
			}
			
			if(option ==4)
			{
				fileEnd = w.readFile(filename);
				for( x = 0 ; x < fileEnd ; x++ )   //gives all data from program to an array in the  write class
				{
					FileContent[x] = w.viewFile();
				}
		
				_instruction = FileContent[1];
			
				/*for( x = 1 ; x<fileEnd ; x++ )
				{
					FileContent[x] = FileContent[x+2];
				}
		
				fileEnd -= 2;*/
			}
		}
		else
			JOptionPane.showMessageDialog(null,"Save first all the necessary items!");
	}
	
	public void saveFile()
	{
		SaveGUI savegui = new SaveGUI();
		savegui.saveMethod();
	}

	public void openFile()
	{
		Frame_For_Open ffo = new Frame_For_Open();
		ffo.launchFrame();
	}
	
	public void editOption()
	{
		String tempVar = "";
		int tempInt = 0;
		int tempEnd;
		String tempString = "";
		String show = "";
		int nmbr = 0;
		int temp = 0;
					tempString = iList.getSelectedItem().toString();  //gets the value from iList
					tempInt = Integer.parseInt(tempString) + 1;
					
					if(( tempInt < 1 ) || ( tempInt >= fileEnd-1 ) )  //checks if the value you choose is in the range. If in the range, "else" statement occurs
					{
						if(fileEnd == 2)  //no data written
						{
							JOptionPane.showMessageDialog(null,"Create first your data then save it","",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							tempString = "( 1 - " + (fileEnd - 3) + ")";
							JOptionPane.showMessageDialog(null,"Youre chosen number is not in range!! "+tempString,"",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						if(option == 1)
						{
							FileContent[tempInt] = (String)JOptionPane.showInputDialog("Enter new question:");
						}
						
						if(option == 2)
						{
							try
							{
								FileContent[tempInt] = (String)JOptionPane.showInputDialog("Enter new question:",""+FileContent[tempInt]);
								t.launchFrame();
								transferFileContent();
								setDisplay();
							}
							
							catch(Exception e){}
						}
						
						if( option == 3)
						{
							if(FileContent[tempInt].equals(""))
							{
								JOptionPane.showMessageDialog(null,"Choose only an item number with question!","Error Status",JOptionPane.ERROR_MESSAGE);
							}
							else
							{

								while(true)
								{								
									try
									{
										FileContent[tempInt] = (String)JOptionPane.showInputDialog("Enter new question:");
									}
									catch(NumberFormatException e)
									{
										break;
									}
									
									String nmbrString = ""; 
									
									try
									{	
										nmbrString = JOptionPane.showInputDialog("Enter how many items:");
										try
										{
											nmbr = Integer.parseInt(nmbrString);
											if((nmbr+fileEnd>100)||(nmbr<1))
											{
												JOptionPane.showMessageDialog(null,"Number not in range!!","Error Status",JOptionPane.ERROR_MESSAGE);											
											}
											else
												break;
										}
										catch(NumberFormatException e)
										{
											JOptionPane.showMessageDialog(null,"Enter a valid number!!","Error Status",JOptionPane.ERROR_MESSAGE);										
										}
									}
									catch(Exception e)
									{
										break;
									}
								}

								for(int x = fileEnd+nmbr;x>tempInt;x--)
								{
									if(x>tempInt+nmbr)
									{
										FileContent[x] = FileContent[x-nmbr];
									}
									else
									{
										FileContent[x+nmbr] = "";
									}
								}
								
								fileEnd+=nmbr;
							}
						}
						
						if(option ==4)
						{
							String first_part = "";
							String second_part = "";
							first_part = (String)JOptionPane.showInputDialog("Enter new question:");
							second_part = "[ " + JOptionPane.showInputDialog("Maximum number of words:") + " ]";
							FileContent[tempInt] = first_part + second_part; 
						}
					}
	}
	
	public void deleteOption()
	{
		int tempInt = 0;
		String tempString = "";
		int tempEnd = 0;
		int nmbr = 0;
			while( ( tempInt < 1 ) || ( tempInt >= fileEnd - 2 ) )  //performs the loop while the number doesnt corresponds to iList requirements. prevents user from using number out of range
			{
					tempString = iList.getSelectedItem().toString();  
					tempInt = Integer.parseInt(tempString) + 1; //adds 1 because the number 1 in textarea starts in FileContent[2]. therefore 1 in IList corresponds to 2 in text area
					tempEnd = tempInt;
					if( ( tempInt < 1 ) || ( tempInt >= fileEnd ) )
					{
						if( ( fileEnd - 2 ) == 0)
						{
							tempString = "( 0 - " + ( fileEnd - 2 ) + ")";
						}
						else
							tempString = "( 1 - " + ( fileEnd - 2 ) + ")";
						
							JOptionPane.showMessageDialog(null,"Youre chosen number is not in range!! "+tempString,"",JOptionPane.ERROR_MESSAGE);
							
						break;
					}
					else
					{
						
						if(option == 2)
						{
							int q = 1;
							int y = 0;
							for(x = 2;x<=fileEnd;x++)
							{
								nmbr = w.extractNumber(FileContent[x]);
								if(tempInt == x)
								{
									for( tempInt = tempInt ; tempInt <= fileEnd-2 ; tempInt++ )
										FileContent[tempInt] = FileContent[tempInt+nmbr+1];
									fileEnd = fileEnd - nmbr - 2;
									break;
								}
								
								for(y = 1;y<=nmbr;y++)
								{
									if(tempInt == (x+y))
									{	
										String s = FileContent[x];
										String[] temp = s.split("[");
										FileContent[x] = temp[0];
										FileContent[x] += "[ "+(nmbr-1)+" ]";
										for( tempInt = tempInt ; tempInt <= fileEnd-2 ; tempInt++ )
											FileContent[tempInt] = FileContent[tempInt+1];
										break;
									}
									
									
								}
								x+=nmbr+1;
								if(tempInt == x)
								{
									JOptionPane.showMessageDialog(null,"Sorry , this line dedicated to blank space. You cannot edit it ","ERROR",JOptionPane.ERROR_MESSAGE);
									break;
								}
								q++;
							}
								
						}
						else
						{
							for( tempInt = tempInt ; tempInt <= fileEnd-2 ; tempInt++ )
							FileContent[tempInt] = FileContent[tempInt+1];
						
							fileEnd--;  //removes 1 from end of the filecontent
							setDisplay();
							JOptionPane.showMessageDialog(null,"Item Deleted!","",JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						
					}
						
			}
	}
	
	public void saveMethod()
	{
		if( option == 1 )
			{
				for(x = 0;x<fileEnd;x++)
				{
					String fileContent = idFileContent[x];
					w.arrayString(fileContent);
				}
		
			}
			
			if( option == 2 )
			{
				for(x = 0;x<fileEnd;x++)
				{
					String fileContent = mcFileContent[x];
					w.arrayString(fileContent);
				}
				
			}
			
			if( option == 3 )
			{
				for(x = 0;x<fileEnd;x++)
				{
					String fileContent = enFileContent[x];
					w.arrayString(fileContent);
				}
			}	
			
			if( option == 4 )
			{
				for(x = 0;x<fileEnd;x++)
				{
					String fileContent = esFileContent[x];
					w.arrayString(fileContent);
				}
			}
			w.writeToFile(filename);
			JOptionPane.showMessageDialog(null,"Work Progress Saved . . ","",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void setAllText()
	{
		active.setText(testno);
		af.readFile();
		if(option == 2)
			if(fileEnd == 2)	
			itemNo.setText("0");
			else
			itemNo.setText(""+(fileEnd-4)); 
		else	
		itemNo.setText(""+(fileEnd-2)); 
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dateToday.setText(dateFormat.format(date));
		name.setText(af.nameField.getText());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		time.setText( sdf.format(cal.getTime()) );
		time.setToolTipText("Time updates everytime this Window is active");
		File id = new File("id.temp");
		File mc = new File("mc.temp");
		File en = new File("en.temp");
		File es = new File("es.temp");
		if(id.exists())
		{
			idOk.setText("PRESENT");
		}
		else
			idOk.setText("NOT PRESENT");	
		
		if(mc.exists())
		{
			mcOk.setText("PRESENT");
		}
		else
			mcOk.setText("NOT PRESENT");	
		
		if(en.exists())
		{
			enOk.setText("PRESENT");
		}
		else
			enOk.setText("NOT PRESENT");	
		
		if(es.exists())
		{
			esOk.setText("PRESENT");
		}
		else
			esOk.setText("NOT PRESENT");	
	}
	
	/*public static void main(String args[])
	{
		MainMenu mn = new MainMenu();
		mn.launchFrame();
	}*/
}