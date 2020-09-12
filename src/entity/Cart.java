package entity;

import org.apache.commons.collections.map.HashedMap;

import javax.servlet.annotation.WebServlet;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value="/sessionCart")
public class Cart {
    /**
     * 购物车条目去重(添加多条商品时应该值改变数量，而不是再加一个条目)，用HashMap的去重
     * 机制刚好解决这个问题
     */
    private Map<String,CartItem> cartItems = new HashMap<>();

    //变量集合时返回集合的长度
    public Collection<CartItem> getCartItems(){
        return this.cartItems.values();
    }

    //计算购物车的总价
    public double getTotalPrice(){
        BigDecimal total = new BigDecimal("0");
            for(CartItem cartItem : cartItems.values()){
            BigDecimal subTotal = new  BigDecimal(cartItem.getSubtotal());
            total = total.add(subTotal);
        }
        return total.doubleValue();
    }

    //添加购物车
    public void addCart(CartItem cartItem){
        //添加前先判断购物车中是否存在该商品
        if(cartItems.containsKey(String.valueOf(cartItem.getProductBean().getId()))){
            CartItem oldCartItem = cartItems.get(cartItem.getProductBean().getId());
            oldCartItem.setCount(oldCartItem.getCount()+cartItem.getCount());
            cartItems.put(String.valueOf(cartItem.getProductBean().getId()),oldCartItem);
        }else {
            //没有直接添加
            cartItems.put(String.valueOf(cartItem.getProductBean().getId()),cartItem);
        }
    }

    //删除指定的商品
    public void removeOne(Integer pid){
        cartItems.remove(pid);
    }

    //清空购物车
    public void clearCart(){
        cartItems.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                '}';
    }
}
