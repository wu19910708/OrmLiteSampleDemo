package dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import model.Student;

/**
 * Created by wxs on 16/8/19.
 */
public class StudentDao extends BaseDao {

    private static StudentDao instance;

    public StudentDao(Context context) {
        super(context);
    }

    @Override
    public Dao getDao() throws SQLException {
        return getHelper().getDao(Student.class);
    }

    public static synchronized StudentDao getInstance(Context context){
        if(instance == null){
            instance = new StudentDao(context);
        }
        return instance;
    }

}
