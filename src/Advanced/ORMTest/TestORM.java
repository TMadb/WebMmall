package Advanced.ORMTest;

import util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestORM {


    public static void main(String[] args) {

        List<ProductBean> list = selectAllProducts(ProductBean.class);
        if(list != null){
            for(ProductBean p : list){
                System.out.println(p);
            }
        }
    }


    //查询方法
    public static List<ProductBean> selectAllProducts(Class c){
        //反射机制获取查询的表名
        TableAnnotation tableAnnotation = (TableAnnotation)c.getAnnotation(TableAnnotation.class);
        //定义查询语句
        String sql = "select * from "+tableAnnotation.tableName();
        //调用JDBC的连接方法进行查询操作
        Connection con = JDBCUtil.getConnection();
        //存放结果
        List<ProductBean> list = new ArrayList<>();
        try {
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            //将取到的结果集映射到返回的数据中
            while(rs.next()){
                Object obj = rowMapper(rs,c);
                list.add((ProductBean) obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //映射数据的方法
    public static Object rowMapper(ResultSet rs,Class c){
        //新建对象，用于映射结果
        Object object = null;
        try {
            object = c.newInstance();
            //获取到所有的字段
            Field[] fields = c.getDeclaredFields();
            //通过循环取出所有的列名
            for(Field f : fields){
                //取出所有列名
                ColumnAnnotation column = f.getAnnotation(ColumnAnnotation.class);
                //判断列名是否为空（去掉控制针的影响）
                if(column != null){
                    //设置所有属性可读（避免私有属性读不出来的尴尬情况）
                    f.setAccessible(true);
                    //设置值
                    f.set(object,rs.getObject(column.columnName()));
                }
            }
        } catch (InstantiationException | SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
