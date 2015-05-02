
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MyForm extends JFrame {

	public static void main(String[] args) {
		MyForm form = new MyForm();
		form.setVisible(true);
	}
	
	public MyForm() {
            
		// Create Form Frame
		super("Sunchai Sutjarit 5410110535");
		setSize(450, 300);
		setLocation(500, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Create Label Hello
		JLabel lblHello = new JLabel("HelloEverybody");
		lblHello.setBounds(200, 53, 36, 14);
		getContentPane().add(lblHello);	

		// Create Button Submit
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(171, 95, 89, 23);
		getContentPane().add(btnSubmit);	

		// Create Event for Button
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Love you");
			}
		});		
		getContentPane().add(btnSubmit);	
      
	}

}
