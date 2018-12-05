package edu.sitengvirginia.stockpricechecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.annotation.NonNull;

public class MainActivity extends AppCompatActivity{

    public static float textsize = 16;
    private Button myButton;
    private Button myaddButton;
    private Button myDeleteButton;
    private Button loadButton;
    private TextView myfavorite;
    RecyclerView rvItems;
    ArrayList<StockItem> myList = new ArrayList<StockItem>();
    static final int req_code = 1;
    StockListAdapter adapter;
    private EditText stockName;
    private ImageButton settingButton;
    private final String filenameExternal = "StockSummary.txt";


    String information[] = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_data_sync, false);
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);

        String fontsize = sharedPref.getString("example_text", "16sp");
        if (fontsize.equals("12sp")) {
            textsize = 12;
        }
        if (fontsize.equals("16sp")) {
            textsize = 16;
        }
        if (fontsize.equals("20sp")) {
            textsize = 20;
        }
        if (fontsize.equals("24sp")) {
            textsize = 24;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFromDatabase();

        stockName = findViewById(R.id.nameinput);

        myButton = findViewById(R.id.button);
        myButton.setText("Detailed Info");

        myaddButton = findViewById(R.id.addbutton);
        myaddButton.setText("Email Summary");
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

    public void updateData(View view) {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean b = false;

        for (int i=0; i<myList.size(); i++) {
            final int k = i;
            LousListAPIInterface apiService =
                    LousListAPIClient.getClient().create(LousListAPIInterface.class);
            final String name = myList.get(i).getMname();
            Call<stockkey> call = apiService.get(myList.get(i).getMname(), "OjQxNmI3YWI5MDhiYjU5ZDllMTk4MTBmZDM0YjQzZDU2");
            call.enqueue(new Callback<stockkey>() {
                @Override
                public void onResponse(@NonNull Call<stockkey> call, @NonNull Response<stockkey> response) {
                    stockkey sections = response.body();
                    if (sections != null && !sections.getData().isEmpty()) {

                        information[0] = String.valueOf(sections.getData().get(0).getHigh());
                        information[1] = String.valueOf(sections.getData().get(0).getLow());
                        information[2] = String.valueOf(sections.getData().get(0).getClose());
                        if(information[0] != null) {
                            String[] args = {name};
                            ContentValues values = new ContentValues();
                            values.put("currentval", information[0]);
                            values.put("todaylow", information[1]);
                            values.put("todayhigh", information[2]);
                            db.update("stock", values, "name=?", args);
                            myList.get(k).setMtodayhigh(information[0]);
                            myList.get(k).setMtodaylow(information[1]);
                            myList.get(k).setMcurrentprice(information[2]);
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "The input stock name does not exist.",
                                Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<stockkey> call, Throwable t) {

                }
            });


        }

        adapter.notifyDataSetChanged();
    };

    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted1");
                return true;
            } else {

                // Log.v(TAG,"Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            // Log.v(TAG,"Permission is granted1");
            return true;
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

            }
            return checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;

        }
        return false;



    }
    public void writeFileExternalStorage(View view) {
        String info2 = "";

        for ( StockItem i:myList){
            info2 = info2 + "stock name: " + i.getMname() + " " +"today low: " +  i.getMtodaylow() + " " +
                    "current price : " + i.getMcurrentprice() +" " + "today high: " + i.getMtodayhigh() + "\n";

        }


        String state = Environment.getExternalStorageState();

        if (isWriteStoragePermissionGranted()) {
            if (!Environment.MEDIA_MOUNTED.equals(state)) {

                return;
            }



            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), filenameExternal);


            FileOutputStream outputStream = null;
            try {

                //file.mkdirs();
                file.getParentFile().mkdirs();

                file.createNewFile();

                //second argument of FileOutputStream constructor indicates whether to append or create new file if one exists
                outputStream = new FileOutputStream(file, true);

                PrintWriter cleann = new PrintWriter(file);
                cleann.close();

                outputStream.write(info2.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());

            Uri path = Uri.fromFile(file);
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("vnd.android.cursor.dir/email");


            SharedPreferences email = getSharedPreferences("EmailPrefsFile", 0);

            String to[] = {email.getString("Email", "")};
            emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
            emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
            emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(emailIntent , "Send email..."));
        };

        //external storage availability check


    }


    public void delete(View view) {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ArrayList<StockItem> newList = new ArrayList<StockItem>();
        for (int i = 0; i<myList.size(); i++) {
            if (myList.get(i).getMchecked()) {
                String[] args = {myList.get(i).getMname()};
                newList.add(myList.get(i));
                db.delete("stock", "name=?", args);
            }

        }
        myList.removeAll(newList);

        //myList = new ArrayList<StockItem>();
        //loadFromDatabase();
        adapter.notifyDataSetChanged();

    }
    public void downloadData(View view) {

        LousListAPIInterface apiService =
                LousListAPIClient.getClient().create(LousListAPIInterface.class);

        Call<stockkey> call = apiService.get(stockName.getText().toString(), "OjQxNmI3YWI5MDhiYjU5ZDllMTk4MTBmZDM0YjQzZDU2");
        call.enqueue(new Callback<stockkey>() {
            @Override
            public void onResponse(@NonNull Call<stockkey> call, @NonNull Response<stockkey> response) {
                stockkey sections = response.body();
                if(sections != null && !sections.getData().isEmpty()) {
                    Log.e("sections", sections.getData().toString());
                    information[0] = String.valueOf(sections.getData().get(0).getHigh());
                    information[1] = String.valueOf(sections.getData().get(0).getLow());
                    information[2] = String.valueOf(sections.getData().get(0).getClose());
                    information[3] = String.valueOf(sections.getData().get(0).getVolume());
                    information[4] = String.valueOf(sections.getData().get(0).getEx_dividend());
                    information[5] = String.valueOf(sections.getData().get(0).getSplit_ratio());


                    Intent add_intent = new Intent(MainActivity.this, API.class);

                    // add_intent.putExtra("NAME", company);
                    add_intent.putExtra("NAME", stockName.getText().toString());
                    add_intent.putExtra("high", information[0]);
                    add_intent.putExtra("low", information[1]);
                    add_intent.putExtra("close", information[2]);
                    add_intent.putExtra("volume", information[3]);
                    add_intent.putExtra("ex", information[4]);
                    add_intent.putExtra("split", information[5]);

                    startActivityForResult(add_intent, req_code);
                }
                else {
                    Toast.makeText(getApplicationContext(),"The input stock name does not exist, make sure" +
                                    " you are inputting stock name not company name",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<stockkey> call, Throwable t) {
                // Log error here since request failed
                Log.e("LousList", t.toString());
            }
        });



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

        ArrayList<StockItem> list = new ArrayList<StockItem>();
        //ArrayList<String> current;
        //ArrayList<String> todayhigh;
        //ArrayList<String> todaylow;
        while(cursor.moveToNext()) {

            String currID = cursor.getString(
                    cursor.getColumnIndexOrThrow("name"));
            String currentprice = cursor.getString(
                    cursor.getColumnIndexOrThrow("currentval"));
            String todaylow = cursor.getString(
                    cursor.getColumnIndexOrThrow("todaylow"));
            String todayhigh = cursor.getString(
                    cursor.getColumnIndexOrThrow("todayhigh"));
            StockItem b = StockItem.createStockItem(currID, currentprice, todaylow, todayhigh, false);
            list.add(b);
        }
        myList = list;

    }

    public void loadFromDatabase() {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String sortOrder =
                "name" + " DESC";
        String[] projection = {
                "name",
                "currentval",
                "todaylow",
                "todayhigh",
        };

        Cursor cursor = db.query(
                "stock",  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<StockItem> list = new ArrayList<StockItem>();
        while(cursor.moveToNext()) {

            String currID = cursor.getString(
                    cursor.getColumnIndexOrThrow("name"));
            String currentprice = cursor.getString(
                    cursor.getColumnIndexOrThrow("currentval"));
            String todaylow = cursor.getString(
                    cursor.getColumnIndexOrThrow("todaylow"));
            String todayhigh = cursor.getString(
                    cursor.getColumnIndexOrThrow("todayhigh"));
            StockItem b = StockItem.createStockItem(currID, currentprice, todaylow, todayhigh, false);
            list.add(b);
        }
        myList = list;

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
                DatabaseHelper mDbHelper = new DatabaseHelper(this);
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("currentval", close);
                values.put("todaylow", todaylow);
                values.put("todayhigh", todayhigh);
                db.insert("stock", "null", values);
                myList.add(b);
                adapter.notifyDataSetChanged();

            }
        }

    }

}
