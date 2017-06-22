package com.maikefengchao.daixu.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.maikefengchao.daixu.Bean.ArticleItem;

import java.sql.SQLException;

/**
 * Created by PC on 2016/5/26.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "daixu.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHelper mDataBaseHelper;
    private static Context mContext;

    private Dao<ArticleItem,Integer> mArticleItems;
    private RuntimeExceptionDao<ArticleItem,Integer> mArticleItemsRuntime;
    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public synchronized static DataBaseHelper getHelper(Context context){
        mContext = context.getApplicationContext();
        if (mDataBaseHelper == null){
            synchronized (DataBaseHelper.class){
                mDataBaseHelper = new DataBaseHelper(mContext);
            }
        }
        return mDataBaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ArticleItem.class);
            mArticleItems = getArticles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, ArticleItem.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<ArticleItem,Integer> getArticles() throws SQLException {
        if (mArticleItems == null){
            mArticleItems = getDao(ArticleItem.class);
        }
        return mArticleItems;
    }

    public RuntimeExceptionDao<ArticleItem,Integer> getArticlesRuntime() {
        if (mArticleItemsRuntime == null){
            mArticleItems = getRuntimeExceptionDao(ArticleItem.class);
        }
        return mArticleItemsRuntime;
    }
}
