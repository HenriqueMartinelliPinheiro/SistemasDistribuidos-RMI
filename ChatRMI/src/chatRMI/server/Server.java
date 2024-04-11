package chatRMI.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import chatRMI.idl.ChatIDL;

public class Server {
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname", "localhost");
			//aqui o objeto remoto ainda não é stub
			Chat chat = new Chat();
			ChatIDL stub = (ChatIDL)UnicastRemoteObject.exportObject(chat, 0);
			
			//executa o servico RMIREGISTRY
			Registry registro = LocateRegistry.createRegistry(12345);
			
			//publica o objeto remoto
			registro.bind("Chat", stub);
			System.out.println("Se chegamos aqui está funcionando.");
			
		} catch (Exception e) {
			System.out.println("Erro");
			e.printStackTrace();
		}
	}
}
