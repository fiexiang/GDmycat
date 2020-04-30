package com.kd.wyq.mycatTable.grpc.server;

import com.kd.wyq.mycatTable.utils.PropertiesKeyset;
import io.grpc.Server;
import io.grpc.ServerBuilder;


//该类是Grpc服务构建类，通过该类可以实现对Grpc Server的创建，启动，停止等。
public class MycatServer {

    //对server的构建并将Grpc服务接口对象注入
    Server server = ServerBuilder
            .forPort(getGport())
            .addService(new MycatGrpcImpl()).build(); //初始化服务端，配置端口，添加服务



    //通过配置文件获取GRPC服务使用的端口
    public  int  getGport(){

        //实例化获取配置文件信息对象
        PropertiesKeyset propertiesKeyset = new PropertiesKeyset();

        //提取PORT
        int gPort = Integer.parseInt(propertiesKeyset.getProperties().get("G_PORT").toString());

        //返回
        return gPort;

    }

    //启动server方法
    public void start() {

        try {

            //启动server
            this.server.start();

            //输出提示信息
            System.out.println("=========Grpc Server for Mycat started,using port : "+ getGport() +" ==========");

            //防止server关闭
            blockUntilShutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //停止server方法
    public void stop() {

        try {

            if (this.server!=null) {

                //停止server
                this.server.shutdown();

                //输出提示信息
                System.out.println("=========Grpc Server for Mycat stopped! ==========");

                //返回
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
