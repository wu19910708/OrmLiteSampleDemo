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

    @DatabaseField(columnName = "student_id")
    private int studetId;

    @DatabaseField(columnName = "student_name")
    private String name;

    @DatabaseField(columnName = "student_age")
    private int age;


    /**
     * 因为student属于某个class,如果用class_id作为student的属性,这种做法虽然没有错,但是没有体现出面向对象的思想
     * 所以可以用这种声明来做,很方便,查询的时候能一下子查出来,而不用去考虑表关联的问题,对我这种数据库小白来说还是非常有用的
     */
    @DatabaseField(canBeNull = true , foreign = true , columnName = "class_id")
    private ClassModel classModel;


    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
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
