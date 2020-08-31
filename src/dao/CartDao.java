package dao;

import entity.Mmall_UserBean;

import java.util.Date;

public interface CartDao {

    //添加购物车
    int insertCart(Integer user_id, Integer product_id, Integer quantity, Double price
            , Date create_time,Date update_time );

}
