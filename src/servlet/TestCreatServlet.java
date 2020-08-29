package servlet;

/*
Cookie测试
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/loginTestServlet")
public class TestCreatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Test</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <form action=\"cookieTestServlet\" method=\"post\">\n" +

                            "  <label>用户:</label>\n" +
                            "  <input type=\"text\" name=\"userName\" /><br>\n" +
                            "  <label>密码:</label>\n" +
                            "  <input type=\"text\" name=\"password\" /><br>\n" +
                            "  <input type=\"checkbox\" name=\"xz\" value=\"true\" />记住密码<br>\n"+
                    "          <input type=\"submit\" />\n" +
                    "  </form>\n" +
                    "</body>\n" +
                    "</html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
