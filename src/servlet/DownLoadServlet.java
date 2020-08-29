package servlet;

/*
下载
 */

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(value = "/DownLoadServlet",loadOnStartup = 1)
public class DownLoadServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");

        resp.setHeader("Content-Disposition","attachment;fileName="+fileName);

        //设置文件的原始地址
        String path = req.getServletContext().getRealPath("/img/");
        //创建输入、输出流
        FileInputStream in = new FileInputStream(new File(path+fileName));
        ServletOutputStream out = resp.getOutputStream();
        //创建读取容器和存放长度的变量
        try {
            byte[] con = new byte[512];
            int temp = 0;
            while((temp = in.read(con))!=-1){
                out.write(con,0,temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out!=null){
                out.close();
            }
            if(in!=null){
                in.close();
            }
        }
    }
}
