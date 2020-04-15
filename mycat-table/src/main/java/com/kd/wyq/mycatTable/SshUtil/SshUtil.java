package com.kd.wyq.mycatTable.SshUtil;

import com.kd.wyq.mycatTable.utils.PropertiesKeyset;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;

import java.io.IOException;
import java.util.Map;

public class SshUtil {


    PropertiesKeyset propertiesKeyset = new PropertiesKeyset();//实例化获取属性文件内容对象

    Map<String,Object> map = propertiesKeyset.getProperties();//获取mycat属性文件内容

    public SshClient getSftpClient(){//过去sftp连接client

        String ip = "";             //mycat服务所在ip
        String catalogue = "";      //mycat配置文件所在目录
        String fileName = "";       //mycat配置文件名称

        String tempPath = "";

        ip = map.get("IP").toString();

        catalogue = map.get("MYCAT_HOME").toString();

        fileName = "schema.xml";

        SshClient client = new SshClient();     //实例化ssh客户端对象

        boolean flag = false;

        try {

            String SSH_PORT = map.get("SSH_PORT").toString();

            client.connect(ip, Integer.parseInt(SSH_PORT));          //建立ssh连接

            PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();

            pwd.setUsername(map.get("UserName").toString());

            pwd.setPassword(map.get("PassWord").toString());

            int result = client.authenticate(pwd);

            if(result == AuthenticationProtocolState.COMPLETE){

                    flag = true;

            }

        }catch (Exception e){

        }
        if (flag){

            return client;

        }else{

            return null;

        }

    }

    public Boolean restartMycat(SshClient client){

        String restartCMD = map.get("MYCAT_BIN").toString()+"mycat restart"; //重启mycat服务命令

        Boolean result = false;  //声明命令执行结果标志对象

        try {

            result = client.openSessionChannel().executeCommand(restartCMD); //执行命令

        } catch (IOException e) {

            e.printStackTrace();

        }

        return  result;
    }


}
