package ua.hillel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		ConnectedClientRepository repository = new ConnectedClientRepository();
		MessageBroadcaster broadcaster = new MessageBroadcaster(repository);
		
		try (ServerSocket serverSocket = new ServerSocket(8080)) {
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				ClientSocketThread connectedClient = new ClientSocketThread(socket, broadcaster);
				
				Thread clientThread = new Thread(connectedClient);
				
				clientThread.setUncaughtExceptionHandler((t, e) -> repository.remove(connectedClient));
			
	
				
				clientThread.setDaemon(true);
				clientThread.start();
				
				
				repository.add(connectedClient);
				broadcaster.mailingToAll(connectedClient.name + " connected");
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
