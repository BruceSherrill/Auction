package AuctionHome;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import AuctionHome.panelTest;





public class ItemCreation extends JFrame{
	public ItemCreation() {
	}
	
	//Panel
	private JPanel MainPanel = new JPanel();

	//Labels
	private JLabel lblAccountRegistration = new JLabel("Item Creation");
	private JLabel lblItemName = new JLabel("Item Name: ");
	private JLabel lblPicture = new JLabel("Picture:");
	private JLabel lblPrice = new JLabel("starting price");
	private JLabel lblTime = new JLabel("time limit");

	//Text Fields
	private static JTextField txtItemName = new JTextField();
	private static JTextField txtPrice = new JTextField();
	private static JTextField txtTime = new JTextField();

	//Buttons
	private JButton btnRegister = new JButton("Register");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnPicture = new JButton("Browse");

	 private static String itemName;
	 private static String picture;
	 private static String price;
	 private static String time;

	 
		
	private class AddBtnListener implements ActionListener	{
		
		@Override
		public void actionPerformed(ActionEvent e) {


		    if (e.getActionCommand().equals("Register")) 
		    {
				getPhoto();
		       panelTest.listPanel.addPanel(panelTest.getJPanel(), 59);
				setVisible(false);

		    }
		       

	        if (e.getActionCommand().equals("Cancel")) 
	        {
				setVisible(false);
	        }
	        
	        if (e.getActionCommand().equals("Browse")) 
	        {
	        	JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					picture = selectedFile.getAbsolutePath();
					System.out.println(picture);
					//label.setIcon(ResizeImage(path));
				}
				else if(result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No File selected");
					
				}
	        }
		}
		
	}
	
	public static void getPhoto() {
		
		
		
		 itemName = txtItemName.getText();
		 //picture = btnPicture.getText();
		 price = txtPrice.getText();
		 time = txtTime.getText();
		 
		 
		 
			File itemFile = new File("items.txt");

			 if(itemFile.exists()==false)
			    {
			            try {
			            	itemFile.createNewFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    }
			 
			 PrintWriter out1 = null;
				try {
					out1 = new PrintWriter(new FileWriter(itemFile, true));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			    out1.append(getItemName() + "," + getPicture() + "," + getPrice() + "," + getTime());
			    out1.append(System.getProperty("line.separator"));
			    out1.close();

	}
	
    public static void  pullItems()
    {
    	
    	
		File itemsFile = new File("Items.txt");
		
		if(itemsFile.exists()==false)
	    {
	            try {
	            	itemsFile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    }
		
		String line;
    	   Scanner itemScnr = null;
			try {
				itemScnr = new Scanner(itemsFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
			//String[] tokens = str.split(",");

			
		//While loop cycles through user name file and checks to see if the users registered user name
		//is already in the file, if it is it has the user re-enter a new user name
		 while(itemScnr.hasNextLine()) {

			 //Sets what's on the current line of the file equal to the string
			 line = itemScnr.nextLine();
			 	//if users inputed user name equals the one already in the database it turns the user name check false
			 String[] tokens = line.split(",");
			 
			 itemName = tokens[0];
			 picture = tokens[1];
			 price = tokens[2];
			 time = tokens[3];
             panelTest.listPanel.addPanel(panelTest.getJPanel(), 59);

			 Arrays.fill(tokens, null);

				
		 }
    }
	
	public ItemCreation(String title)	{
		super(title);
		
		
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		buildMainFrame();
		addListeners();
		
		setVisible(true);
	}
	
	
	
	
	private void addListeners() {
		btnRegister.addActionListener(new AddBtnListener());
		btnCancel.addActionListener(new AddBtnListener());
		btnPicture.addActionListener(new AddBtnListener());

	}
	
	
	
	
	private void buildMainFrame() {
		MainPanel.setLayout(null);
		
		lblAccountRegistration.setBounds(57, 11, 282, 54);
		lblAccountRegistration.setFont(new Font("Tahoma", Font.PLAIN, 30));
		MainPanel.add(lblAccountRegistration);
		
		lblPicture.setBounds(57, 122, 76, 19);
		lblPicture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPicture);
		
		lblItemName.setBounds(57, 178, 85, 19);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblItemName);
		
		lblPrice.setBounds(57, 239, 85, 19);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPrice);
		
		lblTime.setBounds(66, 303, 76, 19);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblTime);
		
		txtItemName.setBounds(146, 179, 142, 20);
		MainPanel.add(txtItemName);
		
		btnPicture.setBounds(146, 123, 142, 20);
		MainPanel.add(btnPicture);
		
		txtPrice.setBounds(146, 240, 142, 20);
		MainPanel.add(txtPrice);
		
		txtTime.setBounds(146, 304, 142, 21);
		MainPanel.add(txtTime);
		
		btnRegister.setBounds(66, 392, 89, 23);
		MainPanel.add(btnRegister);
		
		btnCancel.setBounds(205, 391, 97, 25);
		MainPanel.add(btnCancel);
		
		
		getContentPane().add(MainPanel);
	}
	
	
	




	public static String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		ItemCreation.itemName = itemName;
	}


	public static String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		ItemCreation.picture = picture;
	}
	
	
	public static String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		ItemCreation.price = price;
	}


	public static String getTime() {
		return time;
	}
	public void setTime(String time) {
		ItemCreation.time = time;
	}




	public static void main(String[] args)
	{
		ItemCreation gui = new ItemCreation("Register");

	}
	
	
	
	


}
