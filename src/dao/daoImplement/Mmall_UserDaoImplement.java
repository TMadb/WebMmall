package dao.daoImplement;

/*
dao层实现类
 */

import JavaBean.Mmall_UserBean;
import dao.BaseDao;
import dao.Mmall_UserDao;

public class Mmall_UserDaoImplement extends BaseDao<Mmall_UserBean> implements Mmall_UserDao {

    @Override
    public int insertUser(String sql, Object... params) {
        int con = executeUpdate(sql);
        if(con > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public Mmall_UserBean selectOneMmall_User(String userName, String password) {
        String sql = "select * from mmall_user where username =? and password =? ";
        Mmall_UserBean userBean = selectOne(sql,Mmall_UserBean.class,userName,password);
        return userBean;
    }

    @Override
    public int updatePassword(String newPassword, String oldPassword) {
        String sql = "update mmall_user set password=? where password=? ";
        int con = executeUpdate(sql,newPassword,oldPassword);
        return con;
    }
}
