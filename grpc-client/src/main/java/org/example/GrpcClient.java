package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.stubs.ping_pong.PingPongRequest;
import org.example.stubs.ping_pong.PingPongResponse;
import org.example.stubs.ping_pong.PingPongServiceGrpc;

import java.util.logging.Logger;

public class GrpcClient {

    private static final Logger LOGGER =  Logger.getLogger(GrpcClient.class.getName());

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:8082")
                .usePlaintext()
                .build();
        PingPongServiceGrpc.PingPongServiceBlockingStub stub = PingPongServiceGrpc.newBlockingStub(
                channel);

        PingPongRequest request = PingPongRequest.newBuilder()
                .setMessage("Ping")
                .build();
        LOGGER.info("[Client] sending request: " + request.getMessage());
        PingPongResponse response = stub.pingPong(request);

        LOGGER.info("[Client] received response: " +  response.getMessage());
        channel.shutdown();
    }
}
