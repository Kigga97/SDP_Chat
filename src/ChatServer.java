import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final String PIN = "1234";
    private static Map<String, ClientHandler> handlers = new HashMap<>();

    public static synchronized Map<String, ClientHandler> getHandlers() {
        return handlers;
    }
    public static void main(String[] args) throws Exception {
        // Create a server socket to listen for incoming connections
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            // Accept an incoming connection
            Socket clientSocket = serverSocket.accept();

            // Create a buffered reader to read messages from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Read the PIN and username from the client
            String pin = in.readLine();
            String username = in.readLine();

            // Check the PIN
            if (pin.equals(PIN)) {
                // Create a client handler to manage the connection
                ClientHandler handler = new ClientHandler(clientSocket, username);

                // Add the handler to the map of handlers
                handlers.put(username, handler);

                // Create a thread to handle the connection
                Thread thread = new Thread(handler);

                // Start the thread
                thread.start();
            } else {
                // If the PIN is incorrect, close the socket
                clientSocket.close();
            }
        }
    }
}
