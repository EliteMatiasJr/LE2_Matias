package com.matias.le2_matias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SubActivity extends AppCompatActivity {

    Button btnLoadShared, btnLoadInternal, btnClear, btnBack;
    TextView tvDisplay;
    SharedPreferences preference;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        btnLoadShared = (Button) findViewById(R.id.btn_loadShared);
        btnLoadInternal = (Button) findViewById(R.id.btn_loadInternal);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnBack = (Button) findViewById(R.id.btn_back);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        preference = getSharedPreferences("test", Context.MODE_WORLD_READABLE);
    }

    public void loadShared(View view) {
        String user = preference.getString("user","");
        String pwd = preference.getString("pwd","");
        tvDisplay.setText("Username: " + user + " Password: " + pwd);
    }

    public void loadInternal(View view) {
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output1.txt");
            while((read = fis.read()) != -1) {
                buffer1.append((char)read);
            }
            fis = openFileInput("output2.txt");
            while((read = fis.read()) != -1) {
                buffer2.append((char)read);
            }
            fis.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText("Username: " + buffer1.toString() + " Password: " + buffer2.toString());
    }

    public void clearAll(View view) {
        tvDisplay.setText("");
        Toast.makeText(this, "Cleared Displayed Information!", Toast.LENGTH_LONG).show();
    }

    public void backActivity (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
