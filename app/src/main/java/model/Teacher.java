package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by wxs on 16/8/18.
 */
@DatabaseTable(tableName = "teacher_tb")
public class Teacher implements Serializable {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private int teacherId;

    @DatabaseField
    private String teacherName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
