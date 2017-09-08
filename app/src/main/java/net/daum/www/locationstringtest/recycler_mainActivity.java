package net.daum.www.locationstringtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by thswl on 2017-09-05.
 */

public class recycler_mainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<aLocation> myDataset;

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, recycler_mainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new myAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new aLocation("titld","content"));
        myDataset.add(new aLocation("hello! seoul", "very exiciting place"));
    }
}