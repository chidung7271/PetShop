package qltc.BussinessLogicLayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private static final String serverAddress = "127.0.0.1";
    private static final int port = 12345;
    public static Socket socket;
    public static PrintWriter out_to_server;
    public static BufferedReader in_from_server;

    public static void ClientConnection() throws IOException {
        socket = new Socket(serverAddress, port);
        out_to_server = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        in_from_server = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public static void close() throws IOException {
        if (socket != null && !socket.isClosed()) {
            System.out.println("Closing the client socket");
            socket.close();
        }
        if (out_to_server != null) {
            out_to_server.close();
        }
        if (in_from_server != null) {
            in_from_server.close();
        }
    }
}
