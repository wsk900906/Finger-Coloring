package com.swifty.fillcolor;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.swifty.fillcolor.mui.MyProgressDialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swifty.Wang on 2015/7/31.
 */
public class GridViewActivity extends Activity {
    private GridView gridView;
    List<String> urls = new ArrayList<>();
    GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        loadUrls(getIntent().getExtras().getInt(MyApplication.THEMEID));
    }

    private void loadUrls(int anInt) {
        MyProgressDialog.show(this, null, getString(R.string.grabdata));
        AsyncTask asyncTask = new LoadDetailAsyn();
        asyncTask.execute(anInt);
    }

    private void initViews() {
        setContentView(R.layout.activity_gridview);
        gridView = (GridView) findViewById(R.id.detail_gird);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotoPaintActivity(urls.get(i));
            }
        });
    }

    private void gotoPaintActivity(String s) {
        Intent intent = new Intent(this, PaintActivity.class);
        intent.putExtra(MyApplication.BIGPIC, s.replace("thumbs/", "").replace("_m", ""));
        startActivity(intent);
    }

    private class LoadDetailAsyn extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Document doc = Jsoup.connect(MyApplication.LISTURL + (int) objects[0]).get();
                Elements elements = doc.getElementsByTag("img");
                urls.clear();
                for (int i = 0; i < elements.size(); i++) {
                    if (elements.get(i).attr("src").toLowerCase().contains("thumbs")) {
                        urls.add(elements.get(i).attr("src"));
                        L.e(elements.get(i).attr("src"));
                    }
                }
                return "SUCCESS";
            } catch (IOException e) {
                L.e(e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            MyProgressDialog.DismissDialog();
            if ("SUCCESS".equals((String) o)) {
                showGrid();
            }
        }
    }

    private void showGrid() {
        gridViewAdapter = new GridViewAdapter(this, urls);
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(gridViewAdapter);
        swingBottomInAnimationAdapter.setAbsListView(gridView);
        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(MyApplication.ABSLISTVIEW_ANIMATION_DELAY);
        gridView.setAdapter(swingBottomInAnimationAdapter);
    }
}
