//temporary Auction home page

package AuctionHome;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import LoginSystem.LoginSystem;
import LoginSystem.User;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class AuctionHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuctionHome window = new AuctionHome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuctionHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAuctionHome = new JLabel("Auction Home");
		lblAuctionHome.setBounds(168, 94, 107, 14);
		frame.getContentPane().add(lblAuctionHome);
		
		JLabel lblLoggedOnAs = new JLabel("Logged on as: ");
		lblLoggedOnAs.setBounds(108, 152, 93, 16);
		frame.getContentPane().add(lblLoggedOnAs);
		
		String currentUser = LoginSystem.currentUser;
		
		JLabel lblNewLabel = new JLabel(currentUser);
		lblNewLabel.setBounds(219, 152, 189, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}