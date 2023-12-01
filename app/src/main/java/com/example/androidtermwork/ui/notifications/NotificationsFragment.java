package com.example.androidtermwork.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.androidtermwork.R;
import com.example.androidtermwork.TvStationPlayPage;
import com.example.androidtermwork.adpter.TVStationAdapter;
import com.example.androidtermwork.pojo.TVStation;
import com.example.androidtermwork.util.TestUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 这是电视台Fragment
 */
public class NotificationsFragment extends Fragment {

    private View bindingView;
    private ListView TVListView;
    private SmartRefreshLayout refreshLayout;
    //private Gson gson;
    private Handler refreshLayoutHandler;
    private TVStationAdapter tvStationAdapter;
    private int page = 1;
    private static final int TV_STATION_PLAY = 0x03;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);*/

        bindingView = inflater.inflate(R.layout.fragment_notifications, container, false);
        View root = bindingView.getRootView();
        TVListView = bindingView.findViewById(R.id.tvListView);
        refreshLayout = bindingView.findViewById(R.id.refreshLayout);
        init();
        eventListener();
        refreshLayout.autoRefresh(150);
        return root;
    }

    private void init() {
        //gson = new Gson();
        refreshLayoutHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Map<String, Object> resp = (Map)msg.obj;
                if (msg.what == 0){
                    switch ((int)resp.get("code")){
                        case 200: refreshLayout.finishRefresh();break;
                        case 400: refreshLayout.finishRefresh(false);break;
                    }
                } else if (msg.what == 1){
                    switch ((int)resp.get("code")){
                        case 200: refreshLayout.finishLoadMore();break;
                        case 400: refreshLayout.finishLoadMore(false);break;
                        case 201: refreshLayout.finishLoadMoreWithNoMoreData();break;
                    }
                }
                ((BaseAdapter) resp.get("data_adapter")).notifyDataSetChanged();
            }
        };

        tvStationAdapter = new TVStationAdapter(getContext(), new ArrayList<>());
        TVListView.setAdapter(tvStationAdapter);
    }

    private void bindTVListViewData(List<TVStation> tvStations, int operationType) {
        List<TVStation> tvStationsInAdapter = tvStationAdapter.getTvStations();
        switch (operationType) {
            case 0:
                tvStationsInAdapter.clear();
                tvStationsInAdapter.addAll(tvStations);
                break;
            case 1:
                tvStationsInAdapter.addAll(tvStations);
                break;
        }

    }

    private void refreshLayoutMessage(Map<String, Object> resp, int what) {
        refreshLayoutHandler.sendMessage(Message.obtain(refreshLayoutHandler, what, resp));
    }

    private void eventListener() {
        // onLoad
        refreshLayout.setOnLoadMoreListener(new MyOnLoadMoreRefreshListener(1)::listener);
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new MyOnLoadMoreRefreshListener(0)::listener);
        TVListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TVStation station = (TVStation) parent.getItemAtPosition(position);
                startActivityForResult(
                        new Intent(getActivity(), TvStationPlayPage.class)
                                .putExtra("id", id)
                                .putExtra("title", station.getTitle())
                                .putExtra("author", station.getAuthor())
                                .putExtra("date", station.getDate())
                                .putExtra("sourceUrl", station.getVideoSource()), TV_STATION_PLAY);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bindingView = null;
    }

    class MyOnLoadMoreRefreshListener{
        private int type;

        public MyOnLoadMoreRefreshListener(int type) {
            this.type = type;
        }

        public void listener(RefreshLayout refreshLayout){
            new Thread(() -> {
                Map<String, Object> resp = new HashMap<>();
                resp.put("data_adapter", tvStationAdapter);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    resp.put("code", 400);
                    refreshLayoutMessage(resp, type);
                }
                List<TVStation> tvStations;
                if (type == 0) {
                    page = 1;
                    // 模拟分页获取数据
                    tvStations = TestUtil.getTVStationsPaging(page, getContext());
                } else {
                    tvStations = TestUtil.getTVStationsPaging(++page, getContext());
                }
                if (tvStations.isEmpty()) {
                    resp.put("code", 201);
                } else {
                    resp.put("code", 200);
                    bindTVListViewData(tvStations, type);
                }
                refreshLayoutMessage(resp, type);

            }).start();

        }

    }
}

