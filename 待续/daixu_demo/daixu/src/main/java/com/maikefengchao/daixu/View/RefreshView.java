package com.maikefengchao.daixu.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.maikefengchao.daixu.R;

/**
 * Created by PC on 2016/6/12.
 */
public class RefreshView extends RelativeLayout implements View.OnClickListener{
    private ImageView mIvRefresh;
    private Context mContext;
    private OnRefreshStartListener mRsListener;
    private boolean isFinish = true;
    private boolean isCanRefresh = true;

    public RefreshView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        mContext = context;
        mIvRefresh = new ImageView(context);
        mIvRefresh.setImageResource(R.mipmap.ic_launcher);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mIvRefresh,params);
        setOnClickListener(this);
    }
    //设置旋转的动画
    private void setRefreshAnimStart(){
        LinearInterpolator lin = new LinearInterpolator();
        //Animation.RELATIVE_TO_SELF表示以自身为点
        Animation am = new RotateAnimation( 0, +360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f );

        // 动画开始到结束的执行时间(1000 = 1 秒)
        am. setDuration ( 1000 );
        // 动画重复次数(-1 表示一直重复)
        am.setRepeatCount ( -1 );
        //设置插值器
        am.setInterpolator(lin);
        // 图片配置动画
        mIvRefresh.startAnimation(am);
        isFinish = false;
    }

    public void setOnRefreshStartListener(OnRefreshStartListener listener){
        mRsListener = listener;
    }
    /**
     * 当加载完成，结束动画
     * */
    public void refreshAnimStop(){
        mIvRefresh.clearAnimation();
        isFinish = true;
    }

    @Override
    public void onClick(View v) {
        if (isFinish == true && mRsListener != null && isCanRefresh == true){
            setRefreshAnimStart();
            mRsListener.refreshStart();
        }
    }

    /**
     * 是否允许调用置顶刷新控件
     * */
    public void setCanRefresh(boolean canRefresh) {
        isCanRefresh = canRefresh;
    }

    public interface OnRefreshStartListener {
        void refreshStart();
    }

}
