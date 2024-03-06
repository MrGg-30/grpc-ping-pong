package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.service.PingPongServiceImpl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageServer {

    private Server server;
    private static final Logger LOGGER = Logger.getLogger(MessageServer.class.getName());

    public void startServer() throws InterruptedException {
        int port = 8082;
        try {
            server = ServerBuilder
                    .forPort(port)
                    .addService(new PingPongServiceImpl())
                    .build()
                    .start();
            LOGGER.log(Level.INFO, "Server started on port 8082");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Server didn't start", e);
        }
        server.awaitTermination();
    }

    public static void main(String[] args) throws InterruptedException {
        MessageServer messageServer = new MessageServer();
        messageServer.startServer();
    }
}