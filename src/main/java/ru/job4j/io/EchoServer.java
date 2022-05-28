package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("Exit")) {
                            server.close();
                            System.out.println("server closed");
                        } else if (str.contains("Hello")) {
                            System.out.println("Hello");
                        } else if (str.contains("/?msg=")) {
                            System.out.println(str.substring(10, str.length() - 9));
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("IOException", e);
        }
    }
}