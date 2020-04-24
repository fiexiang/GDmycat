package com.kd.wyq.mycatTable.grpc.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kd.wyq.mycatTable.model.HandleTable;
import com.kd.wyq.mycatTable.model.Table;
import com.kd.wyq.mycatTable.service.TableService;
import com.kd.wyq.mycatTable.service.impl.TableServiceImpl;
import io.grpc.stub.StreamObserver;
import io.grpc.wyq.mycat.MycatGrpc.MycatImplBase;
import io.grpc.wyq.mycat.MycatReply;
import io.grpc.wyq.mycat.MycatRequest;

public class MycatGrpcImpl extends MycatImplBase {

    TableService tableService = new TableServiceImpl();

    @Override
    public void addTable(MycatRequest request, StreamObserver<MycatReply> responseObserver) {

        String addTableText = request.getParmsList();

        Table table = JSON.parseObject(addTableText,Table.class);

        String result = tableService.addTable(table);

        MycatReply reply = MycatReply.newBuilder().setMessage(result).build();

        responseObserver.onNext(reply);

        responseObserver.onCompleted();

    }

    @Override
    public void delTable(MycatRequest request, StreamObserver<MycatReply> responseObserver) {

        String delTableText = request.getParmsList();

        Table table = JSON.parseObject(delTableText,Table.class);

        String result = tableService.delTable(table);

        MycatReply reply = MycatReply.newBuilder().setMessage(result).build();

        responseObserver.onNext(reply);

        responseObserver.onCompleted();
    }

    @Override
    public void handleTable(MycatRequest request, StreamObserver<MycatReply> responseObserver) {
        String handleTableText = request.getParmsList();

        HandleTable table = JSON.parseObject(handleTableText,HandleTable.class);

        String result = tableService.handleTable(table) + "";

        MycatReply reply = MycatReply.newBuilder().setMessage(result).build();

        responseObserver.onNext(reply);

        responseObserver.onCompleted();
    }
}
