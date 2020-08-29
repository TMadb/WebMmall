package servlet;

/*
注册界面
 */

import JavaBean.EmployeeBean;
import dao.BaseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/RegisterServlet",loadOnStartup = 1)
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注册的字段
        String userName = req.getParameter("account");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        BaseDao dao = new BaseDao();
        //数据库操作语句
        String sql = "insert into employeeuser values('"+userName+"','"+password+"','"+email+"')";

        if(dao.executeUpdate(sql) > 0){
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }
    }
}
