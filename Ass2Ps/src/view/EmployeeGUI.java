package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.Book;
import model.BookCRUD;

public class EmployeeGUI  extends JFrame implements java.util.Observer{
	private JScrollPane scrollPane;
	private Container contentPane;
	private SpringLayout layout;
	private JTable table;
	private JLabel userLabel;
	
	private JButton searchButton;
	private JTextField titleTextField;
	private JTextField yearTextField;
	private JTextField authorTextField;
	private JLabel titleLabel;
	private JLabel yearLabel;
	private JLabel authorLabel;
	
	private BookCRUD books;
	private ArrayList<model.Book> booklist = new ArrayList<model.Book>();
	
	private String[] columnBooks = {"ID", "Title", "Author", "Year", "Genre", "Quantity", "Price"};
	private Object[][] data;
	
	private JLabel quantityLabel;
	private JTextField quantityTextField;
	private JButton buyButton;
	
	public EmployeeGUI(BookCRUD b){
		super();
		books = b;
		
		b.addObserver(this);
		
        contentPane = this.getContentPane();
        layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        authorLabel = new JLabel("Author:");
        yearLabel = new JLabel("Year:");
        titleLabel = new JLabel("Title:");
        searchButton = new JButton("Search");
        authorTextField = new JTextField(10);
        titleTextField = new JTextField(10);
        yearTextField = new JTextField(10);
        userLabel = new JLabel("Employee");
        quantityLabel = new JLabel("Quantity:");
        quantityTextField = new JTextField(5);
        buyButton = new JButton("Sell");
        
        booklist = books.findAll();
        data = new Object[booklist.size()][7];
        table = new JTable(data, columnBooks);
        
        for(int i = 0;i<booklist.size();i++){
        	data[i][0] = booklist.get(i).getId();
        	data[i][1] = booklist.get(i).getTitle();
        	data[i][2] = booklist.get(i).getAuthor();
        	data[i][3] = booklist.get(i).getYear();
        	data[i][4] = booklist.get(i).getGenre();
        	data[i][5] = booklist.get(i).getQuantity();
        	data[i][6] = booklist.get(i).getPrice();
        }
		scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        contentPane.add(authorLabel);
        contentPane.add(authorTextField);
        contentPane.add(searchButton);
        contentPane.add(titleLabel);
        contentPane.add(titleTextField);
        contentPane.add(userLabel);
        contentPane.add(yearLabel);
        contentPane.add(yearTextField);
        contentPane.add(scrollPane);
        contentPane.add(buyButton);
        contentPane.add(quantityLabel);
        contentPane.add(quantityTextField);
        
        
        layout.putConstraint(SpringLayout.NORTH, userLabel,
                15,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, userLabel,
                15,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, userLabel,
                15,
                SpringLayout.EAST, contentPane);
        //search button textField labels
        layout.putConstraint(SpringLayout.NORTH, titleLabel,
                15,
                SpringLayout.SOUTH, userLabel);
        layout.putConstraint(SpringLayout.WEST, titleLabel,
                10,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, titleTextField,
                0,
                SpringLayout.NORTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, titleTextField,
                10,
                SpringLayout.EAST, titleLabel);
        layout.putConstraint(SpringLayout.NORTH, authorLabel,
                0,
                SpringLayout.NORTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, authorLabel,
                10,
                SpringLayout.EAST, titleTextField);
        layout.putConstraint(SpringLayout.NORTH, authorTextField,
                0,
                SpringLayout.NORTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, authorTextField,
                10,
                SpringLayout.EAST, authorLabel);
        layout.putConstraint(SpringLayout.NORTH, yearLabel,
                0,
                SpringLayout.NORTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, yearLabel,
                10,
                SpringLayout.EAST, authorTextField);
        layout.putConstraint(SpringLayout.NORTH, yearTextField,
                0,
                SpringLayout.NORTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, yearTextField,
                10,
                SpringLayout.EAST, yearLabel);
        layout.putConstraint(SpringLayout.NORTH, searchButton,
                -3,
                SpringLayout.NORTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, searchButton,
                10,
                SpringLayout.EAST, yearTextField);
        layout.putConstraint(SpringLayout.NORTH, scrollPane,
                15,
                SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, scrollPane,
                10,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, scrollPane,
                -10,
                SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane,
                -15,
                SpringLayout.NORTH, quantityLabel);
        layout.putConstraint(SpringLayout.SOUTH, quantityLabel,
                -15,
                SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, quantityLabel,
                200,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, quantityTextField,
                2,
                SpringLayout.SOUTH, quantityLabel);
        layout.putConstraint(SpringLayout.WEST, quantityTextField,
                10,
                SpringLayout.EAST, quantityLabel);
        layout.putConstraint(SpringLayout.SOUTH, buyButton,
                3,
                SpringLayout.SOUTH, quantityLabel);
        layout.putConstraint(SpringLayout.WEST, buyButton,
                10,
                SpringLayout.EAST, quantityTextField);
        
        this.setTitle("Book Store - Employee");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocation(480,200);
        this.setVisible(true);
        this.setSize(600,400);
        this.setResizable(false);
	}
	
	public String getAuthor(){
		return authorTextField.getText();
	}
	
	public String getTitle(){
		return titleTextField.getText();
	}
	
	public String getYear(){
		return yearTextField.getText();
	}
	
	public String getQuantity(){
		return this.quantityTextField.getText();
	}
	
	public void clearTextFields(){
		quantityTextField.setText("");
		authorTextField.setText("");
		titleTextField.setText("");
		yearTextField.setText("");
	}
	
	public int getSelectedBook(){
		if(table.getSelectedRow() < 0){
			return -1;
		}
		else{
			return (int) table.getValueAt(table.getSelectedRow(), 0);
		}
	}
	
	public void setBooklist(ArrayList<Book> b){
		booklist = b;
	}
	
	public void addSellListener(ActionListener a){
		buyButton.addActionListener(a);
	}
	
	public void addSearchListener(ActionListener a){
		searchButton.addActionListener(a);
	}
	
	public void updateTable(ArrayList<Book> b){
		data = new Object[b.size()][7];
		for(int i = 0;i<b.size();i++){
        	data[i][0] = b.get(i).getId();
        	data[i][1] = b.get(i).getTitle();
        	data[i][2] = b.get(i).getAuthor();
        	data[i][3] = b.get(i).getYear();
        	data[i][4] = b.get(i).getGenre();
        	data[i][5] = b.get(i).getQuantity();
        	data[i][6] = b.get(i).getPrice();
        }
        table = new JTable(data, columnBooks);
        scrollPane.setViewportView(table);
        this.repaint();
	}
	
	public void update(Observable arg0, Object arg1) {
		booklist = books.findAll();
		data = new Object[booklist.size()][7];
        
        for(int i = 0;i<booklist.size();i++){
        	data[i][0] = booklist.get(i).getId();
        	data[i][1] = booklist.get(i).getTitle();
        	data[i][2] = booklist.get(i).getAuthor();
        	data[i][3] = booklist.get(i).getYear();
        	data[i][4] = booklist.get(i).getGenre();
        	data[i][5] = booklist.get(i).getQuantity();
        	data[i][6] = booklist.get(i).getPrice();
        }
        table = new JTable(data, columnBooks);
        scrollPane.setViewportView(table);
        this.repaint();
	}
}
