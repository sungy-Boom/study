package com.study;

import com.study.helloworld.GreeterGrpc;
import com.study.helloworld.HelloReply;
import com.study.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by SunGuiyong
 * on 2018/3/22.
 */
public class HelloProtoClient {
    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub stub;


    public HelloProtoClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();

        stub = GreeterGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = stub.sayHello(request);
        System.out.println(response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        HelloProtoClient client = new HelloProtoClient("127.0.0.1", 50051);
        for (int i = 0; i < 5; i++) {
            client.greet("world:" + i);
        }
        client.shutdown();
    }
}
