import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        // Create a client socket and connect to the server
        Socket socket = new Socket("localhost", 8000);

        // Create a print writer to send messages to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Send the PIN and username to the server
        out.println("1234");
        out.println("Alice");

        // Create a buffered reader to read messages from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Create a buffered reader to read messages from the user
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            // Send the message to the server
            out.println(userInput);

            // Read and print the server's response
            System.out.println(in.readLine());
        }
    }
}
