package com.kd.wyq.mycatTable.model;

public class Table {

    private String name;
    private String dataNode;
    private String primaryKey; //可以不写
    private String rule;
    private String schemaName;
    private String type;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataNode() {
        return dataNode;
    }

    public void setDataNode(String dataNode) {
        this.dataNode = dataNode;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
