package XMLAccess;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.User;


public class ReadXMLUser {
	ArrayList<User> users = new ArrayList<User>();
	
	public ReadXMLUser(String file) {
		citire(file);
	}
	
	public void citire(String file){
		File xmlFile = new File(file);
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
	
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("user");
			
			for(int i = 0;i<nList.getLength();i++){
				Node userNode = nList.item(i);
				User user = null;
				
				if(userNode.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element) userNode;
					user = new User(Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent()),
							e.getElementsByTagName("name").item(0).getTextContent(),
							e.getElementsByTagName("username").item(0).getTextContent(),
							e.getElementsByTagName("password").item(0).getTextContent(),
							e.getElementsByTagName("role").item(0).getTextContent());
				}
				users.add(user);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> getUsers(){
		return this.users;
	}
	
	public void setUsers(ArrayList<User> u){
		this.users = u;
	}
}
