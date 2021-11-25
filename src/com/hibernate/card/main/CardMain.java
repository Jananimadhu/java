package com.hibernate.card.main;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.card.entity.Card;

import javax.transaction.Transactional;

public class CardMain {
	//static ServerSocket variable
	private static ServerSocket server;
	//socket server port on which it will listen
	private static int port = 9876;
	public static void main(String[] args) {
		
		
		//create Sesion Factory object
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Card.class)
								.buildSessionFactory();

		//create Session object
		Session session = factory.getCurrentSession();

		try {




			//create the socket server object
			server = new ServerSocket(port);
			//keep listens indefinitely until receives 'exit' call or program terminates
			while(true){
				session.beginTransaction();
				System.out.println("Waiting for the client request");
				//creating socket and waiting for client connection
				Socket socket = server.accept();
				//read from socket to ObjectInputStream object
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				//convert ObjectInputStream object to String
				String message = (String) ois.readObject();
				System.out.println("Card info Received: " + message);
				//create ObjectOutputStream object
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				//write object to Socket
				oos.writeObject("Card Info "+message);
				createCards(session);
				session.getTransaction().commit();
//				session.beginTransaction();
				//close resources
				ois.close();
				oos.close();
				socket.close();
				//terminate the server if client sends exit request
				if(message.equalsIgnoreCase("exit")) break;
			}
			System.out.println("Shutting down Socket server!!");
			//close the ServerSocket object
			server.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		



	}
	@Transactional
	private static void createCards(Session session) throws ParseException {

		System.out.println("Initializing Cards Object for Create !!");
		Card card = new Card("4386240000000000","2403",510,201);
		System.out.println("Card Info"+card.toString());
		session.save(card);

	}


}
