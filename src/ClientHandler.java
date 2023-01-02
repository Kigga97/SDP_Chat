import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private String username;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, String username) {
        this.clientSocket = socket;
        this.username = username;
    }

    public void run() {
        try {
            // Create a print writer to send messages to the client
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Create a buffered reader to read messages from the client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Send the message to all clients
                for (String username : ChatServer.getHandlers().keySet()) {
                    ClientHandler handler = ChatServer.getHandlers().get(username);
                    handler.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
