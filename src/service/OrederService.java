package service;

import java.util.Date;

public interface OrederService {

    //添加订单
    public int addOrder(String order_no, Integer user_id,
                           Integer status, String adrress, Date send_time, Date close_time);

    //添加订单条目
    public int addOrderItem(String order_no,Integer user_id,Integer product_id,Integer payment,String pay_type,Double totalprice,String main_image,Double price,String subtitle,String address,Date create_time,
                               Date update_time);
}
