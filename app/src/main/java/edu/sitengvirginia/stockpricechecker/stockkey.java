package edu.sitengvirginia.stockpricechecker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class stockkey {

    @SerializedName("data")
    @Expose
    private List<Data> data;

    @SerializedName("result_count")
    @Expose
    private int result_count;

    @SerializedName("page_size")
    @Expose
    private int page_size;

    @SerializedName("current_page")
    @Expose
    private int current_page;

    @SerializedName("total_pages")
    @Expose
    private int total_pages;

    @SerializedName("api_call_credits")
    @Expose
    private int api_call_credits;


    public int getResult_count() {
        return result_count;
    }

    public int getPage_size() {
        return page_size;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getApi_call_credits() {
        return api_call_credits;
    }


    public List<Data> getData() {
        return data;
    }

}
