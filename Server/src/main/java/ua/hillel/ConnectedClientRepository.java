package ua.hillel;

import java.util.ArrayList;
import java.util.List;

public class ConnectedClientRepository {

	
	private final List<ClientSocketThread> connectedClients = new ArrayList<>();
	
	
	public List<ClientSocketThread> getAllClients(){
		return List.copyOf(connectedClients);
	}
	
	
	public void add(ClientSocketThread clientST) {
		this.connectedClients.add(clientST);
	}
	
	
	public void remove(ClientSocketThread clientST) {
		this.connectedClients.remove(clientST);
	}
	
	
	public void mailing(String message) {
		this.connectedClients.forEach(null);
	}
	
}
