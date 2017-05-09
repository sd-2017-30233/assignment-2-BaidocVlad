package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import model.Book;

public class ReportTXT extends Report{
	
	Writer writer = null;
	
	public ReportTXT(ArrayList<Book> b){
		super.books = b;
	}
	public void write(String file) {
		String str = "";
		for (int i = 0; i < books.size(); i++) {
			str = str + "ID: " + books.get(i).getId() + 
					System.lineSeparator() + "Title: " + books.get(i).getTitle() + 
					System.lineSeparator() + "Author: " + books.get(i).getAuthor()+ 
					System.lineSeparator() + "Year: " + books.get(i).getYear() + 
					System.lineSeparator() + "Quantity: " + books.get(i).getQuantity() + 
					System.lineSeparator() + System.lineSeparator() + System.lineSeparator();
		}

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "utf-8"));
			writer.write(str);
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}
	}

}
