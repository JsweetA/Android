package com.example.androidtermwork.pojo;

//积分概览
public class PointSummary
{
    private int points;   //总积分
    private int pointsToday;  //今日已累积积分
    private int dan; //段位
    private String danName;  //段位名称
    private int danStar;  //段位星星图片id

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPointsToday() {
        return pointsToday;
    }

    public void setPointsToday(int pointsToday) {
        this.pointsToday = pointsToday;
    }

    public String getDanName() {
        return danName;
    }

    public void setDanName(String danName) {
        this.danName = danName;
    }

    public int getDanStar() {
        return danStar;
    }

    public void setDanStar(int danStar) {
        this.danStar = danStar;
    }

    public int getDan() {
        return dan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }
}
