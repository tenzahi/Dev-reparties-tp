package act2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			
			// Réservation du port et acceptation de connexion
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			
			System.out.println("Client connecté");
			
			
			// Utlisation du InputStreamReader et BufferedReader pour pouvoir lire tous les caractères reçus du client
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bfr = new BufferedReader(isr);
			
			// Conversion du Char vers Integer. 
			int op1 = Integer.parseInt(bfr.readLine());
			int op2 = Integer.parseInt(bfr.readLine());
			
			String operation = bfr.readLine();
			
			// Traitement / Service
			int resultat = op1;
			switch(operation) {
			case "+":
				resultat += op2;
				break;
			case "-":
				resultat -= op2;
				break;
			case "*":
				resultat *= op2;
				break;
			case "/":
				resultat /= op2;
				break;
			}
			
			// Utilisation de PrintWriter pour pouvoir envoyer plus d'un octet
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			
			pw.println(resultat);
			
			// Libération des ressources
			serverSocket.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
