package dao.daoImplement;

import dao.BaseDao;
import dao.OrderDao;

import java.util.Date;

public class OrderDaoImplement extends BaseDao implements OrderDao {
    @Override
    public int insertOrder(String order_no, Integer user_id, Integer status, String adrress, Date send_time, Date close_time) {

            String sql = "insert into mmall_order values(?,?,?,?,?,?,?)";
            int i = executeUpdate(sql, null, order_no, user_id, status, adrress, send_time, close_time);
            return i;

    }

    @Override
    public int insertOrderItem(String order_no, Integer user_id, Integer product_id,Integer payment, String pay_type ,Double totalprice,String main_image,Double price,String subtitle,String address, Date create_time, Date update_time) {
        String sql = "insert into mmall_order_item values(?,?,?,?,?,?,?,?,?,?,?,?)";
        int i = executeUpdate(sql, order_no, user_id, product_id, payment, pay_type, totalprice, main_image, price, subtitle, address, create_time, update_time);
        return i;

    }
}
