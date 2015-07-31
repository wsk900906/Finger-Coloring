package com.swifty.fillcolor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private List<String> urls;

    public GridViewAdapter(Context context, List<String> urls) {
        mContext = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int i) {
        return urls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if (imageView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new AbsListView.LayoutParams(MyApplication.SCREEN_WIDTH / 3, MyApplication.SCREEN_WIDTH / 3));
        }
        AsynImageLoader.showImageAsynWithAllCacheOpen(imageView, MyApplication.EXTRAURL + urls.get(position));


        return imageView;
    }

}