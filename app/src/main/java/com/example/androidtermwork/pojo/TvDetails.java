package com.example.androidtermwork.pojo;

public class TvDetails {
    private long id;
    private String title;
    private String author;
    private String date;
    private String moreInfo;
    private long playCounts;
    private long feelGoodCounts;
    private String introduction;
    private boolean isFeelGood;
    private boolean isCollect;
    private String sourceUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public long getPlayCounts() {
        return playCounts;
    }

    public void setPlayCounts(long playCounts) {
        this.playCounts = playCounts;
    }

    public long getFeelGoodCounts() {
        return feelGoodCounts;
    }

    public void setFeelGoodCounts(long feelGoodCounts) {
        this.feelGoodCounts = feelGoodCounts;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isFeelGood() {
        return isFeelGood;
    }

    public void setFeelGood(boolean feelGood) {
        isFeelGood = feelGood;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
