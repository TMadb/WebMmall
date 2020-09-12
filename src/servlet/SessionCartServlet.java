package servlet;

import com.chinasofti.servlet.BaseServlet;
import entity.Cart;
import entity.CartItem;
import service.serviceImplement.ProductServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/SessionCartServlet")
public class SessionCartServlet extends BaseServlet {

    ProductServiceImplement productServiceImplement = new ProductServiceImplement();

    public String addCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("session_cart");
        PrintWriter out = response.getWriter();
        if(cart != null){
            CartItem cartItem = new CartItem(
                    productServiceImplement.selectProductById(Integer.parseInt(request.getParameter("id")))
                    ,Integer.parseInt(request.getParameter("quantity"))
            );
            //将条目添加到购物车
            if(cartItem != null){
                cart.addCart(cartItem);
            }
            System.out.println(cart);
            return "f:/filter/cart.jsp";
        }else{
            out.write("total");
            return "f:/productDetail.jsp";
        }
    }
    //清空购物车
    public String clearCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart =(Cart) request.getSession().getAttribute("session_cart");
        cart.clearCart();
        return "f:/filter/cart.jsp";
    }

    public String removeOne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart =(Cart) request.getSession().getAttribute("session_cart");
        Integer id = Integer.parseInt(request.getParameter("id"));
        cart.removeOne(id);
        return "f:/filter/cart.jsp";
    }
}
