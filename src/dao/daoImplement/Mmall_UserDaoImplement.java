package dao.daoImplement;

/*
dao层实现类
 */

import com.chinasofti.commons.CommonUtils;
import entity.Mmall_UserBean;
import dao.BaseDao;
import dao.Mmall_UserDao;

import java.util.Date;

public class Mmall_UserDaoImplement extends BaseDao<Mmall_UserBean> implements Mmall_UserDao {

    @Override
    public Mmall_UserBean selectOneMmall_User(String userName, String password) {
        String sql = "select * from mmall_user where username =? and password =? ";
        Mmall_UserBean userBean = selectOne(sql,Mmall_UserBean.class, userName,password);
        return userBean;
    }

    @Override
    public int updatePassword(String newPassword, String oldPassword) {
        String sql = "update mmall_user set password=? where password=? ";
        int con = executeUpdate(sql,newPassword,oldPassword);
        return con;
    }

    @Override
    public int insertUser(String userName, String passWord, String email, String phone, Date create_time, Date update_time) {
        String sql = "insert into mmall_user values(null,?,?,?,?,?,?)";
        int i = executeUpdate(sql,userName,passWord,email,phone,create_time,update_time );
        return i;
    }

    @Override
    public Mmall_UserBean queryOneByUserName(String userName) {
        String sql = "select * from mmall_user where username=?";
        Mmall_UserBean user = selectOne(sql,Mmall_UserBean.class,userName);
        return user;
    }
}
