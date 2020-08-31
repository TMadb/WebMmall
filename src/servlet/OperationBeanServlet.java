package servlet;

import entity.ProductBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OperationBeanServlet")
public class OperationBeanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>\n" +
                "<html >\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title th:text=\"|购物车_${application.name}|\">购物车</title>\n" +
                "<link type=\"text/css\" rel=\"stylesheet\" href=\"css/index.css\" />\n" +
                "<script>\n" +
                "\t\t\n" +
                "</script>\n" +
                "</head>\n" +
                "<body class=\"second\">\n" +
                "\t<div class=\"brand_list container_2\">\n" +
                "\t\t<div ></div>\n" +
                "\t\t<div class=\"wrapper clearfix\">\n" +
                "\t\t\t<div class=\"position mt_10\">\n" +
                "\t\t\t\t<span>您当前的位置：</span> <a > 首页</a> » 购物车\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div class=\"myshopping m_10\">\n" +
                "\t\t\t\t<ul class=\"order_step\">\n" +
                "\t\t\t\t\t<li class=\"current\"><span class=\"first\">1、查看购物车</span></li>\n" +
                "\t\t\t\t\t<li><span>2、填写核对订单信息</span></li>\n" +
                "\t\t\t\t\t<li class=\"last\"><span>3、成功提交订单</span></li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<form id=\"form1\" method=\"post\">\n" +
                "\t\t\t\t<input type=\"hidden\" name=\"opr\" value=\"initadd\" />\n" +
                "\t\t\t\t<table width=\"100%\" class=\"cart_table m_10\">\n" +
                "\t\t\t\t\t<col width=\"65px\" />\n" +
                "\t\t\t\t\t<col width=\"115px\" />\n" +
                "\t\t\t\t\t<col width=\"400px\" />\n" +
                "\t\t\t\t\t<col width=\"80px\" />\n" +
                "\t\t\t\t\t<col width=\"80px\" />\n" +
                "\t\t\t\t\t<col width=\"80px\" />\n" +
                "\t\t\t\t\t<caption>查看购物车</caption>\n" +
                "\t\t\t\t\t<thead>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<th><input type=\"checkbox\" id=\"selAll\">&nbsp;全选</th>\n" +
                "\t\t\t\t\t\t\t<th>图片</th>\n" +
                "\t\t\t\t\t\t\t<th>商品名称</th>\n" +
                "\t\t\t\t\t\t\t<th>单价</th>\n" +
                "\t\t\t\t\t\t\t<th>数量</th>\n" +
                "\t\t\t\t\t\t\t<th>小计</th>\n" +
                "\t\t\t\t\t\t\t<th class=\"last\">操作</th>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</thead>\n" +
                "\t\t\t\t\t<tbody id=\"bookList\">");
        List<ProductBean> products = (List<ProductBean>) request.getSession().getAttribute("products");
        int count = 0;
        for(ProductBean productBean : products){
            count = productBean.getCount();
            count+=1;
            productBean.setCount(count);
        }
        //显示添加到购物车中产品列表
        if(request.getSession().getAttribute("products") !=null){
            for(ProductBean product:products){
                out.write("<tr>\n" +
                        "\t\t\t\t\t\t\t<td><input type=\"checkbox\"\n" +
                        "\t\t\t\t\t\t\t\t/></td>\n" +
                        "\t\t\t\t\t\t\t<td><img src='productsImage\\"+product.getMain_image()+"' width=\"66px\"\n" +
                        "\t\t\t\t\t\t\t\theight=\"66px\"  /></td>\n" +
                        "\t\t\t\t\t\t\t<td class=\"t_l\"><a\n" +
                        "\t\t\t\t\t\t\t\tclass=\"blue\" >"+product.getSubtitle()+"</a></td>\n" +
                        "\t\t\t\t\t\t\t<td>￥<b\n" +
                        "\t\t\t\t\t\t\t\t>"+product.getPrice()+"</b></td>\n" +
                        "\t\t\t\t\t\t\t<td>\n" +
                        "\t\t\t\t\t\t\t\t<div class=\"num\">\n" +
                        "\t\t\t\t\t\t\t\t\t<a class=\"reduce\" href=\"shopcar\" >-</a> <input\n" +
                        "\t\t\t\t\t\t\t\t\t\t class=\"tiny\"\n" +
                        "\t\t\t\t\t\t\t\t\t\ttype=\"text\"  value=\""+product.getCount()+"\"> <a\n" +
                        "\t\t\t\t\t\t\t\t\t\tclass=\"add\" href=\"shopcar\"\n" +
                        "\t\t\t\t\t\t\t\t\t\t>+</a>\n" +
                        "\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t<td>￥<b class=\"red2\"\n" +
                        "\t\t\t\t\t\t\t\t>0</b></td>\n" +
                        "\t\t\t\t\t\t\t<td><a href=\"javascript:void(0)\"\n" +
                        "\t\t\t\t\t\t\t\t>删除</a></td>\n" +
                        "\t\t\t\t\t\t</tr>");

            }

        }

        out.write("</tbody>\n" +
                "\t\t\t\t\t<tfoot>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td colspan=\"2\" class=\"t_l\"></td>\n" +
                "\t\t\t\t\t\t\t<td colspan=\"6\" class=\"t_r\"><a class=\"btn_continue\" href=\"\">继续购物</a>\n" +
                "\t\t\t\t\t\t\t\t<a class=\"btn_pay\" href=\"javascript:finish();\">去结算</a></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</tfoot>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</form>\n" +
                "\t\t\t<div class=\"box\">\n" +
                "\t\t\t\t<div class=\"title\">热门商品推荐</div>\n" +
                "\t\t\t</div>\n" +
                "\n" +
                "\t\t</div>\n" +
                "\t\t<div ></div>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        //显示购物车的产品列表

        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>\n" +
                "<html >\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title th:text=\"|购物车_${application.name}|\">购物车</title>\n" +
                "<link type=\"text/css\" rel=\"stylesheet\" href=\"css/index.css\" />\n" +
                "<script>\n" +
                "\t\t\n" +
                "</script>\n" +
                "</head>\n" +
                "<body class=\"second\">\n" +
                "\t<div class=\"brand_list container_2\">\n" +
                "\t\t<div ></div>\n" +
                "\t\t<div class=\"wrapper clearfix\">\n" +
                "\t\t\t<div class=\"position mt_10\">\n" +
                "\t\t\t\t<span>您当前的位置：</span> <a > 首页</a> » 购物车\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div class=\"myshopping m_10\">\n" +
                "\t\t\t\t<ul class=\"order_step\">\n" +
                "\t\t\t\t\t<li class=\"current\"><span class=\"first\">1、查看购物车</span></li>\n" +
                "\t\t\t\t\t<li><span>2、填写核对订单信息</span></li>\n" +
                "\t\t\t\t\t<li class=\"last\"><span>3、成功提交订单</span></li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<form id=\"form1\" method=\"post\">\n" +
                "\t\t\t\t<input type=\"hidden\" name=\"opr\" value=\"initadd\" />\n" +
                "\t\t\t\t<table width=\"100%\" class=\"cart_table m_10\">\n" +
                "\t\t\t\t\t<col width=\"65px\" />\n" +
                "\t\t\t\t\t<col width=\"115px\" />\n" +
                "\t\t\t\t\t<col width=\"400px\" />\n" +
                "\t\t\t\t\t<col width=\"80px\" />\n" +
                "\t\t\t\t\t<col width=\"80px\" />\n" +
                "\t\t\t\t\t<col width=\"80px\" />\n" +
                "\t\t\t\t\t<caption>查看购物车</caption>\n" +
                "\t\t\t\t\t<thead>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<th><input type=\"checkbox\" id=\"selAll\">&nbsp;全选</th>\n" +
                "\t\t\t\t\t\t\t<th>图片</th>\n" +
                "\t\t\t\t\t\t\t<th>商品名称</th>\n" +
                "\t\t\t\t\t\t\t<th>单价</th>\n" +
                "\t\t\t\t\t\t\t<th>数量</th>\n" +
                "\t\t\t\t\t\t\t<th>小计</th>\n" +
                "\t\t\t\t\t\t\t<th class=\"last\">操作</th>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</thead>\n" +
                "\t\t\t\t\t<tbody id=\"bookList\">");
        List<ProductBean> products = (List<ProductBean>) request.getSession().getAttribute("products");
        int count = 0;
        for(ProductBean productBean : products){
            count = productBean.getCount();

            if(count > 0){
                count-=1;
            }
            if(count ==0 ){
                count-=0;
            }
            productBean.setCount(count);
        }
        //显示添加到购物车中产品列表
        if(request.getSession().getAttribute("products") !=null){
            for(ProductBean product:products){
                out.write("<tr>\n" +
                        "\t\t\t\t\t\t\t<td><input type=\"checkbox\"\n" +
                        "\t\t\t\t\t\t\t\t/></td>\n" +
                        "\t\t\t\t\t\t\t<td><img src='productsImage\\"+product.getMain_image()+"' width=\"66px\"\n" +
                        "\t\t\t\t\t\t\t\theight=\"66px\"  /></td>\n" +
                        "\t\t\t\t\t\t\t<td class=\"t_l\"><a\n" +
                        "\t\t\t\t\t\t\t\tclass=\"blue\" >"+product.getSubtitle()+"</a></td>\n" +
                        "\t\t\t\t\t\t\t<td>￥<b\n" +
                        "\t\t\t\t\t\t\t\t>"+product.getPrice()+"</b></td>\n" +
                        "\t\t\t\t\t\t\t<td>\n" +
                        "\t\t\t\t\t\t\t\t<div class=\"num\">\n" +
                        "\t\t\t\t\t\t\t\t\t<a class=\"reduce\" href=\"shopcar\" >-</a> <input\n" +
                        "\t\t\t\t\t\t\t\t\t\t class=\"tiny\"\n" +
                        "\t\t\t\t\t\t\t\t\t\ttype=\"text\"  value=\""+product.getCount()+"\"> <a\n" +
                        "\t\t\t\t\t\t\t\t\t\tclass=\"add\" href=\"shopcar\"\n" +
                        "\t\t\t\t\t\t\t\t\t\t>+</a>\n" +
                        "\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t<td>￥<b class=\"red2\"\n" +
                        "\t\t\t\t\t\t\t\t>0</b></td>\n" +
                        "\t\t\t\t\t\t\t<td><a href=\"javascript:void(0)\"\n" +
                        "\t\t\t\t\t\t\t\t>删除</a></td>\n" +
                        "\t\t\t\t\t\t</tr>");

            }

        }

        out.write("</tbody>\n" +
                "\t\t\t\t\t<tfoot>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td colspan=\"2\" class=\"t_l\"></td>\n" +
                "\t\t\t\t\t\t\t<td colspan=\"6\" class=\"t_r\"><a class=\"btn_continue\" href=\"\">继续购物</a>\n" +
                "\t\t\t\t\t\t\t\t<a class=\"btn_pay\" href=\"javascript:finish();\">去结算</a></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</tfoot>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</form>\n" +
                "\t\t\t<div class=\"box\">\n" +
                "\t\t\t\t<div class=\"title\">热门商品推荐</div>\n" +
                "\t\t\t</div>\n" +
                "\n" +
                "\t\t</div>\n" +
                "\t\t<div ></div>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>");

    }
}
