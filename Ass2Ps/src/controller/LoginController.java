package controller;

import java.awt.event.*;

import model.Main;
import model.User;
import model.UserCRUD;
import view.Login;

public class LoginController {
	
	private Login login;
	private UserCRUD users;
	
	public LoginController(Login login, UserCRUD users){
		this.login = login;
		this.users = users;
		
		this.login.addLoginListener(new LoginListener());
	}
	
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			User u = users.findUserName(login.getUser());
			if(u != null){
				if(u.getPassword().equals(login.getPassword())){
					login.clearAll();
					if(u.getRole().equals("admin")){
						Main.openAdmin();
					}else{
						Main.openEmployee();
					}
				}else{
					login.sendError("Parola incorecta");
				}
			}else{
				login.sendError("Nu exista utilizatorul");
			}
		}
	}
}
