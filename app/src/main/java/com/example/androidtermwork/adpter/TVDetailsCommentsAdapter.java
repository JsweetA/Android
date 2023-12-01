package com.example.androidtermwork.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.androidtermwork.R;
import com.example.androidtermwork.pojo.TvComment;
import com.example.androidtermwork.pojo.TvDetails;
import com.example.androidtermwork.util.FormatUtil;
import com.example.androidtermwork.util.TestUtil;
import com.example.androidtermwork.volley.BitmapCache;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TVDetailsCommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TvDetails tvDetails;
    private List<TvComment> tvComments;
    private static final int DETAILS = 0;
    private static final int COMMENT = 1;
    private static final int HINT_NO_COMMENT = 2;
    private static boolean isMoreInfoOpened = false;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Context context;
    public TVDetailsCommentsAdapter(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new BitmapCache());
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;
        switch (viewType){
            case DETAILS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_play_details_item, parent, false);
                viewHolder = new TvDetailsViewHolder(view);
                break;
            case COMMENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_play_comment_item, parent, false);
                viewHolder = new TvCommentsViewHolder(view);
                break;
            default: // case HINT_NO_COMMENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_play_hint_item_nocomment, parent, false);
                viewHolder = new RecyclerView.ViewHolder(view){};
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TvDetailsViewHolder){
            bindDetailsData(holder);
        } else if (holder instanceof TvCommentsViewHolder){
            bindCommentData(holder, position);
        }
    }

    private void bindCommentData(RecyclerView.ViewHolder viewHolder, int position) {
        TvCommentsViewHolder holder = (TvCommentsViewHolder) viewHolder;
        TvComment comment = tvComments.get(position - 1);
        //请求并设置头像
        if (comment.getUserHead() != null) {
            imageLoader.get(comment.getUserHead(),
                    ImageLoader.getImageListener(holder.userHeadImage, R.mipmap.loading_head_portrait, R.mipmap.default_head_portrait));
        } else {
            holder.userHeadImage.setImageResource(R.mipmap.default_head_portrait);
        }

        //设置用户名
        holder.userName.setText(comment.getUserName());
        //设置评论内容
        holder.commentContent.setText(comment.getCommentContent());
        //设置评论发表时间
        holder.commentDate.setText(comment.getCommentDate());
        //设置点赞
        holder.setFeelGood(comment.getFeelGoodCounts(), comment.isFeelGood());
        //设置点赞事件监听（测试，跳过接口调用）
        holder.feelGoodHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment.isFeelGood()){
                    comment.setFeelGood(false);
                    comment.setFeelGoodCounts(comment.getFeelGoodCounts() - 1);
                } else {
                    comment.setFeelGood(true);
                    comment.setFeelGoodCounts(comment.getFeelGoodCounts() + 1);
                }
                holder.setFeelGood(comment.getFeelGoodCounts(), comment.isFeelGood());
            }
        });
        //todo 设置点击回复事件监听

    }

    private void bindDetailsData(RecyclerView.ViewHolder viewHolder) {
        TvDetailsViewHolder holder = (TvDetailsViewHolder) viewHolder;
        //设置视频标题
        holder.title.setText(tvDetails.getTitle());
        //设置作者和发布时间
        String authorAndDate = tvDetails.getAuthor() + " " + tvDetails.getDate();
        holder.authorAndDate.setText(authorAndDate);
        //设置播放量
        String playCounts = "播放 " + FormatUtil.simpleFormat(tvDetails.getPlayCounts());
        holder.playCounts.setText(playCounts);
        //设置点赞数
        holder.setFeelGood(tvDetails.getFeelGoodCounts(), tvDetails.isFeelGood());
        //点赞数事件监听
        holder.feelGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvDetails.isFeelGood()){
                    // 调用相关接口，成功后增加点赞数
                    //测试，跳过接口调用
                    tvDetails.setFeelGoodCounts(tvDetails.getFeelGoodCounts() + 1);
                    tvDetails.setFeelGood(true);
                } else {
                    //调用相关接口，成功后减少点赞数
                    //测试，跳过接口调用
                    tvDetails.setFeelGoodCounts(tvDetails.getFeelGoodCounts() - 1);
                    tvDetails.setFeelGood(false);
                }
                holder.setFeelGood(tvDetails.getFeelGoodCounts(), tvDetails.isFeelGood());
            }
        });
        //设置简介
        holder.introduction.setText(tvDetails.getIntroduction());

        //设置更多信息和展开更多信息
        holder.moreInfo.setVisibility(View.GONE);
        if (tvDetails.getMoreInfo() == null || tvDetails.getMoreInfo().length() == 0) {
            holder.openOrCloseHub.setVisibility(View.GONE);

        } else {
            holder.moreInfo.setText(tvDetails.getMoreInfo());
            holder.openOrCloseHub.setOnClickListener(view -> {
                ImageView openOrClose = view.findViewById(R.id.tvPlay_openOrCloseImage);
                if (isMoreInfoOpened){
                    isMoreInfoOpened = false;
                    openOrClose.setRotation(0f);
                    holder.moreInfo.setVisibility(View.GONE);
                } else {
                    isMoreInfoOpened = true;
                    openOrClose.setRotation(180f);
                    holder.moreInfo.setVisibility(View.VISIBLE);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        int t = tvComments.size() + 1;
        return t == 1 ? 2 : t;
    }

    @Override
    public int getItemViewType(int position) {
        int type = COMMENT;
        if (tvComments.size() == 0){
            type = HINT_NO_COMMENT;
        }
        return position == 0 ? DETAILS : type;
    }

    public TvDetails getTvDetails() {
        return tvDetails;
    }

    public void setTvDetails(TvDetails tvDetails) {
        this.tvDetails = tvDetails;
    }

    public List<TvComment> getTvComments() {
        return tvComments;
    }

    public void setTvComments(List<TvComment> tvComments) {
        this.tvComments = tvComments;
    }

    class TvDetailsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView authorAndDate;
        TextView moreInfo;
        TextView playCounts;
        CheckBox feelGood;
        TextView introduction;
        RelativeLayout openOrCloseHub;
        public TvDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            authorAndDate = itemView.findViewById(R.id.authorAndDate);
            moreInfo = itemView.findViewById(R.id.moreInfo);
            playCounts = itemView.findViewById(R.id.playCounts);
            feelGood = itemView.findViewById(R.id.feelGood);
            introduction = itemView.findViewById(R.id.introduction);
            openOrCloseHub = itemView.findViewById(R.id.tvPlay_openOrCloseHub);
        }

        /**
         * 设置视频点赞
         * @param counts
         * @param status
         */
        private void setFeelGood(long counts, boolean status){
            String feelGoodCounts = "点赞 " + FormatUtil.simpleFormat(counts);
            feelGood.setText(feelGoodCounts);
            feelGood.setChecked(status);
        }
    }

    class TvCommentsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView userHeadImage;
        TextView userName;
        TextView feelGoodCounts;
        ImageView feelGood;
        TextView commentContent;
        TextView commentDate;
        TextView reply;
        LinearLayout feelGoodHub;
        public TvCommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            userHeadImage = itemView.findViewById(R.id.userHeadImage);
            userName = itemView.findViewById(R.id.userName);
            feelGoodCounts = itemView.findViewById(R.id.commentFeelGoodCounts);
            feelGood = itemView.findViewById(R.id.commentFeelGood);
            commentContent = itemView.findViewById(R.id.commentContent);
            commentDate = itemView.findViewById(R.id.commentDate);
            reply = itemView.findViewById(R.id.replyComment);
            feelGoodHub = itemView.findViewById(R.id.commentFeelGoodHub);
        }


        /**
         * 设置评论点赞
         * @param counts
         * @param status
         */
        private void setFeelGood(long counts, boolean status){
            String feelGoodCounts = String.valueOf(counts);
            if (counts > 10000){
                feelGoodCounts = String.format("%.2f万", FormatUtil.getFloorCount(counts) /10000.0);
                //feelGoodCounts = counts / 10000.0 + "万";
            }
            this.feelGoodCounts.setText(feelGoodCounts);
            if (status){
                feelGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.feel_good));
            } else {
                feelGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_feel_good));
            }
        }


    }

}
