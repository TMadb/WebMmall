package servlet;

import com.chinasofti.commons.CommonUtils;
import dao.daoImplement.Mmall_UserDaoImplement;
import entity.CartBean;
import entity.Mmall_UserBean;
import service.serviceImplement.CartServiceImplement;
import service.serviceImplement.Mmall_UserServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartServlet",value = "/cartServlet")
public class CartServlet extends HttpServlet {

    Mmall_UserServiceImplement serviceImplement = new Mmall_UserServiceImplement();
    CartServiceImplement cartServiceImplement = new CartServiceImplement();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("text/html;charset=utf-8");
        resp.setContentType("utf-8");
        String option = req.getParameter("opr");
        if(option.equals("joinCart")){
            addCart(req,resp);
        }
    }

    private void addCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        //将传过来的数据转换为cart对象
        CartBean cart = CommonUtils.toBean(req.getParameterMap(), CartBean.class);
        String username = req.getParameter("name");
        PrintWriter out = resp.getWriter();
        if(username!=null){
            System.out.println(username);
            out.write("true");
        }else{
            out.write("false");
        }

//        Mmall_UserBean userBean = serviceImplement.selectOneByUserName(username);

    }
}
