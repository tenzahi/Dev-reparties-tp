package ClientPackage;

import java.io.IOException; 
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	public static void main(String[] args){
		System.out.println("Je suis un client");
		try{
			// Connecter au serveur en utilisant l'adresse IP cette fois + port.
			Socket socket = new Socket("192.168.1.9", 1234);
			System.out.println("Je suis connecté");
			
			// Le 'InputStream' est utilisé pour lire du stream avec le serveur.
			InputStream is = socket.getInputStream();
			
			// Le 'OutputStream' est utilisé pour ecrire dans le stream avec le serveur.
			OutputStream os = socket.getOutputStream();
			
			
			// Donner la main à l'utilisateur pour saisir une valeur
			int nb;
			Scanner scanner = new Scanner(System.in);
			nb = scanner.nextInt();
			scanner.close();
			
			// Ecrire la valuer dans le stream.
			os.write(nb);
			
			// Lire la resultat.
			System.out.println("Resultat multiplication : " + is.read());
			System.out.println("Resultat addition: " + is.read());
			
			
			socket.close();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}