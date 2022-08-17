package Entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("teacher")
@Data
public class Teacher {
    @TableId("id")
    private Integer id;
    @TableField("teacher_name")
    private String teacherName;
    @TableField("teacher_id")
    private Integer teacherId;
    @TableField("email")
    private String email;
    @TableField("phone")
    private String phone;
}