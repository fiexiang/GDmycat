package com.kd.wyq.mycatTable.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kd.wyq.mycatTable.SshUtil.BackConfigureFile;
import com.kd.wyq.mycatTable.SshUtil.CopyFile;
import com.kd.wyq.mycatTable.SshUtil.SshUtil;
import com.kd.wyq.mycatTable.model.HandleTable;
import com.kd.wyq.mycatTable.model.Table;
import com.kd.wyq.mycatTable.service.TableService;
import com.kd.wyq.mycatTable.utils.Dom4jUtil;
import com.kd.wyq.mycatTable.utils.JDBCUtil;
import com.sshtools.j2ssh.SshClient;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.wyq.mycat.MycatGrpc;
import io.grpc.wyq.mycat.MycatReply;
import io.grpc.wyq.mycat.MycatRequest;

public class TableServiceImpl implements TableService {

    Dom4jUtil dom4jUtil = new Dom4jUtil();

    SshUtil sshUtil = new SshUtil();

    CopyFile copy = new CopyFile();

    BackConfigureFile bak = new BackConfigureFile();

    //添加表具体实现逻辑
    public String addTable(Table table) {

        SshClient client = new SshUtil().getSftpClient();

        String tempath = dom4jUtil.addTableToConfigureFile(table,client);

        //备份原来的schema.xml文件
        bak.backUpConfiguteFile(client);

        //将临时目录中修改后的配置文件移动到mycat配置文件目录，覆盖原配置文件
        copy.copyFile(tempath,client);

        Boolean flag = sshUtil.restartMycat(client);

        if (flag) {

            try {

                Thread.sleep(10000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }

        String sql = " CREATE TABLE `"+table.getName()+"` " +
                "( `ID`  varchar(18) CHARACTER SET utf8 " +
                "COLLATE utf8_general_ci NOT NULL ," +
                "`TIME`  datetime NOT NULL )";

        JDBCUtil jdbcUtil = new JDBCUtil();

        jdbcUtil.DbUpdate(sql,table.getSchemaName(),new Object[]{});

        return "Mycat 添加表功能已经完成";
    }

    //删除表具体实现逻辑
    public String delTable(Table table) {

        SshClient client = new SshUtil().getSftpClient();

        Dom4jUtil dom4jUtil = new Dom4jUtil();

        String tempath = dom4jUtil.deleteTableToConfigureFile(table,client);

        String sql = "DROP TABLE IF EXISTS `"+table.getName()+"`;";

        JDBCUtil jdbcUtil = new JDBCUtil();

        jdbcUtil.DbUpdate(sql,table.getSchemaName(),new Object[]{});

        //备份原来的schema.xml文件
        bak.backUpConfiguteFile(client);

        //将临时目录中修改后的配置文件移动到mycat配置文件目录，覆盖原配置文件
        copy.copyFile(tempath,client);

        Boolean flag = sshUtil.restartMycat(client);

        if (flag)
            System.out.println("Mycat 重启完成");
        try {

            Thread.sleep(10000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        return "Mycat 删除表功能已经完成";
    }

    //修改表具体实现逻辑
    public String updateTable(Table table) {
        return null;
    }

    public int handleTable(HandleTable table) {
        //获取操作表的SQL文
        String sql = table.getHandle();
        //获取表对象
        Table t = table.getTable();

        JDBCUtil jdbcUtil = new JDBCUtil();

        int result = jdbcUtil.DbUpdate(sql,t.getSchemaName(),new Object[]{});

        return result;
    }

    public static void main(String[] args) throws InterruptedException {

       /* String rrr = "{\"handle\":\"alter table wyq_test drop index `time_index`\",\"table\":{\"name\":\"wyq_test\",\"schemaName\":\"FUJIAN\"}}";

        MycatRequest request = MycatRequest.newBuilder().setParmsList(rrr).build(); //配置请求对象

        Channel channel = ManagedChannelBuilder.forAddress("localhost",15001).usePlaintext(true).build(); //实例化通道，请求完成后会自动关闭

        MycatGrpc.MycatBlockingStub blockingStub = MycatGrpc.newBlockingStub(channel);

        MycatReply response = blockingStub.handleTable(request);//发送请求

        System.out.println(response.getMessage()); //输出结果

        Thread.sleep(5000);*/

        Table table = new Table();
        table.setSchemaName("FUJIAN");
        table.setName("wyq_test");

        HandleTable t = new HandleTable();
        t.setTable(table);

        String handle = "ALTER TABLE wyq_test ADD COLUMN age int(1) COMMENT '年龄'";
        t.setHandle(handle);

        System.out.println(JSON.toJSONString(t));
    }

}



