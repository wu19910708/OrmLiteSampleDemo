package dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import model.ClassModel;

/**
 * Created by wxs on 16/8/19.
 */
public class ClassDao extends BaseDao<ClassModel,Integer> {

    private static ClassDao instance;

    public ClassDao(Context context){
        super(context);
    }

    @Override
    public Dao<ClassModel, Integer> getDao() throws SQLException {
        return getHelper().getDao(ClassModel.class);
    }

    public static synchronized ClassDao getInstance(Context context){
        if(instance == null){
            instance = new ClassDao(context);
        }
        return instance;
    }
}
