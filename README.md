# OrmLiteSampleDemo
Android ormlite sample demo

##一个很简单的ormlite的demo，主要是为了熟悉这个sqlite框架
```
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
```
用到JAVA中的DAO层思想，好多常用方法都封装在了BaseDao中
关联表的查询也很简单，有兴趣的可以拷下来看看
