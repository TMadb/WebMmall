package service;

import entity.Mmall_UserBean;

import java.util.Date;

public interface Mmall_UserService {

    int updatePassword(String newPassword,String oldPassword);

    Mmall_UserBean login(String userName,String password);

    int addUser(String userName, String passWord, String email, String phone, Date create_time, Date update_time);

    Mmall_UserBean selectOneByUserName(String userName);
}
