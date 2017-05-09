package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.Book;
import model.BookCRUD;
import model.User;
import model.UserCRUD;

public class AdminGUI extends JFrame implements ActionListener, java.util.Observer{
	private JScrollPane scrollPaneB;
	private JScrollPane scrollPaneU;
	private Container contentPane;
	private SpringLayout layout = new SpringLayout();
	private SpringLayout layoutu = new SpringLayout();
	private SpringLayout layoutb = new SpringLayout();
	
	private BookCRUD books;
	private ArrayList<model.Book> booklist = new ArrayList<model.Book>();
	private UserCRUD users;
	private ArrayList<model.User> userlist = new ArrayList<model.User>();
	private String[] columnBooks = {"ID", "Title", "Author", "Year", "Genre", "Quantity", "Price"};
	private String[] columnUsers = {"ID", "Name", "User Name", "Password", "Role"};
	private Object[][] dataBooks;
	private Object[][] dataUsers;
	private JTable tableBooks;
	private JTable tableUsers;
	
	private JLabel userLabel = new JLabel("Admin");
	
	private JPanel cards = new JPanel(new CardLayout());
	private JPanel booksPanel = new JPanel();
	private JPanel usersPanel = new JPanel();
	private CardLayout cardLayout;
	
	private JButton usersCardButton = new JButton("Users");
	private JButton booksCardButton = new JButton("Books");
	
	private JLabel reportLabel = new JLabel("Generate Sold-out books report:");
	private JButton reportXMLButton = new JButton("XML Report");
	private JButton reportTXTButton = new JButton("TXT Report");
	
	private JLabel bookIdLabel = new JLabel("ID:");
	private JLabel bookTitleLabel = new JLabel("Title:");
	private JLabel bookAuthorLabel = new JLabel("Author:");
	private JLabel bookYearLabel = new JLabel("Year:");
	private JLabel bookGenreLabel = new JLabel("Genre:");
	private JLabel bookQuantityLabel = new JLabel("Quantity:");
	private JLabel bookPriceLabel = new JLabel("Price:");
	
	private JTextField bookIdTextField = new JTextField(4);
	private JTextField bookTitleTextField = new JTextField(10);
	private JTextField bookAuthorTextField = new JTextField(10);
	private JTextField bookYearTextField = new JTextField(4);
	private JTextField bookGenreTextField = new JTextField(7);
	private JTextField bookQuantityTextField = new JTextField(4);
	private JTextField bookPriceTextField = new JTextField(4);
			
	private JLabel userIdLabel = new JLabel("ID:");
	private JLabel userNameLabel = new JLabel("Name:");
	private JLabel userUsernameLabel = new JLabel("User Name:");
	private JLabel userPasswordLabel = new JLabel("Password:");
	private JLabel userRoleLabel = new JLabel("Role:");
	
	private JTextField userIdTextField = new JTextField(3);
	private JTextField userNameTextField = new JTextField(8);
	private JTextField userUsernameTextField = new JTextField(8);
	private JTextField userPasswordTextField = new JTextField(7);
	private JTextField userRoleTextField = new JTextField(6);
	
	private JButton adaug = new JButton("Adaugare");
	private JButton sterg = new JButton("Stergere");
	private JButton modific = new JButton("Modificare");
	
	private int caz;
	
