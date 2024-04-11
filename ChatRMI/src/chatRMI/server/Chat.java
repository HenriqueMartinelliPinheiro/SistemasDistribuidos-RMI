package chatRMI.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chatRMI.idl.ChatIDL;
import chatRMI.idl.ClientIDL;

public class Chat implements ChatIDL{
	private List<ServerClient> clients = new ArrayList<ServerClient>();
	
	@Override
	public String sendMessage(int id, String message, int senderId) throws RemoteException {
		for(ServerClient client : clients) {
			if (client.getId()==id) {
				
				try {
					Registry registry = LocateRegistry.getRegistry(client.getIp(), Integer.parseInt(client.getPort()));
					ClientIDL chatClient = (ClientIDL)registry.lookup("ChatClient");
					
					chatClient.receiveMessage(message, senderId);
					return "Mensagem Enviada";
					
				} catch (Exception e) {
					e.printStackTrace();
					return "Mensagem Não Enviada";
				}
			}
		}
		return "ID não encontrado.";
	}
	
	public String connect(String ip, String port, int id) {
		
		ServerClient cl = new ServerClient(ip, port, id);
		this.clients.add(cl);
		
		System.out.println("Cliente Conectado: "+ id);
		return ("Cliente conectado");
	}
	
	public void sendMessageAll(String message, int senderId) {
		for(ServerClient client : clients) {
			if (client.getId()!=senderId) {
				
				try {
					Registry registry = LocateRegistry.getRegistry(client.getIp(), Integer.parseInt(client.getPort()));
					ClientIDL chatClient = (ClientIDL)registry.lookup("ChatClient");
					chatClient.receiveMessage(message, senderId);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String disconnect(int id) {
		Iterator<ServerClient> iterator = clients.iterator();
		
        while (iterator.hasNext()) {
            ServerClient client = iterator.next();
            
            if (client.getId() == id) {
                iterator.remove();
                System.out.println("Cliente Desconectado: "+id);
                
        		return ("Cliente Desconectado");
            }
        }
        System.out.println(clients.size());
        return "Erro ao Desconectar";
	}
}
