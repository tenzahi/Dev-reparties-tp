package act3_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	public static void main(String[] args) {
		new Server().start();
	}
	
	private int numClient = 1;
	
	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(1234)) {
			while(true) {
				
				Socket socket = serverSocket.accept();
				new ClientProcess(socket, numClient++).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public class ClientProcess extends Thread {
		Socket socket;
		int ID;
		
		public ClientProcess(Socket socket, int ID) {
			super();
			this.socket = socket;
			this.ID = ID;
		}
		
		public void run() {
			try {
				System.out.println("Client ID " + ID + " (" + socket.getRemoteSocketAddress() + ") est connecté");
				
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				
				pw.println("Vous etes le client " + ID);
				
				Operation op = (Operation) new ObjectInputStream(socket.getInputStream()).readObject();
				
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
				
				new ObjectOutputStream(socket.getOutputStream()).writeObject(op);
				
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
