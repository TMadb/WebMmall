package service;

import JavaBean.Mmall_UserBean;

public interface Mmall_UserService {

    int insertUser(String sql,Object... params);

    int updatePassword(String newPassword,String oldPassword);

    Mmall_UserBean login(String userName,String password);
}
