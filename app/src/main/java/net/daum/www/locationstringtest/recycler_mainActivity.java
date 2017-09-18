package net.daum.www.locationstringtest;

import android.app.Dialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/*
 * Created by thswl on 2017-09-05.
 */

public class recycler_mainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<aLocation> myDataset;

    private ImageView mItemImage;

    DatabaseReference mDataset = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mDataRefti = mDataset.child("Context");

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

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

        mItemImage = (ImageView)findViewById(R.id.imageView);

        myDataset.add(new aLocation("titld","content",mItemImage));
        myDataset.add(new aLocation("hello! seoul", "very exiciting place",mItemImage));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_crime_list, menu);

        return true;

    }

    @Override
    protected void onRestart(){
        super.onRestart();

    }

    @Override
    protected void onStart(){
        super.onStart();


        mDataRefti.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot2){
                String text2 = dataSnapshot2.child("title").getValue(String.class);
                String text1 = dataSnapshot2.child("context").getValue(String.class);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.menu_item_new_crime:
                Toast.makeText(this, "new one", Toast.LENGTH_SHORT).show();
                Intent intent = addData.newIntent(recycler_mainActivity.this);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}