package com.chinark.apppickimagev3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.chinark.apppickimagev3.R;
import com.chinark.apppickimagev3.utils.SDCardImageLoader;
import com.chinark.apppickimagev3.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * Created by PC on 2016/6/24.
 * PhotoWall中GridView的适配器
 * 不含CheckBox只能够单选
 */
public class PhotoWallAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> imagePathList = null;
    private SDCardImageLoader loader;

    private OnSelectPictureListener mListener;
    public PhotoWallAdapter(Context context, ArrayList<String> imagePathList) {
        this.context = context;
        this.imagePathList = imagePathList;
        loader = SDCardImageLoader.getImageLoader();
    }

    @Override
    public int getCount() {
        return imagePathList == null ? 0 : imagePathList.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String filePath = (String) getItem(position);

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_photo_wall, null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.photo_wall_item_photo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setTag(filePath);
        loader.loadImage(4, filePath, holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectPicture(filePath);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }

    /*公共方法*/

    public void setSelectPictureListener(OnSelectPictureListener listener){
        mListener = listener;
    }
}
