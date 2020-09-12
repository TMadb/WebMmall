package servlet;

import com.chinasofti.commons.CommonUtils;
import com.chinasofti.jdbc.TxQueryRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BaseDao;
import dao.daoImplement.Mmall_UserDaoImplement;
import dao.daoImplement.OrderDao;
import entity.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import service.OrderDaoService;
import service.serviceImplement.OrderServiceImplement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/order")
public class OrderServlet extends HttpServlet {

    BaseDao<Mmall_UserBean> dao = new BaseDao();
    OrderDaoService daoService = new OrderDaoService();
    QueryRunner qr = new TxQueryRunner();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");//处理响应编码
        req.setCharacterEncoding("UTF-8");
        String option = req.getParameter("opr");
        if(option.equals("addOrderData")){
            try {
                addOrderData(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(option.equals("showOneOrder")){
            try {
                showOneOrder(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(option.equals("showAllOrder")){
            try {
                showAllOrder(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void showOneOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException{
        String userName =(String) req.getSession().getAttribute("userName");
        Mmall_UserBean user = dao.selectOne("select * from mmall_user where username=?",Mmall_UserBean.class,userName);
        Mmall_orderBean orderBean = qr.query("select * from mmall_order where user_id = ?",new BeanHandler<>(Mmall_orderBean.class),user.getId());
        req.getSession().setAttribute("order",orderBean);
        System.out.println(orderBean);
//        Mmall_orderBean order = daoService.getOneOrder(orderBean.getId());
//        if(order != null){
//            ResponseSet<Mmall_orderBean> set = new ResponseSet<>(200,"cg",order);
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(resp.getWriter(),set);
//        }else{
//            ResponseSet<List<Mmall_orderBean>> set = new ResponseSet<>(500,"sb",null);
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(resp.getWriter(),set);
//        }
    }

    private void showAllOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException{
        String userName =(String) req.getSession().getAttribute("userName");
        Mmall_UserBean user = dao.selectOne("select * from mmall_user where username=?",Mmall_UserBean.class,userName);
        List<Mmall_orderBean> orders = daoService.getAllOrder(user.getId());
        Mmall_orderBean orderBean = qr.query("select * from mmall_order where user_id = ?",new BeanHandler<>(Mmall_orderBean.class),user.getId());
        req.getSession().setAttribute("order",orderBean);
        req.getSession().setAttribute("orders",orders);
        req.getRequestDispatcher("/filter/order_confirm.jsp").forward(req,resp);
//        if(orders != null){
//            ResponseSet<List<Mmall_orderBean>> set = new ResponseSet<>(200,"cg",orders);
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(resp.getWriter(),set);
//        }else{
//            ResponseSet<List<Mmall_orderBean>> set = new ResponseSet<>(500,"sb",null);
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(resp.getWriter(),set);
//        }

    }

    private void addOrderData(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
              String userName =(String) req.getSession().getAttribute("userName");
              Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                    Mmall_UserBean.class,userName);
              //订单一天后关闭
              Date today = new Date();
              Calendar c = Calendar.getInstance();
              c.setTime(today);
              c.add(Calendar.DAY_OF_MONTH, 1);
              Date closeTime = c.getTime();
              Mmall_orderBean order = new Mmall_orderBean();
              order.setId(CommonUtils.uuid());
              order.setStatus(0);
              order.setAddress("四川省泸州市合江县先滩镇");
              order.setSend_time(new Date());
              order.setClose_time(closeTime);
              order.setUser(user);
              Cart cart = (Cart) req.getSession().getAttribute("session_cart");
              order.setTotalprice(cart.getTotalPrice());
              for(CartItem cartItem : cart.getCartItems()){
                  Mmall_order_itemBean orderItemBean = new Mmall_order_itemBean();
                  orderItemBean.setOrder_no(CommonUtils.uuid());
                  orderItemBean.setOrder(order);
                  orderItemBean.setCreate_time(new Date());
                  orderItemBean.setUpdate_time(new Date());
                  orderItemBean.setAddress("四川省泸州市合江县先滩镇");
                  orderItemBean.setPayment(5.0);
                  orderItemBean.setPayment_type("支付宝");
                  orderItemBean.setProduct(cartItem.getProductBean());
                  orderItemBean.setPrice(cartItem.getProductBean().getPrice());
                  orderItemBean.setTotalprice(cartItem.getSubtotal());
                  orderItemBean.setMain_image(cartItem.getProductBean().getMain_image());
                  orderItemBean.setSubtitle(cartItem.getProductBean().getSubtitle());
                  order.getOrderItemBeanList().add(orderItemBean);
              }
              daoService.addOrder(order);
              req.getSession().setAttribute("totalprice",cart.getTotalPrice());
//              req.getSession().setAttribute("orderShow",cart);
              //清除购物车
//              cart.clearCart();
              req.getRequestDispatcher("/filter/order_add.jsp").forward(req,resp);
          }
                    //清除购物车的状态
                    //          dao.executeUpdate("update mmall_cart set status=0,checked=0 where user_id=?",user.getId());
                    //        Integer status = 1;
                    //        String address = "四川省泸州市合江县先滩镇";

                    //        //通过userName查询user_id
                    //        Integer user_id = user.getId();
                    //        Mmall_order_itemBean item = mmallOrderItemBeanBaseDao.selectOne("select * from mmall_order_item where user_id = ?",Mmall_order_itemBean.class,user_id);
                    //        HttpSession session = req.getSession();
                    //        session.setAttribute("order_no",item.getOrder_no());
                    //        HttpSession session1 = req.getSession();
                    //        session1.setAttribute("order_totalprice",item.getTotalprice());
                    //        int i = serviceImplement.addOrder(item.getOrder_no(),user_id,1,address,new Date(),closeTime);
                    //        if(i > 0){
                    //            req.getRequestDispatcher("/filter/order_confirm.jsp").forward(req,resp);
                    //        }else{
                    //            System.out.println("添加失败");
                    //            req.getRequestDispatcher("/filter/order_confirm.jsp").forward(req,resp);
                    //        }

    }

