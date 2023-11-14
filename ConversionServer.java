package rmiServer;
import java.rmi.Naming;
import rmiService.ConversionImpl;
import rmiService.IConversion;

public class ConversionServer {
 public static void main(String[] args) {
     try {
         IConversion conversionService = new ConversionImpl();
         Naming.rebind("//localhost/ConversionService", conversionService);
         System.out.println("Serveur RMI prêt...");
     } catch (Exception e) {
         System.err.println("Erreur du serveur : " + e.toString());
         e.printStackTrace();
     }
 }
}


