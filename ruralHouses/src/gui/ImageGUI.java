package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ImageGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	//main program for test
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageGUI frame = new ImageGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImageGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblImg = new JLabel("Jaca House");
		lblImg.setBounds(-32, -39, 674, 442);
		//Change when show information is done, we have to pass a house and display image
		lblImg.setIcon(new ImageIcon("/home/ezequiel/Desktop/tmp/SE/RHJaca.jpg"));
		contentPane.add(lblImg);	
	}

}
