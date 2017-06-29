package com.example.saw.omnirecyclerview.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Saw on 2017/6/29.
 */
class OmniRecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter {
    private static final int CONTENT_TYPE = 0;
    private static final int HEADER_TYPE = -1;
    private static final int FOOTER_TYPE = -2;

    private RecyclerView.Adapter mAdapter;
    private ArrayList<View> mHeaderViewInfos = new ArrayList<>();
    private ArrayList<View> mFooterViewInfos = new ArrayList<>();

    public OmniRecyclerAdapter(ArrayList<View> headerViewInfos, ArrayList<View> footerViewInfos, RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        if (mHeaderViewInfos == null) {
            mHeaderViewInfos = new ArrayList<>();
        } else {
            mHeaderViewInfos = headerViewInfos;
        }

        if (mFooterViewInfos == null) {
            mFooterViewInfos = new ArrayList<>();
        } else {
            mFooterViewInfos = footerViewInfos;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return new HeaderViewHolder(mHeaderViewInfos.get(0));
        } else if (viewType == FOOTER_TYPE) {
            return new FooterViewHolder(mFooterViewInfos.get(0));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType != HEADER_TYPE && viewType != FOOTER_TYPE) {
            mAdapter.onBindViewHolder(holder, position - mHeaderViewInfos.size());
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter != null ? mAdapter.getItemCount() + mFooterViewInfos.size() + mHeaderViewInfos.size() :
                mFooterViewInfos.size() + mHeaderViewInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = mHeaderViewInfos.size();
        if (numHeaders > position) {
            return HEADER_TYPE;
        }
        final int adjPosition = position - numHeaders;
        int adapterCount;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adapterCount > adjPosition) {
                mAdapter.getItemViewType(adjPosition);
                return CONTENT_TYPE;
            }
        }

        return FOOTER_TYPE;
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }
    private static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }
}
