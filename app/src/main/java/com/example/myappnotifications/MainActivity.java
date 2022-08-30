package com.example.myappnotifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentNotifications fragmentNotifications = FragmentNotifications.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container_main, fragmentNotifications).commit();


    }


    public void showMyDialogResult(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}