package com.swifty.fillcolor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.swifty.fillcolor.mui.MyProgressDialog;


public class PaintActivity extends ActionBarActivity {
    ColourImageView colourImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        MyProgressDialog.show(this, null, getString(R.string.loadpicture));
        colourImageView = (ColourImageView) findViewById(R.id.fillImageview);
        String URL = MyApplication.EXTRAURL + getIntent().getExtras().getString(MyApplication.BIGPIC);
        AsynImageLoader.showImageAsynWithAllCacheOpen(colourImageView, URL, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                Toast.makeText(PaintActivity.this, getString(R.string.loadpicturefailed), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                MyProgressDialog.DismissDialog();
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                Toast.makeText(PaintActivity.this, getString(R.string.loadpicturefailed), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
