package service.serviceImplement;

import dao.daoImplement.CartImplementDao;
import entity.CartBean;
import service.CartService;
import java.util.Date;

public class CartServiceImplement implements CartService {

    CartImplementDao dao = new CartImplementDao();
    @Override
    public int addCart(Integer user_id, Integer product_id, Integer quantity, Double price,Double totalprice, String subtitle,String main_image,Integer status) {
        int i = dao.insertCart(user_id, product_id, quantity, price,totalprice, subtitle, main_image,status);
        return i;
    }

    @Override
    public int deleteOne(Integer id) {
        int i = dao.delCartOne(id);
        return i;
    }


}
