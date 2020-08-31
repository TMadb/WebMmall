package service;

import java.util.Date;

public interface CartService {

    int addCart(Integer user_id, Integer product_id, Integer quantity, Double price
            , Date create_time, Date update_time );
}
