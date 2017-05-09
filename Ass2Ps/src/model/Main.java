package model;

import java.util.ArrayList;

import controller.*;
import view.*;
import XMLAccess.*;

public class Main {
	
	public static ReadXMLBook readBook = new ReadXMLBook("books.xml");
	public static ReadXMLUser readUser = new ReadXMLUser("users.xml");
	public static LoginController loginController;
	public static AdminController adminController;
	public static EmployeeController employeeController;
	
	public static AdminGUI adminGUI;
	public static EmployeeGUI employeeGUI;
	public static Login login;
	
	public static BookCRUD books = new BookCRUD();
	public static UserCRUD users = new UserCRUD();

	public static void main(String[] args) {
		login = new Login();
		loginController = new LoginController(login,users);
	}
	
	public static ArrayList<Book> getReadBooks(){
		return readBook.getBooks();
	}
	
	public static ArrayList<User> getReadUsers(){
		return readUser.getUsers();
	}
	
	public static void openAdmin(){
		adminGUI = new AdminGUI(books, users);
		adminController = new AdminController(adminGUI,users,books);
	}
	
	public static void openEmployee(){
		employeeGUI = new EmployeeGUI(books);
		employeeController = new EmployeeController(employeeGUI,books);
	}
}
