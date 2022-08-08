package Utils;

import org.apache.hadoop.fs.Path;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class hiveUtil {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://hadoop102:10000";
    private static String user = "root";
    private static String password = "";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    private static StringBuffer sb = null;
    private static Vector<String> columns = null;
    public static Statement getHiveJdbc()throws Exception{
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
        return stmt;
    }

//    "create table if not exists hive_jdbc_test.students(\n" +
//            "s_id\tstring,\n" +
//            "name\tstring,\n" +
//            "birthday\tdate,\n" +
//            "sex\tstring)\n" +
//            "row format delimited fields terminated by '\\t'";external table if not exists

    public static void creatTable(boolean isOut, String tableName,
                                  List<String> columns,List<String> type,String fenge)
                                  throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("create ");
        if (isOut){
            sb.append("external ");
        }
        sb.append("table if not exists ")
        .append(tableName)
        .append(" ( ");
        for (int i = 0; i < columns.size(); i++) {
            sb.append(columns.get(i)).append("\t").append(type.get(i)).append(",");
        }
        sb.deleteCharAt(sb.length()-1)
            .append(") ")
            .append("row format delimited fields terminated by '")
            .append(fenge)
            .append("' ");
        System.out.println(sb);
        //stmt.execute(sb.toString());
    }

    public static void loadDataToTable(Path path,String table,boolean isLocal) throws Exception{
        String doSome = null;
        //load data [local] inpath 'filepath' [overwrite] into table table_name [partition(part1=val1,part2=val2)]
        if (isLocal){
            doSome = "load data local inpath '" + path + "' overwrite into table "+table;
        }
        else {
            doSome = "load data inpath '" + path + "' overwrite into table "+table;
        }
        stmt.execute(doSome);
        System.out.println(table+"表数据已加载！");
    }


    public static ResultSet descTable(String table) throws Exception {
        String sql = "desc "+table;
        rs = stmt.executeQuery(sql);
        ResultSet result=rs;
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
        return result;
    }

    public static void getDesc(String table) throws Exception {
        String sql = "desc "+table;
        rs = stmt.executeQuery(sql);
        sb=new StringBuffer();
        columns=new Vector<>();
        while (rs.next()){
            columns.add(rs.getString(1));
            sb.append(rs.getString(2)).append("    ");
        }
    }


    public static void selectData(String table) throws Exception {
        String sql = "select * from "+table;
        getDesc(table);
        rs = stmt.executeQuery(sql);
        System.out.println(sb);
        while (rs.next()) {
            for (int i = 0; i < columns.size(); i++) {
                System.out.print(rs.getString(columns.get(i))+"    ");
            }
            System.out.println();
        }
    }


    public static void closeConnect() throws Exception{
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();
        columns.add("columns1");
        columns.add("columns2");
        type.add("string");
        type.add("string");
        creatTable(true,"textsuibian",columns,type,"\\t");
    }

}
