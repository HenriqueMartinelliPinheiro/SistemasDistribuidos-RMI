package chatRMI.client;

import chatRMI.idl.ClientIDL;

public class Client implements ClientIDL{
		private String ip;
		private String port;
		private int id;
		
		public Client(String ip, String port, int id) {
			this.ip = ip;
			this.port = port;
			this.id = id;
		}
		
		public String getIp() {
			return ip;
		}	
		
		public String getPort() {
			return port;
		}	
		
		public void sendMessage(String message) {
			try {
				
				System.out.println("Mensagem Enviada");

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void receiveMessage(String message, int senderId) {
			System.out.println("Mensagem: "+ message + " \n Remetente:" + senderId+ "\n");
		}
}
