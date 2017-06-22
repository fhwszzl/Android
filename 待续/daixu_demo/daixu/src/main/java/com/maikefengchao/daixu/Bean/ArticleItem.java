package com.maikefengchao.daixu.Bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by PC on 2016/5/26.
 */
public class ArticleItem {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String peopleName;
    @DatabaseField
    private String peopleIconUrl;
    @DatabaseField
    private String sendDate;
    @DatabaseField
    private String title;
    @DatabaseField
    private String briefContent;
    @DatabaseField
    private boolean isCollect;
    @DatabaseField
    private int enjoyCount;
    @DatabaseField
    private int collectCount;
    @DatabaseField
    private int remarkCount;
    @DatabaseField(canBeNull = false)
    private String articleContentUrl;

    public String getArticleContentUrl() {
        return articleContentUrl;
    }

    public void setArticleContentUrl(String articleContentUrl) {
        this.articleContentUrl = articleContentUrl;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public int getEnjoyCount() {
        return enjoyCount;
    }

    public void setEnjoyCount(int enjoyCount) {
        this.enjoyCount = enjoyCount;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getPeopleIconUrl() {
        return peopleIconUrl;
    }

    public void setPeopleIconUrl(String peopleIconUrl) {
        this.peopleIconUrl = peopleIconUrl;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public int getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(int remarkCount) {
        this.remarkCount = remarkCount;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + articleContentUrl + '\'' +
                ", date='" + sendDate + '\'' +
                ", content='" + briefContent + '\'' +
                '}';
    }

}
