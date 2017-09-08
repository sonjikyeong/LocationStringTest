package net.daum.www.locationstringtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thswl on 2017-09-05.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private ArrayList<aLocation> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title_text);
            mContent = (TextView) view.findViewById(R.id.content_text);

        }
    }

    public myAdapter(ArrayList<aLocation> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mTitle.setText(" "+mDataset.get(position).getTitle());
        holder.mContent.setText(" "+mDataset.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
