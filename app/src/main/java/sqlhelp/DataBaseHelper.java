package sqlhelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wxs on 16/8/18.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


    private final static int DATA_BASE_VERSION = 1;

    private Map<String, Dao> daos = new HashMap();

    private DataBaseHelper(Context context , String DBName){
        super(context,DBName,null,DATA_BASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }


    public static DataBaseHelper instance;
    public static synchronized DataBaseHelper getHelper(Context context , String dbName){
        context = context.getApplicationContext();
        if(instance == null){
            synchronized (DataBaseHelper.class){
                if(instance == null){
                    instance = new DataBaseHelper(context,dbName);
                }
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }


    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
        OpenHelperManager.releaseHelper();
        instance = null;
    }
}
