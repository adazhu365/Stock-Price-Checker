package edu.sitengvirginia.stockpricechecker;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StockItem implements Serializable{
    private String mname;
    private String mcurrentprice;
    private String mtodaylow;
    private String mtodayhigh;
    private boolean mchecked;

    private StockItem(String name, String currentprice, String todaylow, String todayhigh, boolean checked) {
        mname = name;
        mcurrentprice = currentprice;
        mtodayhigh = todayhigh;
        mtodaylow = todaylow;
        mchecked = checked;
    }

    public static StockItem createStockItem(String name, String currentprice, String todaylow, String todayhigh, boolean checked) {
        StockItem b = new StockItem(name, currentprice, todaylow, todayhigh, checked);
        return b;
    }

    public String getMname() {
        return mname;
    }
    public String getMcurrentprice() {
        return mcurrentprice;
    }
    public String getMtodaylow() {
        return mtodaylow;
    }
    public String getMtodayhigh() {
        return mtodayhigh;
    }
    public void setMchecked(boolean val){this.mchecked=val; }

    public boolean getMchecked() {
        return mchecked;
    }


    public static ArrayList<StockItem> createInitialBucketList() {
        ArrayList<StockItem> initialList = new ArrayList<StockItem>();
        initialList.add(new StockItem("Apple", "230", "225", "240", false));

        initialList.add(new StockItem("IBM", "100", "99", "101", false));

        initialList.add(new StockItem("Tesla", "350", "320", "360", false));

        return initialList;
    }
}
