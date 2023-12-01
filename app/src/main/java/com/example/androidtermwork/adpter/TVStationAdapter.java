package com.example.androidtermwork.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.androidtermwork.R;
import com.example.androidtermwork.pojo.TVStation;
import com.example.androidtermwork.volley.BitmapCache;

import java.util.List;


public class TVStationAdapter extends BaseAdapter {
    private static final int BIG_COVER_TV = 0;
    //private static final int STD_COVER_TV = 1;
    private Context context;
    private List<TVStation> tvStations;
    private RequestQueue mQueue;
    private ImageLoader imageLoader;

    public TVStationAdapter(Context context, List<TVStation> tvStations) {
        this.context = context;
        this.tvStations = tvStations;
        mQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(mQueue, new BitmapCache());
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public List<TVStation> getTvStations() {
        return tvStations;
    }
    public void setTvStations(List<TVStation> tvStations) {
        this.tvStations = tvStations;
    }

    @Override
    public int getCount() {
        return tvStations.size();
    }

    @Override
    public Object getItem(int position) {
        return tvStations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tvStations.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TVStationViewHolder viewHolder;
        TVStation tvStation = tvStations.get(position);
        if (convertView != null){
            viewHolder = (TVStationViewHolder) convertView.getTag();
            bindItemData(tvStation, viewHolder);
            return convertView;
        }
        switch (tvStation.getItemType()){
            case BIG_COVER_TV:
                convertView = LayoutInflater.from(context).inflate(R.layout.tv_station_big_item, null);
                break;
            default: convertView = LayoutInflater.from(context).inflate(R.layout.tv_station_standard_item, null);
        }
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(layoutParams);
        viewHolder = buildTVStationViewHolder(convertView);
        bindItemData(tvStation, viewHolder);
        convertView.setTag(viewHolder);
        return convertView;
    }

    private void bindItemData(TVStation tvStation, TVStationViewHolder viewHolder) {
        //设置标题
        viewHolder.title.setText(tvStation.getTitle());
        //设置作者和发布时间
        String authorAndDate = tvStation.getAuthor() + " " + tvStation.getDate();
        viewHolder.authorAndDate.setText(authorAndDate);
        //设置视频时长
        int minutes = tvStation.getTimeLength()/60;
        int seconds = tvStation.getTimeLength()%60;
        String timeLength = minutes + ":" + seconds;
        viewHolder.timeLength.setText(timeLength);

        //请求封面图片
        imageLoader.get(tvStation.getCover(),
                ImageLoader.getImageListener(viewHolder.cover, R.mipmap.img_loading, R.mipmap.img_fail_to_load));

        //viewHolder.cover.setImageURI(Uri.parse(tvStation.getCover()));

    }

    private TVStationViewHolder buildTVStationViewHolder(View convertView) {
        TVStationViewHolder viewHolder = new TVStationViewHolder();
        viewHolder.cover = convertView.findViewById(R.id.tvImgCover);
        viewHolder.title = convertView.findViewById(R.id.tvTitle);
        viewHolder.timeLength = convertView.findViewById(R.id.tvTimeLength);
        viewHolder.authorAndDate = convertView.findViewById(R.id.tvAuthorDate);
        return viewHolder;
    }

    @Override
    public int getViewTypeCount() {
        return 2;  //大图片封面和小封面图片两种电视台
    }

    /**
     * “电视台”ViewHolder
     */
    private static final class TVStationViewHolder {
        ImageView cover;   //封面图片
        TextView title;  //标题
        TextView authorAndDate;   //作者和发布时间
        TextView timeLength;  //视频时长
    }
}