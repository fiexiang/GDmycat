package com.kd.wyq.mycatTable.grpc.server;

import com.kd.wyq.mycatTable.utils.PropertiesKeyset;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.rmi.server.ExportException;

public class MycatServer {

    Server server = ServerBuilder
            .forPort(getGport())
            .addService(new MycatGrpcImpl()).build(); //初始化服务端，配置端口，添加服务



    public  int  getGport(){

        PropertiesKeyset propertiesKeyset = new PropertiesKeyset();

        int gPort = Integer.parseInt(propertiesKeyset.getProperties().get("G_PORT").toString());

        return gPort;

    }

    public void start() {

        try {

            this.server.start();

            System.out.println("=========Grpc Server for Mycat started,using port : "+ getGport() +" ==========");

            blockUntilShutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stop() {

        try {

            if (this.server!=null) {

                this.server.shutdown();

                System.out.println("=========Grpc Server for Mycat stopped! ==========");

                return;

            }
            else {

                System.out.println("There are no-one server has running!");

                return;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //防止服务关闭
    private void blockUntilShutdown() throws InterruptedException {
        if (server!=null){
            server.awaitTermination();;
        }
    }

}
