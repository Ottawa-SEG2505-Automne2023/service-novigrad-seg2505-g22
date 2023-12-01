package com.example.segprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectHours extends AppCompatActivity {

    private EditText mondayStart;
    private EditText mondayEnd;
    private EditText tuesdayStart;
    private EditText tuesdayEnd;
    private EditText wednesdayStart;
    private EditText wednesdayEnd;
    private EditText thursdayStart;
    private EditText thursdayEnd;
    private EditText fridayStart;
    private EditText fridayEnd;
    private ArrayList <String> hours = new ArrayList<>();
    private Button submitHours;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("succursales");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hours);

        mondayStart = (EditText)findViewById(R.id.editTextTime);
        tuesdayStart =(EditText)findViewById(R.id.editTextTime3);
        wednesdayStart =(EditText)findViewById(R.id.editTextTime4);
        thursdayStart =(EditText)findViewById(R.id.editTextTime5);
        fridayStart  =(EditText)findViewById(R.id.editTextTime6);
        mondayEnd  =(EditText)findViewById(R.id.editTextTime8);
        tuesdayEnd =(EditText)findViewById(R.id.editTextTime7);
        wednesdayEnd =(EditText)findViewById(R.id.editTextTime9);
        thursdayEnd =(EditText)findViewById(R.id.editTextTime11);
        fridayEnd =(EditText)findViewById(R.id.editTextTime10);
        submitHours =(Button)findViewById(R.id.submitHours);

        submitHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mondayStart.getText().toString())){
                    mondayStart.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(mondayEnd.getText().toString())){
                    mondayEnd.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(tuesdayStart.getText().toString())){
                    tuesdayStart.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(tuesdayEnd.getText().toString())){
                    tuesdayEnd.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(wednesdayStart.getText().toString())){
                    wednesdayStart.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(wednesdayEnd.getText().toString())){
                    wednesdayEnd.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(thursdayStart.getText().toString())){
                    thursdayStart.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(thursdayEnd.getText().toString())){
                    thursdayEnd.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(fridayStart.getText().toString())){
                    fridayStart.setError("Required");
                    return;
                } else if (TextUtils.isEmpty(fridayEnd.getText().toString())){
                    fridayEnd.setError("Required");
                    return;
                } else {


                        hours.add(0,mondayStart.getText().toString());
                        hours.add(1,mondayEnd.getText().toString());
                        hours.add(2,tuesdayStart.getText().toString());
                        hours.add(3,tuesdayEnd.getText().toString());
                        hours.add(4,wednesdayStart.getText().toString());
                        hours.add(5,wednesdayEnd.getText().toString());
                        hours.add(6,thursdayStart.getText().toString());
                        hours.add(7,thursdayEnd.getText().toString());
                        hours.add(8,fridayStart.getText().toString());
                        hours.add(9,fridayEnd.getText().toString());



                    String Hours [][] = new String [5][2];


                    Intent intent = getIntent();
                    String name =intent.getStringExtra("SuccursaleName");
                    String Username = intent.getStringExtra("username");

                    Query checkUser = myRef.orderByChild("name").equalTo(name);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                           snapshot.child(name).child("Hours").getRef().setValue(hours);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Intent intent2 = new Intent(getApplicationContext(),Employee.class);
                    intent2.putExtra("username",Username);
                    startActivity(intent2);

                }
            }
        });







    }
}