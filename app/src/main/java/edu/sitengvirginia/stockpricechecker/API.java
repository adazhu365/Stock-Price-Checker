package edu.sitengvirginia.stockpricechecker;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class API extends AppCompatActivity {

    TextView courseNameTextView;
    TextView todaylow;
    TextView todayhigh;
    TextView todayclose;
    TextView volume;
    TextView ex_dividend;
    TextView split_ratio;
    private Button myButton;
    private Button addFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        courseNameTextView = (TextView) findViewById(R.id.textView2);
        todayhigh = (TextView) findViewById(R.id.textView5);
        todaylow = (TextView) findViewById(R.id.textView6);
        todayclose = (TextView) findViewById(R.id.textView10);
        volume = (TextView) findViewById(R.id.textView12);
        ex_dividend = (TextView) findViewById(R.id.textView14);
        split_ratio = (TextView) findViewById(R.id.textView16);

        myButton = findViewById(R.id.button4);
        addFavorite = findViewById(R.id.button10);


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_intent = new Intent(API.this, MainActivity.class);
                startActivity(add_intent);
            }
        });

        addFavorite.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                float low = Float.valueOf(todaylow.getText().toString());
                float high = Float.valueOf(todayhigh.getText().toString());

                Intent add_intent = new Intent(API.this, MainActivity.class);
                add_intent.putExtra("low", low);
                add_intent.putExtra("high", high);


                startActivity(add_intent);
            }
        });
        courseNameTextView.setText(getIntent().getStringExtra("NAME"));
        todayhigh.setText(getIntent().getStringExtra("high"));
        todaylow.setText(getIntent().getStringExtra("low"));
        todayclose.setText(getIntent().getStringExtra("close"));
        volume.setText(getIntent().getStringExtra("volume"));
        ex_dividend.setText(getIntent().getStringExtra("ex"));
        split_ratio.setText(getIntent().getStringExtra("split"));
    }




}
