package com.kd.wyq.mycatTable.grpc;

import com.kd.wyq.mycatTable.grpc.server.MycatServer;

//该类为入口类
public class MainAPP {


    //入口类main方法
    public static void main(String[] args) {

        //实例化server
        MycatServer server = new MycatServer();

            /*
            String commond = args[0];

            if (commond.equals("start"))
                server.start();
            else if (commond.equals("stop"))
                server.stop();
            */

            //启动server
            server.start();



    }



}
