package com.example.saw.omnirecyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.saw.omnirecyclerview.view.OmniRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    OmniRecyclerView omniRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        omniRecyclerView = (OmniRecyclerView) findViewById(R.id.omniRecyclerView);
        ViewGroup.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i=0; i< 3; i++) {
            TextView headerView = new TextView(this);
            headerView.setLayoutParams(layoutParams);
            headerView.setText("HeaderView " + i);
            omniRecyclerView.addHeaderView(headerView);
        }
        TextView footerView = new TextView(this);
        layoutParams = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        footerView.setLayoutParams(layoutParams);
        footerView.setText("FooterView");
        omniRecyclerView.addFooterView(footerView);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i, "Item " + i);
        }
        MyAdapter adapter = new MyAdapter(list);
        omniRecyclerView.setAdapter(adapter);
        omniRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
