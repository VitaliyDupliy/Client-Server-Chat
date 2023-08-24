package ua.hillel;

import java.io.IOException;

public class MessageBroadcaster {

	private final ConnectedClientRepository repository;

	public MessageBroadcaster(ConnectedClientRepository repository) {
		this.repository = repository;
	}
	
	public void mailingToAll(String message) {
		this.repository.getAllClients().forEach(client -> {
			try {
				client.sendMessage(message);
			} catch (IOException e) {
				System.out.println("Error sending message");
			}
		});
	}
	
}
