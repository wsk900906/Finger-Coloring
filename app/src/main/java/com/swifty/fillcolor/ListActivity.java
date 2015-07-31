package com.swifty.fillcolor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Swifty.Wang on 2015/7/31.
 */
public class ListActivity extends Activity {
    private ListView listView;
    List<String> themelist;
    View footer;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initLocalData();

    }

    private void initViews() {
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.theme_list);
        footer = getLayoutInflater().inflate(R.layout.textview_footer, null);
        listView.addFooterView(footer);

    }

    private void initLocalData() {
        Resources res = getResources();
        String[] themes = res.getStringArray(R.array.themeName);
        themelist = new ArrayList<>(Arrays.asList(themes));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, themelist);
        // add list animation
//        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(adapter);
//        swingBottomInAnimationAdapter.setAbsListView(listView);
//        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
//        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(300);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotoDetailGridActivity(i + 1);
            }
        });
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load more themes from http://www.coloring-book.info/
            }
        });
    }

    private void gotoDetailGridActivity(int i) {
        Intent intent = new Intent(this, GridViewActivity.class);
        intent.putExtra(MyApplication.THEMEID, i);
        startActivity(intent);
    }

}
