package chatRMI.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import chatRMI.idl.ChatIDL;
import chatRMI.idl.ClientIDL;
import chatRMI.server.Chat;

public class ClientChat {
	public static void main(String[] args) {
		int myId = 1;
		int op = -1;
		Scanner in = new Scanner(System.in);
		
		try {
				System.setProperty("java.rmi.server.hostname", "localhost");
				Client client = new Client("localhost", "5000",myId);
				ClientIDL stub = (ClientIDL)UnicastRemoteObject.exportObject(client, 0);
				
				Registry registro = LocateRegistry.createRegistry(5000);
				
				registro.bind("ChatClient", stub);

				try {
					Registry registry = LocateRegistry.getRegistry("127.0.0.1",12345);
					ChatIDL chat = (ChatIDL)registry.lookup("Chat");
					String connectionStatus = chat.connect("127.0.0.1","5000", myId);
					System.out.println(connectionStatus);
					
					String message;
					
					while(op!=0) {
						System.out.println("O que deseja fazer: \n"
								+ "1 - Enviar Mensagem Privada \n"
								+ "2 - Enviar Para Todos \n"
								+ "0 - Sair");
						
						op = in.nextInt();
						in.nextLine();

						switch (op) {
						case 1:
							System.out.println("Digite o ID do destinatário:");
							int id = in.nextInt();
							in.nextLine();

							System.out.println("Digite a mensagem que quer enviar: ");
							message = in.nextLine();

							String status = chat.sendMessage(id, message, myId);
							System.out.println(status);
							break;
						
						case 2:
							System.out.println("Digite a mensagem que quer enviar: ");
							message = in.nextLine();
							chat.sendMessageAll(message, myId);
							break;
										
						default:
							String disconnectStatus = chat.disconnect(myId);
							System.out.println(disconnectStatus);
							break;
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
