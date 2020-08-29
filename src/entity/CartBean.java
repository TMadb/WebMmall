package entity;

/*
购物车
 */

import java.util.Date;

public class CartBean {

    private Integer id ;

    private Mmall_UserBean user;

    //存储哪件商品，查询自数据库，根据点击的图片返回的id查询
    private ProductBean product;

    private Integer quantity;

    private Double totalprice;

    private Date create_time;

    private Date update_time;

    public CartBean(Integer id, Mmall_UserBean user, ProductBean product, Integer quantity, Double totalprice, Date create_time, Date update_time) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public CartBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mmall_UserBean getUser() {
        return user;
    }

    public void setUser(Mmall_UserBean user) {
        this.user = user;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
