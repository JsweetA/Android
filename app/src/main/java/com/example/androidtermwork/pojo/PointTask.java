package com.example.androidtermwork.pojo;

//积分任务
public class PointTask
{
    private String name; // 任务名
    private String description; // 任务描述
    private int todayPoints; // 今日已获积分
    private int pointsLimit; // 任务积分上限
    private String goAction; // 表示点击去做任务后的动作

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoAction() {
        return goAction;
    }

    public void setGoAction(String goAction) {
        this.goAction = goAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTodayPoints() {
        return todayPoints;
    }

    public void setTodayPoints(int todayPoints) {
        this.todayPoints = todayPoints;
    }

    public int getPointsLimit() {
        return pointsLimit;
    }

    public void setPointsLimit(int pointsLimit) {
        this.pointsLimit = pointsLimit;
    }
}
