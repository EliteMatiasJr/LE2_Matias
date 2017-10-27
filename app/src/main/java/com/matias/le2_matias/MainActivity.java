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
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tvUsername, twPassword, tvDisplay;
    EditText etUsername, etPassword;
    Button btnShared, btnInternal, btnNext;
    SharedPreferences preference;
    FileOutputStream fos;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        preference = getSharedPreferences("test", Context.MODE_WORLD_READABLE);
    }

    public void saveShared(View view) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("user",etUsername.getText().toString());
        editor.putString("pwd",etPassword.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved in Shared Preferences!",Toast.LENGTH_LONG).show();
    }

    public void saveInternal(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        try {
            fos = openFileOutput("output1.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos = openFileOutput("output2.txt", Context.MODE_PRIVATE);
            fos.write(password.getBytes());
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();
    }

    public void nextActivity (View view){
        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }


}
