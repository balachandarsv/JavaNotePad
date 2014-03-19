import java.awt.*;
import java.awt.event.*;
import java.io.*;
class Notepad implements ActionListener
{
	Frame f;											//Creates a Frame Reference
	MenuBar mb;											//Creates a MenuBar Reference
	Menu file,edit,help;								//Creates Menu References 
	MenuItem newf,open,save,exit,cut,copy,paste,hel;	//Creates Menu Item References 
	TextArea ta;										//Creates Text Area Reference
	FileDialog fd,fd1;									//Creates FileDialog References (i.e) Save and Open Dialog box
	Toolkit toolkit;									//For time being understand that it is used for returning window height and width
	String txtc;										//Used to store the copied text or text that is about to be moved
	Notepad()
	{
		f=new Frame("Notepad--");						//Converts reference to Frame object with Frame Name as Notepad--
		f.addWindowListener(new WindowAdapter(){ public void windowClosing(WindowEvent we) {	
                System.exit(0);
            }});										//Code for exiting the application using adapter classes
		toolkit= Toolkit.getDefaultToolkit();						
		int width=(int)toolkit.getDefaultToolkit().getScreenSize().getWidth()-10;	//width of application
		int height=(int)toolkit.getDefaultToolkit().getScreenSize().getHeight()-10;	//height of application
		f.setSize(width,height);													//setting window sizes
		ta=new TextArea();															//Creating text area
		f.add(ta);																	//Adding textarea to frame
		mb=new MenuBar();															//Creating object for menubar mb		
		file=new Menu("File");														//Creating new menu File
		edit=new Menu("Edit");														//Creating new menu Edit
		help=new Menu("Help");														//Creating new menu Help
		mb.add(file);
		newf=new MenuItem("New");													//Creating MenuItems				
		open=new MenuItem("Open");
		save=new MenuItem("Save");
		exit=new MenuItem("Exit");
		cut=new MenuItem("Cut");
		copy=new MenuItem("Copy");
		paste=new MenuItem("Paste");
		hel=new MenuItem("Help");
		file.add(newf);																//Adding menu items to corresponding menu and adding ActionListeners
		newf.addActionListener(this);
		file.add(open);
		open.addActionListener(this);
		file.add(save);
		save.addActionListener(this);
		file.add("-");
		file.add(exit);
		exit.addActionListener(this);
		edit.add(cut);
		cut.addActionListener(this);
		edit.add(copy);
		copy.addActionListener(this);
		edit.add(paste);
		paste.addActionListener(this);
		help.add(hel);
		hel.addActionListener(this);
		mb.add(edit);
		mb.add(help);
		f.setMenuBar(mb);														//Adding Menubar to Frame
		fd=new FileDialog(f,"Open",FileDialog.LOAD);							//Creating Dialogbox for Open
		fd.setSize(300,300);
		fd1=new FileDialog(f,"Save",FileDialog.SAVE);							//Creating Dialogbox for Save
		fd1.setSize(300,300);
		f.setVisible(true);														//Setting Frame's Visibility to TRUE
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command=String.valueOf(ae.getActionCommand());
		switch(command)
		{
			case "New":	if(!ta.getText().equals(""))						//Adding ActionListener for New Menu Item
						{
							
								try{
									String content=ta.getText();
									fd1.setVisible(true);
									FileOutputStream fout=new FileOutputStream(fd1.getDirectory()+fd1.getFile()+".txt");
									fout.write(content.getBytes());
									ta.setText("");
									}
								catch(Exception e){}
						
						}
			
						break;
			case "Open":if(!ta.getText().equals(""))						//Adding ActionListener for Open Menu Item
						ta.setText("");
						try{
						fd.setVisible(true);
						FileInputStream fin=new FileInputStream(fd.getDirectory()+fd.getFile());
						int b;
						while((b=(byte)fin.read())!=-1)
						{
							ta.append(String.valueOf((char)b));
						}
						}
						catch(Exception e){}
						break;
			case "Save":try{												//Adding ActionListener for SAVE Menu Item
						String content=ta.getText();
						fd1.setVisible(true);
						FileOutputStream fout=new FileOutputStream(fd1.getDirectory()+fd1.getFile()+".txt");
						fout.write(content.getBytes());
						}
						catch(Exception e){}
						break;
			case "Exit":if(!ta.getText().equals(""))						//Adding ActionListener for Exit Menu Item
						{
							
								try{
									String content=ta.getText();
									fd1.setVisible(true);
									FileOutputStream fout=new FileOutputStream(fd1.getDirectory()+fd1.getFile()+".txt");
									fout.write(content.getBytes());
									}
								catch(Exception e){}
						
						}
						else
						{
							System.exit(0);
						}
						break;
			case "Copy":txtc=ta.getSelectedText();									//Adding ActionListener for Copy Menu Item
						break;
			case "Paste":ta.insert(txtc,ta.getCaretPosition());						//Adding ActionListener for Paste Menu Item
						break;
			case "Cut":txtc=ta.getSelectedText();									//Adding ActionListener for Cut Menu Item
						int start=ta.getSelectionStart();
						int end=ta.getSelectionEnd();
						ta.replaceRange("",start,end);
						
						break;
			case "Help":Dialog d=new Dialog(f,"Help");								//Adding ActionListener for Help Menu Item
						d.setSize(500,500);
						d.setLayout(new GridLayout(0,1));
						d.add(new Label("Features: This notepad includes the following features"));
						d.add(new Label(" 1.Menus - File -New Open Save Exit  . "));
						d.add(new Label(" 2. Edit - Cut Copy Paste."));
						d.add(new Label("On opening a file if the contents is not saved it will prompt for saving the file."));
						d.add(new Label("If the cancel button is clicked the contents are lost!"));
						d.add(new Label("Author :S.Balachandar"));
						d.setVisible(true);
						try{
						Thread.sleep(5000);
						}catch(Exception e){}
						d.setVisible(false);
						break;
	}		
	}
	public static void main(String ar[])
	{
		Notepad n=new Notepad();													//Creating an object for the class Notepad
		System.out.println("Author: S.Balachandar");								//Author Credits
	}
}
