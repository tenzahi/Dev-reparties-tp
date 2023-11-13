package act2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			// Connexion au serveur
			Socket socket = new Socket("localhost", 1234);
			
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true); // Utilisation de PrintWriter pour pouvoir envoyer plus d'un octet
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Donner le premier operateur : ");
			int op1 = scanner.nextInt();
			
			
			System.out.print("\nDonner l'operation : ");
			String operation;
			
			// Verification de l'operation
			do {
				operation = scanner.nextLine();
			}while(!(operation.equals("*")) && !(operation.equals("+")) && !(operation.equals("-")) && !(operation.equals("/")));
			
			System.out.print("\nDonner le deuxieme operateur : ");
			int op2 = scanner.nextInt();
			
			scanner.close();
			
			
			// Envoi des données au serveur
			pw.println(op1);
			pw.println(op2);
			pw.println(operation);
			
			// Utlisation du InputStreamReader et BufferedReader pour pouvoir lire tous les caractères reçus du serveur
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bfr = new BufferedReader(isr);
			
			System.out.println("\n" + op1 + " " + operation + " " + op2 + " = " + bfr.readLine());
			
			
			// Deconnexion et libération des ressources
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
