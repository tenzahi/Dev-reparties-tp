package rmiService;

//ConversionImpl.java

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConversionImpl extends UnicastRemoteObject implements IConversion {
 protected ConversionImpl() throws RemoteException {
     super();
 }

 @Override
 public double convertirMontant(double mt) throws RemoteException {
     // Implémentation de la conversion ici
     return mt * 2;  // Exemple simple : doubler le montant
 }
}
