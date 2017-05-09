package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Report;
import model.ReportFactory;
import model.*;
import view.AdminGUI;

public class AdminController {
	
	private AdminGUI adminGUI;
	private UserCRUD users;
	private BookCRUD books;
	
	public AdminController(AdminGUI adminGUI, UserCRUD users, BookCRUD books){
		this.adminGUI = adminGUI;
		this.users = users;
		this.books = books;
		
		this.adminGUI.addAdaugListener(new AdaugListener());
		this.adminGUI.addModificListener(new ModificListener());
		this.adminGUI.addStergListener(new StergListener());
		this.adminGUI.addReportXMLListener(new ReportXMLListener());
		this.adminGUI.addReportTXTListener(new ReportTXTListener());
	}
	
	class AdaugListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int id;
			if(adminGUI.getCase() == 1){
				try{
					id = Integer.parseInt(adminGUI.getUserId());
					User u = new User(id,adminGUI.getUserName(),
							adminGUI.getUserUserName(),adminGUI.getUserPassword(),adminGUI.getUserRole());
					if(users.findId(id) != null){
						JOptionPane.showMessageDialog(null, "Exista un utilizator cu acest ID!");
					}else{
						users.insertUser(u);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Utilizator adaugat cu succes!");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduceti un ID valid.");
					return;
				}
			}else{
				try{
					id = Integer.parseInt(adminGUI.getBookId());
					int p = Integer.parseInt(adminGUI.getBookPrice());
					int q = Integer.parseInt(adminGUI.getBookQuantity());
					int y = Integer.parseInt(adminGUI.getBookYear());
					Book b = new Book(id,adminGUI.getBookTitle(),adminGUI.getBookAuthor(),y,
							adminGUI.getBookGenre(),q,p);
					if(books.findId(id) == null){
						books.insertBook(b);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Carte adaugata cu succes!");
					}else{
						JOptionPane.showMessageDialog(null, "Exista o carte cu acest ID!");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduceti un ID valid.");
					return;
				}
			}
		}
	}

	
	class ModificListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int id;
			if(adminGUI.getCase() == 1){
				try{
					id = Integer.parseInt(adminGUI.getUserId());
					User u = new User(id,adminGUI.getUserName(),
							adminGUI.getUserName(),adminGUI.getUserPassword(),adminGUI.getUserRole());
					if(users.findId(id) == null){
						users.insertUser(u);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Utilizatorul specificat prin ID nu exista, el a fost adaugat.");
					}else{
						users.updateUser(u);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Utilizatorul a fost modificat cu succes!.");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduceti un ID valid.");
					return;
				}
			}else{
				try{
					int p,q,y;
					id = Integer.parseInt(adminGUI.getBookId());
					if(adminGUI.getBookPrice().equals("")){
						p = 0;
					}else{
						p = Integer.parseInt(adminGUI.getBookPrice());
					}
					
					if(adminGUI.getBookQuantity().equals("")){
						q = 0;
					}else{
						q = Integer.parseInt(adminGUI.getBookQuantity());
					}
					
					if(adminGUI.getBookYear().equals("")){
						y = 0;
					}else{
						y = Integer.parseInt(adminGUI.getBookYear());
					}
					Book b = new Book(id,adminGUI.getBookTitle(),adminGUI.getBookAuthor(),y,
							adminGUI.getBookGenre(),q,p);
					if(books.findId(id) == null){
						books.insertBook(b);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Cartea specificata prin acest ID nu exista, ea adaugata cu succes!");
					}else{
						books.updateBook(b);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Cartea a fost modificata cu succes.");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduceti un ID valid.");
					return;
				}
			}
		}
		
	}

	
	class StergListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int id;
			if(adminGUI.getCase() == 1){
				try{
					id = Integer.parseInt(adminGUI.getUserId());
					if(users.findId(id) != null){
						users.deleteUser(id);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Utilizatorul specificat prin ID a fost sters.");
					}else{
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Utilizatorul specificat prin ID nu exista!");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduceti un ID valid.");
					return;
				}
			}else{
				try{
					id = Integer.parseInt(adminGUI.getBookId());
					if(books.findId(id) != null){
						books.deleteBook(id);
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Cartea specificata prin ID a fost stearsa.");
					}else{
						adminGUI.clearFields();
						JOptionPane.showMessageDialog(null, "Cartea specificata prin ID nu exista!");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduceti un ID valid.");
					return;
				}
			}
		}
		
	}
	
	class ReportXMLListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("xml");
			ReportFactory reportFactory = new ReportFactory();
			Report report = reportFactory.factoryMethod("xml");
			report.write("soldout.xml");
		}
	}
	
	class ReportTXTListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("txt");
			ReportFactory reportFactory = new ReportFactory();
			Report report = reportFactory.factoryMethod("txt");
			report.write("soldout.txt");
		}
	}
}
