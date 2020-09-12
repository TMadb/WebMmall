package dao;

import entity.Mmall_orderBean;
import entity.Mmall_order_itemBean;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    //添加订单
    public int insertOrder(String order_no, Integer user_id,
                            Integer status, String adrress, Date send_time, Date close_time);

    //添加订单条目
    public int insertOrderItem(String order_no,Integer user_id,Integer product_id,Integer payment,String paytype,Double totalprice,String main_image,Double price,String subtitle,String address,Date create_time,
                               Date update_time) ;
}
