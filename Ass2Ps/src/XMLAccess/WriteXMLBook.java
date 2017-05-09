package XMLAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Book;

public class WriteXMLBook {
	
	public void writeFile(String file, ArrayList<Book> books) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("books");
			doc.appendChild(rootElement);
			
			for(int i=0;i<books.size();i++){
				Element childBook = doc.createElement("book");
				rootElement.appendChild(childBook);

				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(""+books.get(i).getId()));
				childBook.appendChild(id);

				Element title = doc.createElement("title");
				title.appendChild(doc.createTextNode(books.get(i).getTitle()));
				childBook.appendChild(title);

				Element author = doc.createElement("author");
				author.appendChild(doc.createTextNode(books.get(i).getAuthor()));
				childBook.appendChild(author);

				Element year = doc.createElement("year");
				year.appendChild(doc.createTextNode(""+books.get(i).getYear()));
				childBook.appendChild(year);
				
				Element genre = doc.createElement("genre");
				genre.appendChild(doc.createTextNode(books.get(i).getGenre()));
				childBook.appendChild(genre);

				Element quantity = doc.createElement("quantity");
				quantity.appendChild(doc.createTextNode(""+books.get(i).getQuantity()));
				childBook.appendChild(quantity);

				Element price = doc.createElement("price");
				price.appendChild(doc.createTextNode(""+books.get(i).getPrice()));
				childBook.appendChild(price);
				
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(file));
				transformer.transform(source, result);
			}
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
