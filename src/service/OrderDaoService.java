package service;

/**
 * 采用事务进行提交处理
 * 同时成功才能提交
 * 否则回滚
 */

import com.chinasofti.jdbc.JdbcUtils;
import dao.daoImplement.OrderDao;
import entity.Mmall_orderBean;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoService {

    OrderDao dao = new OrderDao();

    public void addOrder(Mmall_orderBean order){
        try {
            //显示事务开始
            JdbcUtils.beginTransaction();
            //订单插入
            dao.insertOrder(order);
            dao.insertOrderItem(order.getOrderItemBeanList());
            //成功操作后提交
            JdbcUtils.commitTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                //发生异常后回滚
                JdbcUtils.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //根据user_id查询所有订单
    public List<Mmall_orderBean> getAllOrder(Integer user_id){
        return dao.selectAllOrder(user_id);
    }

    //根据订单编号查询指定订单
    public Mmall_orderBean getOneOrder(String order_id){
        return dao.selectOneOrder(order_id);
    }
}
