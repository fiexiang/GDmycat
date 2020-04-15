package com.kd.wyq.mycatTable.service;

import com.kd.wyq.mycatTable.model.Table;

public interface TableService {

    //添加表
    String addTable(Table table);

    //删除表
    String delTable(Table table);

    //修改表
    String updateTable(Table table);

}
