package edu.sitengvirginia.stockpricechecker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
//
    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("open")
    @Expose
    public float open;

    @SerializedName("high")
    @Expose
    public float high;

    @SerializedName("low")
    @Expose
    public float low;

    @SerializedName("close")
    @Expose
    public float close;

    @SerializedName("volume")
    @Expose
    public float volume;

    @SerializedName("ex_dividend")
    @Expose
    public float ex_dividend;

    @SerializedName("split_ratio")
    @Expose
    public float split_ratio;

    @SerializedName("adj_factor")
    @Expose
    public float adj_factor;

    @SerializedName("adj_open")
    @Expose
    public float adj_open;

    @SerializedName("adj_high")
    @Expose
    public float adj_high;

    @SerializedName("adj_low")
    @Expose
    public float adj_low;

    @SerializedName("adj_close")
    @Expose
    public float adj_close;

    @SerializedName("adj_volume")
    @Expose
    public float adj_volume;

    public boolean mchecked;


    Data(String date1, float high1, float low1, boolean checked) {
        date = date1;
        high = high1;
        low = low1;
        mchecked = checked;
    }


    public String getDate() {
        return date;
    }

    public float getOpen() {
        return open;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getClose() {
        return close;
    }

    public float getVolume() {
        return volume;
    }

    public float getEx_dividend() {
        return ex_dividend;
    }

    public float getSplit_ratio() {
        return split_ratio;
    }

    public float getAdj_factor() {
        return adj_factor;
    }

    public float getAdj_open() {
        return adj_open;
    }

    public float getAdj_high() {
        return adj_high;
    }

    public float getAdj_low() {
        return adj_low;
    }

    public float getAdj_close() {
        return adj_close;
    }

    public float getAdj_volume() {
        return adj_volume;
    }


}
