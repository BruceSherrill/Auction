package AuctionHome;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import AuctionHome.ItemCreation;


public class panelTest extends JPanel
{
	
	
    private JPanel fillerPanel = new JPanel();
    private ArrayList<JPanel> panels = new ArrayList<JPanel>();
    static panelTest listPanel = new panelTest();

    private static String itemName;
    private static String picture;
    private static String price;
    private static String time;

	 
    
    
    
    public panelTest()
    {
    	setLayout(new GridBagLayout());
    }

    public void addPanel(JPanel p, int height)
    {
    	//Removes the filler panel that was there (see the set of 3 comments below)
        remove(fillerPanel);
        GridBagConstraints gbc = new GridBagConstraints();
       //gridx is where the cell is going to be within the grid (Goes off top left corner)
        //From left to right
        gbc.gridx = 0;
        //up and down
        gbc.gridy = panels.size();
        
        //This fills in the empty space on the sides
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = height;
        gbc.weightx = 1.0;
        
        //Adds panel to array list
        panels.add(p);
        add(p, gbc);
        
        
        
        //Putting an invisible panel on the end pushes the panels to the top
        //This is to fix an issue when you have 1-4 panels they try to center themselves vertically
        //This instead pushes them to the top because the weight it set to 1.0
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = panels.size();
        gbc.weighty = 1.0;
        add(fillerPanel, gbc);
        
        
        revalidate();
        repaint();

    }
   
    public void removePanel(int i)
    {
    	//Removes JPanel
        remove(i);
        //Removes from array
        panels.remove(i);
        
        revalidate();
        repaint();
    }

    public static void main(String[] args) 
    {
  
    	//Creates new frame
        JFrame f = new JFrame();
        //exits on close
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Sets size of page
        f.setBounds(100, 100, 1009, 701);
        
        f.getContentPane().setLayout(new BorderLayout());

      
        
        
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent paramActionEvent)
            {
	        	ItemCreation.main(null);
            }
        });
        
        

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent paramActionEvent)
            {
                listPanel.removePanel(0);
            }
        });
        

        //ADDS BUTTONS TO LAYOUT
        f.getContentPane().add(btnRemove, BorderLayout.EAST);
        f.getContentPane().add(btnAdd, BorderLayout.WEST);

        
        
        //SCROLLPANE
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(listPanel);
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ItemCreation.pullItems();
        f.setVisible(true);
    }

    
    
    public static JPanel getJPanel()
    {
        JPanel panel = new JPanel();

        
        BufferedImage img = null;
		try {
			img = ImageIO.read(new File(ItemCreation.getPicture()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        panel.setLayout(new GridLayout(1, 5));
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        ImageIcon imageIcon = new ImageIcon(img);
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        
        panel.add(new JLabel(new ImageIcon(newimg)));
        panel.add(new JLabel(ItemCreation.getItemName()));
        panel.add(new JLabel(ItemCreation.getPrice()));
        panel.add(new JLabel(ItemCreation.getTime()));

        JButton backButton = new JButton(new ImageIcon("BID.png"));
        backButton.setPreferredSize(new Dimension(40, 40));
        backButton.setContentAreaFilled(false); 
        backButton.setBorderPainted(false); 

        panel.add(backButton);

        return panel;
    }
}