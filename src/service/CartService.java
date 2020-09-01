package service;

import entity.CartBean;

import java.util.Date;

public interface CartService {

    //添加购物车
    int addCart(Integer user_id, Integer product_id, Integer quantity, Double price
            ,Double totalprice, String subtitle,String main_image );

}
