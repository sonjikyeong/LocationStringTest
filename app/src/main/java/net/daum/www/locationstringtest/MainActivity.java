package net.daum.www.locationstringtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ERROR = 0;
    private static final String TAG = "LocationStringTest";

    private GoogleApiClient mClient;
    private Button mLocationFindButton;
    private TextView mLongtitude;
    private TextView mAltitude;
    private Button mStartButton;
    private aLocation mLocation = new aLocation();

    DatabaseReference mLocationRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mlocationRef = mLocationRef.child("Location");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        invalidateOptionsMenu();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .build();

        mLocationFindButton = (Button) findViewById(R.id.button);
        mAltitude = (TextView) findViewById(R.id.altitude);
        mLongtitude = (TextView) findViewById(R.id.longtitude);

        mLocationFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findImage();
                mlocationRef.setValue("Altitude is.."+ mLocation.getAltitude()+" and Longtitude is "+ mLocation.getLongtitude());
                //mAltitude.setText("Altitude is.. " + mLocation.getAltitude());
                //mLongtitude.setText("Longtitude is.." + mLocation.getLongtitude());
            }
        });

        mStartButton = (Button) findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = recycler_mainActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode, this, REQUEST_ERROR,
                    new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //서비스를 사용할 수 없으면 실행을 중단한다.
                            finish();
                        }
                    });

            errorDialog.show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        invalidateOptionsMenu();
        mClient.connect();

        mlocationRef.addValueEventListener(new ValueEventListener(){
            @Override
                    public void onDataChange(DataSnapshot dataSnapshot2){
                String text2 = dataSnapshot2.getValue(String.class);
                mAltitude.setText(text2);
                mLongtitude.setText(text2);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        mClient.disconnect();
    }

    private void findImage() {
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setNumUpdates(1);
        request.setInterval(0);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi
                .requestLocationUpdates(mClient, request, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.i(TAG, "Got a fix: " + location);
                        mLocation.setAltitude(location.getAltitude());
                        mLocation.setLongtitude(location.getLongitude());
                    }
                });
    }
}

