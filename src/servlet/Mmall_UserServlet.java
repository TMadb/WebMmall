package servlet;

/**
 * 用户操作
 */

import entity.Cart;
import entity.Mmall_UserBean;
import com.chinasofti.commons.CommonUtils;
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
import java.util.Date;

@WebServlet(name = "Mmall_UserServlet",value = "/register")
public class Mmall_UserServlet extends HttpServlet {

    //查询数据库
    Mmall_UserServiceImplement userServiceImplement = new Mmall_UserServiceImplement();
    BaseDao dao = new BaseDao();
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
            case("checkUserName"):
                String userName = request.getParameter("userName");
                PrintWriter out = response.getWriter();
                Mmall_UserBean user = (Mmall_UserBean)dao.selectOne("select * from mmall_user where username=?", Mmall_UserBean.class,
                        userName);
                if(user!=null){
                    String result = "true";
                    out.write(result);
                }else{
                    String result = "false";
                    out.write(result);
                }
                break;
            case("checkYzm"):
                String yzm = request.getParameter("wyzm");
                String nyzm = yzm.toUpperCase();
                String wyzm = String.valueOf(request.getSession().getAttribute("zcyzm"));
                String nwyzm = wyzm.toUpperCase();
                PrintWriter outer = response.getWriter();
                if(nwyzm.equals(nyzm) ){
                    String result = "true";
                    outer.write(result);
                }else{
                    String result = null;
                    if(yzm.equals("") ){
                        result = "nullError";
                        outer.write(result);
                    }else if(yzm.length()<4 || yzm.length()>4){
                        result = "lengthError";
                        outer.write(result);
                    }else{
                        result = "false";
                        outer.write(result);
                    }

                }
                break;
        }
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void loginOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //销毁用户session
        request.getSession().invalidate();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String userName = request.getParameter("account");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        String ncaptcha = captcha.toUpperCase();
        String yzm = String.valueOf(request.getSession().getAttribute("yzm"));
        String nyzm  = yzm.toUpperCase();
        Mmall_UserBean userBean =userServiceImplement.login(userName,CommonUtils.getMD5String(password));
        if(userBean != null && ncaptcha.equals(nyzm)){
            //将登陆的用户名存储
            HttpSession session = request.getSession();
            session.setAttribute("userName",userBean.getUsername());
            //登录发车
            request.getSession().setAttribute("session_cart",new Cart());
            System.out.println(request.getSession().getAttribute("session_cart"));
            response.sendRedirect("index.jsp");
        }else{
            if(userBean == null || userBean.getUsername() != userName){
                request.setAttribute("user", "用户名错误");
            }
            if(userBean == null || userBean.getPassword() != password){
                request.setAttribute("password", "密码错误");
            }
            if(captcha == null || captcha.length() < 4 || captcha.length() > 4){
                request.setAttribute("yzm","验证码错误" );
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
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

    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
        protected void register (HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            //获取参数
            String userName = request.getParameter("account");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            int i = userServiceImplement.addUser(userName,CommonUtils.getMD5String(password),
                    email,phone,new Date(),new Date());
            if(userName == null){
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if(i > 0){
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{

                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
}
