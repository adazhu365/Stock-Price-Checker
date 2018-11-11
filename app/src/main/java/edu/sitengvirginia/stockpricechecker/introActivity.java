package edu.sitengvirginia.stockpricechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;

public class introActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "EmailPrefsFile";

    EditText spEmailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_layout);
        spEmailEditText = (EditText) findViewById(R.id.email);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String Email = settings.getString("Email", "a");
        spEmailEditText.setText(Email);
        Button mybutton = findViewById(R.id.button);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent add_intent = new Intent(introActivity.this, MainActivity.class);
                startActivity(add_intent);
            }
        });
    }
    public void saveToSharedPreferences(View view) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Email", spEmailEditText.getText().toString());

        editor.apply();

    }

}
