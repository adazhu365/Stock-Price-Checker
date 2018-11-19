package edu.sitengvirginia.stockpricechecker;
import java.io.Serializable;
import java.text.ParseException;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private Button myButton;
    private Button myaddButton;
    private TextView myfavorite;
    RecyclerView rvItems;
    ArrayList<StockItem> myList;
    private Snackbar snackbar;
    static final int req_code = 1;
    StockListAdapter adapter;
    private EditText stockName;
    private ImageButton settingButton;
    private TextView add1;
    private TextView add2;

    String information[] = new String[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Log.e("test", "yaya");
            myList = StockItem.createInitialBucketList();
        }
        else {
            Serializable mylist = savedInstanceState.getSerializable("key");
            myList = (ArrayList<StockItem>) mylist;
        }
        stockName = findViewById(R.id.nameinput);
        //add1 = (TextView) findViewById(R.id.textView20);
//        add1.setText(getIntent().getStringExtra("low"));
//        add2 = (TextView) findViewById(R.id.textView21);
//        add1.setText(getIntent().getStringExtra("high"));


        myButton = findViewById(R.id.button);
        myButton.setText("Detailed Info");


//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String company = stockName.getText().toString();
//
//                Intent add_intent = new Intent(MainActivity.this, API.class);
//
//                // add_intent.putExtra("NAME", company);
//
//                System.out.println(information[0]);
//
//                add_intent.putExtra("high", information[0]);
//
//                startActivity(add_intent);
//
//            }
//        });


        myaddButton = findViewById(R.id.addbutton);
        myaddButton.setText("Add Stock to Favorite");
        myfavorite = findViewById(R.id.favorite);
        myfavorite.setText("Favorite");

        settingButton = findViewById(R.id.settingbutton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent add_intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(add_intent, req_code);
            }
        });

        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        adapter = new StockListAdapter(this, myList);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    public void downloadData(View view) {

        Log.e("tag2", "yay");
        // Add your code here to download the data and update the screen

        LousListAPIInterface apiService =
                LousListAPIClient.getClient().create(LousListAPIInterface.class);
        Log.e("tag4", "HEREEE");

        Call<stockkey> call = apiService.get(stockName.getText().toString(), "OjQxNmI3YWI5MDhiYjU5ZDllMTk4MTBmZDM0YjQzZDU2");
        //  Call<stockkey> call = apiService.sectionList();

        Log.e("tag5", "122222");


        call.enqueue(new Callback<stockkey>() {

            @Override
            public void onResponse(Call<stockkey> call, Response<stockkey> response) {
                stockkey sections = response.body();
                Log.d("TAG3", response.body().toString());

                information[0] = String.valueOf(sections.getData().get(0).getHigh());
                information[1] = String.valueOf(sections.getData().get(0).getLow());
                information[2] = String.valueOf(sections.getData().get(0).getClose());
                information[3] = String.valueOf(sections.getData().get(0).getVolume());
                information[4] = String.valueOf(sections.getData().get(0).getEx_dividend());
                information[5] = String.valueOf(sections.getData().get(0).getSplit_ratio());


                Intent add_intent = new Intent(MainActivity.this, API.class);

                // add_intent.putExtra("NAME", company);

                System.out.println(information[0]);

                add_intent.putExtra("NAME", stockName.getText().toString());


                add_intent.putExtra("high", information[0]);
                add_intent.putExtra("low", information[1]);
                add_intent.putExtra("close", information[2]);
                add_intent.putExtra("volume", information[3]);
                add_intent.putExtra("ex", information[4]);
                add_intent.putExtra("split", information[5]);



                startActivity(add_intent);

            }

            @Override
            public void onFailure(Call<stockkey> call, Throwable t) {
                // Log error here since request failed
                Log.e("LousList", t.toString());
            }
        });

        // return info;
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                Log.e("test", name);
                String currentprice = data.getStringExtra("currentprice");
                String todaylow = data.getStringExtra("todaylow");
                String todayhigh = data.getStringExtra("todayhigh");
                StockItem b = StockItem.createStockItem(name, currentprice, todaylow, todayhigh,false);


                myList.add(b);
                Log.e("test2", name);
                adapter.notifyDataSetChanged();
            }
        }
        /*if (requestCode == 2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                String name = data.getStringExtra("name");
                Log.e("test", name);
                String description = data.getStringExtra("description");
                String latitude = data.getStringExtra("latitude");
                String longitude = data.getStringExtra("longitude");
                String date = data.getStringExtra("date");
                int position = data.getIntExtra("position", 0);
                boolean checked = data.getBooleanExtra("checked", false);
                StockItem b = StockItem.editStockItem(myList.get(position), name, description, latitude, longitude, date, checked);
                myList.remove(myList.get(position));


                myList.add(b);
                Log.e("test2", name);
                adapter.notifyDataSetChanged();
            }
        }
        */
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("key", myList);
        Log.e("test", savedInstanceState.toString());
        Log.e("test", "yyy");
        super.onSaveInstanceState(savedInstanceState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Serializable mylist = savedInstanceState.getSerializable("key");

        myList = (ArrayList<StockItem>) mylist;
    }

}
