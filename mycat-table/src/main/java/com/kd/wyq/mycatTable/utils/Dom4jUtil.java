package com.kd.wyq.mycatTable.utils;

import com.kd.wyq.mycatTable.model.HandleTable;
import com.kd.wyq.mycatTable.model.Table;
import com.sshtools.j2ssh.SshClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.util.List;

public class Dom4jUtil {


    public Document getDocument(String fileName){//根据xml文件路径，获取document对象

        SAXReader saxReader = new SAXReader();

        try {

            return saxReader.read(fileName);

        } catch (DocumentException e) {

            e.printStackTrace();

        }

        return null;

    }

    public String copyMycatConfigureFileToTemp(SshClient client){//将mycat配置文件，备份到临时目录，方便修改

        GetMycatConfigureFile mycatFile = new GetMycatConfigureFile();

        String tempPath = mycatFile.copyMycatConfigureFile(client);

        return tempPath;

    }


    public String addTableToConfigureFile(Table table, SshClient client){//添加table信息到mycat配置文件

        String tempPath = this.copyMycatConfigureFileToTemp(client);

        Document document = this.getDocument(tempPath);

        Element rootElement = document.getRootElement();

        List<Element> schemaList = rootElement.elements();

        Element realSchema = this.getRealSchema(rootElement,table.getSchemaName());

        Element newTable = realSchema.addElement("table");

        String tableName = table.getName().toUpperCase();

        String tableDataNode = table.getDataNode();

        String tableRule = table.getRule();

        newTable.addAttribute("name",tableName);

        newTable.addAttribute("dataNode",tableDataNode);

        newTable.addAttribute("rule",tableRule);

        if(table.getPrimaryKey() != null){

            String primaryKey = table.getPrimaryKey();

            newTable.addAttribute("primaryKey",primaryKey);

        }

        write(document,tempPath);


        return tempPath;

    }

    public String deleteTableToConfigureFile( Table table, SshClient client){//删除mycat配置文件中的指定table信息

        String tempPath = this.copyMycatConfigureFileToTemp(client);

        Document document = this.getDocument(tempPath);

        Element rootElement = document.getRootElement();

        Element realSchema = this.getRealSchema(rootElement,table.getSchemaName());

        Node node = realSchema.selectSingleNode("table[@name='"+table.getName().toUpperCase()+"']");

        realSchema.remove(node);

        write(document,tempPath);

        return tempPath;

    }

    public String updateTableToConfigureFile(HandleTable t, SshClient client){//删除mycat配置文件中的指定table信息

        String tempPath = this.copyMycatConfigureFileToTemp(client);

        Document document = this.getDocument(tempPath);

        Element rootElement = document.getRootElement();

        Table table = t.getTable();

        Element realSchema = this.getRealSchema(rootElement,table.getSchemaName());

        Element node = (Element) realSchema.selectSingleNode("table[@name='"+table.getName().toUpperCase()+"']");

        node.attribute("name").setValue(t.getHandle().toUpperCase());

        write(document,tempPath);

        return tempPath;

    }

    public Element getRealSchema(Element rootElement, String schemaNama){//根据schemaName从root element对象中获取对应的xml信息对象element

        Element element = (Element) rootElement.selectSingleNode("schema[@name='"+schemaNama+"']");

        return element;
    }

    public void write(Document document,String tempPath){

        try {

            OutputFormat format = OutputFormat.createPrettyPrint(); //设置XML文档输出格式
//        format.setSuppressDeclaration(true);
          format.setIndent(true); //设置是否缩进
          format.setIndent("   "); //以空格方式实现缩进
          format.setNewlines(true); //设置是否换行

            XMLWriter writer = new XMLWriter(new FileWriter(tempPath),format);

            writer.write(document);

            writer.flush();

            writer.close();

        }catch (Exception e){

        }

    }

}
