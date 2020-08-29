package entity;

/*
订单条目表，一个订单表可以有多个订单条目表
 */

import java.util.Date;

public class Mmall_order_itemBean {

    private Integer id;

    private Mmall_UserBean userBean;

    private String order_no;

    private ProductBean product;

    private String product_name;

    private String product_img;

    private Integer crrent_unit_type;

    private Integer quantity;

    private Double total_price;

    private Date create_time;

    private Date update_time;

    public Mmall_order_itemBean(Integer id, Mmall_UserBean userBean, String order_no, ProductBean product, String product_name, String product_img, Integer crrent_unit_type, Integer quantity, Double total_price, Date create_time, Date update_time) {
        this.id = id;
        this.userBean = userBean;
        this.order_no = order_no;
        this.product = product;
        this.product_name = product_name;
        this.product_img = product_img;
        this.crrent_unit_type = crrent_unit_type;
        this.quantity = quantity;
        this.total_price = total_price;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Mmall_order_itemBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mmall_UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(Mmall_UserBean userBean) {
        this.userBean = userBean;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public Integer getCrrent_unit_type() {
        return crrent_unit_type;
    }

    public void setCrrent_unit_type(Integer crrent_unit_type) {
        this.crrent_unit_type = crrent_unit_type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
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

    @Override
    public String toString() {
        return "Mmall_order_itemBean{" +
                "id=" + id +
                ", userBean=" + userBean +
                ", order_no='" + order_no + '\'' +
                ", product=" + product +
                ", product_name='" + product_name + '\'' +
                ", product_img='" + product_img + '\'' +
                ", crrent_unit_type=" + crrent_unit_type +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
