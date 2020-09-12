package entity;

import java.math.BigDecimal;

public class CartItem {

    //商品的对象
    private ProductBean productBean;
    //购物总数
    private Integer count;
    //返回商品小计
    public double getSubtotal(){
        BigDecimal price = new BigDecimal(productBean.getPrice()+"");
        BigDecimal count = new BigDecimal(this.count+"");
        return price.multiply(count).doubleValue();
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CartItem(ProductBean productBean, Integer count) {
        this.productBean = productBean;
        this.count = count;
    }

    public CartItem() {
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productBean=" + productBean +
                ", count=" + count +
                '}';
    }
}
