import Utils.hdfsUtil;
import Utils.hiveUtil;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.Statement;

public class hdfsWithHive {
    public static void main(String[] args) throws Exception{
        FileSystem fs = hdfsUtil.getFileSystem(new URI("hdfs://hadoop101:8020"), "hdfs");
        Statement stmt = hiveUtil.getHiveJdbc();

        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\course.txt"),
                new Path("/hivetext/course"));
        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\teacher.txt"),
                new Path("/hivetext/teacher"));
        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\student.txt"),
                new Path("/hivetext/student"));
        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\score.txt"),
                new Path("/hivetext/score"));
        String create_student = "create table if not exists hive_jdbc_test.students(\n" +
                "s_id\tstring,\n" +
                "name\tstring,\n" +
                "birthday\tdate,\n" +
                "sex\tstring)\n" +
                "row format delimited fields terminated by '\\t'";

        String create_score = "create table if not exists hive_jdbc_test.score(\n" +
                "s_id\tstring,\n" +
                "c_id\tstring,\n" +
                "s_score\tint)\n" +
                "row format delimited fields terminated by '\\t'";

        String create_course = "create table if not exists hive_jdbc_test.course(\n" +
                "c_id\tstring,\n" +
                "c_name\tstring,\n" +
                "c_teacher\tstring)\n" +
                "row format delimited fields terminated by '\\t'";

        String create_teacher = "create table if not exists hive_jdbc_test.teacher(\n" +
                "t_id\tstring,\n" +
                "t_name\tstring)\n" +
                "row format delimited fields terminated by '\\t'";

        stmt.execute("create database if not exists hive_jdbc_test");
        stmt.execute(create_student);
        stmt.execute(create_score);
        stmt.execute(create_course);
        stmt.execute(create_teacher);


        String studentPath = "/hivetext/student";
        String scorePath = "/hivetext/score";
        String coursePath = "/hivetext/course";
        String teacherPath = "/hivetext/teacher";

        String studentTable ="hive_jdbc_test.students";
        String scoreTable ="hive_jdbc_test.score";
        String courseTable ="hive_jdbc_test.course";
        String teacherTable ="hive_jdbc_test.teacher";

        hiveUtil.loadDataToTable(new Path(studentPath),studentTable,false);
        hiveUtil.loadDataToTable(new Path(scorePath),scoreTable,false);
        hiveUtil.loadDataToTable(new Path(coursePath),courseTable,false);
        hiveUtil.loadDataToTable(new Path(teacherPath),teacherTable,false);

        hiveUtil.descTable(studentTable);
        hiveUtil.selectData(studentTable);



        fs.close();
        hiveUtil.closeConnect();

    }

}
