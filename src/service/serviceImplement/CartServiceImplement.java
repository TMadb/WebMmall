package service.serviceImplement;

import dao.daoImplement.CartImplementDao;
import service.CartService;

import java.util.Date;

public class CartServiceImplement implements CartService {

    CartImplementDao dao = new CartImplementDao();
    @Override
    public int addCart(Integer user_id, Integer product_id, Integer quantity, Double price, Date create_time, Date update_time) {
        int i = dao.insertCart(user_id, product_id, quantity, price, create_time, update_time);
        return i;
    }
}
