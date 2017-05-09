package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import XMLAccess.WriteXMLUser;

public class UserCRUD extends java.util.Observable {
	
	private ArrayList<User> userlist;
	private WriteXMLUser wru = new WriteXMLUser();
	
	public UserCRUD(){
		this.userlist = Main.getReadUsers();
	}
	
	public ArrayList<User> findAll(){
		return userlist;
	}
	
	public User findId(int id){
		for(int i = 0;i<userlist.size();i++){
			if(userlist.get(i).getId() == id){
				return userlist.get(i);
			}
		}
		return null;
	}
	
	public User findUserName(String name){
		for(int i = 0;i<userlist.size();i++){
			if(userlist.get(i).getUserName().toLowerCase().equals(name.toLowerCase())){
				return userlist.get(i);
			}
		}
		return null;
	}
	
	public void insertUser(User u){
		for(int i = 0;i<userlist.size();i++){
			if(userlist.get(i).getUserName().equals(u.getUserName()) || userlist.get(i).getId() == u.getId()){
				JOptionPane.showMessageDialog(null, "Numele sau ID de utilizator exista deja.");
				return;
			}
		}
		userlist.add(u);
		wru.writeFile("users.xml", userlist);
		setChanged();
		notifyObservers();
	}
	
	public void updateUser(User u){
		for(int i = 0;i<userlist.size();i++){
			if(userlist.get(i).getId() == u.getId()){
				
				if(!u.getName().equals("")){
					userlist.get(i).setName(u.getName());
				}
				if(!u.getUserName().equals("")){
					userlist.get(i).setUserName(u.getUserName());
				}
				if(!u.getRole().equals("")){
					userlist.get(i).setRole(u.getRole());
				}
				if(!u.getPassword().equals("")){
					userlist.get(i).setPassword(u.getPassword());
				}
				//userlist.set(i, u);
				wru.writeFile("users.xml", userlist);
				setChanged();
				notifyObservers();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Nu exista utilizator cu acest ID.");
	}
	
	public void deleteUser(int id){
		for(int i = 0;i<userlist.size();i++){
			if(userlist.get(i).getId() == id){
				userlist.remove(i);
				wru.writeFile("users.xml", userlist);
				setChanged();
				notifyObservers();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Nu exista utilizator cu acest ID.");
	}
}
