package net.daum.www.locationstringtest;

import android.content.Intent;
import android.graphics.Picture;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by thswl on 2017-09-05.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private ArrayList<aLocation> mDataset;

    //앨범으로 전송시 돌려받을 번호
    static int REQUEST_PHOTO_ALBUM = 2;
    //첫번째 이미지 아이콘 샘플 이다.
    static String SAMPLEIMG = "ic_launcher.png";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;

        private Button mImageLoadButton;
        private ImageView iv;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title_text);
            mContent = (TextView) view.findViewById(R.id.content_text);

            mImageLoadButton = (Button) view.findViewById(R.id.photoAlbum);
            iv = (ImageView) view.findViewById(R.id.imgView);

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(" " + mDataset.get(position).getTitle());
        holder.mContent.setText(" " + mDataset.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
