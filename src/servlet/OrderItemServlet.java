package servlet;

import com.chinasofti.commons.CommonUtils;
import com.chinasofti.jdbc.TxQueryRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BaseDao;
import entity.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import service.serviceImplement.OrderServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderItemServlet",value = "/orderItem")
public class OrderItemServlet extends HttpServlet {

    OrderServiceImplement serviceImplement = new OrderServiceImplement();
    BaseDao<Mmall_UserBean> dao = new BaseDao();
    BaseDao<CartBean> cartDao = new BaseDao();
    BaseDao<Mmall_order_itemBean> mmallOrderItemBeanBaseDao = new BaseDao<>();
    BaseDao<Mmall_orderBean> mmall_orderBeanBaseDao = new BaseDao<>();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");//处理响应编码
        req.setCharacterEncoding("UTF-8");
        String option = req.getParameter("opr");
        if(option.equals("showData")){
            try {
                showItemData(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(option.equals("showOrder")){
            showOrder(req,resp);
        }else if(option.equals("order")){
            showOrderCommit(req,resp);
        }
    }

    private void showOrderCommit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String userName = (String) req.getSession().getAttribute("userName");
        Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                Mmall_UserBean.class,userName);
        Integer user_id = user.getId();
        System.out.println(user_id);
        Mmall_orderBean order = mmall_orderBeanBaseDao.selectOne("select * from mmall_order where user_id = ?",
                Mmall_orderBean.class,user_id);
        ResponseSet<Mmall_orderBean> set = new ResponseSet<>(200,"成功",order);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(),set);
    }

    private void showOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        try {
            String userName = (String) req.getSession().getAttribute("userName");
            Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                    Mmall_UserBean.class,userName);
            Integer user_id = user.getId();
            Mmall_orderBean order = mmall_orderBeanBaseDao.selectOne("select * from mmall_order where user_id = ?",
                    Mmall_orderBean.class,user_id);
            System.out.println(order);
            ResponseSet<Mmall_orderBean> set = new ResponseSet<>(200,"成功",order);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(),set);
        }catch (Exception e) {
            ResponseSet<Mmall_orderBean> set = new ResponseSet<>(500, "失败", null);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(), set);
        }
    }

    private void showItemData(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        try {
            String userName = (String) req.getSession().getAttribute("userName");
            Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                    Mmall_UserBean.class,userName);
            QueryRunner qr = new TxQueryRunner();
            //查出所有的订单条目
            List<Mmall_order_itemBean> orderItemList = qr.query("select * from mmall_order_item where user_id = ?",
                    new BeanListHandler<>(Mmall_order_itemBean.class),user.getId());
            //将查出的数据转换
            ResponseSet set = new ResponseSet(200,"成功",orderItemList);
            //将数据封装并写回前端
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(),set);
        }catch (Exception e){
            //将查出的数据转换
            ResponseSet set = new ResponseSet(500,"失败",null);
            //将数据封装并写回前端
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(),set);
            e.printStackTrace();
        }
    }
}
