package service.serviceImplement;

import JavaBean.Mmall_UserBean;
import dao.daoImplement.Mmall_UserDaoImplement;
import service.Mmall_UserService;

public class Mmall_UserServiceImplement implements Mmall_UserService {

    Mmall_UserDaoImplement mmall_userDaoImplement = new Mmall_UserDaoImplement();
    @Override
    public int insertUser(String sql, Object... params) {
        int con = mmall_userDaoImplement.insertUser(sql,params);
        if(con > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePassword(String newPassword, String oldPassword) {
        int con = mmall_userDaoImplement.updatePassword(newPassword,oldPassword);
        return con;
    }

    @Override
    public Mmall_UserBean login(String userName, String password) {
        Mmall_UserBean userBean = mmall_userDaoImplement.selectOneMmall_User(userName,password);
        return userBean;
    }
}
