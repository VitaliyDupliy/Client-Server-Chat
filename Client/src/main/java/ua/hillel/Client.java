package ua.hillel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 8080); Scanner scanner = new Scanner(System.in)) {

						
			Thread fromServerThread = new Thread(new Runnable() {

				@Override
				public void run() {

					while (true) {
						try {
							System.out.println(new DataInputStream(socket.getInputStream()).readUTF());
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
				}
			});

			fromServerThread.setDaemon(true);
			fromServerThread.start();

			while (true) {
				String text = scanner.nextLine();

				if (text.equals("exit")) {
					
					break;
				}
				new DataOutputStream(socket.getOutputStream()).writeUTF(text);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
