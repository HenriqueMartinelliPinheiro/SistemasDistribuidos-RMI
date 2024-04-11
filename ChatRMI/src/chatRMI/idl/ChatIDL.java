package chatRMI.idl;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatIDL extends Remote{
	String sendMessage(int id, String message, int senderId) throws RemoteException;
	String connect(String ip, String port, int id) throws RemoteException;
	void sendMessageAll(String message, int id) throws RemoteException;
	String disconnect(int id) throws RemoteException;
}
