package rmiclient;
import java.rmi.Naming;
import java.util.Scanner;
import rmiService.ICon;

public class RmiClient {

	public static void main(String[] args) {
		
		try {
			IConverte stub = (IConverte) Naming.lookup("rmi://localhost:1099/BK");
			Scanner sc = new Scanner(System.in);
			System.out.print("commbier d'euro vous voulez changer au dinar : ");
			double mt = sc.nextDouble();
			System.out.println("votre montant en Euro est  : "+mt +" €");
			System.out.println("votre montant en Dinar est : "+stub.convertirMontant(mt)+" DT");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

