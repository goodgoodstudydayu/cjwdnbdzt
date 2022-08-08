import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
public class hiveText {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://hadoop102:10000";
    private static String user = "root";
    private static String password = "";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // 加载驱动、创建连接
    @Before
    public void init() throws Exception {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }

    // 创建数据库
    @Test
    public void createDatabase() throws Exception {
        String sql = "create database hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询所有数据库
    @Test
    public void showDatabases() throws Exception {
        String sql = "show databases";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
    @After
    public void destory() throws Exception {
        if ( rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
