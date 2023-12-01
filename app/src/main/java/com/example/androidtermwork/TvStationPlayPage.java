package com.example.androidtermwork;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtermwork.adpter.TVDetailsCommentsAdapter;
import com.example.androidtermwork.pojo.TvComment;
import com.example.androidtermwork.pojo.TvDetails;
import com.example.androidtermwork.util.StatusBarUtil;
import com.example.androidtermwork.util.TestUtil;
import com.example.androidtermwork.view.JzvdPlayer;
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;


public class TvStationPlayPage extends AppCompatActivity {

    private TvDetails tvDetails;
    private List<TvComment> tvComments;
    private RecyclerView detailsCommentsView;
    private JzvdPlayer playerStandard;
    private Intent startIntent;
    private SmartRefreshLayout refreshLayout;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_station_play);
        //设置状态栏字体为黑色
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        startIntent = getIntent();
        prepareTestData();
        buildDetailsData();
        initPlayer();
        loadDetailsAndComments();
        buildCommentIcon();

    }

    /**
     * 测试方法
     */
    private void prepareTestData() {
        //第一个视频有测试数据
        int id = (int) startIntent.getLongExtra("id", 0);
        if (id > 0){
            // 里面Mock数据模拟查看详细
            tvDetails =
                    TestUtil.getMockData("mock_data_tv_details_"+ id + ".json",
                            new TypeToken<TvDetails>(){}, this);
            tvComments = TestUtil.getMockData("mock_data_tv_comments_"+ id + ".json",
                    new TypeToken<List<TvComment>>(){}, this);
        } else {
            tvDetails = new TvDetails();
            tvComments = new ArrayList<>();
            tvDetails.setPlayCounts((long)(Math.random() * 1005));
            tvDetails.setFeelGoodCounts((long)(Math.random() * 105));
            tvDetails.setCollect((Math.random() + 0.5) > 1.0d);
            tvDetails.setFeelGood((Math.random() + 0.5) > 1.0d);
            tvDetails.setIntroduction("暂无简介");
            closeLoadMore();
        }
    }

    /**
     * 关闭下拉加载更多评论
     */
    private void closeLoadMore(){
        if (refreshLayout == null)
            refreshLayout = findViewById(R.id.videoInfoRefreshLayout);
        refreshLayout.setEnableLoadMore(false);
    }
    /**
     * 加载视频详情和评论
     */
    private void loadDetailsAndComments() {
        detailsCommentsView = findViewById(R.id.play_recyclerView);
        TVDetailsCommentsAdapter dataAdapter = new TVDetailsCommentsAdapter(this);
        dataAdapter.setTvDetails(tvDetails);
        dataAdapter.setTvComments(tvComments);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        detailsCommentsView.setLayoutManager(layoutManager);
        detailsCommentsView.setAdapter(dataAdapter);
    }

    /**
     * 准备视频详情
     *
     */
    private void buildDetailsData(){
        tvDetails.setId(startIntent.getLongExtra("id", 0));
        tvDetails.setTitle(startIntent.getStringExtra("title"));
        tvDetails.setAuthor(startIntent.getStringExtra("author"));
        tvDetails.setDate(startIntent.getStringExtra("date"));
        tvDetails.setSourceUrl(startIntent.getStringExtra("sourceUrl"));
    }

    /**
     * 加载播放器
     */
    private void initPlayer(){
        playerStandard = findViewById(R.id.play_playerStandard);
        playerStandard.setUp(tvDetails.getSourceUrl(),"");
        playerStandard.startVideo();
    }

    /**
     * 设置评论按钮显示评论数，以及点击事件
     */
    private void buildCommentIcon() {
        ImageView icon = findViewById(R.id.icComment);
        TextView commentCount = findViewById(R.id.tvCommentCount);
        if (tvComments.size() > 0){
            commentCount.setText(String.valueOf(tvComments.size()));
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scrollItemToTop(1);
                }
            });
        } else {
            commentCount.setVisibility(View.GONE);
        }

    }
    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void scrollItemToTop(int position){
        LinearSmoothScroller smoothScroller = new LinearTopSmoothScroller(this);
        smoothScroller.setTargetPosition(position);
        detailsCommentsView.getLayoutManager().startSmoothScroll(smoothScroller);
    }

}

class LinearTopSmoothScroller extends LinearSmoothScroller {

    public LinearTopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}