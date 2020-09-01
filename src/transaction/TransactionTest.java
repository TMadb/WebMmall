package transaction;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TransactionTest {

    public Connection getConnection() throws Exception {
        //原生的mysql连接
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/webdatabase?characterEncoding=utf8";
        String user = "root";
        String passwrod = "123456";
        return DriverManager.getConnection(url, user,passwrod);
    }

    @Test
    public void Test() throws Exception {
        System.out.println(getConnection());
    }

}
