package com.kd.wyq.mycatTable.SshUtil;

import com.kd.wyq.mycatTable.utils.PropertiesKeyset;
import com.sshtools.j2ssh.SshClient;

import java.io.IOException;

public class CopyFile {

    PropertiesKeyset propertiesKeyset = new PropertiesKeyset();//实例化获取属性文件内容对象

    public void copyFile(String tempFilePath, SshClient client){
        //根据teamFilePath获取操作后的配置文件 ，通过client传输到mycat配置目录中进行配置文件的替换

        try {

                client.openSftpClient().put(tempFilePath,propertiesKeyset.getProperties().get("MYCAT_HOME").toString()+"schema.xml");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
