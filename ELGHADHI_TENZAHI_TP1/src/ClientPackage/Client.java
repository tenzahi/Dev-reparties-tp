package ClientPackage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import calculatrice.Calculatrice;

public class Client {

public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket s = new Socket("localhost",1234);
		System.out.println("je suis un  client connecte ! ");

		Scanner scaner = new Scanner(System.in);	
		
		System.out.print("donner  un entier  : ");
		int a = scaner.nextInt();
		System.out.print("taper un operateur  + - * / : ");
		String op = scaner.next();
		System.out.print("donner le dexuiem entier  : ");
        int b =scaner.nextInt();  
        
        Calculatrice c = new Calculatrice(a,op,b);
		OutputStream out = s.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(c);
		oos.flush();
		
		InputStream is = s.getInputStream();
		DataInputStream dis = new DataInputStream(is);
	    int res = dis.readInt(); 
		
		System.out.println("la resultat retourner par le server est : "+res);
		
		s.close();
	}}