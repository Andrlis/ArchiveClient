package application;

import java.util.Scanner;

import data_classes.Request;

import java.io.*;
import java.net.Socket;

public class Client {

	private Socket server;
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;

	private BufferedReader systemReader;

	public Client() {
		try {
			server = new Socket("localhost", 2015);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public boolean createIOTreads() {
		try {
			objInputStream = new ObjectInputStream(server.getInputStream());
			objOutputStream = new ObjectOutputStream(server.getOutputStream());

			systemReader = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void work() {
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				System.out.print("Client message: ");
				String question = scanner.nextLine();

				objOutputStream.writeObject(question);

				String answer;
				answer = (String) objInputStream.readObject();

				if (answer.equals("exit"))
					break;
				System.out.print("Server answer: ");
				System.out.println(answer);
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
			scanner.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void sendRequest(Request request) {
		Request answer;
		try {
			while (true) {
				if (request.requestType == null)
					break;

				objOutputStream.writeObject(request);

				answer = (Request) objInputStream.readObject();

				if (answer.requestType.equals("exit"))
					break;

				System.out.println("Server answer: type " + request.requestType 
						+ " message: " + request.requestMessage);		
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void endWork() {
		try {
			objOutputStream.close();
			objInputStream.close();
			server.close();
			systemReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
