package com.kd.wyq.mycatTable.SshUtil;

import com.kd.wyq.mycatTable.utils.PropertiesKeyset;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.session.SessionChannelClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackConfigureFile {

    PropertiesKeyset propertiesKeyset = new PropertiesKeyset(); //实例化获取属性文件内容对象

    public Boolean backUpConfiguteFile(SshClient client){ //备份mycat配置文件

        Boolean bakResult = false; //实例化备份结果标志对象

        try {

                Date date = new Date();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm");
                //获取当前日期信息
                String dataText = sdf.format(date);
                //拼接备份shell 命令
                String backUpCMD = "cp "+propertiesKeyset.getProperties().get("MYCAT_HOME")+"schema.xml  "+propertiesKeyset.getProperties().get("MYCAT_HOME")+"schema.xml.bak-"+dataText;
                //打开连接通道
                SessionChannelClient sessionChannelClient = client.openSessionChannel();
                //执行命令
                bakResult = sessionChannelClient.executeCommand(backUpCMD);
                //关闭通道
                sessionChannelClient.close();


        } catch (IOException e) {

            e.printStackTrace();

        }

        return bakResult;

    }

}
