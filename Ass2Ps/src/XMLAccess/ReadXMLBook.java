package XMLAccess;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;






import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLBook {
	ArrayList<model.Book> books = new ArrayList<model.Book>();
	
	public ReadXMLBook(String file){
		citire(file);
	}
	
	public void citire(String file){
		File xmlFile = new File(file);
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory				.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
	
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("book");
			
			for(int i = 0;i<nList.getLength();i++){
				Node bookNode = nList.item(i);
				model.Book book = null;
				
				if(bookNode.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element) bookNode;
					book = new model.Book(Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent()),
							e.getElementsByTagName("title").item(0).getTextContent(),
							e.getElementsByTagName("author").item(0).getTextContent(),
							Integer.parseInt(e.getElementsByTagName("year").item(0).getTextContent()),
							e.getElementsByTagName("genre").item(0).getTextContent(),
							Integer.parseInt(e.getElementsByTagName("quantity").item(0).getTextContent()),
							Integer.parseInt(e.getElementsByTagName("price").item(0).getTextContent()));
				}
				books.add(book);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<model.Book> getBooks(){
		return books;
	}
	
	public void setUsers(ArrayList<model.Book> b){
		this.books = b;
	}
}
