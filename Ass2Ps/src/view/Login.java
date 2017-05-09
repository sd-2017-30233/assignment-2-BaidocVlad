package view;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Login extends JFrame implements ActionListener{
	private static final long serialVersionUID = -5585544690420657305L;
	private JLabel labeluser = new JLabel("Nume Utilizator:");
	private JLabel labelpassword = new JLabel("Parola:");
	private JLabel labelmessage = new JLabel(" ");
	
	private JTextField textuser= new JTextField(15);
	private JPasswordField textpassword = new JPasswordField(15);
	
	private JButton buttonlogin = new JButton("Login");
	private JButton buttonclose = new JButton("Anulare");
	
	
	public Login(){
		super();
		
		
		Container contentPane = this.getContentPane();
		SpringLayout layout = new SpringLayout();
		
        contentPane.setLayout(layout);
        contentPane.add(labeluser);
        contentPane.add(textuser);
        contentPane.add(labelpassword);
        contentPane.add(textpassword);
        contentPane.add(labelmessage);
        contentPane.add(buttonlogin);
        contentPane.add(buttonclose);
        
        
        labelmessage.setForeground (Color.red);
        
        
        //nume utilizator + text field utilizator
        layout.putConstraint(SpringLayout.WEST, labeluser,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.WEST, textuser,
                5,
                SpringLayout.EAST, labeluser);
        layout.putConstraint(SpringLayout.NORTH, textuser,
                5,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labeluser,
                5,
                SpringLayout.NORTH, contentPane);
        //label parola
        layout.putConstraint(SpringLayout.NORTH, labelpassword,
                5,
                SpringLayout.SOUTH, labeluser);
        layout.putConstraint(SpringLayout.WEST, labelpassword,
                5,
                SpringLayout.WEST, contentPane);
        //text field parola
        layout.putConstraint(SpringLayout.WEST, textpassword,
                5,
                SpringLayout.EAST, labeluser);
        layout.putConstraint(SpringLayout.NORTH, textpassword,
                5,
                SpringLayout.SOUTH, textuser);
        //label mesaj
        layout.putConstraint(SpringLayout.WEST, labelmessage,
                25,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelmessage,
                10,
                SpringLayout.SOUTH, labelpassword);
        
        
        //butoane
        layout.putConstraint(SpringLayout.WEST, buttonclose,
                45,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, buttonclose,
                10,
                SpringLayout.SOUTH, labelmessage);
        layout.putConstraint(SpringLayout.WEST, buttonlogin,
                25,
                SpringLayout.EAST, buttonclose);
        layout.putConstraint(SpringLayout.NORTH, buttonlogin,
                10,
                SpringLayout.SOUTH, labelmessage);
        
        this.setTitle("User Login Book Store");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocation(480,290);
        this.setVisible(true);
        this.setSize(290,150);
        this.setResizable(false);
        
        buttonclose.addActionListener(this);
	}
	
	
	public void sendError(String s){
		textpassword.setText("");
		labelmessage.setText(s);;
		this.repaint();
	}
	public String getUser(){
		return this.textuser.getText();
	}
	public String getPassword(){
		return this.textpassword.getText();
	}
	public void clearAll(){
		textpassword.setText("");
		labelmessage.setText("");
		textuser.setText("");
	}
	
	public void addLoginListener(ActionListener a) {
		buttonlogin.addActionListener(a);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonclose) {
			setVisible(false);
			dispose();
		}
	}

}