package com.example.androidtermwork.pojo;

public class TvComment {
    private String userHead;
    private String userId;
    private String userName;
    private long feelGoodCounts;
    private boolean isFeelGood;
    private String commentContent;
    private String commentDate;

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getFeelGoodCounts() {
        return feelGoodCounts;
    }

    public void setFeelGoodCounts(long feelGoodCounts) {
        this.feelGoodCounts = feelGoodCounts;
    }

    public boolean isFeelGood() {
        return isFeelGood;
    }

    public void setFeelGood(boolean feelGood) {
        isFeelGood = feelGood;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
