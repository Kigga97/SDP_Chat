import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NameServiceTest {

    private Map<Integer, Socket> pinToSocket = new HashMap<>();

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(65443)) {
            while (true) {
                Socket socket = serverSocket.accept();

                int pin = socket.getInputStream().read();
                pinToSocket.put(pin, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //USAR para definir o PIN no cliente
    public Socket getSocket(int pin) {
        return pinToSocket.get(pin);
    }
}