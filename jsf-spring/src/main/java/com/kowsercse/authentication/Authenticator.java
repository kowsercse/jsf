package com.kowsercse.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.kowsercse.dao.UserDao;
import com.kowsercse.entity.Role;
import com.kowsercse.entity.User;

@Service
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class Authenticator {

	@Autowired
	private UserDao userDao;
	
	private User currentUser;
	
	public boolean authorize(String userName, String password) {
		currentUser = new User();
		currentUser.setUserName("fun");
		return true;		
//		User user = userDao.findByUserName(userName);
//		if(user.getPassword().equals(password)) {
//			currentUser = user;
//			return true;
//		}
//		return false;
	}
	
	public boolean authorized(User user, Role requiredRole) {
		if(user.getRole().getPriority() <= requiredRole.getPriority()) {
			return true;
		}
		return false;
	}
	
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public boolean isLoggedIn() {
		return currentUser == null? false: true;
	}
	
}
