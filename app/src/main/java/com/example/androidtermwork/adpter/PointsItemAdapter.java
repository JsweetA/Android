package com.example.androidtermwork.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtermwork.activity_problem_1;
import com.example.androidtermwork.R;
import com.example.androidtermwork.pojo.PointSummary;
import com.example.androidtermwork.pojo.PointTask;

import java.util.List;

/**
 * “学习积分”界面采用ListView，这是ListView的数据适配器。
 * 列表项中第一项是“积分概述”，之后的项都是“积分任务”
 */
public class PointsItemAdapter extends BaseAdapter {

    private static final int TYPE_SUMMARY = 0;  //表示积分概述
    private static final int TYPE_TASK = 1;  //表示积分任务
    private Context context;
    private PointSummary summary;  //积分概述
    private List<PointTask> pointTasks;  //积分任务列表

    public PointsItemAdapter(Context context, PointSummary summary, List<PointTask> pointTasks) {
        super();
        this.context = context;
        this.summary = summary;
        this.pointTasks = pointTasks;
    }

    @Override
    public int getCount() {
        return pointTasks.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_SUMMARY : TYPE_TASK;  //只有第一个列表项是概述，之后的都是任务
    }

    @Override
    public int getViewTypeCount() {
        return 2;   //概述、任务2种类型的item放在ListView中
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskSummaryViewHolder summaryHolder;
        TaskViewHolder taskHolder;
        switch (getItemViewType(position)) {
            case TYPE_SUMMARY: {
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.points_page_item_sum, null);
                    summaryHolder = buildSummaryViewHolder(convertView);
                    convertView.setTag(summaryHolder);

                } else {
                    summaryHolder = (TaskSummaryViewHolder) convertView.getTag();
                }
                buildPointSummaryLayout(summaryHolder);
                break;
            }

            case TYPE_TASK: {
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.points_page_item_task, null);

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    convertView.setLayoutParams(layoutParams);
                    taskHolder = buildTaskViewHolder(convertView);

                    convertView.setTag(taskHolder);
                } else {
                    taskHolder = (TaskViewHolder) convertView.getTag();
                }
                buildPointTaskLayout(taskHolder, position - 1);
                break;
            }
        }
        return convertView;
    }

    /**
     * 获取积分概述布局中的组件，存放到其ViewHolder中
     *
     * @param summaryView
     * @return viewHolder
     */
    private TaskSummaryViewHolder buildSummaryViewHolder(View summaryView) {
        TaskSummaryViewHolder viewHolder = new TaskSummaryViewHolder();
        viewHolder.points = summaryView.findViewById(R.id.tvPoints);
        viewHolder.danName = summaryView.findViewById(R.id.tvDanName);
        viewHolder.pointsToday = summaryView.findViewById(R.id.tvPointsToday);
        viewHolder.danStars = summaryView.findViewById(R.id.imgDanStar);
        return viewHolder;
    }

    /**
     * 将“积分概览”的数据展示到布局中
     *
     * @param viewHolder
     */
    private void buildPointSummaryLayout(TaskSummaryViewHolder viewHolder) {
        viewHolder.points.setText(String.valueOf(this.summary.getPoints()));
        viewHolder.danName.setText(String.format(context.getString(R.string.dan_name),
                this.summary.getDanName()));
        viewHolder.danStars.setImageDrawable(context.getDrawable(summary.getDanStar()));
        SpannableString pointsTodayString =
                SpannableString.valueOf(String.format(context.getString(R.string.today_points),
                        summary.getPointsToday()));

        pointsTodayString.setSpan(new ForegroundColorSpan(Color.RED), 6, pointsTodayString.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //标红 ”%d积分“
        viewHolder.pointsToday.setText(pointsTodayString);
    }

    /**
     * 获取积分任务布局中的组件，存放到其ViewHolder中
     *
     * @param taskView
     * @return
     */
    private TaskViewHolder buildTaskViewHolder(View taskView) {
        TaskViewHolder taskViewHolder = new TaskViewHolder();
        taskViewHolder.name = taskView.findViewById(R.id.taskName);
        taskViewHolder.description = taskView.findViewById(R.id.taskDescription);
        taskViewHolder.pointDetails = taskView.findViewById(R.id.taskPointsDetail);
        taskViewHolder.go = taskView.findViewById(R.id.taskGo);
        taskViewHolder.pointsProgress = taskView.findViewById(R.id.progress_bar_points);

        return taskViewHolder;
    }

    /**
     * 将“积分任务”的数据展示到其布局中
     *
     * @param position
     * @param viewHolder
     */
    private void buildPointTaskLayout(TaskViewHolder viewHolder, int position) {
        PointTask pointTaskData = this.pointTasks.get(position);
        viewHolder.name.setText(pointTaskData.getName());
        viewHolder.description.setText(pointTaskData.getDescription());

        int pointsToday = pointTaskData.getTodayPoints();
        int pointsLimit = pointTaskData.getPointsLimit();
        String pointDetails = String.format(context.getString(R.string.today_points_details),
                pointsToday, pointsLimit);
        viewHolder.pointDetails.setText(pointDetails);
        viewHolder.pointsProgress.setProgress((int) ((double) pointsToday / pointsLimit * 100));
        //根据今日积分和积分上限判断今日该任务是否完成，未完成添加跳转界面的事件监听器
        String backColor = context.getString(R.string.background_todo);
        String textColor = context.getString(R.string.text_todo);
        String text = "去看看";

        if (pointsToday == pointsLimit) {
            backColor = context.getString(R.string.background_done);
            textColor = context.getString(R.string.text_done);
            text = "已完成";
        }
        if (pointsToday < pointsLimit || pointTaskData.getGoAction().startsWith("new_")){
            viewHolder.go.setTag(pointTaskData.getGoAction());   //将动作描述设置为去做任务TextView的tag
            viewHolder.go.setOnClickListener(new DoTaskClickListener(context));
        }

        viewHolder.go.setText(text);
        viewHolder.go.setTextColor(Color.parseColor(textColor));
        viewHolder.go.setBackgroundColor(Color.parseColor(backColor));

    }

    /**
     * ViewHolder类，用于存放布局中组件，把它放在组件的Tag中。
     * 需要时可以直接获取它，通过它改掉布局中组件的显示内容等属性。
     */
    private static final class TaskSummaryViewHolder {
        TextView points;  //总积分
        TextView pointsToday;  //今日已累积积分
        TextView danName;  //段位名称
        ImageView danStars;  //段位星星数图片
    }

    private static final class TaskViewHolder {
        TextView name;  //任务名称
        TextView description;  //任务描述
        TextView pointDetails;  //积分详情
        TextView go;  //去做任务
        ProgressBar pointsProgress;  //积分进度条
    }
}

//点击去做任务的事件监听器
class DoTaskClickListener implements View.OnClickListener {

    private Context context;

    public DoTaskClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        String task = (String) v.getTag();
        AppCompatActivity activity = (AppCompatActivity) context;
        if (task.startsWith("res_")) {

            activity.setResult(Activity.RESULT_OK, new Intent().putExtra("goAction", task));
            activity.finish();
            return;
        }

        //TestUtil.showSnakeBarMessage(task, -1, v);
        switch (task){
            case "new_dailyQ":
                Intent intent = new Intent(context, activity_problem_1.class);
                context.startActivity(intent);
                break;
            case "new_weeklyQ":
                Intent intent1 = new Intent(context, activity_problem_1.class);
                context.startActivity(intent1);
                break;
            //case...
        }
        activity.finish();
    }
}
