package de.firma.server;

import de.firma.protocol.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	public static void main(String[] args) {
		int portNumber = args.length > 0 ? Integer.parseInt(args[0]) : 8000;
		Protocol protocol = new Protocol();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Shutting down")));

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			System.out.println("Listening to connections on port " + portNumber);
			while (true) {
				try (Socket clientSocket = serverSocket.accept();
						BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
					
					String response = "";					
					if (!protocol.isInProgress()) {
						response  = protocol.startConversation();
					} else {
						response = protocol.processRequest(in);
					}

					// Sends the server reply by using the HTTP 1.1 protocol
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
					out.print("HTTP/1.1 200 \r\n"); // Version & status code
					out.print("Content-Type: text/plain\r\n"); // The type of data
					out.print("Connection: close\r\n"); // Will close stream
					out.print("\r\n"); // End of headers
					out.print(response + "\r\n");
					// Close socket, breaking the connection to the client, and
					// closing the input and output streams
					out.close(); // Flush and close the output stream
					in.close(); // Close the input stream
					clientSocket.close(); // Close the socket itself
					if (!protocol.isInProgress()) {
						break;
					}
				}
			} // Loop to wait for the next connection
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} 
	}
}
