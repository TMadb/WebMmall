package servlet;

import entity.ProductBean;
import service.serviceImplement.ProductServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProductServlet",value = "/addCart")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        //获取到id
        String productId = request.getParameter("productId");
        HttpSession session = request.getSession();
        ProductBean productBean = null;
        if(productId!=null){
            //将id转换并保存
            int id = Integer.parseInt(productId);
            ProductServiceImplement productServiceImplement = new ProductServiceImplement();
            //查询数据库中有无此id
            productBean = productServiceImplement.selectProductById(id);
            //判断商品列表是否存在
            if(request.getSession().getAttribute("products") !=null){
                List<ProductBean> products = (List<ProductBean>) request.getSession().getAttribute("products");
                boolean isExsits = false;
                for (int i =0;i<products.size();i++){
                    if(products.get(i).getId() == productBean.getId()){
                        //1,如果存在，数量+1,再把修改后的集合重新放入session
                        products.get(i).setCount(products.get(i).getCount()+1);
                        isExsits = true;
                        break;
                    }

                }
                if(!isExsits){
                    //2,如果不存在,则将当前的产品对象添加到购物车的集合中
                    products.add(productBean);

                }
                request.getSession().setAttribute("products",products);

            }else {
                List<ProductBean> products = new ArrayList<>();
                products.add(productBean);
                request.getSession().setAttribute("products",products);
            }

            PrintWriter out =  response.getWriter();
            out.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "添加成功\n,<a href=\"shopcar\">去结算</a>" +
                    "</body>\n" +
                    "</html>");
            }
        }

    }

