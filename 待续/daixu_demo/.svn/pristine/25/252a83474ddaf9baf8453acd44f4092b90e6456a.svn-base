package com.maikefengchao.daixu.Dao;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.maikefengchao.daixu.Bean.ArticleItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/5/26.
 */
public class ArticleItemDao {
    private DataBaseHelper mDataBase;
    private RuntimeExceptionDao<ArticleItem,Integer> mArticleItems;
    private Context mContext;

    public ArticleItemDao(Context context) {
        mDataBase = DataBaseHelper.getHelper(context);
        mArticleItems = mDataBase.getArticlesRuntime();
    }

    /**
     * 更新或添加
     * @param newsItem 需要更新的文章列表项
     */
    public void createOrUpdate(ArticleItem newsItem){
        mArticleItems.createOrUpdate(newsItem);
    }

    public List<ArticleItem> queryArticle(int currentCount) {
        //添加下面五个的索引
        List count = new ArrayList();
        for (int i=0; i<5; ++i){
            count.add(currentCount);
            currentCount += currentCount;
        }
        //搜索下面的索引
        List<ArticleItem> newsItems = null;
        try {
            newsItems = mArticleItems.queryBuilder().where()
                    .in("id",count).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsItems;
    }

    /**
     * 删除数据库的数据
     */
    public void deleteAll(){
        mArticleItems.delete(queryAll());
    }


    public List<ArticleItem> queryAll(){
        List<ArticleItem> news = mArticleItems.queryForAll();
        return news;
    }
}
