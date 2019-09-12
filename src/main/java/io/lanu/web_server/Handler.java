package io.lanu.web_server;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;

public class Handler implements Callable<String> {

    private Socket socket;

    Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String call() {
        try (Connection connection = new Connection(socket)){
            connection.readRequest();
            Date today = new Date();
            String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
            connection.sendResponse(httpResponse);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "OK";
    }

}
