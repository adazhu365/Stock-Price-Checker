package edu.sitengvirginia.stockpricechecker;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StockItem implements Serializable{
    private String mname;
    private String mdescription;
    private String mlatitude;
    private String mlongitude;
    private String mdate;
    private boolean mchecked;

    private StockItem(String name, String description, String latitiude, String longitude, String date, boolean checked) {
        mname = name;
        mdescription = description;
        mlatitude = latitiude;
        mlongitude = longitude;
        mdate = date;
        mchecked = checked;
    }

    public static StockItem createStockItem(String name, String description, String latitude, String longitude, String date, boolean checked) {
        StockItem b = new StockItem(name, description, latitude, longitude, date, checked);
        return b;
    }

    public static StockItem editStockItem(StockItem b, String name, String description, String latitude, String longitude, String date, boolean checked) {
        b = new StockItem(name, description, latitude, longitude, date, checked);
        return b;
    }

    public String getMname() {
        return mname;
    }
    public String getMdescription() {
        return mdescription;
    }
    public String getMlatitude() {
        return mlatitude;
    }
    public String getMlongitude() {
        return mlongitude;
    }
    public String getMdate() {
        return mdate;
    }
    public void setMchecked(boolean val){this.mchecked=val; }

    public boolean getMchecked() {
        return mchecked;
    }


    public static ArrayList<StockItem> createInitialBucketList() {
        ArrayList<StockItem> initialList = new ArrayList<StockItem>();
        initialList.add(new StockItem("Last Year", "whatever", "37.3", "38.4", "2017/7/27", false));

        initialList.add(new StockItem("Last Year2", "whatever", "37.3", "38.4", "2018/8/27", false));

        initialList.add(new StockItem("Last Year3", "whatever", "37.3", "38.4", "2018/10/27", false));

        return initialList;
    }
}
