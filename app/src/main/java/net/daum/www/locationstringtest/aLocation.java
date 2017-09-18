package net.daum.www.locationstringtest;

import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by thswl on 2017-09-03.
 */

public class aLocation {
    private double mAltitude;
    private double mLongtitude;

    private String mTitle;
    private String mContent;
    private ImageView mListImage;

    public aLocation(String mA, String mL, ImageView mI){
        this.mTitle=mA;
        this.mContent=mL;
        this.mListImage=mI;
    }

    public aLocation(){
        this.mAltitude=0;
        this.mLongtitude=0;

        this.mTitle=null;
        this.mContent=null;
        this.mListImage=null;
    }

    public double getAltitude() {
        return mAltitude;
    }

    public void setAltitude(double altitude) {
        mAltitude = altitude;
    }

    public double getLongtitude() {
        return mLongtitude;
    }

    public void setLongtitude(double longtitude) {
        mLongtitude = longtitude;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mtitle) {
        this.mTitle = mtitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public ImageView getListImage() {
        return mListImage;
    }

    public void setListImage(ImageView listImage) {
        mListImage = listImage;
    }
}
