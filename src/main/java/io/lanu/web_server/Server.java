package io.lanu.web_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT =2019;

    public void start(){

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try (ServerSocket server = new ServerSocket(PORT)){
            System.out.println("Server has been started.");
            while (true) {
                Socket socket = server.accept();
                Handler handler = new Handler(socket);
                executorService.submit(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
