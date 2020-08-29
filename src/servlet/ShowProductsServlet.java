package servlet;

/*
加载商品列表
 */

import JavaBean.PageBean;
import JavaBean.ProductBean;
import dao.BaseDao;
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
        }else if(opr.equals("showproductBysort") && condition.equals("黑枸杞")){
            showproductBysort(request,response,condition);
        }else if(opr.equals("showproductBysort") && condition.equals("红枸杞")){
            showproductBysort(request,response,condition);
        }
    }

    private void showproductBysort(HttpServletRequest request, HttpServletResponse response,String condition)
            throws ServletException, IOException{
        List<ProductBean> products = productServiceImplement.selectAllProductBySort(condition);
        JSONArray array = JSONArray.fromObject(products);
        try {
            PrintWriter out = response.getWriter();
            out.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void showProducts(HttpServletRequest request, HttpServletResponse response){

        List<ProductBean> products = productServiceImplement.selectAllProducts();
        JSONArray array = JSONArray.fromObject(products);
        try {
            PrintWriter out = response.getWriter();
            out.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void showProductsDetailes(HttpServletRequest request, HttpServletResponse response){
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
        try {
            out = response.getWriter();
            out.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//
//
//        out.write("<!DOCTYPE html\n" +
//                "\tPUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
//                "<html>\n" +
//                "\n" +
//                "<head>\n" +
//                "\t<meta charset=\"utf-8\" />\n" +
//                "\t<title >商品列表_蜗牛图书商城</title>\n" +
//                "\t<link type=\"text/css\" rel=\"stylesheet\" href=\"css/index.css\" />\n" +
//                "</head>\n" +
//                "\n" +
//                "<body class=\"index\">\n" +
//                "\t<div class=\"container\">\n" +
//                "\t\t<div ></div>\n" +
//                "\t\t<div ></div>\n" +
//                "\t\t<div ></div>\n" +
//                "\t\t<div class=\"wrapper clearfix container_2\">\n" +
//                "\t\t\t<div class=\"sidebar f_l\">\n" +
//                "\t\t\t\t<!--销售排行-->\n" +
//                "\t\t\t\t<div class=\"box m_10\">\n" +
//                "\t\t\t\t\t<div class=\"title\">销售排行榜</div>\n" +
//                "\t\t\t\t\t<div class=\"content\">\n" +
//                "\t\t\t\t\t\t<ul class=\"ranklist\" id='ranklist'>\n" +
//                "\t\t\t\t\t\t\t<li><span>1</span><a class=\"p_name\" href=\"\" target=\"_blank\">图书名</a></li>\n" +
//                "\t\t\t\t\t\t</ul>\n" +
//                "\t\t\t\t\t</div>\n" +
//                "\t\t\t\t</div>\n" +
//                "\t\t\t\t<!--销售排行-->\n" +
//                "\t\t\t</div>\n" +
//                "\n" +
//                "\t\t\t<div class=\"main f_r\" style=\"margin-top: 200px\">\n" +
//                "\t\t\t\t<ul class=\"display_list clearfix m_10\">");
//
//
//        for(ProductBean product:products){
//            out.write("<li class=\"clearfix win\">\n" +
//                    "\t\t\t\t\t\t<div class=\"pic\">\n" +
//                    "\t\t\t\t\t\t\t<a href=\"\" target=\"_blank\"><img src=\"productsImage\\"+product.getMain_image()+"\" width=\"200\" height=\"200\" /></a>\n" +
//                    "\t\t\t\t\t\t</div>\n" +
//                    "\t\t\t\t\t\t<h3 class=\"title\">\n" +
//                    "\t\t\t\t\t\t\t<a class=\"p_name\" href=\"\" target=\"_blank\">"+product.getSubtitle()+"</a>\n" +
//                    "\t\t\t\t\t\t\t<span>总销量：0(0人评论\n" +
//                    "\t\t\t\t\t\t\t\t)</span><span class=\"grade\" lay-data=\"\"><i style=\"width: 56px\"></i></span>\n" +
//                    "\t\t\t\t\t\t</h3>\n" +
//                    "\t\t\t\t\t\t<div class=\"handle\">\n" +
//                    "\t\t\t\t\t\t\t<label class=\"btn_gray_m\"><img src=\"css/images/ucenter/shopping.jpg\" width=\"15\"\n" +
//                    "\t\t\t\t\t\t\t\t\theight=\"15\" /><a href=\"addCart?productId="+product.getId()+"\">加入购物车</a>\n" +
//                    "\t\t\t\t\t\t\t\t\tonclick=\"joinCart_list(1);\" /></label>\n" +
//                    "\t\t\t\t\t\t</div>\n" +
//                    "\t\t\t\t\t\t<div class=\"price\">\n" +
//                    "\t\t\t\t\t\t\t￥0<s>￥>0</s>\n" +
//                    "\t\t\t\t\t\t</div>\n" +
//                    "\t\t\t\t\t</li>");
//        }
//
//        out.write("</ul>\n" +
//                "\t\t\t\t<div class='pages_bar'>\n" +
//                "\t\t\t\t\t<a href='javascript:void(0)' onclick=\"goPage(1)\">首页</a>\n" +
//                "\t\t\t\t\t<a>1</a>\n" +
//                "\t\t\t\t\t<a href='javascript:void(0)'>尾页</a>\n" +
//                "\t\t\t\t\t<span>当前第1页/共1页</span>\n" +
//                "\t\t\t\t</div>\n" +
//                "\t\t\t</div>\n" +
//                "\t\t</div>\n" +
//                "\t\t<div ></div>\n" +
//                "\t\t<div \"></div>\n" +
//                "\t</div>\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>");
//    }

    //    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }

}
