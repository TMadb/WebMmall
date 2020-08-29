package service;

/*
产品的接口
 */

import JavaBean.PageBean;
import JavaBean.ProductBean;

import java.util.List;

/*
服务接口
 */
public interface ProductService {
    //查询数据库中的全部的商品
    List<ProductBean> selectAllProducts();

    //通过id的方式查询商品信息
    ProductBean selectProductById(int id);

    //查询产品数据库中的总记录数
    int selectCountProduct();

    //页数查询方法
    List<ProductBean> selectAllProductsByPageBean(PageBean page);

    //分类查询
    public List<ProductBean> selectAllProductBySort(String condition);
}
