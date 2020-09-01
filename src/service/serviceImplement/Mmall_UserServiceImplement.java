package service.serviceImplement;


import entity.Mmall_UserBean;
import dao.daoImplement.Mmall_UserDaoImplement;
import service.Mmall_UserService;
import java.util.Date;

public class Mmall_UserServiceImplement implements Mmall_UserService {

    Mmall_UserDaoImplement mmall_userDaoImplement = new Mmall_UserDaoImplement();

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

    @Override
    public int addUser(String userName, String passWord, String email, String phone, Date create_time, Date update_time) {
        int i = mmall_userDaoImplement.insertUser(userName,passWord,email,phone,create_time,update_time);
        return i;
    }

    @Override
    public Mmall_UserBean selectOneByUserName(String userName) {
        return mmall_userDaoImplement.queryOneByUserName(userName);
    }
}
