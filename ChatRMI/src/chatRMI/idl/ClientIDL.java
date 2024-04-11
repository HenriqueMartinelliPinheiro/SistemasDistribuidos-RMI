package chatRMI.idl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIDL extends Remote{
  void receiveMessage(String message, int id) throws RemoteException;
}
