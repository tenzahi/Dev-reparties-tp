package rmiService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConvertImpl extends UnicastRemoteObject implements IConverte{

	public ConvertImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public double convertirMontant(double montant) throws RemoteException {
		// TODO Auto-generated method stub
		return  montant*3.37;
	}



}
