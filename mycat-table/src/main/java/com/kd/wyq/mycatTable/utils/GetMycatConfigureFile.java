package com.kd.wyq.mycatTable.utils;

import com.sshtools.j2ssh.SshClient;

import java.io.*;
import java.util.Map;

public class GetMycatConfigureFile {

    PropertiesKeyset propertiesKeyset = new PropertiesKeyset(); //声明获取属性文件内容对象

    Map<String,Object> map = propertiesKeyset.getProperties(); //获取mycat属性文件内容，返回值为map

    public String copyMycatConfigureFile(SshClient client){ //复制mycat配置文件到java运行临时目录

        String tempPath = ""; //声明临时路径

        String configurePath = map.get("MYCAT_HOME").toString()+"schema.xml"; //声明mycat服务配置文件所在路径

        String folder=System.getProperty("java.io.tmpdir"); //获取java运行文件临时目录

        File dir = new File(folder); //声明临时目录文件夹

        if(!dir.exists()){

            dir.mkdirs(); //如果不存在，创建该文件夹。没啥意义，就是肌肉记忆，到这就自己写出来了。

            }

        File file = new File(folder+"mycat-schema.xml"); //创建文件，文件内容是mycat配置文件内容。

        if(!file.exists()){

            try {

                file.createNewFile(); //创建

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        tempPath = folder+"mycat-schema.xml"; //赋值mycat配置文件临时存放路径

        OutputStream os = null; //声明输出流对象os

        try {
            os = new FileOutputStream(tempPath);

            client.openSftpClient().get(configurePath,os); //拷贝mycat配置文件到临时路径

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }


        return tempPath;

    }


}
