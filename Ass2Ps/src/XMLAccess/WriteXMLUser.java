package XMLAccess;

import java.io.File;
import java.util.ArrayList;

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

import model.User;

public class WriteXMLUser {
	public void writeFile(String file, ArrayList<User> u){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("users");
			doc.appendChild(rootElement);
			
			for(int i=0;i<u.size();i++){
				Element childUser = doc.createElement("user");
				rootElement.appendChild(childUser);
				
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(""+u.get(i).getId()));
				childUser.appendChild(id);

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(""+u.get(i).getName()));
				childUser.appendChild(name);
				
				Element username = doc.createElement("username");
				username.appendChild(doc.createTextNode(""+u.get(i).getUserName()));
				childUser.appendChild(username);
				
				Element password = doc.createElement("password");
				password.appendChild(doc.createTextNode(""+u.get(i).getPassword()));
				childUser.appendChild(password);
				
				Element role = doc.createElement("role");
				role.appendChild(doc.createTextNode(""+u.get(i).getRole()));
				childUser.appendChild(role);
				
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
