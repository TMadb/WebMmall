package dao;

import JavaBean.PageBean;
import JavaBean.ProductBean;

import java.util.List;

public interface ProductDao {

    //查询数据库中的全部的商品
    List<ProductBean> selectAllProducts();

    //通过id的方式查询商品信息
    ProductBean selectProductById(int id);

    //页面查询方法
    List<ProductBean> selectAllProductsByPageBean(PageBean page);

    //查询产品数据库中的总记录数
    int selectCountProduct();
    //分类查询
    List<ProductBean> selectAllProductBySort(String condition);
}
