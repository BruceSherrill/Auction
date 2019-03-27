package LoginSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Register {

	private JFrame AccountRegistration;
	public JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	public String firstName;
	public String lastName;
	public String username;
	public String password;

    private static List<User> listOfUsers = new ArrayList<>();

	
	 //Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.AccountRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	 // Create the application.
	public Register() {
		initialize();
	}

	
	
	 // Initialize the contents of the frame.
	private void initialize() {
		
		//CREATES NEW FRAME
		AccountRegistration = new JFrame();
		AccountRegistration.setBounds(100, 100, 400, 500);
		AccountRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AccountRegistration.getContentPane().setLayout(null);
		
		//TITLE "Account Registration" text
		JLabel lblAccountRegistration = new JLabel("Account Registration");
		lblAccountRegistration.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAccountRegistration.setBounds(57, 11, 282, 54);
		AccountRegistration.getContentPane().add(lblAccountRegistration);
		
		//"FIRST NAME: " text
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(57, 122, 76, 19);
		AccountRegistration.getContentPane().add(lblFirstName);
		
		//"LAST NAME: " text
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(57, 178, 85, 19);
		AccountRegistration.getContentPane().add(lblLastName);
		
		//"USERNAME: " text
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(57, 239, 85, 19);
		AccountRegistration.getContentPane().add(lblUsername);
		
		//"PASSWORD: " text
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(66, 303, 76, 19);
		AccountRegistration.getContentPane().add(lblPassword);
		
		//FIRSTNAME input
		txtFirstName = new JTextField();
		txtFirstName.setBounds(146, 123, 142, 20);
		AccountRegistration.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		//LASTNAME input
		txtLastName = new JTextField();
		txtLastName.setBounds(146, 179, 142, 20);
		AccountRegistration.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		//USERNAME input
		txtUsername = new JTextField();
		txtUsername.setBounds(146, 240, 142, 20);
		AccountRegistration.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		//PASSWORD input
		txtPassword = new JPasswordField();
		txtPassword.setBounds(146, 304, 142, 21);
		AccountRegistration.getContentPane().add(txtPassword);

		
		//REGISTER BUTTON
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				//User inputs 
			    firstName = txtFirstName.getText();
				lastName = txtLastName.getText();
				username = txtUsername.getText();
				password = txtPassword.getText();
				 
				 //If Statement verifies that no inputs are blanks
				 if (username != null && !username.isEmpty() && password != null && !password.isEmpty() 
					&& firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty())
				 {
					 
					 	//Adds new user to array list
			            listOfUsers.add(new User(username,password));
		        	
		        	//Closes the account registration page
				    AccountRegistration.dispose();
				 }
				 
				 
				 //else if text field is left blank error message is displayed
			        else 
			        {
			        	JOptionPane.showMessageDialog(null, "Invalid Registration Details! Please Retry"
			        			, "Registration Error", JOptionPane.ERROR_MESSAGE);
			        	//Sets text fields to null
			        	txtUsername.setText(null);
			        	txtPassword.setText(null);
			        	txtFirstName.setText(null);
			        	txtLastName.setText(null);
			        }

				    


			}
		});
		


		btnRegister.setBounds(146, 392, 89, 23);
		AccountRegistration.getContentPane().add(btnRegister);
	}



	public static List<User> getListOfUsers() {
		return listOfUsers;
	}



	

}