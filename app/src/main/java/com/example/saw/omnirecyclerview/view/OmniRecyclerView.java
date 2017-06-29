package com.example.saw.omnirecyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Saw on 2017/6/29.
 */

public class OmniRecyclerView extends RecyclerView {
    Adapter mAdapter;
    ArrayList<View> mHeaderViewInfos = new ArrayList<>();
    ArrayList<View> mFooterViewInfos = new ArrayList<>();

    public OmniRecyclerView(Context context) {
        super(context);
        init();
    }

    public OmniRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OmniRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    public void addHeaderView(View view){
        mHeaderViewInfos.add(view);

        if (mAdapter != null) {
            wrapHeaderListAdapterInternal();
        }

    }

    public void addFooterView(View view) {
        mFooterViewInfos.add(view);

        if (mAdapter != null) {
            wrapHeaderListAdapterInternal();
        }
    }


    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter = wrapHeaderListAdapterInternal(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }

        super.setAdapter(mAdapter);
    }


    private OmniRecyclerAdapter wrapHeaderListAdapterInternal(
            ArrayList<View> headerViewInfos,
            ArrayList<View> footerViewInfos,
            Adapter adapter) {
        return new OmniRecyclerAdapter(headerViewInfos, footerViewInfos, adapter);
    }
    private void wrapHeaderListAdapterInternal() {
        mAdapter = new OmniRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
    }
}
