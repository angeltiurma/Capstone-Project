package com.dicoding.nettoietonvisage.treatment;

import android.os.Parcel;
import android.os.Parcelable;

public class Level implements Parcelable {
    private String levell;
    private String description;
    private String recommendation;

    public Level(){

    }
    public String getLevell() {
        return levell;
    }

    public void setLevell(String levell) {
        this.levell = levell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    protected Level(Parcel in) {
        levell = in.readString();
        description = in.readString();
        recommendation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(levell);
        dest.writeString(description);
        dest.writeString(recommendation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public com.dicoding.nettoietonvisage.treatment.Level createFromParcel(Parcel in) {
            return new com.dicoding.nettoietonvisage.treatment.Level(in);
        }

        @Override
        public com.dicoding.nettoietonvisage.treatment.Level[] newArray(int size) {
            return new com.dicoding.nettoietonvisage.treatment.Level[size];
        }
    };
}
