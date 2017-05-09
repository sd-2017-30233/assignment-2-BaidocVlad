package model;

public class Book {
	private int id;
	private String title;
	private String author;
	private int year;
	private String genre;
	private int quantity;
	private int price;
	
	public Book(int id, String title, String author, int year, String genre,
			int quantity, int price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.genre = genre;
		this.quantity = quantity;
		this.price = price;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public String getGenre() {
		return genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", year=" + year + ", genre=" + genre + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
}
