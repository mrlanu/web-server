package io.lanu.web_server;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class Connection implements Closeable {

    private final Socket socket;
    private final BufferedReader in;
    private final OutputStream out;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.out = socket.getOutputStream();
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendResponse(String page) throws IOException{

            out.write(page.getBytes());

    }

    public void readRequest() throws IOException, ClassNotFoundException{
        String LINE_SEPARATOR = System.getProperty("line.separator");
        String line;
        StringBuilder builder = new StringBuilder();
        while (!(line = in.readLine()).isEmpty()) {
            builder.append(line);
            builder.append(LINE_SEPARATOR);
        }
        System.out.println(builder.toString());
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    @Override
    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
}
