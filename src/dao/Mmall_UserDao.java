package dao;

import JavaBean.Mmall_UserBean;

public interface Mmall_UserDao {


    int insertUser(String sql,Object...params);

    Mmall_UserBean selectOneMmall_User(String userName,String password);
    //修改密码
    int updatePassword(String newPassword,String oldPassword);
}
