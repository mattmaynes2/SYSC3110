package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;

public class ABContactPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JButton addButton;
	
	public ABContactPanel(){
		super();
		setLayout(new GridLayout(4, 2));
		
		this.nameField = new JTextField();
		this.addressField = new JTextField();
		this.phoneField = new JTextField();
		this.addButton = new JButton("Add");
		
		add(new JLabel("Name"));
		add(this.nameField);
		add(new JLabel("Address"));
		add(this.addressField);
		add(new JLabel("Phone Number"));
		add(this.phoneField);
		add(new JLabel(""));
		add(this.addButton);
		
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public void setAddressField(JTextField addressField) {
		this.addressField = addressField;
	}

	public JTextField getPhoneField() {
		return phoneField;
	}

	public void setPhoneField(JTextField phoneField) {
		this.phoneField = phoneField;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}
	
	
	
}
