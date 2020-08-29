package service.serviceImplement;

import JavaBean.PageBean;
import JavaBean.ProductBean;
import dao.daoImplement.ProductDaoImplement;
import service.ProductService;

import java.util.List;

public class ProductServiceImplement implements ProductService {
    ProductDaoImplement dao = new ProductDaoImplement();
    @Override
    public List<ProductBean> selectAllProducts() {
        return dao.selectAllProducts();
    }

    @Override
    public ProductBean selectProductById(int id) {
        return dao.selectProductById(id);
    }

    @Override
    public int selectCountProduct() {
        return dao.selectCountProduct();
    }

    @Override
    public List<ProductBean> selectAllProductsByPageBean(PageBean page) {
        return dao.selectAllProductsByPageBean(page);
    }

    @Override
    public List<ProductBean> selectAllProductBySort(String condition) {
        return dao.selectAllProductBySort(condition);
    }
}
