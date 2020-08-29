package dao.daoImplement;

import JavaBean.PageBean;
import JavaBean.ProductBean;
import dao.BaseDao;
import dao.ProductDao;
import java.util.List;

public class ProductDaoImplement extends BaseDao<ProductBean> implements ProductDao {

    //查询所有的产品数据
    @Override
    public List<ProductBean> selectAllProducts() {
        String sql = "select * from mmall_product";
        List<ProductBean> productBeans = selectList(sql,ProductBean.class);
        return productBeans;
    }

    //通过Id查询单个数据
    @Override
    public ProductBean selectProductById(int id) {

        String sql = "select * from mmall_product where id=?";
        ProductBean productBean = null;
        if(selectOne(sql,ProductBean.class,id)!=null){
            productBean = selectOne(sql,ProductBean.class,id);
        }
        return productBean;
    }

    //分页查询数据
    @Override
    public List<ProductBean> selectAllProductsByPageBean(PageBean page) {
        String sql = "select * from mmall_product limit ?,?";
        //从第几个开始显示
        int index = (page.getCurrentPage()-1)*page.getPageSize();
        //查询数据
        List<ProductBean> productBeanList = selectList(sql,ProductBean.class,index,page.getPageSize());
        //判断返回的数据是否可用，如果不为空，则保存
        if(productBeanList != null){
            return productBeanList;
        }
        return null;
    }

    //统计查询
    @Override
    public int selectCountProduct() {
        String sql = "select count(*) from mmall_product";
        return selectCount(sql);
    }

    @Override
    public List<ProductBean> selectAllProductBySort(String condition) {
        String sql = "select * from mmall_product where subtitle like ";
        String con = ""+"'%"+condition+"%'"+"";
        String order = "order by price asc";
        sql = sql.concat(con).concat(order);
        return selectList(sql,ProductBean.class);
    }
}
