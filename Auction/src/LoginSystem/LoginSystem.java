package LoginSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import AuctionHome.AuctionHome;


import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;




public class LoginSystem {

	private JFrame LoginSystem;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	public static User loggedInUser;
	public static String currentUser;
	
	 // Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSystem window = new LoginSystem();
					window.LoginSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	 //Create the application.
	public LoginSystem() {
		initialize();
	}


	
	 // Initialize the contents of the frame.
	private void initialize() {
		//CREATES NEW FRAME
		LoginSystem = new JFrame();
		LoginSystem.setBounds(200, 200, 500, 300);
		LoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginSystem.getContentPane().setLayout(null);
		
		//"LOGIN: " text
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLogin.setBounds(193, 11, 103, 45);
		LoginSystem.getContentPane().add(lblLogin);
		
		//"USERNAME: " text
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(72, 94, 72, 32);
		LoginSystem.getContentPane().add(lblUsername);
		
		//"PASSWORD: " text
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(82, 145, 82, 32);
		LoginSystem.getContentPane().add(lblPassword);
		
		//USERNAME input
		txtUsername = new JTextField();
		txtUsername.setBounds(154, 102, 200, 20);
		LoginSystem.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		//PASSWORD input
		txtPassword = new JPasswordField();
		txtPassword.setBounds(154, 153, 200, 20);
		LoginSystem.getContentPane().add(txtPassword);
		
		
		
		
		//LOGIN BUTTON
		//everything in this is performed when user clicks login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				//User inputs set equal to strings
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText();
				String username = txtUsername.getText();
				
				//Verification system
				int verification = 1;
				int verify = 0;
				    
				
				
			        //-------------------------------------NEW USER STORAGE---------------------------------------
			        //-------------------------------------------TEST---------------------------------------------
			        //--------------------------------------------------------------------------------------------

			            // holds the instance of a user who successfully logged in
			            loggedInUser = null;

			            // Cycles through list and checks for user name/password match
			            for (User user : Register.getListOfUsers())
			            {
			                if (user.getUsername().equals(username))
			                {
			                    if (user.getPassword().equals(password))
			                    {
			                    	//Sets the successfully logged in user to loggedInUser
			                        loggedInUser = user;

			                        // when a user is found, "break" stops cycling through the list
			                        break;
			                    }
			                }
			            }

			            // Shows if user successfully logged in or not by seeing if loggedInUser changed from null
			            if (loggedInUser != null)
			            {
			                System.out.println("User successfully logged in: "+loggedInUser.getUsername());
			                currentUser = loggedInUser.getUsername().toString();
			                verify++;
			            }
			            
			            else
			            {
			                System.out.println("Invalid username/password combination");
			            }
			        
			//--------------------------------------------END TEST--------------------------------------------------
			//------------------------------------------------------------------------------------------------------
			        
			            
			            
			            
			            
			            
			        //if the user successfully inputs user name and password login screen will close
			        //and open the auction home
			        if (verify == verification) {
					    LoginSystem.dispose();
			        	AuctionHome.main(null);
			        	
			        }
			        
			        
			        //else the user inputs invalid login details
			        //This opens a error message
			        else 
			        {
			        	JOptionPane.showMessageDialog(null, "Invalid Login Details"
			        			, "Login Error", JOptionPane.ERROR_MESSAGE);
			        	//sets text fields to blank
			        	txtUsername.setText(null);
			        	txtPassword.setText(null);
			        	
			        }
			      
			       
			}
		});
		btnLogin.setBounds(154, 207, 89, 23);
		LoginSystem.getContentPane().add(btnLogin);
		
		
		
		
		
		//REGISTER BUTTON
		//THIS OPENS THE REGISTER CLASS IF CLICKED
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
	        	Register.main(null);

			}
		});
		btnRegister.setBounds(265, 207, 89, 23);
		LoginSystem.getContentPane().add(btnRegister);
	}
}