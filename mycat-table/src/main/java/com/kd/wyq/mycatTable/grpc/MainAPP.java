package com.kd.wyq.mycatTable.grpc;

import com.kd.wyq.mycatTable.grpc.server.MycatServer;

public class MainAPP {


    public static void main(String[] args) {

        MycatServer server = new MycatServer();

            /*
            String commond = args[0];

            if (commond.equals("start"))
                server.start();
            else if (commond.equals("stop"))
                server.stop();
            */


        server.start();



    }



}
