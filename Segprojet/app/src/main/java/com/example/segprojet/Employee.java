package com.example.segprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Employee extends AppCompatActivity {


    private Button selectHours;
    private Button SelectSuccursale;
    private Button selectServices;
    private Button logOut;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);


        selectHours =(Button)findViewById(R.id.selectHours);
        selectServices =(Button)findViewById(R.id.selectServices);
        logOut =(Button)findViewById(R.id.logOut2);
        SelectSuccursale = (Button)findViewById(R.id.SelectSucc);

        boolean confirm=false;
        Intent intent =getIntent();
        String Username = intent.getStringExtra("username");

        SelectSuccursale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.segprojet.SelectSuccursale.class);

                intent.putExtra("Username",Username);

                startActivity(intent);
            }
        });



        selectHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username;
                Query checkUser = myRef.orderByChild("username").equalTo(username);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String SuccursaleName = snapshot.child(username).child("succursaleName").getValue(String.class);
                        Toast.makeText(Employee.this, username+" "+SuccursaleName, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SelectHours.class);

                        if (!TextUtils.isEmpty(SuccursaleName)) {
                            intent.putExtra("SuccursaleName", SuccursaleName);
                            startActivity(intent);
                        } else {

                            //Toast.makeText(Employee.this, "Select A Succursale first", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });

        selectServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username;
                Query checkUser = myRef.orderByChild("username").equalTo(Username);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String SuccursaleName = snapshot.child(username).child("succursaleName").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), AddService.class);

                        if (!TextUtils.isEmpty(SuccursaleName)) {
                            intent.putExtra("SuccursaleName", SuccursaleName);
                            intent.putExtra("username",username);
                            startActivity(intent);
                        } else {

                            Toast.makeText(Employee.this, "Select A Succursale first", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });
    }
}