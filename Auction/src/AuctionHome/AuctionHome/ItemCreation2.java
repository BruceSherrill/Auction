package AuctionHome;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import AuctionHome.HomeScreen;


public class ItemCreation2 extends JFrame{
	public ItemCreation2() {
	}
	
	private static Item item = new Item();

	//Panel
	private JPanel MainPanel = new JPanel();

	//Labels
	private JLabel lblItemCreation = new JLabel("Item Creation");
	private JLabel lblItemName = new JLabel("Item Name: ");
	private JLabel lblPicture = new JLabel("Picture:");
	private JLabel lblPrice = new JLabel("starting price:");
	private JLabel lblTime = new JLabel("# of Days:");

	//Text Fields
	private static JTextField txtItemName = new JTextField();
	private static JTextField txtPrice = new JTextField();
	private static JTextField txtTime = new JTextField();

	//Buttons
	private JButton btnRegister = new JButton("Add Item");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnPicture = new JButton("Browse");

	//Strings
	private static String itemName;
	private static String picture;
	static String stringDateEnd;

	
	//Doubles/Integers
	private static Double price;
	private static int time;
	private static int itemCount = 0;
	 
	//ArrayLists
	private static ArrayList<JButton> buttonList = new ArrayList<JButton>();
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	
	//Image Icon for bid button
	static ImageIcon bid = new ImageIcon(HomeScreen.class.getClassLoader().getResource("AuctionHome/Images/BID.png"));
	
	static LocalDate dateEnd;
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY");
	    
	
	
