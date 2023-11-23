package com.example.segprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    private EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        textView = (EditText) findViewById(R.id.textView);

        Intent intent = getIntent();

        String username ="welcome, "+ intent.getStringExtra("username");

        textView.setText(username);

    }
}