package logic;

import data_classes.User;

public class AppLogic {

	private UserDAO dao;
	private User user;
	public boolean authorization;
	
	
	public AppLogic(){
		dao = new UserDAO();
		authorization = false;
		user = null;
	}
	
	/**
	 * Method returns user who works now.
	 * @return user
	 */
	public User getUser(){
		return this.user;
	}
	
	protected void finalize() {
		dao.closeConnection();
	}
	
	/**
	 * Method adds new user to DB.
	 * @param newUser
	 */
	public void addNewUser(String log, String passw) {
		User newUser = new User();
		newUser.setUsername(log); 
		newUser.setPassword(MD5Hash.getHash(passw));
		newUser.setAdmin(false);

		dao.getConnection();
		dao.insert(newUser);
		dao.closeConnection();
	}

	/**
	 * Method set user who works with application now.
	 * @param log
	 * @param pasw
	 */
	public void setUser(String log, String pasw) {

		User tempUser = null;
		tempUser = dao.selectUser(log);
		if (tempUser != null && tempUser.getPassword().equals(MD5Hash.getHash(pasw))) {
			user = tempUser;
			authorization = true;
		}
	}

	/**
	 * Exit from user.
	 */
	public void exitUser() {
		authorization = false;
		user = null;
	}
}
