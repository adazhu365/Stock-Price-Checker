package edu.sitengvirginia.stockpricechecker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
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
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private Button myButton;
    private Button myaddButton;
    private Button myDeleteButton;
    private Button loadButton;
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

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_data_sync, false);

        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String marketPref = sharedPref.getString("sync_frequency", "-1");
        Toast.makeText(this, marketPref, Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = new ArrayList<StockItem>();
        loadFromDatabase();
        Log.e("list", myList.toString());
        /*if (savedInstanceState == null) {
            myList = StockItem.createInitialBucketList();// may replace the values
        }
        else {
            Serializable mylist = savedInstanceState.getSerializable("key");
            myList = (ArrayList<StockItem>) mylist;
        }
        */
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
        myDeleteButton = findViewById(R.id.deletebutton);
        myDeleteButton.setText("Delete");
        myfavorite = findViewById(R.id.favorite);

        myfavorite.setText("Favorite");
        String FILENAME = "hello_file2";
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            ArrayList<StockItem> a = new ArrayList<StockItem>();
            while((line = reader.readLine()) != null){
                //Log.e("yes", line);

            }
            fis.close();
        }catch(Exception e) {
            File file = new File(FILENAME);
            Log.e("StorageExample", e.getMessage());
        }
        settingButton = findViewById(R.id.settingbutton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent add_intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(add_intent, req_code);
            }
        });

        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        adapter = new StockListAdapter(this, myList);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }
    public void delete(View view) {
        Log.e("delete", "da");
        for (int i = 0; i<myList.size(); i++) {
            Log.e("delete", "ddadad");
            if (myList.get(i).getMchecked()) {
                myList.remove(myList.get(i));
            }

        }


        saveToDatabase();
        myList = new ArrayList<StockItem>();
        loadFromDatabase();
        adapter.notifyDataSetChanged();

    }
    public void downloadData(View view) {

        //Log.e("tag2", "yay");
        // Add your code here to download the data and update the screen

        LousListAPIInterface apiService =
                LousListAPIClient.getClient().create(LousListAPIInterface.class);
        //Log.e("tag4", "HEREEE");

        Call<stockkey> call = apiService.get(stockName.getText().toString(), "OjQxNmI3YWI5MDhiYjU5ZDllMTk4MTBmZDM0YjQzZDU2");
        //  Call<stockkey> call = apiService.sectionList();

        //Log.e("tag5", "122222");


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

                startActivityForResult(add_intent, req_code);

            }

            @Override
            public void onFailure(Call<stockkey> call, Throwable t) {
                // Log error here since request failed
                Log.e("LousList", t.toString());
            }
        });

        // return info;
    };

    public void saveToDatabase() {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i=0; i<myList.size(); i++) {
            values.put("name", myList.get(i).getMname());
            values.put("currentval", myList.get(i).getMcurrentprice());
            values.put("todaylow", myList.get(i).getMtodaylow());
            values.put("todayhigh", myList.get(i).getMtodayhigh());

        }
        long newRowId;
        newRowId = db.insert(
                "stock",
                null,
                values);

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "name",
                "currentval",
                "todaylow",
                "todayhigh",
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                "name" + " DESC";


        Cursor cursor = db.query(
                "stock",  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        while(cursor.moveToNext()) {
            String currID = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );
            Log.e("DBData", currID);
        }
        String FILENAME = "hello_file2";
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        }catch(Exception e) {
            Log.e("StorageExample0", e.getMessage());
        }
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (int i=0; i<myList.size(); i++) {
                String string = myList.get(i).getMname() + " " + myList.get(i).getMcurrentprice()
                        + " " + myList.get(i).getMtodaylow() + " " + myList.get(i).getMtodayhigh();
                fos.write(string.getBytes());
                fos.write("\n".getBytes());
            }
            fos.close();
        }catch(Exception e) {
            Log.e("StorageExample1", e.getMessage());
        }

    }

    public void loadFromDatabase() {
        String FILENAME = "hello_file2";
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            ArrayList a = new ArrayList();
            while((line = reader.readLine()) != null){
                a.add(line);
            }
            Log.e("loadfromstorage", a.toString());
            fis.close();
            for (int i=0; i<a.size(); i++) {
                String[] splited = a.get(i).toString().split("\\s+");
                Log.e("test", a.toString());
                StockItem b = StockItem.createStockItem(splited[0], splited[1], splited[2], splited[3],false);
                myList.add(b);
            }
        }catch(Exception e) {
            File file = new File(FILENAME);
            //myList = StockItem.createInitialBucketList();
            Log.e("StorageExample2", e.getMessage());

        }



        // Add code here to load from the database

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String close = data.getStringExtra("Close");
                String todaylow = data.getStringExtra("todaylow");
                String todayhigh = data.getStringExtra("todayhigh");
                StockItem b = StockItem.createStockItem(name, close, todaylow, todayhigh,false);


                myList.add(b);
                saveToDatabase();
                adapter.notifyDataSetChanged();

            }
        }

    }
    /*@Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("key", myList);
        super.onSaveInstanceState(savedInstanceState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Serializable mylist = savedInstanceState.getSerializable("key");

        myList = (ArrayList<StockItem>) mylist;
    }
*/
}
