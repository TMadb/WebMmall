package dao;

import entity.Mmall_UserBean;

import java.util.Date;

public interface Mmall_UserDao {


    Mmall_UserBean selectOneMmall_User(String userName,String password);
    //修改密码
    int updatePassword(String newPassword,String oldPassword);
    //插入数据
    int insertUser(String userName, String passWord, String email, String phone, Date create_time,Date update_time);

    Mmall_UserBean queryOneByUserName(String userName);
}
