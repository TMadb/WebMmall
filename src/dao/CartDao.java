package dao;

import entity.CartBean;
import entity.Mmall_UserBean;

import java.util.Date;

public interface CartDao {

    //添加购物车
    int insertCart(Integer user_id, Integer product_id, Integer quantity, Double price
            ,Double totalprice, String subtitle,String main_image );

}
