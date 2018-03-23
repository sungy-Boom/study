package com.study;

import com.study.person.bean.Person;
import com.study.person.service.SavePersonInfoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by SunGuiyong
 * on 2018/3/22.
 */
public class HelloProtoClient {
    private final ManagedChannel channel;
    private final SavePersonInfoGrpc.SavePersonInfoBlockingStub localStub;

    public HelloProtoClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();

        //初始化
        localStub = SavePersonInfoGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(Person.PersonInfo personInfo) {
        Person.Result result = localStub.savePerson(personInfo);
        System.out.println("work code :" + result.getCode());
        System.out.println("other information :" + result.getOtherInfo());
    }

    public static void main(String[] args) throws InterruptedException {
        HelloProtoClient client = new HelloProtoClient("127.0.0.1", 50051);
        for (int i = 0; i < 5; i++) {
            Person.PersonInfo p = Person.PersonInfo.newBuilder()
                    .setPersonName("test" + i)
                    .setAge(i)
                    .setSex("男")
                    .setClazz(Person.TheClass.newBuilder()
                            .setClassName("终极一班")).build();
            client.greet(p);
        }
        client.shutdown();
    }
}
