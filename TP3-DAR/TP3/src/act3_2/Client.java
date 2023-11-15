package act3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args){
		try {
			Socket socket = new Socket("localhost", 1234);
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Donner le premier operateur : ");
			int op1 = scanner.nextInt();
			
			System.out.print("\nDonner l'operation : ");
			String operation;
			
			do {
				operation = scanner.nextLine();
			}while(!(operation.equals("*")) && !(operation.equals("+")) && !(operation.equals("-")) && !(operation.equals("/")));
	
			System.out.print("\nDonner le deuxieme operateur : ");
			int op2 = scanner.nextInt();
			
			scanner.close();
			
			String output = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
			
			System.out.println(output);
			
			Operation op = new Operation(op1, op2, operation.charAt(0));
			
			new ObjectOutputStream(socket.getOutputStream()).writeObject(op);
			
			op = (Operation) new ObjectInputStream(socket.getInputStream()).readObject();
			
			System.out.println("\n" + op.op1 + " " + op.operation + " " + op.op2 + " = " + op.resultat);
			
			
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
