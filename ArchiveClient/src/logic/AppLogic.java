package logic;

import java.util.ArrayList;

import application.Client;
import data_classes.Request;
import data_classes.User;
import data_classes.UserCard;

public class AppLogic {

	private Client client;
	private UserDAO dao;
	private User user;
	public boolean authorization;
	public int currentCard;
	private ArrayList<UserCard> cards;
	
	
	public AppLogic(){
		client = new Client();
		client.createIOTreads();
		cards = new ArrayList<UserCard>();
		dao = new UserDAO();
		authorization = false;
		user = null;
		currentCard = 0;
	}
	
	/**
	 * First request to Server. Get all cards from archive.
	 */
	public void start(){
		Request request = new Request("getAll");
		request = client.sendRequest(request);
		if(request!=null){
			cards.addAll(request.cardList);
		}
	}
	
	/**
	 * Get cards array;
	 */
	public ArrayList<UserCard> getCards(){
		return this.cards;
	}
	
	/**
	 * Set cards array
	 */
	public void setCards(ArrayList<UserCard>cards){
		this.cards.clear();
		this.cards.addAll(cards);
	}
	
	/**
	 * Disconnect server.
	 */
	public void end(){
		Request request = new Request("exit");
		client.sendRequest(request);
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
		dao.getConnection();
		tempUser = dao.selectUser(log);
		dao.closeConnection();
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
