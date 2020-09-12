package service.serviceImplement;

import com.chinasofti.jdbc.JdbcUtils;
import dao.daoImplement.OrderDaoImplement;
import service.OrederService;
import util.JDBCUtil;

import java.sql.SQLException;
import java.util.Date;

public class OrderServiceImplement implements OrederService {
    OrderDaoImplement orderDaoImplement = new OrderDaoImplement();


    @Override
    public int addOrderItem(String order_no, Integer user_id, Integer product_id, Integer payment, String pay_type,Double totalprice,String main_image,Double price,String subtitle, String address, Date create_time, Date update_time) {
        int i = 0;
        try {
            JdbcUtils.beginTransaction();
            i = orderDaoImplement.insertOrderItem(order_no,user_id,product_id,payment,pay_type,totalprice,main_image,price,subtitle,address,create_time,update_time);
            JdbcUtils.commitTransaction();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public int addOrder(String order_no, Integer user_id, Integer status, String adrress, Date send_time, Date close_time){
        int i = 0;
        try {
            JdbcUtils.beginTransaction();
            i = orderDaoImplement.insertOrder(order_no,user_id,status,adrress,send_time,close_time);
            JdbcUtils.commitTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
}
