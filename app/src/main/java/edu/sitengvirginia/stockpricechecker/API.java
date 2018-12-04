package edu.sitengvirginia.stockpricechecker;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
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
import edu.sitengvirginia.stockpricechecker.MainActivity;

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

        TextView text = findViewById(R.id.textView7);
        text.setTextSize(MainActivity.textsize);
        TextView text2 = findViewById(R.id.textView8);
        text2.setTextSize(MainActivity.textsize);
        TextView text3 = findViewById(R.id.textView9);
        text3.setTextSize(MainActivity.textsize);
        TextView text4 = findViewById(R.id.textView11);
        text4.setTextSize(MainActivity.textsize);
        TextView text5 = findViewById(R.id.textView13);
        text5.setTextSize(MainActivity.textsize);
        TextView text6 = findViewById(R.id.textView15);
        text6.setTextSize(MainActivity.textsize);




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
                final Button button = findViewById(R.id.button);
                //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                TextView todayhigh = findViewById(R.id.textView5);
                TextView todaylow = findViewById(R.id.textView6);
                TextView Close = findViewById(R.id.textView10);
                TextView name = findViewById(R.id.textView2);
                //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name.getText().toString());
                resultIntent.putExtra("todaylow", todaylow.getText().toString());
                resultIntent.putExtra("todayhigh", todayhigh.getText().toString());
                resultIntent.putExtra("Close", Close.getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();


                //Intent add_intent = new Intent(API.this, MainActivity.class);
                //add_intent.putExtra("low", low);
                //add_intent.putExtra("high", high);
            }
        });
        courseNameTextView.setText(getIntent().getStringExtra("NAME"));
        courseNameTextView.setTextSize(MainActivity.textsize);
        todayhigh.setText(getIntent().getStringExtra("high"));
        todayhigh.setTextSize(MainActivity.textsize);
        todaylow.setText(getIntent().getStringExtra("low"));
        todaylow.setTextSize(MainActivity.textsize);
        todayclose.setText(getIntent().getStringExtra("close"));
        todayclose.setTextSize(MainActivity.textsize);
        volume.setText(getIntent().getStringExtra("volume"));
        volume.setTextSize(MainActivity.textsize);
        ex_dividend.setText(getIntent().getStringExtra("ex"));
        ex_dividend.setTextSize(MainActivity.textsize);
        split_ratio.setText(getIntent().getStringExtra("split"));
        split_ratio.setTextSize(MainActivity.textsize);
    }




}
