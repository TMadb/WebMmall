package dao.daoImplement;

import dao.BaseDao;
import dao.CartDao;
import entity.CartBean;
import entity.Mmall_UserBean;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CartImplementDao extends BaseDao<CartBean> implements CartDao {
    @Override
    public int insertCart(Integer user_id, Integer product_id, Integer quantity, Double price,Double totalprice, String subtitle,String main_image,Integer status) {
        String sql = "insert into mmall_cart values(?,?,?,?,?,?,?,?,?,?)";
        int i = executeUpdate(sql,null,user_id,product_id,quantity,price,quantity*price,subtitle,main_image,status,0);
        return i;
    }

    @Override
    public int delCartOne(Integer id) {
        String sql = "update mmall_cart set status = 0 where id =?";
        int i = executeUpdate(sql,id);
        return i;
    }

}
