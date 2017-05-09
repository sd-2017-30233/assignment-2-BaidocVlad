package model;

import java.util.ArrayList;
import model.Book;

public abstract class Report {
	
	protected ArrayList<Book> books;
	
	public abstract void write(String file);
}
