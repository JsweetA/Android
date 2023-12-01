package com.example.androidtermwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.androidtermwork.adpter.PointsItemAdapter;
import com.example.androidtermwork.pojo.PointSummary;
import com.example.androidtermwork.pojo.PointTask;
import com.example.androidtermwork.util.ResourceUtil;
import com.example.androidtermwork.util.StatusBarUtil;
import com.example.androidtermwork.util.TestUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class PointsPage extends AppCompatActivity {

    private Gson gson;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_page);
        initInstance();
        //setToolbar();
        bindListViewData();
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        eventListener();
    }

    /**
     * 初始化对象(引用)
     */
    private void initInstance() {
        gson = new Gson();
        toolbar = findViewById(R.id.points_toolbar);
    }

    /**
     * 添加事件监听器
     */
    private void eventListener() {
        //右上角返回
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 返回
            }
        });
    }

    /**
     * 给ListView绑定数据
     */
    private void bindListViewData() {
        PointSummary pointSummary =
                gson.fromJson(
                        TestUtil.getJson("mock_user_points.json", this),
                        PointSummary.class);
        int dan = pointSummary.getDan();
        pointSummary.setDanName(getResources().getStringArray(R.array.dan_name_array)[dan]);
        pointSummary.setDanStar(
                ResourceUtil.getIdentifier(this, getString(R.string.dan_star_prefix) + dan, "mipmap")
        );
        List<PointTask> pointTasks = gson.fromJson(
                TestUtil.getJson("mock_list_task.json", this),
                new TypeToken<List<PointTask>>() {
                }.getType()
        );
        ListView listView = findViewById(R.id.contentListView);
        listView.setAdapter(new PointsItemAdapter(this, pointSummary, pointTasks));
    }

    /**
     * 设置ToolBar
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}