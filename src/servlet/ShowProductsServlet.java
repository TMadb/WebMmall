package servlet;

/*
加载商品列表
 */

import com.chinasofti.commons.CommonUtils;
import com.chinasofti.jdbc.JdbcUtils;
import com.chinasofti.jdbc.TxQueryRunner;
import entity.PageBean;
import entity.ProductBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.serviceImplement.ProductServiceImplement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowProductsServlet",value = "/showProducts")
public class ShowProductsServlet extends HttpServlet {

    //查询数据
    ProductServiceImplement productServiceImplement = new ProductServiceImplement();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置网页响应格式
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String opr = request.getParameter("opr");
        String condition = request.getParameter("condition");
        if(opr.equals("show")){
            showProducts(request,response);
        }else if(opr.equals("showDetails")){
            showProductsDetailes(request,response);
        }else if(opr.equals("showproductBysort")){
            showproductBysort(request,response,condition);
        }
//        else if(opr.equals("one")){
//            showOneById(request,response,id);
//        }
    }

    private void showOnDetail(HttpServletRequest request, HttpServletResponse response, int id)
            throws ServletException, IOException{
        ProductBean productBean = productServiceImplement.selectProductById(id);
        System.out.println("cg");
        request.setAttribute("productOne", productBean);
        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
    }

//    private void showOneById(HttpServletRequest request, HttpServletResponse response, int id)
//            throws ServletException, IOException{
//           //查询数据
//           ProductBean productBean = productServiceImplement.selectProductById(id);
//           request.setAttribute("product",productBean);
//           request.getRequestDispatcher("productDetail.jsp").forward(request, response);
//    }

    private void showproductBysort(HttpServletRequest request, HttpServletResponse response,String condition)
            throws ServletException, IOException{
        List<ProductBean> products = productServiceImplement.selectAllProductBySort(condition);
        JSONArray array = JSONArray.fromObject(products);
        PrintWriter out = response.getWriter();
        out.write(array.toString());
    }

    protected void showProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<ProductBean> products = productServiceImplement.selectAllProducts();
        JSONArray array = JSONArray.fromObject(products);
        PrintWriter out = response.getWriter();
        out.write(array.toString());

    }

    protected void showProductsDetailes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //获取Ajax传过来的数据
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //总记录数
        int count = productServiceImplement.selectCountProduct();
        //新建Page对象为分页查询做准备
        PageBean pageBean = new PageBean();
        pageBean.setConut(count);
        pageBean.setCurrentPage(Integer.parseInt(currentPage));
        pageBean.setPageSize(Integer.parseInt(pageSize));
        //根据分页查询
        List<ProductBean> productBeans = productServiceImplement.selectAllProductsByPageBean(pageBean);
        //对查询出的结果集进行格式处理，将之转换为JSONArray格式
        JSONArray result = JSONArray.fromObject(productBeans);
        //再一次进行转换,将之转换为JSONObject
        JSONObject object = new JSONObject();
        //将获取的数据添加到JSONObject中
        object.accumulate("currentPage",pageBean.getCurrentPage());
        object.accumulate("totalPage",pageBean.getTotalPage());
        object.accumulate("products",result);
        PrintWriter out = null;
        out = response.getWriter();
        out.write(object.toString());
    }
}
