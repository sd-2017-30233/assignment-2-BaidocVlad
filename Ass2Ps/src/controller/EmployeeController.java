package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.EmployeeGUI;
import model.Book;
import model.BookCRUD;

public class EmployeeController {

	private EmployeeGUI employeeGUI;
	private BookCRUD books;
	
	public EmployeeController(EmployeeGUI employeeGUI, BookCRUD book){
		this.employeeGUI = employeeGUI;
		this.books = book;
		
		this.employeeGUI.addSellListener(new SellListener());
		this.employeeGUI.addSearchListener(new SearchListener());
	}
	
	class SellListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try{
				int q = Integer.parseInt(employeeGUI.getQuantity());
				if(employeeGUI.getSelectedBook()<0){
					JOptionPane.showMessageDialog(null, "Selectati o carte din tabel.");
				}else{
					if(books.getQuantityOfBook(employeeGUI.getSelectedBook())<q){
						JOptionPane.showMessageDialog(null, "Cantitatea ceruta nu este disponibila.");
						return;
					}else{
						books.sellBook(employeeGUI.getSelectedBook(), q);
						employeeGUI.setBooklist(books.findAll());
					}
				}
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, "Introduceti un numar valid.");
				return;
			}
		}
		
	}
	
	class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int y = 0;
			try{
				if(!employeeGUI.getYear().equals("")){
					y = Integer.parseInt(employeeGUI.getYear());
				}
				employeeGUI.updateTable(books.findBook(employeeGUI.getTitle(), employeeGUI.getAuthor(), y));
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, "Anul nu este un numar valid.");;
			}
		}
		
	}
}
