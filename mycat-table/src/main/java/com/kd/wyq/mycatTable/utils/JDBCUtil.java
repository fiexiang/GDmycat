package com.kd.wyq.mycatTable.utils;/*
    Create By WangYuqiang,
    Data:2019/6/17,
    Time:14:36

*/

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

    private  static PropertiesKeyset propertiesKeyset = new PropertiesKeyset();

    private  static  Map<String,Object> mycat = propertiesKeyset.getProperties();

    private final static String DRIVER = mycat.get("DRIVER").toString();

    private final static String URL = mycat.get("URL").toString()+mycat.get("IP").toString()+":"+mycat.get("PORT").toString()+"/";

    private final static String USER = mycat.get("USER").toString();

    private final static String PASSWORD = mycat.get("PASSWORD").toString();

    protected  Connection conn;

    protected Statement stat;

    protected PreparedStatement ps;

    protected ResultSet rs;

    static{

        try {

            System.out.println("========开始加载数据库驱动======");

            Class.forName(DRIVER);

            System.out.println("========数据库驱动加载完成=======");


        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    public void getConnection(String schemaName){

        System.out.println("=======开始建立数据库连接===========");

        try {

            System.out.println(URL);
            System.out.println(USER);
            System.out.println(PASSWORD);

            this.conn = DriverManager.getConnection(URL,USER,PASSWORD);

            System.out.println("=======建立数据库连接完成===========");

        } catch (SQLException e) {

            e.printStackTrace();

            System.out.println("============数据库连接异常===========");

        }

    }

    /*
    *   数据库更新
    * */
    public  int DbUpdate(String sql,String schemaName,Object... params){
        try {
            //获取连接
            this.getConnection(schemaName);
            //获取数据库预处理语句对象
            this.ps = this.conn.prepareStatement(sql);
            //遍历Object数组
            for(int i = 0;i<params.length;i++){
                this.ps.setObject(i+1, params[i]);
            }
            //获取返回结果
            int result = this.ps.executeUpdate();

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("执行数据库更新语句:"+sql+"失败");
        }
    }
    /*
     *   获取结果集
     * */
    public void getResultSet(String sql,String schemaName,Object...params) {
        try {
            //获取数据库连接
            this.getConnection(schemaName);
            //获取数据库预处理语句对象
            this.ps = this.conn.prepareStatement(sql);
            //遍历Object数组（注意索引）
            for(int i = 1;i <= params.length;i++){
                this.ps.setObject(i, params[i-1]);
            }
            //复制rs对象返回结果集
            this.rs = this.ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取"+sql+"语句返回结果集异常");
        }
    }
    /*
     *   将单条结果集返回为Map
     * */
    public Map<String,String> queryForMap(String sql,String schemaName, Object... params){

        Map<String,String> map = null;

        //获取rs对象
        this.getResultSet(sql,schemaName, params);
        try {
            //判断是否有值
            if(this.rs.next()) {
                //实例化map
                map = new HashMap<String,String>();
                //获取元数据
                ResultSetMetaData rsmd = this.rs.getMetaData();
                //获取列数量
                int columCount = rsmd.getColumnCount();
                //循环存储
                for(int i = 1;i<=columCount;i++) {
                    String key = rsmd.getColumnLabel(i);
                    String value = this.rs.getString(key);
                    map.put(key, value);
                }
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("执行"+sql+"语句，返回Mpa结果集失败");
        }
    }
    /**
     * 将多条结果集返回为List泛型为Map
     */
    public List<Map<String,String>> queryForList(String sql,String schemaName, Object...params){
        //声明List
        List<Map<String,String>> list = null;
        //获取结果集对象
        this.getResultSet(sql,schemaName, params);
        try {
            //判断是否有返回结果集
            if(this.rs.next()) {
                //因为next方法修改了指针，使用previous将指针复原
                rs.previous();
                //实例化List
                list = new ArrayList<Map<String,String>>();
                //获取元数据
                ResultSetMetaData rsmd = this.rs.getMetaData();
                //获取行数量
                int columCount = rsmd.getColumnCount();
                //遍历
                while(rs.next()) {
                    //实例化Map
                    Map<String,String> map = new HashMap<String,String>();
                    //循环存储
                    for(int i = 1;i<=columCount;i++) {
                        String key = rsmd.getColumnLabel(i);
                        String value = this.rs.getString(key);
                        map.put(key, value);
                    }
                    //添加到List
                    list.add(map);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("执行"+sql+"语句，返回List结果集异常");
        }

    }

    /*
    *确定返回结果集为Int类型，将结果集值返回
    * */
    public Integer queryForInt(String sql,String schemaName,Object...params) {
        //获取结果集
        this.getResultSet(sql, schemaName,params);

        try {
            if(this.rs.next()) {
                return this.rs.getInt(1);
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("执行"+sql+"语句，返回Int类型异常");
        }
    }
    /*
     *确定返回结果集为String类型，将结果集值返回
     * */
    public String queryForString(String sql,String schemaName,Object...params) {
        //获取结果集
        this.getResultSet(sql,schemaName, params);
        try {
            if(this.rs.next()) {
                return this.rs.getString(1);
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("执行"+sql+"语句，返回String类型异常");
        }
    }
    /**
     * 批量处理sql语句方法
     */
    public void updataBatch(String schemaName,String...sqls) {
        try {
            //获取数据库连接
            this.getConnection(schemaName);
            //关闭自动提交
            this.conn.setAutoCommit(false);
            //获取数据库处理语句对象
            this.stat = this.conn.createStatement();
            //遍历sql语句数组
            for(String sql:sqls) {
                this.stat.addBatch(sql);
            }
            //执行sql语句，此时还没有手动提交，不会获得返回值
            this.stat.executeBatch();
            //手动提交
            this.conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("执行批量"+sqls+"语句异常");
        }
    }

    //关闭连接
    public void closeConn() throws SQLException {

        if(!this.conn.isClosed()){

            this.conn.close();

        }
    }

}
