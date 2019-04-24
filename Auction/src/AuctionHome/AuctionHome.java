package AuctionHome;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import LoginSystem.Login;

public class AuctionHome extends JFrame {
	public AuctionHome() {
	}
	
	//Labels
	JLabel lblAuctionHome = new JLabel("Auction Home");
	JLabel lblLoggedInAs = new JLabel("Hello, " + Login.getUsername());

	//Buttons
	JButton btnLogout = new JButton("LOGOUT");

	//Panels
	private JPanel MainPanel = new JPanel();

	//Separator
	JSeparator separator = new JSeparator();
	
	
	private class AddBtnListener implements ActionListener	
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			
			 if (e.getActionCommand().equals("LOGOUT")) 
			 {
				 	setVisible(false);
		        	Login.main(null);
			 }
			 
			 
		}
	}
	
	
	
	
	public AuctionHome(String title)	{
		super(title);
		
		setBounds(100, 100, 1009, 701);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildMainFrame();
		addListeners();
		setVisible(true);

}
	
	
	
	private void addListeners() {
		btnLogout.addActionListener(new AddBtnListener());
	}
	
	
	private void buildMainFrame() {
		MainPanel.setLayout(null);
		
		lblLoggedInAs.setBounds(12, 11, 386, 87);
		lblLoggedInAs.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 20));
		MainPanel.add(lblLoggedInAs);
		
		lblAuctionHome.setBounds(259, 0, 437, 98);
		lblAuctionHome.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 60));
		MainPanel.add(lblAuctionHome);
		
		btnLogout.setBounds(750, 40, 198, 36);
		MainPanel.add(btnLogout);
		
		separator.setBounds(0, 100, 991, 2);
		MainPanel.add(separator);

		
		
		getContentPane().add(MainPanel);
	}
		
	
		public static void main(String[] args) 
		{
			AuctionHome gui = new AuctionHome("AuctionHome2 GUI");
		}
		
	

}
