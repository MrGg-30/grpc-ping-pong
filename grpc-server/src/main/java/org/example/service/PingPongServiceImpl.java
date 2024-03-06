package org.example.service;

import io.grpc.stub.StreamObserver;
import org.example.stubs.ping_pong.PingPongRequest;
import org.example.stubs.ping_pong.PingPongResponse;
import org.example.stubs.ping_pong.PingPongServiceGrpc;

import java.util.logging.Logger;

public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {

    private static final Logger LOGGER = Logger.getLogger(PingPongServiceImpl.class.getName());

    @Override
    public void pingPong(PingPongRequest request, StreamObserver<PingPongResponse> responseObserver) {
        LOGGER.info("[Server] received request: " + request.getMessage());

        PingPongResponse response = PingPongResponse.newBuilder()
                .setMessage("Pong")
                .build();

        responseObserver.onNext(response);
        LOGGER.info("[Server] sending response: " + response.getMessage());
        responseObserver.onCompleted();
    }
}
