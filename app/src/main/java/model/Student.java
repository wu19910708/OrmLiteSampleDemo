package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by wxs on 16/8/18.
 */
@DatabaseTable(tableName = "student_tb")
public class Student implements Serializable{

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private int studetId;

    @DatabaseField
    private String name;

    @DatabaseField
    private int age;

    @DatabaseField
    private int classId;

    @DatabaseField
    private String className;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudetId() {
        return studetId;
    }

    public void setStudetId(int studetId) {
        this.studetId = studetId;
    }
}
