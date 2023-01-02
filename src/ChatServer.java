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

        ServerSocket serverSocket = new ServerSocket(8000); //Listener

        while (true) {

            Socket clientSocket = serverSocket.accept(); // Accept conn


            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Buffered reader to read client messages


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
