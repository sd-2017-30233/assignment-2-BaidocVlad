package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import XMLAccess.ReadXMLBook;
import XMLAccess.WriteXMLBook;

public class BookCRUD extends java.util.Observable {
	
	private ArrayList<Book> booklist;
	private WriteXMLBook wrb = new WriteXMLBook();
	public BookCRUD(){
		booklist = Main.getReadBooks();
	}
	
	public void sellBook(int id, int q){
		for(int i = 0;i<booklist.size();i++){
			if(booklist.get(i).getId() == id){
				booklist.get(i).setQuantity(booklist.get(i).getQuantity() - q);
			}
		}
		wrb.writeFile("books.xml",booklist);
		setChanged();
		notifyObservers();
	}
	
	public Book findId(int id){
		for(int i = 0;i<booklist.size();i++){
			if(booklist.get(i).getId() == id){
				return booklist.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Book> findAll(){
		return booklist;
	}
	
	public ArrayList<Book> findBook(String title, String author, int year){
		ArrayList<Book> result = new ArrayList<Book>();
		boolean k = true;
		for(int i=0;i<booklist.size();i++){
			k = true;
			if((!title.equals("")) && (!booklist.get(i).getTitle().toLowerCase().startsWith(title.toLowerCase()))){
				k = false;
			}
			if((!author.equals("")) && (!booklist.get(i).getAuthor().toLowerCase().startsWith(author.toLowerCase()))){
				k = false;
			}
			if((!(year == 0)) && (!(booklist.get(i).getYear() == year))){
				k = false;
			}
			if(k) result.add(booklist.get(i));
		}
		return result;
	}
	
	public void insertBook(Book b){
		for(int i = 0;i<booklist.size();i++){
			if(booklist.get(i).getId() == b.getId()){
				JOptionPane.showMessageDialog(null, "ID-ul cartii exista deja.");
				return;
			}
		}
		booklist.add(b);
		wrb.writeFile("books.xml",booklist);
		setChanged();
		notifyObservers();
	}
	
	public void updateBook(Book b){
		for(int i = 0;i<booklist.size();i++){
			if(booklist.get(i).getId() == b.getId()){
				
				if(!b.getTitle().equals("")){
					booklist.get(i).setTitle(b.getTitle());
				}
				if(!b.getAuthor().equals("")){
					booklist.get(i).setAuthor(b.getAuthor());
				}
				if(!b.getGenre().equals("")){
					booklist.get(i).setGenre(b.getGenre());
				}
				if(b.getYear() != 0){
					booklist.get(i).setYear(b.getYear());
				}
				if(b.getQuantity() != 0){
					booklist.get(i).setQuantity(b.getQuantity());
				}
				if(b.getPrice() != 0){
					booklist.get(i).setPrice(b.getPrice());
				}				
				// booklist.set(i, b);
				wrb.writeFile("books.xml",booklist);
				setChanged();
				notifyObservers();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Nu exista carte cu acest ID.");
	}
	
	public void deleteBook(int id){
		for(int i = 0;i<booklist.size();i++){
			if(booklist.get(i).getId() == id){
				booklist.remove(i);
				wrb.writeFile("books.xml",booklist);
				setChanged();
				notifyObservers();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Nu exista carte cu acest ID.");
	}
	
	public int getQuantityOfBook(int id){
		for(int i = 0;i<booklist.size();i++){
			if(booklist.get(i).getId() == id){
				return booklist.get(i).getQuantity();
			}
		}
		JOptionPane.showMessageDialog(null, "Nu exista carte cu acest ID.");
		return 0;
	}
}
