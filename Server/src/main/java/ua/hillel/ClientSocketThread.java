package ua.hillel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocketThread implements Runnable {

	String name = "Client: ";
	static int clientID = 1;
	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	private final MessageBroadcaster broadcaster;
	private boolean exit;

	public ClientSocketThread(Socket socket, MessageBroadcaster broadcaster) throws IOException {
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.broadcaster = broadcaster;
		this.name = name + clientID;
		clientID++;
		exit = false;
	}

	@Override
	public void run() {

		while (!exit) {
			try {
				String clientMessage = dis.readUTF();
				if (clientMessage.equals("exit")) {
					this.exit = true;
				}

				this.broadcaster.mailingToAll(clientMessage);

			} catch (IOException e) {

				throw new RuntimeException(e);
			}
		}

	}

	public void sendMessage(String message) throws IOException {
		this.dos.writeUTF(message);
	}

	public MessageBroadcaster getBroadcaster() {
		return broadcaster;
	}

	public Socket getSocket() {
		return socket;
	}

	

}
