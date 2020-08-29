package servlet;

/*
文件上传
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/UploadFileServlet",loadOnStartup = 1)
@MultipartConfig
public class UploadFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        Part part = req.getPart("photo");
        String str = req.getServletContext().getRealPath("/img/");
        //获取提交的文件名
        String path = part.getSubmittedFileName();
        part.write(str+path);
        PrintWriter out = resp.getWriter();
        out.println("<html><body><img src='img/"+path+"'></body></html>");

    }
}
