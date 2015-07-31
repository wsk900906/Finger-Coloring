package com.swifty.fillcolor;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class AsynImageLoader {

    public static void showImageAsyn(ImageView imageView, String url) {
        ImageLoaderConstMethod.getInstance().displayImage(url, imageView,
                ImageLoaderConstMethod.getOptions(),
                new ImageLoaderConstMethod.AnimateFirstDisplayListener());
    }

    public static void showImageAsynWithoutCache(ImageView imageView, String url) {
        ImageLoaderConstMethod.getInstance().displayImage(url, imageView,
                ImageLoaderConstMethod.getNoDiskCacheOptions(),
                new ImageLoaderConstMethod.AnimateFirstDisplayListener());
    }

    public static void showImageAsynWithAllCacheOpen(ImageView imageView, String url) {
        ImageLoaderConstMethod.getInstance().displayImage(url, imageView,
                ImageLoaderConstMethod.getOpenAllCacheOptions(),
                new ImageLoaderConstMethod.AnimateFirstDisplayListener());
    }

    public static void showImageAsynWithAllCacheOpen(ImageView imageView, String url, ImageLoadingListener listener) {
        ImageLoaderConstMethod.getInstance().displayImage(url, imageView,
                ImageLoaderConstMethod.DetailImageOptions(),
                listener);
    }
}
