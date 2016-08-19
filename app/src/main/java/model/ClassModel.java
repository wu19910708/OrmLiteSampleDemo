package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by wxs on 16/8/19.
 */
@DatabaseTable(tableName = "class_tb")
public class ClassModel implements Serializable {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "class_name")
    private String className;

    @DatabaseField(columnName = "class_id")
    private int classId;




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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
