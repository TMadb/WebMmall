package dao;

import util.JDBCUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有数据库实现类的基类
 */
public  class BaseDao<T> {
    /**
     *增删改通用方法
     * @param sql   预定义的sql语句
     * @param params 替换占位符的参数列表  Object... 表示可以传递0个或多个参数
     * @return 受影响的行数
     */
    public int executeUpdate(String sql,Object...params){

        PreparedStatement ps = null;
        //获取连接
        Connection connection = JDBCUtil.getConnection();
        try {
            //创建PrepaedStatement对象
            ps = connection.prepareStatement(sql);
            //循环给占位符设置值
            for(int i = 0;i<params.length;i++){
                ps.setObject(i+1,params[i]);
            }
            //执行sql语句，返回受影响的行数
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            JDBCUtil.close(null,ps,connection);
        }

        return 0;
    }

    /**
     * 查询一条记录,返回一个记录实体对象
     * @param sql 预定义的sql语句，含占位符
     * @param params 预定义sql中占位符对应的参数列表
     * @return 返回一个记录实体对象
     */
    public T selectOne(String sql,Class c,Object... params){

        PreparedStatement ps = null;
        Object obj = null;
        ResultSet rs = null;
        Connection connection =  JDBCUtil.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            //循环设置参数
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            //执行查询语句,返回结果集
             rs = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            //如果有记录,则映射结果集
            if(rs.next()){
                //映射结果集
                 obj = rowMapper(rs,resultSetMetaData,c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            JDBCUtil.close(rs, ps, connection);
        }
        return (T)obj;
    }

    /**
     *查询多条记录
     * @param sql
     * @param params
     * @return 返回记录数对应的集合对象
     */
    public List<T> selectList(String sql, Class c, Object...params){
        PreparedStatement ps = null;
        List<T> objs = new ArrayList<>();
        ResultSet rs = null;

        Connection connection =  JDBCUtil.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();

            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            //如果有记录，就添加在集合中
            while(rs.next()){
                //映射结果集
                Object obj = rowMapper(rs,resultSetMetaData,c);
                objs.add((T)obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, connection);
        }
        return objs;
    }

    /**
     * 将每一条记录通过反射创建实体对象，并设置对象中的属性
     * @param rs
     * @param resultSetMetaData
     * @param c
     * @return
     */
    public T rowMapper(ResultSet rs,ResultSetMetaData resultSetMetaData,Class c) {
        Object obj = null;
       try{
           obj = c.newInstance();
           for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
               String filedName = resultSetMetaData.getColumnLabel(i + 1);
               Field field = c.getDeclaredField(filedName);
               //根据字段名拼接set方法，首字母变大写
               String methodName = "set" + String.valueOf(filedName.charAt(0)).toUpperCase() + filedName.substring(1);
               //找到对应的set方法
               Method method = c.getDeclaredMethod(methodName, field.getType());
               try {
                   //调用对应的set方法，将数据库中取出来的数据设置进去
                   method.invoke(obj, rs.getObject(field.getName()));
               } catch (InvocationTargetException e) {
                   e.printStackTrace();
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return (T)obj;
    }

    //查询数据库中的记录总数
    public int selectCount(String sql) {
        Connection con = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        //存放记录
        int count = 0;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //判断结果集是否还有下一条记录
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关流
            JDBCUtil.close(rs,ps,con);
        }
        return count;
    }

}
