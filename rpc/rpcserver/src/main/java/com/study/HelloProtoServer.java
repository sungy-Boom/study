//package com.study;
//
//import com.study.person.bean.Person;
//import com.study.person.service.SavePersonInfoGrpc;
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import io.grpc.stub.StreamObserver;
//
//import java.io.IOException;
//
///**
// * Created by SunGuiyong
// * on 2018/3/22.
// */
//public class HelloProtoServer {
//
//    private int port = 50051;
//    private Server server;
//
//    private void start() throws IOException {
//        server = ServerBuilder.forPort(port)
//                .addService(new PersonServiceImpl())
//                .build()
//                .start();
//        System.out.println("service start...");
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.err.println("*** shutting down gRPC server since JVM is shutting down");
//            HelloProtoServer.this.stop();
//            System.err.println("*** server shut down");
//        }));
//    }
//
//    private void stop() {
//        if (server != null) {
//            server.shutdown();
//        }
//    }
//
//    // block 一直到退出程序
//    private void blockUntilShutdown() throws InterruptedException {
//        if (server != null) {
//            server.awaitTermination();
//        }
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        final HelloProtoServer server = new HelloProtoServer();
//        server.start();
//        server.blockUntilShutdown();
//    }
//
//
//    private class PersonServiceImpl extends SavePersonInfoGrpc.SavePersonInfoImplBase {
//        public void savePerson(Person.PersonInfo personInfo, StreamObserver<Person.Result> result) {
//            System.out.println("person info :" + personInfo.toString());
//            Person.Result res = Person.Result.newBuilder().setCode(200).build();
//            result.onNext(res);
//            result.onCompleted();
//        }
//    }
//
//}
