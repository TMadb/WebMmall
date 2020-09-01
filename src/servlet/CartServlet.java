package servlet;

import com.chinasofti.commons.CommonUtils;
import com.chinasofti.jdbc.TxQueryRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BaseDao;
import dao.daoImplement.Mmall_UserDaoImplement;
import entity.CartBean;
import entity.Mmall_UserBean;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import service.serviceImplement.CartServiceImplement;
import service.serviceImplement.Mmall_UserServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CartServlet",value = "/cartServlet")
public class CartServlet extends HttpServlet {

    //查询数据库的方法
    Mmall_UserServiceImplement serviceImplement = new Mmall_UserServiceImplement();
    CartServiceImplement cartServiceImplement = new CartServiceImplement();
    BaseDao<Mmall_UserBean> dao = new BaseDao<>();
    BaseDao<CartBean> cartdao = new BaseDao<>();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");//处理响应编码
        req.setCharacterEncoding("UTF-8");
        String option = req.getParameter("opr");
        if(option.equals("joinCart")){
            addCart(req,resp);
        }else if(option.equals("showCart")){
            try {
                showCart(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        PrintWriter out = resp.getWriter();
        String userName = (String) req.getSession().getAttribute("userName");
        Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                Mmall_UserBean.class,userName);
        Integer user_id = user.getId();
//        CartBean cart =cartdao.selectOne("select mc.id,mc.user_id,mc.product_id,mc.quantity,mc.price,mc.totalprice,mc.create_time,mc.update_time\n" +
//                        "from mmall_user mu left join mmall_cart mc on mc.user_id=mu.id where user_id=?",
//                CartBean.class,user_id);
        QueryRunner qr = new TxQueryRunner();
        List <CartBean> cart = qr.query("select mc.id,mc.user_id,mc.product_id,mc.quantity,mc.price,mc.totalprice,mc.subtitle,mc.main_image\n" +
                "from mmall_user mu left join mmall_cart mc on mc.user_id=mu.id where user_id=?",new BeanListHandler<>(CartBean.class),user_id);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(),cart);
        //将对象转换为json对象
//        JSONObject json = JSONObject.fromObject(cart);
//        System.out.println(json.toString());
//        out.print(json);
    }

    private void addCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        //获取传递的参数
        Integer product_id = Integer.parseInt(req.getParameter("product_id"));
        Integer quantity =  Integer.parseInt(req.getParameter("quantity"));
        double price = Double.valueOf(req.getParameter("price"));
        String userName =(String)req.getSession().getAttribute("userName");
        String main_image = req.getParameter("img");
        String subtitle = req.getParameter("shopName");
        Mmall_UserBean user = null;
        PrintWriter out = resp.getWriter();
        Integer user_id = 0;
        int i = 0;
        if(userName != null){
            user = serviceImplement.selectOneByUserName(userName);
            user_id = user.getId();
            i=cartServiceImplement.addCart(user_id,product_id,quantity,
                    price,quantity*price,subtitle,main_image);
            out.write("false");
        }else{
            out.write("total");
        }
        //添加成功与否
        if(i==0){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功");
        }
    }
}
