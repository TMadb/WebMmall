package entity;

/*
订单表
 */

import java.util.Date;

public class Mmall_orderBean {

    private Integer id;
    //订单表中对应的user表的外键，表示一对一的关系
    private Mmall_UserBean user;

    private String order_no;

    private String shipping_id;

    private Integer payment;

    private Integer pament_type;

    private Integer postage;

    private Integer status;

    private Date payment_time;

    private Date send_time;

    private Date end_time;

    private Date close_time;

    private Date create_time;

    private Date update_time;

    public Mmall_orderBean() {
    }
    public Mmall_orderBean(Integer id, Mmall_UserBean user, String order_no, String shipping_id, Integer payment, Integer pament_type, Integer postage, Integer status, Date payment_time, Date send_time, Date end_time, Date close_time, Date create_time, Date update_time) {
        this.id = id;
        this.user = user;
        this.order_no = order_no;
        this.shipping_id = shipping_id;
        this.payment = payment;
        this.pament_type = pament_type;
        this.postage = postage;
        this.status = status;
        this.payment_time = payment_time;
        this.send_time = send_time;
        this.end_time = end_time;
        this.close_time = close_time;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Mmall_orderBean{" +
                "id=" + id +
                ", user=" + user +
                ", order_no='" + order_no + '\'' +
                ", shipping_id='" + shipping_id + '\'' +
                ", payment=" + payment +
                ", pament_type=" + pament_type +
                ", postage=" + postage +
                ", status=" + status +
                ", payment_time=" + payment_time +
                ", send_time=" + send_time +
                ", end_time=" + end_time +
                ", close_time=" + close_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
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

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getPament_type() {
        return pament_type;
    }

    public void setPament_type(Integer pament_type) {
        this.pament_type = pament_type;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
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
