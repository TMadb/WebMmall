package entity;

/*
购物车
 */

import java.util.Date;

public class CartBean {

    private Integer id ;

    private Integer user_id;

    //存储哪件商品，查询自数据库，根据点击的图片返回的id查询
    private Integer product_id;

    private Integer quantity;

    private Double price;

    private Double totalprice;

    private String subtitle;

    private String main_image;

    public CartBean(Integer id, Integer user_id, Integer product_id, Integer quantity, Double totalprice, String subtitle, String main_image) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.subtitle = subtitle;
        this.main_image = main_image;
    }

    public CartBean() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "CartBean{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalprice=" + totalprice +
                ", subtitle=" + subtitle +
                ", main_image=" + main_image +
                '}';
    }
}
