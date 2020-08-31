package dao.daoImplement;

import dao.BaseDao;
import dao.CartDao;
import entity.CartBean;
import entity.Mmall_UserBean;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CartImplementDao extends BaseDao<CartBean> implements CartDao {
    @Override
    public int insertCart(Integer user_id, Integer product_id, Integer quantity, Double price, Date create_time, Date update_time) {
        String sql = "insert into mmall_cart values(null,?,?,?,?,?,?)";
        int i = executeUpdate(sql,CartBean.class,user_id,product_id,quantity,price,new Date(),new Date());
        return i;
    }
}
