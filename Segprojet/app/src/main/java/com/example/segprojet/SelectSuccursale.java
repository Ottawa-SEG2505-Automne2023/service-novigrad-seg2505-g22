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

public class SelectSuccursale extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;
    DatabaseReference myRef2 ;

    private EditText succursaleName;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_succursale);
        succursaleName =(EditText)findViewById(R.id.newSuccursaleName);
        Submit =(Button)findViewById(R.id.SubmitS);


        Intent intent =  getIntent();
        String Username = intent.getStringExtra("Username");



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(succursaleName.getText().toString())) {
                    
                    Succursale succ = new Succursale(succursaleName.getText().toString());
                    myRef= database.getReference("succursales");
                    myRef.child(succursaleName.getText().toString()).setValue(succ);

                    myRef2= database.getReference("users");
                    String username = Username;
                    //Toast.makeText(SelectSuccursale.this, Username, Toast.LENGTH_SHORT).show();
                    Query checkUser = myRef2.orderByChild("username").equalTo(Username);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                           snapshot.child(username).child("succursaleName").getRef().setValue(succursaleName.getText().toString());
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Intent intent = new Intent(getApplicationContext(),Employee.class);
                    intent.putExtra("username",Username);
                    startActivity(new Intent(getApplicationContext(), Employee.class));
                }else {
                    succursaleName.setError("Succursale Name Required");
                    return;
            }


            }
        });


    }
}