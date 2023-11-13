package SeverPackage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args){
		System.out.println("Je suis un server");
		
		try {
			// réserver un port et attendre qu'un utilisateur se connecte.
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("J'attend un client");
			Socket socket = serverSocket.accept();
			
			System.out.println("Un client est connecté");
			
			// Le 'InputStream' est utilisé pour lire du stream avec l'utlisatuer.
			InputStream is = socket.getInputStream();
			
			//Le 'OutputStream' est utilisé pour ecrire dans le stream avec l'utlisateur.
			OutputStream os = socket.getOutputStream();
			
			// Traitement
			int nb = is.read();
			int resultat = nb*5;
			
			// Ecrire les resultats dans le stream.
			os.write(resultat);
			resultat = nb + 5;
			os.write(resultat);
			
			
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}