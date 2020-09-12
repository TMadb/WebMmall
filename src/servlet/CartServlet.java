package servlet;

/**
 * 购物车的操作
 */

import com.chinasofti.jdbc.TxQueryRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BaseDao;
import entity.CartBean;
import entity.Mmall_UserBean;
import entity.ResponseSet;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
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
import java.util.List;

@WebServlet(name = "CartServlet",value = "/cartServlet")
public class CartServlet extends HttpServlet {

    //查询数据库的方法
    Mmall_UserServiceImplement serviceImplement = new Mmall_UserServiceImplement();
    CartServiceImplement cartServiceImplement = new CartServiceImplement();
    BaseDao<Mmall_UserBean> dao = new BaseDao<>();
    BaseDao temp = new BaseDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");//处理响应编码
        req.setCharacterEncoding("UTF-8");
        //判断操作参数
        String option = req.getParameter("opr");
        String oid =req.getParameter("id");
        String all = req.getParameter("all");
        Integer cartId= 0;
        String ocartId = req.getParameter("cartId");
        if(ocartId !=null ){
            cartId = Integer.parseInt(req.getParameter("cartId"));
        }
        Integer id = 0;
        if(oid!=null){
            id = Integer.parseInt(req.getParameter("id"));
        }
        if(option.equals("joinCart")){
            addCart(req,resp);
        }else if(option.equals("showCart")){
            try {
                showCart(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(option.equals("del")){
            try {
                del(req,resp,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(option.equals("delAll")){
            try {
                delAll(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if(option.equals("count")){
            try {
                countPrice(req,resp,all,cartId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(option.equals("countSum")){
            countSum(req,resp);
        }
    }

    private void countSum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //查询数据库的基本类
        QueryRunner qr = new TxQueryRunner();

        //获取用户的id，通过登陆的用户名查询
        String userName =(String) req.getSession().getAttribute("userName");
        Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                Mmall_UserBean.class,userName);
        try {
            Integer i =(Integer) qr.query("select sum(quantity) from mmall_cart where user_id = ?",
                    new ScalarHandler(),user.getId());
            PrintWriter out = resp.getWriter();
            out.write(i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 查询当前购物车的总金额
     * @param req
     * @param resp
     * @param all
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void countPrice(HttpServletRequest req, HttpServletResponse resp,String all,Integer cartId)
            throws ServletException, IOException, SQLException{
        //查询数据库的基本类
        QueryRunner qr = new TxQueryRunner();
        PrintWriter out = resp.getWriter();
        //获取用户的id，通过登陆的用户名查询
        String userName =(String) req.getSession().getAttribute("userName");
        Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                Mmall_UserBean.class,userName);

        //修改选择的状态和查询单选时的价格
        if(all.equals("yes")){
            int i = qr.update("update mmall_cart set checked=1 where status=1 and user_id = ?",user.getId());
            if(i>0){
                String sql = "select sum(totalprice) from mmall_cart where status=1 and user_id = ?";
                //购物车里面所有商品的总价格
                Double countprice =(Double) qr.query(sql,new ScalarHandler(),user.getId());
                out.write(String.valueOf(countprice));
                System.out.println("yescg");
            }
        }else if(all.equals("no")){
            int i = qr.update("update mmall_cart set checked=0 where status=1 and user_id = ?",user.getId());
            if(i>0){
                System.out.println("nocg");
            }
        }else if(all.equals("countOne")){
            int i = qr.update("update mmall_cart set checked=1 where status=1 and id = ?",cartId);
            if(i>0){
                //单件商品的小计
                Double countOne =(Double) qr.query("select totalprice from mmall_cart where status=1 and id = ?",new ScalarHandler(),cartId);
                out.write(String.valueOf(countOne));
                System.out.println("onecg");
            }
        }else if(all.equals("countOneNo")){
            int i = qr.update("update mmall_cart set checked=0 where status=1 and id = ?",cartId);
            if(i>0){
                System.out.println("oneNocg");
            }
        }

    }

    /**
     * 数据的软删除，改变数据库中该条数据的状态，同步
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void delAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException{
        String userName = (String) req.getSession().getAttribute("userName");
        Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                Mmall_UserBean.class,userName);
        String sql = "update mmall_cart set status=0 where user_id=?";
        int i = temp.executeUpdate(sql,user.getId());
        if(i > 0){
            System.out.println("清空成功");
            req.getRequestDispatcher("/filter/cart.jsp").forward(req,resp);
        }else{
            System.out.println("清空失败");
            req.getRequestDispatcher("/filter/cart.jsp").forward(req,resp);
        }
    }

    /**
     * 数据的软删除（单个），改变数据库中该条数据的状态
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void del(HttpServletRequest req, HttpServletResponse resp,Integer id)
            throws ServletException, IOException, SQLException{

        int i = cartServiceImplement.deleteOne(id);
        if(i > 0){
            System.out.println("删除成功");
            req.getRequestDispatcher("/filter/cart.jsp").forward(req,resp);
        }else{
            System.out.println("删除失败");
        }
    }

    /**
     * 数据展示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void showCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        try {
            String userName = (String) req.getSession().getAttribute("userName");
            Mmall_UserBean user = dao.selectOne("select * from mmall_user where username = ?",
                    Mmall_UserBean.class,userName);
            Integer user_id = user.getId();
            //使用自定义查询类
            QueryRunner qr = new TxQueryRunner();
            List<CartBean> cart = qr.query("select mc.id,mc.user_id,mc.product_id,mc.quantity,mc.price,mc.totalprice,mc.subtitle,mc.main_image,mc.status from mmall_cart mc left join mmall_user mu on mu.id=mc.user_id where mc.status=1 and user_id=?",
                    new BeanListHandler<>(CartBean.class),user_id);
            System.out.println(cart);
            //将查询到的数据封装成自定义的响应对象返回
            ResponseSet<List<CartBean>> set = new ResponseSet<>(200,"查询成功",cart);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(),set);
        }catch (Exception e){
            ResponseSet<List<CartBean>> set = new ResponseSet<>(500,"访问失败",null);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(),set);
            e.printStackTrace();
        }

    }

    /**
     * 添加数据到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        //获取传递的参数
        Integer product_id = Integer.parseInt(req.getParameter("product_id"));
        Integer quantity =  Integer.parseInt(req.getParameter("quantity"));
        double price =Double.valueOf(req.getParameter("price")) ;
        String userName =(String)req.getSession().getAttribute("userName");
        String main_image = req.getParameter("img");
        String subtitle = req.getParameter("shopName");
        Double totalprice = quantity*price;
        Mmall_UserBean user = null;
        PrintWriter out = resp.getWriter();
        int i = 0;
        //如果用户没有登录，返回参数让用户确定是否要登录
        if(userName != null){
            user = serviceImplement.selectOneByUserName(userName);
            i=cartServiceImplement.addCart(user.getId(),product_id,quantity,
                    price,totalprice,subtitle,main_image,1);
            out.write("false");
        }else{
            //前端收到参数会弹出提示框提示用户登录
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
