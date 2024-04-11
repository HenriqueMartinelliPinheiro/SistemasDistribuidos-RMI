package chatRMI.server;

public class ServerClient {

	private String ip;
	private String port;
	private int id;
	
	public ServerClient(String ip, String port, int id) {
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

	public int getId() {
		return id;
	}
}
