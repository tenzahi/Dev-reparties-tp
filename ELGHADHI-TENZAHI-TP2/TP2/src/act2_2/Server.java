package act2_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			
			// Réservation du port et acceptation de connexion
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			
			System.out.println("Client connecté");
			
			// Utlisation du ObjectInputStream pour pouvoir lire l'objet reçus du client
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			// Conversion du type Object vers Operation
			Operation op = (Operation) ois.readObject();
			
			// Traitement / Service
			int resultat = op.op1;
			switch(op.operation) {
			case '+':
				resultat += op.op2;
				break;
			case '-':
				resultat -= op.op2;
				break;
			case '*':
				resultat *= op.op2;
				break;
			case '/':
				resultat /= op.op2;
				break;
			}
		
			op.setResultat(resultat);
			
			// Le renvoi du même objet vers le client après modification de la propriétés 'Resultat'
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(op);
			
			// Libération des ressources
			serverSocket.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
