package dao;

import android.content.Context;
import android.support.annotation.Nullable;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;

import sqlhelp.DataBaseHelper;

/**
 * Created by wxs on 16/8/18.
 *
 * BaseDao里面封装了增删改查的几个常规方法,按照自己的需求调用即可
 * 直接把这个类拷到自己的项目就可以了
 */
public abstract class BaseDao<T,Integer> {

    protected DataBaseHelper helper;

    protected Context context;

    public static final String DATABASE_NAME = "test";

    public BaseDao(Context context) {
        this.context = context;
    }

    public DataBaseHelper getHelper() {
        helper = DataBaseHelper.getHelper(context, DATABASE_NAME);
        return helper;
    }

    public abstract Dao<T, Integer> getDao() throws SQLException;


    public int save(T t) throws SQLException {
        return getDao().create(t);
    }

    public void save(List<T> list) throws SQLException, InvalidParameterException {
        if (list != null && !list.isEmpty()) {
            for (T t : list) {
                save(t);
            }
        } else {
            return;
        }
    }

    public T queryById(Integer id) throws SQLException{
        return getDao().queryForId(id);
    }


    public List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        return getDao().query(preparedQuery);
    }

    public List<T> query(String attributeName, String attributeValue) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        queryBuilder.where().eq(attributeName, attributeValue);
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    public List<T> query(@Nullable String[] columnNames, @Nullable String[] attributeNames, @Nullable String[] attributeValues)
            throws SQLException, InvalidParameterException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        if (attributeNames != null && attributeValues != null) {
            if (attributeNames.length != attributeValues.length) {
                throw new InvalidParameterException("params size is not equal");
            } else {
                for (int i = 0; i < attributeNames.length; i++) {
                    if (i == 0) {
                        wheres.eq(attributeNames[i], attributeValues[i]);
                    } else {
                        wheres.and().eq(attributeNames[i], attributeValues[i]);
                    }
                }
            }
        }
        if (columnNames != null && columnNames.length > 0) {
            queryBuilder.selectColumns(columnNames);
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);

    }

    /**
     * 原生sql的查询
     *
     * @param sql 原生sql语句
     * @return
     * @throws SQLException
     */
    public List<String[]> query(String sql) throws SQLException {
        GenericRawResults<String[]> rawResults =
                getDao().queryRaw(sql);
        return rawResults.getResults();
    }


    public List<T> query(String[] attributeNames, String[] attributeValues) throws SQLException, InvalidParameterException {
        if (attributeNames.length != attributeValues.length) {
            throw new InvalidParameterException("params size is not equal");
        }
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        for (int i = 0; i < attributeNames.length; i++) {
            if (i == 0) {
                wheres.eq(attributeNames[i], attributeValues[i]);
            } else {
                wheres.and().eq(attributeNames[i], attributeValues[i]);
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * @param name
     * @param value
     * @return
     * @throws SQLException 一定要判断返回的对象是不是null,不然会出问题
     */
    public T queryByParam(String name, String value) throws SQLException {
        List<T> list = query(name, value);
        if (null != list && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<T> queryAll() throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.queryForAll();
    }

    public int delete(PreparedDelete<T> preparedDelete) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(preparedDelete);
    }

    public int delete(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(t);
    }

    public int delete(List<T> lst) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(lst);
    }

    public int delete(String[] attributeNames, String[] attributeValues) throws SQLException,
            InvalidParameterException {
        List<T> lst = query(attributeNames, attributeValues);
        if (null != lst && !lst.isEmpty()) {
            return delete(lst);
        }
        return 0;
    }

    public int deleteByParam(String idName, String idValue) throws SQLException,
            InvalidParameterException {
        T t = queryByParam(idName, idValue);
        if (null != t) {
            return delete(t);
        }
        return 0;
    }

    public int update(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.update(t);
    }

    public int update(String sql) throws SQLException {
        return getDao().updateRaw(sql);
    }

    public void update(String attributeName, String attributeValue, String[] columnNames, Object[] values) throws SQLException, InvalidParameterException {
        if (columnNames.length != values.length) {
            throw new InvalidParameterException("columnNames and values length is not same");
        }
        UpdateBuilder<T, Integer> updateBuilder = getDao().updateBuilder();
        updateBuilder.where().eq(attributeName, attributeValue);
        for (int i = 0; i < columnNames.length; i++) {
            updateBuilder.updateColumnValue(columnNames[i], values[i]);
        }
        PreparedUpdate<T> preparedUpdate = updateBuilder.prepare();
        getDao().update(preparedUpdate);
    }

    public void update(String attributeName, String attributeValue, String columnNames, Object values) throws SQLException{
        UpdateBuilder<T, Integer> updateBuilder = getDao().updateBuilder();
        updateBuilder.where().eq(attributeName, attributeValue);
        updateBuilder.updateColumnValue(columnNames, values);
        PreparedUpdate<T> preparedUpdate = updateBuilder.prepare();
        getDao().update(preparedUpdate);
    }

    public boolean isTableExsits() throws SQLException {
        return getDao().isTableExists();
    }

    public long countOf() throws SQLException {
        return getDao().countOf();
    }





    /**
     * 传入类名
     *
     * @param clazz
     * @throws SQLException
     */
    public void dropTable(Class clazz) throws SQLException {
        TableUtils.clearTable(getDao().getConnectionSource(),clazz);
    }

}
