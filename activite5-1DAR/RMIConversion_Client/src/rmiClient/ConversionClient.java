package rmiClient;
import rmiService.IConversion;
import java.rmi.Naming;

public class ConversionClient {
 public static void main(String[] args) {
     try {
         String url = "//localhost/ConversionService";

         IConversion conversionService = (IConversion) Naming.lookup(url);

         // Appel de la méthode du service RMI
         double montantConverti = conversionService.convertirMontant(100.0);

         System.out.println("Montant converti : " + montantConverti);
     } catch (Exception e) {
         System.err.println("Erreur du client : " + e.toString());
         e.printStackTrace();
     }
 }
}

