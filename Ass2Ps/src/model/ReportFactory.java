package model;

import java.util.ArrayList;

import model.Book;
import model.Main;

public class ReportFactory {
	private ArrayList<Book> booklist = new ArrayList<Book>();
	
	public ReportFactory(){
		this.booklist = Main.getReadBooks();
		for(int i = 0;i<booklist.size();i++){
			if(booklist.get(i).getQuantity() != 0){
				booklist.remove(i);
				i--;
			}
		}
	}
	
	public Report factoryMethod(String mod){
		if(mod.equals("txt")){
			return new ReportTXT(booklist);
		}
		if(mod.equals("xml")){
			return new ReportXML(booklist);
		}
		return null;
	}
}
