package dao.daoImplement;

/**
 * 订单操作
 * 批处理操作(批量添加多条订单条目)
 */

import com.chinasofti.commons.CommonUtils;
import com.chinasofti.jdbc.TxQueryRunner;
import entity.Mmall_orderBean;
import entity.Mmall_order_itemBean;
import entity.ProductBean;
import exception.DaoException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDao {

    private QueryRunner qr = new TxQueryRunner();
    //插入订单
    public void insertOrder(Mmall_orderBean order){
        try {
            String sql = "insert into mmall_order values(?,?,?,?,?,?,?)";
            //多个参数，所以创建数组存储
            Object[] params = new Object[]{
                    order.getId(),order.getUser().getId(),
                    order.getTotalprice(),order.getStatus(),
                    order.getAddress(),order.getSend_time(),
                    order.getClose_time()
            };
            qr.update(sql,params);
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    //插入订单条目(一个订单有多个订单条目)
    public void insertOrderItem(List<Mmall_order_itemBean> orderItemBeans){
        try {
            String sql = "insert into mmall_order_item values(?,?,?,?,?,?,?,?,?,?,?,?)";
            //多个参数，参数中有多个条目，需要批处理
            //长度为orderItem的长度
            Object[][] params = new Object[orderItemBeans.size()][];
            for(int i = 0 ; i < orderItemBeans.size();i++){
                Mmall_order_itemBean order_itemBean = orderItemBeans.get(i);
                //一次可能会添加多个订单条目，需要循环将其添加
                 params[i] = new Object[]{
                        order_itemBean.getOrder_no(),order_itemBean.getOrder().getId(),
                        order_itemBean.getProduct().getId(),order_itemBean.getPayment(),
                        order_itemBean.getPayment_type(),order_itemBean.getTotalprice(),
                        order_itemBean.getMain_image(),order_itemBean.getPrice(),
                        order_itemBean.getSubtitle(),order_itemBean.getAddress(),
                        order_itemBean.getCreate_time(),order_itemBean.getUpdate_time()
                };
            }
            //批处理语句
            qr.batch(sql,params);
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    //查询当前用户的所有订单信息
    public List<Mmall_orderBean> selectAllOrder(Integer user_id){
        try {
            String sql = "select * from mmall_order where user_id=?";
            List<Mmall_orderBean> orders = qr.query(sql,new BeanListHandler<>(Mmall_orderBean.class),user_id);
            for(Mmall_orderBean orderBean : orders){
                //设置user对象
                orderBean.setUser(new Mmall_UserDaoImplement().selectOneByUserId(user_id));
                //订单表和用户表连表查询处所有的订单条目和商品信息
                sql = "select * from mmall_product mp,mmall_order_item moi where mp.id=moi.product_id and order_id=?";
                AddOrderItem(orderBean);
            }
            return orders;
        }catch (Exception e){
            throw new DaoException();
        }
    }
    //根据订单编号查询指定订单
    public Mmall_orderBean selectOneOrder(String orderId){
        try {
            String sql = "select * from mmall_order where id=?";
            Mmall_orderBean order = qr.query(sql,new BeanHandler<>(Mmall_orderBean.class),orderId);
            AddOrderItem(order);
            return order;
        }catch (Exception e){
            throw  new DaoException();
        }
    }

    private void AddOrderItem(Mmall_orderBean orderBean) throws SQLException {
        //订单表和用户表连表查询处所有的订单条目和商品信息
        String sql = "select * from mmall_product mp,mmall_order_item moi where mp.id=moi.product_id and order_id=?";
        List<Map<String,Object>> mapList = qr.query(sql,new MapListHandler(),orderBean.getId());
        //将查询出的数据转换为对应的对象
        for(Map<String,Object> map :mapList){
            ProductBean productBean = CommonUtils.toBean(map,ProductBean.class);
            Mmall_order_itemBean orderItemBean = CommonUtils.toBean(map,Mmall_order_itemBean.class);
            //装载对象
            orderItemBean.setProduct(productBean);
            orderBean.getOrderItemBeanList().add(orderItemBean);
        }
    }
}