	public AdminGUI(BookCRUD b, UserCRUD u){
		super();
		books = b;
		users = u;
		
		b.addObserver(this);
		u.addObserver(this);
		
		contentPane = this.getContentPane();
        
        contentPane.setLayout(layout);

		cards.add(booksPanel,"Card Books");
		cards.add(usersPanel,"Card Users");
		cardLayout = (CardLayout) cards.getLayout();
		
		booklist = books.findAll();
		dataBooks = new Object[booklist.size()][7];
		for(int i = 0;i<booklist.size();i++){
			dataBooks[i][0] = booklist.get(i).getId();
			dataBooks[i][1] = booklist.get(i).getTitle();
			dataBooks[i][2] = booklist.get(i).getAuthor();
			dataBooks[i][3] = booklist.get(i).getYear();
			dataBooks[i][4] = booklist.get(i).getGenre();
			dataBooks[i][5] = booklist.get(i).getQuantity();
			dataBooks[i][6] = booklist.get(i).getPrice();
        }
		
		userlist = users.findAll();
		dataUsers = new Object[userlist.size()][5];
		
		for(int i = 0;i<userlist.size();i++){
			dataUsers[i][0] = userlist.get(i).getId();
			dataUsers[i][1] = userlist.get(i).getName();
			dataUsers[i][2] = userlist.get(i).getUserName();
			dataUsers[i][3] = userlist.get(i).getPassword();
			dataUsers[i][4] = userlist.get(i).getRole();
		}
		
		tableBooks = new JTable(dataBooks, columnBooks);
		tableUsers = new JTable(dataUsers, columnUsers);
		scrollPaneB = new JScrollPane(tableBooks,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneU = new JScrollPane(tableUsers,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		cardLayout.show(cards, "Card Users");
		caz = 1;
		
		contentPane.add(cards);
		contentPane.add(userLabel);
		contentPane.add(reportLabel);
		contentPane.add(reportXMLButton);
		contentPane.add(reportTXTButton);
		contentPane.add(usersCardButton);
		contentPane.add(booksCardButton);
		contentPane.add(adaug);
		contentPane.add(modific);
		contentPane.add(sterg);
		
		booksPanel.setLayout(layoutb);
		usersPanel.setLayout(layoutu);
		
		
		booksPanel.add(scrollPaneB);
		booksPanel.add(bookIdLabel);
		booksPanel.add(bookIdTextField);
		booksPanel.add(bookAuthorLabel);
		booksPanel.add(bookAuthorTextField);
		booksPanel.add(bookGenreLabel);
		booksPanel.add(bookGenreTextField);
		booksPanel.add(bookPriceLabel);
		booksPanel.add(bookPriceTextField);
		booksPanel.add(bookQuantityLabel);
		booksPanel.add(bookQuantityTextField);
		booksPanel.add(bookTitleLabel);
		booksPanel.add(bookTitleTextField);
		booksPanel.add(bookYearLabel);
		booksPanel.add(bookYearTextField);
		
		usersPanel.add(scrollPaneU);
		usersPanel.add(userIdLabel);
		usersPanel.add(userIdTextField);
		usersPanel.add(userNameLabel);
		usersPanel.add(userNameTextField);
		usersPanel.add(userPasswordLabel);
		usersPanel.add(userPasswordTextField);
		usersPanel.add(userRoleLabel);
		usersPanel.add(userRoleTextField);
		usersPanel.add(userUsernameLabel);
		usersPanel.add(userUsernameTextField);
		
		layout.putConstraint(SpringLayout.NORTH, userLabel,
                15,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, userLabel,
                15,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, userLabel,
                15,
                SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, usersCardButton,
                15,
                SpringLayout.SOUTH, userLabel);
        layout.putConstraint(SpringLayout.WEST, usersCardButton,
                15,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, booksCardButton,
                0,
                SpringLayout.NORTH, usersCardButton);
        layout.putConstraint(SpringLayout.WEST, booksCardButton,
                25,
                SpringLayout.EAST, usersCardButton);
        
        
        
        layout.putConstraint(SpringLayout.NORTH, reportLabel,
                0,
                SpringLayout.NORTH, usersCardButton);
        layout.putConstraint(SpringLayout.WEST, reportLabel,
                25,
                SpringLayout.EAST, booksCardButton);
        layout.putConstraint(SpringLayout.NORTH, reportXMLButton,
                0,
                SpringLayout.NORTH, usersCardButton);
        layout.putConstraint(SpringLayout.WEST, reportXMLButton,
                25,
                SpringLayout.EAST, reportLabel);
        layout.putConstraint(SpringLayout.NORTH, reportTXTButton,
                0,
                SpringLayout.NORTH, usersCardButton);
        layout.putConstraint(SpringLayout.WEST, reportTXTButton,
                25,
                SpringLayout.EAST, reportXMLButton);
        
        
        
        layout.putConstraint(SpringLayout.NORTH, cards,
                15,
                SpringLayout.SOUTH, usersCardButton);
        layout.putConstraint(SpringLayout.WEST, cards,
                15,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, cards,
                -15,
                SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, cards,
                -15,
                SpringLayout.NORTH, adaug);        
        layout.putConstraint(SpringLayout.SOUTH, adaug,
                -15,
                SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, adaug,
                15,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, modific,
                -15,
                SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, modific,
                15,
                SpringLayout.EAST, adaug);
        layout.putConstraint(SpringLayout.SOUTH, sterg,
                -15,
                SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, sterg,
                15,
                SpringLayout.EAST, modific);
        layoutb.putConstraint(SpringLayout.NORTH, scrollPaneB,
                0,
                SpringLayout.NORTH, booksPanel);
        layoutb.putConstraint(SpringLayout.WEST, scrollPaneB,
                0,
                SpringLayout.WEST, booksPanel);
        layoutb.putConstraint(SpringLayout.EAST, scrollPaneB,
                0,
                SpringLayout.EAST, booksPanel);
        layoutb.putConstraint(SpringLayout.SOUTH, scrollPaneB,
                -10,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookIdLabel,
                10,
                SpringLayout.WEST, booksPanel);
        layoutb.putConstraint(SpringLayout.SOUTH, bookIdLabel,
                -40,
                SpringLayout.SOUTH, booksPanel);
        layoutb.putConstraint(SpringLayout.NORTH, bookIdTextField,
                0,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookIdTextField,
                10,
                SpringLayout.EAST, bookIdLabel);
        layoutb.putConstraint(SpringLayout.NORTH, bookTitleLabel,
                0,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookTitleLabel,
                10,
                SpringLayout.EAST, bookIdTextField);
        layoutb.putConstraint(SpringLayout.NORTH, bookTitleTextField,
                0,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookTitleTextField,
                10,
                SpringLayout.EAST, bookTitleLabel);
        layoutb.putConstraint(SpringLayout.NORTH, bookAuthorLabel,
                0,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookAuthorLabel,
                10,
                SpringLayout.EAST, bookTitleTextField);
        layoutb.putConstraint(SpringLayout.NORTH, bookAuthorTextField,
                0,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookAuthorTextField,
                10,
                SpringLayout.EAST, bookAuthorLabel);
        layoutb.putConstraint(SpringLayout.NORTH, bookYearLabel,
                0,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookYearLabel,
                10,
                SpringLayout.EAST, bookAuthorTextField);
        layoutb.putConstraint(SpringLayout.NORTH, bookYearTextField,
                0,
                SpringLayout.NORTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookYearTextField,
                10,
                SpringLayout.EAST, bookYearLabel);
        layoutb.putConstraint(SpringLayout.NORTH, bookGenreLabel,
                15,
                SpringLayout.SOUTH, bookIdLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookGenreLabel,
                0,
                SpringLayout.WEST, bookIdLabel);
        layoutb.putConstraint(SpringLayout.SOUTH, bookGenreLabel,
                -10,
                SpringLayout.SOUTH, booksPanel);
        layoutb.putConstraint(SpringLayout.NORTH, bookGenreTextField,
                0,
                SpringLayout.NORTH, bookGenreLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookGenreTextField,
                10,
                SpringLayout.EAST, bookGenreLabel);
        layoutb.putConstraint(SpringLayout.NORTH, bookQuantityLabel,
                0,
                SpringLayout.NORTH, bookGenreLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookQuantityLabel,
                10,
                SpringLayout.EAST, bookGenreTextField);
        layoutb.putConstraint(SpringLayout.NORTH, bookQuantityTextField,
                0,
                SpringLayout.NORTH, bookGenreLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookQuantityTextField,
                10,
                SpringLayout.EAST, bookQuantityLabel);
        layoutb.putConstraint(SpringLayout.NORTH, bookPriceLabel,
                0,
                SpringLayout.NORTH, bookGenreLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookPriceLabel,
                10,
                SpringLayout.EAST, bookQuantityTextField);
        layoutb.putConstraint(SpringLayout.NORTH, bookPriceTextField,
                0,
                SpringLayout.NORTH, bookGenreLabel);
        layoutb.putConstraint(SpringLayout.WEST, bookPriceTextField,
                10,
                SpringLayout.EAST, bookPriceLabel);
        layoutu.putConstraint(SpringLayout.NORTH, scrollPaneU,
                0,
                SpringLayout.NORTH, usersPanel);
        layoutu.putConstraint(SpringLayout.WEST, scrollPaneU,
                0,
                SpringLayout.WEST, usersPanel);
        layoutu.putConstraint(SpringLayout.EAST, scrollPaneU,
                0,
                SpringLayout.EAST, usersPanel);
        layoutu.putConstraint(SpringLayout.SOUTH, scrollPaneU,
                -10,
                SpringLayout.NORTH, userIdLabel);
        
        layoutu.putConstraint(SpringLayout.WEST, userIdLabel,
                0,
                SpringLayout.WEST, usersPanel);
        layoutu.putConstraint(SpringLayout.SOUTH, userIdLabel,
                -10,
                SpringLayout.SOUTH, usersPanel);
        layoutu.putConstraint(SpringLayout.WEST, userIdLabel,
                10,
                SpringLayout.WEST, usersPanel);
        layoutu.putConstraint(SpringLayout.NORTH, userIdTextField,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userIdTextField,
                10,
                SpringLayout.EAST, userIdLabel);
        layoutu.putConstraint(SpringLayout.NORTH, userNameLabel,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userNameLabel,
                10,
                SpringLayout.EAST, userIdTextField);
        layoutu.putConstraint(SpringLayout.NORTH, userNameTextField,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userNameTextField,
                10,
                SpringLayout.EAST, userNameLabel);
        layoutu.putConstraint(SpringLayout.NORTH, userUsernameLabel,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userUsernameLabel,
                10,
                SpringLayout.EAST, userNameTextField);
        layoutu.putConstraint(SpringLayout.NORTH, userUsernameTextField,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userUsernameTextField,
                10,
                SpringLayout.EAST, userUsernameLabel);
        layoutu.putConstraint(SpringLayout.NORTH, userPasswordLabel,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userPasswordLabel,
                10,
                SpringLayout.EAST, userUsernameTextField);
        layoutu.putConstraint(SpringLayout.NORTH, userPasswordTextField,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userPasswordTextField,
                10,
                SpringLayout.EAST, userPasswordLabel);
        layoutu.putConstraint(SpringLayout.NORTH, userRoleLabel,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userRoleLabel,
                10,
                SpringLayout.EAST, userPasswordTextField);
        layoutu.putConstraint(SpringLayout.NORTH, userRoleTextField,
                0,
                SpringLayout.NORTH, userIdLabel);
        layoutu.putConstraint(SpringLayout.WEST, userRoleTextField,
                10,
                SpringLayout.EAST, userRoleLabel);
        
        
        
        
        usersCardButton.addActionListener(this);
        booksCardButton.addActionListener(this);

        userIdLabel.setForeground (Color.red);
        bookIdLabel.setForeground (Color.red);
        
		this.setTitle("Book Store - Admin");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocation(250,250);
        this.setVisible(true);
        this.setSize(740,450);
        this.setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == usersCardButton){
			cardLayout.show(cards, "Card Users");
			caz = 1;
		}
		if (e.getSource() == booksCardButton){
			cardLayout.show(cards, "Card Books");
			caz = 2;
		}
	}
	
	public int getCase(){
		return this.caz;
	}
	
	public String getUserId(){
		return this.userIdTextField.getText();
	}
	
	public String getUserName(){
		return this.userNameTextField.getText();
	}
	
	public String getUserUserName(){
		return this.userUsernameTextField.getText();
	}
	
	public String getUserPassword(){
		return this.userPasswordTextField.getText();
	}
	
	public String getUserRole(){
		return this.userRoleTextField.getText();
	}
	
	public String getBookId(){
		return this.bookIdTextField.getText();
	}
	
	public String getBookTitle(){
		return this.bookTitleTextField.getText();
	}
	
	public String getBookAuthor(){
		return this.bookAuthorTextField.getText();
	}
	
	public String getBookYear(){
		return this.bookYearTextField.getText();
	}
	
	public String getBookGenre(){
		return this.bookGenreTextField.getText();
	}
	
	public String getBookQuantity(){
		return this.bookQuantityTextField.getText();
	}
	
	public String getBookPrice(){
		return this.bookPriceTextField.getText();
	}
	
	public void clearFields(){
		this.userIdTextField.setText("");
		this.userNameTextField.setText("");
		this.userPasswordTextField.setText("");
		this.userUsernameTextField.setText("");
		this.userRoleTextField.setText("");
		
		this.bookAuthorTextField.setText("");
		this.bookGenreTextField.setText("");
		this.bookIdTextField.setText("");
		this.bookPriceTextField.setText("");
		this.bookQuantityTextField.setText("");
		this.bookTitleTextField.setText("");
		this.bookYearTextField.setText("");
	}
	
	public void addAdaugListener(ActionListener a){
		adaug.addActionListener(a);
	}
	
	public void addStergListener(ActionListener a){
		sterg.addActionListener(a);
	}
	
	public void addModificListener(ActionListener a){
		modific.addActionListener(a);
	}
	
	public void addReportXMLListener(ActionListener a){
		reportXMLButton.addActionListener(a);
	}
	
	public void addReportTXTListener(ActionListener a){
		reportTXTButton.addActionListener(a);
	}
	
	public void updateTableUser(ArrayList<User> u){
		dataUsers = new Object[u.size()][5];
		for(int i = 0;i<u.size();i++){
			dataUsers[i][0] = u.get(i).getId();
			dataUsers[i][1] = u.get(i).getName();
			dataUsers[i][2] = u.get(i).getUserName();
			dataUsers[i][3] = u.get(i).getPassword();
			dataUsers[i][4] = u.get(i).getRole();
		}
		tableUsers = new JTable(dataUsers, columnUsers);
		scrollPaneU.setViewportView(tableUsers);
		
		this.repaint();
	}
	public void updateTableBook(ArrayList<Book> b){
		dataBooks = new Object[b.size()][7];
		for(int i = 0;i<b.size();i++){
			dataBooks[i][0] = b.get(i).getId();
			dataBooks[i][1] = b.get(i).getTitle();
			dataBooks[i][2] = b.get(i).getAuthor();
			dataBooks[i][3] = b.get(i).getYear();
			dataBooks[i][4] = b.get(i).getGenre();
			dataBooks[i][5] = b.get(i).getQuantity();
			dataBooks[i][6] = b.get(i).getPrice();
        }
		tableBooks = new JTable(dataBooks, columnBooks);
		scrollPaneB.setViewportView(tableBooks);
		
		this.repaint();
	}

	public void update(Observable o, Object arg) {
		booklist = books.findAll();
		dataBooks = new Object[booklist.size()][7];
		for(int i = 0;i<booklist.size();i++){
			dataBooks[i][0] = booklist.get(i).getId();
			dataBooks[i][1] = booklist.get(i).getTitle();
			dataBooks[i][2] = booklist.get(i).getAuthor();
			dataBooks[i][3] = booklist.get(i).getYear();
			dataBooks[i][4] = booklist.get(i).getGenre();
			dataBooks[i][5] = booklist.get(i).getQuantity();
			dataBooks[i][6] = booklist.get(i).getPrice();
        }
		tableBooks = new JTable(dataBooks, columnBooks);
		scrollPaneB.setViewportView(tableBooks);
		
		userlist = users.findAll();
		dataUsers = new Object[userlist.size()][5];
		for(int i = 0;i<userlist.size();i++){
			dataUsers[i][0] = userlist.get(i).getId();
			dataUsers[i][1] = userlist.get(i).getName();
			dataUsers[i][2] = userlist.get(i).getUserName();
			dataUsers[i][3] = userlist.get(i).getPassword();
			dataUsers[i][4] = userlist.get(i).getRole();
		}
		tableUsers = new JTable(dataUsers, columnUsers);
		scrollPaneU.setViewportView(tableUsers);
		
		this.repaint();
	}
}