package lk.udith.chatapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);

        while (true){
            System.out.println("Waiting for client ...");
            Socket socket = serverSocket.accept();
            System.out.println("A new Client Connected");
            ClientHandler clientHandler=new ClientHandler(socket,clientHandlers);
            clientHandlers.add(clientHandler);
            clientHandler.start();
        }
    }
}
