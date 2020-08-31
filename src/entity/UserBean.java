package entity;

public class UserBean {

    private int userid;

    private String username;

    private String password;

    private int count=0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBean(int userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public UserBean() {
    }
}
