package SeverPackage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import calculatrice.Calculatrice;

public class Server{

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("je suis un server  attend la connexion d'un client  ! ");
				
				Socket s = ss.accept();
				System.out.println("un client est connecte !!");
				
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Calculatrice c = (Calculatrice) ois.readObject();
			
			int a = c.getA();
			int b = c.getB();
			String op = c.getOp();
			int res = 0;
			
			switch(op) {
			case "+": res = a+b;
				break;
			case "-": res = a-b;
				break;
			case "*": res = a*b;
				break;
			case "/": res = a/b;
				break;
			}
			
			System.out.println("le client a envoye l'operation suivant "+a+" "+op+" "+b+" = "+res);
			
			OutputStream out = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
		    dos.writeInt(res);
				
				s.close();
				ss.close();
	}
}