	//Adds button listeners to frame 
	private class AddBtnListener implements ActionListener	
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			//Add Item Button
		    if (e.getActionCommand().equals("Add Item")) 
		    {
		       //Saves Item to csv
		       saveItem();
		       //creates item Panel and displays it
		       HomeScreen.listPanel.addPanel(getJPanel(), 59);
		       //Sets Item creation screen invisible
			   setVisible(false);
		    }
		       
		    
		    //Cancel Button
	        if (e.getActionCommand().equals("Cancel")) 
	        {
			    //Sets Item creation screen invisible
				setVisible(false);
	        }
	        
	        
	        //Browse Button
	        if (e.getActionCommand().equals("Browse")) 
	        {
	        	//Allows user to browse files and select a picture for their item
	        	//and sets a string equal to that file path
	        	JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = file.getSelectedFile();
					picture = selectedFile.getAbsolutePath();
				}
	        }   
		}
		
	}
	
	
	

	
	//Creates a new Item Panel
    public static JPanel getJPanel()
    {
        JPanel panel = new JPanel();

        //Reads the picture file at the end of the file path
        BufferedImage img = null;
		try {
			img = ImageIO.read(new File(picture));
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//creates grid layout and adds border
        panel.setLayout(new GridLayout(1, 5));
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        //takes image icon transforms it to image then scale it down to 120x120
        ImageIcon imageIcon = new ImageIcon(img);
        Image image = imageIcon.getImage();  
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);   
        //price JLabel
        JLabel newPrice = new JLabel("$" + price.toString());
        
        //adds all item info to item panel
        panel.add(new JLabel(new ImageIcon(newimg)));//img
        panel.add(new JLabel(itemName));//name
        panel.add(newPrice);//price
        //formats date
        LocalDate localDate = LocalDate.parse(stringDateEnd);
        stringDateEnd = localDate.format(formatter);
        panel.add(new JLabel("ENDS: " + stringDateEnd));//end date
        panel.add(buttonList.get(itemCount));//button
        
        //adds one to item count
        itemCount++;
        //returns the created Item Panel
        return panel;
    }
    
    
    
    
    //Allows You to get the item price from other class
    static Double getPrice(Double price1) {
    	price1 = price;
    	return price;	
    }

    
    
    
    //Saves the new item 
	public static void saveItem() 
	{
		//sets user inputted text equal to appropriate variable
		 itemName = txtItemName.getText();
		 price = Double.parseDouble(txtPrice.getText());
		 time = Integer.parseInt(txtTime.getText());
		 
		 //adds x(time) amount of days onto the current one, making the end date
		 dateEnd = LocalDate.now().plus(time, ChronoUnit.DAYS);
		 //sets string equal to formatted date
		 stringDateEnd = dateEnd.toString();

		 //creates new Jbutton w/ action listener and ID for ea item
		 JButton h =new JButton(bid);
		 //Sets the button id equal to it's items itemcount
		 h.putClientProperty("id", Integer.valueOf(itemCount));

		 //Action listener for each bid button for each item
		 h.addActionListener(new ActionListener() 
		 { 
			  public void actionPerformed(ActionEvent e) 
			  { 
				  //Gets ID of button that was clicked
				  Object property = h.getClientProperty("id");
				  if (property instanceof Integer) {
				     int objectCounter = ((Integer)property);
				     
				     //sets string equal to the item that was clicked
				     String clickedItem = String.valueOf(itemList.get(objectCounter));
				     
				     //Breaks up string by comma's
				     String[] clickedItems = clickedItem.split(",");
				     
				     //sets ea index equal a peice of info about the item
					 String n = clickedItems[0];
					 String pic = clickedItems[1];
					 Double pri = Double.parseDouble(clickedItems[2]);
					 String t = clickedItems[3];
				   
					 //sets item
				     Bid.setItem(pic, n, t, pri);
				     
				     //opens item's bid screen
				     Bid.main(null);
				  	}				  
			  } 
		 });
		 	//sets size of button and removes filled area and border
	        h.setPreferredSize(new Dimension(40, 40));
	        h.setContentAreaFilled(false); 
	        h.setBorderPainted(false); 
	        //adds button to the arraylist of buttons
	        buttonList.add(h);

	        //sets itemFile equal to the items.csv
			File itemFile = new File("items.csv");

			//creates file if it doesn't already exist
			 if(itemFile.exists()==false)
			    {
			            try 
			            {
			            	itemFile.createNewFile();
						} catch (IOException e1)
			               {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						   }
			    }
			
			 //creates new item and adds it to item array
             Item i = new Item(itemName, picture, price, stringDateEnd, buttonList.get(itemCount));
             itemList.add(i);
             
             //Writes the item to the .csv file
			 try (FileWriter writer = new FileWriter(itemFile, true)) 
			 {
            		 writer.append(itemList.get(itemCount).toString() + "\n");	 
	         } catch (IOException e) 
			 {
					// TODO Auto-generated catch block
					e.printStackTrace();
		     }
			 	 
	}
	

	
	//Pulls all item from CSV that were stored while program was closed
    public static void  pullItems()
    {
    	
		File itemsFile = new File("Items.csv");
		
		//Creates new file if it does not exist
		if(itemsFile.exists()==false)
	    {
	            try 
	            {
	            	itemsFile.createNewFile();
				} catch (IOException e1) 
	            {
					// TODO Auto-generated catch block
					e1.printStackTrace();
	            }
	    }
		
		
		//Creates new scanner called input
		Scanner input = new Scanner(System.in);

         try {
			input = new Scanner(itemsFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

		//While loop cycles through user name file and checks to see if the users registered user name
		//is already in the file, if it is it has the user re-enter a new user name
		 while(input.hasNextLine()) {

			 //Sets what's on the current line of the file equal to the string
			String line = input.nextLine();
			 
			 //if users inputed user name equals the one already in the database it turns the user name check false
			 String[] itemDetails = line.split(",");
			 
			 itemName = itemDetails[0];
			 picture = itemDetails[1];
			 price = Double.parseDouble(itemDetails[2]);
			 stringDateEnd = (itemDetails[3]);

			 
			 //if date is after the current one we continue
			 //if that date is before the current one
			 // we will now show the item
		        LocalDate itemEndDate = LocalDate.parse(stringDateEnd);
		        LocalDate today = LocalDate.now();

		        if (itemEndDate.isAfter(today)) {
		        	//Creates a button for ea item pulled
		        	//Exact same as the buttons created in saveItem method
			 JButton h =new JButton(bid);
			 h.putClientProperty("id", Integer.valueOf(itemCount));

			 h.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  Object property = h.getClientProperty("id");
					  if (property instanceof Integer) {
					     int objectCounter = ((Integer)property);
					     
					     String clickedItem = String.valueOf(itemList.get(objectCounter));
					     
					     String[] clickedItems = clickedItem.split(",");
					     
						 String n = clickedItems[0];
						 String pic = clickedItems[1];
						 Double pri = Double.parseDouble(clickedItems[2]);
						 String t = clickedItems[3];

					     Bid.setItem(pic, n, t, pri);   
					     Bid.main(null);
					     
					     
					  }			
					  } 
				} );
			    //sets button size and removes filled area and border
		        h.setPreferredSize(new Dimension(40, 40));
		        h.setContentAreaFilled(false); 
		        h.setBorderPainted(false); 
		     //adds button to arralist of buttons
			 buttonList.add(h);
			 
			 //sets all the items details
			 item.setItemName(itemName);
			 item.setPicture(picture);
			 item.setPrice(price);
             item.setTime(stringDateEnd);
			 item.setButtonList(buttonList.get(itemCount));
			 //creates new item and adds it to the array
             Item i = new Item(itemName, picture, price, stringDateEnd, buttonList.get(itemCount));
             itemList.add(i);
			 //adds new item JPanel
             HomeScreen.listPanel.addPanel(getJPanel(), 59);

			 Arrays.fill(itemDetails, null);
		        }

		 }
    }
    
    
    
    //getter and setter for the arraylists

	public static ArrayList<JButton> getJButtonList() {
		return buttonList;
	}
	public static void setJButtonList(ArrayList<JButton> buttonList) {
		ItemCreation2.buttonList = buttonList;
	}
	
	public static ArrayList<Item> getItemList() {
		return itemList;
	}
	public static void setItemList(ArrayList<Item> itemList) {
		ItemCreation2.itemList = itemList;
	}

	
	
	
	//sets title bounds 
	//builds frame and sets listeners
	//sets visibility to visible
	public ItemCreation2(String title)	
	{
		super(title);
		
		
		setBounds(400, 100, 400, 500);
		
		
		buildMainFrame();
		addListeners();
		
		setVisible(true);
	}
	
	
	
	
	
	
	//Adds button listeners
	private void addListeners() 
	{
		btnRegister.addActionListener(new AddBtnListener());
		btnCancel.addActionListener(new AddBtnListener());
		btnPicture.addActionListener(new AddBtnListener());

	}
	
	
	
	
	
	
	
	//Builds the frame
	private void buildMainFrame() {
		MainPanel.setLayout(null);
		
		lblItemCreation.setBounds(100, 11, 282, 54);
		lblItemCreation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		MainPanel.add(lblItemCreation);
		
		lblPicture.setBounds(80, 122, 76, 19);
		lblPicture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPicture);
		
		lblItemName.setBounds(50, 178, 90, 25);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblItemName);
		
		lblPrice.setBounds(49, 239, 90, 19);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MainPanel.add(lblPrice);
		
		lblTime.setBounds(68, 303, 90, 19);
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
	
	
	





	public static void main(String[] args)
	{
		ItemCreation2 gui = new ItemCreation2("Item Creation");

	}
	
	
	
	


}
