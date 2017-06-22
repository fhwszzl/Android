package com.maikefengchao.daixu.Adatper;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.chinark.apppickimagev3.utils.ScreenUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Activity.ShowPictureActivity;

import java.util.List;

/**
 * Created by PC on 2016/7/3.
 */
public class ArticleAdapter extends BaseRecyclerAdapter<String>{
    private static final String IMAGE_URL = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    private Context mContext;
    public ArticleAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return inflater.inflate(R.layout.fragment_listview_article,parent,false);
    }

    @Override
    protected RecyclerView.ViewHolder getHolder(View view) {
        return new ArticleViewHolder(view);
}

    @Override
    protected void onBindHolder(RecyclerView.ViewHolder holder, int position) {
        ArticleViewHolder viewHolder = (ArticleViewHolder) holder;
        String adv = getArrayList().get(position);
        viewHolder.tvContent.setText(adv);
        viewHolder.gallery.removeAllViews();//这行必须加，解决viewHolder无缘无故加载多张图片 暂未找到原因
        for(int i=0; i<2; ++i){
            SimpleDraweeView draweeView = new SimpleDraweeView(mContext);
            Uri uri = Uri.parse(IMAGE_URL);
            draweeView.setImageURI(uri);
            GenericDraweeHierarchy hierarchy = draweeView.getHierarchy();
            hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE);
            draweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShowPictureActivity.class);
                    mContext.startActivity(intent);
                }
            });
            int width = ScreenUtils.getScreenW();
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width/3,width/3);
            viewHolder.gallery.addView(draweeView,params);

        }
        //传送给监听器的数据
        viewHolder.itemView.setTag(adv);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        private LinearLayout gallery;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            tvContent = getViewById(itemView,R.id.tv_content);
            gallery = getViewById(itemView,R.id.gallery);
        }

        private <V> V getViewById(View view,int id){
            return (V) view.findViewById(id);
        }
    }
}
