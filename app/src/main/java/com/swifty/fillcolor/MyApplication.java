package com.swifty.fillcolor;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Swifty.Wang on 2015/7/31.
 */
public class MyApplication extends Application {
    public static final String MAINURL = "http://www.coloring-book.info/";
    public static final String EXTRAURL = MAINURL + "coloring/";
    public static final String LISTURL = EXTRAURL + "coloring_page.php?id=";
    public static final String THEMEID = "themeid";
    public static final String BIGPIC = "bigpic";
    public static final int ABSLISTVIEW_ANIMATION_DELAY = 300;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        SCREEN_WIDTH = wm.getDefaultDisplay().getWidth();//фад╩©М╤х
        SCREEN_HEIGHT = wm.getDefaultDisplay().getHeight();
    }

    public void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoaderConstMethod.getInstance().init(config);
    }

}
