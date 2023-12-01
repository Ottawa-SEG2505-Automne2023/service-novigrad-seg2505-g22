package com.example.segprojet;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class Client extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // Only handle login, no need for other actions
        handleLogin(username);

    }

    private void handleLogin(String username) {
        if (TextUtils.isEmpty(username)) {
            // Handle the case where username is empty
            Toast.makeText(Client.this, "Invalid username", Toast.LENGTH_SHORT).show();
            return;
        }

        Query checkUser = myRef.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String succursaleName = snapshot.child(username).child("succursaleName").getValue(String.class);

                if (!TextUtils.isEmpty(succursaleName)) {
                    // Login successful, you can perform any additional actions here if needed
                    Toast.makeText(Client.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle the case where succursaleName is empty
                    Toast.makeText(Client.this, "Select a Succursale first", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
            }
        });
    }
}
