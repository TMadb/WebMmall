package servlet;

import JavaBean.Mmall_UserBean;
import dao.BaseDao;
import service.serviceImplement.Mmall_UserServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Mmall_UserServlet",value = "/register")
public class Mmall_UserServlet extends HttpServlet {

    Mmall_UserServiceImplement userServiceImplement = new Mmall_UserServiceImplement();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置响应及请求的编码格式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String opration = request.getParameter("opr");
        switch (opration) {
            case ("register"):
                register(request, response);
                break;
            case ("updatepass"):
                updatepass(request, response);
                break;
            case ("login"):
                loginUser(request,response);
                break;
            case ("out"):
                loginOut(request,response);
                break;
        }
    }

    private void loginOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //销毁用户session
        request.getSession().invalidate();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String userName = request.getParameter("account");
        String password = request.getParameter("password");
        Mmall_UserBean userBean =userServiceImplement.login(userName,password);
        if(userBean != null){
            //将登陆的用户名存储
            HttpSession session = request.getSession();
            session.setAttribute("userName",userBean.getUsername());
            response.sendRedirect("index.jsp");
        }else{
            System.out.println("登录失败");
            HttpSession session = request.getSession();
            session.setAttribute("login", "登录失败,请检查用户名和密码是否正确");
            response.sendRedirect("login.jsp");
        }
    }

    protected void updatepass (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            //获取相应的值
            String password = request.getParameter("oldpass");
            String newpassword = request.getParameter("newpass");
            System.out.println(password);
            int con = userServiceImplement.updatePassword(newpassword, password);
            if (con > 0) {
                System.out.println("修改成功");

            } else {
                System.out.println("修改失败");
            }

        }

        protected void register (HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException{
            //获取值
            String username = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String passquestion = request.getParameter("passquestion");
            String passanswer = request.getParameter("passanswer");
            String captcha = request.getParameter("captcha");
            BaseDao baseDao = new BaseDao();
            String sql1 = "select username from mmall_user";
            Mmall_UserBean userbean = (Mmall_UserBean) baseDao.selectOne(sql1, Mmall_UserBean.class);
            PrintWriter out = response.getWriter();
            if (userbean.getUsername().equals(username)) {
                System.out.println(userbean.getUsername());
                out.write(0);
            } else {
                out.write(1);
            }
            //格式化时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (userbean == null) {
                String sql = "INSERT INTO mmall_user VALUES (default, '" + username + "', '" + password + "', '" + email + "', '" + phone + "', '" +
                        passquestion + "', '" + passanswer + "', '" + 1 + "', '2020-08-06 18:09:48'," + simpleDateFormat.format(new Date()) + ")";
                Mmall_UserServiceImplement mmallUserServiceImplement = new Mmall_UserServiceImplement();
                //,username,password,email,phone,passquestion,passanswer
                int con = mmallUserServiceImplement.insertUser(sql);
                if (con > 0) {
                    System.out.println("注册成功");
                } else {
                    System.out.println("注册失败，请重试（可能是数据库中有同名的用户）");
                }
            } else {
                System.out.println("数据库中有同名的用户,请重试");
            }
        }
}
