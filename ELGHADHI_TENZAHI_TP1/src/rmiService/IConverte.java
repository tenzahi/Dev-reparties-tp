package rmiService;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConverte extends Remote {
	 double convertirMontant(double montant) throws RemoteException ;
}
