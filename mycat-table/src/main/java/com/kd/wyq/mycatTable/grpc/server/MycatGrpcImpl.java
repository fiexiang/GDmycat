package com.kd.wyq.mycatTable.grpc.server;

import com.alibaba.fastjson.JSON;
import com.kd.wyq.mycatTable.model.HandleTable;
import com.kd.wyq.mycatTable.model.Table;
import com.kd.wyq.mycatTable.service.TableService;
import com.kd.wyq.mycatTable.service.impl.TableServiceImpl;
import io.grpc.stub.StreamObserver;
import io.grpc.wyq.mycat.MycatGrpc.MycatImplBase;
import io.grpc.wyq.mycat.MycatReply;
import io.grpc.wyq.mycat.MycatRequest;

public class MycatGrpcImpl extends MycatImplBase {

    //声明Table类接口服务对象
    TableService tableService = new TableServiceImpl();

    //添加表的实现逻辑接口
    @Override
    public void addTable(MycatRequest request, StreamObserver<MycatReply> responseObserver) {

        //获取请求JSON参数，JSON格式有严格要求。
        String addTableText = request.getParmsList();

        //将JSON转换为Table对象
        Table table = JSON.parseObject(addTableText,Table.class);

        //添加表的具体逻辑实现，并获取返回提示信息
        String result = tableService.addTable(table);

        //完成返回
        completed(result,responseObserver);

    }

    //删除表的实现逻辑接口
    @Override
    public void delTable(MycatRequest request, StreamObserver<MycatReply> responseObserver) {

        //获取请求JSON参数，JSON格式有严格要求。
        String delTableText = request.getParmsList();

        //将JSON转换为Table对象
        Table table = JSON.parseObject(delTableText,Table.class);

        //删除表的具体逻辑实现，并获取返回提示信息
        String result = tableService.delTable(table);

        //完成返回
        completed(result,responseObserver);
    }


    //操作表的实现逻辑接口
    @Override
    public void handleTable(MycatRequest request, StreamObserver<MycatReply> responseObserver) {

        //获取请求JSON参数，JSON格式有严格要求。
        String handleTableText = request.getParmsList();

        //将JSON转换为HandleTable表处理对象
        HandleTable table = JSON.parseObject(handleTableText,HandleTable.class);

        //操作表的具体逻辑实现，并获取返回提示信息
        String result = tableService.handleTable(table) + "";

        //完成返回
        completed(result,responseObserver);
    }


    //更新表的实现逻辑接口
    @Override
    public void updateTable(MycatRequest request, StreamObserver<MycatReply> responseObserver) {

        //获取请求JSON参数，JSON格式有严格要求。
        String handleTableText = request.getParmsList();

        //将JSON转换为HandleTable表处理对象
        HandleTable table = JSON.parseObject(handleTableText,HandleTable.class);

        //操作表的具体逻辑实现，并获取返回提示信息
        String result = tableService.updateTable(table);

        //完成返回
        completed(result,responseObserver);
    }

    //该方法完成了对Reply对象的创建以及返回结果提示信息操作
    void completed(String result,StreamObserver<MycatReply> responseObserver){

        MycatReply reply = MycatReply.newBuilder().setMessage(result).build();

        responseObserver.onNext(reply);

        responseObserver.onCompleted();

    }
}
